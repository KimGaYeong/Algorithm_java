package algorithm.August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1915 {
    static int N, M, answer;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        InputStream input = bj2225.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer =0;

        map = new int[N][M];
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = str.charAt(j)-'0';
                if(map[i][j]==1) answer =1;
            }
        }

        // 그 칸을 왼쪽 위로 하는 정사각형을 생각하면 정사각형의 경계는 오른쪽 아래가 됨.
        // 오른쪽 아래를 기준으로 N*N은 모두 정사각형이야 함.

        for(int i=1;i<N;i++){
            for(int j=1;j<M;j++){
                if(map[i][j] == 1){
                    if(map[i][j-1]!=0 && map[i-1][j]!=0 && map[i-1][j-1]!=0){
                        map[i][j] = Math.min(map[i][j-1], Math.min(map[i-1][j], map[i-1][j-1]))+1;
                        answer = Math.max(answer, map[i][j]);
                    }
                }
            }
        }

        System.out.println((int)Math.pow(answer,2));
    }
}
