package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj2224 {
    static int N;
    static int MAX_SIZE = 58;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int [][] arr = new int[MAX_SIZE][MAX_SIZE];
        int cnt=0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine(); //한 줄씩 읽고 캐릭터 받아옴
            char first = str.charAt(0);
            char second = str.charAt(str.length()-1);

            if(first == second) continue; //문자로 받아온 애들을 숫자로 바꿔서 arr배열에 넣어줌.
            int from = first - 'A';
            int to = second - 'A';

            if(arr[from][to] != 1){
                arr[from][to] = 1;
                cnt+=1;
            }
        }

        for(int k=0;k<MAX_SIZE;k++){
            for(int i=0;i<MAX_SIZE;i++){
                for(int j=0;j<MAX_SIZE;j++){
                    if(i!=j && arr[i][j] == 0 && arr[i][k] ==1 && arr[k][j] ==1){
                        arr[i][j] =1;
                        cnt+=1;
                    }
                }
            }
        }
        System.out.println(cnt);
        for(int i=0;i<MAX_SIZE;i++){
            for(int j=0;j<MAX_SIZE;j++){
                if(arr[i][j] != 0){
                    System.out.println((char)(i+'A') + " => " + (char)(j+'A'));
                }
            }
        }
    }
}
