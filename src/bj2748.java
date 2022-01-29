import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj2748 {

    static long[] Fibo;
    static int n;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        Fibo = new long[91];

        Fibo[0] =0;
        Fibo[1] =1;

        for(int i=2;i<=n;i++){
            Fibo[i] = Fibo[i-2]+ Fibo[i-1];
            System.out.println(i + "번째 피보나치 수는 " + Fibo[i]);
        }

        System.out.println(Fibo[n]);
    }


}

/**
 *  Fn = Fn-1 + Fn-2 (n ≥ 2)
 */