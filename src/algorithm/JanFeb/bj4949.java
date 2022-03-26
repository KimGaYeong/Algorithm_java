package algorithm.JanFeb;

import java.io.*;
import java.util.*;

public class bj4949{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = 0;
        while(t == 0) {
            String line = br.readLine();
            if(line.equals(".")) break;
            boolean chk = true;
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < line.length(); i++){
                char c = line.charAt(i);
                if(c == '(' || c == '['){
                    stack.add(c);
                } else if(c == ')'){
                    if(!stack.empty() && stack.pop() == '(') continue;
                    else {
                        chk = false;
                        break;
                    }
                } else if(c == ']') {
                    if(!stack.empty() && stack.pop() == '[') continue;
                    else {
                        chk = false;
                        break;
                    }
                }
            }
            if(chk && stack.isEmpty()) sb.append("yes\n");
            else sb.append("no\n");
        }

        System.out.println(sb);
    }
}