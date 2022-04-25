package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
N×M인 배열에서 모양을 찾으려고 한다. 배열의 각 칸에는 0과 1 중의 하나가 들어있다. 두 칸이 서로 변을 공유할때, 두 칸을 인접하다고 한다.
1이 들어 있는 인접한 칸끼리 연결했을 때, 각각의 연결 요소를 모양이라고 부르자. 모양의 크기는 모양에 포함되어 있는 1의 개수이다.
배열의 칸 하나에 들어있는 수를 변경해서 만들 수 있는 모양의 최대 크기를 구해보자.
 */
public class bj16932 {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static List<Integer> groupsize;
    static Queue<Info> queue;
    static int[][] del = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj15988.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        Queue<Info> list = new LinkedList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] ==0) list.add(new Info(i,j));
            }
        }
        int tmpsize = 0;
        int result = 0;
        groupsize = new ArrayList<>();
        groupsize.add(0);
        //그룹 구별하고, 그룹별 사이즈 구하기.
        int k=1;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visit[i][j] && map[i][j]==1){
                    MakeGroup(i,j,k);
                    k+=1;
                }
            }
        }

        //System.out.println(groupsize);

        Set<Integer> group = new HashSet<>();
        while(!list.isEmpty()){
            Info info = list.poll();
            //0 리스트를 돌면서 사방에 숫자가 있는지 확인. 숫자가 2개 이상이면 그자리 1로 바꾸고 BFS 돌려보기.
            visit = new boolean[N][M];

            int groupcnt = 0;
            group = new HashSet<>();
            tmpsize = 1;
            for(int d=0;d<4;d++){
                int nx = info.x + del[d][0];
                int ny = info.y + del[d][1];

                if(isIn(nx,ny) && map[nx][ny] !=0 && !group.contains(map[nx][ny])) {
                    group.add(map[nx][ny]);
                    tmpsize += groupsize.get(map[nx][ny]);
                }
            }

            result = Math.max(result, tmpsize);
        }
        System.out.println(result);
    }

    public static void MakeGroup(int x, int y, int k){
        int size = 0;
        queue = new LinkedList<>();
        queue.add(new Info(x,y));
        visit[x][y] = true;

        while(!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();
            size++;
            map[cx][cy] = k;

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(isIn(nx,ny) && !visit[nx][ny] && map[nx][ny] !=0){
                    visit[nx][ny] = true;
                    queue.add(new Info(nx,ny));
                }
            }
        }

        groupsize.add(size);
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

    public static class Info{
        int x,y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
