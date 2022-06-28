package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj25307 {
    static int N, M, K, result;
    static Info start, target, mannequin;
    static int[][] Dstore, mlen;
    static boolean[][] check;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}}; //상하좌우로 이동
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Dstore = new int[N][M];
        mlen = new int[N][M];
        boolean isChair = false;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                Dstore[i][j] = Integer.parseInt(st.nextToken());
                if(Dstore[i][j] == 4){
                    start = new Info(i,j,0);
                }else if(Dstore[i][j] ==2){
                    target = new Info(i,j,0);
                    isChair = true;
                }else if(Dstore[i][j] ==3){
                    mannequin = new Info(i,j,0);
                }
            }
        }

        if(!isChair){
            System.out.println(-1);
            System.exit(0);
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                mlen[i][j] = Math.abs(mannequin.x-i) +  Math.abs(mannequin.y-j);
            }
            //System.out.println(Arrays.toString(mlen[i]));
        }

        //상하좌우 이동시마다 체력 1 소모
        // 마네킹(3)과의 거리가 K이하여야함.
        // 0:빈칸 1:기둥 2:의자 3:마네킹 4:시루

        // 4에서 출발 ~ 2에 도착하기 (없으면 -1)

        //4의 위치 x,y에서 시작하는 BFS를 돌리자..

        result = -1;
        bfs(start, target);

        System.out.println(result);
    }

    public static void bfs(Info start, Info target){
        check = new boolean[N][M];
        Queue<Info> queue = new LinkedList<>();
        int x = start.x;
        int y = start.y;
        int c = start.cnt;

        queue.add(start);
        check[x][y] = true;

        while(!queue.isEmpty()){
            Info info = queue.poll();
            //System.out.println(info);
            int cx = info.x;
            int cy = info.y;
            int ccnt = info.cnt;

            if(cx == target.x && cy == target.y){
                result = ccnt;
                return;
            }

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(!isIn(nx,ny)) continue;
                if(mlen[nx][ny] <= K) continue;

                check[nx][ny] = true;
                queue.add(new Info(nx,ny, ccnt+1));
            }
        }


    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M && Dstore[x][y]!=1 && !check[x][y];
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
                    ", cnt=" + cnt +
                    '}';
        }
    }
}
