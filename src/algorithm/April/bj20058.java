package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 단계 결정 ? : 한 변의 길이를 2^L만큼 만들고 L은 1씩 늘어남.
 * 단계를 결정한 후 모든 격자를 시계방향으로 90도 회전. (함수로)
 * 얼음이 있는 칸 3개 이상과 (상하좌우로) 인접해있지 않은 칸은 얼음양이 1 줄어든다. (?) -> 칸마다 돌려보고 한번에 녹여
 */
public class bj20058 {
    static int N, Q, result;
    static boolean[][] visit;
    static int[][] map;
    static int[] Level;
    static Queue<Info> queue;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj20058.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        Level = new int[Q];
        N = (int) Math.pow(2, N);
        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<Q;i++){
            Level[i] = Integer.parseInt(st.nextToken());
        }

        for(int q=0;q<Q;q++){
            int lev = (int) Math.pow(2, Level[q]); //레벨에 따른 한 칸의 크기

            // 회전 : level크기만큼 격자 나눈 뒤 회전.
            for(int i=0;i<N;i+=lev){
                for(int j=0;j<N;j+=lev){
                    rotate(i,j,lev);
                }
            }

            // 녹이기1 : 전체 칸을 탐색하며 얼음을 확인하고 녹일 얼음을 queue에 저장
            queue = new LinkedList<>();
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    Find(i,j);
                }
            }

            // 녹이기 2 : 담은 얼음을 녹인다.
            while (!queue.isEmpty()){
                Info info = queue.poll();
                map[info.x][info.y]-=1;
            }

        }

        // Q만큼의 시간이 지났으면 얼음 합 출력하고 개수 센다.

        int sum = CalSum(); //남아있는 얼음의 합
        if(sum<=0){ //님아있는 얼음의 합이 0 이하면
            System.out.println(0);
        }else{
            System.out.println(sum);
        }

        visit = new boolean[N][N];
        result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!visit[i][j] && map[i][j]>0) Calcount(i,j);
            }
        }
        System.out.println(result);
    }

    private static int CalSum() {
        int c=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]>0) c+=map[i][j];
            }
        }
        return c;
    }


    public static void Calcount(int x, int y){
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(x,y));
        visit[x][y] = true;
        int c=0;

        while (!q.isEmpty()){
            int cx = q.peek().x;
            int cy = q.peek().y;
            q.poll();
            c+=1;
            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];
                if(isIn(nx,ny) && !visit[nx][ny] && map[nx][ny]>0){
                    visit[nx][ny] = true;
                    q.add(new Info(nx,ny));
                }
            }
        }

        if(result < c){
            result=c;
        }
    }

    public static void rotate(int x, int y, int L) {
        // 돌린 크기만큼으로 생성해준다.
        int[][] copyMap = new int[L][L];
        int[][] original = new int[L][L];

        for(int i=x;i<x+L;i++){
            for(int j=y;j<y+L;j++){
                original[i-x][j-y] = map[i][j];
            }
        }
        for (int i=0;i<L;i++) {
            for (int j=0;j<L;j++) {
                copyMap[j][L-1-i] = original[i][j];
            }
        }

        // 새로 돌린 배열로 반환해준다.
        for (int i=0;i<L;i++) {
            for (int j=0;j<L;j++) {
                map[i+x][j+y] = copyMap[i][j];
            }
        }

    }


    public static void Find(int x, int y){
        int cnt=0;
        for(int d=0;d<4;d++){
            int nx = x + del[d][0];
            int ny = y + del[d][1];

            if(isIn(nx,ny) && map[nx][ny]>0){
                cnt++;
            }
        }
        if(cnt<3){
            queue.add(new Info(x,y));
        }
    }


    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    public static class Info{
        int x,y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
