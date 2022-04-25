package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj17829 {
    static int N;
    static int[][] map;

    public static void main(String[] args)  throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //0, 2, 4, 6
        int[][] arr = {};
        while(true){
            if(N==1) break;

            arr = DFS(N);
            map = arr;

            N /=2;
        }

        System.out.println(arr[0][0]);
    }

    public static int[][] DFS(int n){

        PriorityQueue<Integer> pq;
        int[][] NewArr = new int[n/2][n/2];

        for(int i=0;i<n;i+=2){
            for(int j=0;j<n;j+=2){
                pq = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
                pq.add(map[i][j]);
                pq.add(map[i][j+1]);
                pq.add(map[i+1][j]);
                pq.add(map[i+1][j+1]);
                //System.out.println(pq);

                pq.poll();
                //System.out.println(i + " " + j);
                NewArr[i/2][j/2] = pq.poll();
            }
        }

        return NewArr;
    }
}
