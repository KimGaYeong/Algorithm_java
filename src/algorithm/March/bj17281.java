package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1~9까지의 정수를 채워 넣으면 된다.
 *
 *
 */
import java.util.StringTokenizer;

public class bj17281 {
    static int ans;
    static int N;
    static int count = 0;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] player =new int[N][9];
        for(int n = 0; n<N;n++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                player[n][i]= Integer.parseInt(st.nextToken());
            }
        }
        // 플레이어의 순서를 정하는 순열
        int [] order = new int[9];
        order[3] = 0;
        // order 만들어올 순열
        boolean[] visitied =new boolean[9];
        visitied[0] =true;
        check(0,visitied, order, player);
        System.out.println(ans);
    }

    private static void check(int s,boolean[] visitied, int[] order, int[][] player) {
        if(s==9) {
            // 이닝별
            int cnt = 0 ;
            int t =-1 ; //이게 타자 순서..
            for(int n = 0; n<N;n++) { // 이닝
                int out = 0;
                int l1=0,l2=0,l3=0;
                while(out<3) {
                    t++;
                    if(t==9)t=0;
                    switch(player[n][order[t]]) {
                        case 0:
                            out++;
                            break;
                        case 4:
                            cnt+=l1+l2+l3+1;
                            l1=0;l2=0;l3=0;
                            break;
                        case 3:
                            cnt+=l3+l2+l1;
                            l3=1;l2=0;l1=0;
                            break;
                        case 2:
                            cnt+=l3+l2;
                            l3=l1;l2=1;l1=0;
                            break;
                        case 1:
                            cnt+=l3;
                            l3=l2;l2=l1;l1=1;
                            break;
                    }
                }
            }
            ans= cnt>ans?cnt:ans;
            return;
        }
        for(int i = 1; i<9;i++) {
            if(!visitied[i]) {
                order[s] = i;
                visitied[i] = true;
                if(s==2)check(4, visitied, order, player);
                else check(s+1, visitied, order, player);
                visitied[i] = false;
            }
        }
    }
}
/*
out * 8 + homerun * 1
2
4 0 0 0 0 0 0 0 0
4 0 0 0 0 0 0 0 0
 */
