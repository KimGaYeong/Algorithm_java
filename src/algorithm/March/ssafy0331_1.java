package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ssafy0331_1 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj19228.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //노란색 : 인접한 두 층에 연속 사용 가능
        //파란색 : 인접한 두 층에 연속 사용 불가능
        /**
         * 전 층의 색이 노란색인 경우 : +2
         * 전 층의 색이 파란색인 경우 : +1
         */
        int N = 8;
        int[] DP = new int[N + 1];
        DP[1] = 2;
        DP[2] = 3;

        for(int i = 3; i <= N; i++) {
            DP[i] = DP[i - 1] + DP[i - 2];
        }

        System.out.println(DP[N]);
    }
}
