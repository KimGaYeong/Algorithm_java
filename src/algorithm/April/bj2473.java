package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 용액 3개를 혼합해 특성 값이 0에 가까운 애를 고른다.
// int 범위 -2,147,483,648 ~ 2,147,483,647
// 용액 값 범위 -1,000,000,000 이상 1,000,000,000
public class bj2473 {
    static int N;
    static long[] arr;
    static long[] result;
    static long min = Long.MAX_VALUE;
    public static void main(String[] args)  throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        result = new long[3];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //정렬
        Arrays.sort(arr);
        //용액 3개 넘을 경우 답이 Int범위를 넘어감.
        //최소 012 ~ 최대 N-3N-2N-1 -> 제일 최대인 값을 골랐다고 가정하고
        //나머지 두개를 이분 탐색으로 찾아서 값을 비교함.
        for(int i=2;i<=N-1;i++){
            binarySearch(i);
        }
        //System.out.println(Arrays.toString(arr));

       // Arrays.sort(result);
        for(int i=0;i<3;i++){
            sb.append(result[i] + " ");
        }

        System.out.println(sb);
        // 용액의 최다 크기는 5천이라 아무래도 DFS를 걍 돌리기는 어려울 듯 싶다.
        // 다 알칼리일 수도, 다 산성일 수도 있어서 알칼리랑 산성을 분리해서 저장하기는 어려울 듯 싶고...

        //-> 하나를 지정해놓고 이분 탐색으로 2개를 풀자.
    }
// idx idx+1 ..... N-1
    public static void binarySearch(int idx){
        int low = 0; //최대가 2라면 최소는 0부터
        int mid = idx-1; //최대가 idx(N-1)라면 중간은 idx-1까지

        while(low<mid){
            long tmp = arr[low]+arr[mid]+arr[idx];
            if(Math.abs(tmp) < min){
                //최소값 갱신하고
                min = Math.abs(tmp);
                //결과도 갱신
                result[0] = arr[low];
                result[1] = arr[mid];
                result[2] = arr[idx];

            }else{
                if(tmp>0) mid -=1;
                else low +=1;
            }

        }
    }
}

//5
//-2 6 -97 -6 98