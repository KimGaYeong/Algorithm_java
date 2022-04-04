package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

//BFS + DP문제. 먼저 무지성으로 연결한 후, 개수를 세서 가장 적은 애로 찾으면 된다.
public class swea_1767 {
    static int N, minWireCnt, maxCore, infosize;
    static int[][] map;
    static ArrayList<Info> infos;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = swea_1767.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            minWireCnt = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            infos = new ArrayList<>();
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    //가장자리가 아닌 1들의 개수를 세서, 걔네를 기준으로 심어주면 되지 않을까?
                    if(map[i][j]==1 && !isSide(i,j)){
                        infos.add(new Info(i,j));
                    }
                }

                infosize = infos.size();
                DFS(0, 0, 0);
            }
            //System.out.println(infos);

            sb.append("#" + t + " " + minWireCnt + "\n");
        }

        System.out.println(sb);
    }

    public static void DFS(int size, int core_count, int wire_count){
        //base part
        if(size==infosize){
            //몇개인지 세어보기.
            if(maxCore < core_count) {
                maxCore = core_count;
                minWireCnt = wire_count;
            } else if( maxCore == core_count) {
                minWireCnt = Math.min(wire_count, minWireCnt);
            }
            return;
        }

        //inductive part
        int cx = infos.get(size).x;
        int cy = infos.get(size).y;

        //cx, cy를 기준으로 채워보기
        for(int d=0;d<4;d++){
            int cnt=0;
            int nx = cx;
            int ny = cy;

            while(true) {
                nx += del[d][0];
                ny += del[d][1];

                if(!isIn(nx,ny)) break;

                if(map[nx][ny] == 1) {
                    cnt = 0;
                    break;
                }
                cnt++;
            }

            //count 개수만큼 채우기
            int fx = cx;
            int fy = cy;

            for( int i = 0; i < cnt; i++) {
                fx += del[d][0];
                fy += del[d][1];
                map[fx][fy] = 1;
            }

            if(cnt == 0){
                DFS(size+1, core_count, wire_count);
            }else {
                DFS(size+1, core_count+1, wire_count+cnt);
                fx = cx;
                fy = cy;

                while(true) {

                    fx += del[d][0];
                    fy += del[d][1];

                    if(!isIn(fx,fy)) break;

                    map[fx][fy] =0;
                }
            }
        }

    }

    public static boolean isSide(int i, int j){
        return i==N-1 || i==0 || j==N-1 || j==0 ;
    }

    public static boolean isIn(int i, int j){
        return i>=0 && j>=0 && i<N && j<N;
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
