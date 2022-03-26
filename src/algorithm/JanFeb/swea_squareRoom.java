package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_squareRoom {
    static int N;
    static int[][] arr;
    static int[][] visit;
    static int[][] dp;
    static Queue<xy> queue;
    static int[][] del = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T;t++){
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            dp = new int[N][N];
            visit = new int[N][N];

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int result_cnt =0;
            int result_value = Integer.MAX_VALUE;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    int count = BFS(i,j);
                    System.out.println(count + " " + i + " " + j + " " + arr[i][j]);
                    int value = arr[i][j];
                    if(result_cnt < count){
                        result_cnt = count;
                        result_value = value;
                    }
                    else if(result_cnt == count){
                        if(result_value > value){
                            result_cnt = count;
                            result_value = value;
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + result_value + " " + result_cnt);


        }
    }
    public static int BFS(int x, int y){
        queue = new LinkedList<>();
        queue.offer(new xy(x,y));
        int cnt=1;

        while(!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];
                if(isIn(nx, ny) && arr[nx][ny] == arr[cx][cy]+1){
                    cnt+=1;
                    dp[nx][ny] = dp[cx][cy]+1;
                    queue.offer(new xy(nx, ny));
                }
            }
        }

        return cnt;

    }

    public static boolean isIn(int x, int y){
        return x<N && y<N && x>=0 && y>=0;
    }


    public static class xy{
        int x, y ;//좌표를 저장
        xy(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
