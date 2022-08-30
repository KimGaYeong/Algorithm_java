package algorithm.August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 O를 출석, L을 지각, A를 결석
 개근상을 받을 수 없는 사람 : 지각을 두 번 이상 했거나, 결석을 세 번 연속으로 한 사람 // L개수 2이상 or AAA

 일단 나머지를 출력하라는 것을 보아하니 DP가 아닐련지..
 */
public class bj1563 {
    static int N;
    static int answer;
    static int[][][] DP;
    // DP[a][b][c] : 학기가 a일 때 지각 횟수 b, 연속 결석 일수 c
    // a <=n, b <2 (0,1). c<3 (0,1,2)
    // a >= b+c
    public static void main(String[] args) throws IOException {
        InputStream input = bj1563.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        DP = new int[N+1][2][3];

        DP[1][0][0] = DP[1][1][0] = DP[1][0][1] = 1;

        answer = 0;
        for(int i=2;i<=N;i++){
            DP[i][0][0] = (DP[i-1][0][0] + DP[i-1][0][1] + DP[i-1][0][2]) %1000000;
            // 결석은 '연속 결석'이라서 무조건 그 전학기에도 결석을 해야 한다. (결석 1일차 제외)
            DP[i][0][1] = DP[i-1][0][0] %1000000;
            DP[i][0][2] = DP[i-1][0][1] %1000000;
            DP[i][1][1] = DP[i-1][1][0] %1000000; // 지각은 누적이라 상관 없음.
            DP[i][1][2] = DP[i-1][1][1] %1000000;
            // 결석 초기화는 ... 그 전에 뭐였는지 상관이 없다. 경우의 수를 다 포함해도 상관 없다.
            DP[i][1][0] = ( DP[i-1][0][0] + DP[i-1][0][1] + DP[i-1][0][2]
                    + DP[i-1][1][0] + DP[i-1][1][1] + DP[i-1][1][2]) %1000000;

        }

        answer = ( DP[N][0][0] + DP[N][0][1] + DP[N][0][2]
                + DP[N][1][0] + DP[N][1][1] + DP[N][1][2] ) %1000000;

        System.out.println(answer);
    }
}
