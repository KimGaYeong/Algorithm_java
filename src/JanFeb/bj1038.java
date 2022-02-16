package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class bj1038 {
    static int N;
    static ArrayList<Long> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //모든 가능한 경우의 수를 다 보면 X. 특정 조건만 만족할 것.  N은 1,000,000까지.
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        if(N<=10) {
            System.out.println(N);
        }else if(N>1022){
            System.out.println(-1);
        }else{
            for(int i=0;i<10;i++){
                Cal(i,1);
            }
            Collections.sort(list);
            System.out.println(list.get(N));
        }


    }
    public static void Cal(long num, int depth){
        if(depth>10) return;
        list.add(num);

        for(int i=0;i<num%10;i++){
            if(num%10 >i) Cal((num*10)+i, depth+1);
        }
    }
}
