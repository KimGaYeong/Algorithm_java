package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1197 {
    static int V, E;
    static int result;
    static List<Node>[] arr;
    static PriorityQueue<Node> pq;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        arr = new ArrayList[V+1];
        visit = new boolean[V+1];

        //입력
        for(int i=1;i<=V;i++){
            arr[i] = new ArrayList<>();
        }
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[s].add(new Node(e,w));
            arr[e].add(new Node(s,w));
        }
        result = 0;
        //프림 알고리즘.
        System.out.println(Prim(1));
    }

    public static class Node implements Comparable<Node>{
        int num;
        int weight;

        Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
        //오름차순으로 정렬. 그래야 작은거부터 뽑을 수 있음.
    }

    public static int Prim(int x){
        pq = new PriorityQueue<>();
        //프림 알고리즘 시작. 어느 노드에서 시작하는지는 상관 없음.
        pq.add(new Node(x,0));

        while(!pq.isEmpty()){
            int idx = pq.peek().num;
            int wei = pq.peek().weight;
            System.out.println("int, wei" + idx + " " + wei);
            pq.poll(); //어짜피 제일 가중치가 작은 애가 뽑힌다.
            //방문 안한곳이 있다면 방문 처리 해주기. 그리고 가중치 결과값에 더해주기.
            if(!visit[idx]){
                visit[idx] = true;
                result += wei;
                for(Node node : arr[idx]){ //현재 idx랑 연결된 애들 중
                    //아직 방문 안한 노드로 ㄱㄱ
                    if(!visit[node.num]) pq.add(node);
                }
            }
        }
        return result;
    }

}
