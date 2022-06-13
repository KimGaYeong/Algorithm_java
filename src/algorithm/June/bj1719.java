package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj1719 {
    static int n, m;
    static int[][] dis, path;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dis = new int[n][n];
        path = new int[n][n];

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i!=j) dis[i][j] = 1000000000;
            }
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            dis[from][to] = weight;
            dis[to][from] = weight;

            path[from][to] = from;
            path[to][from] = to;
        }


//        for(int i=0;i<n;i++){
//            System.out.println(Arrays.toString(dis[i]));
//        }

        floydWarshall();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) sb.append("-");
                else sb.append(path[j][i]+1);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);


                System.out.println("------------------");
                for(int i=0;i<n;i++){
                    System.out.println(Arrays.toString(path[i]));
                }
    }

    public static void floydWarshall() {

        for(int k=0;k<n;k++){ //거쳐가는 노드
            for(int i=0;i<n;i++){ //출발 노드
                for(int j=0;j<n;j++){ //도착 노드
                    if(dis[i][j] > dis[i][k]+dis[k][j]){
                        //dis[i][j] = Math.min(dis[i][k] + dis[k][j], dis[i][j]);
                        dis[i][j] = dis[i][k] + dis[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

//        System.out.println("------------------");
//        for(int i=0;i<n;i++){
//            System.out.println(Arrays.toString(dis[i]));
//        }
    }
}
