package algorithm.August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj17136 {
    static int answer;
    static int[][] map;
    static int[] cnts = {5,5,5,5,5}; //다섯 장씩 가지고 있음.
    public static void main(String[] args) throws IOException {
        InputStream input = bj1563.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[10][10];

        for(int i=0;i<10;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<10;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        DFS(0,0);

        if(answer==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    public static void DFS(int idx, int cnt){
        if(idx==100){
            answer = Math.min(answer, cnt);
            return;
        }

        if(answer <= cnt) return;

        int x = idx / 10;
        int y = idx % 10;

        if(map[x][y] ==1){
            for(int i=4;i>=0;i--){
                if(cnts[i]>0 && check(x, y, i)){
                    fill(x, y, i, 0);
                    cnts[i]--;
                    DFS(idx+1, cnt+1);
                    cnts[i]++;
                    fill(x, y, i, 1);
                }
            }
        }else{
            DFS(idx+1, cnt);
        }
    }

    public static void fill(int x, int y, int N, int num){
        N+=1;

        for(int i=x;i<x+N;i++){
            for(int j=y;j<y+N;j++){
                map[i][j] = num;
            }
        }
    }

    public static boolean check(int x, int y, int N){
        N+=1;

        if(x+N>10 || y+N>10) return false;

        for(int i=x;i<x+N;i++){
            for(int j=y;j<y+N;j++){
                if(map[i][j]!=1) return false;
            }
        }

        return true;
    }
}
