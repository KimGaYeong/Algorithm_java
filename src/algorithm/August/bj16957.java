package algorithm.August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj16957 {
    static int R, C;
    static int[][] map, ball;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,1},{-1,1},{1,-1}};
    public static void main(String args[])throws IOException {
        InputStream input = bj16957.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        ball = new int[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(ball[i], 1);
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                DFS(i, j);
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                sb.append(ball[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static void DFS(int x, int y){
        int min = Integer.MAX_VALUE; //8방향 최소값
        int X = Integer.MAX_VALUE;
        int Y = Integer.MAX_VALUE;
        // 움직일 수 있는 곳

        for(int i=0;i<8;i++){
            //8방향을 돌면서
            int nx = x + del[i][0];
            int ny = y + del[i][1];

            //인접한 8방향에 적힌 모든 정수가 현재 칸에 적힌 수보다 크면 이동을 멈춘다.
            //그 외의 경우에는 가장 작은 정수가 있는 칸으로 공이 이동한다.
            if(isIn(nx,ny) && isMin8del(x, y, nx, ny, min)){
                min = map[nx][ny];
                X = nx; Y = ny;
            }
            //가장 작은 곳 등장.
        }

        //안움직이면  공 개수 하나 늘리기
        if(min < map[x][y]) {
            ball[X][Y] += ball[x][y];
            ball[x][y] = 0;
            DFS(X, Y);
        }
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<R && y<C;
    }

    public static boolean isMin8del(int x, int y, int nx, int ny, int min){
        return map[nx][ny] < map[x][y] && map[nx][ny] < min;
    }
}
