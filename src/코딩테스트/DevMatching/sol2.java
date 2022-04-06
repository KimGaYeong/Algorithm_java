package 코딩테스트.DevMatching;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class sol2 {
    static int count=0;
    static int N, M;
    static ArrayList<Info> infos;
    static int[][] map;
    static int[][] del = {{0,-1},{0,1},{1,0},{-1,0}};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String[] grid = {"aa?"};
        int ans = solution(grid);
        System.out.println(ans);
    }

    public static int solution(String[] grid) {
        infos = new ArrayList<>();
        N = grid.length;
        M = grid[0].length();
        map = new int[N][M];
        for(int i=0;i<N;i++){
            String tmp = grid[i];
            for(int j=0;j<M;j++){
                //채워야 할 부분을 저장.
                if(tmp.charAt(j) =='?'){
                    if(map[i][j] == 0){
                        infos.add(new Info(i,j));
                    }
                }else{
                    map[i][j] = tmp.charAt(j)-'a'+1;
                }
            }
            //여기에 1, 2, 3을 채워 연결되는지를 확인하면 됨.
            //System.out.println(Arrays.toString(map[i]));
        }

        int size = infos.size();
        //System.out.println(size);
        BFS(size-1);

        //System.out.println(count);
        return count;
    }

    public static void BFS(int cnt){
        //base part
        if(cnt<0){
            //연결 됐는지 check 함수!
            for(int i=0;i<N;i++){
                System.out.println(Arrays.toString(map[i]));
            }
            System.out.println();
            if(check()) count++;
            return;
        }

        //inductive part
        int cx = infos.get(cnt).x;
        int cy = infos.get(cnt).y;

        for(int i=1;i<=3;i++){
            if( map[cx][cy]==0){
                map[cx][cy] = i;
                BFS(cnt-1);
                map[cx][cy] = 0;
            }
        }
    }

    public static boolean check(){
        boolean ca = false;
        boolean cb = false;
        boolean cc = false;

        int answer = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] ==1 && !ca){
                    System.out.println("1개수 : " + find(i,j, 1));
                    answer += find(i,j, 1);
                    ca = true;
                }
                if(map[i][j] ==2 && !cb){
                    System.out.println("2개수 : " + find(i,j, 2));
                    answer += find(i,j, 2);
                    cb = true;
                }
                if(map[i][j] ==3 && !cc){
                    System.out.println("3개수 : " + find(i,j, 3));
                    answer += find(i,j,3);
                    cc = true;
                }
            }
        }

        System.out.println(answer + "-> 합 " + "총개수 : " + (N*M));
        if(answer == N*M){
            return true;
        }else{
            return false;
        }
    }

    public static int find(int x, int y, int target){
        int ans = 1;
        boolean[][] visit = new boolean[N][M];
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(x,y));
        visit[x][y] = true;

        while(!queue.isEmpty()){
            int cx = queue.peek().x;
            int cy = queue.peek().y;
            queue.poll();

            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(nx>=0 && ny>=0 && nx<N && ny<M && map[nx][ny]==target && !visit[nx][ny]){
                    visit[nx][ny] = true;
                    queue.add(new Info(nx,ny));
                    ans+=1;
                }
            }
        }

        return ans;
    }

    public static class Info{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
