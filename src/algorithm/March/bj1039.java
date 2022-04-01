package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * M : 정수 N의 자릿수
 * 1 <= i < j <= M 고르기 .즉 1부터 M까지 중에 문자 두개 골라서 작은게 i 큰게 j
 * i번째 숫자와 j번째 숫자를 K번 바꿔서 올 수 있는 가장 큰 수
 */
public class bj1039 {
    static String N;
    static int K;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1039.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        K = Integer.parseInt(st.nextToken());

        System.out.println(DFS(0,N));
    }

    public static int DFS(int cnt, String N){
        int num = Integer.parseInt(N);
        //base part
        if(cnt==K){
            return num;
        }

        //inductive part
        return num;
    }
}
