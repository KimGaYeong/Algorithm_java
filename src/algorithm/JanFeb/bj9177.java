package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
굳이 단더 두개로 만들 필요 없이 정답 글자에서 맨 앞글자만 따지면 되잖아 ?????
 */
public class bj9177 {
    static int N;
    static Queue<Character> q1;
    static Queue<Character> q2;
    static String ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int n=0;n<N;n++){
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            //첫 번째 문자열 저장.
            for(int i=0;i<st.nextToken().length();i++){
                q1.add(st.nextToken().charAt(i));
            }
            //두 번째 문자열 저장.
            for(int i=0;i<st.nextToken().length();i++){
                q1.add(st.nextToken().charAt(i));
            }
            //정답 문자열 저장.
            ans = st.nextToken();

            // 만약에 peek값이 같으면 ans에서 그 뒤 3개까지 보기.
            // ab / ac 현재
            int cnt=0;
        }
    }
}
