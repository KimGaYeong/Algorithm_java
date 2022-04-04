package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea_1767_2 {
    static int N;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    static int[][] map;
    static ArrayList<Info> infos;
    static int MaxCnt;
    static int MinLen;

    public static void main(String[] args) throws IOException {

        InputStream input = swea_1767_2.class.getResourceAsStream("input.txt");
        System.setIn(input);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            infos = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 1 && !isSide(r,c)) {
                        infos.add(new Info(r, c));
                    }
                }
            } // end of for(input)

            MaxCnt = Integer.MIN_VALUE;
            MinLen = Integer.MAX_VALUE;
            chooseCore(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(MinLen).append("\n");
        }
        System.out.println(sb.toString());
    } // end of main

    public static void chooseCore(int idx, int Core_cnt, int Min_len) {

        //base part
        if (idx == infos.size()) {
            if (MaxCnt < Core_cnt) {
                MaxCnt = Core_cnt;
                MinLen = Min_len;
            } else if (MaxCnt == Core_cnt) {
                MinLen = MinLen > Min_len ? Min_len : MinLen;
            }
            return;
        }

        //inductive part
        Info core = infos.get(idx);
        for (int dir = 0; dir < 4; dir++) {
            int cableLen = setCable(core, dir);
            if (cableLen != -1) {
                chooseCore(idx + 1, Core_cnt + 1, Min_len + cableLen);
                int r = core.r;
                int c = core.c;
                for (int i = 0; i < cableLen; i++) {
                    int nR = r + del[dir][0];
                    int nC = c + del[dir][1];
                    map[nR][nC] = 0;
                    r = nR;
                    c = nC;
                }
            }
        }
        chooseCore(idx + 1, Core_cnt, Min_len);
    }

    private static int setCable(Info core, int dir) {
        boolean canSetCable = false;
        int coreLen = 0;
        int r = core.r;
        int c = core.c;
        while (true) {
            int nR = r + del[dir][0];
            int nC = c + del[dir][1];

            if (!isIn(nR,nC)) { // 설치 가능
                canSetCable = true;
                break;
            }

            if (map[nR][nC] != 0) {
                break;
            }
            r = nR;
            c = nC;
        }
        r = core.r;
        c = core.c;
        if (canSetCable) { // 전선 설치 가능
            while (true) {
                int nR = r + del[dir][0];
                int nC = c + del[dir][1];
                if (!isIn(nR,nC)) { // 설치 가능
                    break;
                }
                coreLen++;
                map[nR][nC] = 2;
                r = nR;
                c = nC;
            }
            return coreLen;
        } else {
            return -1;
        }
    }

    private static class Info {
        int r, c;

        public Info(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static boolean isSide(int i, int j){
        return i==N-1 || i==0 || j==N-1 || j==0 ;
    }
    public static boolean isIn(int i, int j){
        return i>=0 && j>=0 && i<N && j<N;
    }
}

