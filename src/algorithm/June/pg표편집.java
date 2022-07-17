package algorithm.June;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class pg표편집 {

    public static void main(String[] args) {
        int n = 8; //0~7
        int k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};

        System.out.println(solution(n,k,cmd));
    }

    static boolean[] isExisted;
    static List<Integer> list;
    static StringTokenizer st;

    public static String solution(int n, int k, String[] cmd) {
        StringBuilder sb= new StringBuilder();
        list = new LinkedList<>();
        for(int i=0;i<n;i++){
            list.add(i);
        }
        now = k;

        isExisted = new boolean[n];

        for(String str : cmd){
            String first="";
            int second =0;
            st = new StringTokenizer(str);
            first = st.nextToken();

            if(first.equals("D") || first.equals("U")){
                second = Integer.parseInt(st.nextToken());
            }
            solve(first, second);
        }
//        System.out.println(list);

        for(int i=0; i<n; i++) {
            sb.append("O");
        }

        while(!recent.isEmpty()) {
            int idx2 = recent.pop().value;
            //System.out.println(idx2);
            sb.setCharAt(idx2, 'X');
        }

        return sb.toString();

    }

    /*
        "U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
        "D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
        "C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
        "Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
    */
    static int now; //now:현재 있는 index. 즉 현재 선택된 행!, //선택된 행과 인덱스는 다르다.
    static Stack<Replication> recent = new Stack<>(); // 가장 최근에 삭제된 애의 값과 그 인덱스.

    public static void solve(String first, int second){
//        System.out.println("beforeList : " + list);
//        System.out.println("현재 선택된 칸 ; " + now);
//        System.out.println("command: " + first + " " + second);

        switch (first) {
            case "C" -> {
                recent.add(new Replication(list.get(now), now)); //value, index

//                System.out.println("삭제!!! -->" + recent);
                int size = list.size();
                list.remove(now); //현재 선택된 행 삭제. (선택된 '행'의 인덱스)는 그대로임.

                // 인덱스 자체는 변화는 없지만 선택하고 있는 '값'은 바뀐 상황.
                //System.out.println("지운 다음 선택된 행 : "+ now);
                //삭제한 행의 index가 맨 뒤라면? now 한 칸 위의 인덱스로 바꾸기.
                if (now == size - 1) {
                    now -=1;
                }

            }
            case "D" -> now += second;
//                System.out.println("선택된 칸에서 " + second + " 만큼 아래에 있는 행 : " + now);
            case "U" -> now -= second;
//                System.out.println("선택된 칸에서 " + second + " 만큼 위에 있는 행 : " + now);
            default -> {  //first.equals("Z")
                // 가장 최근 삭제한 애의 값과 행 : 값은 recent의 value, 위치는 index.
                // 얘를 현재 index에 삽입한다.
                Replication revival = recent.pop();
                list.add(revival.idx, revival.value);

                if(revival.idx <= now) {
                    now+=1;
                }
            }
        }
//        System.out.println("afterList : " + list);

    }
    public static class Replication{
        int value, idx;

        public Replication(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }

}
