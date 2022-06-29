package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj14395 {
    static long N, K;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        if(N==K){
            System.out.println(0);
            System.exit(0);
        }

        BFS();
    }

    static String[] strs = {"*","+","-","/"};
    //연산의 아스키 코드 순서는 '*', '+', '-', '/' 이다.

    public static void BFS(){
        Queue<Operation> queue = new LinkedList<>();
        HashSet<Long> set = new HashSet<>();
        queue.add(new Operation(N, ""));

        while(!queue.isEmpty()){
            Operation op = queue.poll();

            if(op.val == K){
                System.out.println(op.result);
                return;
            }

            for(int i=0;i<4;i++){
                long nextValue = change(op.val, i);
                if(set.contains(nextValue) || nextValue<0) continue;

                set.add(nextValue);
                queue.add(new Operation(nextValue, op.result+strs[i]));
            }
        }

        System.out.println(-1);


    }

    public static long change(long value, int i){
        if (i == 0) {
            value *= value;
        }else if(i==1){
            value += value;
        }else if(i==2){
            return 0;
        }else{
            return 1;
        }

        return value;
    }

    public static class Operation{
        Long val;
        String result;

        public Operation(Long val, String result) {
            this.val = val;
            this.result = result;
        }

        @Override
        public String toString() {
            return "Operation{" +
                    "val=" + val +
                    ", result='" + result + '\'' +
                    '}';
        }
    }
}
