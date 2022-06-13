package algorithm.June;

import java.util.*;

public class pg주차요금계산 {
    public static void main(String[] args) {
        int[] fees = new int[]{180, 5000, 10, 600};
        String[] records = new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT",
                "07:59 5961 OUT", "07:59 0148 IN",
                "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

        System.out.println(Arrays.toString(solution(fees, records)));
    }

    public static int[] solution(int[] fees, String[] records) {
        List<Park> cars = new ArrayList<>();
        StringTokenizer st;
        Park[] parks = new Park[records.length];
        for(int i=0;i<records.length;i++){
            st = new StringTokenizer(records[i]);
            String t = st.nextToken();
            String n = st.nextToken();
            String io = st.nextToken();
            parks[i] = new Park(t,n,io);

        }

        Arrays.sort(parks);
        System.out.println(Arrays.toString(parks));


        for(int i=0;i< parks.length-1;i++){
            if(parks[i].IO.equals("IN") && Objects.equals(parks[i].num, parks[i + 1].num)){
                int after = change(parks[i+1].time);
                int before = change(parks[i].time);
                int time = after-before;
                cars.add(new Park(parks[i].num, time));
            }else if(parks[i].IO.equals("IN") && !Objects.equals(parks[i].num, parks[i + 1].num)){
                int after = 23*60 + 59;
                int before = change(parks[i].time);
                int time = after-before;
                cars.add(new Park(parks[i].num, time));
            }
        }

        if(parks[parks.length-1].IO.equals("IN")){
            int after = 23*60 + 59;
            int before = change(parks[parks.length-1].time);
            int time = after-before;
            cars.add(new Park(parks[parks.length-1].num, time));
        }

//        for(int i=0;i< cars.size();i++){
//            System.out.println(cars.get(i).num + " : " + cars.get(i).price);
//        }
//        System.out.println();

        List<Park> ans = new ArrayList<>();
        for(Park p : cars){
            if(ans.contains(new Park(p.num))){
                int idx = ans.indexOf(new Park(p.num));
                int pay = ans.get(idx).price;
                ans.set(idx, new Park(p.num, p.price+pay));
            }else{
                ans.add(new Park(p.num, p.price));
            }
        }

//        for(int i=0;i< ans.size();i++){
//            System.out.println(ans.get(i).num + " : " + ans.get(i).price);
//        }

        int[] answer = new int[ans.size()];

        for(int i=0;i<ans.size();i++){
            int p = ans.get(i).price;
            if( p<= fees[0]){
                answer[i] = fees[1];
            }else{
                double a = Math.ceil((double)(p-fees[0])/(double)fees[2]);
                answer[i] = fees[1] + (int)(a*fees[3]);
            }
        }

        return answer;
    }

    private static int change(String str){
        String tmp = "", tmp2 = "";
        int t = 0, t2 = 0;
        tmp = str.substring(0,2); //시간
        t = Integer.parseInt(tmp);
        tmp2 = str.substring(3); //분
        t2 = Integer.parseInt(tmp2);

        return t*60 + t2;
    }
    private static class Park implements Comparable<Park>{
        String time, num, IO;
        int price;

        public Park(String time, String num, String IO) {
            this.time = time;
            this.num = num;
            this.IO = IO;
        }

        public Park(String num, int price) {
            this.num = num;
            this.price = price;
        }

        public Park(String num) {
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Park park = (Park) o;
            return Objects.equals(num, park.num);
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
        }

        @Override
        public int compareTo(Park o) {
            if(this.num.equals(o.num)) return this.time.compareTo(o.time);
            else    return this.num.compareTo(o.num);
        }

        @Override
        public String toString() {
            return "Park{" +
                    "time='" + time + '\'' +
                    ", num='" + num + '\'' +
                    ", IO='" + IO + '\'' +
                    '}';
        }
    }
}
