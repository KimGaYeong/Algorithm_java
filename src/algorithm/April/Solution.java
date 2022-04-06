package algorithm.April;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    static int Ans = Integer.MAX_VALUE;
    static int[][] map, n_map;
    static int N, W, H;
    static int[] shoot_serial;
    public static void main(String[] args) throws IOException {
        InputStream input = swea_5656.class.getResourceAsStream("input.txt");
        System.setIn(input);        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T=Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            Ans=Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            n_map = new int[H][W];
            shoot_serial = new int[N];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Search(0);
            sb.append("#"+test_case+" "+Ans+"\n");

        }
        System.out.println(sb);
    }
    public static void Down() {
        int temp;

        for (int i = 0; i < W; i++) {
            for (int s = 0; s < H-1; s++) {
                for (int h = H-2; h >= 0; h--) {
                    if(n_map[h][i]>0 && n_map[h+1][i]==0) {
                        temp = n_map[h][i];
                        n_map[h][i] = n_map[h+1][i];
                        n_map[h+1][i] = temp;
                    }
                }
            }
        }
    }

    public static void Pop(int[] rc) {
        int range = n_map[rc[0]][rc[1]];

        int nr;
        int nc;
        if(range>0) {
            n_map[rc[0]][rc[1]]=0;
            for (int d = 0; d < 4; d++) {
                for (int i = 1; i < range; i++) {
                    nr = rc[0]+i*del[d][0];
                    nc = rc[1]+i*del[d][1];
                    if(nr>=0 && nr<H && nc>=0 && nc<W) {
                        if(n_map[nr][nc]>0) {
                            Pop(new int[] {nr,nc});
                        }
                    }
                }
            }
        }
    }

    public static void Shoot(int w) {
        for (int i = 0; i < H; i++) {
            if(n_map[i][w]>0) {
                Pop(new int[] {i,w});
                return;
            }
        }
    }

    public static void Search(int cnt) {

        if(cnt==N) {
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    n_map[i][j] = map[i][j];
                }
            }
            for (int i = 0; i < N; i++) {
                Shoot(shoot_serial[i]);
                Down();
            }
            int temp=0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if(n_map[i][j]>0)temp++;
                }
            }
            Ans = Math.min(Ans, temp);
            return;
        }
        for (int i = 0; i < W; i++) {
            shoot_serial[cnt]=i;
            Search(cnt+1);
        }
    }
}