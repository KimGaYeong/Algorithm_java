package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//수열을 자르기.
public class bj2616 {
    static int cnt, max;
    static int[][] DP;
    static int[] prefix;
    static Subway[] subway;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 선언
        cnt = Integer.parseInt(br.readLine());
        DP = new int[max+1][cnt];
        prefix = new int[cnt];
        subway = new Subway[cnt];

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i=0;i<cnt;i++){
            subway[i] = new Subway(i+1, Integer.parseInt(st.nextToken()));
            prefix[i] = sum + subway[i].cus;
            sum += subway[i].cus;
        }
        for(int p : prefix){
            System.out.print(p + " ");
        }
        max = Integer.parseInt(br.readLine());

    }

    public static class Subway{
        int idx, cus ;//좌표를 저장
        Subway(int idx, int cus){
            this.idx = idx;
            this.cus = cus;
        }
    }
}

/*
소형 기관차는 무조건 3대!
무조건 주어진 객차 개수를 맞춰야 하는건 아님.
 */
