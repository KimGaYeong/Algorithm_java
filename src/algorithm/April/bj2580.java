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
    static int[][] map = new int[9][9];
    static ArrayList<Info> infos;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        InputStream input = bj2580.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        infos = new ArrayList<>();
        for(int i=0;i<9;i++){
            String str = br.readLine();
            for(int j=0;j<9;j++){
                map[i][j] = str.charAt(j)-'0';
                //채워야 할 부분을 저장
                if(map[i][j] == 0){
                    infos.add(new Info(i,j));
                }
            }
        }

        //스도쿠 내에서 빈칸이 뚫려있는 개수 만큼 채워야 한다.
        int size = infos.size();
        BFS(size-1);

    }

    public static void BFS(int cnt){
        //base part
        if(cnt<0){
            //check하기
            draw();
            System.exit(0);
        }
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
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    //스도쿠에 해당 숫자가 들어가도 되는지를 체크해준다.
    public static boolean check(int x, int y, int num){

        for(int i=0;i<9;i++){
            if(map[x][i]==num) return false; //가로줄 확인
            if(map[i][y]==num) return false; //세로줄 확인
        }

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