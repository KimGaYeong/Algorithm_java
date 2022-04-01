package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1525_2 {
    static int[] del = {-1, 1, -3, 3};
    static String map = "";
    static boolean check;
    static String answer = "123456780";
    static int cnt;
    static Queue<String> queue;
    static HashSet<String> hashSet;
    public static void main(String[] args) throws IOException {

        InputStream input = bj1525_2.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cnt =0;
        //모든 배열을 String으로 만들기
        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                String tmp = st.nextToken();
                map += tmp;
            }
        }

        queue = new LinkedList<>();
        hashSet = new HashSet<>();
        Solve();

        if(check) System.out.println(cnt);
        else System.out.println("-1");
    }


    public static String swap(String str, int a, int b){
        StringBuilder sb = new StringBuilder(str);
        char aidx = sb.charAt(a);
        char bidx = sb.charAt(b);

        sb.setCharAt(a, bidx);
        sb.setCharAt(b, aidx);

        return sb.toString();
    }


    public static void Solve(){
        queue.add(map);
        hashSet.add(map);

        loop : while(!queue.isEmpty()){
            int size = queue.size();
            lop : while(size>0){
                String str = queue.poll();
                int cx = str.indexOf("0");
                if(str.equals(answer)){
                    check = true;
                    break lop;
                }
                for(int d=0;d<4;d++){
                    int nx = cx + del[d];

                    if(nx < 0 || nx >= 9 || (d==1 && (cx+1)%3 ==0) || (d==0 && (cx)%3 ==0)) continue;
                    else{
                        String next = swap(str, cx, nx);
                        if(!hashSet.contains(next)){
                            hashSet.add(next);
                            queue.add(next);
                        }
                    }
                }
                size--;
            }
            if(check) break loop;
            cnt++;
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