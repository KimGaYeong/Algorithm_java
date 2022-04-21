package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
1부터 n까지 번호가 붙은 방을 지나가야 함.
각 방 안에는 번호가 붙은 문 or 레프리콘, 트롤 있을 수 있음.
문 : 문 번호의 방으로 이동
레프리콘 : 모험가의 소지금이 일정 양 이상 : 그대로 둠. 이하 : 일정량 까지 채워줌
트롤 : 통행료 지불.

소지금이 0 인 상태에서 출발하며 도착할 수 있으면 Yes, 아니면 No 출력
 */
public class bj2310 {
    static int n;
    static boolean result;
    static Room[] rooms;
    static StringBuilder sb = new StringBuilder();

    static int[] visit;
    public static void main(String[] args) throws IOException {
        InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        while(true){
            n = Integer.parseInt(br.readLine());
            if(n==0) break;

            rooms = new Room[n];
            visit = new int[n];
            Arrays.fill(visit, -1);
            result = false;

            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                int money = Integer.parseInt(st.nextToken());
                List<Integer> list= new ArrayList<>();
                while (true){
                    int idx = Integer.parseInt(st.nextToken());
                    if(idx==0) break;
                    list.add(idx-1);
                }
                rooms[i] = new Room(str, money, list);
            }

            //System.out.println(Arrays.toString(rooms));
            DFS(0, 0);

            if(!result)sb.append("No" + "\n");
            //System.out.println();
        }
        System.out.println(sb);
    }

    public static void DFS(int idx, int cost){
        //System.out.println("idx : " + idx + " cost : " + cost + " room.money : " + rooms[idx].money + " Roomin : " + rooms[idx].in);

        Room room = rooms[idx];
        int roomMoney = room.money;

        if(room.in.equals("L")){
            if(cost< roomMoney) cost = roomMoney;
        }else if(room.in.equals("T")){
            if(cost>= roomMoney) cost -= roomMoney;
            else{
                return;
            }
        }

        //System.out.println("cost " + cost);

        if(idx==n-1){
            //System.out.println("됨");
            result = true;
            sb.append("Yes" + "\n");
            return;
        }

        int size = room.num.size();
        //System.out.println("      size : " + size);

        for(int i=0;i<size;i++){
            visit[idx] = Math.max(visit[idx], cost);
            int Roomidx = room.num.get(i);
            //System.out.println("Roomidx : " + Roomidx);
            if(visit[Roomidx] < cost){
                DFS(Roomidx, cost);
            }
        }
    }

    public static class Room{
        String in; //방이 트롤인지 빈방인지 레프리콘인지
        int money; //트롤이면 통행료, 레프리콘이면 금화채우기
        List<Integer> num; //이어지는 방들

        public Room(String in, int money, List<Integer> num) {
            this.in = in;
            this.money = money;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "in='" + in + '\'' +
                    ", money=" + money +
                    ", num=" + num +
                    '}';
        }
    }

}
