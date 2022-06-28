package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj2015 {
    static int N, K;
    static int[] list, sum;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new int[N+1]; //원래 주어지는 정수들
        sum = new int[N+1]; //누적합

        Map<Integer, Long> hmap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            list[i] = Integer.parseInt(st.nextToken());
            sum[i] = list[i] + sum[i-1];
        }

        //합이 K인 부분합 개수 출력
        long result = 0L;

        for(int i=1;i<=N;i++){
            if(sum[i] == K){ //누적합이 K랑 같으면? 개수늘려주기
                result++;
            }
            //sum[i] == A라고 가정했을 때
            // A-K라는 누적합이 존재한다면?
            // sum[i] == A이므로 A라는 누적합도 존재하는 상태이다.
            // A - (A-K) = K이므로 가능한 개수이다.
            if(hmap.containsKey(sum[i]-K)){
                result += hmap.get(sum[i]-K);
            }

            //sum[i] == A라고 가정했을 때
            // A라는 누적합이 아직 저장이 안되어있으면? 저장시켜줌.
            if(!hmap.containsKey(sum[i])){
                hmap.put(sum[i], 1L);
            }else{ //이미 저장 되어있으면 1 더해줌.
                hmap.put(sum[i], hmap.get(sum[i])+1);
            }
        }

        System.out.println(result);
    }
}
