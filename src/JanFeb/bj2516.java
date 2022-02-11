package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 */
public class bj2516 {
    static int[][] paint;
    static int N, S;
    static int[][] DP;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        paint = new int[N+1][2];
        for(int n=1;n<=N;n++){
            st = new StringTokenizer(br.readLine());
            paint[n][0] = Integer.parseInt(st.nextToken());
            paint[n][1] = Integer.parseInt(st.nextToken());
        }
        // 가장 긴 증가하는 수열 찾기 문제랑 비슷하다고 생각했고..
        // 입력이 상대적으로 많으니까 시간을 줄이기 위해 이분탐색을 활용하기로 함.

        Arrays.sort(paint, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o2[1]-o1[1];
                else return o1[0]-o2[0];
            }
        });
        //높이는 오름차순, 가격은 내림차순으로 정렬.
        /*
        for(int[] a : paint){
            for(int k : a){
                System.out.print(k + " ");
            }
            System.out.println();
        }
        */
        DP = new int[N+1][2];
        for (int n = 1; n <= N; n++) {
            if (paint[n][0] - DP[n - 1][1] >= S) { //차이가 S이상이면 더함.
                DP[n][0] = DP[n - 1][0] + paint[n][1];
                DP[n][1] = paint[n][0];
            } else {
                int prev; // n과의 높이 차이가 S이상인 지점
                prev = binary_search(n);
                if (DP[n - 1][0] > DP[prev][0] + paint[n][1]) { //현재 그림과 더했지만 유지되는 가치가 더 클 때
                    DP[n][0] = DP[n - 1][0];
                    DP[n][1] = DP[n - 1][1];
                } else { //현재 그림과 높이차이가 가장 작게나는 가치가 더 클 때
                    DP[n][0] = DP[prev][0] + paint[n][1];
                    DP[n][1] = paint[n][0];
                }
            }
        }
        System.out.println(DP[N][0]);

    }
    public static int binary_search(int n){
        int low = 0;
        int high = n - 1;

        while (low <= high){
            int mid = (low + high) / 2;
            if (paint[n][0] - paint[mid][0] >= S) low = mid + 1;
            else high = mid - 1;
        }
        return high;
    }
}
