package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2630 {
    static int N;
    static int[][] arr;
    static int blue, white;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        //입력
        arr = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 하얀색 : 0, 파란색 : 1 , N<=2^7
        blue=0; white =0;
        Rectangle(0,0, N);
        System.out.println(white + "\n" + blue );
    }
    public static void Rectangle(int xidx, int yidx, int num) { //전체를 찾고


        int half = num/2;
        //안되면
        int count=0;
        int color = arr[xidx][yidx];
        for(int i=xidx;i<xidx+num;i++){
            for(int j=yidx;j<yidx+num;j++){
                if(arr[i][j] == color){
                    count++;
                }
            }
        }
        if(count == num*num){
            if(color == 0) white +=1;
            else blue +=1;
            return;
        }else{
            Rectangle(xidx, yidx, half); // 왼쪽 위
            Rectangle(xidx, yidx+half, half); // 오른쪽 위
            Rectangle(xidx+half, yidx, half); // 왼쪽 아래
            Rectangle(xidx+half, yidx+half, half); // 오른쪽 아래
        }

        //재귀.
    }
}
