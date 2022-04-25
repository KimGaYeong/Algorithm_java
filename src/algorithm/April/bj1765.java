package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
친구의 친구는 친구
원수의 원수는 친구

//
 */
public class bj1765 {
    static int n, m;
    static int[] parent;
    public static void main(String[] args)  throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
        }


        // 음....... Ummmm......
        // 일단 친구끼리 모아놓은 그룹을 만들고 (친구의 친구는 친구)
        // 거기서 그룹을 2개씩 비교하면서 원수가 있는지를 찾으면 되나?/???? (원수의 원수는 친구)


        //union find로 찾기?
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]); //경로 압축
    }

    public static void union(int cur, int before) {
        cur = find(cur);
        before = find(before);
        if (cur != before) {
            parent[cur] = before;
        }
    }
}
