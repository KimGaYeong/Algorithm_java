package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj20057 {
    static int N, result;
    static int s_rc;
    static int[][] map;
    static int[][] del2 = {{1,0},{0,1},{-1,0},{0,-1}}; //아래 오른쪽 위 왼쪽..
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        s_rc = (N/2);

        map = new int[N][N];
        int ori = 0;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                ori += map[i][j];
            }
        }
        result = 0;
        MakeLineMapIdx();

        result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                result += map[i][j];
            }
        }
        System.out.println(ori-result);
    }

    private static void MakeLineMapIdx(){
        //토네이도 이동하기.
        int r = s_rc;
        int c = s_rc;
        //System.out.println(r + " " + c);
        // 다음칸은 r, c-1칸.
        moveSend(r,c,r,c-1);

        int idx = 1;
        int cr = 0;
        int cn = 0;
        for (int k = 1; k <= N / 2; k++) {
            r -= 1; c -= 1;
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < k * 2; i++) {
                    r += del2[d][0];
                    c += del2[d][1];

                    //System.out.println(cr + " " + cn + " " + r + " " + c);
                    if(idx!=1){
                        moveSend(cr,cn,r,c);
                    }
                    idx++;

                    cr = r;
                    cn = c;
                }
            }

        }

    }

    public static String str(int d){
        //아래 오른쪽 위 왼쪽..
        if(d==0) return "아래";
        else if(d==1) return "오른쪽";
        else if(d==2) return "위";
        else return "왼쪽";
    }

    //아래 오른쪽 위 왼쪽..
    static int[][][] del1 = {
            {{0,-1},{0,-2},{0,1},{0,2},{1,-1},{1,0},{1,1},{-1,-1},{-1,1},{2,0}}, //o
            {{-1,0},{-2,0},{1,0},{2,0},{-1,1},{0,1},{1,1},{-1,-1},{1,-1},{0,2}}, //o
            {{0,-1},{0,-2},{0,1},{0,2},{-1,-1},{-1,0},{-1,1},{1,-1},{1,1},{-2,0}},
            {{-1,0},{-2,0},{1,0},{2,0},{-1,-1},{0,-1},{1,-1},{-1,1},{1,1},{0,-2}} //o
    };
    static double[] perc = {
            7,2,7,2,10,0,10,1,1,5
    };

    public static void moveSend(int cr, int cc, int nr, int nc){
        int vr = nr-cr;
        int vc = nc-cc;
        int d = 0;
        int leftsend = 0; //map 밖으로 나가는 모래의 양
        int send = 0; //map 안에 쌓이는 모래의 양


        for(int i=0;i<4;i++){
            if(del2[i][0] == vr && del2[i][1] ==vc){
                d = i;
            }
        }
//        System.out.println(nr + " " + nc + " " + str(d));

        //모래 흩날리기..
        /*
        0  0 2 0 0
        0 10 7 1 0
        5 a  y x 0      x->y로 토네이도가 이동헀을 경우
        0 10 7 1 0
        0  0 2 0 0
        */

        int num = map[nr][nc];
        //System.out.println(del2[d][0] + " " + del2[d][1]);
        map[nr][nc] = 0;
        for(int i=0;i<10;i++){
            if(i==5) continue;
            int nx = nr + del1[d][i][0];
            int ny = nc + del1[d][i][1];
            //System.out.println(nx + " " + ny);
            if(isIn(nx,ny)){// map에 있으면 쌓이는 것
//                System.out.println(num + " " + (double)perc[i]/100);
                send += Math.floor((double)num*perc[i]/100);
                map[nx][ny] += Math.floor((double)num*perc[i]/100);
            }else{// map에 없으면 나가는것
                leftsend+= Math.floor((double)num*perc[i]/100);
            }
        }

        int nx = nr + del1[d][5][0];
        int ny = nc + del1[d][5][1];
        if(isIn(nx,ny)){
            //5에는 전체 모래에서 어찌저찌 다 날라간 모래를 넣는다.
            map[nx][ny] += num - (send+leftsend);
        }else{ //5가 칸에 없을 경우는 leftsend에 추가
            leftsend += (num-send);
        }

//        System.out.println("--");
//        for(int i=0;i<N;i++){
//            System.out.println(Arrays.toString(map[i]));
//        }
//        System.out.println("--");
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }
}


/*

25 24 23 22 21
10 9 8 7 20
11 2 1 6 19
12 3 4 5 18
13 14 15 16 17
 */