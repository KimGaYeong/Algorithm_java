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

    public static void main(String[] args) throws IOException {
        InputStream input = bj2580.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        //입력
        int N = Integer.parseInt(st.nextToken()); // 땅 한 변의 크기
        int M = Integer.parseInt(st.nextToken()); // 초기 나무 개수
        int K = Integer.parseInt(st.nextToken()); // K년 후 살아있는 나무의 개수 구하기
        int[][] del = {{-1,0},{1,0},{0,-1},{0,1},{1,1},{-1,-1},{1,-1},{-1,1}};

        //땅(양분) 입력받기
        int[][] map = new int[N][N];
        int[][] add = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = 5;
                add[i][j] = Integer.parseInt(st.nextToken());
            }
            //System.out.println(Arrays.toString(map[i]));
        }

        //나무 입력받기
        ArrayList<Tree> trees = new ArrayList<>();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x,y,z));
        }
        //Collections.sort(trees);
        ArrayList<Tree> DeadTree = new ArrayList<>();

        int cnt =0;
        // 계절 Cycle
        while(true){ //K:1000, M:100, 8 -> 800000
            if(cnt==K) break;
            Iterator<Tree> iterator = trees.iterator();
            //봄
            while(iterator.hasNext()){
                Tree t = iterator.next();
                //나무가 자기 나이만큼 양분 먹음. (r,c)에 양분이 내 나이보다 크거나 같으면 먹고 없으면 나무 쥬금
                if(map[t.r][t.c] >= t.age){
                    map[t.r][t.c]-=t.age;
                    t.age+=1;
                }else{
                    //죽은 나무들은 여름에 양분이 된다.
                    DeadTree.add(t);
                    iterator.remove();
                }
            }

            //여름
            for(Tree t : DeadTree){
                map[t.r][t.c] += (int)(t.age/2);
            }

            //가을
            int tsize = trees.size();
            for(int i=0;i<tsize;i++){
                Tree t = trees.get(i);
                if(t.age%5 ==0){
                    for(int d=0;d<8;d++){
                        int nx = t.r + del[d][0];
                        int ny = t.c + del[d][1];
                        if(nx>=0 && ny>=0 && nx<N && ny<N){
                            trees.add(new Tree(nx,ny,1));
                        }
                    }
                }
            }
            //겨울
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    map[i][j] += add[i][j];
                }
            }
            cnt++;
        }

        System.out.println(trees.size());
    }
    public static class Tree{
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }


    }

}
