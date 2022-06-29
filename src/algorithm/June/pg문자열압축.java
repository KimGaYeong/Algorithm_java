package algorithm.June;

import java.util.*;

public class pg문자열압축 {
    public static void main(String[] args) {

        String s = "abcabcdede";
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
        int answer = Integer.MAX_VALUE;

        int cut = 1;
        String str = "";
        int result = 0;
        Queue<String> queue = new LinkedList<>();
        Queue<String> mini = new LinkedList<>();
        String before = "";

        while(cut<=s.length()){

            queue.clear();
            mini.clear();
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
                //System.out.print("Before : " + before + ", str : " + str + "   ");

                System.out.print(str + " ");
                if(before.equals(str)){
                    idx++;
                }else{
                    result+= (before.length());
                    int k = String.valueOf(idx).length();
                    if(idx!=1){
                        result +=k;
                    }
                    //System.out.println("[ " + before + " " + idx + " ( " + k + ")" + " ]");
                    idx2 = idx;
                    idx = 1;
                    before = str;

                }
                //System.out.println(idx);
            }
            //System.out.println("str : " + str + " ");
            result+= (str.length());
            System.out.println("result : " + result);

            answer = Math.min(answer, result);
            cut++;
            //System.out.println();
        }
        return answer;
    }

}
