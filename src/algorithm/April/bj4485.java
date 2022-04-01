package algorithm.April;

import algorithm.March.bj19228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * DP+BFS
 */
public class bj4485 {
    static int N, ans;
    static int[][] check;
    static int[][] map;
    static int[][] del = {{0,-1},{0,1},{1,0},{-1,0}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj19228.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t=1;
        //testcase 시작
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N==0) break;
            check = new int[N][N]; // 그때그때 최대값 담을DP배열
            map = new int[N][N]; //입력값 대입

            //입력
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
                Arrays.fill(check[i], Integer.MAX_VALUE);
            }
            //맨 처음 check값은 무조건 map값. 여기서 출발하기 때문
            check[0][0] = map[0][0];
            ans = Integer.MAX_VALUE ;
            BFS(0,0);

            sb.append("Problem " + t + ": " + ans + "\n");
            t++;
        }
        System.out.println(sb);
    }

    public static void BFS(int x, int y){
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(x,y));

        while(!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();

            //Break 조건. 오른쪽 맨 밑에 올 때 까지 기다린다.
            if(cx==N-1 && cy==N-1){
                if(ans > check[cx][cy]){
                    ans = check[cx][cy];
                }
            }

            //도착하지 못했으면 사방탐색
            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(isIn(nx,ny)){
                    //기존에 저장된 값보다 새로 가는 값이 크면 저장된 값 갱신.
                    if(check[nx][ny] > check[cx][cy]+map[nx][ny]){
                        check[nx][ny] = check[cx][cy]+map[nx][ny];
                        queue.add(new Info(nx,ny));
                    }
                }
            }
        }
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
