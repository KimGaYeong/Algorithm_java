package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class bj1158 {
    static LinkedList<Integer> arr;
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new LinkedList<>();
        for(int i=1;i<=N;i++){
            arr.add(i);
        }
        sb.append("<");
        while(!arr.isEmpty()){
            for(int i=0;i<K;i++){
                if(i==K-1){
                    int tmp = arr.remove();
                    if(arr.isEmpty()) sb.append(tmp + ">");
                    else sb.append(tmp + ", ");
                }else{
                    int tmp = arr.remove();
                    arr.add(tmp);
                }
            }

        }
        System.out.println(sb);
    }
}
