package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1953 {
    static int N, M, R, C, L;
    static int[][] map;
    static boolean[][] visit;
    static boolean[][] mark;
    static int[][][] del = {{{-1,0},{1,0},{0,-1},{0,1}},{{-1,0},{1,0},{0,0},{0,0}},
            {{0,0},{0,0},{0,-1},{0,1}},{{-1,0},{0,0},{0,0},{0,1}},
            {{0,0},{1,0},{0,0},{0,1}},{{0,0},{1,0},{0,-1},{0,0}},{{-1,0},{0,0},{0,-1},{0,0}}};
    // del : 0~6.

    //상 하 좌 우 : {-1,0},{1,0},{0,-1},{0,1} // {0,0}
    static HashSet<Info> hashSet;
    public static void main(String[] args) throws IOException {
        InputStream input = swea_1953.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            int answer=0;

            //입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            mark = new boolean[N][M];
            visit = new boolean[N][M];
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            hashSet = new HashSet<Info>();
            DFS(R,C,1);

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(mark[i][j]) answer++;
                }
            }
            sb.append("#" + t + " " + answer + "\n");
        }

        System.out.println(sb);
    }

    public static void DFS(int i, int j, int  cnt){

        mark[i][j] = true;
        //base part
        if(cnt==L){
            return;
        }

        int cx = i;
        int cy = j;
        visit[cx][cy] = true;
        int num = map[cx][cy]-1;

        for(int d=0;d<4;d++){
            int nx = cx + del[num][d][0];
            int ny = cy + del[num][d][1];

            if(isIn(nx,ny) && map[nx][ny]!=0 && !visit[nx][ny]){
                if(isOk(nx,ny,cx,cy,d)){
                    visit[nx][ny] = true;
                    DFS(nx,ny,cnt+1);
                    visit[nx][ny] = false;
                }
            }
        }

    }

    private static boolean isOk(int nx, int ny, int cx, int cy, int d) {
        int before = map[cx][cy]-1; //이 전 터널 구조물이 몇 번인지?
        int after = map[nx][ny]-1; // 이 후 터널 구조물이 몇 번인지?
        //d : 상0 하1 좌2 우3
        if(before ==0){
            if(d==0){ //상
                if(after==0 || after==1 || after==4 || after==5) return true;
            }else if(d==1){ //하
                if(after==0 || after==1 || after==3 || after==6) return true;
            }else if(d==2){ //좌
                if(after==0 || after==2 || after==3 || after==4) return true;
            }else if(d==3){ //우
                if(after==0 || after==2 || after==5 || after==6) return true;
            }
        }else if(before ==1){
            if(d==0){ //상
                if(after==0 ||after==1 || after==4 || after==5) return true;
            }else if(d==1){ //하
                if(after==0 || after==1 || after==3 || after==6) return true;
            }
        }else if(before ==2){
            if(d==2){ //좌
                if(after==0 || after==2 || after==3 || after==4) return true;
            }else if(d==3){ //우
                if(after==0 || after==2 || after==5 || after==6) return true;
            }
        }else if(before ==3){
            if(d==0){ //상
                if(after==0 || after==1 || after==4 || after==5) return true;
            }else if(d==3){ //우
                if(after==0 || after==2 || after==5 || after==6) return true;
            }
        }else if(before ==4){
            if(d==1){ //하
                if(after==0 || after==1 || after==3 || after==6) return true;
            }else if(d==3){ //우
                if(after==0 || after==2 || after==5 || after==6) return true;
            }
        }else if(before ==5){
            if(d==1){ //하
                if(after==0 || after==1 || after==3 || after==6) return true;
            }else if(d==2){ //좌
                if(after==0 || after==2 || after==3 || after==4) return true;
            }
        }else if(before ==6){
            if(d==0){ //상
                if(after==0 || after==1 || after==4 || after==5) return true;
            }else if(d==2){ //좌
                if(after==0 || after==2 || after==3 || after==4) return true;
            }
        }
        return false;
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }
    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
