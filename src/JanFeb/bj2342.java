package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj2342 {
    static ArrayList<Integer> map;
    static int [][][] DDR;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new ArrayList<Integer>();

        while(st.hasMoreTokens()){
            int tmp = Integer.parseInt(st.nextToken());
            if(tmp !=0) {
                map.add(tmp);
            }else{
                break;
            }
        }
        System.out.println(map.toString());
        cnt = map.size();
        DDR = new int[cnt+1][5][5];
        //(0,0,0)에서 시작
        int result = DP(0,0,0);
        System.out.println(result);

    }

    static int DP(int idx, int left, int right){
        if(idx==cnt) return 0;
        if(DDR[idx][left][right] !=0) return DDR[idx][left][right];

        int leftValue = jumpValue(left, map.get(idx)) + DP(idx+1, map.get(idx),right);
        int rightValue = jumpValue(right, map.get(idx)) + DP(idx+1, left, map.get(idx));

        //System.out.println("jump left-> : " + jumpValue(left, map.get(idx)) + " & jump right-> : " + jumpValue(right, map.get(idx)));
        //System.out.println("next : " + DP(idx+1, map.get(idx),right) + "& next right : " + DP(idx+1, left, map.get(idx)));
        DDR[idx][left][right] = Math.min(leftValue, rightValue);

        //System.out.println("idx : " + idx + " DDR : " + DDR[idx][left][right]);
        return DDR[idx][left][right];
    }

    static int jumpValue(int start, int end){
        int move = 0;
        if(start==end){
            move = 1;
        }else if(start==0) {
            move = 2;
        }else if(Math.abs(start-end) ==2){
            move = 4;
        }else move=3;

        return move;
    }
}
