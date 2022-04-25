package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class bj2631 {
    static int N;
    static int[] line, DP;
    public static void main(String[] args) throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        line = new int[N];
        DP = new int[N];
        Arrays.fill(DP, 1);
        for(int i=0;i<N;i++){
            line[i] = Integer.parseInt(br.readLine())-1;
        }

        int result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                if(line[i]>line[j]) DP[i] = Math.max(DP[i], DP[j]+1);

            }
        }

        for(int i=0;i<N;i++){
            result = Math.max(result, DP[i]);
        }
        System.out.println(N-result);
    }
}
