package 코딩테스트.SKtest;

import java.util.Arrays;

public class sol4 {
    static long[][] ans;
    static int[][] arr;
    static boolean[] visit;
    static int result=0;
    public static void main(String[] args) {
        int n=5;
        int[][] edges = {{0,1},{0,2},{1,3},{1,4}};

        System.out.println(solution(n, edges));
    }
    public static long solution(int n, int[][] edges) {
        ans = new long[n][n];
        long answer = 0;
        arr = new int[n][n];
        visit = new boolean[n];

        for(int i=0;i<edges.length;i++){
            arr[edges[i][0]][edges[i][1]] =1;
            arr[edges[i][1]][edges[i][0]] =1;
        }

        for(int i=0;i<edges.length;i++){
            for(int j=0;j<edges.length;j++){
                visit = new boolean[n];
                result =0;
                if(i>j){
                    DFS(i,j,n);
                }
            }
        }

        for(int i=0;i<ans.length;i++){
            System.out.println(Arrays.toString(ans[i]));

        }
        return answer;

    }

    public static void DFS(int v, int e, int n){
        visit[v] = true;
        result+=1;

        if(v==e){
            ans[v][e] = result;
            ans[e][v] = result;

            result=0;
            return;
        }

        for(int i=0;i<n;i++){
            if(arr[v][i] ==1 && !visit[i]){
                DFS(i,e, n);
                visit[i] = false;
            }
        }

        return;
    }
}
