package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
문제 풀기 전에 든 생각으로는... 쉬워보이는데 좌표를 생각하는 방식에 따라 ? 달라질 것 같다.
회전을 N번 하고 멈춘다 ----> N+1만큼 이동해서 만나면 된다.
한 방향이 연속적으로 6번 되면 멈출수밖에 없음. (좌회전, 우회전 개념)
 */
public class bj17370 {
    static int N, count;
    static int[][] del = {{-1,0},{-1,1},{1,1},{1,0},{1,-1},{-1,-1}}; //위 오른쪽위 오른쪽아래 아래 왼쪽아래 왼쪽위
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visit = new boolean[100][100];
        count = 0;
        visit[50][50] = true;
        DFS(49,50,0,0); //d==0 위부터 올라감.

        System.out.println(count);
    }

    public static void DFS(int x, int y, int cnt, int d){
        if(visit[x][y]){
            if(cnt==N) count++;
            return;
        }

        if(cnt==N) return;

        int[] where = Del(d);
        System.out.println(where[0] + " " + where[1]);

        for(int i=0;i<2;i++){
            int nd = where[i];
            int nx = x + del[nd][0];
            int ny = y + del[nd][1];

            visit[x][y] = true;
            DFS(nx,ny,cnt+1,nd);
            visit[x][y] = false;
        }
    }

    public static int[] Del(int d){
        int[] del2 = new int[2];
        //{{-1,0}위,{-1,1}오른쪽위,{1,1}오른쪽아래,{1,0}아래,{1,-1}왼쪽아래,{-1,-1}왼쪽위};
        if(d==0){ //위 -> 왼위 오위
            del2[0] = 5; del2[1] = 1;
        }else if (d==1){ //오위 -> 위 오아
            del2[0] = 0; del2[1] = 2;
        }else if (d==2){ //오아 -> 아래 오위
            del2[0] = 3; del2[1] = 1;
        }else if (d==3){ //아래 -> 왼아 오아
            del2[0] = 4; del2[1] = 2;
        }else if (d==4){ //왼아 -> 왼위 아래
            del2[0] = 5; del2[1] = 3;
        }else if (d==5){ //왼위 -> 위 왼아
            del2[0] = 0; del2[1] = 4;
        }
        return del2;
    }
}
