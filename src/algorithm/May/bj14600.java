package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14600 {
    static int K, PK;
    static int[][] map;
    static int x, y;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        PK = (int) Math.pow(2, K);
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken())-1;
        y = Integer.parseInt(st.nextToken())-1;


        if(K==1){
            map = new int[][]{{1,1},{1,1}};
            map[PK-1-y][x] = -1;
        }else{
            //일단 다 채워놓고
            map = new int[][]{{4, 4, 5, 5}, {4, 3, 3, 5}, {1, 3, 3, 2}, {1, 1, 2, 2}};
            //x,y가 속한 곳이 어딘지 찾기.
            if(x>0 && x<3 && y>0 && y<3){ //가운데 부분에 있냐?
                //원래 : x:2,y:1 내꺼 : 2,2 PK : 4
                map[PK-1-y][x] = -1;
            }else{
                int num = map[PK-1-y][x];
                //11, 12, 21, 22
                if(num==1) map[2][1] = 1;
                else if(num==2) map[2][2] = 2;
                else if(num==4) map[1][1] = 4;
                else if(num==5) map[1][2] = 5;
                map[PK-1-y][x] = -1;
            }
        }
        print();
        System.out.println(sb);

    }

    public static void print(){
        for(int i=0;i<PK;i++){
            for(int j=0;j<PK;j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
    }
}
