package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj2933 {
    static int R, C, N;
    static char[][] map;
    static int[] heights;
    static boolean[][] visit;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());
        heights = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            //높이 나올 때마다 클러스터 확인 시작.
            heights[i] = R-Integer.parseInt(st.nextToken());

            /* 미네랄 뿌시기*/
            if(i%2==0) { //(짝수 -> 창영이 차례. -> 이쪽
                loop : for(int c=0;c<C;c++){
                    if(map[heights[i]][c]=='x'){
                        map[heights[i]][c] = '.';
                        break loop;
                    }
                }
            }else { //홀수 -> 상근이 차례, <- 이쪽
                loop : for(int c=C-1;c>=0;c--){
                    if(map[heights[i]][c]=='x'){
                        map[heights[i]][c] = '.';
                        break loop;
                    }
                }
            }

            /* 공중 미네랄 떨어지기*/

//            for(int j=0;j<R;j++){
//                System.out.println(Arrays.toString(map[j]));
//            }

            //공중 미네랄을 찾아 붙이자.

            visit = new boolean[R][C];
            for(int x=0;x<R;x++){
                for(int y=0;y<C;y++){
                    if(!visit[x][y] && map[x][y]=='x'){
                        BFS(x,y);
                    }
                }
            }
        }

        for(int x=0;x<R;x++){
            for(int y=0;y<C;y++){
                sb.append(map[x][y] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void BFS(int x, int y){
        Queue<Info> queue = new LinkedList<>();
        Queue<Info> isFloat = new LinkedList<>();
        boolean isBottom = false;

        queue.add(new Info(x,y));
        visit[x][y] = true;

        while(!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();
            if(cx == R-1){ // 연결된 애들 중 하나가 바닥에 붙었다면? OK
                isBottom = true;
            }
            isFloat.add(new Info(cx,cy));

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(isIn(nx,ny) && !visit[nx][ny] && map[nx][ny]=='x'){
                    visit[nx][ny] = true;
                    queue.add(new Info(nx,ny));
                }
            }
        }
        char[][] copymap = copy(map);

        //바닥에 붙었다!
        if(!isBottom){
            // 밑으로 내릴 수 있는지 확인
            down(isFloat, copymap);
        }else{
            return;
        }
    }

    public static void down(Queue<Info> isfloat, char[][] copymap){
        boolean isPossible = true;
        char[][] copyCopymap = copy(copymap); //임시 배열.
        PriorityQueue<Info> infos = new PriorityQueue<>();

        while(!isfloat.isEmpty()){
            infos.add(isfloat.poll());
        }
        System.out.println(infos);

        while(true){
//
//            for(int j=0;j<R;j++){
//                System.out.println(Arrays.toString(copymap[j]));
//            }
//            System.out.println("-----------------");

            for(Info info : infos){
                System.out.println(info);
                if(copyCopymap[info.x+1][info.y]=='.'){
                    System.out.println("success : x : " + info.x + " y :  "+ info.y );
                    copyCopymap[info.x][info.y] = '.';
                    copyCopymap[info.x+1][info.y] = 'x';
                }else{
                    System.out.println("fail : x : " + info.x + " y :  "+ info.y );
                    isPossible = false;
                    break;
                }

            }
            //만약 다 옮길 수 있으면?
            if(isPossible){
                copymap  = copy(copyCopymap); //갱신
            }else{
                break;
            }
        }

        //되는데까지 다 갱신했으면
        map = copy(copymap); //원래 map 갱신

    }

    public static char[][] copy(char[][] original){
        char[][] newmap = new char[R][C];

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                newmap[i][j] = original[i][j];
            }
        }

        return newmap;
    }

    public static class Info implements Comparable<Info>{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Info o) {
            if(this.x == o.x) return Integer.compare(o.y, this.y);
            else return Integer.compare(o.x, this.x);
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<R && y<C;
    }
}

/*       0 1 2 3 4 5 6 7
       0 . . . . . . . .
       1 . . . . . . . .
       2 . . . . . x . .
       3 . . . x x x . .
       4 . . . x x . . .
       5 . . x . x x . .
       6 . . x . . . x .
       7 . . x x . . x . */
