package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2169 {
    // 로봇은 움직일 때 배열에서 왼쪽, 오른쪽, 아래쪽으로 이동할 수 있지만, 위쪽으로는 이동할 수 없다.
    // (i,j)를 기준으로 (i,j-1), (i,j+1), (i+1,j)로 이동 가능
    // 한 번 탐사한 곳은 탐사할 수 없다.
    // 위의 조건을 만족하면서, 탐사한 지역들의 가치의 합이 최대가 되도록 하는 프로그램을 작성하시오.

    static int N, M;
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1082.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 잘못하다간 1,000*1,000 을 탐색해야 하는데... 매번 BFS를 돌릴 수가 없을 것 같다.
        // 따라서 DP를 써야할 것 같다.

        // 흐음.... DP어려운뎅...
        // 간단히 점화식을 세워보자면..
        // (i,j)를 기준으로 (i,j-1), (i,j+1), (i+1,j)로 이동 가능.

        // dp는.. i,j에서 현재까지 가장 큰 가치의 합. "i,j를 밟았다"고 가정함.

        //i==0 일 때. 첫 줄
        dp[0][0] = map[0][0];
        for(int j=1;j<M;j++){
            dp[0][j] = map[0][j] + dp[0][j-1];
        }
        System.out.println(Arrays.toString(dp[0]));

        //i=1~N-1일 때. 둘째줄부터
        // (i,j)를 기준으로 (i,j-1), (i,j+1), (i+1,j)로 이동 가능
        // dp[i][j]는 따라서 내 옆에서 오거나 위에서 오거나 ? 이다.

        int[] leftdp = new int[M];
        int[] rightdp = new int[M];

        for (int i = 1; i < N; i++) {
            //옆에서 오는 경우

            leftdp = new int[M]; //왼쪽->오른쪽으로 이동하는 경우
            //맨왼쪽 left[0];
            leftdp[0] = dp[i-1][0]+map[i][0];
            for(int j=1;j<M;j++){ //왼쪽에서 쌓이거나 위에서 내려오거나
                leftdp[j] = map[i][j] + Math.max(leftdp[j-1], dp[i-1][j]);
            }
//            System.out.println(Arrays.toString(leftdp));

            rightdp = new int[M];//오른쪽->왼쪽으로 이동하는 경우
            //맨오른쪽 left[M-1];
            rightdp[M-1] = dp[i-1][M-1]+map[i][M-1];
            for(int j=M-2;j>=0;j--){ //왼쪽에서 쌓이거나 위에서 내려오거나
                rightdp[j] = map[i][j] + Math.max(rightdp[j+1], dp[i-1][j]);
            }

//            System.out.println(Arrays.toString(rightdp));

            for(int j=0;j<M;j++){
                dp[i][j] = Math.max(leftdp[j], rightdp[j]);
            }
//            System.out.println(Arrays.toString(dp[i]));
        }

        //0,0에서 시작 N-1,M-1에서 끝
        System.out.println(dp[N-1][M-1]);

    }


}


