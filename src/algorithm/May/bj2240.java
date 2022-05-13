package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//매 초마다, 두 개의 나무 중 하나의 나무에서 열매가 떨어지게 된다.
public class bj2240 {
    static int T, W, result;
    static int[] time;
    static int[][] DP;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        time = new int[T];
        DP = new int[W+1][T]; // 자리를 0~W번 바꿨을때?

        for(int i=0;i<T;i++){ //한번도 안바꿨으면(w==0일때)
            // time[i]가 1일때만 자두를 먹을 수 있음.
            time[i] = Integer.parseInt(br.readLine());

            if(i>=1){
                if(time[i] ==1){
                    DP[0][i] = DP[0][i-1]+1;
                }else{
                    DP[0][i] = DP[0][i-1];
                }
            }else{
                if(time[i] ==1){
                    DP[0][0] = 1;
                }
            }
        }
        // 맨 처음에는 1번 자두나무 밑에 있음
        // 안움직이면 + 짝수번 움직였을 때는 1번
        // 홀수 번 움직였을 때는 2번에 있음

        result =0;

        //0번 시간에서 자두가 1에 위치해있으면 DP[0번바꾸고][time=0] =1;

        //한번도 안바꿨을 때는..

        for(int w=1;w<=W;w++){
            //t==0일때
            if(w%2 ==0){ //짝수 번 움직였다면? -> 원래 1번이니까 짝수번 움직이면 다시 1번으로
                //짝수 번 움직였는데 현재 자두가 떨어지는 곳이 1번인 경우 : +1인데...
                if(time[0]==1){
                    DP[w][0] = Math.max(DP[w-1][0], DP[w][0])+1;
                }else{
                    DP[w][0] = DP[w-1][0];
                }

            }else{
                if(time[0]==2) {
                    DP[w][0] = Math.max(DP[w-1][0], DP[w][0])+1;
                }else{
                    DP[w][0] = DP[w-1][0];
                }
            }

            for(int t=1;t<T;t++){

                //일단 다 DP[w][t] = DP[w-1][t-1], DP[w][t-1]중에 큰걸 하나 고른다음... ++1;
                if(w%2 ==0){ //짝수번 : 1번나무, 홀수번 : 2번나무
                    if(time[t] ==1){ //짝수번 바꿨는데 1번이다? 난 지금 자두를 먹을 수 있다.일단+1
                        // 안바꾼 옛날꺼 그대로 가거나 바꾸고 하나더하거나..
                        DP[w][t] = Math.max(DP[w-1][t], DP[w][t-1]+1);
                    }else{ //반대쪽이면 먹을 수 있으니까 걔가 더해지거나.... 그냥 가거나...
                        DP[w][t] = Math.max(DP[w-1][t-1]+1, DP[w][t-1]);
                    }
                }else{
                    if(time[t] ==2){
                        DP[w][t] = Math.max(DP[w-1][t], DP[w][t-1]+1);
                    }else{
                        DP[w][t] = Math.max(DP[w-1][t-1]+1, DP[w][t-1]);
                    }
                }
            }
            result = Math.max(result, DP[w][T-1]);
        }

        for(int i=0;i<=W;i++){
            System.out.println(Arrays.toString(DP[i]));
        }

        System.out.println(result);

    }
}
