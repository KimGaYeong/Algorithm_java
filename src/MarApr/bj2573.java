package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2573 {
    static int N, M;
    static int[][] del = {{-1,0},{1,0},{0,1},{0,-1}};
    static int[][] map;
    static boolean check;
    static boolean[][] visited;
    static Queue<Info> queue2;
    public static void main(String[] args) throws IOException {
        InputStream input = bj2573.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer= 0;
        while (true){

            if(Allzero()){ //만약 다 0이면?
                System.out.println(0);
                break;
            }

            check = false;

            //1. 일단 먼저 개수 센다.
            checking();

            if(check){ //2개 이상
                System.out.println(answer);
                break;
            }
            //System.out.println("다 안녹았음 : " + answer);
            //2. 다 녹진 않았으면 melt 해주기.

            melt();
            answer++;
        }

    }

    public static boolean Allzero(){
        for(int i=1;i<N-1;i++){
            for(int j=1;j<M-1;j++){
                if(map[i][j]!=0){ //하나라도 0이 아니면? 다 녹지 않았음.
                    return false;
                }
            }
        }
        return true;
    }
    //2개 이상인지 check하는 함수
    public static void checking(){
        visited = new boolean[N][M];
        int cnt=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]!=0 && !visited[i][j]){
                    find(i,j);
                    cnt++;
                    if(cnt>=2) {
                        check = true;
                        return;
                    }
                }
            }
        }
        check = false;
    }

    //덩어리 개수를 찾는 함수
    public static void find(int i, int j){
        queue2 = new LinkedList<>();

        queue2.add(new Info(i,j));
        visited[i][j] = true;

        while(!queue2.isEmpty()){
            int cx = queue2.peek().x;
            int cy = queue2.peek().y;
            queue2.poll();

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];
                if(isIn(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0){
                    visited[nx][ny] = true;
                    queue2.add(new Info(nx, ny));
                }
            }
        }
    }

    //하루 지날 때 마다 녹는 빙하.
    public static void melt() {
        int[][] count = new int[N][M];
        for(int i=1;i<N-1;i++){
            for(int j=1;j<M-1;j++){
                count[i][j] = cnt(i,j);
            }
        }


        for(int i=1;i<N-1;i++){
            for(int j=1;j<M-1;j++){
                if(map[i][j] !=0){
                    map[i][j] -= count[i][j];
                    if(map[i][j]<0){
                        map[i][j] = 0;
                    }
                }
            }
        }

    }

    public static int cnt(int cx, int cy){
        int count =0;

        for (int d = 0; d < 4; d++) {
            int nx = cx + del[d][0];
            int ny = cy + del[d][1];
            if (isIn(nx, ny) && map[nx][ny] ==0) {
                count++;
            }
        }
        return count;
    }

    //범위 안에 있는지?
    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

    //객체 생성
    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
