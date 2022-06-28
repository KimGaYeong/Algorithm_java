package algorithm.June;

import java.util.*;

public class pg카드짝맞추기 {
    public static void main(String[] args) {
        int[][] board = new int[][]{{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
        /*
        1 0 0 3
        2 0 0 0
        0 0 0 2
        3 0 1 0
         */
        int r = 1;
        int c = 0;
        System.out.println(solution(board, r, c));
    }

    /*
    방향키 ←, ↑, ↓, → 중 하나를 누르면, 커서가 누른 키 방향으로 1칸 이동
    [Ctrl] 키를 누른 상태에서 방향키 ←, ↑, ↓, → 중 하나를 누르면, 누른 키 방향에 있는 가장 가까운 카드로 한번에 이동
    해당 방향에 카드가 하나도 없다면 그 방향의 가장 마지막 칸으로 이동

    앞면이 보이는 카드가 1장 뿐이라면 그림을 맞출 수 없으므로 두번째 카드를 뒤집을 때 까지 앞면을 유지합니다.
    앞면이 보이는 카드가 2장이 된 경우, 두개의 카드에 그려진 그림이 같으면 해당 카드들이 화면에서 사라지며,
    그림이 다르다면 두 카드 모두 뒷면이 보이도록 다시 뒤집힙니다.


    " 남은 카드를 모두 제거하는데 필요한 키 조작 횟수의 최솟값 "
    //7번 부재중 ->
     */


    //일단 "최소" 조작이니까 BFS를 사용해야겠다.
    static boolean[][] check;
    static int[][] map;
    static final int INF = 987654321;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static int solution(int[][] board, int r, int c) {
        int answer = 0;

        map = board;
        answer = getOrder(new Info(r,c,0));

        return answer;
    }

    //카드 지우는 순서 정하기
    /*
    board 배열의 각 원소는 0 이상 6 이하인 자연수입니다.
    0은 카드가 제거된 빈 칸을 나타냅니다.
    1 부터 6까지의 자연수는 2개씩 들어있으며 같은 숫자는 같은 그림의 카드를 의미합니다.
    뒤집을 카드가 없는 경우(board의 모든 원소가 0인 경우)는 입력으로 주어지지 않습니다.
    r과 c는 0 이상 3 이하인 정수입니다.
     */
    public static int getOrder(Info target){

        int max = INF;
        for(int i=1;i<=6;i++){

            List<Info> list = new ArrayList<>();

            for(int r=0;r<4;r++){
                for(int c=0;c<4;c++){
                    if(map[r][c] == i) list.add(new Info(r,c,0));
                }
            }

            if(list.size()==0) continue;
            System.out.println("list : " + list);
        /*
        1 0 0 3
        2 0 0 0
        0 0 0 2
        3 0 1 0
         */
            //카드는 두장 있다! 뭐부터 할 지 결정하자.
            Info first = list.get(0);
            Info second = list.get(1);
            //System.out.println("--- "+first + " " +second);
            //결정했으면 비교해야됨
            //첫번째꺼부터
            int case1 = solution(target, first) + solution(first, second) +2;//Enter 입력 2번 추가
            // 두번째꺼부터
            int case2 = solution(target, second) + solution(second, first) +2;

            //System.out.println(case1 + " " + case2);

            //
            map[first.x][first.y] = map[second.x][second.y] = 0;

            //둘중에 작은거를 답으로.
            int result1 = Math.min(max, case1+getOrder(second));
            int result2 = Math.min(max, case2+getOrder(first));

            max = Math.min(max,Math.min(result1, result2));

            map[first.x][first.y] = map[second.x][second.y] = i;
        }

        if(max == INF){
            return 0;
        }else{
            return max;
        }
    }

    public static int solution(Info start, Info dest){
        check = new boolean[4][4];
        Queue<Info> queue = new LinkedList<>();
        int x = start.x;
        int y = start.y;
        queue.add(start);
        check[x][y] = true;

        while(!queue.isEmpty()){
            Info info = queue.poll();
            int cx = info.x;
            int cy = info.y;

            //목표지점 도착
            if(cx == dest.x && cy == dest.y){
                return info.cnt;
            }

            //아직 도착 안했을 경우

            //우선 사방향부터 본다.
            for(int d=0;d<4;d++){
                int nx = cx + del[d][0];
                int ny = cy + del[d][1];

                if(!isIn(nx,ny)) continue;
                if(!check[nx][ny]){
                    check[nx][ny] = true;
                    queue.add(new Info(nx,ny,info.cnt+1));
                }

                //컨트롤+사방향까지 탐색한다.
                for(int i=0;i<2;i++){
                    if(map[nx][ny]!=0) break;
                    int nx2 = nx + del[d][0];
                    int ny2 = ny + del[d][1];
                    if(!isIn(nx2, ny2)) break;
                    nx = nx2;
                    ny = ny2;
                }

                if(!check[nx][ny]){
                    check[nx][ny] = true;
                    queue.add(new Info(nx, ny, info.cnt+1));
                }
            }

        }
        return INF;
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<4 && y<4;
    }

    public static class Info{
        int x, y, cnt;

        public Info(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }


        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}
