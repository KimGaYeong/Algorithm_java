package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//아무리봐도 BFS!
public class bj2589 {
    static char[][]  map;
    static int[][] cnt;
    static boolean[][] visit;
    static int R;
    static int C;
    static int result;
    static int[][] del = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);
        map = new char[R+1][C+1];
        //cnt = new int[R+1][C+1];
        for(int r=1;r<=R;r++){
            String tmp2 = br.readLine();
            for(int c=1;c<=C;c++){
                map[r][c]=  tmp2.charAt(c-1);
            }
        }

        result =0;
        for(int r=1;r<=R;r++){
            for(int c=1;c<=C;c++){
                if(map[r][c] == 'L'){
                    BFS(r, c);
                }
            }
        }
        System.out.println(result);
    }
    public static void BFS(int x, int y){
        visit = new boolean[R+1][C+1];
        cnt = new int[R+1][C+1];
        Queue<xy> queue = new LinkedList<>();
        queue.offer(new xy(x, y)); //x,y를 큐에 삽입
        visit[x][y] = true;

        while(!queue.isEmpty()){
            int c_x = queue.peek().x; //queue에서 x,y 저장.
            int c_y = queue.peek().y;
            queue.poll(); //queue에서 제

            for(int i=0;i<4;i++){
                int n_x = c_x + del[i][0];
                int n_y = c_y + del[i][1];

                if(isIn(n_x, n_y)){
                    queue.offer(new xy(n_x, n_y));
                    visit[n_x][n_y] = true;
                    cnt[n_x][n_y] = cnt[c_x][c_y] +1;
                    if(result < cnt[n_x][n_y]) result = cnt[n_x][n_y];
                }
            }
        }
    }
    static boolean isIn(int r, int c){
        return 0<r && 0<c && r<=R && c<=C && map[r][c] == 'L' && !visit[r][c];
    }

    public static class xy{
        int x, y ;//좌표를 저장
        xy(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


}
//연결된 거리가 가장 긴 L을 고르고, 그 때 연결 노드끼리의 거리를 cnt로 구한다.
