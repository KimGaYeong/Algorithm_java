package 코딩테스트.SKtest;

import java.util.Arrays;

public class sol2 {
    public static void main(String[] args) {
        int n=5;
        int[][] result = {{1,2,3,4,1},{4,5,6,5,2},{3,6,7,6,3},{2,5,6,5,4},{1,4,3,2,1}};
        boolean clockwise = true;

        int[][] re=  solution(n,clockwise);
        for(int i=0;i< result.length;i++){
            System.out.println(Arrays.toString(re[i]));
        }
    }

    public static int[][] solution(int n, boolean clockwise) {
        int[][] ans = new int[n][n];
        int num = 1;
        int first = 0;
        int last = n-1;

        while (true){
            ans[first][last] = num;
            ans[last][first] = num;
            ans[first][first] = num;
            ans[last][last] = num;

            int midnum = ((num+1)+(last-first-2))-(num+1)+1;

            if(midnum<=0) break;

            int[] mid = new int[midnum];

            if(!clockwise){ //반시계
                int tmp = num+1;
                for(int i=0;i<midnum;i++){
                    mid[i] = tmp;
                    tmp++;
                }
                num = tmp;
            }else{ //시계
                int tmp = num+midnum;
                for(int i=0;i<midnum;i++){
                    mid[i] = tmp;
                    tmp--;
                }
                num = tmp;
            }

            int k=0;
            for(int i=first+1;i<=last-1;i++){
                ans[i][first] = mid[k];
                k++;
            }

            k=0;
            for(int i=first+1;i<=last-1;i++){
                ans[last][i] = mid[k];
                k++;
            }

            k=0;
            for(int i=last-1;i>=first+1;i--){
                ans[i][last] = mid[k];
                k++;
            }

            k=0;
            for(int i=last-1;i>=first+1;i--){
                ans[first][i] = mid[k];
                k++;
            }

            first +=1;
            last -=1;
            if(!clockwise){
                num = mid[k-1]+1;
            }else {
                num = mid[0]+1;
            }
        }

        return ans;
    }
}
