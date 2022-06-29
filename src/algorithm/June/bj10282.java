package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj10282 {
    static int n, d, c;
    static List<List<Com>> list;
    static int INF = Integer.MAX_VALUE;
    static boolean[] visit;
    static int [] distance;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken())-1;

            list = new ArrayList<>();
            for(int i=0;i<n;i++){
                list.add(new ArrayList<>());
            }

            visit = new boolean[n];
            distance = new int[n];
            for(int i=0;i<d;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;
                int s = Integer.parseInt(st.nextToken());

                list.get(b).add(new Com(a,s));
            }

            Arrays.fill(distance, INF);

            distance[c] = 0;
            PriorityQueue<Com> pq = new PriorityQueue<>();
            pq.add(new Com(c,0));

            while(!pq.isEmpty()){
                //System.out.println(pq);
                Com com = pq.poll();
                if(visit[com.num]) continue;
                visit[com.num] = true;
                for(Com next : list.get(com.num)){
                    if(distance[next.num]>distance[com.num]+next.time){
                        distance[next.num] = distance[com.num]+next.time;
                        pq.add(new Com(next.num, distance[next.num]));
                    }
                }
            }

            //System.out.println(Arrays.toString(distance));

            int cnt=0, max=0;
            for(int d : distance){
                if(d!=INF){
                    cnt++;
                    max = Math.max(max, d);
                }
            }
            System.out.println(cnt + " " + max);
        }
    }

    public static class Com implements Comparable<Com>{
        int num, time;

        public Com(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Com o) {
            return this.time-o.time;
        }

        @Override
        public String toString() {
            return "Com{" +
                    "num=" + num +
                    ", time=" + time +
                    '}';
        }
    }
}
