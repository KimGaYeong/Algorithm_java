package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2869 {
    static int N;
    static int[] svc;
    static int[][][] dp;
    static int result;
    static int[][] del = {{-1,-3,-9},{-1,-9,-3},{-3,-1,-9},{-3,-9,-1},{-9,-1,-3},{-9,-3,-1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj2869.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        svc = new int[3];
        dp = new int[61][61][61];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            svc[i] = Integer.parseInt(st.nextToken());
        }
        result = Integer.MAX_VALUE;
        bfs(svc[0], svc[1], svc[2], 0);
        System.out.println(result);
    }

    public static void bfs(int first, int second, int third, int cnt){
        if(dp[first][second][third] <=cnt && dp[first][second][third] !=0){
            return;
        }
        if(first==0 && second==0 && third==0){
            result = Math.min(result, cnt);
            return;
        }
        dp[first][second][third] = cnt;

        int nfirst, nsecond, nthird=0;
        for(int i=0;i<6;i++){
            if(first+del[i][0]<0) nfirst =0;
            else nfirst = first + del[i][0];
            if(second+del[i][1]<0) nsecond =0;
            else nsecond  = second +  del[i][1];
            if(third+del[i][2]<0) nthird =0;
            else nthird = third +  del[i][2];

            bfs(nfirst, nsecond, nthird, cnt+1);
        }
    }
}
