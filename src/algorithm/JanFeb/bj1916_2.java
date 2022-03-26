package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1916_2 {
    static int N, M;
    static int goal_a, goal_b;
    static int[][] map;
    static int[][] cnt;
    static boolean[][] visit;
    static int result;
    static int[][] del = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args)  throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int m=0;m<M;m++){
            String tmp = br.readLine();
            StringTokenizer st = new StringTokenizer(tmp);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[a][b] = cost;
        }

        String tmp = br.readLine();
        StringTokenizer st = new StringTokenizer(tmp);
        goal_a = Integer.parseInt(st.nextToken());
        goal_b = Integer.parseInt(st.nextToken());

        result =0;
        BFS(goal_a,goal_a);
        System.out.println(result);

    }

    public static int BFS(int x, int y){
        visit = new boolean[N+1][N+1];
        cnt = new int[N+1][N+1];
        Queue<xy> queue = new LinkedList<>();
        queue.offer(new xy(x, y)); //x,y를 큐에 삽입
        visit[x][y] = true;

        while(!queue.isEmpty()){
            int c_x = queue.peek().x; //queue에서 x,y 저장.
            int c_y = queue.peek().y;
            queue.poll(); //queue에서 제
            if(c_y ==goal_b) break;

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
        return result;
    }

    static boolean isIn(int r, int c){
        return 0<r && 0<c && r<=N && c<=N && map[r][c]!=0 && !visit[r][c];
    }

    public static class xy {
        int x, y;
        xy(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

/*
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5
*/

/*
2차원 배열의 1,1부터 시작!
*/
