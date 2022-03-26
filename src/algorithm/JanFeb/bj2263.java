package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2263 {
    static int N;
    static int[] in;
    static int[] post;
    static int[] index;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        in = new int[N+1];
        post = new int[N+1];
        index = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;++i){
            in[i] = Integer.parseInt(st.nextToken());
            index[in[i]] =i;
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;++i){
            post[i] = Integer.parseInt(st.nextToken());
        }

        pre(0,N-1,0,N-1,sb);
        System.out.println(sb.toString());
    }

    static void pre(int in_start, int in_end, int post_start, int post_end, StringBuilder sb){
        if(in_end < in_start || post_end < post_start) return;

        int root = post[post_end];
        int root_idx = index[root];
        sb.append(root + " ");
        pre(in_start, root_idx-1, post_start, post_start + root_idx-1-in_start, sb);
        pre(root_idx+1, in_end, post_start+root_idx-in_start, post_end -1, sb);
    }
}
/*\
 * pre 전위 순회는 root -> left child -> right child
 * in 중위 순회는 left child -> root -> right child
 * post 후위 순회는 left child -> right child -> root
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * 전위 순회에서 root를 찾을 수 있고, 중위 순회에서 left, right를 구별할 수 있다.
 * 그 말인 즉슨, 중위 순회에서 찾은 left, right를 먼저 찾고
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * in과 post를 통해 left child를 알 수 있고, 그 다음이 root라는 것을 알 수 있다.
 *
 * 전위 순회에서 찾은 root를 돌려주면 후위 순회가 완성된다는 의미.
 *
 * */