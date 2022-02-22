package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class bj1561 {
    static int N, M;
    static long result;
    //static int[] arr;
    static Play[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //애들 수
        M = Integer.parseInt(st.nextToken()); //놀이공원 번호
        st = new StringTokenizer(br.readLine());
        arr = new Play[M];
        for(int i=0;i<M;i++){
            arr[i] = new Play(i+1,Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr, new Comparator<Play>(){
            @Override
            public int compare(Play o1, Play o2) {
                return o1.time-o2.time;
            }
        });
        result = Bsearch(N, arr);

        System.out.println(result);
    }

    public static long Bsearch(int n, Play[] plays){
        long answer = 0;


        long min = 1;
        long max = (long) plays[(plays.length-1)].time*n;
        long mid = 0;
        long sum = 0;
        answer = max;

        while(min <= max){
            sum =0;
            mid = (min + max)/2;
            for(int t =0;t<M;t++){
                sum += (long) (mid/plays[t].time);

            }
            if(sum>=n){
                if(mid<answer) answer = mid;
                max = mid-1;
            }else{
                min = mid+1;
            }
        }
        return answer;
    }

    public static class Play{
        int num, time;
        Play(int num, int time){
            this.num = num;
            this.time = time;
        }
    }
}
