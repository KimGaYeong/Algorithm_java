package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//백트래킹? 뭘까..? -> 문제에 나온 예제 보고 DFS삽질함.

public class bj13975 {
    static int N;
    static long ans;
    static PriorityQueue<Long> pq;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1937.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            N = Integer.parseInt(br.readLine());

            pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            ans = 0L;
            while (true) {
                if(pq.size()==1) break;
                long a = pq.poll();
                long b = pq.poll();
                ans += a + b;
                pq.add(a + b);
            }
            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }

}
