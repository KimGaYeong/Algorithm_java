package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj17143 {
    static int R, C, M;
    static Queue<Integer> queue;
    static boolean[] visit;
    static int[][] map, tmp;
    static int[][] del  ={{0,0},{-1,0},{1,0},{0,1},{0,-1}};
    static List<Shark> sharks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); //상어 마릿수 최대 만마리.

        map = new int[R + 1][C + 1]; //맵
        visit = new boolean[M];
        sharks = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); //속력
            int d = Integer.parseInt(st.nextToken()); //방향
            int z = Integer.parseInt(st.nextToken()); //크기 * 두 상어가 같은 크기 ㄴㄴ 얘로 구분.
            map[r][c] = z;
            sharks.add(new Shark(r, c, s, d, z));
        }

        //만개를 돌려서 r,c가 같으면서 나머지 등등이 1이되는 애를 찾고.... 제일 r이 작은애를 먹고 겹치면 z가 큰 애로..
        //중요한건 시간대 c에 상어의 r위치가 c이냐가 중요한 것 아닐까 ??!?!?!?!?!?!??/

        int answer = 0;
        for (int c = 1; c <= C; c++) {
            //인간 이동.
            //낚시 시작
            for (int i = 1; i <= R; i++) {
                if (map[i][c] != 0) { //가장 가까운애. 한놈만 먹는다
                    answer += map[i][c];
                    sharks.remove(new Shark(map[i][c]));
                    map[i][c] = 0;
                    break;
                    //얘를 지움.
                }
            }

            //낚시 했음 상어 움직이기
            move();

            //상어 잡아먹기
            eat();
        }
        System.out.println(answer);
    }

    public static int switchdel(int x){
        if(x==1){
            return 2;
        }else if(x==2){
            return 1;
        }else if(x==3){
            return 4;
        }else{
            return 3;
        }
    }

    public static void move(){
        //1<->2, 3<->4
        for(Shark s : sharks){
            for(int i=0;i<s.s;i++){
                int x = s.r + del[s.d][0];
                int y = s.c + del[s.d][1];
                if(!isIn(x,y)) {
                    if(x<1) x =2;
                    if(y<1) y =2;
                    if(x>R) x = 2*R-x;
                    if(y>C) y = 2*C-y;
                    int nextd = switchdel(s.d);
                    s.d = nextd;
                }
                s.r = x;
                s.c = y;
            }
        }
    }
    public static boolean isIn(int x, int y){
        return x>0 && y>0 && x<=R && y<=C;
    }

    public static void eat(){
        queue = new LinkedList<>();
        tmp = new int[R+1][C+1];

        for(Shark s : sharks){
            int x = s.r;
            int y = s.c;
            if(tmp[x][y]==0){ //아무도 없음 넣고
                tmp[x][y] = s.z;
            }
            if(tmp[x][y] !=0 && tmp[x][y] < s.z){ //누가 있음. 근데 내 크기가 더 커!
                queue.offer(tmp[x][y]); //원래 크기는 삭제~
                tmp[x][y] = s.z; //내 크기를 집어넣자.
            }else if(tmp[x][y] !=0 && tmp[x][y] > s.z){ //누가 있음. 근데 쟤 크기가 더 커!
                queue.offer(s.z); //원래 크기는 삭제~
            }
        }

        while(!queue.isEmpty()){
            sharks.remove(new Shark(queue.poll()));
        }
        map = tmp;
    }


    public static class Shark{
        int r,c,s,d,z;

        public Shark(int z) { //크기로 구분 가능
            this.z = z;
        }

        public Shark(int r, int c, int s, int d, int z ) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Shark shark = (Shark) o;
            if(this.z == shark.z) return true;
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c, s, d, z);
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    '}';
        }
    }
}