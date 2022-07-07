package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1082 {
    static int N, M;
    static String result="";
    static int[] P;
    static int[] DP;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1082.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        P = new int[N];
        DP = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            P[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());

        //M원을 모두 사용해서 만들 수 있는 가장 큰 방번호 구하기



    }
}
