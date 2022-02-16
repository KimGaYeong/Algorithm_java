package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14501{
    static int N;
    static Counsel[] list;
    static int[] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        list=  new Counsel[N+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            list[i] = new Counsel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        DP = new int[N+2]; // DP[i] : i일까지 최대 얻을 수 있는 돈의 크기.

        for (int i = N; i > 0; i--) {
            int day = i + list[i].date;

            if (day <= N + 1)
                DP[i] = Math.max(list[i].money + DP[day], DP[i + 1]);
            else
                DP[i] = DP[i + 1];
        }
        System.out.println(DP[1]);
    }

    public static class Counsel{
        int date;
        int money;
        Counsel(int date, int money){
            this.date = date;
            this.money = money;
        }
    }
}
