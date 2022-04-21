package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
예제 2 출력이 70억개... DFS로 신나게 풀었다가 예제 2가 도저히 안나와서 갈아 엎음.
시간 초과를 해결할 수 있는 방법은 DP뿐.
 */
public class bj5557 {
    static int N;
    static long[][] count;
    static int[] num;
    public static void main(String[] args) throws IOException {
        InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        count = new long[N-1][21]; //2의 63승이라니 Long형으로 저장해야된다.
        solve();
        for(int i=0;i<N-1;i++){
            System.out.println(Arrays.toString(count[i]));
        }
        System.out.println(count[N-2][num[N-1]]);
    }

    //count[i][j] : i+1개의 숫자를 사용해서 만든 j라는 숫자를 몇 개 만들 수 있는지를 저장한다.
    public static void solve(){
        //초기값
        count[0][num[0]] =1L;

        // 숫자는 1~N-1개까지 연산한다. (index로는 0~N-2)
        for(int i=1;i<N-1;i++){
            for(int j=0;j<=20;j++){ //j라는 값을 만들어야 되는데, 어짜피 0이상 20이하의 숫자이므로.. ㅎㅎ
                if(count[i-1][j]!=0) { //count에 값이 있어야만 연산이 가능하다는 소리. 동전 기억나시죠
                    if(j+num[i]<=20) count[i][j+num[i]] +=count[i-1][j];
                    if(j-num[i]>=0) count[i][j-num[i]] +=count[i-1][j];
                }
            }
        }

    }


}
