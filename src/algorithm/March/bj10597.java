package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백트래킹을 어디다 저장하지?
public class bj10597 {
    static String str;
    static int strlen;
    static boolean[] visit = new boolean[51];
    public static void main(String[] args) throws IOException {
        InputStream input = bj10597.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        str = br.readLine();
        strlen = str.length();
        solve(0,0,"");
    }
    public static void solve(int idx, int N, String ans){
        if(idx ==strlen){
            for(int i=1;i<=N;i++){
                if(!visit[i]) return;
            }
            System.out.println(ans.trim());
            System.exit(0);
            return;

        }

        String tmp = str.substring(idx,idx+1);
        int num = Integer.parseInt(tmp);
        if(!visit[num]){
            visit[num] = true;
            if(num>N){
                solve(idx+1, num, ans + " " + tmp);
            }else{
                solve(idx+1, N, ans + " " + tmp);
            }
            visit[num] = false;
        }
        if(idx < strlen-1){
            tmp = str.substring(idx, idx+2);
            num = Integer.parseInt(tmp);
            if(num<=50 && !visit[num]){
                visit[num] = true;
                if(num>N){
                    solve(idx+2, num, ans + " " + tmp);
                }else{
                    solve(idx+2, N, ans + " " + tmp);
                }
                visit[num] = false;
            }
        }
    }
}
