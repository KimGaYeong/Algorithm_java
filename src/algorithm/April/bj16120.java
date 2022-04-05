package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * P가 나오면 일단 스택에 넣는다.
 * A가 나오면 스택 안에서 두개를 꺼내고
 * 두개를 못꺼내면 ? PPAP아님.
 * 두개를 꺼낼 수 있으면 내 뒤에 P가 들어오는지도 따져야 함.
 */
public class bj16120 {
    static char[] chars;
    public static void main(String[] args) throws IOException {
        InputStream input = swea_5656.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        String str = br.readLine();
        if(str.equals("P")){
            System.out.println("PPAP");
            return;
        }
        chars = new char[str.length()];
        for(int i=0;i<str.length();i++){
            chars[i] = str.charAt(i);
        }
        int i=0;
        Stack<Character> stack = new Stack<>();
        boolean is_2P_inStack = false; //A가 나왔을 때 스택 안에 PP가 있음
        boolean is_back_P = false; //A가 나왔고 그 뒤에 P가 있음

        while(i<chars.length){
            if(chars[i] =='P'){ //A 이전 P인 경우
                stack.add(chars[i]);
                if(is_2P_inStack){ //A 다음 P인 경우 (A 앞 스택 길이가 2 이상이여도 뒤에 P가 없으면 NP)
                    is_back_P = true;
                }
            }else{
                is_2P_inStack = false;
                is_back_P = false;
                //문자가 A이면 스택 길이가 2 이상인지 확인.
                if(stack.size()>=2) {
                    is_2P_inStack = true;
                    for(int c=0;c<2;c++){
                        stack.pop();
                    }
                }else{
                    System.out.println("NP");
                    return;
                }
                //그리고 바로 뒤 문자열이 P인지 확인.
            }
            i++;
        }

        if(is_back_P){ //맨 마지막이 P로 끝나면!
            System.out.println("PPAP");
        }else{
            System.out.println("NP");
        }

    }
}
