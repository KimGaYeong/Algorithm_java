package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj16234 {
    static int one_Cnt=0;
    static int N, L, R;
    static int[][] map;
    static boolean[][] visit;
    static int[][] del  = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj16234.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        //입력
        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day=0;
        while(true){
            visit = new boolean[N][N];

            one_Cnt = 0; //연합이 자기 자신인 경우. 즉 연합을 만들 수 없는 경우를 센다.
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!visit[i][j]) BFS(i,j);
                }
            }
            if(one_Cnt==N*N) break; //연합의 개수가 칸의 개수가 되면 더이상 연합을 만들 수 없으므로 break!
            day++;
        }
        System.out.println(day);
    }

    public static void BFS(int x, int y){
        Queue<Info> open = new LinkedList<>(); //연합인 나라를 저장
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(x,y));
        visit[x][y] = true;
        int sum =0; //연합의 총 인구수

        while(!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();

            sum += map[cx][cy];
            open.add(new Info(cx,cy));

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];
                //상하좌우의 나라가 map 안에 위치해 있고, 맞닿은 나라의 인구수 차가 범위를 충족해서 국경선이 열렸다면?
                if(isIn(nx,ny) && isOk(map[nx][ny], map[cx][cy])){
                    visit[nx][ny] = true;
                    queue.add(new Info(nx,ny));
                }
            }
        }

        //System.out.println(open + " sum : " + sum + " 개수 : " + open.size());
        if(open.size()==1) one_Cnt++; //개인 연합인 경우 개수를 센다.
        //각 칸의 인구수 : 연합 인구수 / 연합 칸 개수
        int human = sum/(open.size());
        while(!open.isEmpty()){
            Info o = open.poll();
            map[o.x][o.y] = human;
        }
    }


    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<N && !visit[x][y];
    }

    public static boolean isOk(int x, int y){
        return Math.abs(x-y)>=L && Math.abs(x-y)<=R;
    }

    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
