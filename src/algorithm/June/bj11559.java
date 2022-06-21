package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class bj11559 {
    static char[][] map;
    static boolean[][] visit;
    static boolean check;
    static int cnts, result;
    static int[][] del = {{-1,0},{1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        map = new char[12][6];

        for(int i=0;i<12;i++){
            String line = br.readLine();
            for(int j=0;j<6;j++){
                map[i][j] = line.charAt(j);
            }
            //System.out.println(Arrays.toString(map[i]));
        }
        /*
        총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.
        이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.
        R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
        */

        result =0;
        while(true) {
            visit = new boolean[12][6];
            check = false;
            cnts = 0;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!visit[i][j] && map[i][j]!='.') {
                        cnts+=BFS(i, j);
                    }
                }
            }

            //System.out.println("cnt : " + cnts);
            if(cnts==0) break;
            else result++;

            down();
            //break;
        }

        System.out.println(result);
    }

    public static void down(){

//        System.out.println("전");
//        for(int i=0;i<12;i++){
//            System.out.println(Arrays.toString(map[i]));
//        }
//        System.out.println("------");

        for(int j=0;j<=5;j++){
            String line = "";
            for(int i=11;i>=0;i--){
                if(map[i][j]!='.') line+=map[i][j];
            }

            int k = 11;
            for(int i=0;i<line.length();i++){
                map[k][j] = line.charAt(i);
                k--;
            }
            for(int i=11-line.length();i>=0;i--){
                map[i][j] = '.';
            }
        }

//        System.out.println();
//        System.out.println("후");
//        for(int i=0;i<12;i++){
//            System.out.println(Arrays.toString(map[i]));
//        }
//        System.out.println("-----");
    }

    public static int BFS(int x, int y){
        int c =0;
        Queue<Info> queue = new LinkedList<>();
        Queue<Info> check4 = new LinkedList<>();
        queue.add(new Info(x,y));
        visit[x][y] = true;

        while(!queue.isEmpty()){
            Info info = queue.poll();
            int cx = info.x;
            int cy = info.y;
            check4.add(info);

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(isIn(nx,ny) && !visit[nx][ny] && map[nx][ny] == map[x][y]){
                    visit[nx][ny] = true;
                    queue.add(new Info(nx,ny));
                }
            }
        }
        if(check4.size()>=4){
            while(!check4.isEmpty()){
                Info info = check4.poll();
                map[info.x][info.y] = '.';
            }
            c++;
        }

        return c;
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<12 && y<6;
    }

    public static class Info{
        int x,y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
