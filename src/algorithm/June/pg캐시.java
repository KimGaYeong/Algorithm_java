package algorithm.June;

import java.util.ArrayDeque;
import java.util.Deque;

public class pg캐시 {
    public static void main(String[] args) {
        int cacheSize = 2;
        String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution(cacheSize, cities));
    }

    public static int solution(int cacheSize, String[] cities) {
        int time = 0;
        Deque<String> cache = new ArrayDeque<>();

        for(String s : cities){
            s = s.toUpperCase();
            //System.out.println(s);
            //이미존재 hit 캐시에 없음 miss

            if(cache.contains(s)){
                time+=1;
                cache.remove(s);
                cache.add(s);
            }else{
                time+=5;
                cache.add(s);
                if(cache.size()== cacheSize+1){
                    cache.pollFirst();
                }
            }
        }
        return time;
    }
}
