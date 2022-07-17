package algorithm.June;

import algorithm.May.bj17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
모든 선수의 랭킹 차이의 총 합이 최소값이 되는 값을 찾는다.
랭킹이 차이가 있으므로 무조건 작은 숫자의 선수가 올라가게 되는데... 그말인 즉슨
숫자가 큰 선수와 숫자가 작은 선수가 만날 수록 합이 크게나온다.

그래서 숫자가 비슷한 애들끼리 붙여줘야 하는데 그러려면 큰 순이거나 작은 순이거나... 최대한 그래야한다.
숫자가 큰 애들은 언제나 질 수밖에 없으므로 얘를 기준으로 하나씩 버려주면서 올라간다.

1 6 2 5 3 4 : 제일 큰 애는 6이고, 6은 1,2 사이에 있다. 따라서 6은 2와 겨루는게 제일 작다.
 */
public class bj2262 {
    static int N;
    static List<Integer> tornament;
    static int target;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1082.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // n(1 ≤ n ≤ 256)
        // 토너먼트 시키기..

        N = Integer.parseInt(br.readLine());
        tornament = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            tornament.add(Integer.parseInt(st.nextToken()));
        }

        target = N;
        int result = 0;

        while(true){
            if(target==1) break;

            int targetidx = tornament.indexOf(target);
            int targetValue = tornament.get(targetidx);
            if(targetidx==0){ //맨 왼쪽인 경우
                result += targetValue-tornament.get(targetidx+1);
            }else if(targetidx==tornament.size()-1){ //맨 오른쪽인 경우
                result += targetValue-tornament.get(targetidx-1);
            }else{ //왼쪽 오른쪽이 존재하는 경우
                result += targetValue- (Math.max(tornament.get(targetidx-1), tornament.get(targetidx+1)));
            }
            tornament.remove(targetidx);
            target -=1;
        }

        System.out.println(result);

    }
}
