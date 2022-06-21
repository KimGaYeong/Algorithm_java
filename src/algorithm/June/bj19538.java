package algorithm.June;

import java.io.*;
import java.util.*;

public class bj19538 {

    static int N, M;
    static int[] times;
    static boolean[] isBelieve;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        // 몇 초에 루머를 믿게 되었는지
        times = new int[N+1];
        for(int i=1; i<=N; i++) {
            times[i] = -1;
        }

        // 주변인 관계
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        String str;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            while(!((str = st.nextToken()).equals("0"))) {
                int j = Integer.parseInt(str);
                graph.get(i).add(j);
            }
        }

//        for(int i=1; i<=N; i++) {
//            System.out.println(graph.get(i).toString());
//        }

        // 루머를 믿으면 true, 안 믿으면 false
        isBelieve = new boolean[N+1];

        // 루머 최초 유포자들
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++) {
            int num = Integer.parseInt(st.nextToken());
            isBelieve[num] = true; // 루머 믿음
            times[num] = 0; // 0초부터 믿음
        }

        bfs();

        for(int i=1; i<=N; i++) {
            bw.write(times[i]+" ");
        }
        bw.flush();
    }

    public static void bfs() {
        // 루머 믿는 사람들 따라 bfs 탐색
        Queue<Integer> q = new LinkedList<>();

        // 초기 루머 믿는 사람들 정보 q에 들어감
        for(int i=1; i<=N; i++) {
            if(isBelieve[i]) {
                q.offer(i);
            }
        }

        // depth를 확인해야 하므로 time 정보 관리하고, q size만큼씩 확인
        int time = 1;
        while(!q.isEmpty()) {
            int size = q.size();

            List<Integer> toBelieve = null;
            while (size-- > 0) {
                int curr = q.poll();

                // 주변인 중 루머를 믿게 되는 사람들
                toBelieve = new ArrayList<>();

                // 주변인들에게 루머 퍼뜨리려 한다
                for (int adj : graph.get(curr)) {
                    if (isBelieve[adj]) continue; // 이미 믿는 사람 제외

                    int count = 0; // 주변인 중 몇 명이 루머를 믿는지
                    for (int next : graph.get(adj)) { // 주변인의 주변인 확인
                        if (isBelieve[next]) count++;
                    }

                    //System.out.println("adj: " + adj + ", count: " + count);

                    // 주변인 중 절반 이상이 루머를 믿을 때 루머를 믿게 됨
                    if (count >= (graph.get(adj).size() + 1) / 2) {
                        toBelieve.add(adj);
                    }
                }

                // 루머는 한 번에 퍼져야 함
                for (int adj : toBelieve) {
                    isBelieve[adj] = true;
                    times[adj] = time;
                    q.offer(adj);
                }

//                System.out.println(Arrays.toString(times));
            }

            time++;
        }
    }
}