package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

///K명의 친구 관계인 학생들이 존재하지 않는다면 -1을 출력
public class bj2026 {
    static int K, N, F;
    static boolean[] visit;
    static boolean[][] map;
    static int[] friendCnt;
    static boolean finish;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        friendCnt = new int[N];

        map = new boolean[N][N];
        //System.out.println(map);
        //학생의 집합(?)이 K명인지 찾기.

        for(int i=0;i<F;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;

            map[from][to] = true;
            map[to][from] = true;
            friendCnt[from]++;
            friendCnt[to]++;
        }

        //System.out.println(map);
        finish = false;

        //서로 친구관계여야 한다. -> 친구 관계 : 친구의 친구이면 친구 아님.
        visit = new boolean[N];
        for(int i=0;i<N;i++){
            if(friendCnt[i]<K-1) continue;

            visit[i] = true;
            DFS(i, 1);
            visit[i] = false;

            if(finish) break;
        }

        if(finish) System.out.println(sb);
        else System.out.println("-1");
    }

    public static void DFS(int idx, int cnt){
        if(finish) return;
        //System.out.println(idx + " " + cnt);
        if(cnt==K){
            //검증
            //System.out.println(Arrays.toString(visit));

            for(int i=0; i<N; i++) {
                if(visit[i]) System.out.println(i+1);
            }
            finish = true;
            return;
        }

        for(int i=idx+1;i<N;i++){
//            System.out.println("idx is " + idx + " i is " + i  + " && map is " + map.get(idx).contains(i));
//            System.out.println("idx is " + idx + " i is " + i  + " && com is " + com(i));
            if(map[idx][i] && com(i)){
                visit[i] = true;
                DFS(i, cnt+1);
                visit[i] = false;
            }
        }
    }


    public static boolean com(int idx){
        //현재 idx를 보고있고 앞에 확인한 애들은 visit처리가 되어있는 상태.
        // 내 map에 visit된 애들이 다 친구인지 확인
        for(int i=0;i<N;i++){
            if(i!=idx && visit[i]){
                if(!map[idx][i]) return false;
            }
        }
        return true;
    }
}
