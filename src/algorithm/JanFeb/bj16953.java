package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj16953 {
    static int A, B;
    static int[] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int num = B;
        int cnt=0;
        while (true){
            if(num == A){
                System.out.println(cnt+1);
                return;
            }

            if(num < A){
                System.out.println(-1);
                return;
            }

            if(num%10 ==1){
                String str = String.valueOf(num);
                str = str.substring(0, str.length()-1);
                num = Integer.parseInt(str);
            }else if(num%2 ==0){
                num /=2;
            }else {
                System.out.println(-1);
                return;
            }

            System.out.println(num);
            cnt++;

        }
    }

}