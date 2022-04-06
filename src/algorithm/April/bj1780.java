package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 각 1/9만큼의 상자 별로 가장 왼쪽 위 인덱스 찾는 기준 : 행, 열을 3으로 나눈 몫에 3 곱하기.
 */
public class bj1780 {
    static int N;
    static int[] count;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1780.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //입력
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = new int[3]; // -1, 0, 1을 저장.

        /*
        1. N, AllisSame?
        2. if false Divide & Goto_1, N/3
        3. if true Count
         */

        solve(0,0,N); //(0,0)부터 크기 N만큼 먼저 찾아보기.

        for(int i=0;i<3;i++){
            System.out.println(count[i]);
        }
    }
    public static void solve(int x, int y, int Num){
        if(AllisSame(x,y,Num)){ //모두 같은 수인지?
            //같은 수라면 개수 세기.
            int compare = map[x][y];
            if(compare==0){
                count[1]++;
            }else if(compare==1){
                count[2]++;
            }else if(compare==-1){
                count[0]++;
            }
            return;
        }

        //같은 수가 아니라면?
        int Num2 = Num/3; // 나누기 3을 한 길이만큼 돌린다.
        for(int i=x;i<x+Num;i+=Num2){
            for(int j=y;j<y+Num;j+=Num2){
                solve(i,j,Num2);
            }
        }
    }

    public static boolean AllisSame(int x, int y, int Side){
        int num = map[x][y];
        for(int i=x;i<x+Side;i++){
            for(int j=y;j<y+Side;j++){
                if(num != map[i][j]) return false;
            }
        }
        return true;
    }
}
