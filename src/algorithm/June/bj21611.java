package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
방향과 거리가 지정되면 그 칸에있는 구슬이 모두 파괴됨.
빈칸이 생기면 구슬이 밀림.
번호순으로 구슬이 4개 이상 붙으면 폭발.
(더이상 폭발할 구슬이 없을 때 까지)
더이상 폭발할 구슬이 없으면 구슬이 변화함.
(연속 구슬이 하나의 그룹이 된다.
하나의 그룹이 두개의 구슬 A, B로 변함
구슬 A의 번호는 그룹에 들어있는 구슬의 개수,  B의 번호는
그룹을 이루고 있는 구슬의 번호)

 */
public class bj21611 {
    static int N;
    static int s_rc;
    static int[] lineMap;
    static int[][] map;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    static int[][] del2 = {{1,0},{0,1},{-1,0},{0,-1}}; //아래 오른쪽 위 왼쪽..
    static int[] bombs;//
    static int[] bombcnt = new int[3];
    static boolean flag;
    public static void main(String[] args) throws IOException {
        InputStream input = bj21611.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        s_rc = (N/2);
        int M = Integer.parseInt(st.nextToken());
        lineMap = new int[N*N];
        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        map[s_rc][s_rc] = 5; //(상어위치)

        for(int i=0;i<M;i++){
            //마법이 들어갈 방향, 거리
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());

            // 마법!
            doMagic(d,s);
            // 한줄로 펴서 0을 지우고 다시 한줄로 만들기.
            MakeLineMapIdx();
            // 구슬 폭발
            flag = true;
            while(flag){
                doBomb();
            }
            //System.out.println("폭발 끝");

            change();
            changeMap();
        }
//        System.out.println(Arrays.toString(bombcnt));
        System.out.println(cal());
    }

    private static int cal(){
        int result =0;

        for(int i=0;i<3;i++){
            result += (i+1)*bombcnt[i];
        }

        return result;
    }

    private static void change(){
        //연속하는 구슬은 하나의 그룹
        //연속하는 구슬이 변합니다
        //하나의 그룹은 두 개의 구슬 A와 B로 변한다.
        // 구슬 A의 번호는 그룹에 들어있는 구슬의 개수이고,
        // B는 그룹을 이루고 있는 구슬의 번호이다.
        // 구슬은 다시 그룹의 순서대로 1번 칸부터 차례대로 A, B의 순서로 칸에 들어간다.

        // 2 2 2 -> 3(개의) 2(번구슬)로 변함
        // 1 1 -> 2(개의) 1(번구슬)로 변함
        // 2 -> 1(개의) 2(번구슬)로 변함.

        Queue<Integer> mystack = new LinkedList<>();
        mystack.add(bombs[0]);
        //4개 이상 연속하면 폭발
        int size = bombs.length;
        int cnt=1;
        int idx = 2;
        while(idx<N*N){
            if(bombs[idx] ==0){

                break;
            }
            //System.out.println("idx : " + idx + " bombs[idx] : " + bombs[idx] + " before: " + bombs[idx-1] + " cnt : " + cnt);
            if(bombs[idx] == bombs[idx-1]){
                cnt++;
            }else{
                mystack.add(cnt);
                mystack.add(bombs[idx-1]);
                cnt = 1;
            }
            idx++;
        }

        if(bombs[1]!=0){
            mystack.add(cnt);
            mystack.add(bombs[idx-1]);
        }


//        System.out.println("After change : " + mystack); //60
//        System.out.println(mystack.size());
        Arrays.fill(bombs, 0);

        //queue size >N*N-1 넘어가면 :

        int i=0;
        while(!mystack.isEmpty() && i<=N*N-1){
            bombs[i++] = mystack.poll();
        }

    }

    private static void doMagic(int d, int s){
        //상어 위치
        for(int i=1;i<=s;i++){
            int nx = s_rc + del[d][0]*i;
            int ny = s_rc + del[d][1]*i;
            if(isIn(nx,ny))map[nx][ny] = 0;
            else break;
        }

//        for(int i=0;i<N;i++){
//            System.out.println(Arrays.toString(map[i]));
//        }
    }

    private static boolean isIn(int x, int y){
        return (x>=0 && y>=0 && x<N && y<N);
    }

    private static void changeMap(){
        map = new int[N][N];
        int r = s_rc;
        int c = s_rc;
        map[r][c] = 5;
        lineMap[0] = 5; //상어
        int idx = 1;
        int mapidx = 1;
        for (int k = 1; k <= N / 2; k++) {
            r -= 1; c -= 1;
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < k * 2; i++) {
                    r += del2[d][0];
                    c += del2[d][1];

                    map[r][c] = bombs[mapidx++];
                }
            }
        }
//        System.out.println("--");
//        for(int i=0;i<N;i++){
//            System.out.println(Arrays.toString(map[i]));
//        }
//        System.out.println("--");
    }

    private static void MakeLineMapIdx(){
        bombs = new int[N*N];
        Queue<Integer> queue= new LinkedList<>();
        //한줄로 펴기
        int r = s_rc;
        int c = s_rc;
//        System.out.println(r + " " + c);
        lineMap[0] = 5; //상어
        int idx = 1;

        for (int k = 1; k <= N / 2; k++) {
            r -= 1; c -= 1;
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < k * 2; i++) {
                    r += del2[d][0];
                    c += del2[d][1];
                    //System.out.println(r + " " + c);

                    if (map[r][c] != 0) queue.add(map[r][c]);
                }
            }

        }

        bombs[0] = (lineMap[0]);
        //0 지우기.
        while (!queue.isEmpty()){
            lineMap[idx] = queue.poll();
            bombs[idx] = (lineMap[idx]);
            idx+=1;
        }

//        System.out.println("0 지우고 한줄로 펴기 : " + Arrays.toString(bombs));

    }

    private static boolean doBomb(){
        //System.out.println("폭발! : " + Arrays.toString(bombs));
        Stack<Integer> mystack = new Stack<>();
        mystack.add(bombs[0]);
        //4개 이상 연속하면 폭발
        int size = bombs.length;
        int cnt=1;
        int idx = 1;
        flag = false;
        while(idx<N*N){
            if(bombs[idx] ==0){
                break;
            }
            //System.out.println("idx : " + idx + " bombs[idx] : " + bombs[idx] + " before: " + bombs[idx-1] + " cnt : " + cnt);
            if(bombs[idx] == bombs[idx-1]){
                cnt++;
            }else{
                if(cnt>=4){
                    while(cnt-->0){
                        int a = mystack.pop();
                        bombcnt[a-1]++;
                    }
                    flag = true; //한번이라도 폭발함.
                }
                cnt = 1;
            }
            mystack.add(bombs[idx]);
            idx++;
        }
        if(cnt>=4){
            while(cnt-->0){
                int a = mystack.pop();
                bombcnt[a-1]++;
            }
            flag = true; //한번이라도 폭발함.
        }


        //System.out.println("폭발 후! : " + mystack);

        Arrays.fill(bombs, 0);
        for(int i=mystack.size()-1;i>=0;i--){
            bombs[i] = mystack.pop();
        }
        return flag;

    }

}
