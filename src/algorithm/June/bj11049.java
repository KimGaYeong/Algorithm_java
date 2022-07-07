package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj11049 {
    static int N, r, c;
    static Matrix[] list;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new Matrix[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[i] = new Matrix(r,c);
        }

        //으악
        //항상 순서대로 곱셈을 할 수 있는 애들만 입력으로 주어진다 -> A*B B*C C*D D*E 등...

    }

    public static class Matrix{
        int r, c;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
