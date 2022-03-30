package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1202 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj1202.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //가방, 보석을 정렬한 뒤 DP를 사용한다면?
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Jew[] jews = new Jew[N];
        Bag[] bags = new Bag[K];

        for(int n=0;n<N;n++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            jews[n] = new Jew(m, u);
        }
        Arrays.sort(jews);
        //System.out.println(Arrays.toString(jews));
        for(int k=0;k<K;k++){
            int w = Integer.parseInt(br.readLine());
            bags[k] = new Bag(w);
        }
        Arrays.sort(bags);

        long answer=0;
        int idx=0;
        PriorityQueue<Jew> pq =new PriorityQueue<>((o1, o2) -> Integer.compare(o2.price,o1.price));

        for(int i=0;i<K;i++){
            //System.out.println("bag[" + i + "]'s limit is " + bags[i].limit);
            while (idx<N){
                //System.out.println("----> jews[" + idx + "].weight is " + jews[idx].weight);
                if(jews[idx].weight<=bags[i].limit){
                    pq.add(jews[idx]);
                }
                idx++;

            }
            if(!pq.isEmpty()){
                answer += pq.poll().price;
            }
        }

        System.out.println(answer);
    }

    public static class Bag implements Comparable<Bag>{
        int limit;

        public Bag(int limit) {
            this.limit = limit;
        }

        @Override
        public int compareTo(Bag o) {
            return o.limit;
        }
    }

    public static class Jew implements Comparable<Jew>{
        int weight, price;

        public Jew(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jew o) {

            if(this.price==o.price)return o.weight-this.weight;
            else return o.price-this.price;

        }

        @Override
        public String toString() {
            return "Jew{" +
                    "weight=" + weight +
                    ", price=" + price +
                    '}';
        }
    }
}
