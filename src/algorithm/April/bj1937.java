package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 대나무를 먹고 자리를 옮긴 곳에 그 전보다 대나무다 거 많아야 한다.
 */
public class bj1937 {
    static int N;
    static int[][] map;
    static int[][] visit;
    static int[][] del = {{-1,0},{1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj1937.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(visit[i], -1);
        }
        int answer=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                answer = Math.max(answer, DFS(i,j));
            }
        }

        System.out.println(answer);
    }

    public static int DFS(int x, int y){
        if(visit[x][y]<0){
            visit[x][y] = 0;

            for(int d=0;d<4;d++){
                int nx = x + del[d][0];
                int ny = y + del[d][1];

                if(isIn(nx,ny) && map[nx][ny]>map[x][y]){
                    visit[x][y] = Math.max(visit[x][y], DFS(nx,ny));
                }
            }

            visit[x][y] +=1;
        }

        return visit[x][y];
    }

    public static boolean isIn(int x,int y){
        return x>=0 && y>=0 && x<N && y<N;
    }
}
