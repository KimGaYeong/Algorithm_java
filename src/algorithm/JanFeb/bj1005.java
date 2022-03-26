package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 위상정렬. 음.. inArr의 차수가 같은 애들끼리 봐서 건물 짓는 시간이 가장 커다란 애들 크기로 더하기?
 */
public class bj1005 {
    static int T;
    static int N, K, W;
    static int[] inArr;
    static int[] sum;
    static Building[] buildings;
    static Queue<Integer> queue;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 건물 개수
            K = Integer.parseInt(st.nextToken()); // 규칙 개수
            inArr = new int[N+1]; // 건물의 진입 차수
            sum = new int[N+1];
            st = new StringTokenizer(br.readLine());
            buildings = new Building[N+1];
            //building : [건물 번호, 건물 짓는데 걸리는 시간]
            for(int i=1;i<N+1;i++){
                buildings[i] = new Building(i,Integer.parseInt(st.nextToken()));
            }

            // 노드 정보 초기화
            graph = new ArrayList<>();
            for(int i=0;i<=N;i++) { // 1~N까지의 건물
                    graph.add(new ArrayList<>());
            }

            // 노드 정보 입력
            for(int i=0;i<K;i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
                inArr[to]++;
            }

            W = Integer.parseInt(br.readLine()); // 지어야 하는 빌딩

            //진입 차수가 0인 지점부터 계산.
            queue = new LinkedList<>();
            for(int i=1;i<=N;i++){
                if(inArr[i]==0){
                    queue.offer(i);
                    sum[i] = buildings[i].time;
                }
            }

            while(!queue.isEmpty()){
                int first = queue.poll(); //노드 꺼냄
                //인접 노드들 list
                for(Integer i : graph.get(first)){
                    inArr[i]--;
                    sum[i] = Math.max(sum[i], sum[first]+buildings[i].time);
                    if(inArr[i]==0){
                        queue.offer(i);
                        //System.out.println("from : " + first + " to : " + i);
                        //sum[i] = sum[first] + buildings[i].time;
                    }
                    //System.out.println(Arrays.toString(sum));
                }

            }
            sb.append(sum[W] + "\n");
        }

        System.out.println(sb);

    }

    public static class Building{
        int num, time;
        Building(int num, int time){
            this.num = num;
            this.time = time;
        }
    }
}
