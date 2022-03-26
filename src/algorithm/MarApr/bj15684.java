package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 추가해야 하는 가로선 개수의 최솟값을 출력
 * 2. 정답이 3보다 큰 값이면 -1을 출력
 * 추가 개수가 0,1,2,3만 보면 됨. 작은수에서 나왔으면 더 찾을 필요 X
 *
 * 사다리 개수는 n~n+1 사이에 짝수개 존재해야함.
 */
public class bj15684 {
    static int N, M, H;
    static int[][] ladder;
    static boolean check = false;
    public static void main(String[] args) throws IOException {
        InputStream input=  bj15684.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H+1][N+1];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = b+1;
            ladder[a][b+1] = b;
        }

        int k=0;
        for(k=0;k<=3;k++){
            combination(ladder, k);
            if(check){ //연결 됐으면.
                break;
            }
        }
        System.out.println(k==4? -1:k);

    }

    public static boolean isCheck(int[][] lad){
        for(int i=1;i<=N;i++){
            int k =i;
            for(int j=1;j<=H;j++){
                if(lad[j][k] !=0) k= lad[j][k];
            }
            if(k != i) return false;
        }
        return true;
    }
    public static void combination(int[][] lad, int ladcnt){
        if(check){ //연결 됐으면
            return;
        }
        if(ladcnt==0){
            check = isCheck(lad);
            return;
        }
        for(int i=1;i<=H;i++){
            for(int j=1;j<N;j++){
                if(lad[i][j] ==0 && lad[i][j+1]==0){
                    lad[i][j] = j+1;
                    lad[i][j+1] = j;

                    combination(lad, ladcnt-1);
                    if(check) return;
                    lad[i][j] = 0;
                    lad[i][j+1] =0;
                }
            }
        }
    }
}
