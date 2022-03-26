package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다익스트라로 해결할 수 없음. Cycle O (맨 처음 정점으로 돌아옴)-> cycle이기 때문에 여러 정점에서 시작해도 결과 동일
 * 정점의 수 N을 모두 돌며 순회하면 N! -> 시간초과 -> bitmasking사용 (O^N)
 *
 */
public class bj2098 {
    static int N;
    static int[][] map, DP;
    static int MAX = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        //입력
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DP = new int[N][(1<<N)-1];
        /*

         */

        for(int i=0;i<N;i++) {
            for (int j = 0; j < (1<<N)-1; j++) {
                DP[i][j] = MAX;
            }
        }

        sb.append(TSP(0,1));
        System.out.println(sb);
    }


    public static int TSP(int x, int visit) {
        //다 돌았으면
        if (visit == (1 << N) - 1) {
            if (map[x][0] == 0) return MAX; //경로 없을 경우
            else return map[x][0]; //있을 경우
        }

        //memozation
        if (DP[x][visit] != MAX) {
            return DP[x][visit];
        }

        for(int i = 0;i< N;i++){
            //방문하지 않았고 경로가 존재
            if ((visit & (1 << i)) == 0 && map[x][i] != 0) {
                DP[x][visit] = Math.min(DP[x][visit], TSP(i, visit | (1 << i)) + map[x][i]);
            }
        }

        return DP[x][visit];
    }

}
