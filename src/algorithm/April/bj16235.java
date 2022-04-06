package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 같은 칸에 여러 개 나무 심을 수 있음.
 * 봄 : 나무 나이만큼 양분 섭취, 나무 나이 +1 (나이가 어린 나무부터 양분 섭취,자신 나이만큼 양분 못 먹을 경우 죽음)
 * 여름 : 봄에 죽은 나무가 양분으로 변해 (죽은 나무 나이 /2 )만큼이 나무가 있던 칸에 양분으로 추가.
 * 가을 : 나이가 5의 배수인 나무가 번식. 인접한 8개의 칸에 나이가 1인 나무가 생김. (상하좌우, 대각선)
 * 겨울 : 로봇이 돌아다니며 양분을 추가
 */
public class bj16235 {
    static int N, M, K;
    static int[][] map, add;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1},{1,1},{-1,-1},{1,-1},{-1,1}};
    static Queue<Tree> alive, dead;
    static PriorityQueue<Tree> trees;
    public static void main(String[] args) throws IOException {
        InputStream input = bj16235.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        //입력
        N = Integer.parseInt(st.nextToken()); // 땅 한 변의 크기
        M = Integer.parseInt(st.nextToken()); // 초기 나무 개수
        K = Integer.parseInt(st.nextToken()); // K년 후 살아있는 나무의 개수 구하기

        //땅(양분) 입력받기
        map = new int[N][N];
        add = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = 5;
                add[i][j] = Integer.parseInt(st.nextToken());
            }
            //System.out.println(Arrays.toString(map[i]));
        }

        trees = new PriorityQueue<>();
        //나무 입력받기
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x,y,z));
        }
        alive = new LinkedList<>();
        dead = new LinkedList<>();

        int cnt =0;
        // 계절 Cycle
        while(K-->0){ //K:1000, M:100, 8 -> 800000
            spring();

            summer();

            fall();

            winter();
        }

        System.out.println(trees.size());
    }

    private static void winter() {
        //겨울
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] += add[i][j];
            }
        }
    }

    private static void fall() {
        //가을
        // 나이가 5배수인 애들이 번식함.
        while(!alive.isEmpty()){
            Tree tree = alive.poll();
            int x = tree.r;
            int y = tree.c;
            int age = tree.age;

            trees.add(tree);

            if(age%5 ==0){
                for (int d=0;d<8;d++) {
                    int nx = x + del[d][0];
                    int ny = y + del[d][1];
                    if(isIn(nx,ny)){
                        trees.add(new Tree(nx,ny, 1));
                    }
                }
            }
        }
    }

    private static void summer() {
        // 여름
        // 봄에 죽은 나무가 양분으로 변함. (죽은 나무 나이 /2)
        while (!dead.isEmpty()) {
            Tree tree = dead.poll();
            int x = tree.r;
            int y = tree.c;
            int age = tree.age;
            map[x][y] += (int) (age/2);
        }
    }

    private static void spring() {
        //봄
        while(!trees.isEmpty()){
            Tree tree = trees.poll();
            int x = tree.r;
            int y = tree.c;
            int age = tree.age;

            // 자기 나이만큼 먹고, 먹으면 나이 1 증가.
            if (age <= map[x][y]) {
                map[x][y] -= age;
                age += 1;
                alive.add(new Tree(x, y, age));
            }
            // 못먹으면 죽음.
            else {
                dead.add(new Tree(x, y, age));
            }
        }
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    public static class Tree implements Comparable<Tree>{
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age-o.age;
        }
    }

}
