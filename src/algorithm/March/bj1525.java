package algorithm.March;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1525 {

    static int N;
    static long[] minDp;
    static String[] maxDp;
    static int M;

    public static void main(String[] args) throws IOException {
        InputStream input = bj2869.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        minDp = new long[101];
        maxDp = new String[101];

        Arrays.fill(minDp, Long.MAX_VALUE);
        minDp[2]=1;
        minDp[3]=7;
        minDp[4]=4;
        minDp[5]=2;
        minDp[6]=6;
        minDp[7]=8;
        minDp[8]=10;

        String[] add = {"1","7","4","2","0","8"};

        for(int i=9;i<=100;i++) {
            for(int j=2;j<=7;j++) {
                String line = minDp[i-j]+add[j-2];
                minDp[i] = Math.min(Long.parseLong(line), minDp[i]);
            }
        }

        String[] add2 = {"0","0","1","7","4","2","6","8"};
        maxDp[2] = "1";
        for(int i=3;i<=100;i++) {
            String line = "";
            if(i%2==0) { //짝수면
                for(int k=0;k<i/2;k++) {
                    line += "1";
                }
            } else {
                int val = i/2-1;
                for(int k=0;k<val;k++) {
                    line += "1";
                }
                line = add2[i-(val*2)] +""+line;
            }
            maxDp[i] = line;
        }

        for(int i=0;i<N;i++) {
            M = Integer.parseInt(br.readLine());
            System.out.println(minDp[M]+" "+maxDp[M]);
        }
    }

}
/*
22 11111
288888 11111111111111111111
208888888 111111111111111111111111111111
200888888888 1111111111111111111111111111111111111111
188888888888888 11111111111111111111111111111111111111111111111111

 */