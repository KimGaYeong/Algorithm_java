package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj3687 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj2869.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            int s = Integer.parseInt(br.readLine());
            sb.append(makeMin(s) + " ");
            sb.append(makeMax(s) + "\n");
        }
        System.out.println(sb);
    }

    public static long makeMin(int s){
        StringBuilder sb = new StringBuilder();
        // DP[i] = 성냥 i개로 만들 수 있는 최고 작은 수.
        // 점화식 : DP[i개-한번빼보는개수]+ 한번 빼보는 개수로 최소로 만들 수 잇는 수
        long[] DP = new long[s+1];
        Arrays.fill(DP, Long.MAX_VALUE);
        //0~9까지 최소 개수를 일단 저장함. 점화식은 10부터 세웠는데 s가 몇일지 몰라서...
        long[] tempDP = {0,0,1,7,4,2,6,8,10,18};
        /*
        대충 한번 DP[8] 이상부터는 앞에 1이 들어간다. 그말인 즉슨 1은 이제부터
        무조건 포함되어야 된다는 말이니까 점화식을 DP[10]부터 만들거다. 헷갈리니까
         */
        if(s<10){
            //System.out.println(tempDP[s]);
            return tempDP[s];
        }else{
            for(int i=0;i<=9;i++){
                DP[i] = tempDP[i];
            }
            tempDP[6] = 0;
            for(int i=9;i<=s;i++){
                for(int j=2;j<8;j++){ //어짜피 8부터는 앞에 1되니까 필요없음
                    long tmp = Long.parseLong(String.valueOf(DP[i-j])+String.valueOf(tempDP[j]));
                    DP[i] = Math.min(DP[i], tmp);
                }
                //System.out.println(DP[i]);
            }
            return DP[s];
        }
    }

    public static String makeMax(int s){
        StringBuilder sb = new StringBuilder();
        if(s%2==1){ //홀수일때는 s = 2*k+1이라고 했을 때 7 + 1...1(k-1)번
            sb.append(7);
            for(int i=0;i<(s/2)-1;i++){
                sb.append(1);
            }
        }else{
            for(int i=0;i<(s/2);i++){
                sb.append(1);
            }
        }
        return sb.toString();
    }
}
