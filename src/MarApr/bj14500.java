package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
DFS를 돌면서 4칸씩 움직이게 만든다음 그 합의 최대를 찾으면 되지 않을까~?하는 마음..
약간 그 저번에 풀었던 순조부처럼 기저부분이랑 있으면서 백트래킹 할때처럼 !!!!!!
최소값을 찾는게 아니니까 백트래킹은 안쓰고 (최대값이면 굳이 안돌아가도 되지않나?)
 */
public class bj14500{
    static int N, M;
    static int[][] map;
    static int answer;
    static boolean[][] check;
    static int[][] del = {{1,0},{-1,0},{0,1},{0,-1}};
    static int[][][] del2 = {{{0,0},{0,1},{0,2},{1,1}}, {{1,0},{1,1},{1,2},{0,1}}, {{0,1},{1,1},{2,1},{1,0}}, {{0,0},{1,0},{2,0},{1,1}}}; //세로
\    public static void main(String[] args) throws IOException {
        InputStream input = bj2170.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //map 채우기
        check = new boolean[N][M];
        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                DFS(i,j,1,map[i][j]);

                DFS2(i, j);
            }
        }
        System.out.println(answer);
    }

    //4개를 고르고 합 구하기.
    public static void DFS(int x,int y, int cnt, int ans){
        if(cnt==4){
            answer = Math.max(ans, answer);
            return;
        }
        check[x][y] = true;
        for(int d=0;d<4;d++){
            int nx = x + del[d][0];
            int ny = y + del[d][1];
            if(isIn(nx,ny) && !check[nx][ny]){
                check[nx][ny] = true;
                DFS(nx, ny, cnt+1, ans+map[nx][ny]);
                check[nx][ny] = false;
            }
        }
        check[x][y] = false;

    }

    public static void DFS2(int x,int y){
        int nx, ny, sum;
        boolean outCheck = false;

        for(int i=0; i<4; i++) {
            sum = 0;
            outCheck = false;
            for(int j=0; j<4; j++) {
                nx = x + ex[i][j]; // 세로
                ny = y + ey[i][j]; // 가로

                // 종이 범위 넘어가는지 체크
                if(nx<0 || nx>=n || ny<0 || ny>=m) {
                    outCheck = true;
                    continue;
                }

                sum += maps[nx][ny];
            }

            // 범위 안나갔으면
            if(!outCheck)
                result = Math.max(sum, result);
        }
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

}
