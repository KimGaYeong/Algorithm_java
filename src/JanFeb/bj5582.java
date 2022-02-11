package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj5582 {
    static String First;
    static String Second;
    static char first;
    static char second;
    static int cnt;
    static int result;
    static int pre_i;
    static int pre_j;
    public static void main(String[] args) throws IOException {
        System.setIn(bj5582.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        First = br.readLine();
        Second = br.readLine();
        int flen = First.length();
        int slen = Second.length();
        result=0;
        for(int i=0;i<flen;i++){
            for(int j=0;j<slen;j++){
                first = First.charAt(i);
                second = Second.charAt(j);
                pre_i = i;
                pre_j = j;
                if (first == second){
                    cnt =1;
                    while(true){
                        if (pre_i+1 >= flen || pre_j+1 >=slen) break;
                        char F = First.charAt(pre_i+1);
                        char S = Second.charAt(pre_j+1);
                        if(!IsSame(F, S)) break;
                        else {
                            cnt += 1;
                            pre_i += 1;
                            pre_j += 1;
                        }
                        if (pre_i >= flen || pre_j >= slen) break;
                    }
                    if(result < cnt) result = cnt;
                }
            }
        }
        System.out.println(result);
    }
    static boolean IsSame(char A, char B){
        if (A ==B){
            return true;
        }
        return false;
    }
}

/*
문제를 풀기 전 : 둘 중 하나의 문자열을 돌면서 한 문자를 기준으로 다른 문자열에서 해당 문자가
존재하는 지를 확인한다. 그 후 해당 문자열이 존재하지 않으면 다음 문자로 넘어가고,
해당 문자가 존재한다면 그 다음 문자열이 존재하는지를 확인한다. 다음 문자열이 존재할 때마다 카운트를 증가시킨다.
ABRACADABRA
ECADADABRBCRDARA
*/