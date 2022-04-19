package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj17837_2 {
    static Marker[] markers;
    static ArrayList<Integer>[][] map;
    static int[][] color;
    static int[][] del = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    static int N, K;

    public static void main(String[] args) throws IOException {
        InputStream input = bj17837.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new int[N + 1][N + 1];
        map = new ArrayList[N + 1][N + 1];
        markers = new Marker[K + 1];

        // 컬러맵과 마커맵 초기화
        for(int r = 1 ; r <= N ; ++r) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1 ; c <= N ; ++c) {
                color[r][c] = Integer.parseInt(st.nextToken());
                map[r][c] = new ArrayList<>();
            }
        }

        // 마커배열 초기화
        for(int i = 1 ; i <= K ; ++i) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            markers[i] = new Marker(i, r, c, d - 1);
            map[r][c].add(i);
        }

        game();
    }

    private static void game() {
        int times = 0;

        while(times <= 1000) {
            times++;

            for(int i = 1 ; i <= K ; ++i) {
                if(move(i)) {
                    System.out.println(times);
                    return;
                };
            }
        }
        // 1000번 안에 게임이 끝나지 않을 경우
        System.out.println(-1);
    }

    private static boolean move(int i) {
        Marker now = markers[i];

        int nr = now.r + del[now.d][0];
        int nc = now.c + del[now.d][1];

        // 맵을 벗어나거나 파란색이면
        if(nr > N || nr < 1 || nc > N || nc < 1 || color[nr][nc] == 2) {
            changeDirection(now);
            nr = now.r + del[now.d][0];
            nc = now.c + del[now.d][1];
        }

        // 맵을 벗어나지 않고 파란색이 아니면
        if(nr <= N && nr >= 1 && nc <= N && nc >= 1 && color[nr][nc] != 2) {
            actionByColor(now, nr, nc);
            if(map[nr][nc].size() >= 4) return true;
        }

        return false;
    }

    private static void actionByColor(Marker now, int nr, int nc) {
        ArrayList<Integer> from = map[now.r][now.c];
        ArrayList<Integer> to = map[nr][nc];

        // 현재 칸에서의 높이
        int cpt = from.indexOf(now.id);

        int fromSize = from.size();
        int toSize = to.size();

        switch(color[nr][nc]) {
            case 0:
                // to로 마커 옮기기
                for(int i = cpt ; i < fromSize ; ++i) {
                    int marker = from.get(i);
                    // 마커리스트 업데이트하기
                    updateList(marker, nr, nc);
                    to.add(marker);
                }
                // from에서 마커 지우기
                removeMarkers(from, fromSize - 1, cpt);

                break;
            case 1:
                // to로 마커 뒤집어서 옮기기
                for(int i = fromSize - 1 ; i >= cpt ; --i) {
                    int marker = from.get(i);
                    updateList(marker, nr, nc);
                    to.add(marker);
                }
                // from에서 마커 지우기
                removeMarkers(from, fromSize - 1, cpt);
                break;
        }
    }

    private static void changeDirection(Marker marker) {
        // 방향이 짝수면 +1
        if(marker.d % 2 == 0) {
            updateList(marker.id, marker.r, marker.c, marker.d + 1);
            // 방향이 홀수면 -1
        } else {
            updateList(marker.id, marker.r, marker.c, marker.d - 1);
        }
    }

    // 마커 지워주기
    private static void removeMarkers(ArrayList<Integer> list, int top, int bottom) {
        for(int i = top ; i >= bottom ; --i) {
            list.remove(i);
        }
    }

    // 마커리스트 동기화를 위한 함수
    private static void updateList(int marker, int nr, int nc) {
        markers[marker].r = nr;
        markers[marker].c = nc;
    }
    private static void updateList(int marker, int nr, int nc, int nd) {
        markers[marker].r = nr;
        markers[marker].c = nc;
        markers[marker].d = nd;
    }

    private static class Marker {
        int id, r, c, d;

        Marker(int id, int r, int c, int d){
            this.id = id;
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}