package algorithm.June;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class pg합승택시요금 {

    static ArrayList<ArrayList<Info>> map;
    static PriorityQueue<Info> pq;
    static int N, S, A, B;
    public static void main(String[] args) {

        int n=7; //지점 개수
        int s=3; //출발 지점
        int a=4; //A의 도착 지점
        int b=1; //B의 도착 지점
        //지점 사이 예상 택시 요금 //[a,b,c] 지점 a,b사이 요금 c
        int[][] fares = new int[][]{{5, 7, 9},{4, 6, 4},{3, 6, 1},{3, 2, 3},{2, 1, 6}};
        //즉 4->6->2가 빠른지, 4->2->6이 빠른지 보면 되는 것 아닌가?!

        System.out.println(solution(n,s,a,b,fares));
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        N = n; S = s; A = a; B = b;

        int l = fares.length;
        map = new ArrayList<>();
        for(int i=0;i<=n;i++){
            map.add(new ArrayList<>());
        }

        for(int i=0;i<l;i++){
            //System.out.println(Arrays.toString(fares[i]));
            int from = fares[i][0];
            int to = fares[i][1];
            int weight = fares[i][2];
            map.get(from).add(new Info(to, weight));
            map.get(to).add(new Info(from, weight));
        }

        //시작과 끝이 정해져있는 다익스트라?
        //즉 s->a->b가 빠른지, s->b->a이 빠른지 보면 되는 것
        //6(A도착)에서 한 번, 2(B도착)에서 한 번 돌린다.

        int disResult = 20000000;
        for(int i=1;i<=n;i++){
            if(i==s || i==a || i==b) continue;
            int[] dis = dijkstra(i);
            disResult = Math.min(dis[s] + dis[a] + dis[b], disResult);
        }

        int[] disS = dijkstra(s);
        int[] disB = dijkstra(b);
//        System.out.println("disA : " + Arrays.toString(disS));
//        System.out.println("disB : " + Arrays.toString(disB));
        int first = disS[a] + disB[a];
        int second = disS[b] + disB[a];
        int third = disS[a] + disS[b];

        return Math.min(disResult, Math.min(third, Math.min(first, second)));
    }

    public static int[] dijkstra(int idx){

        pq = new PriorityQueue<>();

        boolean[] check = new boolean[N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist, 20000000);

        pq.add(new Info(idx,0));
        dist[idx] = 0;
        check[idx] = true;

        while(!pq.isEmpty()){
            int cidx = pq.peek().idx;
            int cweight = pq.peek().weight;
            pq.poll();

            if(cweight > dist[cidx]) continue;

            for(int i=0;i<map.get(cidx).size();i++){
                int nidx = map.get(cidx).get(i).idx;
                int nweight = map.get(cidx).get(i).weight;

                if(dist[nidx] > dist[cidx] + nweight){
                    dist[nidx] = dist[cidx] + nweight;
                    pq.add(new Info(nidx, dist[nidx]));
                }
            }
        }
        return dist;
    }

    public static class Info implements Comparable<Info>{
        int idx, weight;

        public Info(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
