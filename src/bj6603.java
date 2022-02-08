import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//순서에 의미 없음. 조합.

public class bj6603 {
    static int k;
    static int[] arr;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k==0) return;
            arr = new int[k];
            visit = new boolean[k];

            for(int i=0;i<k;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            combi(0,0);
            System.out.println(sb);
            sb = new StringBuilder();
        }

    }
    public static void combi(int start, int cnt){
        if(cnt ==6){
            for(int i=0;i<k;i++){
                if(visit[i]) sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start;i<k;i++){
            visit[i] = true;
            combi(i+1, cnt+1);
            visit[i] = false;
        }

    }

}
