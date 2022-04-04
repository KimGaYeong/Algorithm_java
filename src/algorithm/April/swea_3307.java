package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
수열의 길이 : 1000, 원소는 2^31. 중첩 for문으로 풀게될 경우 N^2의 시간 복잡도로 안녕~ 시간초과가 나는 경우도 있다.
여기서는 1000000 -> 백만으로 시간초과는 나지 않지만... 빠른게 좋으니까 풀어보겠다.
이런 경우에는 이분 탐색을 사용한다. -> 이분 탐색은 정렬이 꼭 필수! -> "최대 부분 증가 수열의 ""길이""를 구할 때 쓰인다."
 */
public class swea_3307 {
    static int T, N;
    static int[] arr;
    static ArrayList<Integer> lis;
    public static void main(String[] args) throws IOException {
        InputStream input = swea_3307.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            int answer=0;
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            lis = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                if(lis.isEmpty() || arr[i] > lis.get(lis.size() - 1))
                    lis.add(arr[i]);
                else {
                    int idx = binarySearch(lis.size(), arr[i]);
                    lis.set(idx, arr[i]);
                }
            }

            sb.append("#" + t + " " + lis.size() + "\n");
        }
        System.out.println(sb);
    }

    private static int binarySearch(int end, int find) {

        int start = 0;

        int mid = 0;
        while(start <= end) {
            mid = (start + end) / 2;

            if(find < lis.get(mid)) end = mid - 1;
            else if(find > lis.get(mid)) start = mid + 1;
            else return mid;
        }

        return start;
    }

}
