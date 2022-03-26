package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj13904_2 {
    static int N;
    static int[][] homework;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Node[] hw = new Node[N];
        int due=0;
        for(int n=0;n<N;n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int sco = Integer.parseInt(st.nextToken());
            hw[n] = new Node(day, sco);
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

    public static class Node {
        int idx;
        int score;

        public Node(int idx, int score){
            this.idx = idx;
            this.score =score;
        }

    }

}

/**
 * 마감 6일째부터 돌면서 1개씩 하기?
 */
