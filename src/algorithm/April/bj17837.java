package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj17837 {
    static int N,K; //N:체스판 크기 K:말 개수
    static int[][] color;
    static ArrayList<Integer>[][] map;
    static boolean build4 = false;
    static Horse[] horses;
    static int[][] del = {{0,1},{0,-1},{-1,0},{1,0}}; //우 좌 상 하
    public static void main(String[] args) throws IOException {
        InputStream input = bj17837.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //맵 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        color = new int[N][N];
        map = new ArrayList[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                color[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayList<>();
            }
        }

        //말 입력
        K = Integer.parseInt(st.nextToken());
        horses = new Horse[K];

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int del = Integer.parseInt(st.nextToken())-1;

            horses[i] = new Horse(i,x,y,del);
            map[x][y].add(i);
        }

        build4 = false;
        int cnt=0;
        while(true){
            if(cnt>=1000){
                System.out.println(-1);
                return;
            }
            move();

            if(build4)break;

            cnt++;
        }

        System.out.println(cnt);
    }


    public static void move(){

        //말들을 움직인다.
        for(int i=0;i<K;i++){
            Horse horse = horses[i];
            int d = horse.del;
            int nx = horse.x + del[d][0];
            int ny = horse.y + del[d][1];

            //다음 칸이 흰 색인 경우 : 그 칸으로 이동.(쌓음)
            //다음 칸이 빨간색인 경우 : 순서 뒤집어서 해당 칸에 쌓음.
            //다음 칸이 파란색인 경우 : 말의 이동을 반대로 하고 한 칸 이동.그래도 파란색이면 가만히 있음.

            //N*N안에는 있는 경우
            if(isIn(nx,ny)){

            }
        }


    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }
    public static class Horse{
        int idx;
        int x, y;
        int del;

        public Horse(int idx, int x, int y, int del) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.del = del;
        }
    }
}
