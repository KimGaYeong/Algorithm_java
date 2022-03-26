package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 트리의 지름 -> 트리의 깊이!!를 찾는 것이 제일 중요하다.
 * 트리의 깊이 -> 깊이우선탐색, DFS를 사용하자
 */
public class bj1167 {
    static int V;
    static ArrayList<Node>[] tree;
    static int longestnode, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V+1];
        for(int i=1;i<V+1;i++){
            tree[i] = new ArrayList<>();
        }
        //tree 구성하기
        for(int i=0;i<V;i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            while(true){
                int from = Integer.parseInt(st.nextToken());
                if(from != -1){
                    int weight = Integer.parseInt(st.nextToken());
                    tree[to].add(new Node(from, weight));
                }else{
                    break;
                }
            }
        }

        dfs(1,0,0);
        count=0;
        dfs(longestnode, 0,0);
        System.out.println(count);
    }
/*
처음 작성 코드  : 객체 사용 X, int[][] 배열 사용.  따라서 배열의 크기 만큼 0이 아닌 부분
            (노드끼리 이어지는 부분)을 탐색해야 하므로 V*V만큼 반복문 돌게 됨.
            visit으로 각 노드에 방문 했는지 안해줬는지를 따졌음
            --> 메모리 초과.

정답 처리된 코드 :  객체를 사용하고 V만큼이 아니라 node가 가지고 있는 이어진 노드의 갯수 만큼 반복문 돔
            --> 통과

 */
    public static class Node{
        int to;
        int weight;

        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

    }
    public static void dfs(int x, int p, int y){

        if(count < y){
            count = y;
            longestnode = x;
        }

        for(int i=0;i<tree[x].size();i++){
            if(tree[x].get(i).to == p) continue;
            dfs(tree[x].get(i).to, x, y + tree[x].get(i).weight);
        }
    }

    /*

    다익스트라로 모든 각 정점에서 최대로 긴 거리를 구하고 그 거리들 중에서 최대값 구하면?? -> 시간초과잼
    정점 간 거리를 기준으로 경로를 결정. 따라서 가장 긴 경로는 한 노드에서 가장 멀리 있는 노드와 겹침.

    */
}
