package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
  물고기 : 번호, 방향이 있다.
  물고기가 같은 번호를 갖는 일은 없다. -> 물고기 번호로 구분 가능

  상어 처음 방향 : (0,0) 물고기랑 같은 방향
 */

/*
 * 번호가 작은 물고기부터 한 칸 이동. - >빈 칸, 다른 물고기가 있는 칸은 이동 가능.
 * 상어가 있는 칸은 이동 불가능.
 *
 * 물고기는 이동할 수 있는 칸을 향할 때 까지 방향을 45도 반시계 회전한다.
 * 이동할 수 없으면? 이동하지 않는다. 그 외의 경우는 이동한다. 다른 물고기가 있는 칸으로 이동할 경우 서로의 위치를 바꾼다.
 */
public class bj19236_1 {
    static int[][] map;
    static shark shark;
    static LinkedList<fish> fishes, eatfish;
    static int[][] del = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        fishes = new LinkedList<>();

        for(int i=0;i<4;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++){
                int idx = Integer.parseInt(st.nextToken());
                int del = Integer.parseInt(st.nextToken())-1;
                fishes.add(new fish(idx, del, i,j));
            }
        }
        //a : 물고기 번호, b : 방향
        //물고기 저장.
        Collections.sort(fishes);

        play();
        solve();

    }

    public static void play(){
        shark = new shark(fishes.get(0).del, 0, 0);
        fishes.remove(new fish(0,0));

    }

    /*
     * 번호가 작은 물고기부터 한 칸 이동. - >빈 칸, 다른 물고기가 있는 칸은 이동 가능.
     * 상어가 있는 칸은 이동 불가능.
     *
     * 물고기는 이동할 수 있는 칸을 향할 때 까지 방향을 45도 반시계 회전한다.
     * 이동할 수 없으면? 이동하지 않는다. 그 외의 경우는 이동한다. 다른 물고기가 있는 칸으로 이동할 경우 서로의 위치를 바꾼다.
     */

    public static void solve(){
        boolean IsSharksMove = true;
        while(true){
            if(!IsSharksMove){//상어가 움직이지 못 할 때까지
                break;
            }

            // 물고기의 대이동
            for(fish onefish : fishes){
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
                int nextidx = fishes.indexOf(new fish(nx,ny));
                int nd = fishes.get(nextidx).del;

                int tmpx = cx; int tmpy = cy; int tmpd = cd;
                onefish.x = nx; onefish.y = ny; onefish.del = nd;
                fishes.get(nextidx).x = tmpx; fishes.get(nextidx).y = tmpy; fishes.get(nextidx).del= cd;

            }

            //상어가 잡아먹어요
            eatfish = new LinkedList<>();
            int sx = shark.x;
            int sy = shark.y;
            int sd = shark.del;

            while(true){
                int nx = sx + del[sd][0];
                int ny = sy + del[sd][1];

                if(isIn(nx,ny)){
                    int nextidx = fishes.indexOf(new fish(nx,ny));
                    if(nextidx==-1) continue;
                    else{
                        int nd = fishes.get(nextidx).del;
                        eatfish.add(new fish(nextidx, nd, nx,ny));
                    }
                }else{
                    break;
                }
            }


        }
    }

    // 반시계 방향으로 회전
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

    public static class shark{
        int del, x, y;

        public shark(int del, int x, int y) {
            this.del = del;
            this.x = x;
            this.y = y;
        }
    }

    public static class fish implements Comparable<fish>{
        int idx, del, x, y;

        public fish(int idx, int del, int x, int y) {
            this.idx = idx;
            this.del = del;
            this.x = x;
            this.y = y;
        }

        public fish(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "fish{" +
                    "idx=" + idx +
                    ", del=" + del +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            fish fish = (fish) o;
            return x == fish.x && y == fish.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(fish o) {
            return this.idx-o.idx;
        }
    }
}
