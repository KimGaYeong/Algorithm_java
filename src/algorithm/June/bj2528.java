package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
     올라갈 수 있는 경우 : 시작, 끝이랑 비교했을 때
     */
public class bj2528 {
    static int N, L;
    static int now, answer;
    static boolean isFinish = false;
    static Stair stairs[];
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        stairs = new Stair[N + 1];
        now = 1;
        answer = -1;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            int del = Integer.parseInt(st.nextToken());
            if (del == 0) { //왼쪽에서 시작, 끝, 방향
                stairs[i] = new Stair(0, length, del);
            } else { //오른쪽에서
                stairs[i] = new Stair(L-length, L, del);
            }
        }

        while (true) {
            up();
            move();
            answer++;

            if(isFinish) break;
        }

        System.out.print(answer);
    }

    public static void up() {
        Stair cs, ns;
        while (true) {
            if (now == N) {
                isFinish = true;
                break;
            }
            cs = stairs[now];
            ns = stairs[now + 1];
/*
     1)
     =====
       ------

     2)
       ======
     ------

     3)
        ====
      ---------

     4)
     ========
      ------
             */
            if ((cs.end >= ns.start && cs.end <= ns.end) || cs.start >= ns.start && cs.start <= ns.end){ // case 1
                now++;
            } else if (cs.start < ns.start && cs.end > ns.end) { // case 3
                now++;
            } else if (cs.start >= ns.start && cs.end <= ns.end) { // case 4
                now++;
            } else{
                break;
            }
        }
    }

    public static void move() {
        for (int i = 1; i <= N; i++) {
            Stair cur = stairs[i];
            if (cur.del == 0) {
                cur.start++;
                cur.end++;
                if (cur.end == L) {
                    cur.del = 1;
                }
            } else {
                cur.start--;
                cur.end--;
                if (cur.start == 0) {
                    cur.del = 0;
                }
            }
        }
    }

    public static class Stair {
        int start, end, del; //시작, 끝, 방향

        Stair(int start, int end, int del) {
            this.start = start;
            this.end = end;
            this.del = del;
        }
    }
}
