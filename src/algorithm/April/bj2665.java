package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj2665 {
    static int N;
    static int[][] map, cnt;
    static boolean[][] visit;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args)  throws IOException {
        InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        PriorityQueue<Info> pq=  new PriorityQueue<>();
        map = new int[N][N];
        cnt = new int[N][N];
        visit = new boolean[N][N];
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }
        for(int i=0;i<N;i++){
            Arrays.fill(cnt[i], Integer.MAX_VALUE);
        }
        cnt[0][0] = 0;
        visit[0][0] = true;
        pq.add(new Info(0,0,0));

        while(!pq.isEmpty()){
            int cx = pq.peek().x;
            int cy = pq.peek().y;
            int cw = pq.peek().cnt;
            pq.poll();

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];
                int nw = cw;
                if(isIn(nx,ny)){
                    if(map[nx][ny] ==0) {//검은방이면 카운트 하나 더!
                        nw+=1;
                    }

                    if (cnt[nx][ny] > nw) {		// 현재 [y][x] 를 경유해서 갈 때, 비용 더 적은 경우
                        cnt[nx][ny] = nw;
                        pq.add(new Info(nx, ny, nw));
                    }
                }
            }
        }
        System.out.println(cnt[N-1][N-1]);
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }
    public static class Info implements Comparable<Info>{
        int x,y,cnt;

        public Info(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Info o) {
            return this.cnt-o.cnt;
        }
    }
}