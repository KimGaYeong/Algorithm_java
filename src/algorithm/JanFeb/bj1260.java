package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1260 {
    static int N, M, V;
    static int[][] arr;
    static boolean[] visit;
    static Queue<Integer> queue;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        visit = new boolean[N+1];
        arr= new int[N+1][N+1];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = arr[to][from] =1;
        }

        DFS(V);
        sb.append("\n");
        visit = new boolean[N+1];
        queue = new LinkedList<>();
        BFS(V);
        System.out.println(sb);
    }

    public static void DFS(int start){
        visit[start] = true;
        sb.append(start + " ");
        for(int i=1;i<=N;i++){
            if(!visit[i] && arr[start][i] ==1){
                DFS(i);
            }
        }
    }

    public static void BFS(int start){
        queue.offer(start);
        visit[start] = true;

        while(!queue.isEmpty()){
            int cur = queue.peek();
            sb.append(cur + " ");
            queue.poll();
            for(int i=1;i<=N;i++){
                if(!visit[i] && arr[cur][i] ==1){
                    queue.offer(i);
                    visit[i] = true;
                }
            }

        }
    }
}
