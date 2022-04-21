package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj17837_2 {
    static int N, K; //N:체스판 크기 K:말 개수
    static Horse[] horses;
    static ArrayList<Integer>[][] map;
    static int[][] color;
    static int[][] del = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //우 좌 상 하

    public static void main(String[] args) throws IOException {
        InputStream input = bj17837.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //입력
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new int[N + 1][N + 1];
        map = new ArrayList[N + 1][N + 1];
        horses = new Horse[K + 1];

        //
        for(int r = 1 ; r <= N ; ++r) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1 ; c <= N ; ++c) {
                color[r][c] = Integer.parseInt(st.nextToken());
                map[r][c] = new ArrayList<>();
            }
        }

        // 체스말 입력
        for(int i = 1 ; i <= K ; ++i) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            horses[i] = new Horse(i, r, c, d );
            map[r][c].add(i);
        }

        game();
    }

    public static void game() {
        int t = 0;

        //1000번 넘으면 ㄴㄴ
        while(t <= 1000) {
            t++;

            for(int i = 1 ; i <= K ; ++i) {
                //움직임.

                if(move(i)) {
                    System.out.println(t);
                    return;
                };
            }
        }
        // 1000번 넘으면 ㄴㄴ
        System.out.println(-1);
    }

    public static boolean move(int i) {
        Horse horse = horses[i];

        //말에 따라 이동할 위치,
        int nx = horse.r + del[horse.d][0];
        int ny = horse.c + del[horse.d][1];

        //다음 칸이 흰 색인 경우 : 그 칸으로 이동.(쌓음)
        //다음 칸이 빨간색인 경우 : 순서 뒤집어서 해당 칸에 쌓음.
        //다음 칸이 파란색인 경우 : 말의 이동을 반대로 하고 한 칸 이동.그래도 파란색이면 가만히 있음.


        //map 벗어나거나 blue인경우
        if(!isIn(nx,ny) || color[nx][ny] == 2) {
            upsidedown(horse);
            nx = horse.r + del[horse.d][0];
            ny = horse.c + del[horse.d][1];
        }
        //빨강, 하양
        if(isIn(nx,ny) && color[nx][ny] != 2) {
            Play(horse, nx, ny);
            if(map[nx][ny].size() >= 4) return true;
        }

        return false;
    }

    public static boolean isIn(int x, int y){
        return x>=1 && y>=1 && x<=N && y<=N;
    }

    //말 이동하기.
    public static void Play(Horse now, int nr, int nc) {
        //현재 있는 애들.
        ArrayList<Integer> from = map[now.r][now.c];
        ArrayList<Integer> to = map[nr][nc];

        // 현재 칸에서의 높이
        int height = from.indexOf(now.idx);
        int fromSize = from.size();
        int toSize = to.size();

        switch(color[nr][nc]) {
            case 0:
                //white
                // to로 말 옮기기
                for(int i = height ; i < fromSize ; ++i) {
                    int idx = from.get(i);
                    horses[idx].r = nr;
                    horses[idx].c = nc;
                    to.add(idx);
                }
                // 말 지우기
                remove(from, fromSize - 1, height);

                break;
            case 1:
                //red
                //말 뒤집어서 옮기기
                for(int i = fromSize - 1 ; i >= height ; --i) {
                    int idx = from.get(i);
                    horses[idx].r = nr;
                    horses[idx].c = nc;
                    to.add(idx);
                }
                // 말 지우기
                remove(from, fromSize - 1, height);
                break;
        }
    }

    public static void upsidedown(Horse horse) {
        // 방향이 우, 상이면 +1
        if(horse.d % 2 == 0) {
            horses[horse.idx].r = horse.r;
            horses[horse.idx].c = horse.c;
            horses[horse.idx].d = horse.d + 1;
        // 방향이 좌, 하면 -1
        } else {
            horses[horse.idx].r = horse.r;
            horses[horse.idx].c = horse.c;
            horses[horse.idx].d = horse.d - 1;
        }
    }

    // 말 지워주기
    public static void remove(ArrayList<Integer> list, int top, int bottom) {
        for(int i = top ; i >= bottom ; --i) {
            list.remove(i);
        }
    }

    public static class Horse {
        int idx, r, c, d;

        Horse(int id, int r, int c, int d){
            this.idx = id;
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}