package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14888 {
    static int N, min, max;
    static int[] map;
    static int[] op;
    public static void main(String[] args) throws IOException {
        InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();


        N = Integer.parseInt(br.readLine());
        map = new int[N];
        op = new int[4];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            op[i] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        DFS(map[0],1);
        sb.append(max + "\n" + min);
        System.out.println(sb);
    }

    public static void DFS(int num, int idx){
        //base part
        if(idx ==N){
            min = Math.min(num, min);
            max = Math.max(num, max);
            return;
        }

        //inductive part
        for(int i=0;i<4;i++){
            if(op[i]>0){
                op[i]-=1;
                if(i ==0){
                    DFS(num + map[idx], idx +1);
                }
                else if(i ==1){
                    DFS(num - map[idx], idx +1);
                }
                else if(i ==2){
                    DFS(num * map[idx], idx +1);
                }
                else if(i ==3){
                    DFS((int)(num / map[idx]), idx +1);
                }
                op[i]+=1;
            }
        }
    }

}
