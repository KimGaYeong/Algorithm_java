package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
근처에 보이는 숫자 N개를 종이에 적는다.
그 다음, 종이에 적은 수를 M으로 나누었을 때, 나머지가 모두 같게 되는 M을 모두 찾으려고 한다.
 M은 1보다 커야 한다.

N개의 수가 주어졌을 때, 가능한 M을 모두 찾는 프로그램을 작성하시오.
째 줄에 종이에 적은 수의 개수 N이 주어진다. (2 ≤ N ≤ 100)

다음 줄부터 N개 줄에는 종이에 적은 수가 하나씩 주어진다.
 이 수는 모두 1보다 크거나 같고, 1,000,000,000보다 작거나 같은 자연수이다.
 같은 수가 두 번 이상 주어지지 않는다.

항상 M이 하나 이상 존재하는 경우만 입력으로 주어진다.

첫째 줄에 가능한 M을 공백으로 구분하여 모두 출력
M은 증가하는 형태로 출력
 */
public class bj2981 {
    static int N;
    static boolean check;
    static int[] list, diff;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        list = new int[N];
        diff = new int[N-1];

        list[0] = Integer.parseInt(br.readLine());
        for(int i=1;i<N;i++){
            list[i] = Integer.parseInt(br.readLine());
            diff[i-1] = Math.abs(list[i]-list[i-1]);
        }
        Arrays.sort(diff);

        for(int i=2;i<=diff[0];i++){
            check = true;
            for(int d : diff){
                if(d%i != 0){
                    check = false;
                    break;
                }
            }
            if(check) sb.append(i+" ");
        }

        System.out.println(sb);



        //유클리드 호제법
        /*
        A, B의 최대공약수를 찾는 로직
        A = aG , B = bG
        A = qB +r, aG = qB +r = qbG +r
        r = aG-qbG = (a-qb)G
        B = bG

        즉 어떤 수 A랑 B가 있을 때 A와 B의 최대공약수는 B와 A%B의 최대공약수와 같다.
         */

    }
}
