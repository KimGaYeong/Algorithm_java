package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 원숭이의 시작 지점 : 0,0
 * 원숭이의 도착 지점 : W-1, H-1
 *
 */
public class bj1600 {
    static int K, W, H, answer;
    static boolean ispossible = true;
    static int[][] map;
    static int[][] delmonkey = {{-1,0},{1,0},{0,-1},{0,1}};
    static int[][] delhorse = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,2},{1,-2},{2,-1},{2,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj1600.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];

        for(int i=0;i<H;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<W;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //좌표(x, y), answer, 말 만큼 따라한 개수 (K)
        BFS(0,0, 0);

        if(ispossible){
            System.out.println(answer);
        }else {
            System.out.println(-1);
        }
    }

    public static void BFS(int x, int y, int cnt){
        int [][][] check = new int[K+1][H][W];
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(x, y, cnt));

        while (!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            int ccnt = queue.peek().cnt;
            queue.poll();

            //base part
            if(cx == H-1 && cy == W-1){
                ispossible = true;
                answer = check[ccnt][cx][cy];
                return;
            }

            if(ccnt <K){
                for(int d=0;d<8;d++){
                    int nx = cx + delhorse[d][0];
                    int ny = cy + delhorse[d][1];
                    int ncnt  = ccnt+1;
                    if(isIn(nx,ny) && check[ncnt][nx][ny]==0 && map[nx][ny]!=1){
                        check[ncnt][nx][ny] = check[ccnt][cx][cy]+1;
                        queue.offer(new Info(nx, ny, ncnt));
                    }
                }
            }

            for(int d=0;d<4;d++){
                int nx = cx + delmonkey[d][0];
                int ny = cy + delmonkey[d][1];
                int ncnt = ccnt;
                if(isIn(nx,ny) && check[ncnt][nx][ny]==0 && map[nx][ny]!=1){
                    check[ncnt][nx][ny] = check[ccnt][cx][cy]+1;
                    queue.offer(new Info(nx, ny, ncnt));
                }
            }


        }

        ispossible = false;
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<H && y<W;
    }

    public static class Info{
        int x, y, cnt;

        public Info(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
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
