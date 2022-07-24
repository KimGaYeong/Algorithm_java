package algorithm.CodingTest.Kakaopay;

import java.util.*;

public class sol1 {
    public static void main(String[] args) {
        int region = 2;
        int num = 4;
        int[][] info = {{1, 0, 2, 1}, {2, 6, 5, 2}, {3, 10, 2, 4}, {1, 1, 5, 6}, {2, 7, 10, 2}, {3, 8, 6, 3}};

        Queue<Integer> queue = new LinkedList<>();

        int a = (int) Math.pow(1,2);
        Map<String, String> map = new HashMap<>();
        map.put("s", "s");

        int as = 1;
        String str = Integer.toString(as) + Integer.toString(as);
        System.out.println(str);
    }

    //{거주지 / 무주택 / 가입 기간 / 부양수}
    //1 ≤ region ≤ 3 , (1=서울, 2=경기, 3=인천) -> PQ 3개 만들어서 넣기.
    // 가점 : 2*
    static int cnt;
    public static int[] solution(int region, int num, int[][] info){

        PriorityQueue<People>[] regions = new PriorityQueue[3];
        for(int i=0;i<3;i++){
            regions[i] = new PriorityQueue<>();
        }
//        PriorityQueue<People> region1 = new PriorityQueue<>();
//        PriorityQueue<People> region2 = new PriorityQueue<>();
//        PriorityQueue<People> region3 = new PriorityQueue<>();
        int len = info.length;
        int[] answer = new int[len];
        Arrays.fill(answer, -1);

        Arrays.sort(answer);

        for(int i=0;i<len;i++){
            int[] in = info[i];
            System.out.println(Arrays.toString(in));
            int score = (2*in[1]) + in[2] + (5*in[3]) + 9;
            regions[in[0]-1].add(new People(score, i, -1));
        }

//        [People{score=16, idx=0, flag=-1}, People{score=46, idx=3, flag=-1}]
//        [People{score=36, idx=1, flag=-1}, People{score=43, idx=4, flag=-1}]
//        [People{score=46, idx=5, flag=-1}, People{score=51, idx=2, flag=-1}]

        cnt = 1;
        int target = region;
        while(!regions[target-1].isEmpty()){
            if(num==0){
                break;
            }

            People p = regions[target-1].poll();
            answer[p.idx] = cnt;
            System.out.println(cnt+"명뽑았다!");
            cnt++;
            num-=1;
        }

        if(num>0){
            System.out.println("num>0");

            PriorityQueue<People> finall = new PriorityQueue<>();
            for(int i=0;i<3;i++){
                if(i == target-1){
                    continue;
                }
                System.out.println(i);
                while(!regions[i].isEmpty()){
                    People p = regions[i].poll();
                    finall.add(p);
                }
            }

            System.out.println(finall);

            while(!finall.isEmpty()){
                if(num==0){
                    break;
                }
                People p = finall.poll();
                answer[p.idx] = cnt;
                System.out.println(cnt+"명뽑았다!");
                cnt++;
                num-=1;
            }
        }

        return answer;
    }

    public static class People implements Comparable<People>{
        int score;
        int idx;
        int flag;

        public People(int score, int idx, int flag) {
            this.score = score; // = (2*noHouse) + time + (5*family) + 9;
            this.idx = idx;
            this.flag = flag;
        }

        @Override
        public int compareTo(People o) {
            if(this.score == o.score) {
                return Integer.compare(this.idx, o.idx); // 오름차순
            }
            else{
                return -1 * Integer.compare(this.score, o.score); // 내림차순
            }
        }

        @Override
        public String toString() {
            return "People{" +
                    "score=" + score +
                    ", idx=" + idx +
                    ", flag=" + flag +
                    '}';
        }
    }
}
