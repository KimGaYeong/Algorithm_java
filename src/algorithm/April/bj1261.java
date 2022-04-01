package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1,1 ~> N,M : 0,0 ~> N-1,M-1로 변경해서 푼다.
 *
 * 벽을 의미하는 1을 0으로 어떻게 뿌실 것이냐가 관건.
 *
 * -> 무슨 벽을 뿌실까? 고민하면 안된다.
 * 일단 벽을 뿌셔보고 check[N-1][M-1]일 때 뿌신 벽의 개수가 가장 적어지게 갱신하면 된다.
 */
public class bj1261 {
    static int N, M;
    static int ans = Integer.MAX_VALUE;
    static boolean[][] visit;
    static int[][] map;
    static int[][] check;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj1261.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        //입력
        map = new int[N][M];
        visit = new boolean[N][M];
        check = new int[N][M];
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = str.charAt(j)-'0';
            }
            //System.out.println(Arrays.toString(map[i]));
        }

        BFS(0,0);
        System.out.println(ans);
    }

    public static void BFS(int x, int y){
        PriorityQueue<Info> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cnt,o2.cnt));
        visit[x][y] = true;
        queue.add(new Info(x,y,0));

        while(!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            int cnt = queue.peek().cnt;
            queue.poll();

            if(cx==N-1 && cy==M-1){
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
                    }else if(map[nx][ny]==1){
                        check[nx][ny] = check[cx][cy]+1;
                    }

                    visit[nx][ny] = true;
                    queue.add(new Info(nx, ny, check[nx][ny]));

                }
            }
            System.out.println(queue);
        }
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

    public static class Info implements Comparable<Info> {
        int x, y, cnt;

        public Info(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Info o) {
            return o.cnt-this.cnt;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}
