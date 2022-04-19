package algorithm.April;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class bj19236 {

    static int[][] del = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
    static List<Fish> fishes;
    static Shark shark;
    static int maxSum = 0;

    public static void main(String[] args) throws Exception {

        InputStream input = bj19236.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[4][4];
        fishes = new ArrayList<>();

        // input
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int idx= Integer.parseInt(st.nextToken());
                int del = Integer.parseInt(st.nextToken()) - 1;

                fishes.add(new Fish(i, j, idx, del, true));
                map[i][j] = idx;
            }
        }

        //물고기 idx순 정렬
        Collections.sort(fishes);
        System.out.println(fishes);

        // 상어는 (0, 0) 물고기를 먹고 시작하며 위치는 -1 로 표현
        int tmpidx = fishes.indexOf(new Fish(0,0));
        Fish firstfish = fishes.get(tmpidx);
        shark = new Shark(0, 0, firstfish.del, firstfish.idx);
        firstfish.isAlive = false; //쥬금.
        map[0][0] = -1; //상어 위치

        dfs(map, shark, fishes);
        System.out.println(maxSum);
    }

    // 상어가 잡아먹을 물고기가 없을 때 까지.
    public static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
        // 잡아먹은 양의 최대값 구하기
        if (maxSum < shark.eatSum) {
            maxSum = shark.eatSum;
        }

        // 모든 물고기 순서대로 이동
        movefish(arr, fishes);

        //이동 했으면 상어의 냠냠타임 -> 아무리 많이 먹어도 내 방향으로 1, 2, 3칸까지
        for (int d = 1; d < 4; d++) {
            int nx = shark.x + del[shark.dir][0] * d;
            int ny = shark.y + del[shark.dir][1] * d;

            if (isIn(nx,ny) && arr[nx][ny] > 0) {
                // 물고기 잡아먹고 dfs
                int[][] copymap = copymap(arr);
                List<Fish> fishCopies = copyFishes(fishes);

                copymap[shark.x][shark.y] = 0;

                Fish f = fishCopies.get(arr[nx][ny] - 1);
                Shark newShark = new Shark(f.x, f.y, f.del, shark.eatSum + f.idx);
                f.isAlive = false;
                copymap[f.x][f.y] = -1;

                dfs(copymap, newShark, fishCopies);

                //아직 안했다.
                //죄송

            }
        }
    }

    // 이동가능한 칸은 빈칸, 다른 물고기가 있는 칸
    // 45도 반시계 방향으로 회전하면서 스캔
    public static void movefish(int[][] arr, List<Fish> fishes) {
        // 물고기의 대이동
        for(Fish onefish : fishes){
            int cx = onefish.x;
            int cy = onefish.y;
            int cd = onefish.del;

            int nx = cx + del[cd][0];
            int ny = cy + del[cd][1];

            //이동할 수 있으면?
            if(!isIn(nx,ny) || (shark.x == nx && shark.y == ny)){
                cd = turn(cx, cy, cd);
            }

            // 이동시키기
            nx = cx + del[cd][0];
            ny = cy + del[cd][1];
            int nextidx = fishes.indexOf(new Fish(nx,ny));
            int nd = fishes.get(nextidx).del;

            int tmpx = cx; int tmpy = cy; int tmpd = cd;
            onefish.x = nx; onefish.y = ny; onefish.del = nd;
            fishes.get(nextidx).x = tmpx; fishes.get(nextidx).y = tmpy; fishes.get(nextidx).del= cd;

        }

    }

    public static int[][] copymap(int[][] arr) {
        int[][] tmp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            System.arraycopy(arr[i], 0, tmp[i], 0, 4);
        }

        return tmp;
    }

    public static List<Fish> copyFishes(List<Fish> fishes) {
        List<Fish> tmp = new ArrayList<>();

        for(Fish f : fishes){
            tmp.add(f);
        }
        return tmp;
    }

    public static class Shark {
        int x, y, dir, eatSum;

        Shark() { }

        Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }

    public static int turn(int x, int y, int d){
        re : while(true){
            d = ((d+1)%8);
            int nx = x + del[d][0];
            int ny = y + del[d][1];
            if(isIn(nx,ny) && !(shark.x == nx && shark.y == ny)){
                break re;
            }
        }
        return d;
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<4 && y<4;
    }

    public static class Fish implements Comparable<Fish>{
        int x, y, idx, del;
        boolean isAlive = true;

        Fish(int x, int y, int id, int del, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.idx = id;
            this.del = del;
            this.isAlive = isAlive;
        }

        public Fish(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fish fish = (Fish) o;
            return x == fish.x && y == fish.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Fish o) {
            return this.idx-o.idx;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", idx=" + idx +
                    ", del=" + del +
                    ", isAlive=" + isAlive +
                    '}';
        }
    }


}