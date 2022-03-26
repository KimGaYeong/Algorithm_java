package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj9372 {
    //union find로 연결 개수 찾기.
    static int T;
    static int N, M;
    static int[] parent;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            count =0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            parent = new int[N+1];
            M = Integer.parseInt(st.nextToken());

            for(int i=1;i<=N;i++){
                parent[i] = i;
            }
            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                union(to, from);
            }
            sb.append(count + "\n");
        }
        System.out.println(sb);
    }
    public static int find(int x){
        if(x == parent[x]){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x !=y){
            parent[y] = x;
            count++;
        }
    }

}
