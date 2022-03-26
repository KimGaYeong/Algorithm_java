package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj13460 {
    static int N, M;
    static int Rx, Ry, Bx, By;
    static char[][] map;
    static HashSet<Info> hashSet;
    static boolean check;
    static int[][] del = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    //아래로 굴리기, 위로 굴리기, 오른쪽으로 굴리기, 왼쪽으로 굴리기

    public static void main(String[] args) throws IOException {
        InputStream input = bj13460.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        hashSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
                if(map[i][j] =='R'){
                    map[i][j] = '.';
                    Rx = i; Ry = j;
                }
                if(map[i][j] =='B'){
                    map[i][j] = '.';
                    Bx = i; By = j;
                }
            }
        }


        // .빈칸 #장애물 0구멍 R성공볼 B실패볼

        Solve();
    }

    public static void Solve(){
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(Rx, Ry, Bx, By, 0));
        hashSet.add(new Info(Rx, Ry, Bx, By));

        while(!queue.isEmpty()){
            Info info = queue.poll();
            int crx = info.rx;
            int cry = info.ry;
            int cbx = info.bx;
            int cby = info.by;
            int cnt = info.cnt;

            if(cnt>=10){
                System.out.println(-1);
                return;
            }

            for(int d=0;d<4;d++){
                boolean checkB = false, checkR = false;

                //먼저 공부터 굴린다.

                check = false;
                int[] nb = roll(cbx, cby, d);
                if(check){
                    checkB = true;
                }

                check = false;
                int[] nr = roll(crx, cry, d);
                if(check){
                    checkR = true;
                }


                if(checkB && !checkR){ //파랑이만 들어감.
                    continue;
                }else if(!checkB && checkR) { //빨강이만 들어감
                    System.out.println(cnt+1);
                    return;
                }else if(checkB && checkR){ //둘 다 들어감
                    continue;
                }

                //겹치는지 확인하기
                if(nr[0] == nb[0] && nr[1] == nb[1]){
                    if(d ==0){ //아래로 굴리다 만남 / x만 확인
                        if(crx > cbx) nb[0] -=1; // 파란공이 빨간공 누르는 중.
                        else nr[0] -=1;
                    }else if(d ==1){ //위로 굴리다 만남 / x만 확인
                        if(crx > cbx) nr[0] +=1; // 빨간공이 파란공 누르는 중.
                        else nb[0] +=1;
                    }else if(d ==2){ //오른쪽으로 굴리다 만남 / y만 확인
                        if(cry > cby) nb[1] -=1; // 파란공이 빨간공 누르는 중.
                        else nr[1] -=1;
                    }else{ //왼쪽으로 굴리다 만남 / y만 확인
                        if(cry > cby) nr[1] +=1; // 빨간공이 파란공 누르는 중.
                        else nb[1] +=1;
                    }
                }
                Info tmp = new Info(nr[0], nr[1], nb[0], nb[1], cnt+1);

                if(!hashSet.contains(tmp)){
                    hashSet.add(tmp);
                    queue.add(tmp);
                }

            }
        }
        System.out.println(-1);
    }

    public static int[] roll(int x, int y, int d){
        int[] ans = new int[2];

        while(true){
            int nx = x + del[d][0];
            int ny = y + del[d][1];

            if(isIn(nx,ny) && map[nx][ny] != '#'){
                x = nx; y = ny;
                if(map[nx][ny] == 'O'){
                    check = true;
                    break;
                }
            }else{
                break;
            }
        }
        ans[0] = x;
        ans[1] = y;

        return ans;
    }

    public static boolean isIn(int x, int y){
        return x>=1 && y>=1 && x<N-1 && y<M-1;
    }
    public static class XY{
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class Info{
        //Red, Blue ball의 x,y좌표
        int rx, ry, bx, by, cnt;

        public Info(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
        public Info(int rx, int ry, int bx, int by) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return rx == info.rx && ry == info.ry && bx == info.bx && by == info.by && cnt == info.cnt;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rx, ry, bx, by, cnt);
        }
    }
}

/*
8 8
########
#.####.#
#...#B##
#.##.R.#
######.#
##.##O.#
###.##.#
########
 */