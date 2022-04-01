package algorithm.April;

import java.util.*;
import java.io.*;

public class bj1194_2 {

    public static class Status{
        int row, col, move;
        String keys;

        public Status(int r, int c, int m, String k) {
            row = r; col = c; move = m; keys = k;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];

        Queue<Status> q = new LinkedList<>();
        visit = new ArrayList[N][M];

        for(int i=0;i<N;i++) {
            String line = br.readLine();
            for(int j=0;j<M;j++) {
                visit[i][j] = new ArrayList<>();

                char c = line.charAt(j);
                if(c=='0') {
                    q.add(new Status(i, j, 0, "000000"));
                    visit[i][j].add("000000");
                    c = '.';
                }

                map[i][j] = c;
            }
        }

        int[][] d = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
        int answer = -1;

        loop : while(!q.isEmpty()) {
            Status cur = q.poll();

            for(int i=0; i<4;i++) {
                int nr = cur.row + d[0][i];
                int nc = cur.col + d[1][i];

                if(0<=nr && nr<N && 0<=nc && nc<M) {
                    if(map[nr][nc]=='.' && check(nr, nc, cur.keys)) {
                        visit[nr][nc].add(cur.keys);
                        q.add(new Status(nr, nc, cur.move+1, cur.keys));
                    }
                    if("abcdef".contains(String.valueOf(map[nr][nc]))) {
                        String newKey = makeKey(cur.keys, map[nr][nc]-'a');
                        if(check(nr, nc, newKey)) {
                            visit[nr][nc].add(newKey);
                            q.add(new Status(nr, nc, cur.move+1, newKey));
                        }
                    }
                    if("ABCDEF".contains(String.valueOf(map[nr][nc])) && cur.keys.charAt(map[nr][nc]-'A')=='1' && check(nr,nc, cur.keys)) {
                        visit[nr][nc].add(cur.keys);
                        q.add(new Status(nr, nc, cur.move+1, cur.keys));
                    }
                    if(map[nr][nc]=='1') {
                        answer = cur.move+1;
                        break loop;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static ArrayList<String>[][] visit;

    public static boolean check(int r, int c, String keys) {
        for(int i=0;i<visit[r][c].size();i++) {
            String info = visit[r][c].get(i);
            if(keys.equals(info))  return false;
        }

        return true;
    }

    public static String makeKey(String key, int idx) {
        StringBuilder sb = new StringBuilder();
        if(idx>0) sb.append(key.substring(0, idx));
        sb.append('1');
        if(idx<6) sb.append(key.substring(idx+1, 6));

        return sb.toString();
    }

}
