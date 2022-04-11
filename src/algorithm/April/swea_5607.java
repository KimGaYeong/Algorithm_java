package algorithm.April;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_5607 {
    /*
    자연수 N와 R가 주어진다. 이 때의 N combination R의 값을 1234567891로 나눈 나머지를  출력하세요.
    예를들면 N이 4, R이 2라면 4 combination 2는 (4 * 3) / (2 * 1) = 6이 된다.

    페르마 소정리
    A가 0이 아닐 때 A의 p-1제곱 : 1 mod p
    쟤 양변을 나누면 : A의 p-2제곱 : 1/a mod p
     */
    static long MOD = 1234567891L;
    static int N,R;
    static long[] factorial;
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        fac();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            long A = factorial[N] % MOD;
            long B = ((factorial[R]%MOD) * (factorial[N-R] % MOD)) % MOD;

            long ans = ((A % MOD) * (div(B, MOD - 2))) % MOD;

            sb.append("#" + t + " " + ans + "\n");
        }
        System.out.println(sb);
    }

    public static void fac() {
        factorial = new long[1000001];
        factorial[0] = 1L;
        for(int i=1; i<1000001; i++) {
            factorial[i] = (factorial[i-1]*i)%MOD;
        }
    }

    public static long div(long a, long b) {
        if(b==0) return 1;
        else if(b==1) return a;
        long tmp = div(a, b/2);

        if(b%2 == 1)
            return (((tmp * tmp) % MOD) * a) % MOD;
        else
            return (tmp*tmp)%MOD;
    }
}