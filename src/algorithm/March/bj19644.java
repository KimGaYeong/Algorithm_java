package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj19644 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj19644.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //입력
        int N = Integer.parseInt(br.readLine());
        int[] M = new int[2];
        st = new StringTokenizer(br.readLine());
        M[0] = Integer.parseInt(st.nextToken());
        M[1] = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(br.readLine());
        List<zombi> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            list.add(new zombi(Integer.parseInt(br.readLine()),0));
        }
        for(int i=0;i<N;i++){
            for(int j=i;j<i+M[0];j++){
                list.get(j).gun+=M[1];
                if(j==N-1) break;
            }
        }
        int i=0;
        while(N-->=0){
            if(list.size()==0){
                System.out.println("YES");
                break;
            }
            if(list.get(i).power <= list.get(i).gun){
                list.remove(i);
            }else{
                if(C>0) {
                    C--;
                    for (int j = i; j < i + M[0]; j++) {
                        list.get(j).gun -= M[1];
                        if (j == list.size() - 1) break;
                    }
                    list.remove(i);
                }else{
                    System.out.println("NO");
                    break;
                }
            }
        }


    }

    public static class zombi{
        int power;
        int gun;

        public zombi(int power, int gun) {
            this.power = power;
            this.gun = gun;
        }

        @Override
        public String toString() {
            return "zombi{" +
                    "power=" + power +
                    ", gun=" + gun +
                    '}';
        }
    }
}
