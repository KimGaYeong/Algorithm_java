package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

//순서가 의미가 있기 때문에 순열. 선택만 하게 되면(순서가 상관 없으면) 조합이다.
//얘는 순열~

//
public class bj5568 {
    static int N, K;
    static int[] result;
    static int[] card;
    static Set<String> set;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        card = new int[N];
        result = new int[N];
        visit = new boolean[N];
        set = new HashSet<>();
        for(int i=0;i<N;i++){
            card[i] = Integer.parseInt(br.readLine());
        }
        combi(0,0);
        System.out.println(set.size());
    }

    public static void combi(int start, int cnt){
        if(cnt ==K){
            sb = new StringBuilder();
            for(int i=0;i<K;i++){
                sb.append(result[i]);
            }
            set.add(sb.toString());
            return;
        }

        for(int i=0;i<N;i++){
            if(!visit[i]){
                visit[i] = true;
                result[cnt] = card[i];
                combi(start, cnt+1);
                visit[i] = false;
            }
        }
    }

}
