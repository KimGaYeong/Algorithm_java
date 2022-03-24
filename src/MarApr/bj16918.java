package MarApr;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj16918 {
    static int[][] del = {{1,0},{-1,0},{0,1},{0,-1}};
    static int[][] map;
    static int R, C;
    static Queue<Info> queue;

    public static void main(String[] args) throws IOException {
        InputStream input = bj16918.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        int n=0;
        //1. 일부 칸에 폭탄 설치
        for(int i=0;i<R;i++){
            String tmp = br.readLine();
            for(int j=0;j<C;j++){
                if(tmp.charAt(j) == '.'){
                    map[i][j] = 0; // 폭탄이 없는 상태
                }else{
                    map[i][j] = 1; // 1초짜리 폭탄이 심어진다.
                }
            }
        }
        //2. 1초가 지남. (기존 폭탄은 1초 지남)
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]>0){
                    map[i][j]+=1;
                }
            }
        }
        n+=1;

        while(true){
            if(n>=N){
                break;
            }
            //3. 모든 칸에 폭탄 설치.
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    map[i][j]+=1;
                }
            }
            n+=1;
            if(n>=N){
                break;
            }
            //4. 3초 전에 설치된 폭탄이 폭발한다.
            queue = new LinkedList<>();
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    map[i][j]+=1;
                    if(map[i][j]>=3){
                        bomb(i,j);
                    }
                }
            }
            while (!queue.isEmpty()){
                int x = queue.peek().x;
                int y = queue.peek().y;
                queue.poll();
                map[x][y] = 0;
            }
            n+=1;
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==0){
                    sb.append(".");
                }else{
                    sb.append("O");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void bomb(int x, int y){
        queue.add(new Info(x,y));
        for(int i=0;i<4;i++){
            int nx = x + del[i][0];
            int ny = y + del[i][1];
            if(isIn(nx,ny)){
                queue.add(new Info(nx,ny));
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
    }
}