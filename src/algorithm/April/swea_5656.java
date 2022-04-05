package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 구슬 : 좌, 우로만 움직일 수 있음. 항상 맨 위에 있는 벽돌만 깨트릴 수 있음.
 * 구슬이 명중한 벽돌 : 상하좌우로 벽돌숫자-1만큼씩 제거.
 * 빈 공간 생길 경우 밑으로 떨어짐.
 */
public class swea_5656 {
    static int N, W, H, answer;
    static int[][] map;
    static int[] arr;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = swea_5656.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            answer=Integer.MAX_VALUE;

            //map 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //N개의 벽돌을 떨어트리자.
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            for(int i=0;i<H;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<W;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //map이 입력됐으면 DFS를 돌린다.
            Solve(N-1);

            sb.append("#" + t + " " + answer + " \n");
        }

        System.out.println(sb);
    }
    public static void Solve(int size){

        //base part
        if(size<0){
            int cnt = count();
            if(answer>cnt){
                answer = cnt;
            }
            return;
        }

        for(int i=0;i<W-1;i++){
            //i번째 칼럼에서 지울 애 찾기
            int[][] next = new int[H][W];
            Info target = find(i);
            if(target ==null){
                System.out.println("못찾음");
                continue;
            }
            else{
                Destroy(target.x, target.y); //지우기
                System.out.println("파괴!");
                //다 지웠으면 0으로 떙김
                down();
                System.out.println("땡겨!");
                Solve(size-1);
            }
        }
    }

    public static int count(){
        int a=0;
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                if(map[i][j]==1)a++;
            }
        }
        return a;
    }
    public static void Destroy(int x, int y){
        Queue<Info> queue = new LinkedList<>();
        HashSet<Info> hashSet = new HashSet<>();
        //파괴할 애들을 큐에 담는다.
        hashSet.add(new Info(x,y));
        queue.add(new Info(x,y));
        while(!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            int num = map[cx][cy]-1;
            queue.poll();

            if(num==0) continue;
            for(int i=0;i<N;i++){
                int nx = num*del[i][0];
                int ny = num*del[i][1];

                if(isIn(nx,ny)){
                    Info info = new Info(nx,ny);
                    if(!hashSet.contains(info)){
                        hashSet.add(info);
                    }
                    queue.add(new Info(nx,ny));
                }
            }
        }

        //지움
        Iterator<Info> iter = hashSet.iterator();
        while(iter.hasNext()){
            Info info = iter.next();
            map[info.x][info.y] =0;
        }

    }

    private static void down() {

        for (int c = 0; c < W; c++) {

            int r = H - 1;
            while(r > 0) {
                // 빈 공간이라면
                if(map[r][c] == 0) {
                    // 직전 행부터 탐색
                    int nr = r - 1;
                    // 처음 만나는 벽돌 찾기
                    while(nr > 0 && map[nr][c] == 0) nr--;

                    map[r][c] = map[nr][c];
                    map[nr][c] = 0;
                }

                r--;
            }
        }

    }


    public static Info find(int num){
        for(int i=0;i<H;i++){
            if(map[i][num] !=0){
                Info ans = new Info(i,num);
                return ans;
            }
        }
        return null;
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<H && y<W;
    }

    public static class Info{
        int x,y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
