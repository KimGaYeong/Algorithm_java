package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// 입력 : 주사위에서 나올 수 10개
// 말이 파란색 칸에서 이동을 시작하면 파란색 화살표를 타야 함.
// 이동하는 도중이거나 파란색이 아닌 칸에서 이동을 시작하면 빨간색 화살표를 타야 함.
// 말이 도착 칸으로 이동하면 주사위에 나온 수와 관계 없이 이동을 마침.

// 말이 이동을 마치는 칸에 다른 말이 있으면 고를 수 없다. 도착 칸 예외
// 말이 이동을 마칠 때마다 카넹 적혀있는 수가 점수에 추가

// 주사위는 1~5
public class bj17825 {
    static int result;
    static int[] arr;
    static List<Pan> map;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        arr=  new int[10];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<10;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        map = new ArrayList<>();
        setting();
        result =0;
        DFS(0, 0, new int[4]);
        System.out.println(result);
    }

    public static void DFS(int sum, int cnt, int[] horse){

        if(cnt==10){ //10번 다 돌면
            result = Math.max(result, sum);
            return;
        }

        for(int i=0;i<4;i++){
            boolean end = false;
            boolean isAnotherHorse;
            if(horse[i] == 50) continue;

            // 주사위에 나오는 수 결정.
            int diceVal = arr[cnt];

            //어디로 가야될 지 결정.
            //파란색으로 갈 수 있으면 파랑이로 ㄱㄱ
            int next = map.get(horse[i]).blue;
            if(next==0){
                //그럴 수 없으면 빨강이
                next = map.get(horse[i]).red;
            }
            if(next==50){
                return;
            }

            //주사위 칸만큼 이동...
            while(diceVal-->1){
                next = map.get(next).red;
                if(next==50){ //끝에 도착하면 끄읕
                    end = true;
                    int[] copy = copy(horse);
                    copy[i] = next;
                    DFS(sum, cnt+1, copy);
                    break;
                }
            }
            if(end) continue;


            isAnotherHorse = true;
            for(int j=0;j<4;j++){ //같은 곳에 없어야 함. 같은 곳에 있으면 고르지 말기.
                if(i==j) continue;
                if(horse[j] == next){
                    isAnotherHorse = false;
                    break;
                }
            }

            if(isAnotherHorse){
                int[] copy = copy(horse);
                copy[i] = next;
                DFS(sum+map.get(next).val,cnt+1, copy);
            }
        }

        result = Math.max(result, sum);
    }

    public static int[] copy(int[] orimap){
        int[] copymap = new int[4];
        for(int i=0;i<4;i++){
            copymap[i] = orimap[i];
        }
        return copymap;
    }

    public static void setting(){
        //한바퀴 삥
        for(int i=0;i<=19;i++){
            if(i==5 || i==10 || i==15) continue;
            map.add(new Pan(i, 2*(i), i+1, 0));
        }

        //맨 마지막
        map.add(new Pan(20, 40, 50, 0));

        //빨간색
        map.add(new Pan(21, 13, 22, 0));
        map.add(new Pan(22, 16, 23, 0));
        map.add(new Pan(23, 19, 31, 0));
        //파란색
        map.add(new Pan(26, 28, 25, 0));
        map.add(new Pan(25, 27, 24, 0));
        map.add(new Pan(24, 26, 31, 0));
        //초록색
        map.add(new Pan(27, 22, 28, 0));
        map.add(new Pan(28, 24, 31, 0));
        //핑크색
        map.add(new Pan(29, 30, 30, 0));
        map.add(new Pan(30, 35, 20, 0));
        //정가운데
        map.add(new Pan(31, 25, 29, 0));

        //파란색선 있는 애들
        map.add(new Pan(5, 10, 6, 21));
        map.add(new Pan(10, 20, 11, 27));
        map.add(new Pan(15, 30, 16, 26));

        Collections.sort(map);
        //System.out.println(map);
    }

    public static class Pan implements Comparable<Pan>{
        int idx, val; //동그라미의 인덱스와 동그라미 안에 들어있는 값
        int red, blue; //빨간 선으로 가면 나오는 인덱스랑 파란 선으로 가면 나오는 인덱스

        public Pan(int idx, int val, int red, int blue) { //파란색으로 가는 것도 있는 경우
            this.idx = idx;
            this.val = val;
            this.red = red;
            this.blue = blue;
        }

        @Override
        public int compareTo(Pan o) {
            return Integer.compare(this.idx, o.idx);
        }

        @Override
        public String toString() {
            return "Pan{" +
                    "idx=" + idx +
                    '}';
        }
    }
}


