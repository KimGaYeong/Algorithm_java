package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj10775 {
    static int[] parent;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        parent = new int[G+1];

        for (int i = 1; i <= G; i++) {
            parent[i] = i; //일단 맨 처음에는 자기 번호 게이트로 도킹을 시킴.
        }

        int answer = 0;
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());
            int gate = find(g); //g라는 비행기가 도킹할 게이트를 find로 찾음.
            if (gate == 0) { //다음 도킹할 게이트가 없으면 break.
                break;
            }
            answer++;
            union(gate, gate - 1); //다음에 비행기가 도킹할 곳은 지금 게이트의 이전 게이트임.
            //따라서 다음에 도킹할 게이트를 나타낼 곳을 바꾸어 줘야 함.
        }
        System.out.println(answer);
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x; //맨 처음에는 도킹할 번호가 자기번호!
        }
        return parent[x] = find(parent[x]); //경로 압축 : 시간 복잡도 O(N) -> O(1)으로 단축 가능
   }
    public static void union(int cur, int before) { //비행기가 도킹할 곳은 cur 게이트의 이전 게이트.
        cur = find(cur);
        before = find(before);
        if (cur != before) {
            parent[cur] = before;
        }
    }
}