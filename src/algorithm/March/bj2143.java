package algorithm.March;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
(100+1+ | 01)+
+	앞 문자가 하나 이상
|	패턴 안에서 or 연산을 수행할 때 사용

*/
public class bj2143 {
    static int N;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1039.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            if(parser(br.readLine())){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    public static boolean parser(String s){
        String pattern = "((100+1+)|(01))+";
        //"^(01|10(0+1+))+$"
        boolean ans = s.matches(pattern);
        return ans;
    }

}

