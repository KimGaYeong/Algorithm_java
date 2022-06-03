package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1826 {
    static OilStation[] stations;
    static PriorityQueue<OilStation> oilStations;
    static int L, P, cnt;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        oilStations = new PriorityQueue<>();
        stations = new OilStation[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int dis = Integer.parseInt(st.nextToken());
            int fu = Integer.parseInt(st.nextToken());
            stations[i] = new OilStation(dis, fu);
        }

        Arrays.sort(stations, new Comparator<OilStation>() {
            @Override
            public int compare(OilStation o1, OilStation o2) {
                if (o1.distance == o2.distance) return -1*Integer.compare(o1.fuel, o2.fuel);
                else{
                    return Integer.compare(o1.distance, o2.distance);
                }
            }
        });

        //System.out.println(Arrays.toString(stations));
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); //성경 위치부터 마을까지 거리
        P = Integer.parseInt(st.nextToken()); //맨 처음 가지고 있는 연료 양
        cnt =0;
        boolean flag = false;
        //1km를 가는데 1L의 연료가 새 나감.

        int idx =0;

        while(P<L){
            for(int i=idx;i<N;i++){ //일단 전체를 돌면서
                if(stations[i].distance <= P){//내 연료 양으로 갈 수 있는 곳 체크.
                    oilStations.add(stations[i]);
                    idx++;
                }
            }

            //System.out.println("oilStation : " + oilStations);

            if(oilStations.isEmpty()){ //갈 수 있는 곳이 없으면?
                flag = true;
                break;
            }else{ //있으면?
                P += oilStations.poll().fuel;
                cnt++;
            }

            //System.out.println("현재 내 연료! " + P);
        }

        if(flag) System.out.println("-1");
        else    System.out.println(cnt);

    }

    public static class OilStation implements Comparable<OilStation>{
        // 연료가 많은 순서로 정렬?
        int distance, fuel;

        public OilStation(int distance, int fuel) {
            this.distance = distance;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(OilStation o) {
            return -1 * Integer.compare(this.fuel, o.fuel);
        }

    }
}
