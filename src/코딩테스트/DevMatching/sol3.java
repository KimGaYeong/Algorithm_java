package 코딩테스트.DevMatching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class sol3 {

    static int V;
    static ArrayList<ArrayList<Node>> map;
    static int[] distance;
    static boolean[] check;
    static PriorityQueue<Node> pq;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[][] edges = {{0,1},{1,2},{2,3},{4,0},{5,1},{6,1},{7,2},{7,3},{4,5},{5,6},{6,7}};
        int n=8;
        int k=4;
        int a=0;
        int b=3;

        System.out.println(solution(n,edges,k,a,b));
    }

    public static int solution(int n, int[][] edges, int k, int a, int b) {
        int answer = n;
        map = new ArrayList<>();

        V = n;
        for(int i=0;i<V;i++) {
            map.add(new ArrayList<>());
        }
        distance = new int[V];
        check = new boolean[V];

        for(int i=0;i<edges.length;i++){
            int from = edges[i][0];
            int to = edges[i][0];
            map.get(from).add(new Node(to, 1));
        }

        pq = new PriorityQueue<>();
        Dijkstra(a);

        for(int i=0;i<V;i++){
            if(distance[i]<k){
                answer-=1;
            }
        }
        //System.out.println(Arrays.toString(distance));
        return answer;

    }
    public static class Node implements Comparable<Node>{
        int idx;
        int weight;

        Node(int idx, int weight){
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }


    }
    public static void Dijkstra(int start) {

        // 1. 거리 초기화
        for(int i=0;i<V;i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // 2. 시작 노드 삽입
        pq.offer(new Node(start, 0));
        distance[start] = 0;
        check[start] = true;
        // 4. 꺼내기

        while(!pq.isEmpty()) {
            int minidx = 0;
            int minwei = Integer.MAX_VALUE;

            minidx = pq.peek().idx;
            minwei = pq.peek().weight;
            pq.poll();

            if(minwei > distance[minidx]) continue;

            for(int i=0;i<map.get(minidx).size();i++) {
                int nextidx = map.get(minidx).get(i).idx;
                int nextwei = map.get(minidx).get(i).weight;

                if(distance[nextidx] >= distance[minidx] + nextwei) {
                    distance[nextidx] = distance[minidx] + nextwei;
                    pq.add(new Node(nextidx, distance[nextidx]));

                }
            }
        }

    }
}
