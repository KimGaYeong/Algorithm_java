package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ssafy0331_2 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj19228.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = 6;
        int[] DP = new int[N+1];
        //[][0] = 파란색으로만 채우는 방법 [n개를][1] = 노란색으로만 채우는 방법 두개 더하기.

        DP[0] = 1;
        DP[1] = 2;
        DP[2] = 5;
        for(int i=3;i<=N;i++){
            DP[i] = 2*DP[i-1] + DP[i-2];
            System.out.println(i + " " + DP[i]);
        }

    }
}
