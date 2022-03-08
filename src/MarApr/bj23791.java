package MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj23791 {
    static int[] A, B;
    static boolean han, yang;
    public static void main(String[] args) throws IOException {
        InputStream input = bj23791.class.getResourceAsStream("input.txt");
        System.setIn(input);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        int Q = Integer.parseInt(br.readLine());

        for(int q=0;q<Q;q++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int cnt = bs(1, Integer.MAX_VALUE, i, j, k);

            sb.append(han?1:2).append(" " + cnt + "\n");
        }

        System.out.println(sb);
    }

    //a : 한식, b : 양식
    public static int bs(int start, int end, int hend, int yend, int k){

        while (start <= end){
            int mid = (int)(((long)start + (long)end) / 2);

            han = true;
            yang = true;

            //한식에서의 target idx
            int hidx = Arrays.binarySearch(A, 0, hend, mid);
            if(hidx <0){ //존재하지 않을 경우 들어갈 자리
                han = false; //존재안해
                hidx = -(hidx+1); //들어갈 자리
            }else{ //만약 들어있다면 idx가 아닌 몇번째인지.
                hidx++;
            }

            //양식에서의 target idx찾기
            int yidx = Arrays.binarySearch(B, 0, yend, mid);
            if(yidx <0){
                yang = false; //존재안해
                yidx = -(yidx+1);
            }else{
                yidx++;
            }

            // 한식에서의 순서와 양식에서의 순서를 합하면 총 한식, 양식을 통틀어 센 순서와 같다.
            int order = hidx + yidx;

            if(order ==k && (han || yang)){
                if(han) return hidx; //한식에 존재하면 순서 리턴
                else return yidx; // 양식에 존재하면 순서 리턴
            }else if(order<k){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        return -1;
    }
}
