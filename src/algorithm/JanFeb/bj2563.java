package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj2563 {
    static xy[] paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        paper = new xy[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            paper[i] = new xy(x,y);
        }

        Arrays.sort(paper, new Comparator<xy>() {
            @Override
            public int compare(xy o1, xy o2) {
                if(o1.x == o2.x){
                    return o1.y-o2.y;
                }else{
                    return o1.x-o2.x;
                }
            }
        });

        for(xy i : paper){
            System.out.println(i.x + " " + i.y);
        }

        int minus =0;
        int i=0;
        while(true){
            if(i==N-1) break;
            int xval = Math.abs(paper[i].x-paper[i+1].x);
            int yval = Math.abs(paper[i].y-paper[i+1].y);

            if(xval<10 && yval<10){
                minus += (Math.abs((paper[i].x+10)-paper[i+1].x) * Math.abs((paper[i+1].y+10)-paper[i].y));
            }
            i++;
        }

        System.out.println((100*N)-minus);
    }

    public static class xy{
        int x, y;
        xy(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
