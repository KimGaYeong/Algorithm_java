package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * D : 궁수 공격 거리. 왼쪽부터 탐색하기.
 * 거리가 D 이하 중 왼쪽부터 한명씩 공격하기. 같은 적이 여러 궁수에게 공격당할 수 있음. (boolean 배열을 통해 관리하자)
 */
public class bj17135 {
    static int N, M, D, answer;
    static int[][] map;
    static int[][] copymap;
    static Queue<Info> delete;
    static int EnemyCnt =0;
    static int[][] del = {{0,-1},{0,1},{-1,0},{1,0}}; //일단 왼쪽부터 보게!
    public static void main(String[] args) throws IOException {
        InputStream input = bj17135.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        //입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copymap = new int[N][M];

        answer = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1){
                   EnemyCnt++;
                }
            }
        }

        DFS(0,0); // 궁수 배치하기

        System.out.println(answer);
    }

    public static void DFS(int idx, int num){
        //base part
        if(num==3){
            if(idx==18){
                //여기서부터 진짜 시작!
                mapcopy();
                solve();
            }
            /*
            //여기서부터 진짜 시작!
            mapcopy();
            solve();

             */
            int cnt = EnemyCnt-count();
            if(answer < cnt) answer = cnt;
            return;
        }

        for(int i=idx;i<N*M;i++){
            int cx = i/M;
            int cy = i%M;

            if(map[cx][cy] ==0){
                map[cx][cy] = 2; //궁수가 있는 곳을 2로 만든다.
                DFS(i, num+1);
                map[cx][cy] = 0;
            }

        }
    }

    public static int count() {
        int c=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(copymap[i][j]==1){
                    c++;
                }
            }
        }
        return c;
    }
    public static void solve(){
        while(true){
            delete = new LinkedList<>();
            //적이 있는 경우 궁수에 가장 가까운 적을 찾는다.
            // BFS를 돌려서 만나는 가장 첫번째 애를 kill
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(copymap[i][j]==2){
                        BFS(i,j);
                    }
                }
            }

            //System.out.println(hashSet);
            if(delete.size()==0) break; // 적을 다 없애면 끝남.

            //Delete
            while(!delete.isEmpty()){
                Info info = delete.poll();
                copymap[info.r][info.c] = 0;
            }

            //MoveEnemy
            for(int i=N-1;i>=0;i--){
                for(int j=M-1;j>=0;j--){
                    if(copymap[i][j]==1){
                        move(i,j);
                    }
                }
            }
        }
    }

    public static void move(int i, int j){
        copymap[i][j] = 0;

        if(i != N-1){
            copymap[i+1][j] = 0;
        }
    }

    public static void BFS(int x, int y){
        boolean[][] visit = new boolean[N][M];
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(x,y));
        visit[x][y] = true;

        while (!queue.isEmpty()){
            int cx = queue.peek().r;
            int cy = queue.peek().c;
            queue.poll();

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(isIn(nx,ny) && isOk(nx,ny,x,y) && !visit[nx][ny]){
                    if(copymap[nx][ny] ==1){ //적이면?
                        delete.add(new Info(nx,ny));
                        return;
                    }else if(copymap[nx][ny] ==0){ //빈 칸이면?
                        visit[nx][ny] = true;
                        queue.add(new Info(nx,ny));
                    }
                }
            }
        }
    }

    public static boolean isOk(int nx, int ny, int cx, int cy){
        return (Math.abs(nx-cx) + Math.abs(ny-cy)) <=D;
    }

    public static boolean isZero(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(copymap[i][j] ==1) return false;
            }
        }
        return true;
    }

    private static void mapcopy() {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                copymap[i][j] = map[i][j];
            }
        }

        for(int i=0;i<N;i++){
            System.out.println(Arrays.toString(copymap[i]));
        }
        System.out.println();

    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

    public static class Info{
        int r, c;

        public Info(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
