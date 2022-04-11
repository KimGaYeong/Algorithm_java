package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class swea_8458 {
    /**
     * 점들을 움직여 원점으로 이동. // 한 번 움직일 때마다 모든 점이 움직인다.
     * i번째 움직임에서 각 점은 상or하or좌or우로 i만큼 이동해야 함.
     * //중복 조합으로 4만큼 뽑아서 그만큼 i번째 점을 이동시킴.
     * //Not 중복조합! DFS!
     */
    static int N, ans;
    static Info[] infos;
    static int[] src = {0,1,2,3};
    static int[][] del = {{-1,0},{0,-1},{1,0},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = swea_5656.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        //입력
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            infos = new Info[N];

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                infos[i] = (new Info(x,y));
            }
            System.out.println(Arrays.toString(infos));
            //0,1,2,3중 중복을 포함하여 N개만큼 뽑는다.
            //N개만큼 다 뽑은 경우 0,0에 오면 break 아님 -1
            ans = 0;

            if(!allzero()){
                ans = Integer.MAX_VALUE;
                System.out.println("notAllzero");
                permDup(N, new int[N]);
            }else{
                System.out.println("Allzero");
            }

            sb.append("# " + t + " " + ans +"\n");
        }
        System.out.println(sb);
    }

    public static boolean allzero(){
        int cnt=0;
        for(int i=0;i<N;i++){
            if(infos[i].x ==0 && infos[i].y==0) cnt++;
        }
        if(cnt==N) return true;
        else return false;
    }

    public static void permDup(int toChoose, int[] choosed) {
        //base part
        if(toChoose == 0) {
            //System.out.println(Arrays.toString(choosed));
            play(choosed);
            return;
        }

        //inductive part
        for (int i = 0; i < 4; i++) {
            choosed[choosed.length-toChoose] = src[i];
            permDup(toChoose-1, choosed);
        }
    }

    public static void play(int[] choosed){
        for (int j=0;j<N;j++){
            int d = choosed[j];

            int cnt=0;
            for(int i=0;i<N;i++){
                Info info = infos[i];

                info.x += del[d][0] * (j+1);
                info.y += del[d][1] * (j+1);

                if(info.x ==0 && info.y ==0)cnt++;
            }

            if(cnt==N){
                ans = Math.min(ans, cnt);
                return;
            }
        }
    }

    public static class Info{
        int x,y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
