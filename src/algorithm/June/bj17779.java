package algorithm.June;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj17779 {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 987654321;
        for(int x=1;x<N;x++){
            for(int y=1;y<N;y++){

                for(int d1=1;d1<N;d1++){
                    for(int d2=1;d2<N;d2++){
                        //1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N
                        if(x+d1+d2<=N && 1<=y-d1 && y+d2<=N){
                           result = Math.min(result, solution(map, x, y, d1, d2));
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }

    public static int solution(int[][] map, int x, int y, int d1, int d2){

        boolean[][] isFive = new boolean[N+1][N+1]; //5번 구역 표시
        int[] sum = new int[6];

        int size = 1; //마름모 가로 시작 크기
        int col = y; // 마름모 시작 열

        for(int r=0;r<=d1+d2;r++){ // 마름모 돌면서 5번 선거구 true로만들기
            for(int c=0;c<size;c++){
                isFive[x+r][col+c] = true;
            }

            //행 조절
            if(r<d1) col--; //행이 d1보다 작으면 시작 열 줄여주고
            else col++; //아님 늘려준다.

            if(r<d1 && r<d2) size+=2; //마름모 커지냐 작아지냐에 따라 크기 만들어주기. 커지는중
            else if(r>=d1 && r>=d2) size-=2; //작아지는중
        }

        for(int r=1;r<=N;r++){
            for(int c=1;c<=N;c++){
                //선거구 계산
                if(isFive[r][c]) sum[5] += map[r][c];
                else{
                    int A = whatArea(r,c,x,y,d1,d2);
                    sum[A] += map[r][c];
                }
            }
        }

        Arrays.sort(sum);
        return sum[5]-sum[1];
    }

    public static int whatArea(int r, int c, int x, int y, int d1, int d2){

        /*
        1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
        2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
        3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
        4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
         */
        if(1<=r && r< x+d1 && 1<=c && c<=y) return 1;
        else if(1<=r && r<= x+d2 && y<c && c<=N) return 2;
        else if(x+d1<=r && r<=N && 1<=c && c<y-(d1-d2)) return 3;
        else return 4;
    }
}
