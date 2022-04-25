package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14889 {
    static int N, result;
    static boolean[] visit;
    static int[][] map;
    static int teamA, teamB;
    public static void main(String[] args)  throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MAX_VALUE;

        DFS(0,0);
        System.out.println(result);
    }

    public static void DFS(int idx, int cnt){

        if(cnt == N/2){
            //확인하기
            cal();
        }

        for(int i=idx;i<N;i++){
            if(!visit[i]){
                visit[i] = true;
                DFS(i, cnt+1);
                visit[i] = false;
            }
        }
    }

    public static void cal(){
        teamA = 0;
        teamB = 0;

        for(int i=0;i<N;i++){
            for(int j=i;j<N;j++){
                if(i!=j) {
                    if (visit[i] && visit[j]) {
                        teamA += (map[i][j] + map[j][i]);
                    } else if (!visit[i] && !visit[j]) {
                        teamB += (map[i][j] + map[j][i]);
                    }
                }
            }
        }

        int tmp = Math.abs(teamA-teamB);
        if(tmp==0){
            System.out.println("0");
            System.exit(0);
        }else{
            result = Math.min(result, tmp);
        }
    }
}
