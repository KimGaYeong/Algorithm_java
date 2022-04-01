package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2636 {
    static int R, C;
    static int[][] map;
    static int size;
    static int[][] del ={ {-1,0},{1,0},{0,1},{0,-1}};
    static Queue<Info> delete;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1039.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans=0;

        while(true){
            delete = new LinkedList<>();
            find(0,0);
            //공기랑 접한 부분이 있다면
            if(!delete.isEmpty()){
                melt();
            }else{
                break;
            }

            ans++;
        }

        System.out.println(ans);
        System.out.println(size);

    }
    public static void melt(){
        size = delete.size();
        while(!delete.isEmpty()){
            int x = delete.peek().x;
            int y = delete.peek().y;
            delete.poll();
            map[x][y] = 0;
        }

    }
    public static void find(int i, int j) {
        boolean[][] check = new boolean[R][C];
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(i,j));
        check[i][j] = true;
        while(!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();

            for(int d=0;d<4;d++){
                int nx = cx+del[d][0];
                int ny = cy+del[d][1];
                if(isIn(nx,ny) && !check[nx][ny]){
                    if(map[nx][ny] ==1){
                        delete.add(new Info(nx,ny));
                        check[nx][ny] = true;
                    }else{
                        queue.add(new Info(nx,ny));
                        check[nx][ny] = true;
                    }
                }
            }
        }
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<R && y<C;
    }

    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
