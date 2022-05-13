package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14890 {
    static int N, L, answer;
    static int[][] map;
    static boolean[] check;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer =0;
        int[] row = new int[N];
        int[] col = new int[N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                row[j] = map[j][i];
                col[j] = map[i][j];
            }

            if(solve(col) || solve(row)) answer++;
        }
    }

    public static boolean solve(int[] arr){
        check = new boolean[N];

        for(int i=0;i<N-1;i++){
            int val = arr[i]-arr[i+1];
            if(val==0) continue;
            else if(val==1){
               // 내리막길인 경우
                for(int j=1;j<=L;j++){
                    if(i+j >=N || check[i+j] || arr[j-i] != arr[i]) return false;
                }
            }else if(val==-1){
               // 오르막길인 경우
            }else{
                return false;
            }
        }
        return true;
    }

    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
