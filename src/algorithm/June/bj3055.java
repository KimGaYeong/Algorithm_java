package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다.
 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다.
  또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.

티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.

고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다.
 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다.
첫째 줄에 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력한다. 만약, 안전하게 비버의 굴로 이동할 수 없다면, "KAKTUS"를 출력한다.


 */
public class bj3055 {
    static int R, C, time;
    static int sR,sC,dR,dC;
    static Queue<Info> waters;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    static char[][] map;
    static boolean isMoveReal = true;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1082.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        waters = new LinkedList<>();

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j]=='S'){
                    sR = i; sC = j; // 고슴도치 좌표
                }else if(map[i][j]=='D') {
                    dR = i;
                    dC = j; //비버 좌표
                }else if(map[i][j]=='*'){ //물
                    waters.add(new Info(i,j));
                }
            }
        }

        // 빈 칸 : .
        // 물 : *
        // 돌 : X
        // 비버 굴: D
        // 고슴도치 : S

        time = 0;
        char[][] copymap = copy(map);
        BFS(sR, sC);

        if(isMoveReal) System.out.println(time);
        else{
            System.out.println("KAKTUS");
        }
    }


    public static void BFS(int sr, int sc){
        Queue<Info> queue = new LinkedList<>();
        int watersize =0;
        int gosize = 0;

        queue.add(new Info(sr,sc));
        //물을 먼저 보내고 고슴도치를 이동시킨다.
        while(!queue.isEmpty()){
            //물 먼저 보내기.
            watersize = waters.size();

            for(int i=0;i<watersize;i++){
                Info water = waters.poll();
                //물 사방으로 이동시키기
                for(int d=0;d<4;d++){
                    int wnx = water.x+del[d][0];
                    int wny = water.y+del[d][1];

                    if(!isIn(wnx,wny) || map[wnx][wny]=='*') continue;
                    if(map[wnx][wny]=='.'){
                        map[wnx][wny] = '*';
                        waters.add(new Info(wnx,wny));
                    }

                }
            }
            //System.out.println(waters);

            gosize = queue.size();
            for(int i=0;i<gosize;i++){
                //고슴도치의 이동.
                Info infos = queue.poll();
                int cx = infos.x;
                int cy = infos.y;

                for(int d=0;d<4;d++){
                    int nx = cx + del[d][0];
                    int ny = cy + del[d][1];

                    //맵 밖이거나 이미 방문했거나 돌이나 물이면 못감.
                    if(!isIn(nx,ny) || map[nx][ny]=='S'|| map[nx][ny]=='X' || map[nx][ny]=='*') continue;
                    if(nx==dR && ny==dC){
                        time +=1;
                        return;
                    }
                    queue.add(new Info(nx,ny));
                    map[nx][ny] = 'S';
                    //System.out.println(nx + " " + ny);
                }
            }

//            for(int i=0;i<R;i++){
//                System.out.println(Arrays.toString(map[i]));
//            }
//            System.out.println();

            time+=1;
        }
        isMoveReal = false;
    }


    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<R && y<C;
    }

    public static char[][] copy(char[][] orimap){
        char[][] newmap = new char[R][C];
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                newmap[i][j] = orimap[i][j];
            }
        }
        return newmap;
    }

    public static class Info{
        int x, y;

        public Info(int x, int y){
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
