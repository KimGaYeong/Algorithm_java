package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class bj17298 {
    static int[] A, reverseMap;
    static Stack<Integer> check, tmpResult;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1082.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer sts;

        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N]; //1,000,000 까지. 1000000 * 0000000 ,,,  쉽지않다.
        reverseMap = new int[N];

        sts = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            map[i] = Integer.parseInt(sts.nextToken());
        }

        // map = 3 5 2 7
        check = new Stack<>(); // 맨 뒤부터 check스택에 넣으며 검사를 진행한다.
        tmpResult = new Stack<>(); // 맨 뒤 원소(여기서는7)의 오큰수를 넣는다.

        // 맨 처음에는 map을 뒤집어서 풀자고 생각했으나...
        // for문 2번 탐색을 하지않기위해 스택을 사용할 예정인데,
        // 시간초과 때문에 로직을 짜면서 쓸데 없는 for문을 또 사용하지 말자고 생각했다.
        // (뒤에서부터 확인하기)

        int idx = map.length-1;

        //map의 맨 뒤부터 검사한다. 가장 처음 검사할 수는 map의 맨 마지막 수
        check.push(map[idx]);
        tmpResult.push(-1); // 맨 마지막 수는 오큰수가 없다. 무조건 정담에 -1을 넣는다.

        for(int i = idx-1; i >= 0; i--) { //2번째 수부터는 for문을 통해 검사.
            int checktop = check.peek(); // 현재 검사 스택의 맨 끝 (오른쪽 값)
            int target = map[i]; //이제 검사를 진행해야 할 값

            //검사스택이 있는데 검사를 진행해야 할 값이 더 크면? 오큰수를 찾을 때까지 pop!
            while(!check.isEmpty() && checktop <= target) {
                check.pop();
            }

            //검사 스택이 비었을 경우
            if(check.isEmpty()) { //오큰수가 없다. -1넣고 검사끝내자..
                tmpResult.push(-1);
                check.push(target);
            } else { //오큰수가 있다. 오큰수를 넣고 검사 끝내자
                tmpResult.push(checktop);
                check.push(target);
            }

            //
            check.push(target);
        }

        while(!tmpResult.isEmpty()) sb.append(tmpResult.pop() + " ");

        System.out.println(sb);
    }
}
