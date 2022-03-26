package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * k개를 고르고 visit를 판별하는 것 --> 3000000 * 3000개 ㄴㄴ
 *
 */
public class bj15961 {
    static int N, d, k, c;
    static int result;
    static int[] belt;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        //belt 입력
        belt = new int[N+k]; //회전하면 원래 입력 + 0-k까지 반복되니까
        visited = new int[d]; // 0~d-1
        for(int i=0;i<N;i++){
            belt[i] = Integer.parseInt(br.readLine());
        }
        for(int i=0;i<k;i++){
            belt[N+i] = belt[i];
        }
        //sliding window 알고리즘 사용.
        solve();

        System.out.println(result);

    }

    public static void solve(){

        int cnt = 0;
        //나는 *초밥 개수*를 세야한다.
        for(int i=0;i<k;i++){
            if(visited[belt[i]-1]==0){
                cnt++; //방문 개수가 0이면 늘려주고 초밥 개수도 +1
            }
            visited[belt[i]-1]++;
        }

        for(int i=1;i<N;i++){
            int start = belt[i-1]-1; //window left >> 한 칸.
            visited[start]--; //원래 자리 -1
            if(visited[start]==0) cnt--; //종류 하나 없는지 아닌지 세기

            int end = belt[i+k-1]-1; //window right >> 한 칸
            if(visited[end]==0) cnt++; //새로운 종류면 cnt추가
            visited[end]++; //종류 개수 추가

            if(result <=cnt){
                if(visited[c-1] ==0){
                    cnt++;
                    visited[c-1]++;
                }
                result = cnt;
            }
            //System.out.println("start : " + start + " end : " + end + " cnt : " + cnt);
        }
    }
}

/*
* ON^2 이상으로 해결해야 하는 문제를 선형 시간으로 해결해줄 수 있다.
* "연속되는" value를 이용하는 문제. 연속성이 없다면 투포인터를 사용할 수 없다. (정렬 등을 통해 연속성을 줘도 됨)
* 백트래킹을 사용한다 하더라도 사건초과 날 수 있음.
* 시작점과 끝 점 포인트를 잡고 포인터를 이동시킨다. 1. end pointer를 이동시켜 먼저 첫 번째 값이 들어갈 수 ㅇ
* 있게 하고ㅡ 그렇지 않다면 start, end를 이동. (현재 포함된 값의 합이 작은 경우)
* -> 지금 내 문제같은 경우 연속된 값들의 합이 아닌 '개수'를 세는 것이 맞겠쥥?
* 포인터를 옮겼을 때 값이 변화한다면 이것도 조정해주어야 한다.

어짜피 선형 식이니까 boolean 을 사용해서 list 개수만큼 빼줘도 괜찮겠징..??
 * */
