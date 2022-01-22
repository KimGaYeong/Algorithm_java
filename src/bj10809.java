import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj10809 {
    static int[] alpha;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        //97(a)-122(z)
        int tmp =0;
        alpha = new int[26];
        for(int i=0;i<26;i++)
            alpha[i] = -1;
        visit = new boolean[26];
        for(int s=0; s<S.length();s++){
            tmp = S.charAt(s) -'0'-49;
            if(!visit[tmp]) {
                alpha[tmp] = s;
                visit[tmp] = true;
            }
        }
        for(int i=0; i<26;i++){
            System.out.print(alpha[i] + " ");
        }
    }
}
