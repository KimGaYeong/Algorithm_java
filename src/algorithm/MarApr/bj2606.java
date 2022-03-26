package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj2606 {
    static int V, E, answer;
    static boolean[] visit;
    static List<List<Integer>> list;
    public static void main(String[] args) throws IOException {
        InputStream input = bj2606.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        visit = new boolean[V+1];
        list = new ArrayList<>();
        for(int i=0;i<=V;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            list.get(to).add(from);
            list.get(from).add(to);
        }

        answer = bfs(1);

        System.out.println(answer);
    }

    public static int bfs(int x){
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        visit[x] = true;

        while (!queue.isEmpty()){
            int c = queue.poll();

            for(int i=0;i<list.get(c).size();i++){
                int n = list.get(c).get(i);
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
