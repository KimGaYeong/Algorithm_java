package algorithm.April;

import java.util.*;

/*
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다.
노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때,
 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
 */
public class pg_베스트앨범 {
    public static void main(String[] args) {

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] result = solution(genres, plays);
        System.out.println(Arrays.toString(result));
    }

    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        ArrayList<Integer> answers = new ArrayList<>();
        HashMap<String,Integer> hashMap = new HashMap<>();
        for(int i = 0 ; i < genres.length;i++){
            if(hashMap.containsKey(genres[i])){
                hashMap.put(genres[i],hashMap.get(genres[i])+plays[i]);
            }else{
                hashMap.put(genres[i],plays[i]);
            }
        }
        //System.out.println(hashMap);

        ArrayList<String> arr = new ArrayList<>(hashMap.keySet());

        //System.out.println(arr);

        Collections.sort(arr, (o1, o2) -> (hashMap.get(o2).compareTo(hashMap.get(o1))));

        for(String s : arr){
            HashMap<Integer,Integer> tmp = new HashMap<>();
            for(int i = 0 ; i < genres.length;i++ ){
                if(s.equals(genres[i])){
                    tmp.put(i,plays[i]);
                }
            }
            ArrayList<Integer> keyList = new ArrayList<>(tmp.keySet());

            //System.out.println(keyList);
            Collections.sort(keyList,(o1, o2) -> (tmp.get(o2).compareTo(tmp.get(o1))));
            System.out.println(keyList);

            int cnt =0;
            for(Integer k: keyList){
                if(cnt>1){
                    break;
                }
                answers.add(k);
                cnt++;
            }
        }
        answer = new int[answers.size()];
        for(int i = 0 ; i < answers.size();i++){
            answer[i]=answers.get(i);
        }
        return answer;
    }

}
