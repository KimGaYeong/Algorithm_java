package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1114 {
    static int L, K, C;
    static int[] list;
    static int result;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); //통나무 길이
        K = Integer.parseInt(st.nextToken()); //자를 수 있는 위치
        C = Integer.parseInt(st.nextToken()); // 최대C번까지
        list = new int[K];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<K;i++){
            list[i] = Integer.parseInt(st.nextToken());
        }



    }
}
