package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj16724 {
    static int N, M, result;
    static int[][] map;
    static boolean[][] visit, finish;
    static int[][] del = {{-1,0},{0,1},{1,0},{0,-1}}; //U, R, D, L
    //어느 구역에 있더라도 성우가 피리를 불 때 ‘SAFE ZONE’에 들어갈 수 있게


    //텀 프로젝트랑 똑같은 문제!
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];
        finish = new boolean[N][M];

        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                char c = str.charAt(j);
                if(c=='U') map[i][j] = 0;
                else if(c=='R') map[i][j] = 1;
                else if(c=='D') map[i][j] = 2;
                else  map[i][j] = 3;
            }
        }

        result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visit[i][j]){
                    DFS(i,j);
                }
            }
        }
        System.out.println(result);
    }

    public static void DFS(int x, int y){

        visit[x][y] = true;

        int nx = x + del[map[x][y]][0];
        int ny = y + del[map[x][y]][1];

        if(!visit[nx][ny]){
            DFS(nx, ny);
        }else{
            if(!finish[nx][ny]) result+=1;
        }
        finish[x][y] = true;
    }

}
