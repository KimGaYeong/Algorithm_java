package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// 벽을 3개 세우고, 안전 영역의 개수를 센다.
// 어디를 세울까 고민하지 마라 ! 일단 3개를 세우고 안전 구역 체크 하기!

public class bj14502 {
    static int N, M, ans;
    static int[][] map, copymap;
    static boolean[][] visit;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    static ArrayList<Info> infos;
    public static void main(String[] args) throws IOException {
        InputStream input = bj14502.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        //입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        copymap = new int[N][M];
        visit = new boolean[N][M];

        infos = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0) infos.add(new Info(i,j));
            }
        }

        // 0중에 3개를 골라 1(벽)을 세우고, 2와 맞닿은 0을 2로 바꿔가며 바이러스를 퍼트린 후, 0(안전 구역)의 개수 세기!
        ans = 0;
        DFS(0,0);

        System.out.println(ans);
    }

    public static void DFS(int idx, int num){
        //base part
        if(num==3){
            mapcopy();
            int tmp = check();

            if(ans<tmp){
                ans = tmp;
            }
            return;
        }
        //inductive part
        for(int i=idx; i<N*M; i++){
            //모든 인덱스를 다 돌 수 있는 방법!
            int cx = i/M;
            int cy = i%M;

            if(map[cx][cy] ==0){
                map[cx][cy] = 1;
                DFS(i+1, num+1);
                map[cx][cy] = 0;
            }
        }
    }

    public static void mapcopy(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                copymap[i][j] = map[i][j];
            }
        }
    }

    public static int check(){
        int answer =0;
        visit = new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visit[i][j] && copymap[i][j]==2){
                    spread(i,j);
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(copymap[i][j]==0){
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void spread(int x, int y){
        Queue<Info> queue = new LinkedList<>();
        visit[x][y] = true;
        queue.add(new Info(x,y));

        while (!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];
                if(isIn(nx,ny) && !visit[nx][ny] && copymap[nx][ny]!=1){
                    visit[nx][ny] = true;
                    copymap[nx][ny] = 2;
                    queue.add(new Info(nx,ny));
                }
            }
        }
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
    }
}
