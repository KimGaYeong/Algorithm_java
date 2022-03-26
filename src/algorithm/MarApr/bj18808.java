package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj18808 {
    static int N, M, K;
    static int R, C, num;
    static boolean check;
    static boolean [][] visit;
    static int[][] sticker;
    public static void main(String[] args) throws IOException {
        InputStream input = bj18808.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // map 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new boolean[N][M];

        //sticker 입력
        for(int k=0;k<K;k++){
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            //sticker 생성
            sticker = new int[R][C];
            num=0;
            for(int r=0;r<R;r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0;c<C;c++){
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                    if(sticker[r][c]==1){
                        num+=1;
                    }
                }
            }

            //sticker 붙이기 (자리 없을때만 회전)

            int limit=4; //360도 회전 (4번 제한)
            while(true){
                if(limit==0){
                    break;
                }

                //스티커 붙이기
                stick();

                //붙었으면 다음 스티커로 넘어감
                if(check){
                    break;
                }else{
                    //아니면 시계 방향 90도 회전
                    rotate();
                    limit--;
                }
            }
        }

        //정답 출력
        int result =0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(visit[i][j]){
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    public static void stick(){
        check = false;
        int nr = N-R;
        int nc = M-C;

        //처음부터 안맞는 경우
        if(nr<0 || nc<0){
            check = false;
            return;
        }

        //맞으면 확인
        for(int i=0;i<=nr;i++){
            for(int j=0;j<=nc;j++){
                //스티커 붙혀지는지 확인
                find(i,j);
                //붙었으면 다음 스티커로 , 안붙었으면 다음 자리로
                if(check) return;
            }
        }
    }
    public static void find(int x, int y) {
        Queue<Info> queue = new LinkedList<>();

        check = true;
        for(int i=x;i<x+R;i++){
            for(int j=y;j<y+C;j++){
                if(sticker[i-x][j-y] ==1 && !visit[i][j]){
                    queue.add(new Info(i,j));
                }else if(sticker[i-x][j-y] ==1 && visit[i][j]){
                    check = false;
                    queue.clear();
                    return;
                }
            }
        }
        //스티커 사이즈만큼 다 돌았고 스티커 범위만큼 큐에 담겼을 때
        if(queue.size()!=num){
            check = false;
            return;
        }

        //붙인다.
        if(check) {
            while (!queue.isEmpty()){
                int cx = queue.peek().x;
                int cy = queue.peek().y;
                queue.poll();
                visit[cx][cy] = true;
            }
        }
    }

    public static void rotate(){ //sticker의 R, C가 바뀜.
        int r = C;
        int c = R;
        int[][] roSticker = new int[r][c];

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                roSticker[i][R-1-j] = sticker[j][i];
            }
            //System.out.println(Arrays.toString(roSticker));
        }

        R = r;
        int tmp = c;
        C = tmp;
        sticker = roSticker;
    }

    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
