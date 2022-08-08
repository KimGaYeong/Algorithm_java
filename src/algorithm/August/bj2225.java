package algorithm.August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2225 {
    static int N, K;
    static long[][] DP;
    public static void main(String[] args) throws IOException {
        InputStream input = bj2225.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 만들어야 되는 수
        K = Integer.parseInt(st.nextToken()); // 더해야 되는 수의 개수

        DP = new long[K+1][N+1];
        //DP[r][c] = c를 숫자 r개를 가지고 만들 수 있는 수

        //DP[r][c] = DP[r-1][0] + DP[r-1][1] + ... + DP[r-1][c-1] 
        //DP[0][0] = 1, DP[1][0] = DP[0][-1]? -> 맨뒤가 0인거는 따로계산

        DP[0][0] = 1;
        for(int i=1; i<K+1; i++){
            DP[i][0] = 1; //0은 항상 1개만 갖고 만들 수 있음.
        }

        for(int i=1; i<K+1; i++){
            for(int j=1; j<N+1; j++){
                for(int h=0; h<=j; h++){
                    DP[i][j]+= DP[i-1][j-h]%1000000000;
                }
            }
        }
        System.out.println(DP[K][N]);	    }
}
