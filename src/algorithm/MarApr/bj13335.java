package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj13335 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj2170.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Queue<Integer> queue= new LinkedList<>();
        List<Truck> trucks= new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(st.nextToken());
            queue.add(num);
        }
        int time = 0;
        int finish =0;
        int weight =0; //현재 다리 무게
        while(true){
            if(finish==N){
                time+=1;
                break;
            }

            if(!queue.isEmpty()){
                int cur = queue.peek();
                //다리에 올릴 지 안올릴 지를 결정하고
                if(weight+cur <= L){
                 //다리에 올릴 수 있음.
                    weight += cur;
                    queue.poll();
                    trucks.add(new Truck(cur, W));
                }
            }

            //다리 사이즈만큼 위치 +1해서
            for(int i=0;i<trucks.size();i++){
                trucks.get(i).idx-=1;

                //다리 지나간 애들은 다리에서 꺼내줌.
                if(trucks.get(i).idx==0){
                    finish+=1;
                    weight-=trucks.get(i).weight;
                    trucks.remove(i);
                    i--;
                }
            }

            time++;

        }
        System.out.println(time);
    }
    public static class Truck{
        int weight, idx;
        //트럭의 무게, 현재 다리에서 어느 인덱스에 위치 해 있는지?
        public Truck(int weight, int idx) {
            this.weight = weight;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Truck{" +
                    "weight=" + weight +
                    ", idx=" + idx +
                    '}';
        }
    }
}
