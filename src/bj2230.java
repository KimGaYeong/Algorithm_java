import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2230 {
    static int N, M;
    static int[] A;
    static int[] DP;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = (int) Math.pow(10,9);
        A = new int[N+1];
        for(int i=1;i<=N;i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(A);

        for(int n=1;n<=N;n++){
            int prev = binary_search(n);
            if (prev != n) {
                if (answer > A[prev]-A[n] ) {
                    answer = A[prev]-A[n];
                }
            }
        }
    }
    public static int binary_search(int n){
        int low = 0;
        int high = n - 1;

        while (low <= high){
            int mid = (low + high) / 2;
            if (A[n] - A[mid] >= M) low = mid + 1;
            else high = mid - 1;
        }
        return high;
    }
}
