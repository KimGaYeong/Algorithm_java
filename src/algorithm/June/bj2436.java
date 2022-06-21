package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2436 {
    static long A, B;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        /*
        A와 B를 최대공약수와 최소공배수로 하는 두 개의 자연수를 크기가 작은 수부터 하나의 공백을 사이에 두고 출력한다.
        A와 B를 최대공약수와 최소공배수로 하는 두 개의 자연수 쌍이 여러 개 있는 경우에는 두 자연수의 합이 최소가 되는 두 수를 출력
        */

        long result = 1;
        long f = B/A;
        for (int i = 2; i*i <= f; i++) {
            if (f%i == 0 && gcd(i, f / i) == 1)
                result = i;
        }

        sb.append(A*result + " " +  A*(f / result));
        System.out.println(sb);
    }

    public static long gcd(long a, long b) {

        while (b != 0) {
            long r = a % b; // 나머지를 구해준다.

            // GCD(a, b) = GCD(b, r)이므로 변환한다.
            a = b;
            b = r;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return (long) a * b / gcd(a, b);
    }
}
