package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14936 {
    static int N, M;
    static int[] prankTimeArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int buttonStateBit = 1;

        if (M == 0) { // M이 0초면 누를 수 있는 것이 1가지. (아예 안누르는 경우)
            System.out.println(1);
            return;
        }
        if (N == 1) { // N이 1층뿐이라면 누르거나, 안누르거나 2가지.
            System.out.println(2);
            return;
        }
        if (N == 2) { // N이 2층이라면 (M이 0초일 때 1, M이 1초일 때 3 -> 1)1,2눌러짐 2)1눌러짐 3)2눌러짐 4)1눌러짐 -> 3가지)
            if (M == 1) System.out.println(3);
            else System.out.println(4);
            return;
        }

        prankTimeArr = new int[]{N, (N/2)+(N%2), N/2, ((N-1)/3)+1};

        for (int i = 0; i < 4; i++) {
            if (prankTimeArr[i] <= M) buttonStateBit |= (1 << i + 1);
        }

        for (int i = 0; i < 3; i++) {
            if (M- prankTimeArr[i] < prankTimeArr[3] || (buttonStateBit & (1 << i)) == 0) continue;
            buttonStateBit |= (1 << i + 5);
        }

        //num 숫자를 binary로 변환후, 1의 수를 반환
        System.out.println(Integer.bitCount(buttonStateBit));
        br.close();
    }
}