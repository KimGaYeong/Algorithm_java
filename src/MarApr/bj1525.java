package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1525 {
    static int[] del = {-1, 1, -3, 3};
    static String map = "";
    static boolean check;
    static String answer = "123456780";
    static int cnt;
    static Queue<String> queue;
    static HashSet<String> hashSet;
    public static void main(String[] args) throws IOException {

        InputStream input = bj1525.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //모든 배열을 String으로 만들기
        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                String tmp = st.nextToken();
                map += tmp;
            }
        }

        System.out.println(map);
        queue = new LinkedList<>();
        hashSet = new HashSet<>();
        Solve();

        System.out.println(cnt);
    }


    public static String swap(String str, int a, int b){
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(a, str.charAt(b));
        char tmp = str.charAt(a);
        sb.setCharAt(b,tmp);
        return sb.toString();
    }


    public static void Solve(){
        queue.add(map);
        hashSet.add(map);
        int size = queue.size();

        while(!queue.isEmpty()){
            for(int i=0;i<size;i++){
                String tmp = queue.poll();
                int zero = tmp.indexOf("0");
                if(tmp.equals(answer)) {
                    break;
                }else{
                    for(int d=0;d<4;d++){
                        int nx = zero + del[d];
                        if(isIn(nx)){
                            String next = swap(tmp, zero, d);
                            if(!hashSet.contains(next)){
                                hashSet.add(next);
                                queue.add(next);
                            }
                        }
                    }
                }
                cnt++;
            }

            if(check){
                break;
            }
        }

    }

    public static boolean isIn(int x){
        return x>=0 && x<9;
    }

}

/*
 * 1. 0을 기준으로 주변을 탐색한다. 기존 map( 목표 map)을 string으로 저장해놓고 map과 다른 글자 중
 * 현재 0이랑 가장 가까운 애를 (붙어있는 애를?) 찾아서 바꾼다. 는 느낌
 *
 * 2. 무지성으로 0을 옮겨대서 (어짜피 0이랑 바꿔지는거니까) 막 계속 될 때 까지 바꿔대서 최소 횟수를 찾으면 될까..... (메모리 초과)
 *
 * 3. 처음부터 string으로만 비교한다.
 */