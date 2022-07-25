package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2146 {
    static Queue<Info> queue;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map;
    static boolean[][] visited;
    static int N, ans;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1082.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        queue = new LinkedList<>();
        map = new int[N][N];
        visited = new boolean[N][N];
        ans = Integer.MAX_VALUE;

        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        numbering(); // 섬을 구분해주가 위해 섬을 나타내는 int값 구분

//        for(int i=0;i<N;i++){
//            System.out.println(Arrays.toString(map[i]));
//        }

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(map[i][j] == 0) continue;

                init();
                queue.offer(new Info(i, j));
                visited[i][j] = true;

                int islandNum = map[i][j];
                int dist = findShortestBridge(islandNum);

                if(dist == -1) continue;

                ans = Math.min(ans, dist);
            }
        }

        System.out.println(ans);
    }

    private static int findShortestBridge(int start) {
        int dist = -1;

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0 ; i < size ; ++i) {
                Info cur = queue.poll();

                //다른 섬을 만났다!
                if(map[cur.r][cur.c] != 0 && map[cur.r][cur.c] != start) {
                    return dist;
                }

                //그게 아니라면~ 사방으로 돌면서 다른 섬 찾기!
                for(int d = 0 ; d < 4 ; ++d) {
                    int nr = cur.r + dir[d][0];
                    int nc = cur.c + dir[d][1];

                    if(!isIn(nr,nc)) continue;
                    if(visited[nr][nc] || map[nr][nc] == start) continue;

                    queue.offer(new Info(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            dist++;
        }

        return -1;
    }

    private static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    private static void init() {
        queue.clear();

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                visited[i][j] = false;
            }
        }
    }

    //2번 섬부터 넘버링한다. 섬의 구분이 1로 되어있기 때문에!
    private static void numbering() {
        int number = 2;

        for(int r = 0 ; r < N ; ++r) {
            for(int c = 0 ; c < N ; ++c) {
                if(visited[r][c] || map[r][c] == 0) continue;
                map[r][c] = number;
                queue.offer(new Info(r, c));
                visited[r][c] = true;

                while(!queue.isEmpty()) {
                    Info cur = queue.poll();

                    for(int d = 0 ; d < 4 ; ++d) {
                        int nr = cur.r + dir[d][0];
                        int nc = cur.c + dir[d][1];
                        if(!isIn(nr,nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
                        if(map[nr][nc] == 1) {
                            queue.offer(new Info(nr, nc));
                            map[nr][nc] = number;
                            visited[nr][nc] = true;
                        }
                    }
                }
                number++;
            }
        }
    }

    public static class Info {
        int r, c;

        Info(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}