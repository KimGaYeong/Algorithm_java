package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/*
알파벳 소문자로 이루어진 문자열 W가 주어진다.
양의 정수 K가 주어진다.
어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.

문자열 게임의 수 T가 주어진다. (1 ≤ T ≤ 100)

다음 줄부터 2개의 줄 동안 문자열 W와 정수 K가 주어진다. (1 ≤ K ≤ |W| ≤ 10,000)

T개의 줄 동안 문자열 게임의 3번과 4번에서 구한 연속 문자열의 길이를 공백을 사이에 두고 출력한다.

만약 만족하는 연속 문자열이 없을 시 -1을 출력한다.
 */
public class bj20437 {
    static int T, K;
    static String W;

    public static void main(String[] args) throws IOException {
        InputStream input = bj20437.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            //각 알파벳 별 나올 수 있는 인덱스를 담는 해시맵 만들기.
            HashMap<Character, ArrayList<Integer>> hmap = new HashMap<>();
            for (int i = 0; i < 26; i++) {
                hmap.put((char)(i+97), new ArrayList<>());
            }

            for (int i = 0; i < W.length(); i++) {
                hmap.get(W.charAt(i)).add(i);
            }
            //System.out.println(hmap);

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < 26; i++) {
                char Alphabet = (char) (i+97);
                list = hmap.get(Alphabet);
                if (list.size() < K) //인덱스 개수가 K보다 작으면 넘어감. 어짜피 K개 없음
                    continue;

                //인덱스 개수가 K개 이상이면?
                for (int j = 0; j < list.size() - K + 1; j++) {
                    // k가 1이면 그냥 1 반환 아니면 k 범위 만큼 전부 계산
                    min = Math.min(min, list.get(j+K-1) -list.get(j)+1);
                    max = Math.max(max, list.get(j+K-1) -list.get(j)+1);
                }
            }
            // K개가 안되는 경우
            if (min == Integer.MAX_VALUE) {
                sb.append("-1" + "\n");
                continue;
            }
            sb.append(min + " " + max+ "\n");
        }
        System.out.println(sb);

    }
}
