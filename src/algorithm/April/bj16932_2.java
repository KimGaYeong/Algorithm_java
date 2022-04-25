package algorithm.April;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
N×M인 배열에서 모양을 찾으려고 한다. 배열의 각 칸에는 0과 1 중의 하나가 들어있다.
 두 칸이 서로 변을 공유할때, 두 칸을 인접하다고 한다.
1이 들어 있는 인접한 칸끼리 연결했을 때, 각각의 연결 요소를 모양이라고 부르자.
모양의 크기는 모양에 포함되어 있는 1의 개수이다.
배열의 칸 하나에 들어있는 수를 변경해서 만들 수 있는 모양의 최대 크기를 구해보자.
 */

public class bj16932_2 {

    static int n;
    static int m;
    static int[][] del = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String args[]) throws IOException {

        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int groupN = 2;
        int[] groupSum = new int[500002];
        Queue<Info> queue = new LinkedList();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j]==1){
                    makeGroup(map, new Info(i, j), groupN, groupSum);
                    groupN++;
                }else if(map[i][j]==0) queue.add(new Info(i, j));
            }
        }
        int solution = 0;

        while(!queue.isEmpty()){
            Info info = queue.poll();
            solution = Math.max(solution, Solve(map, new Info(info.y, info.x), groupSum));
        }
        System.out.println(solution);

    }
    public static int Solve(int[][] map, Info START, int[] groupsize){
        int cx = START.x;
        int cy = START.y;
        int tmp = 1;

        HashSet<Integer> set = new HashSet();
        for(int[] d : del){
            int nx = cx+d[0];
            int ny = cy+d[1];

            if(!isIn(nx,cy)) continue;
            int curGroup = map[nx][ny];
            if(curGroup>1 && !set.contains(curGroup)){
                tmp += groupsize[curGroup];
                set.add(curGroup);
            }
        }
        return tmp;
    }

    public static void makeGroup(int[][] map, Info START, int groupN,
                                 int[] groupsize){
        Queue<Info> Q = new LinkedList();
        Q.add(START);

        int sum = 0;
        while(!Q.isEmpty()){
            Info info = Q.poll();
            int cx = info.x;
            int cy = info.y;

            if(map[cx][cy]!=1) continue;
            map[cx][cy] = groupN;
            sum++;

            for(int[] dist : del){
                int nx = cx+dist[0];
                int ny = cy+dist[1];

                if(!isIn(nx,ny)) continue;
                if(map[nx][ny]==1) {
                    Q.add(new Info(nx, ny));
                }
            }
        }
        groupsize[groupN] = sum;
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<n && y<m;
    }


    public static class Info {
        int y;
        int x;
        public Info(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
