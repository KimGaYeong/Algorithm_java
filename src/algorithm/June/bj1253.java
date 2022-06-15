package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj1253 {
    static int N, result;
    static int[] list;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new int[N];


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);
        result = 0;
        //이분 탐색!
        for(int i=0;i<N;i++){
            solution(list[i], 0, N-1, i);
        }

        System.out.println(result);
    }

    public static void solution(int num, int left, int right, int idx){

        while(left < right){

            if(left ==idx){
                left++;
                continue;
            }else if(right==idx){
                right--;
                continue;
            }

            if(list[left]+list[right] == num){
                result++;
                break;
            }

            if(list[left]+list[right] < num){
                left++;
            }else{
                right--;
            }
            //System.out.println("idx : " + idx + " target : " + num + " :: (" + left + " " + right + ")");
        }
    }
}

/*
3
0 0 0
답 3


 */