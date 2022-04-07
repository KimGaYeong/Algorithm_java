package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * map(i, j) ( ==2 ..i가 이김) (==1 .. 비김) (==0.. i가 짐)
 * i==j이면 ==1 나머지는 가위바위보 룰이랑 같음.
 * 지우 -> 경희 -> 민호 순서
 */
public class bj16986 {
    static int N, K, ans;
    static int[][] map;
    static int[][] arr;
    static int[] idx, win;
    static boolean[] selected;
    public static void main(String[] args) throws IOException {
        InputStream input = bj16986.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        arr = new int[3][20];
        idx = new int[3];
        win = new int[3];
        ans=0;
        //입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); //필요한 승수
        map = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //경희, 민호 손동작 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<20;i++){
            arr[1][i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<20;i++){
            arr[2][i] = Integer.parseInt(st.nextToken());
        }

        /* 손동작의 수 : N 즉 N!만큼의 개수를 확인한 후 조건에 부합하는지를 check하기(지우가 이기는지) */

        selected = new boolean[N+1];
        DFS(0);
        System.out.println(ans);
    }

    public static void DFS(int n){
        if(n==N) {
            for(int i=0; i<3; i++) {
                idx[i]=0;
                win[i]=0;
            }
            check(0, 1);
            return;
        }

        for(int i=1; i<=N; i++) {
            if(!selected[i]) {
                selected[i] = true;
                arr[0][n] = i;
                DFS(n+1);
                selected[i] = false;
            }
        }
    }

    public static void check(int first, int second){
        boolean[] hadGame = new boolean[3];
        hadGame[first] = true;
        hadGame[second] = true;
        int winner = 0;

        if(map[arr[first][idx[first]]][arr[second][idx[second]]]==2)
            winner = first;

        else if(map[arr[first][idx[first]]][arr[second][idx[second]]]==1)
            winner = Math.max(first, second);

        else if(map[arr[first][idx[first]]][arr[second][idx[second]]]==0)
            winner = second;

        win[winner]++;
        idx[first]++;
        idx[second]++;

        if(win[1]==K || win[2]==K) return;

        if(win[0]==K) {
            ans = 1;
            return;
        }

        if(idx[0]>=N) return;

        for(int i=0; i<3; i++) {
            if(!hadGame[i])
                check(winner, i);
        }
    }

}
