package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  맨 첨 생각했을 때는 음... tree구조로 만들어서 전위 순회로 출력하기?
 * 인 줄 알았는데 ~~!!
 * 텍스트 자동 완성 기능과 같이 문자열을 저장하고 탐색하는데 유용한 자료구조인 트라이 알고리즘을
 * 이용하는 것 이라고 한다. 밑에 태그 봤음....ㅎ
 *
 * 각 노드는 key, value로 구성된 맵을 가지고 있다.
 *  여기서 Key는 디스크의 디렉토리,  Value는 그 Key에 해당하는 자식노드가 됨.
 *
 *  디스크 디렉토리는 문자열이고, 사전순으로 구분되어야 한다.
 *
 * */
public class bj7432 {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<String>> arr = new ArrayList<>();
        for(int i=0;i<N;i++){
            arr.add(new ArrayList<>());
        }
        for(int i=0;i<N;i++){
            //일단 스트링으로 받아서...
            String tmp = br.readLine();

            // \를 " "로 바꾼다... stringtokenizer 원래 쓰던대로 쓰려고..?
            // -------------> \로 하면 오류남. \\로 해야됨 !!!!
            tmp = tmp.replace("\\", " ");
            //System.out.println(tmp);

            st = new StringTokenizer(tmp);
            while(st.hasMoreTokens()){
                arr.get(i).add(st.nextToken());
            }

            //System.out.println(arr.get(i));
        }

        Trie trie = new Trie("");
        Trie node;

        int n =0;
        while (n != N) {
            node = trie;

            int k = arr.get(n).size();
            int K = 0;
            while (K != k) {
                String name = arr.get(n).get(K);
                int index = -1;

                for (int i = 0; i < node.list.size(); i++) {
                    if (node.list.get(i).name.equals(name)) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    node.list.add(new Trie(name));
                    node = node.list.get(node.list.size() - 1);
                } else {
                    node = node.list.get(index);
                }
                K++;
            }
            n++;
        }

        print(trie, -1);
        System.out.println(sb.toString());
    }

    public static void print(Trie trie, int depth) {

        trie.list.sort((o1, o2) -> o1.name.compareTo(o2.name));

        if(depth != -1) {
            sb.append(" ".repeat(Math.max(0, depth)));
            sb.append(trie.name).append("\n");
        }

        for(Trie tr : trie.list) {
            print(tr, depth+1);
        }

    }

    static class Trie{
        ArrayList<Trie> list;
        String name;

        Trie(String name) {
            list = new ArrayList<>();
            this.name = name;
        }

        @Override
        public String toString() {
            return "Trie{" +
                    "list=" + list +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}

/*


GAMES
 DRIVERS
HOME
WIN
 SOFT
WINNT
 DRIVERS
 SYSTEM32
  CERTSRV
   CERTCO~1
    X86
  CONFIG
 */