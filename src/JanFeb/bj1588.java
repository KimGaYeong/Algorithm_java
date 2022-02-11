package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1588 {
    static String str;
    static int left, right, N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        str = br.readLine();
        left = Integer.parseInt(br.readLine());
        right = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());


    }
}

/*
* 1 -> 132 1개수 +0, 3개수 +1, 2개수 +1
* 2 -> 211 1개수 +2, 3개수 +0, 2개수 +0
* 3 -> 232 1개수 +0, 2개수 +2, 3개수 +0
* */