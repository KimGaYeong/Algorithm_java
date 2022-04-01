package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1202 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj1202.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //가방, 보석을 우선 정렬함
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Jew> jews = new ArrayList<>();
        Bag[] bags = new Bag[K];

        //보석 입력
        for(int n=0;n<N;n++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            jews.add(new Jew(m, u));
        }
        //보석을 가격 우선, 무게 나중순으로 정렬
        Collections.sort(jews);
        //System.out.println(Arrays.toString(jews));

        //가방 입력
        for(int k=0;k<K;k++){
            int w = Integer.parseInt(br.readLine());
            bags[k] = new Bag(w);
        }
        Arrays.sort(bags);
        //System.out.println(Arrays.toString(bags));
        long answer=0;
        int idx=0;

        //보석 빼는 기준은 가격 기준.
        PriorityQueue<Jew> pq =new PriorityQueue<>((o1, o2) -> Integer.compare(o2.price,o1.price));

        for(int i=0;i<K;i++){
            //System.out.println("* bag[" + i + "]'s limit is " + bags[i].limit);
            while (idx<N){
                //System.out.println("----> jews[" + idx + "].weight is " +jews.get(idx).weight);
                if(jews.get(idx).weight<=bags[i].limit){
                    pq.offer(jews.get(idx++));
                }else{
                    break;
                }
            }

            //System.out.println(pq);
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
            return this.limit-o.limit;
        }

        @Override
        public String toString() {
            return "Bag{" +
                    "limit=" + limit +
                    '}';
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

            return this.weight-o.weight;

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
