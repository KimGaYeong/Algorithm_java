package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_5604 {
    static long[] ans;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1937.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            ans = new long[10];
            long mul = 1;

            while (start <= end) {
                // 끝자리 9로 만들기
                while (end % 10 != 9 && start <= end) {
                    cal(end, mul);
                    end--;
                }

                if (end < start) {
                    break;
                }

                // 끝자리 0으로 만들기
                while (start % 10 != 0 && start <= end) {
                    cal(start, mul);
                    start++;
                }

                start /= 10;
                end /= 10;

                for (int i = 0; i < 10; i++) {
                    ans[i] += (end - start + 1) * mul;
                }

                mul *= 10;
            }
            long result =0;
            for(int i=0;i<ans.length;i++){
                result += (i * ans[i]);
            }

            sb.append("#" + t + " " + result + "\n");
        }
        System.out.println(sb);
    }

    public static void cal(long x, long point) {
        while (x > 0) {
            ans[(int)(x % 10)] += point;
            x /= 10;
        }
    }
}
