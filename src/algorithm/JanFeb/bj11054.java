package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj11054 {
    static int N;
    static int[] arr;
    static int[] DP, DP2, DP3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        DP = new int[N];
        DP3 = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<N;i++){
            int num = arr[i];
            DP[i] = 0;
            for(int j=0;j<i;j++){
                if(arr[j] < arr[i] && arr[j] < num && DP[i] <= DP[j]){
                    DP[i] = DP[j]+1;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(DP));
        for(int i=0;i<N;i++){
            DP[i] += low(i);
            DP3[i] = low(i);
        }
        System.out.println(Arrays.toString(DP3));

        int result =0;
        for(int i=0;i<N;i++){
            result = Math.max(DP[i], result);
        }
        System.out.println(result);
    }

    static int low(int num){
        DP2 = new int[N];
        int numb = arr[num];
        for(int i=num;i<N;i++){
            if(arr[i] > numb) continue;
            for(int j=i+1;j<N-1;j++){
                if(arr[j] < arr[j+1]){
                    DP2[i] = Math.max(DP2[j], DP2[i]+1);
                }
            }
        }
        return DP2[N-1];
    }
}
