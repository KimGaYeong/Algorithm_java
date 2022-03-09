package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2573_2 {
    static int N, M;
    static int[][] del = {{-1,0},{1,0},{0,1},{0,-1}};
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        InputStream input = bj2573_2.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //1. 몇덩어리인지 찾기
        int answer =0;
        //2. 혹시 0덩어리인지 찾기.
        //3. 2덩어리 이상이면 stop
        //4. 1덩어리면 melt
        //5. 1~4 반복

        while (true){
            //1
            visit = new boolean[N][M];
            loop : for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j] >0){
                        find(i,j);
                        break loop;
                    }
                }
            }

            //2
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(!visit[i][j] && map[i][j]>0){
                        System.out.println(answer);
                        break;
                    }
                }
            }

        }

    }

    public static boolean Allzero(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] >0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void find(int i, int j){
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(i,j));
        visit[i][j] = true;
        while (!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(isIn(nx,ny) && !visit[nx][ny] && map[nx][ny]>0){
                    visit[nx][ny] = true;
                    queue.add(new Info(nx,ny));
                }
            }
        }
    }

    //범위 안에 있는지?
    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

    //객체 생성
    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
