package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2011 {
    static String secret;
    static long[] DP;
    public static void main(String[] args)  throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        // 1000000 으로 나눈 나머지를 출력하자..
        // 가짓수가 매우 많아보이고 나머지를 출력하라고 하니까 DP라고 생각

        // 암호가 잘못되면 0

        // A ; 1, Z : 26

        secret = br.readLine();
        if(secret.charAt(0)== '0'){
            System.out.println(0);
            System.exit(0);
        }

        int len = secret.length();
        DP = new long[len+1]; //현재 글자 개수까지 왔을 때 만들 수 있는 가짓수
        DP[0] = DP[1] = 1;

        for(int i=1;i<len;i++){
            int before = secret.charAt(i-1)-'0';
            int now = secret.charAt(i)-'0';

            //System.out.println(before + " " + now + " && " + DP[i-1] + " " + DP[i] + " " + DP[i+1]);

            // 다 한글자라고 생각했을 때
            // 근데 10, 20 같이 0이 들어있는건 한글자라고 생각할 수 없으니까 ...
            if (before >= 1 && now !=0) { //두 글자가 모두 1~9 사이인 경우에만 한글자라고 생각 가능.
                //System.out.println(i + " 번째 글자는 1번 case");
                DP[i+1] += DP[i];
            }

            // 두 글자라고 생각할 수 있는 경우는
            // 일단 그 앞글자가 0이 아니면 안되고. 10,20 ... 26까지니까
            // A ; 1, Z : 26
            if((before ==1) || (before ==2 && now<=6)){
                //System.out.println(i + " 번째 글자는 2번 case");
                DP[i+1] += DP[i-1];
            }

            if(before ==0){
                DP[i+1] = DP[i];
                if(now ==0){
                    System.out.println(0);
                    System.exit(0);
                }
            }

            DP[i+1] %= 1000000;
        }

        /*
        2 : 1개
        25 : 2개 (2 또는 25)
         */

        System.out.println(DP[len]);



    }
}
