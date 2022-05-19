package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj14226 {
    static int S, result;
    static boolean[][] dp;
    static Queue<Emoticon> queue;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        S = Integer.parseInt(br.readLine());
        dp = new boolean[2*S][2*S];

        queue = new LinkedList<>();
        queue.offer(new Emoticon(1, 0, 0));

        dp[1][0] = true;

        while(!queue.isEmpty()){
            Emoticon emo = queue.poll();

            if(emo.screen == S){
                sb.append(emo.cnt);
                break;
            }

            // 화면 복사해서 클립보드에 덮어쓰기.
            if(!dp[emo.screen][emo.screen]){
                queue.offer(new Emoticon(emo.screen, emo.screen, emo.cnt + 1));
                dp[emo.screen][emo.screen] = true;
            }

            //클립보드 모든 이모티콘 화면에 붙이기
            if(emo.screen + emo.clipboard <= S && !dp[emo.screen + emo.clipboard][emo.clipboard]){
                queue.offer(new Emoticon(emo.screen + emo.clipboard, emo.clipboard, emo.cnt + 1));
                dp[emo.screen + emo.clipboard][emo.clipboard] = true;
            }

            // 화면 하나 빼기
            if(emo.screen - 1 != 0 && !dp[emo.screen - 1][emo.clipboard]){
                queue.offer(new Emoticon(emo.screen - 1, emo.clipboard, emo.cnt + 1));
                dp[emo.screen - 1][emo.clipboard] = true;
            }
        }
        System.out.println(sb);
    }

    public static class Emoticon {
        int screen, clipboard, cnt;
        Emoticon(int screen, int clipboard, int cnt){
            this.screen = screen;
            this.clipboard = clipboard;
            this.cnt = cnt;
        }
    }
}
