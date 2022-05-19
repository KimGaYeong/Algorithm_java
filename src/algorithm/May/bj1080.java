package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1080 {
    static int N, M, result, size;
    static int[][] mapA, mapB;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        mapA = new int[N][M];
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                mapA[i][j] = str.charAt(j)-'0';
            }
        }

        mapB = new int[N][M];
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                mapB[i][j] = str.charAt(j)-'0';
            }
        }

        if(N<3 || M<3){

            if(!isSame()) {
                System.out.println("-1");
            }else{
                System.out.println("0");
            }
            System.exit(0);
        }

        //세로로는 N-3인덱스까지, 가로로는 M-3인덱스까지

        result = 0;
        for(int i=0;i<=N-3;i++){
            for(int j=0;j<=M-3;j++){
                if(mapA[i][j] != mapB[i][j]){
                    Change(i,j);
                    result++;
                }
            }
        }

        if(!isSame()){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }

    }


    public static boolean isSame(){

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(mapA[i][j] != mapB[i][j]) return false;
            }
        }
        return true;
    }

    public static void Change(int x, int y){
        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++){
                //1은 0으로, 0은 1로
                mapA[i][j] = Math.abs(mapA[i][j]-1);
            }
        }
    }
}
