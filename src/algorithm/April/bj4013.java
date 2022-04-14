package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

//0부터 시작했을 때 2, 6번 위치가 체크하는 부분임.

public class bj4013 {
    static int[][] magnet;
    static boolean[] magnetCheck;
    public static void main(String[] args) throws IOException {
        InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++)
        {
            int K = Integer.parseInt(br.readLine());
             magnet = new int[4][8];

            StringTokenizer st;
            for(int i=0; i<4; i++) {
                st =new StringTokenizer(br.readLine());
                for(int j=0; j<8; j++) {
                    magnet[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //자석번호는 1번부터 시작
            for(int i=0; i<K; i++) {
                magnetCheck = new boolean[4];
                st = new StringTokenizer(br.readLine());
                dfs(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
            }

            int answer =0;
            for(int i=0; i<4; i++) {
                if(magnet[i][0] == 1) {
                    answer += (int) Math.pow(2, i);
                }
            }
            sb.append("#" + t + " " + answer + "\n");
        }
        System.out.println(sb);
    }

    // 1 : 시계 -1 : 반시계
    private static void dfs(int num, int dir) {
        magnetCheck[num] = true;

        //왼쪽
        if (num - 1 >= 0 && !magnetCheck[num - 1]) {
            if (magnet[num][6] != magnet[num - 1][2]) {
                dfs(num - 1, dir * -1);
            }
        }

        //오른쪽
        if (num + 1 < 4 && !magnetCheck[num + 1]) {
            if (magnet[num][2] != magnet[num + 1][6]) {
                dfs(num + 1, dir * -1);
            }
        }

        //회전

        rotate(num, dir);

    }

    private static void rotate(int magnetIdx, int dir) {
        if (dir == 1) {
            int temp = magnet[magnetIdx][7];
            for (int i = 6; i >= 0; i--) {
                magnet[magnetIdx][i + 1] = magnet[magnetIdx][i];
            }
            magnet[magnetIdx][0] = temp;
        } else {
            int temp = magnet[magnetIdx][0];
            for (int i = 1; i < 8; i++) {
                magnet[magnetIdx][i - 1] = magnet[magnetIdx][i];
            }
            magnet[magnetIdx][7] = temp;
        }
    }
}


/**
 * 12345
 * 23451 -> 반시계
 *
 * 12345
 * 51234 -> 시계
 */