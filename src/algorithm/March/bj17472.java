package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj17472 {
    static int N, M, n;
    static int[][] map;
    static boolean[][] visit;
    static int[][] del = {{1,0},{-1,0},{0,1},{0,-1}}; //다리는 one direction으로만 이동 가능.
    static Queue<Info> queue;
    static PriorityQueue<Node> pq;
    static int[] parent;
    static boolean[] check;
    static int answer;
    public static void main(String[] args) throws IOException {
        InputStream input = bj17472.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit  = new boolean[N][M];

        //입력
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //map에서 다 1이니까 아파트 동처럼 호수를 다르게 바꿔주기.
        n=1;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visit[i][j] && map[i][j] ==1){
                    change(i,j, n);
                    n++;
                }
            }
        }

        //출력
        /*
        for(int i=0;i<N;i++){
            System.out.println(Arrays.toString(map[i]));
        }
        */
        n -=1; //마지막에 n++하니까 하나 빼준게 섬 개수
        //System.out.println("섬 개수 n : " + (n));

        //섬 저장.
        parent = new int[n+1];
        check = new boolean[n+1];

        //섬 초기 부모 저장
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        visit  = new boolean[N][M];
        pq = new PriorityQueue<>();

        //섬끼리 연결해줌
        for(int i=0;i<N;i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    findShort(i, j);
                }
            }
        }
        //System.out.println(pq);

        int cnt=0;
        int size = pq.size();
        for(int i=0;i<size;i++){
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;

            int a = find(from);
            int b = find(to);
            if(a!=b) {
                union(from,to);
                answer += node.weight;
                cnt++;
            }
        }

        if(answer !=0 && cnt == n-1){
            System.out.println(answer);
        }else{
            System.out.println(-1);
        }


    }

    public static int find(int x){
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            parent[x] = y;
        }else{
            return;
        }

    }

    public static void findShort(int i, int j){
        int cx = i;
        int cy = j;
        int num = map[i][j];
        int len =0;

        for(int d=0;d<4;d++){
            while (true){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];
                if(isIn(nx,ny)){
                    if(map[nx][ny] == num){//나
                        len =0;
                        cx = i;
                        cy = j;
                        break;
                    }else if(map[nx][ny] ==0){//빈공간
                        len +=1;
                        cx = nx;
                        cy = ny;
                    }else{ //누군가 만남
                        if(len>1){ // 다리 됨
                            pq.offer(new Node(num, map[nx][ny], len));
                            len=0;
                            cx = i;
                            cy = j;
                            break;
                        }else{ //다리 안됨
                            len=0;
                            cx = i;
                            cy = j;
                            break;
                        }
                    }
                }else{ //넘어감
                    len=0;
                    cx = i;
                    cy = j;
                    break;
                }
            }
        }

    }

    public static class Node implements Comparable<Node>{
        int from, to, weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight-o.weight;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static class Info{
        int x,y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void change(int x, int y, int n){
        queue = new LinkedList<>();
        visit[x][y] = true;
        queue.add(new Info(x,y));

        while(!queue.isEmpty()) {
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            map[cx][cy] = n;
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cx + del[i][0];
                int ny = cy + del[i][1];

                if (isIn(nx, ny) && map[nx][ny] == 1 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    queue.offer(new Info(nx, ny));
                }
            }
        }
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }
}
