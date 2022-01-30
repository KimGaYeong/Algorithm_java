import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Deque;

public class bj2812 {
    static int N;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String tmp = br.readLine();
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            System.out.println("현재 Deque : " + dq.toString());
            System.out.println("지금 보고 있는 원소 : " +tmp.charAt(i));
            while(K>0 && !dq.isEmpty() && dq.getLast() < tmp.charAt(i)) {
                String ch = dq.getLast().toString();
                System.out.println("덱의 마지막 원소 : " +  ch);
                dq.removeLast();
                System.out.println("현재 보고있는 원소 [" + tmp.charAt(i) + "]가 덱의 마지막 원소 [" + ch + "]보다 크다.");
                K--;
            }
            dq.addLast(tmp.charAt(i));
        }
        System.out.println("덱 : " +dq.toString());

        for(int i=0;i<dq.size();i++) {
            sb.append(dq.removeFirst());
            i--;
        }
        System.out.println(sb);
    }
}

/*
* 현재 추가할 숫자가 그 전 숫자보다 작을 때 까지 pop_back.
* */
