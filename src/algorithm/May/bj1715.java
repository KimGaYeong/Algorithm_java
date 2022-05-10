package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class bj1715 {
    static int N;
    static long ans;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        ans = 0L;
        while (true) {
            if(pq.size()==1) break;
            int a = pq.poll();
            int b = pq.poll();
            ans += a + b;
            pq.add(a + b);
        }
        sb.append(ans);
        System.out.println(sb);
    }

}
