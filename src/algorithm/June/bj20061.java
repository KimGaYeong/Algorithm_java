package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
둘째 줄부터 N개의 줄에 블록을 놓은 정보가 한 줄에 하나씩 순서대로 주어지며, t x y와 같은 형태이다.

t = 1: 크기가 1×1인 블록을 (x, y)에 놓은 경우
t = 2: 크기가 1×2인 블록을 (x, y), (x, y+1)에 놓은 경우
t = 3: 크기가 2×1인 블록을 (x, y), (x+1, y)에 놓은 경우
 */

// [문제 풀기 전 생각]
// 블록을 한 번 놓을 때마다 지워지는 행/열이 있는지 확인하자.
// 일단 생각해보면 테트리스 두개를 겹쳐놨다고 생각하면 될듯. 빨강에서 내려가는 형태의 테트리스로 생각하면 될 것 같다.
// 그런데 빨간 타일을 기준으로 초록, 파란 타일은 좌우로 대칭되는 형태이다. (약간 빨간 타일이 머리고.. 초록 파랑이 양손느낌..?)
//
// -> 초록, 파란 타일을 가로 4, 세로 6인 배열로 선언한 다음
// 빨간 타일에 놓은 블록이 밀릴 때 타일의 좌표만 좌우 대칭 시켜서 (?) 각자 한테 보내면 될 것 같다. (t=1일때)
// 라는 것이 나의 생각..

//* 하나 더 생각해야될 거는 t가 2,3인 경우 파랑의 모양이 ㅣ <-> ㅡ 이렇게 변해서 내려온다는거..
// 빨강에서 ㅣ(ㅡ)를 주면 초록이는 그냥 내리면 되는데 파랑이는 ㅡ(ㅣ)로 내려온다. 이거 추가

public class bj20061 {
    static int N; //블록을 놓은 횟수
    static int[][] green, blue;
    static int t,x,y;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1082.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        green = new int[6][4]; //가로4 세로6 테트리스
        blue = new int[6][4];

        int cnt = N;
        while(cnt-->0){
            st = new StringTokenizer(br.readLine());

            t = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            //초록이 떨어트리고 t==2,3인 경우 좌우대칭해서 파랑이 떨어트리기
            go(green, t, x, y);
            if(t==2) t=3;
            else if(t==3) t=2;
            go(blue, t, y, x);
        }

        int left=0;
        for(int i=0;i<6;i++){
            for(int j=0;j<4;j++){
                if(green[i][j]==1){
                    left++;
                }
                if(blue[i][j]==1){
                    left++;
                }
            }
        }
        sb.append(result).append("\n").append(left);
        System.out.println(sb);
    }

    static int sit1, sit2;
    public static void go(int[][] map, int t, int r, int c){
        boolean isSit2 = false;
        sit1 = -1; sit2 = -1;
        //아래로 내린다.
        if(t==1){ //얘는 걍 내려도 됨. (밑에만 보면 된다) 1개짜리
            //컬럼을 c로 쭉 내려서 검증.
            for(int i=0;i<7;i++){
                if(i==6 || map[i][c]==1){ //걸리면
                    map[i-1][c] = 1; // 채운다.
                    sit1 = i-1;
                    break;
                }
            }
        }else if(t==2){ //얘는 걍 내리면 안됨. 가로로 생긴 애라 옆으로 두칸을 보면서 내려가야됨.
            for(int i=0;i<7;i++){
                if(i==6 || map[i][c]==1 || map[i][c+1]==1){ //걸리면
                    map[i-1][c] = 1; // 채운다.
                    map[i-1][c+1] = 1;
                    sit1 = i-1;
                    break;
                }
            }
        }else{ //얘도 걍 내려도 됨. //2개짜리
            for(int i=0;i<7;i++){
                if(i==6 || map[i][c]==1){ //걸리면
                    map[i-1][c] = 1; // 채운다.
                    map[i-2][c] = 1;
                    sit1 = i-1;
                    sit2 = i-2;
                    isSit2 = true;
                    break;
                }
            }
        }

        checkAndBomb(map, isSit2);
    }


    static int result =0;
    public static void checkAndBomb(int[][] map, boolean isSit2){

        boolean isfill = true; //다찼는지 확인
        int special = 0; //스페셜블럭에 있는지

        //이 로우만 채워졌으므로 이 로우만 보면 됨.
        //스페셜 로우인 경우는 따로 체크해줘야되서 넘긴다.
        if(sit1==0 || sit1==1){
            special++;
        }else{
            for(int i=0;i<4;i++){ //한줄 다 찼는지 ?
                if(map[sit1][i]==0)break;
            }
            if(isfill){
                result++;
                if(isSit2) sit2++;
                for(int j=sit1;j>0;j--){
                    map[j] = map[j-1].clone();
                }
                Arrays.fill(map[0], 0);
            }
        }
        if(isSit2){ // 두개봐야되는 경우에는 두개 봐주기
            isfill = true;
            //이 로우만 채워졌으므로 이 로우만 보면 됨.
            //스페셜 로우인 경우는 따로 체크해줘야되서 넘긴다.
            if(sit2==0 || sit2==1){
                special++;
            }else{
                for(int i=0;i<4;i++){ //한줄 다 찼는지 ?
                    if(map[sit2][i]==0)break;
                }
                if(isfill){
                    result++;
                    for(int j=sit2;j>0;j--){
                        map[j] = map[j-1].clone();
                    }
                    Arrays.fill(map[0], 0);
                }
            }
        }

        /*
    초록색 보드의 0, 1번 행과 파란색 보드의 0, 1번 열은 그림에는 연한색으로 표현되어 있는 특별한 칸이다.
    초록색 보드의 0, 1번 행에 블록이 있으면, 블록이 있는 행의 수만큼 아래 행에 있는 타일이 사라지고,
    초록색 보드의 모든 블록이 사라진 행의 수만큼 아래로 이동하고,
    파란색 보드의 0, 1번 열에 블록이 있으면, 블록이 있는 열의 수만큼 오른쪽 열에 있는 타일이 사라지고,
    파란색 보드의 모든 블록이 사라진 열의 수만큼 이동하게 된다.
     */
        if(special>0){
            for(int j=5;j>1;j++){
                map[j] = map[j-special].clone();
            }
            Arrays.fill(map[0], 0);
            Arrays.fill(map[1], 0);
        }

    }
}
