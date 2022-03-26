package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj2999 {
    static char[][] secret;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp = br.readLine();
        StringBuilder sb = new StringBuilder();
        int N = tmp.length();

        int R=0;
        int C=0;
        for(int r=1;r<=N;r++){
            int c= N/r;
            if(N%c==0 && r<=c){
                if(r>=R){
                    R =r;
                    C =c;
                }
            }
        }
        //System.out.println("R : " + R + " C : " + C);
        secret = new char[R][C];
        int k=0;
        for(int i=0;i<C;i++){
            for(int j=0;j<R;j++){
                secret[j][i] = tmp.charAt(k);
                k++;
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                sb.append(secret[i][j]);
            }
        }
        System.out.println(sb.toString());
    }
}
