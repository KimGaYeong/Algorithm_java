package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class bj2156 {
    static int n;
    static int[] grapes;
    static int[] DP;
    public static void main(String[] args) throws IOException {
        InputStream input = bj2156.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grapes = new int[n];
        DP = new int[n];
        for(int i=0;i<n;i++){
            grapes[i] = Integer.parseInt(br.readLine());
        }

        if(n==1){
            DP[0] = grapes[0];
        }else if(n==2){
            DP[0] = grapes[0];
            DP[1] = grapes[0]+grapes[1];
        }else{
            DP[0] = grapes[0];
            DP[1] = grapes[0]+grapes[1];
            DP[2] = Math.max(DP[1], Math.max(DP[0]+grapes[2], grapes[1]+grapes[2]));
            solve();
        }
        System.out.println(DP[n-1]);

    }

    public static void solve(){
        for(int i=3;i<n;i++){
            DP[i] = Math.max(DP[i-1], Math.max(DP[i-2]+grapes[i], DP[i-3]+grapes[i-1]+grapes[i]));
        }
    }
}
