package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//문자열의 뒤에 A를 추가한다.
//문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
//문자열을 반환하는 DFS? 종료 조건은 문자열의 길이?

public class bj12919 {
    static String original, target;
    static boolean isPossible =false;
    static int originalsize;
    static StringBuilder s;
    public static void main(String[] args) throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        original = br.readLine();
        originalsize = original.length();
        target = br.readLine();

        DFS(target, target.length());
        if(isPossible) System.out.println("1");
        else System.out.println("0");
    }
    //(1 ≤ S의 길이 ≤ 49, 2 ≤ T의 길이 ≤ 50, S의 길이 < T의 길이)

    public static void DFS(String str, int size){
        if(str.length() == originalsize){
            if(str.equals(original)){
                isPossible = true;
                return;
            }
        }

        if(size<originalsize){
            return;
        }

        if(str.charAt(size-1) == 'A'){
            DFS(str.substring(0,size-1), size-1);
        }

        if(str.charAt(0)=='B'){
            DFS(ReOrder(str.substring(1)), size-1);
        }
    }

    public static String ReOrder(String str){
        StringBuffer sbs = new StringBuffer(str);
        return sbs.reverse().toString();
    }
}
