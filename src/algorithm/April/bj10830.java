package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj10830 {

    /**
     * 행렬 N*M의 B제곱..
     * 슈트라센 방법 : > 정의에 따라 n×n 크기의 두 행렬을 곱하면 O(n^3)의 시간이 소요되지만,
     * 이 알고리즘은 대략 O(n^2.38)의 시간이 소요 --> 시간 초과..
     * 슈트라센 알고리즘이 효율적인 경우는 N이 32 이상부터다. ㅜ_ㅜ
     * 음.........그럼 분할 정복으로 풀어야지...
     */

    static int N;
    static long B;
    static long[][] map;
    static long[][] ans;
    public static void main(String[] args) throws IOException {
        InputStream input = bj2580.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        //입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        map = new long[N][N];
        ans = new long[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Long.parseLong(st.nextToken());
                ans[i][j] = map[i][j];
            }
        }

        while (B-->1){
            ans = multiply(ans, map, N);
        }


        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sb.append((ans[i][j]%1000) + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    // 부분행렬을 반환하는 메소드
    static long[][] subArray(long[][] src, int row, int col, int size) {

        long[][] dest = new long[size][size];
        for (int dset_i = 0, src_i = row; dset_i < size; dset_i++, src_i++) {
            for (int dest_j = 0, src_j = col; dest_j < size; dest_j++, src_j++) {
                dest[dset_i][dest_j] = src[src_i][src_j];
            }
        }
        return dest;
    }


    // src는 복사할 배열(=부분배열), dest는 합쳐질 배열(= 배열 C)
    public static void merge(long[][] src, long[][] dest, int row, int col, int size) {

        for (int src_i = 0, dest_i = row; src_i < size; src_i++, dest_i++) {
            for (int src_j = 0, dest_j = col; src_j < size; src_j++, dest_j++) {
                dest[dest_i][dest_j] = src[src_i][src_j];
            }
        }
    }

    // 행렬 뺄셈
    static long[][] sub(long[][] A, long[][] B, int size) {

        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    // 행렬 덧셈
    static long[][] add(long[][] A, long[][] B, int size) {

        int n = size;

        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public static long[][] multiply(long[][] A, long[][] B, int size) {


        long[][] C = new long[size][size];	// 완성시킬 C 배열

        // size가 1로 가장 작게 쪼개질 경우 (0,0) 원소밖에 없으므로 해당 원소의 곱을 반환
        if (size == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }


        int newSize = size / 2;	// 부분행렬에 대한 사이즈

        // A의 부분행렬
        long[][] a11 = subArray(A, 0, 0, newSize);
        long[][] a12 = subArray(A, 0, newSize, newSize);
        long[][] a21 = subArray(A, newSize, 0, newSize);
        long[][] a22 = subArray(A,newSize, newSize, newSize);

        // B의 부분행렬
        long[][] b11 = subArray(B, 0, 0, newSize);
        long[][] b12 = subArray(B, 0, newSize, newSize);
        long[][] b21 = subArray(B, newSize, 0, newSize);
        long[][] b22 = subArray(B, newSize, newSize, newSize);



        // M1 := (A11 + A22) * (B11 + B22)
        long[][] M1 = multiply(add(a11, a22, newSize), add(b11, b22, newSize), newSize);

        // M2 := (A21 + A22) * B11
        long[][] M2 = multiply(add(a21, a22, newSize), b11, newSize);

        // M3 := A11 * (B12 - B22)
        long[][] M3 = multiply(a11, sub(b12, b22, newSize), newSize);

        // M4 := A22  * (B21 − B11)
        long[][] M4 = multiply(a22, sub(b21, b11, newSize), newSize);

        // M5 := (A11 + A12) * B22
        long[][] M5 = multiply(add(a11, a12, newSize), b22, newSize);

        // M6 := (A21 - A11) * (B11 + B12)
        long[][] M6 = multiply(sub(a21, a11, newSize), add(b11, b12, newSize), newSize);

        // M7 := (A12 - A22) * (B21−B22)
        long[][] M7 = multiply(sub(a12, a22, newSize), add(b21, b22, newSize), newSize);



        // C11 := M1 + M4 − M5 + M7
        long[][] c11 = add(sub(add(M1, M4, newSize), M5, newSize), M7, newSize);

        // C12 := M3 + M5
        long[][] c12 = add(M3, M5, newSize);

        // C21 := M2 + M4
        long[][] c21 = add(M2, M4, newSize);

        // C22 := M1 − M2 + M3 + M6
        long[][] c22 = add(add(sub(M1, M2, newSize), M3, newSize), M6, newSize);


        // 구해진 C의 부분행렬들 합치기
        merge(c11, C, 0, 0, newSize);
        merge(c12, C, 0, newSize, newSize);
        merge(c21, C, newSize, 0, newSize);
        merge(c22, C, newSize, newSize, newSize);

        return C;
    }
}
