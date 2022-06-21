package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class bj9663 {
    static int N, result;
    public static int[] row;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        // 체스에서 퀸 : 일직선으로 앞, 뒤, 옆, 대각선 어떤 방향이든 원하는 만큼 이동할 수 있습니다.
        row = new int[N];
        result=0;
        DFS(0);

        System.out.println(result);
    }

    public static void DFS(int cnt){

        if(cnt==N){
            //check(); ->여기서 검증하면 시간초과!
            result++;
            return;
        }

        // 애초에 한 열에는 퀸이 하나만 올 수 있다. 퀸은 상하좌우대각선으로 제한 없이 갈 수 있기 때문.
        for (int i = 0; i < N; i++) {
            row[cnt] = i;
            if (check(cnt)) { //현재 열이 될 수 있는지 체크!
                DFS(cnt + 1);
            }
        }


    }

    public static boolean check(int cnt){

        for(int i=0;i<cnt;i++){
            //양옆으로 확인
            if(row[i] == row[cnt]) return false;
            //대각선 확인 (열, 행의 차가 같으면 대각선이니까)
            if(Math.abs(cnt-i) == Math.abs(row[cnt]-row[i])) return false;
        }

        return true;
    }
}
