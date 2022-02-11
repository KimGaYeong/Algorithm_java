package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj4673 {
    static boolean[] check;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        check = new boolean[10001];

        //자릿수 합 구하기
        for(int i=1;i<=10000;i++){
            jarisu(i);
        }
        //출력
        for(int j=1;j<=10000;j++){
            if(!check[j]){
                System.out.println(j);
            }
        }
    }

    //나 + 자릿수 합 구하기
    public static void jarisu(int num) {
        int result = num; //원래 자기 숫자에
        // 자릿수를 더해서
        while (num > 0) {
            int newnum = num % 10;
            result += newnum;
            num /= 10;
        }
        //10000 넘으면 break
        if(result>10000) return;
        //안넘으면 check해주고
        if(!check[result]){
            check[result] = true;
        }
        //다시 이어서 찾기.
        jarisu(result);
    }
}
