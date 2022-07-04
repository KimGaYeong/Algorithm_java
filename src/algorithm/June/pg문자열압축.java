package algorithm.June;

import java.util.*;

public class pg문자열압축 {
    public static void main(String[] args) {

        String s = "aabbaccc";
        /*
        "aabbaccc"	7 2a2ba3c
        "ababcdcdababcdcd"	9
        "abcabcdede"	8
        "abcabcabcabcdededededede"	14
        "xababcdcdababcdcd"	17
         */

        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int answer = Integer.MAX_VALUE; //최소값 갱신하려고
        int result = 0; //문자열 길이 저장.

        int cut = 1; //몇개 문자를 기준으로 자르는지?
        String str = ""; //현재 내가 보고있는 문자열
        String before = ""; //비교할 전 문자열

        while(cut<=s.length()){
            result = 0;
            int idx = 1;
            int idx2 = 0;
            //aabbaccc
            before = "";
            for(int j=1;j<=s.length()+1;j+=cut){
                str = "";
                for(int i=j;i<j+cut && i<=s.length();i++){
                    str += String.valueOf(s.charAt(i-1));
                }
                //System.out.println("Before : " + before + ", str : " + str + "   ");

                System.out.print(str + " ");
                if(before.equals(str)){ //전꺼랑 지금 보는거랑 같음.
                    idx++;
                }else{ //다름. aa aa aa bb -> before aa str bb idx:3   -> 3aa 10 ->2 1aa aa
                    result+= (before.length()); //aa의 길이
                    int k = String.valueOf(idx).length(); //3의 길이
                    if(idx!=1){ //개수가 1이 아닐때만 몇개 연속인지 앞에 써주니까
                        result +=k;
                    }
                    //System.out.println("[ " + before + " " + idx + " ( " + k + ")" + " ]");
                    idx = 1;
                    before = str;

                }
                //System.out.println(idx);
            }
            //System.out.println("str : " + str + " ");
            result+= (str.length());
            System.out.println("result : " + result);

            answer = Math.min(answer, result);
            cut++; //
            //System.out.println();
        }
        return answer;
    }

}
