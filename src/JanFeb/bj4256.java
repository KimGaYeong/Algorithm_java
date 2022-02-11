package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj4256 {
    static int T;
    static int n;
    static int[] pre;
    static int[] in;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++) {
            n = Integer.parseInt(br.readLine());


            pre = new int[n];
            in = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                pre[i] = Integer.parseInt(st.nextToken()); //3 2 1 4
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                in[i] = Integer.parseInt(st.nextToken()); // 2 3 4 1
            }

            post(0, n, 0, sb);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    static void post(int start, int end, int cur, StringBuilder sb){
        for(int i=start;i<end;i++){
            if(pre[cur] ==in[i]){ //현재 preorder에서 탐색하지 않은 root를 inorder에서 찾는다.
                post(start, i, cur+1, sb); //inorder에서 root index(i)를 찾았으면
                // root index(i)를 기준으로 현재 root의 위치인 cur까지를 돌려서 left를 찾는다.(얘는 계속 left만 찾겠지?)
                post(i+1, end, cur+1+i-start, sb); //위랑 같음. root를 기준으로 right를 찾는다.
                sb.append(pre[cur] + " ");// post에서 아무 일도 일어나지 않을 때(노드를 다 돌았을 때)
                System.out.println("start : " + start + ", i : " +  i + ", cur : " +  cur + " --> print : " +  pre[cur]);
            }
        }
    }
}
/*
* 먼저 생각할 것 : 전위 순회, 중위 순회, 후위 순회의 차이점. 몰까??
* 전위 순회는 root -> left child -> right child
* 중위 순회는 left child -> root -> right child
* 후위 순회는 left child -> right child -> root
*
* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*
* 전위 순회에서 root를 찾을 수 있고, 중위 순회에서 left, right를 구별할 수 있다.
* 그 말인 즉슨, 중위 순회에서 찾은 left, right를 먼저 찾고
* 전위 순회에서 찾은 root를 돌려주면 후위 순회가 완성된다는 의미.
*
* */