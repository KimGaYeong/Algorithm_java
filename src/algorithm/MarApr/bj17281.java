package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 1~9까지의 정수를 채워 넣으면 된다.
 *
 *
 */
public class bj17281 {
    static int N;
    public static void main(String[] args) throws IOException {

        InputStream input = bj1039.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

    }
}
/*
out * 8 + homerun * 1
2
4 0 0 0 0 0 0 0 0
4 0 0 0 0 0 0 0 0
 */
