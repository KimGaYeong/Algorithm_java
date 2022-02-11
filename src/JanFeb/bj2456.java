package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class bj2456 {
    static int[][] candidate;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer N = Integer.parseInt(br.readLine());
        candidate = new int[4][N];
        int[][] sum = new int[4][5];
        for(int i=0;i<N;i++){
            String tmp = br.readLine();
            StringTokenizer st = new StringTokenizer(tmp);
            candidate[1][i] = Integer.parseInt(st.nextToken()); //1번점수
            candidate[2][i] = Integer.parseInt(st.nextToken()); //2번점수
            candidate[3][i] = Integer.parseInt(st.nextToken()); //3번점수

            for(int j=0;j<3;j++){
                sum[j+1][4] =j+1; // 후보 번호가 몇번인지
                sum[j+1][0] += candidate[j+1][i]; //해당 후보가 몇점인지
                sum[j+1][((candidate[j+1][i])%3)+1]++; //후보별 점수 개수 세기
            }
        }

        Arrays.sort(sum, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] ==o2[0]){
                    return o2[1]-o1[1];
                }else{
                    return o2[0]-o1[0];
                }
            }
        });

        int[] arr1 = Arrays.copyOfRange(sum[0], 1,4);
        int[] arr2 = Arrays.copyOfRange(sum[1], 1,4);

        for(int[] i : sum){
            for(int in : i){
                System.out.print(in + " ");
            }
            System.out.println();
        }

        if(sum[0][0] == sum[1][0]){
            if (is(arr1, arr2)) {
                System.out.println(0 + " " + sum[0][0]);
            }else{
                System.out.println(sum[0][4] + " " + sum[0][0]);
            }
        }else{
            System.out.println(sum[0][4] + " " + sum[0][0]);
        }
    }
    private static boolean is(int[] a, int[] b){
        int cnt=0;
        for(int s=0;s<3;s++){
            if(a[s] ==b[s]) {
                cnt++;
            }
        }
        if(cnt==3) return true;
        else return false;
    }
}
