package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class bj2133_2 {
    static int N;
    static int[] DP;
    static Set<String> set;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        if(N%2 ==1){
            System.out.println("0");
            System.exit(0);
        }
        DP = new int[N+1];

        DP[2] = 3;
        for(int i=4;i<N+1;i+=2){
            DP[i] = DP[i-2]*3;
            for(int j=4;j<i;j+=2){
                DP[i] += DP[i-j]*2;
            }
            DP[i] +=2;
        }

        System.out.println(DP[N]);
    }

}

