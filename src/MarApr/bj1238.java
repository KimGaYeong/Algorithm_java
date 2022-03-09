package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1238 {
    static int N, M, X;
    static List<List<Info>> map, remap;
    static boolean[] visit;
    static int[] distance;
    static int[] me_to_there, there_to_me;
    static PriorityQueue<Info> pq;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1238.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        remap = new ArrayList<>();

        for(int i=0;i<N+1;i++){
            map.add(new ArrayList<>());
            remap.add(new ArrayList<>());

        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map.get(to).add(new Info(from, w));
            remap.get(from).add(new Info(to, w));
        }

        me_to_there = dijkstra(map);
        there_to_me = dijkstra(remap);


        int answer = 0;
        for(int i=1;i<=N;i++){
            answer = Math.max(answer, me_to_there[i]+there_to_me[i]);
        }

        System.out.println(answer);
    }

    public static int[] dijkstra(List<List<Info>> maps){
        visit = new boolean[N+1];
        distance = new int[N+1];
        pq = new PriorityQueue<>();

        for(int i=1;i<=N;i++){
            distance[i] = 1000001;
        }
        pq.add(new Info(X, 0));
        distance[X] =0;

        while (!pq.isEmpty()){
            int cx = pq.peek().V;
            pq.poll();

            if(visit[cx]) continue;
            visit[cx] = true;

            for(int i=0;i<maps.get(cx).size();i++){
                int nx = maps.get(cx).get(i).V;
                int nweight = maps.get(cx).get(i).weight;

                if(distance[nx] > distance[cx]+nweight){
                    distance[nx] = distance[cx]+nweight;
                    pq.add(new Info(nx, distance[nx]));
                }
            }
        }
        return distance;
    }
    public static class Info implements Comparable<Info>{
        int V, weight;

        public Info(int v, int weight) {
            V = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Info o) {
            return this.weight-o.weight;
        }

    }
}
