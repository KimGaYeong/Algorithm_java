package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj12865 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj12865.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //입력받음
        Thing[] things = new Thing[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            things[i] = new Thing(w,v);
        }

        //가지고 있는 배낭 개수 별 최대 가치를 저장할 배열
        /**
         * 무게가 0일때, 1일때, 2일때, ... k일때까지
         * 1번 item만 넣었을 경우, 2번 item까지 넣었을 경우, ~ n번 item까지 넣었을 경우 순서로 저장.
         */
        int[][] dp = new int[N][K+1];

        //물건 개수만큼 돌면서
        for(int i=0; i<N; i++) {
            //해당 물건의 무게
            int weight = things[i].w;
            for(int j=0; j<=K; j++) {
                if(i==0) {
                    //물건 개수가 0개일 때는 채운다. (이때도 물건 무게 조건 달아줘야 함)
                    //물건의 무게가 j보다 작을 때만 넣어준다.
                    if(weight<=j) dp[i][j] = things[i].v;
                }
                else {
                    dp[i][j] = dp[i-1][j];
                    if(weight<=j) {
                        //dp[i-1][j-things[i].w]+things[i].v : j만큼의 무게를 뺀 뒤 현재 물건을 추가로 넣었을 때의 무게와,
                        //dp[i-1][j] : 안넢었을 때 무게를 비교한다.
                        dp[i][j] = Math.max(dp[i-1][j-things[i].w]+things[i].v, dp[i-1][j]);
                    }
                }
            }
        }
        System.out.println(dp[N-1][K]);

    }
    //W : 무게 ,V :가치

    public static class Thing{
        int w, v;

        public Thing(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}