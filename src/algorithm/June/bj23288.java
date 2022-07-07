package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj23288 {
    static int N, M, K, result;
    static int whatDel, cx, cy;
    static int[][] del = {{-1,0},{0,1},{1,0},{0,-1}}; //북 동 남 서
    static int[][] map;
    static int[][] dice = {
                            { 0, 2, 0, 0 },
                            { 4, 1, 3, 6 }, //dice[1][3] = dice[3][1]
                            { 0, 5, 0, 0 },
                            { 0, 6, 0, 0 }
                          };
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        /*
          2
        4 1 3
          5 주사위 양 면의 합은 7
          6

          주사위가 이동 방향으로 한 칸 굴러간다. 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
        주사위가 도착한 칸 (x, y)에 대한 점수를 획득한다.
        주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x, y)에 있는 정수 B를 비교해 이동 방향을 결정한다.
        A > B인 경우 이동 방향을 90도 시계 방향으로 회전시킨다.
        A < B인 경우 이동 방향을 90도 반시계 방향으로 회전시킨다.
        A = B인 경우 이동 방향에 변화는 없다.
         */

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        whatDel = 1; //주사위의 현재 이동 방향. 동쪽을 보고 있음
        cx = 0; //주사위의 현재 좌표
        cy = 0;

        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        for (int i = 0; i < K; i++) {
            move();
        }

        System.out.println(result);

    }

    private static void move() { //주사위의 이동.

        //현재 주사위 좌표
        int nx = cx + del[whatDel][0];
        int ny = cy + del[whatDel][1];

        //3,4에서 2였는데
        //System.out.println(nx + " " + ny + " and " + isIn(nx,ny));

        if (!isIn(nx,ny)) {
            //이동 방향을 반대로 한 다음 한 칸 굴러간다.
            if(whatDel==1) whatDel=3;
            else whatDel = Math.abs(whatDel-2); //북 동 남 서. 북0<->남2 동1<->서3
            nx = cx + del[whatDel][0];
            ny = cy + del[whatDel][1];
        }

        // 현재 좌표 이동~
        cx = nx;
        cy = ny;

        // 한 칸 데굴!
        if (whatDel == 0) { // 북 : 즉 위로 데굴
            /*
            { 0, 2, 0, 0 },
            { 4, 1, 3, 6 }, 얘가 위로 한칸 데굴 굴러가면 (얘를 들어서 십자가가 겹치는쪽(1)을 위로 정육면체에 감싼다는 느낌으로 생각하면 됨)
            { 0, 5, 0, 0 },
            { 0, 6, 0, 0 }

            -> 1이 맨 위에, 3이 맨 오른쪽에 있는 애가 한칸 위로 굴러간다고 생각하면 사실 양 옆(좌우)는 그대로.
            좌우는 그대로지만 위로 데굴하므로 상하에 있는 칸이 밀린다.
            즉 지금 앞을보는게 2 위를보는게 1 뒤를보는게 5 밑을보는게 6
            ----> 앞을보는게 1 위를보는게 5 뒤를보는게 6 밑을보는게 1 이렇게 바뀐다.

            { 0, 1, 0, 0 },
            { 4, 5, 3, 6 }, 얘로 바뀐다.
            { 0, 6, 0, 0 },
            { 0, 2, 0, 0 }
            이런식으로 밀어줌.
             */
            int tmp = dice[0][1]; //2
            for (int i = 0; i < 3; i++) {
                dice[i][1] = dice[i + 1][1]; //한칸씩 밀고
            }
            dice[3][1] = tmp; // 맨위에 있는애를 2로변경.
            //여기서는 상하로 밀지만 좌우로 밀때도 있으므로 [1][3]칸도 꼭 채워줘야한다.
            dice[1][3] = dice[3][1];
        } else if (whatDel == 1) {//동
            int tmp = dice[1][3];
            for(int j = 3; j > 0; j--) {
                dice[1][j] = dice[1][j-1];
            }
            dice[1][0] = tmp;
            dice[3][1] = dice[1][3];
        } else if (whatDel == 2) {//남
            int tmp = dice[3][1];
            for (int i = 3; i > 0; i--) {
                dice[i][1] = dice[i - 1][1];
            }
            dice[0][1] = tmp;
            dice[1][3] = dice[3][1];
        } else {//서
            int tmp = dice[1][0];
            for(int j = 0; j < 3; j++) {
                dice[1][j] = dice[1][j+1];
            }
            dice[1][3] = tmp;
            dice[3][1] = dice[1][3];
        }

        // 점수 획득
        //주사위가 도착한 칸 (x, y)에 대한 점수를 획득한다.

        result += getScores();
        //주사위의 아래에 있는건 밑을 보는애니까 dice[3][1]!
        /*
        북0 동1 남2 서3
         */
        int A = dice[3][1];
        int B = map[cx][cy];
//        A > B인 경우 이동 방향을 90도 시계 방향으로 회전시킨다. : 동(1)->남(2)->서(3)->북(0)->동(1)
//        A < B인 경우 이동 방향을 90도 반시계 방향으로 회전시킨다.: 동(1)->북(0)->서(3)->남(2)->동(1)
//        A = B인 경우 이동 방향에 변화는 없다.
        if(A > B) {
            if(whatDel==3) whatDel=0;
            else{
                whatDel +=1;
            }
        }else if(A < B) {
            if(whatDel==0) whatDel=3;
            else{
                whatDel -=1;
            }
        }
    }

    public static int getScores() {
        Queue<Info> queue = new LinkedList<>();
        boolean visit[][] = new boolean[N][M];

        visit[cx][cy] = true;
        queue.add(new Info(cx, cy));

        /*
        칸 (x, y)에 대한 점수는 다음과 같이 구할 수 있다.
        (x, y)에 있는 정수를 B라고 했을때,
        (x, y)에서 동서남북 방향으로 연속해서 이동할 수 있는 칸의 수 C를 모두 구한다.
        이때 이동할 수 있는 칸에는 모두 정수 B가 있어야 한다. 여기서 점수는 B와 C를 곱한 값이다.
         */
        //System.out.println(cx + " " + cy + " " + whatDel) ;
        int B = map[cx][cy];
        int C = 0;
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            C++;
            for (int d = 0; d < 4; d++) {
                int nx = info.x + del[d][0];
                int ny = info.y + del[d][1];
                if (isIn(nx,ny) && !visit[nx][ny] &&map[nx][ny] == B) {
                    visit[nx][ny] = true;
                    queue.add(new Info(nx, ny));
                }
            }
        }
        return B * C;
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
