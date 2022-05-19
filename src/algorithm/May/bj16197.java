package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj16197 {
    static int N, M, result;
    static char[][] map;
    static coinInfo coinList;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        int c=0;
        coinList = new coinInfo(0,0,0,0);
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] =='o'){
                    map[i][j] = '.';
                    if(c==0){
                        coinList.xo = i;
                        coinList.yo = j;
                        c++;
                    }else{
                        coinList.x1 = i;
                        coinList.y1 = j;
                    }

                }
            }
        }

        result =Integer.MAX_VALUE;
        DFS(0, coinList);

        if(result >10) System.out.println("-1");
        else System.out.println(result);
    }

    public static void DFS(int cnt, coinInfo coinOrigin){
        coinInfo coinTmpList = new coinInfo(0,0,0,0);
        if(cnt>10){
            return;
        }

        for(int d=0;d<4;d++){
            coinTmpList.xo = coinOrigin.xo;
            coinTmpList.yo = coinOrigin.yo;
            coinTmpList.x1 = coinOrigin.x1;
            coinTmpList.y1 = coinOrigin.y1;

            int cx0 = coinTmpList.xo;
            int cy0 = coinTmpList.yo;
            // ->
            int nx0 = cx0 + del[d][0];
            int ny0 = cy0 + del[d][1];

            int cx1 = coinTmpList.x1;
            int cy1 = coinTmpList.y1;
            //->
            int nx1 = cx1 + del[d][0];
            int ny1 = cy1 + del[d][1];

            int tmp = isIn(nx0,ny0)+isIn(nx1, ny1);
            if(tmp==1){
                result = Math.min(result, cnt+1);
                continue;
            }else if(tmp ==0) { //둘다 벗어나면
                continue;
            }else {
                if (map[nx0][ny0] == '.') {
                    coinTmpList.xo = nx0;
                    coinTmpList.yo = ny0;
                }

                if (map[nx1][ny1] == '.') {
                    coinTmpList.x1 = nx1;
                    coinTmpList.y1 = ny1;
                }

                DFS(cnt + 1, coinTmpList);
            }

        }

    }

    public static class coinInfo{
        int xo, yo;
        int x1, y1;

        public coinInfo(int xo, int yo, int x1, int y1) {
            this.xo = xo;
            this.yo = yo;
            this.x1 = x1;
            this.y1 = y1;
        }
    }

    public static int isIn(int x, int y){
        if (x>=0 && y>=0 && x<N && y<M) return 1;
        else return 0;
    }
}
