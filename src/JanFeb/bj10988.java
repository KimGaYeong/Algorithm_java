package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj10988 {
    static String sub1;
    static String sub2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int n = str.length();
        if(n%2 ==0){

            sub1 = str.substring(0,n/2);
            sub2 = str.substring(n/2, n);
        }else{
            sub1 = str.substring(0,n/2);
            sub2 = str.substring(n/2+1, n);
        }
        StringBuffer sb = new StringBuffer(sub2);
        String resub2 = sb.reverse().toString();

        if(sub1.equals(resub2)) System.out.println(1);
        else System.out.println(0);
    }
}
