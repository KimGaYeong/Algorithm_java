package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class bj13422 {
    static int N, M, cnt;
    static int K, sum;
    static int[] map;
    public static void main(String[] args)  throws IOException {
        InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N+M];

            st = new StringTokenizer(br.readLine());
            sum = 0; //총합 저장
            for(int i=0;i<N;i++){
                map[i] = Integer.parseInt(st.nextToken());
                sum += map[i];
            }
            for(int i=0;i<M;i++){
                map[N+i] = map[i];
            }

            //System.out.println(Arrays.toString(map));
            cnt=0; //정답카운트.

            if(N==M){
                if(sum<K) cnt =1;
                else{
                    cnt =0;
                }
            }else{
                solve();

            }

            sb.append(cnt + "\n");
        }
        System.out.println(sb);
    }

    public static void solve(){
        int start =0; int end =M-1; //0번부터 M-1번집까지
        int tmp = 0;

        //초기 tmp값 저장
        for(int i=0;i<M;i++){
            tmp += map[i];
        }

        while(true){
            if(start==N) break;

            if(tmp<K) cnt++;
            tmp-= map[start];
            tmp+= map[end+1];

            start++;
            end++;
        }
    }
}
