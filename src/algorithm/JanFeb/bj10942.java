package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj10942 {
    static int N, M;
    static int[] arr;
    static boolean[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr= new int[N];
        dp = new boolean[N][N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        Pelindrom();

        //from < to 라고 가정.
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;

            if(dp[from][to]){
                sb.append("1\n");
            }else{
                sb.append("0\n");
            }
        }
        System.out.println(sb);

    }

    public static void Pelindrom(){
        for(int i=0;i<N;i++){ //같은수.
            dp[i][i] = true;
        }
        for(int i=0;i<N-1;i++){ //한개차이
            if(arr[i] == arr[i+1]) dp[i][i+1] = true;
        }

        for(int i=2;i<N;i++){ //from, to 사이의 거리.
            for(int j=0;j<N-i;j++){ //시작 idx
                if(arr[j] == arr[j+i] && dp[j+1][i+j-1]){
                    dp[j][j+i] =true;
                }
            }
        }
    }
}
