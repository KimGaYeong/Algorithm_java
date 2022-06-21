package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1781 {
    static int N;
    static Map<Integer, List<Integer>> Homeworks;

    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Homeworks = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int noodle = Integer.parseInt(st.nextToken());

            if(Homeworks.containsKey(deadline)){
                Homeworks.get(deadline).add(noodle);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(noodle);
                Homeworks.put(deadline, list);
            }
        }

        //System.out.println(Homeworks);

        List<Integer> list = new ArrayList<>(Homeworks.keySet());
        Collections.sort(list);
        //System.out.println(list);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int size = list.size();
        for(int i=0;i<size;i++){
            //i일 동안 풀 수 있는 문제를 모두 우선순위 큐에 저장
            for(int num : Homeworks.get(list.get(i))){
                pq.add(num);
                // 푸는 문제의 수가 i일 초과하면
                while(pq.size()>list.get(i)){
                    //라면을 가장 적게 주는 애를 지워버린다.
                    pq.poll();
                }
            }
        }
        long sum =0;
        while(!pq.isEmpty()){
            sum+=pq.poll();
        }
        System.out.println(sum);
    }
}
