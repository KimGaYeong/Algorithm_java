package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class bj2661 {
    static int N;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        // DFS로 (?) 7자리 수를 만들고... 7개 되면 검사하기.

        DFS(0, "");
    }

    public static void DFS(int cnt, String str){

        if(cnt==N){
            //System.out.println(str);
            System.out.println(str);
            System.exit(0);
        }

        for(int i = 1; i<=3; i++){
            str += i;
            //검사하기
            if(solution(str)) DFS(cnt+1, str);
            str = str.substring(0,str.length()-1);
        }
    }

    public static boolean solution(String str) {
        int len = str.length();
        int h_len = len/2;
        for(int i = 1; i <= h_len; i++) {
            String front = str.substring(len-(2*i), len-i);
            String back = str.substring(len-i, len);
            if(front.equals(back)) return false;
        }
        return true;
    }

}

