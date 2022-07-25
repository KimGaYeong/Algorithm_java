package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class bj1132 {
    static long[] alphabet = new long[10]; //각 글자별 가장 크게 나온 숫자를 저장함.(9부터)
    // N개의 수가 주어진다. 이 숫자는 모두 자연수이고, 알파벳 A부터 J가 자리수를 대신해서 쓰여 있다.  -> 10자리. 0~9
    // 이 알파벳은 모두 한 자리를 의미한다. 그리고, 각 자리수는 정확하게 알파벳 하나이다.
    // 0으로 시작하는 수는 없다. 이때, 가능한 수의 합 중 최댓값을 구해보자.
    static int N;
    static long ans;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        ans = 0;
        boolean[] check = new boolean[10];

        for(int i=0;i<N;i++){
            String str = br.readLine();
            int len = str.length()-1; //5
            //9BCDE = 9*(10^4) + B*(10^3) + C*(10^2) + D*(10^1) + E(10^0);
            for (int j = 0; j <= len; j++) {
                int idx = (int)str.charAt(j)-'A';
                if(j==0){
                    check[idx] = true;
                }
                alphabet[idx] += (int)Math.pow(10,len-j);
            }
        }

        //System.out.println(Arrays.toString(check));
        Long[] alphabet2 = Arrays.stream(alphabet).boxed().toArray(Long[]::new);
        int minval = 0;
        long val =Long.MAX_VALUE;
        boolean c = false;
        for(int i=9;i>=0;i--){
            if(!check[i]){
                if(val > alphabet2[i]){
                    val = alphabet2[i];
                    minval = i;
                }
                c = true;
            }
        }
        if(c) alphabet2[minval] = 0L;

        //System.out.println(minval);
        //System.out.println(Arrays.toString(alphabet2));

        Arrays.sort(alphabet2, Collections.reverseOrder());
        //System.out.println(Arrays.toString(alphabet2));


        //가장 큰 값을 가지는 애가 9부터 내려오면됨.
        int mul = 9;
        //가장 작으면서 false인게 0이다.

        for (int k=0;k<=9;k++) {
            if(alphabet2[k]!=0){
                ans += (long) alphabet2[k]*mul;
                mul--;
            }

        }

        System.out.println(ans);
    }
}