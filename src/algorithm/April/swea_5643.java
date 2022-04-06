package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_5643 {
    static int N, M;
    static ArrayList<ArrayList<Integer>> smaller;
    static ArrayList<ArrayList<Integer>> taller;
    public static void main(String[] args) throws IOException {
        InputStream input = swea_5643.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            int answer =0;
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            smaller = new ArrayList<>();
            taller = new ArrayList<>();

            for(int i=0;i<N+1;i++){
                smaller.add(new ArrayList<>());
                taller.add(new ArrayList<>());
            }

            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int small = Integer.parseInt(st.nextToken());
                int tall = Integer.parseInt(st.nextToken());
                taller.get(small).add(tall);
                smaller.get(tall).add(small);
            }

            int count=0;

            for(int i=1;i<=N;i++){
                //i에 대해 나보다 작은애, 큰애 값을 조사한다.
                count = findIdx(i);
                if(count==N-1){ //나 빼고 N-1개
                    answer++;
                }
            }
            sb.append("#" + t + " " + answer + "\n");
        }
        System.out.println(sb);

    }

    public static int findIdx(int num) {
        //i에 대해서 작은애, 큰 애를 찾는다.
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[N+1];
        int count =0;

        //1. i보다 작은 애 찾기
        queue.add(num);
        while (!queue.isEmpty()){
            int c = queue.poll();
            for(int i=0;i<smaller.get(c).size();i++){
                int n = smaller.get(c).get(i);
                if(!visit[n]){
                    visit[n] = true;
                    queue.add(n);
                    count++;
                }
            }
        }
        //1. i보다 큰 애 찾기
        queue.add(num);
        while (!queue.isEmpty()){
            int c = queue.poll();
            for(int i=0;i<taller.get(c).size();i++){
                int n = taller.get(c).get(i);
                if(!visit[n]){
                    visit[n] = true;
                    queue.add(n);
                    count++;
                }
            }
        }
        return count;
    }
}
