package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2110 {
    static int N, C;
    static int[] house;
    static long high, mid, low;
    public static void main(String[] args) throws IOException {

        InputStream input = bj2110.class.getResourceAsStream("input.txt");
        System.setIn(input);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];
        for(int i=0;i<N;i++){
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        low = 1;
        high = house[N-1]-house[0];
        long tmp = 0;

        while(high >= low){
            mid = (low+high) /2;

            long start = house[0];
            long cnt = 1;

            for(int i=1;i<N;i++){
                tmp = house[i] - start;
                if(mid <= tmp){
                    cnt++;
                    start = house[i];
                }
            }

            if(cnt >=C){
                low = mid +1;
            }else {
                high = mid -1;
            }
        }

        System.out.println(high);
    }
}
