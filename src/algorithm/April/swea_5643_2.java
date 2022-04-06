package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_5643_2 {
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        InputStream input = swea_5643_2.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++) {
            int answer = 0;
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int small = Integer.parseInt(st.nextToken())-1;
                int tall = Integer.parseInt(st.nextToken())-1;
                map[small][tall] = 1;
            }

            // 플로이드와샬
            for(int k = 0; k < N; k++) {
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                        if(map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                int inDegree = 0;
                int outDegree = 0;
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 1) outDegree++;
                    if(map[j][i] == 1) inDegree++;
                }
                if(inDegree + outDegree == N-1) {
                    answer++;
                }
            }
            sb.append("#" + t + " " + answer + "\n");
        }

        System.out.println(sb);

    }

}
