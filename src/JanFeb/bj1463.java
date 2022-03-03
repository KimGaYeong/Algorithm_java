package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1463 {
    static int N;
    static int[] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DP = new int[N+1];

        for(int i=2;i<N+1;i++){
            DP[i] = DP[i-1]+1;
            if(i%2==0){
                DP[i] = Math.min(DP[(int) i/2]+1, DP[i]);
            }
            if(i%3==0){
                DP[i] = Math.min(DP[(int) i/3]+1, DP[i]);
            }
        }

        System.out.println(DP[N]);
    }
}