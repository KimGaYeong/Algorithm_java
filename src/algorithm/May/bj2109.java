package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
d일 '안'에 와서 강연을 해야 p만큼의 강연료를 준다.
가장 많은 돈을 벌 수 있도록 순회 공연을 해야 한다.
하루에 한 곳에서만 강연을 할 수 있다.
 */
public class bj2109 {
    static int n;
    static PriorityQueue<Lecture> pq;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        int maxc =0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            pq.add(new Lecture(p,d));
            maxc = Math.max(maxc, d);
        }

        boolean[] check = new boolean[maxc+1];

        int sum =0;
        while(!pq.isEmpty()) {
            Lecture lec = pq.poll();
            for(int i = lec.day; i >= 0; i--) {
                if(!check[i]) {
                    check[i] = true;
                    sum += lec.pay;
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    public static class Lecture implements Comparable<Lecture>{
        int pay;
        int day;

        public Lecture(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }

        @Override
        public int compareTo(Lecture o) {
            if(this.pay == o.pay){
                return Integer.compare(this.day,o.day);
            }else{
                return -1 *Integer.compare(this.pay,o.pay);
            }
        }
    }
}
