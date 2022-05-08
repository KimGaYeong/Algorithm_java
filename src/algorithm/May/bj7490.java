package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj7490 {
    static int N;
    static int[] arr;
    static List<String> stringlist;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){

            N = Integer.parseInt(br.readLine());
            arr=  new int[N];

            for(int i=1;i<=N;i++){
                arr[i-1] = i;
            }

            stringlist = new LinkedList<>();
            DFS(arr[0], String.valueOf(arr[0]), 1);
            DFS((arr[0]*10+arr[1]), arr[0] + " " + arr[1], 2);

            Collections.sort(stringlist);
            for(String s : stringlist){
                sb.append(s);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void DFS(int num, String str, int cnt){

        if(cnt==N){
            if(num==0){
                stringlist.add(str + "\n");
            }
            return;
        }

        DFS(num + arr[cnt], str + "+" + arr[cnt], cnt+1);
        DFS(num - arr[cnt], str + "-" + arr[cnt], cnt+1);
        if(cnt+2<=N){
            DFS(num + (arr[cnt]*10 + arr[cnt+1]), str + "+" + arr[cnt] + " " + arr[cnt+1], cnt+2);
            DFS(num - (arr[cnt]*10 + arr[cnt+1]), str + "-" + arr[cnt] + " " + arr[cnt+1], cnt+2);
        }
    }

}

/*
1+2-3

1+2-3+4-5-6+7
1+2-3-4+5+6-7
1-2 3+4+5+6+7
1-2 3-4 5+6 7
1-2+3+4-5+6-7
1-2-3-4-5+6+7

1+2-3

1+2-3+4-5-6+7
1+2-3-4+5+6-7
1-2 3+4+5+6+7
1-2 3-4 5+6 7
1-2+3+4-5+6-7
1-2-3-4-5+6+7
 */