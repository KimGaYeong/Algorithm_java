package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj21939 {
    static PriorityQueue<Problem> hard;
    static PriorityQueue<Problem> easy;
    static int[] data = new int[100001];
    public static void main(String[] args) throws IOException {
        InputStream input = bj9466.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        hard = new PriorityQueue<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if(o1.tier == o2.tier) return Integer.compare(o2.idx, o1.idx);
                return Integer.compare(o2.tier, o1.tier);
            }
        });

        easy = new PriorityQueue<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if(o1.tier == o2.tier) return Integer.compare(o1.idx, o2.idx);
                return Integer.compare(o1.tier, o2.tier);
            }
        });

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int pidx = Integer.parseInt(st.nextToken());
            int ptier = Integer.parseInt(st.nextToken());
            hard.add(new Problem(pidx, ptier));
            easy.add(new Problem(pidx, ptier));
            data[pidx] = ptier;
        }

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if(str.equals("add")){
                int P = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                data[P] = T;
                hard.add(new Problem(P, T));
                easy.add(new Problem(P, T));
            }else if(str.equals("recommend")){
                int HorE = Integer.parseInt(st.nextToken());
                if(HorE ==1){
                    while(true){
                        Problem problem = hard.peek();
                        if(data[problem.idx] == problem.tier){
                            sb.append(problem.idx + "\n");
                            break;
                        }
                        hard.poll();
                    }
                }else{
                    while(true){
                        Problem problem = easy.peek();
                        if(data[problem.idx] == problem.tier){
                            sb.append(problem.idx + "\n");
                            break;
                        }
                        easy.poll();
                    }
                }
            }else if(str.equals("solved")){
                int idx = Integer.parseInt(st.nextToken());
                data[idx] = 0;     }
        }
        System.out.println(sb);
    }

    public static class Problem implements Comparable<Problem>{
        int idx, tier;

        public Problem(int idx, int tier) {
            this.idx = idx;
            this.tier = tier;
        }

        @Override
        public int compareTo(Problem o) {
            return o.compareTo(this);
        }
    }

}
