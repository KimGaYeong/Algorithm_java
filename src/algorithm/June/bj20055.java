package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj20055 {
    static int N, K, re;
    static List<Cont> belt;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2*N;i++){
            belt.add(new Cont(Integer.parseInt(st.nextToken()), 0));
        }

        //맨 뒤를 앞으로 땡겨!

        int result = 1;
        while(true){
            //System.out.println("처음 : " + belt);
            //컨베이어 벨트 회전
            int idx = (2*N)-1;
            Cont val = belt.get(idx);
            belt.remove(idx);
            belt.add(0, val);

            //회전 후 N칸에서 로봇 다 내리는거
            belt.get(N-1).robot = 0;
            //System.out.println("이동 : " + belt);

            // 가장 먼저 올라온 순. 즉 2N-1부터 역순으로.
            if(belt.get(idx).robot>0 && belt.get(0).robot==0 && belt.get(0).power>=1){
                belt.get(0).robot = belt.get(idx).robot;
                belt.get(idx).robot = 0;
                belt.get(0).power-=1;
            }

            for(int i=idx-1;i>=0;i--){
                if(belt.get(i).robot>0 && belt.get(i+1).robot==0 && belt.get(i+1).power>=1){
                    if(i+1 == N-1){
                        belt.get(i).robot = 0;
                    }
                    belt.get(i+1).robot = belt.get(i).robot;
                    belt.get(i).robot = 0;
                    belt.get(i+1).power-=1;
                }

                //System.out.println("로봇 이동 : " + belt);
            }



            //System.out.println("로봇 이동 끝 : " + belt);

            if(belt.get(0).power>=1){
                belt.get(0).robot+=1;
                belt.get(0).power-=1;
            }

            //System.out.println("로봇 올림 : " + belt);


            re = 0;
            for(Cont c : belt){
                if(c.power==0){
                    re +=1;
                }
            }

            if(re>=K){
                System.out.println(result);
                break;
            }else{
                result++;
            }

        }
        /*
        121212
        212121
         */

    }

    public static class Cont{
        int power;
        int robot;

        public Cont(int power, int robot) {
            this.power = power;
            this.robot = robot;
        }

        @Override
        public String toString() {
            return "{" +
                    "p=" + power +
                    ", r=" + robot +
                    '}';
        }
    }
}
