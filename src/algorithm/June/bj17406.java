package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
가장 왼위가 r-s c-s, 오른아래가 r+s, c+s을 시계방향으로 한 칸씩 돌림.
회전 연산의 순서는 임의로 정해도 됨.
 */
public class bj17406 {
    static int N, M, K, answer;
    static int[][] A;
    static Turn[] turns;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        turns = new Turn[K];
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            turns[i] = new Turn(r,c,s);
        }

        answer = Integer.MAX_VALUE;
        //개수가 몇개 안되서 DFS 쓰기로 함. 6개니까 최대..2^6
        DFS(0, new boolean[K], new int[K]);
        System.out.println(answer);
    }

    public static void DFS(int cnt, boolean[] visit, int[] order){
        if(cnt==K){
            //System.out.println(Arrays.toString(order));
            //순서가 정해졌으면 solution 함수를 돌린다.
            Solution(order);
            return;
        }

        for(int i=0;i<K;i++){
            if(!visit[i]){
                visit[i] = true;
                order[cnt] = i;
                DFS(cnt+1, visit, order);
                visit[i] = false;
                order[cnt] = 0;
            }
        }
    }

    //DFS로 얻은 order 순서에 따라 turn을 한다.
    public static void Solution(int[] order){
        int[][] copyA = copy(A);
        //turn을 한 배열은 copyA라는 배열에 저장함.
        for(int o : order){
            Turn turn = turns[o];
            copyA = GoTurn(turn.r, turn.c, turn.s, copyA);
        }
        int min = Integer.MAX_VALUE;

        //행별로 최소값 찾기!
        for(int i=0;i<N;i++){
            int sum =0;
            for(int j=0;j<M;j++){
                sum += copyA[i][j];
            }
            if(min>sum){
                min = sum;
            }
        }
        //정답 갱신
        if(answer> min){
            answer = min;
        }
    }

    //배열 복사
    public static int[][] copy(int[][] list){
        int[][] result = new int[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                result[i][j] = list[i][j];
            }
        }

        return result;
    }

    public static int[][] GoTurn(int r, int c, int s, int[][] list){
        //가장 왼위가 r-s c-s, 오른아래가 r+s, c+s을 시계방향으로 한 칸씩 돌림.
        int num = s;

        for(int n=0; n<num; n++) { //라인을 전부 돌림.
            int val = list[r-s+n][c-s+n]; //(c,c)

            //왼쪽
            for(int i=r-s+n; i<r+s-n; i++)
                list[i][c-s+n] = list[i+1][c-s+n];

            //아래쪽
            System.arraycopy(list[r + s - n], c - s + n + 1, list[r + s - n], c - s + n, c + s - n - (c - s + n));

            //오른쪽
            for(int i=r+s-n; i>r-s+n; i--)
                list[i][c+s-n] = list[i-1][c+s-n];

            //위쪽
            System.arraycopy(list[r - s + n], c - s + n, list[r - s + n], c - s + n + 1, c + s - n - (c - s + n));

            list[r-s+n][c-s+n+1] = val;
        }
        return list;
    }

    public static class Turn{
        int r, c, s;

        public Turn(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
