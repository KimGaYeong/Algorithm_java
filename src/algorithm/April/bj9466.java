package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * cycle이 만들어져야 할텐데.. cycle이 없는 애는 안되는 것. 자기 자신을 뽑은 경우 cycle이 된다.
 * 각 테스트 케이스의 첫 줄에는 학생의 수가 정수 n (2 ≤ n ≤ 100,000)으로 주어진다.
 * 10000000000 -> 백억...3초여도 안됨. 선형 시간에 해보자.
 */
public class bj9466 {
    static int T, n, result, cnt;
    static int[] student;
    static boolean[] checked;
    static boolean[] isFinishDFS;
    static Queue<Integer> queue;
    static int[] pre;
    public static void main(String[] args) throws IOException {
        InputStream input = bj9466.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            n = Integer.parseInt(br.readLine());
            student = new int[n];
            checked = new boolean[n]; //DFS를 시작한 배열인지?
            isFinishDFS = new boolean[n]; //사이클 확인이 됐는지 안됐는지 확인.
            pre = new int[n]; // 내 전 애들을 확인.
            st= new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                student[i] = Integer.parseInt(st.nextToken())-1;
            }

            // 팀에 속하지 못한 학생 수 나타내기.
            // 즉, cycle이 만들어지지 못한 사람을 찾으면 된다. -> cycle을 찾자.
            // DFS 사용하자.

            result =0;
            for(int i=0;i<n;i++){
                cnt =0;
                if(!checked[i]) DFS(i);
                /*
                for(int j=0;j<n;j++){
                    if(isFinishDFS[j]) System.out.print("T ");
                    else System.out.print("F ");
                }
                System.out.println("cnt : " + cnt + " ");
                */
            }

            sb.append((n-result) + "\n");
        }
        System.out.println(sb);

    }

    /*
    어짜피 한 노드당 사이클은 한개씩만 나온다. (사이클이 나오려면 ㅁ-> ㅂ -> ..... -> ㅁ 되어야 하므로.

    사이클에 포함된 노드들은 어짜피 항상 자기 사이클끼리 연결한다. (그래야 사이클이니까)
    사이클이 아닌 애들은 사이클인 애들한테 팀을 하자고 요구하게 될 수 있다. (alone -> Someone in cycle)


    뒤에 나오게될 노드들이 자기 앞의 어떤 다른 노드를 시작점으로 한 탐색에서 확인을 마친 노드들인지,
    이번 탐색에서 탐색을 마친 노드들인지 확인
     */
    public static void DFS(int n){
        //
        checked[n] = true;
        //다음 사람
        int next = student[n];
        //나랑 이어진 다음 사람을 아직 방문하지 않았다면?
        if(!checked[next]){
            pre[next] = n; //현재 next의 바로 전 친구는 n이다. (나중에 cycle 만들어지면 개수 세려고)
            DFS(next);
        }else{
            //나랑 이어진 다음 사람은 이미 탐색을 시작한 누군가임.

            // 근데 아직 DFS가 안끝났다 ??!!! (내 사이클이라는 의미)
            if(!isFinishDFS[next]){
                findcycle(n, next);
            }else{
                // 이미 cycle 확인이 끝난 친구.
                // System.out.println("그냥 지나갑니다~");
            }
        }

        isFinishDFS[n] = true;

    }

    public static void findcycle(int cur, int next){
        cnt++;
        if(cur == next){
            result += cnt;
            return; //cycle을 끝내자
        }
        else{
            //cycle의 첫번째 칭구를 찾자~
            findcycle(pre[cur], next);
        }
    }
}

/*
0 1 2 3 4 5 6
2 0 2 6 2 3 5

 */