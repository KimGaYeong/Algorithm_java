package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 추가해야 하는 가로선 개수의 최솟값을 출력
 * 2. 정답이 3보다 큰 값이면 -1을 출력
 * 추가 개수가 0,1,2,3만 보면 됨. 작은수에서 나왔으면 더 찾을 필요 X
 *
 * 사다리 개수는 n~n+1 사이에 짝수개 존재해야함.
 */
public class bj15684 {
    static int N, M, H;
    static int[][] ladder;
    static boolean check = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H+1][N+1]; //가로 사다리와 세로 사다리가 만나는 지점
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = b+1;
            ladder[a][b+1] = b;
            //b번 세로선과 b+1번 세로선이 a번 점선에서 연결된다.
        }

        for(int i=0;i<=H;i++){
            System.out.println(Arrays.toString(ladder[i]));
        }
        int k=0;
        for(k=0;k<=3;k++){
            combination(ladder, k);
            if(check){ //연결 됐으면.
                break;
            }
            //연결됐다면 그게 최소 개수이므로 더 진행 할 필요가 없다.
        }
        System.out.println(k==4? -1:k);

    }

    public static boolean isCheck(int[][] lad){
        for(int i=1;i<=N;i++){ //가로줄
            int k =i; // 모든 사다리의 맨 위쪽은 사다리 번호인 i부터 시작.
            for(int j=1;j<=H;j++){
                if(lad[j][k] !=0) k= lad[j][k]; //연결된 곳으로 이동
            }
            if(k != i) return false; //다 내려왔을 때도 i여야 true.
        }
        return true; //i에서 출발하는 모든 k들의 최종 목적지가 i여야 true!
    }

    //사다리를 추가하는 최소 개수를 찾아야 하므로,
    // 사다리를 작은 수부터 최대 사다리 추가 개수인 3개까지 추가한다.
    public static void combination(int[][] lad, int ladcnt){
        if(check){ //연결 됐으면
            return;
        }

        //사다리 추가 개수별로 ischeck를 통해 자기 자리로 내려가는지를 확인한다.
        if(ladcnt==0){
            check = isCheck(lad);
            return;
        }

        //사다리를 만들 수 있는 경우는?

        for(int i=1;i<=H;i++){
            for(int j=1;j<N;j++){
                //즉, 가로선i의 세로선 j와 j+1는 연결되지 않았다는 뜻.
                // 그래야 사다리를 추가할 수 있다.
                if(lad[i][j] ==0 && lad[i][j+1]==0){
                    //사다리 추가
                    lad[i][j] = j+1;
                    lad[i][j+1] = j;

                    combination(lad, ladcnt-1);
                    if(check) return; //백트래킹.
                    //사다리 되돌리기
                    lad[i][j] = 0;
                    lad[i][j+1] =0;
                }
            }
        }
    }
}
