import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1103 {
    static int N, M;
    static int result;
    static boolean checking;
    static int[][] cnt;
    static char[][] board;
    static int[][] del = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        cnt = new int[N][M];

        for(int i=0;i<N;i++){
            String tmp = br.readLine();
            for(int j=0;j<M;j++){
                board[i][j] = tmp.charAt(j);
            }
        }

        result =BFS(0,0);
        if(checking){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }
    }

    public static int BFS(int x, int y){
        Queue<xy> queue = new LinkedList<>();
        queue.offer(new xy(x, y)); //x,y를 큐에 삽입
        int count=0;
        while(!queue.isEmpty()){
            //System.out.print ("현재 queue의 size는 " + queue.size() + "=====>>");
            count+=1;
            int size = queue.size();
            for(int i=0;i<size;i++) { //size를 구해주지 않고 여기로 바로 넣었더니 바로바로 안정해져서 틀림.
                int c_x = queue.peek().x; //queue에서 x,y 저장.
                int c_y = queue.peek().y;
                queue.poll(); //queue에서 제거
                for (int d = 0; d < 4; d++) {
                    int n_x = c_x + ((board[c_x][c_y] - '0') * del[d][0]);
                    int n_y = c_y + ((board[c_x][c_y] - '0') * del[d][1]);
                    if (isIn(n_x, n_y) && cnt[n_x][n_y] < count) { //카운트를 세야됨.
                        System.out.println("n_x : " + n_x + ", n_y : " + n_y + ", cnt[nx][ny] : " + cnt[n_x][n_y] + ", count : " + count);
                        cnt[n_x][n_y] = count;
                        queue.offer(new xy(n_x, n_y));
                    }
                }
            }
            /*
            System.out.println("-----------");
            for(int[] a : cnt){
                for(int ar : a){
                    System.out.print(ar + " ");
                }
                System.out.println();
            }
            System.out.println("count is " + count);
            */
            if(count > 50*50){
                checking = true;
                break;
            }
        }
        return count;
    }
    static boolean isIn(int r, int c){
        return 0<=r && 0<=c && r<N && c<M && board[r][c] != 'H';
    }

    public static class xy{
        int x, y ;//좌표를 저장
        xy(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
