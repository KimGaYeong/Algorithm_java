package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1405 {
    static int N;
    static double[] delpct;
    static double result =0.0;
    static int[][] map;
    static boolean[][] visit;
    static int[][] del = {{0,1},{0,-1},{1,0},{-1,0}};//동오 서왼 남하 북상
    public static void main(String[] args) throws IOException {
        InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        delpct = new double[4];
        visit = new boolean[(2*N)+1][(2*N)+1];
        map = new int[(2*N)+1][(2*N)+1]; //N,N에서 출발.
        for(int i=0;i<4;i++){
            delpct[i] = Integer.parseInt(st.nextToken()) * 0.01;
        }

        visit[N][N] = true;
        DFS(N,N,0, 1.0);

        System.out.println(result);
    }

    public static void DFS(int x, int y, int cnt, double pct){
        if(cnt==N){
            result+=pct;
            return;
        }

        for(int d=0;d<4;d++){
            int nx = x + del[d][0];
            int ny = y + del[d][1];

            if(delpct[d]!=0 && !visit[nx][ny]){
                visit[nx][ny] = true;
                DFS(nx, ny, cnt+1, pct*delpct[d]);
                visit[nx][ny] = false;
            }
        }
    }
}
