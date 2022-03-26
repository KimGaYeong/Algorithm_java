package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj13904 {
    static int N;
    static int[][] homework;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        homework = new int[N+1][2];
        int due=0;
        for(int n=0;n<N;n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            homework[n][0] = Integer.parseInt(st.nextToken());
            homework[n][1] = Integer.parseInt(st.nextToken());
            due = Math.max(due, homework[n][0]);
        }

        int result =0;
        for(int i=due;i>0;i--){
            int idx = N;
            int score =0;
            //System.out.println("나는 지금 " + i + "일차다.");
            for(int j=0; j<N; j++){
                if(homework[j][0] >= i) {
                    if (score < homework[j][1]) {
                        score = homework[j][1];
                        idx = j;
                    }
                }
            }
            //System.out.println("나는 " +(idx+1)+"번 과제를 지울것이다.");
            result += score;
            homework[idx][0] = 0;
        }
        System.out.println(result);
    }
}

/**
 * 마감 6일째부터 돌면서 1개씩 하기?
 */
