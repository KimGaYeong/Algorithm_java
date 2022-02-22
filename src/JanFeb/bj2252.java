package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj2252 {
    static int N, M;
    static int[] inArr;
    static PriorityQueue<Integer> queue;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inArr = new int[N+1];
        graph = new ArrayList<>();
        //노드 정보 초기화
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        //노드 정보 입력
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            inArr[to]++;
        }

        queue = new PriorityQueue<>();
        for(int i=1;i<=N;i++){
            if(inArr[i]==0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int first = queue.poll(); //노드 꺼냄
            sb.append(first + " ");
            //인접 노드들 list
            for(Integer i : graph.get(first)){
                inArr[i]--;
                if(inArr[i]==0){
                    queue.offer(i);
                }
            }
        }

        System.out.println(sb);
    }
}
