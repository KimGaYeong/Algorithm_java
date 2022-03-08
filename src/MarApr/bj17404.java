package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj17404 {
    static int N, result;
    static int[][] house, DP;
    public static void main(String[] args) throws IOException {
        InputStream input = bj17404.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        house = new int[N][3];
        DP = new int[N][3];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        /* 1집은 N집과 절대 같으면 안된다.*/
        result = Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i==j){
                    //시작 집을 뭘로 하냐에 따라 다름.
                    DP[0][i] = house[0][i];
                }else{
                    //하나 고정시키기.
                    DP[0][i] = 1000001;
                }
            }

            for(int j=1;j<N;j++){
                // 다음 집과 겹치면 안됨.
                DP[j][0] = Math.min(DP[j-1][1], DP[j-1][2]) + house[j][0];
                DP[j][1] = Math.min(DP[j-1][0], DP[j-1][2]) + house[j][1];
                DP[j][2] = Math.min(DP[j-1][0], DP[j-1][1]) + house[j][2];
            }

            for(int j=0;j<3;j++){
                if(i!=j){ //1번집과 N번집은 색이 달라야 함.
                    result = Math.min(result, DP[N-1][j]);
                }
            }
        }
        System.out.println(result);

    }
}

/**
 * 빨 -> (초 아님 파)
 */