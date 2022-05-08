package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj1806 {
    static int N, S, answer;
    static int[] list;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken()); //15 이상이 되어야 함. 15가 되면 최고!
        list = new int[N];


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        answer =100001;
        solve();
        if(answer>N){
            System.out.println("0");
        }else{
            System.out.println(answer);
        }
    }

    public static void solve(){
        int op = 0;
        int tp = 0;
        int sum = 0;

        while(true){
            if(S <= sum){ // 조건 만족! answer 갱신.
                System.out.println("1 & " + tp + " " + op + " .. and : " + sum);
                answer = Math.min(answer, tp-op);
                sum -= list[op];
                op++;
            }else if(tp==N){ //탐색 끝
                //System.out.println("2 & " + tp + " " + op + " .. and : " + sum);
                break;
            }else{
                //System.out.println("3 & " + tp + " " + opv + " .. and : " + sum);
                sum += list[tp];
                tp++;
            }

        }
    }
}
