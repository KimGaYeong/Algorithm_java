import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj5582_2 {
    static String First;
    static String Second;
    static int[][] len;
    static int result;
    public static void main(String[] args) throws IOException {
        System.setIn(bj5582_2.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        First = br.readLine();
        Second = br.readLine();
        int flen = First.length();
        int slen = Second.length();
        len = new int[flen+1][slen+1];
        result =0;
        for(int i=1;i<=flen;i++){
            for(int j=1;j<=slen;j++){
                if (IsSame(First.charAt(i-1), Second.charAt(j-1))){
                    len[i][j] = len[i-1][j-1]+1;
                    if (result<=len[i][j]) result = len[i][j];
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