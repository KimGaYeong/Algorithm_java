package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj7490 {
    static int N;
    static int[] arr;
    static List<String> stringlist;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); //testCase

        for(int t=0;t<T;t++){

            //Input
            N = Integer.parseInt(br.readLine());
            arr=  new int[N];
            //1~N까지의 오름차순 숫자 저장
            for(int i=1;i<=N;i++){
                arr[i-1] = i;
            }

            stringlist = new LinkedList<>(); // 결과가 0이 되는 문자열을 저장
            DFS(arr[0], String.valueOf(arr[0]), 1); //1부터 시작하는 DFS
            DFS((arr[0]*10+arr[1]), arr[0] + " " + arr[1], 2); //12부터 시작하는 DFS

            Collections.sort(stringlist); // 저장한 문자열 아스키 코드 순으로 정렬

            //sb에 입력 & 출력
            for(String s : stringlist){
                sb.append(s);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void DFS(int num, String str, int cnt){

        //N번째 숫자까지 들어오면 num을 비교해서 맞으면 stringlist에 추가
        if(cnt==N){
            if(num==0){
                stringlist.add(str + "\n");
            }
            return;
        }

        DFS(num + arr[cnt], str + "+" + arr[cnt], cnt+1); // 1 + 2
        DFS(num - arr[cnt], str + "-" + arr[cnt], cnt+1); // 1 - 2
        if(cnt+2<=N){
            // +12, -12. cnt를 1개 더 늘려줘야 되서 추가 조건이 필요
            DFS(num + (arr[cnt]*10 + arr[cnt+1]), str + "+" + arr[cnt] + " " + arr[cnt+1], cnt+2);
            DFS(num - (arr[cnt]*10 + arr[cnt+1]), str + "-" + arr[cnt] + " " + arr[cnt+1], cnt+2);
        }
    }

}

/*
1+2-3

1+2-3+4-5-6+7
1+2-3-4+5+6-7
1-2 3+4+5+6+7
1-2 3-4 5+6 7
1-2+3+4-5+6-7
1-2-3-4-5+6+7

1+2-3

1+2-3+4-5-6+7
1+2-3-4+5+6-7
1-2 3+4+5+6+7
1-2 3-4 5+6 7
1-2+3+4-5+6-7
1-2-3-4-5+6+7
 */