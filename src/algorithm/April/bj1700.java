package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1700 {
    static int N, K;
    static List<Integer> multitap, order;
    static int outtmp, sub, cnt;

    public static void main(String[] args) throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //멀티탭 구멍
        K = Integer.parseInt(st.nextToken()); //전기용품 사용
        cnt = 0;
        multitap = new LinkedList<>(); //현재 멀티탭 상태
        order = new LinkedList<>(); //전기용품 사용 순서
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order.add(Integer.parseInt(st.nextToken()));
        }

        outtmp = 0; sub = 0;
        //멀티탭이 찼는지 여부에 따라 다름.
        for (int i = 0; i < K; i++) {
            //현재 멀티템에 꽂아야 할 애 : order.get(i)

            //멀티탭에 꽂아야 할 놈이 이미 꽂혀있음. -> (넘어감)
            if (multitap.contains(order.get(i))) {
                continue;
            }

            // 멀티탭에 없는 애를 꼽아야 하는 경우.
            if (multitap.size() < N) { //멀티탭이 다 안찬 경우
                multitap.add(order.get(i)); //멀티탭에 꽂아주면 됨.
            } else if (multitap.size() == N) { //멀티탭이 다 찬 경우
                for (int j = 0; j < N; j++) {
                    //멀티탭을 탐색하며
                    int out = multitap.get(j);

                    //멀티텝에 나중에 다시 꽂지 않아도 되는 애가 있는지 확인.
                    if (!order.subList(i, K).contains(out)) {//꽂지 않아도 되는애가 있음
                        outtmp = j; //걔 자리에 꼽으면 됨.
                        break;
                    } else if (order.subList(i, K).indexOf(out) > sub) {
                        //나중에 꼽아야 되는 애가 있으면 최대한 나중일수록 좋음.
                        sub = order.subList(i, K).indexOf(out);
                        outtmp = j;
                    }
                }

                //뽑을 애를 뽑고, 새로 넣어야 될 애를 넣음.
                multitap.remove(outtmp);
                multitap.add(order.get(i));
                sub = 0; outtmp = 0;
                cnt += 1;
            }
        }
        System.out.println(cnt);


    }
}
//2 7
//2 3 2 3 1 2 7