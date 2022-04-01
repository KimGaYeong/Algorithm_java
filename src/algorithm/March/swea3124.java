package algorithm.March;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea3124 {
    static int V, E;
    static long ans;
    static int[] parent;
    static Edge[] edge;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            ans = 0;
            parent = new int[V + 1];
            edge = new Edge[E];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edge[i] = new Edge(s, e, v);
            }
            makeSet();
            Arrays.sort(edge, (a, b) -> a.value - b.value);
            int cnt = 0;
            for (int i = 0; i < E; i++) {
                Edge e = edge[i];
                if (findSet(e.start) == findSet(e.end)) continue;
                union(e.start, e.end);
                cnt++;
                ans += e.value;

                if (cnt == V-1) break;
            }
            System.out.println("#"+t+" "+ans);
        }
    }

    static void makeSet() {
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
    }

    static int findSet(int x) {
        if (parent[x] == x)
            return x;
        else
            return parent[x] = findSet(parent[x]);
    }

    static void union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px < py)
            parent[py] = px;
        else
            parent[px] = py;
    }

    static class Edge {
        int start, end, value;

        public Edge(int start, int end, int value) {
            super();
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}

/*
1
3 3
1 2 1
2 3 2
1 3 3
 */