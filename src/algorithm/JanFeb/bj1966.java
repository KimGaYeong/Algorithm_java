package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1966 {
    static int T, N, M;
    static int cnt;
    static List<Num> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                int tmp = Integer.parseInt(st.nextToken());
                list.add(new Num(i, tmp));
            }
            cnt = 0;
            int i=0;
            boolean check = false;
            while(true){
                check = false;
                int size = list.size();
                Num s = list.get(0);
                for(int j=i+1;j<size;j++){
                    if(s.num < list.get(j).num){
                        list.remove(0);
                        list.add(s);
                        check = true;
                        break;
                    }
                }
                if(!check){
                    list.remove(0);
                    cnt++;
                    if(s.idx == M){
                        System.out.println(cnt);
                        break;
                    }
                }
            }
        }
    }

    public static class Num implements Comparable<Num>{
        int idx, num;
        Num(int idx, int num){
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Num o) {
            return o.num-this.num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Num num1 = (Num) o;
            return idx == num1.idx && num == num1.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(idx, num);
        }
    }
}
