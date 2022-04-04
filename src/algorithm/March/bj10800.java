package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
- 공 잡는 기준 -
1. 내 공보다 크기가 작아야 함.
2. 내 공과 색이 달라야 한다.
-> 공을 잡은다면? 그 공의 크기만큼 점수를 얻는다.

다른 공을 잡더라도 내 공의 색, 크기는 변하지 않는다.
 */

/*

맨 처음 생각 -> 공의 크기를 기준으로 배열을 정렬하고
이분탐색으로 나보다 작은 크기를 가진 공의 첫번째 인덱스를 구함.
그 다음 누적합을 이용해 인덱스~끝까지의 합을 구함.

근데 생각해보니까 나랑 같은 색깔을 빼야되네
//공 색깔별로 배열을 달리 해도 될 듯. 2차원으로는 X <- 공의 색깔은 최대 200000개가 있을수 있음.

-> 일차적으로 공을 색깔과 크기순으로 정렬하는게 편할 듯?
크기 : 2000개 색깔 : (공개수) 200000개.
크기를 우선적으로 정렬해버리자.
크기로 정렬하고 색깔 같은걸 처리하자.. (v)

 */

public class bj10800 {
    static Ball[] balls;
    static int[] ballsum, ballcolor;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        balls = new Ball[N]; // 컬러볼 배열
        ballsum = new int[N]; // 컬러볼 누적합 저장 배열
        ballcolor = new int[N]; // 조건에 맞는 공 크기 합을 저장할 배열

        //입력
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            balls[i] = new Ball(i, color, size);
        }

        //컬러볼 정렬 (크기, 색깔 순으로 오름차순)
        Arrays.sort(balls);
        System.out.println(Arrays.toString(balls));

        int tmpsum =0; //누적합 저장
        int j=0;
        for(int i=0;i<N;i++){
            Ball target = balls[i];
            Ball compare = balls[j];
            System.out.println("target_color : "+ target.color + " compare_color : "+ compare.color);
            System.out.println("target_size : "+ target.size + " compare_size : "+ compare.size);


            while(compare.size<target.size){
                tmpsum += compare.size; //사이즈 누적합
                ballcolor[compare.color-1] += compare.size; // 조건에 맞으면 일단 해당 색깔에 따라 저장.
                j++;
                compare = balls[j];
            }
            System.out.println("tmpsum : "  + tmpsum);
            System.out.println("ballcolor" + Arrays.toString(ballcolor));
            //전체 합에서 나랑 같은 색깔 애가 먹을 수 있는 만큼은 제외시켜줌.
            ballsum[target.idx] = tmpsum - ballcolor[target.color-1];
            System.out.println("ballxum" + Arrays.toString(ballsum));

            System.out.println("********************************");

        }

        for(int i=0;i<N;i++){
            sb.append(ballsum[i] + "\n");
        }

        System.out.println(sb);

    }

    public static class Ball implements Comparable<Ball>{
        int idx, color, size;

        public Ball(int idx, int color, int size ) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }

        //크기를 우선적으로 정렬하고 그 다음 같은 색깔로 정렬. (둘 다 오름차순)
        @Override
        public int compareTo(Ball o) {
            if(this.size == o.size) return this.color-o.color;
            else return this.size-o.size;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "idx=" + idx +
                    ", color=" + color +
                    ", size=" + size +
                    '}';
        }
    }
}