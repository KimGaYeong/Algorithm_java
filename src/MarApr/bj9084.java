package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj9084 {
    static int T, N, M;
    static int[] coin;
    static int[] DP;
    public static void main(String[] args) throws IOException {
        InputStream input = bj9084.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            N = Integer.parseInt(br.readLine());
            coin = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                coin[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            DP = new int[M+1];
            DP[0] = 1;

            //최소 금액 :
            for(int i=0;i<N;i++){
                for(int j=coin[i];j<=M;j++){
                    DP[j] += DP[j-coin[i]];
                }
            }

            System.out.println(Arrays.toString(DP));
            sb.append(DP[M] + "\n");

        }
        System.out.println(sb);
    }
}
