package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 한줄에 1~9까지 1개씩 들어가야 한다. (얘를 먼저 채우기)
 *
 * -> 1~9까지 무지성으로 채우고 check하기?
 * 그다음 3x3칸에 다 들어갔는지 확인한다.
 *
 */
public class bj2580 {
    static boolean finish;
    static int[][] map = new int[9][9];
    static ArrayList<Info> infos;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        InputStream input = bj2580.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        infos = new ArrayList<>();
        for(int i=0;i<9;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                //채워야 할 부분을 저장
                if(map[i][j] == 0){
                    infos.add(new Info(i,j));
                }
            }
        }

        //스도쿠 내에서 빈칸이 뚫려있는 개수 만큼 채워야 한다.
        int size = infos.size();
        BFS(size-1);

        System.out.println(sb);
    }

    public static void BFS(int cnt){
        //base part
        if(cnt<0){
            //check하기
            finish = true;
            draw();
            return;
        }

        if(finish) return; //만약 스도쿠가 채워졌다면 어떤 것이든지 출력하면 됨.

        //inductive part
        int cx = infos.get(cnt).x;
        int cy = infos.get(cnt).y;

        for(int i=1;i<=9;i++){
            //map에 i를 채울건데 i중에 누가 들어가야 될지를 확인해야됨.
            if( map[cx][cy]==0 && check(cx,cy,i)){
                //하나 채워보고
                map[cx][cy] = i;
                BFS(cnt-1); //채워야 할 0 개수 하나 줄이고~
                map[cx][cy] = 0; //다음 반복을 위해 빈칸으로 다시 바꿔준다.
            }
        }
    }

    // 스도쿠가 완성되었으면 출력해줌!
    public static void draw(){

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
    }


    //스도쿠에 해당 숫자가 들어가도 되는지를 체크해준다.
    public static boolean check(int x, int y, int num){
        //1. 한줄로 넣어도 되는지 확인 (일단 뭘 넣는지는 상관 없음. 같은거만 없으면 됨)
        for(int i=0;i<9;i++){
            if(map[i][y]==num) return false; //세로줄 확인
            if(map[x][i]==num) return false; //가로줄 확인
        }

        //2. 좌표 x,y가 속한 3x3 정사각형의 9칸 확인하기
        //-> 각 좌표를 3으로 나눈 몫에 3을 곱하면 해당 작은 정사각형의 맨 왼쪽 위 인덱스가 나옴!
        //ex) 좌표가 (5,6)이라면?
        // x좌표인 5가 포함된 작은 정사각형의 0,0에 해당되는 x좌표 인덱스 : 5/3 =1, 1*3 =3
        // y좌표인 6이 포함된 작은 정사각형의 0,0에 해당되는 y좌표 인덱스 : 6/3 =2, 2*3 =6
        // -> 따라서 (3,6부터 3*3 for문을 돌면서 확인할 수 있다.
        int sx = 3*(x/3);
        int sy = 3*(y/3);
        for(int i=sx;i<sx+3;i++){
            for(int j=sy;j<sy+3;j++){
                if(map[i][j] == num) return false;
            }
        }
        return true;
    }
    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
