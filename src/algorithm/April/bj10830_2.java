package algorithm.April;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj10830_2 {

    static int N;
    static long B;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        InputStream input = bj2580.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        //입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        map = new int[N][N];
        int[][] ans = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken())%1000;
            }
        }

        ans = solve(B);

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sb.append((ans[i][j]) + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    //시작!
    public static int[][] solve(long cnt) {

        if (cnt==1) return map;

        int[][] res = solve(cnt / 2);

        if (cnt%2 ==0) { // 짝수일 때
            // B/2번 곱한 행렬과 B/2번 곱한 행렬을 곱한다.
            return calculate(res, res);
        }else { // 홀수일 때
            // B/2번 곱한 행렬과 (B/2 +1)번 곱한 행렬을 곱한다.
            int[][] res2 = calculate(res, map);
            return calculate(res, res2);
        }

    }

    //걍 행렬 곱.
    public static int[][] calculate(int[][] A, int[][] B) {

        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    tmp[i][j] += (A[i][k]*B[k][j])%1000;
                }

                tmp[i][j]%=1000;
            }
        }

        return tmp;
    }
}