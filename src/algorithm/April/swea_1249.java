package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 즉 합이 가장 적게 이동하면서 가라는 말 아닌가?
 * 0이면 가고, 1이 아닌 애들 중 PQ를 통해 가장 빨리 채워 나가는 방식으로
 * 알고스팟 문제랑 비슷하게 생각하면 될 듯.
 *
 */
public class swea_1249 {
    static int[][] map, check;
    static boolean[][] visit;
    static int N, ans;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        InputStream input = swea_1249.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            ans =Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visit = new boolean[N][N];
            check = new int[N][N];
            for(int i=0;i<N;i++){
                String str = br.readLine();
                for(int j=0;j<N;j++){
                    map[i][j] = str.charAt(j)-'0';
                }
            }

            BFS(0,0);
            sb.append("#" + t + " " + ans + "\n");
        }
        System.out.println(sb);
    }

    public static void BFS(int x, int y){
        PriorityQueue<Info> pq = new PriorityQueue<>();//((o1, o2) -> Integer.compare(o1.cnt,o2.cnt));
        visit[x][y] = true;
        pq.add(new Info(x,y,0));

        while(!pq.isEmpty()){
            int cx = pq.peek().x;
            int cy = pq.peek().y;
            int cnt = pq.peek().cnt;
            pq.poll();

            if(cx==N-1 && cy==N-1){
                if(ans>check[cx][cy]){
                    ans = check[cx][cy];
                }
                return;
            }

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(isIn(nx,ny) && !visit[nx][ny]){
                    if(map[nx][ny]==0){
                        check[nx][ny] = check[cx][cy];
                    }else if(map[nx][ny]>0){
                        check[nx][ny] = check[cx][cy]+map[nx][ny];
                    }

                    visit[nx][ny] = true;
                    pq.add(new Info(nx, ny, check[nx][ny]));

                }
            }
            //System.out.println(pq);
        }
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    public static class Info implements Comparable<Info>{
        int x, y, cnt;

        public Info(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Info o) {
            return this.cnt - o.cnt;
        }
    }
}
