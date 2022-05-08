package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj12100 {
    static int N, answer;
    static int[][] map;
    static int[] arr = {0,1,2,3};
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makePer2(new int[5], 0);

    }

    private static void makePer2(int[] dels, int cnt) {
        if (cnt ==5) {
            //DFS
            for(int i=0;i<5;i++){
                //move(copyMap(map), dels[i]);
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                dels[cnt] = arr[i];
                makePer2(dels, cnt + 1);
            }
        }
    }


    public static int[][] copyMap(int[][] originMap){
        int[][] newMap = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                newMap[i][j] = originMap[i][j];
            }
        }

        return newMap;
    }

    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
