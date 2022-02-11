package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj2839 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<=N/3; i++) {
            for(int j = 0; j<=N/5; j++) {
                if( i*3 + j* 5 == N) {
                    System.out.println(i+j);
                    return;
                }
            }
        }
        System.out.println("-1");
    }
}
