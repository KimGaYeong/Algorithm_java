package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class bj2133 {
    static int N, result;
    static int[][] map;
    static Set<String> set;
    public static void main(String[] args)throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        if(N%2 ==1){
            System.out.println("0");
            System.exit(0);
        }
        map = new int[3][N];

        set = new HashSet<>();
        result =0;
        DFS(map, 0);
        System.out.println(result);
    }

    public static void DFS(int[][] arr, int cnt){
        if(cnt==(3*N/2)){
            String str = "";
            for(int i=0;i<3;i++){
                str += Arrays.toString(arr[i]);
            }
            if(!set.contains(str)){
                result++;
                set.add(str);
            }
            return;
        }

        for(int i=0;i<3;i++){
            for(int j=0;j<N;j++){
                if(j+1<N && arr[i][j]==0 && arr[i][j+1]==0){
                    arr[i][j] =1;
                    arr[i][j+1]=1;
                    DFS(arr, cnt+1);
                    arr[i][j] =0;
                    arr[i][j+1]=0;
                }
                if(i+1<3 && arr[i][j]==0 && arr[i+1][j]==0){
                    arr[i][j] =2;
                    arr[i+1][j]=2;
                    DFS(arr, cnt+1);
                    arr[i][j] =0;
                    arr[i+1][j]=0;
                }
            }
        }
        return;
    }
}
