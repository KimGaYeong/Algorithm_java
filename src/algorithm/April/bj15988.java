package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj15988 {
    static int N;
    public static void main(String[] args) throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            N = Integer.parseInt(br.readLine());
            long[] DP = new long[1000001];
            DP[0] = 1;
            DP[1] = 1;
            DP[2] = 2;
            DP[3] = 4;

            if(N>=4){
                for(int i=4;i<=N;i++){
                    for(int j=1;j<=3;j++){
                        DP[i] += DP[i-j]%1000000009;
                    }
                }
                System.out.println(DP[N]);
            }else{
                long result = DP[N];
                System.out.println(result);
            }
        }
    }
}
