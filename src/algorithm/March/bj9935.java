package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class bj9935 {
    static String str, bomb;
    public static void main(String[] args) throws IOException {
        InputStream input = bj9935.class.getResourceAsStream("input.txt");
        System.setIn(input);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        bomb = br.readLine();


    }
}
