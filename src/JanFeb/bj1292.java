package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1292 {
    static int[] DP;
    static int A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B= Integer.parseInt(st.nextToken());
        DP= new int[B+1];
        DP[0] = 0;

        int num=1;
        int cnt = 0;
        for(int i=1; i<=B;i++){
            DP[i] += DP[i-1] + num;
            cnt++;
            if(cnt ==num){
                num +=1;
                cnt =0;
            }
        }

        System.out.println(DP[B]-DP[A-1]);
    }
}
