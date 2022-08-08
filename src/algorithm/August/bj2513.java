package algorithm.August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj2513 {
    static int N, K, S;
    static PriorityQueue<Apartment> left, right;
    public static void main(String[] args) throws IOException {
        InputStream input = bj2225.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //apt 단지
        K = Integer.parseInt(st.nextToken()); //버스 정원
        S = Integer.parseInt(st.nextToken()); //학교의 위치
        left = new PriorityQueue<>();
        right = new PriorityQueue<>();
        // 수직선은 0부터 시작
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            if(idx-S >0){
                idx = idx-S;
                right.add(new Apartment(i, Integer.parseInt(st.nextToken())));
            }else{
                idx = S-idx;
                left.add(new Apartment(i, Integer.parseInt(st.nextToken())));
            }
        }


        // 멀리서부터 태워야 인원이 초과됐을 때 다시 태우러 와야하는 아파트의 인덱스가 학교와 조금이라도 가까운 곳으로 왕복할 수 있음.
        // 가깝다고 해서 학교를 기준으로 왼쪽 오른쪽을 모두 번갈아가며 탐색하면 거리는 두배가 됨. 따로 탐색하자



    }

    private static int Solve(PriorityQueue<Apartment> pq){

        int len = 0;

        while(!pq.isEmpty()){
            Apartment apt = pq.poll();
            int doublelen = apt.num/K;
            if(apt.num%K >0) doublelen++;
            //처음으로 태울 수 있는 횟수


        }

        return len;
    }

    private static class Apartment implements Comparable<Apartment>{
        int idx, num;

        public Apartment(int idx, int num){
            this.idx = idx;
            this.num = num;
        }

        @Override
        //인덱스(거리)가 먼 순부터 데려감.
        public int compareTo(Apartment o) {
            return (-1) * this.idx - o.idx;
        }
    }
}
