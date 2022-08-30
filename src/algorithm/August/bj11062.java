package algorithm.August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
근우부터 시작
 */
public class bj11062 {
    static int T, N;
    static int[] map;
    static int[][] DP;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1563.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){

            N = Integer.parseInt(br.readLine());
            map = new int[N];
            DP = new int[N+1][N+1];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                map[i] = Integer.parseInt(st.nextToken());
            }

            //근우가 얻는 점수.. // 근우 먼저 시작.
            solution(0, N-1, true);
            System.out.println(DP[0][N-1]);
        }

    }

    public static int solution(int left, int right, boolean flag){

        //남은 카드가 없을 때
        if(left > right) return 0;

        // 이미 계산됐을 때
        if(DP[left][right] !=0) return DP[left][right];

        if(flag){ // 근우 차례일 때 명우 카드에 뭘 더해야 최대값인지를 정해야 됨.
            return DP[left][right] = Math.max(map[left] + solution(left+1, right, false),
                    map[right] + solution(left, right-1, false));
        }else{ //명우 차례일 때는 최소값을 찾아야 근우가 최대가 된다.
            return DP[left][right] = Math.min(solution(left+1, right, true),
                    solution(left, right-1, true));
        }
    }
}
