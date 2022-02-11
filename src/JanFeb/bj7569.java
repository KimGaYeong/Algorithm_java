package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj7569 {
    static int M;
    static int N;
    static int H;
    static int[][][] map;
    static int del[][]  = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
    static int count;
    static Queue<zxy> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        queue = new LinkedList<>();
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];
        for(int k=0;k<H;k++){
            for(int i=0;i<N;i++){
                str = br.readLine();
                st = new StringTokenizer(str);
                for(int j=0;j<M;j++){
                    map[k][i][j]= Integer.parseInt(st.nextToken());
                    if(map[k][i][j]==1) {
                        queue.offer(new zxy(k,i,j));
                    }
                }
            }
        }
        if(queue.size() ==H*N*M){
            System.out.println("0");
            return;
        }
        BFS();
        count =0;
        outer : for(int[][] ma : map){
            for(int m[] : ma){
                for(int a : m){
                    if(a==0){
                        System.out.println("-1");
                        return;
                    }else{
                        count = Math.max(count, a);
                    }
                }
            }
        }
        System.out.println(count-1);

    }

    public static void BFS(){

        while(!queue.isEmpty()) {
            int c_z = queue.peek().z; //queue에서 z,x,y 저장.
            int c_x = queue.peek().x;
            int c_y = queue.peek().y;
            queue.poll(); //queue에서 제

            for (int i = 0; i < 6; i++) {
                int n_z = c_z + del[i][0];
                int n_x = c_x + del[i][1];
                int n_y = c_y + del[i][2];
                if (isIn(n_z, n_x, n_y)) {
                    queue.offer(new zxy(n_z, n_x, n_y));
                    map[n_z][n_x][n_y] = map[c_z][c_x][c_y] +1 ;
                }
            }
            for(int[][] ma : map){
                for(int m[] : ma) {
                    for (int a : m) {
                        System.out.print(a + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
    static boolean isIn(int h,int r, int c){
        return 0<=h && 0<=r && 0<=c && h<H && r<N && c<M && map[h][r][c] ==0;
    }

    public static class zxy {
        int z, x, y;
        zxy(int z, int x, int y){
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}
