package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class bj16935 {
    static int R;
    static int[] Rarr;
    static List<List<Integer>> arr;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Rarr = new int[R];
        arr = new LinkedList<>();
        for(int n=0;n<N;n++){
            List<Integer> tmp = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int m=0;m<M;m++){
                tmp.add(Integer.parseInt(st.nextToken()));
            }
            arr.add(tmp);
        }
        st = new StringTokenizer(br.readLine());
        for(int r=0;r<R;r++) {
            Rarr[r] = Integer.parseInt(st.nextToken());
        }
        for(int r=0;r<R;r++){
            sb = new StringBuilder();
            int row = arr.size();
            int col = arr.get(0).size();
            if(Rarr[r] ==1){
                List<List<Integer>> arr2 = new LinkedList<>();
                List<Integer> tmp = new LinkedList<>();
                for(int i=0;i<row;i++){
                    tmp = arr.get(row-i-1);
                    arr2.add(tmp);
                }
                arr = arr2;
            }
            else if(Rarr[r] ==2){
                List<List<Integer>> arr2 = new LinkedList<>();
                List<Integer> tmp;
                for(int i=0;i<row;i++){
                    tmp = new LinkedList<>();
                    for(int j=0;j<col;j++){
                        tmp.add(arr.get(i).get(col-j-1));
                    }
                    arr2.add(i, tmp);
                }
                arr = arr2;
            }
            else if(Rarr[r] ==3){
                List<Integer> tmp = new LinkedList<>();
                List<List<Integer>> arr2 = new LinkedList<>();
                for(int i=0;i<col;i++){
                    tmp = new LinkedList<>();
                    for(int j=row-1;j>=0;j--){
                        tmp.add(arr.get(j).get(i));
                    }
                    arr2.add(tmp);
                }
                arr = arr2;
            }
            else if(Rarr[r] ==4){
                List<Integer> tmp = new LinkedList<>();
                List<List<Integer>> arr2 = new LinkedList<>();
                for(int i=col-1;i>=0;i--){
                    tmp = new LinkedList<>();
                    for(int j=0;j<row;j++){
                        tmp.add(arr.get(j).get(i));
                    }
                    arr2.add(tmp);
                }
                arr = arr2;
            }
            else if(Rarr[r] ==5){
                rollR();
            }
            else if(Rarr[r] ==6){
                rollL();
            }

            //System.out.println("------------------");
        }
        int rr = arr.size();
        int cc = arr.get(0).size();
        for(int i=0;i<rr;i++){
            for(int j=0;j<cc;j++){
                sb.append(arr.get(i).get(j) + " ");
            }
            if(i!=rr-1) sb.append("\n");
        }
        System.out.println(sb);

    }

    public static void rollR(){
        int r = arr.size();
        int c = arr.get(0).size();
        int hr = r/2;
        int hc = c/2;
        int[][] arr2 = new int[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                int tmp = arr.get(i).get(j);
                if(i<hr && j>=hc){ //1사분면->4
                    arr2[i+hr][j] = tmp;
                }else if(i<hr && j<hc){ //2사분면->1
                    arr2[i][j+hc] = tmp;
                }else if(i>=hr && j<hc){ //3사분면->2
                    arr2[i-hr][j] = tmp;
                }else if(i>=hr && j>=hc){ //4사분면->3
                    arr2[i][j-hc] = tmp;
                }
            }
        }

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                arr.get(i).set(j, arr2[i][j]);
            }
        }
    }

    public static void rollL(){
        int r = arr.size();
        int c = arr.get(0).size();
        int hr = r/2;
        int hc = c/2;
        int[][] arr2 = new int[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                int tmp = arr.get(i).get(j);
                if(i<hr && j>=hc){ //1사분면->2
                    arr2[i][j-hc] = tmp;
                }else if(i<hr && j<hc){ //2사분면->3
                    arr2[i+hr][j] = tmp;
                }else if(i>=hr && j<hc){ //3사분면->4
                    arr2[i][j+hc] = tmp;
                }else if(i>=hr && j>=hc){ //4사분면->1
                    arr2[i-hr][j] = tmp;
                }
            }
        }

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                arr.get(i).set(j, arr2[i][j]);
            }
        }
    }
}
