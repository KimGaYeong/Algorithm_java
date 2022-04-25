package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
임의의 두 점은 꼭 방문해야 하며 항상 최단거리로 이동해야 한다.
방향이 없는 그래프!
 */
public class bj1504 {
    static int N, E;
    static int v1, v2;
    static ArrayList<ArrayList<Info>> map;
    static PriorityQueue<Info> pq;
    public static void main(String[] args) throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        for(int i=0;i<=N;i++){
            map.add(new ArrayList<>());
        }


        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            map.get(from).add(new Info(to, weight));
            map.get(to).add(new Info(from, weight));
        }


        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());


        //1번 정점에서 N번 정점으로 가는 것이고 ~~ v1, v2를 지난다.
        // 1 -> v1 -> v2 -> N
        // 1 -> v2 -> v1 -> N 둘 중에 고르면 되나>

        //저번에 두번만 다익스트라를 돌리면 된다고 했었던거 같은데 생각을 잘 해보자.

        //dijkstra : v1에서 한번. v1->1, v1->N, v1->v2
        //dijkstra : v2에서 한번. v2->1, v2->N

        int[] distanceV1 = dijkstra(v1);
        int[] distanceV2 = dijkstra(v2);

        //꼭 지나야 하는 두 점 사이에 간선이 없으면 -1을 출력한다.
        if(distanceV1[v2]>=987654321){
            System.out.println(-1);
            return;
        }
//
//        System.out.println(Arrays.toString(distanceV1));
//        System.out.println(Arrays.toString(distanceV2));

        int first = distanceV1[1] + distanceV2[v1] + distanceV2[N];
        int second = distanceV2[1] + distanceV2[v1] + distanceV1[N];

        int result = Math.min(first, second);

        if(result>=987654321){
            System.out.println("-1");
        }else{
            System.out.println(result);
        }

    }

    public static int[] dijkstra(int idx){
        pq = new PriorityQueue<>();

        boolean[] check = new boolean[N+1];
        int[] dist = new int[N+1];
        //1.6 * 10^6 160000
        Arrays.fill(dist, 987654321);

        pq.add(new Info(idx, 0));
        dist[idx] = 0;
        check[idx] = true;

        while(!pq.isEmpty()){
            int curidx = pq.peek().idx;
            int curwei = pq.peek().weight;
            pq.poll();

            if(curwei > dist[curidx]) continue;

            for(int i=0;i<map.get(curidx).size();i++){
                int nextidx = map.get(curidx).get(i).idx;
                int nextwei = map.get(curidx).get(i).weight;

                if(dist[nextidx] > dist[curidx] + nextwei){
                    dist[nextidx] = dist[curidx] + nextwei;
                    pq.add(new Info(nextidx, dist[nextidx]));
                }
            }
        }
        return dist;
    }

    public static class Info implements Comparable<Info>{
        int idx;
        int weight;

        public Info(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "Info{" +
                    "idx=" + idx +
                    ", weight=" + weight +
                    '}';
        }
    }
}
