package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 동-> 서<- 남v 북^ 1 2 3 4 ->
public class bj1726 {
    static int N, M;
    static int[][] map;
    static int[][] del = {{0,1},{0,-1},{1,0},{-1,0}}; //0,1,2,3
    static int sx, sy, sd;
    static int ex, ey, ed;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;
        sd = Integer.parseInt(st.nextToken())-1;

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken())-1;
        ey = Integer.parseInt(st.nextToken())-1;
        ed = Integer.parseInt(st.nextToken())-1;

        BFS();
    }


    public static void BFS(){
        Queue<Info> queue = new LinkedList<>();
        boolean[][][] visit = new boolean[4][M][N];

        int result = Integer.MAX_VALUE;
        queue.add(new Info(sx, sy,sd, 0 ));

        while(!queue.isEmpty()){
            Info info = queue.poll();

            if(info.x == ex && info.y == ey && info.d == ed){
                result = Math.min(result, info.cnt);
                break;
            }

            if(map[info.x][info.y] ==1 || visit[info.d][info.x][info.y]) continue;
            visit[info.d][info.x][info.y] = true;

            //명령 1. Go k: k는 1, 2 또는 3일 수 있다. 현재 향하고 있는 방향으로 k칸 만큼 움직인다.
            for(int k=1;k<=3;k++){
                int nx = info.x + del[info.d][0]*k;
                int ny = info.y + del[info.d][1]*k;

                if(isIn(nx,ny) && map[nx][ny] ==0 && !visit[info.d][nx][ny]){
                    queue.add(new Info(nx, ny, info.d, info.cnt+1));
                }else{
                    break;
                }

            }

            for(int d = 0; d < 4; d++) {
                if(d!= info.d) {
                    int ncnt = info.cnt + turnCnt(info.d, d);
                    queue.add(new Info(info.x, info.y, d, ncnt));
                }
            }
        }
        System.out.println(result);

    }

    // 동-> 서<- 남v 북^ 0 1 2 3 ->
    //명령 2. Turn dir: dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전한다.
    public static int turnCnt(int now, int target){
        if(now == target) return 0;
        else if(now+target==1 || now+target==5){
            return 2;
        }else return 1;
    }

    public static boolean isIn(int x, int y){
        return x>=0 && x<M && y>=0 && y<N;
    }

    public static class Info{
        int x, y, d, cnt;

        public Info(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }

    }
}
