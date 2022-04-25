package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1700 {
    static int N, K;
    static List<Integer> multitap, order;
    static int max, tmpmax, sub, cnt;

    public static void main(String[] args) throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cnt = 0;
        multitap = new LinkedList<>();
        order = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order.add(Integer.parseInt(st.nextToken()));
        }

        max = 0;
        tmpmax = 0;
        sub = 0;
        for (int i = 0; i < K; i++) {

            //꽃아야 할 놈이 이미 꽃혀있음.
            if (multitap.contains(order.get(i))) {
                continue;
            }

            //멀티탭이 다 안찼으면 꽂아주면 된다.
            if (multitap.size() < N) {
                multitap.add(order.get(i));
            } else if (multitap.size() == N) {
                //다 안찼을 때
                for (int j = 0; j < N; j++) {
                    int out = multitap.get(j);
                    //지금 꽂힌 애들중에 나중에 안쓰이는 애들이 있는지 확인함.

                    if (!order.subList(i, K).contains(out)) {//없으면 걔 자리에 꽂음.
                        tmpmax = j;
                        break;
                    } else if (order.subList(i, K).indexOf(out) > sub) {
                        sub = order.subList(i, K).indexOf(out);
                        tmpmax = j;
                    }
                }

                multitap.remove(tmpmax);
                multitap.add(order.get(i));
                sub = 0;
                tmpmax = 0;
                cnt += 1;
            }
        }
        System.out.println(cnt);


    }
}
//2 7
//2 3 2 3 1 2 7