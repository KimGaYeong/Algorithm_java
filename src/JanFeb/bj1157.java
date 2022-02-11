package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class bj1157 {
    static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine().toUpperCase(Locale.ROOT);

        cnt = new int[26];
        for(int i=0;i<str.length();i++){
            cnt[str.charAt(i)-'A'] +=1;
        }

        int result =0;
        int k = 0;
        int count=0;

        for(int c=0;c<26;c++) {
            if (result < cnt[c]){
                result = cnt[c];
                k = c;
            }
        }

        for(int c=0;c<26;c++){
            if(cnt[c] ==result){
                count ++;
            }
        }
        if(count ==1) System.out.println((char)(k+65));
        else System.out.println("?");
    }
}
