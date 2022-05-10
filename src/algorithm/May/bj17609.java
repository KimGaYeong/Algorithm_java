package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj17609 {
    static int N;
    public static void main(String[] args)  throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        while(N-->0){

            String str = br.readLine();
            //반 쪼개서 겹쳐보면 펠린드롬인지 나옴
            if(ispel(str)){
                sb.append("0\n");
                continue;
            }

            if(isPpel(str)){
                sb.append("1\n");
                continue;
            }

            sb.append("2\n");
        }
        System.out.println(sb);
    }

    public static boolean isPpel(String string){
        int s=0;
        int e=string.length()-1;

        while(s<e){
            if(string.charAt(s)!=string.charAt(e)){// 1/234432"5"/1  1/"5"234432/1
                return ispel(string.substring(s,e)) || ispel(string.substring(s+1, e+1));
            }else{
                s++;
                e--;
            }
        }
        return true;
    }

    public static boolean ispel(String string){
        int len = string.length();
        int lenh = len/2;

        for(int i=0;i<lenh;i++){
            if(string.charAt(i) != string.charAt((len-1)-i)) return false;
        }
        return true;
    }
}
