package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2374 {

    /* 문제
    Add[i]가 추가될 때 마다 (1부터 시작해서) i번째  index를 가진 애가 +1되고,
    i-1번째 index를 가진 애와 같은 숫자를 가지며 이어진 i-k까지,
    i+1번째 index를 가진 애와 같은 숫자를 가지며 이어진 i+m까지 +1된다.

    모든 입력은 1,000,000,000 즉 10억 을 넘지 않는다. -> 무슨 의미? O(n)으로 완탐하면 이미 10초
    재수없어서 모든 배열이 같은 크기일 경우를 생각해야 함.*/

    /**
     입력 값을 그룹별로 나누는 것? {1,1,1,1,3,3,1} -> {1,1,1,1} + {3,3} + {1}
     Add(i)하는 i가 특정 그룹의 경계라면? index 0 or GroupSize-1 : 내 그룹도 +1, 내 옆 그룹도 +1
     경계에 속하지 않는다면? 내 그룹만 +1
     내 그룹 사이즈와 내 그룹에 해당하는 경계 인덱스만 알고있으면 됨.
     */

    static int n, answer;
    static String A;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        InputStream input = bj2374.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        n = Integer.parseInt(br.readLine());
        A = "";
        for(int i=1;i<=n;i++){
            A += " " + Integer.parseInt(br.readLine());
        }
        System.out.println(A);
        answer =0;
        DFS(0,0,"");
    }
    public static void DFS(int idx, int cnt, String ans){
        //base part
        if(check(ans)){ //숫자가 모두 같은지 확인
            if(answer > cnt){
                cnt = answer;
            }
            return;
        }

        while(true){

        }
        //inductive part
    }

    public static boolean check(String ans){

        return true;
    }
}
