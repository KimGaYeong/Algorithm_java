package algorithm.April;

import algorithm.March.bj19228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1194 {
    static int N, M, ans;
    static char[][] map;
    static int[][] check;
    static boolean[][] visit;
    static boolean[] key;
    static int[][] del = {{0,-1},{0,1},{1,0},{-1,0}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj19228.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        check = new int[N][M];
        //visit = new boolean[N][M];
        key = new boolean[6];
        int minR =0;
        int minC =0;

        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] =='0'){
                    minR = i; minC = j;
                    map[i][j] = '.';
                }
            }
        }



        System.out.println(BFS(minR, minC));
    }

    public static int BFS(int x, int y){
        ans = Integer.MAX_VALUE;
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(x, y));

        while (!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();
            System.out.println("( cx, cy ) : ( " +cx +  ", " + cy + " )");
            if(map[cx][cy] =='1'){
                return check[cx][cy];
            }
            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(isIn(nx, ny) && map[nx][ny] != '#'){
                    //열쇠라면? 일단 갈 수 있음.
                    if((int)map[nx][ny]>=97 && (int)map[nx][ny]<=102){
                        //갱신 가능하면 감.
                        check[nx][ny] = check[cx][cy] + 1;
                        key[(int)map[nx][ny]-97] = true;
                            //visit[nx][ny] = true;
                        queue.add(new Info(nx,ny));
                            //visit[nx][ny] = false;

                    }else if((int)map[nx][ny]>=65 && (int)map[nx][ny]<=70 && key[(int)map[nx][ny]-65]){
                        //키가 존재하면 갈 수 있음.
                        check[nx][ny] = check[cx][cy] + 1;
                            //visit[nx][ny] = true;
                        queue.add(new Info(nx,ny));
                            //visit[nx][ny] = false;
                    }else if(map[nx][ny]=='.'){
                        //빈칸이면 갈 수 있음.
                        check[nx][ny] = check[cx][cy] + 1;
                            //visit[nx][ny] = true;
                        queue.add(new Info(nx,ny));
                            //visit[nx][ny] = false;
                    }
                }
            }
        }
        return -1;

    }
    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
