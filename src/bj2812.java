import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Deque;

public class bj2812 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String tmp = br.readLine();
        Deque<Character> dq = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            System.out.println(tmp.charAt(i));
            while(K>0 && !dq.isEmpty() && dq.getLast() < tmp.charAt(i)) {
                dq.removeLast();
                K--;
            }
            dq.addLast(tmp.charAt(i));
        }

        while(dq.size() > K) {
            sb.append(dq.removeFirst());
        }
        System.out.println(sb);
    }
}

/*
* 현재 추가할 숫자가 그 전 숫자보다 작을 때 까지 pop_back.
* */
