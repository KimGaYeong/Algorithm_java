package algorithm.MarApr;

import java.util.*;

public class pg신고결과받기 {
    public static void main(String[] args) {
        String[] id_list = {"con", "ryan"};
        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k =3;

        System.out.println(Arrays.toString(solution(id_list, report, k)));
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        StringTokenizer st;
        HashSet<Index> hashSet = new HashSet<>();

        int[] answer = new int[id_list.length];
        ArrayList<Kakao> kakaos = new ArrayList<>();
        int[][] singo = new int[id_list.length][id_list.length];

        //카카오 애들 입력
        for(int i=0;i< id_list.length;i++){
            kakaos.add(new Kakao(id_list[i], i, 0, false));
            //System.out.println(kakaos.get(i));
        }

        for(int i=0;i<report.length;i++){
            st = new StringTokenizer(report[i]);
            String from = st.nextToken();
            String to = st.nextToken();
            //from이 to를 신고했음
            int fromidx = kakaos.indexOf(new Kakao(from));
            int toidx = kakaos.indexOf(new Kakao(to));

            Index tmp = new Index(fromidx, toidx);
            if(hashSet.contains(tmp)){
                continue;
            }else{
                hashSet.add(tmp);
                singo[fromidx][toidx]+=1;
                kakaos.get(toidx).cnt +=1;
            }
            //System.out.println(hashSet);

        }


        for(int i=0;i< id_list.length;i++){
            if(kakaos.get(i).cnt >=k){
                kakaos.get(i).bye = true;
            }
        }

        for(int i=0;i<id_list.length;i++){
            for(int j=0;j<id_list.length;j++){
                if(singo[i][j] >0 && kakaos.get(j).bye){
                    answer[i] +=1;
                }
            }
        }


        return answer;
    }

    public static class Index{
        int from;
        int to;

        public Index(int from, int to){
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Index index = (Index) o;
            return from == index.from && to == index.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "Index{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }
    public static class Kakao{
        String name;
        int idx;
        int cnt;
        boolean bye;

        public Kakao(String name) {
            this.name = name;
        }

        public Kakao(String name, int idx, int cnt, boolean bye) {
            this.name = name;
            this.idx = idx;
            this.cnt = cnt;
            this.bye = bye;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Kakao kakao = (Kakao) o;
            return name.equals(kakao.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Kakao{" +
                    "name='" + name + '\'' +
                    ", idx=" + idx +
                    ", cnt=" + cnt +
                    '}';
        }
    }

}
