package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * -- 규칙 --
 * 1. 우수 마을끼리는 인접하지 않아야 한다.
 * 2. 우수 마을로 선정된 마을 주민 수의 합은 최대가 되어야 한다.
 * 3. 비우수 마을은 최소 1개의 우수 마을과 인접해야 한다.
 */
public class bj1949 {
    static int N;
    static ArrayList<ArrayList<Integer>> map;
    static int[] jumin;
    static int[][] DP;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1949.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        jumin = new int[N+1];
        DP = new int[N+1][2];
        /*
        DP[n][0] : n번 마을이 우수 마을이 아닌 경우 + n번 마을과 연결되는 하위 마을 주민 수의 총 합
        DP[n][1] : n번 마을이 우수 마을인 경우 + n번 마을과 연결되는 하위 마을 주민 수의 총 합

        n번 마을이 우수 마을이라면? 우수 마을끼리는 인접할 수 없으므로 n번의 바로 밑 자식 마을은 우수 마을이면 안됨.
        n번 마을이 우수 마을이 아니라면? 자식 마을은 우수 마을일수도, 우수 마을이 아닐 수도 있음.
        모두 우수 / 모두 우수 아님 -> (조건 3에 위배되므로 안됨) / 자식 중 일부만 우수

        이 조건을 맞춰가며 맨~끝 자식부터 DP를 구한 뒤 루트노드인 DP[1]이 들어갔는지 아닌지를 비교해서 구한다.
         */
        map = new ArrayList<>();
        //map 초기화
        for(int i=0;i<N+1;i++){
            map.add(new ArrayList<>());
        }
        //주민 수 넣어주기
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            jumin[i] = Integer.parseInt(st.nextToken());
        }
        //길 입력받기
        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            //길은 양방향.
            map.get(from).add(to);
            map.get(to).add(from);
        }
        //1번 마을부터 이어봅시다
        DFS(2,0);
        System.out.println(Math.max(DP[2][0], DP[2][1]));
    }

    public static void DFS(int cur, int parent){
        //1번 마을의 자식들을 돌아보면서
        for(int i=0;i<map.get(cur).size();i++){
            int nx = map.get(cur).get(i);
            //nx == before이면 부모노드로 올라가버린거니까 자식 노드만 비교해준다.
            if(nx!=parent){
                // 자식이 있으면 또 거슬러 올라간다. 재귀재귀
                DFS(nx, cur);
                // 현재 마을 cur이 포함이 안되어있다면 -> 내 자식 노드가 포함되거나, 안되거나 둘 중 우수마을 총합이 큰걸로 고른다.
                DP[cur][0] += Math.max(DP[nx][0], DP[nx][1]);
                // 현재 마을 cur이 퐇마이 되어있다면 -> 내 자식 노드가 우수 마을일 수 없다. (조건1) 따라서 자식은 우수마을이 아닌놈으로 고른다.
                DP[cur][1] += DP[nx][0];
            }
        }

        DP[cur][1] += jumin[cur];
    }
}
