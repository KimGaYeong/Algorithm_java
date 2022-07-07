package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class bj1339 {
    public static int[] alphabet = new int[26]; //각 글자별 가장 크게 나온 숫자를 저장함.(9부터)
    static int N, ans;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        ans = 0;

        for(int i=0;i<N;i++){
            String str = br.readLine();
            int len = str.length()-1; //5
            //9BCDE = 9*(10^4) + B*(10^3) + C*(10^2) + D*(10^1) + E(10^0);
            for (int j = 0; j <= len; j++) {
                int idx = (int)str.charAt(j)-'A';
                alphabet[idx] += (int)Math.pow(10,len-j);
            }
        }

        Integer[] alphabet2 = Arrays.stream(alphabet).boxed().toArray(Integer[]::new);
        Arrays.sort(alphabet2, Collections.reverseOrder());

        //가장 큰 값을 가지는 애가 9부터 내려오면됨.
        int mul = 9;
        for (int x : alphabet2) {
            ans += x*mul;
            mul--;
        }

        System.out.println(ans);
    }

}
