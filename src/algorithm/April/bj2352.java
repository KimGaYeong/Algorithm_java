package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 연결 선이 꼬이면 안된다 -> 어따 걸어야되는지를 기준으로 가장 긴 부분 수열을 찾으면 됨.
 */
public class bj2352 {
    static int N;
    static int[] arr;
    static ArrayList<Integer> lines;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1937.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        //입력
        lines =new ArrayList<>();
        N=Integer.parseInt(br.readLine());
        arr = new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //일단 하나 넣어놓음.
        lines.add(arr[0]);

        //얘를 기준으로 탐색.
        for(int i=1;i<N;i++) {
            int tmp=arr[i];
            // i번째 수열의 값이 현재 LIS의 최댓값보다 크다면 최댓값을 갱신
            if(lines.get(lines.size()-1)<tmp){
                lines.add(tmp);
            }
            else { //만약 i번째 수열의 값이 최댓값과 최솟값의 사이에 있다면 이분탐색ㄱㄱ
                int low=0;
                int high= lines.size()-1;
                while(low<high) {
                    int mid=(low+high)/2;
                    if(lines.get(mid)<tmp) low=mid+1;
                    else high=mid;
                }
                //가장 큰 값을 바꿔줌
                lines.set(high,tmp);
            }
        }
        System.out.println(lines.size());

    }
}
