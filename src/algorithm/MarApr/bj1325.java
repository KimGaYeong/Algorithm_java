package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1325 {
    static int N, M, cnt;
    static boolean[] check;
    static ArrayList<ArrayList<Integer>> map;
    public static void main(String[] args) throws IOException {
        InputStream input = bj12865.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //map 초기화
        Com[] coms = new Com[N+1];
        map = new ArrayList<>();
        for(int i=0;i<N+1;i++){
           map.add(new ArrayList<>());
        }


        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map.get(to).add(from);
        }

        for(int i=1;i<=N;i++){
            check = new boolean[N+1];
            cnt =1;
            hack(i);
            //System.out.println(i + " " + hack(i));
            coms[i] = new Com(i, hack(i));
        }


        sb.append(coms[1].idx);

        if(N>1){
            int i=2;
            while(i<=N){
                if(coms[i].cnt == coms[i-1].cnt){
                    sb.append(" " + coms[i].idx);
                    i++;
                }else{
                    break;
                }
            }
        }
        System.out.println(sb);

    }

    public static int hack(int num){
        Queue<Integer> queue = new LinkedList<>();

        queue.add(num);
        check[num] = true;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int i=0;i<map.get(cur).size();i++){
                int next = map.get(cur).get(i);
                if(!check[next]){
                    check[next] = true;
                    queue.add(next);
                    cnt +=1;
                }
            }
        }
        return cnt;
    }


    public static class Com implements Comparable<Com>{
        int idx, cnt;

        public Com(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Com o) {
            return o.cnt-this.cnt;
        }
    }
}


/*
public static void main(String[] args) throws IOException {
        InputStream input = bj12865.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 */