package algorithm.April;


import java.io.*;
import java.util.*;

public class BOJ_20058 {

    static int N, Q, Len;
    static int[][] map;
    static int[] L;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        L = new int[Q];

        Len = 1<<N;

        map = new int[Len][Len];
        for(int i = 0; i< Len; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< Len; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<Q; i++) {
            int size = 1<<L[i];
            subSquares(size);
            //printMap();
            melt();
        }

        System.out.println(countSum());
        System.out.println(findLargest());
    }

    public static int findLargest() {
        int largest = Integer.MIN_VALUE;

        boolean[][] visited = new boolean[Len][Len];

        for(int r=0; r<Len; r++) {
            for(int c=0; c<Len; c++) {
                if(visited[r][c]) continue;

                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{r, c});
                visited[r][c] = true;
                int temp = 0;

                while(!q.isEmpty()) {
                    int[] curr = q.poll();
                    temp++;
                    for(int d=0; d<4; d++) {
                        int nr = curr[0]+dr[d];
                        int nc = curr[1]+dc[d];

                        if(nr<0 || nr>=Len || nc<0 || nc>=Len) continue;
                        if(visited[nr][nc] || map[nr][nc] <= 0) continue;

                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});

                    }
                }
                largest = Math.max(largest, temp);
            }
        }
        return largest;
    }

    public static void printMap() {
        for(int i=0; i<Len; i++) {
            for(int j=0; j<Len; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void melt() {
        boolean[][] checked = new boolean[Len][Len];

        for(int r=0; r<Len; r++) {
            for(int c=0; c<Len; c++) {
                int adjCount = 0;
                if(map[r][c] == 0) continue;
                for(int d=0; d<4; d++) {
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr < 0 || nr >= Len || nc < 0 || nc >= Len || map[nr][nc] <= 0) continue;
                    adjCount++;
                }
                if(adjCount < 3) checked[r][c] = true;
            }
        }

        for(int r=0; r<Len; r++) {
            for(int c=0; c<Len; c++) {
                if(checked[r][c]) {
                    map[r][c]--;
                }
            }
        }
    }

    public static int countSum() {
        int result = 0;
        for(int i=0; i<Len; i++) {
            for(int j=0; j<Len; j++) {
                result += map[i][j];
            }
        }
        return result;
    }

    public static void subSquares(int size) {
        int[][] roll = new int[Len][Len];
        for(int r=0; r<Len; r+=size) {
            for(int c=0; c<Len; c+=size) {
                for(int i=0; i<size; i++) {
                    for(int j=0; j<size; j++) {
                        roll[c+i][r+j] = map[c+size-j-1][r+i];
                    }
                }
            }
        }
        map = roll;
    }
}