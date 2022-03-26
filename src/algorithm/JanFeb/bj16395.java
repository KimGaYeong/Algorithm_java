package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj16395 {
    static int[][] P;
    static int n, k;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        P = new int[n][n];
        P[0][0] = 1;

        for(int i=1;i<n;i++){
            P[i][0] = 1;
            P[i][n-1] = 1;
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<k;j++){
                P[i][j] = P[i-1][j-1] + P[i-1][j];
            }
        }

        System.out.println(P[n-1][k-1]);
    }
}
