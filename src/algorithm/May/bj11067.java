package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class bj11067 {
    static int T;
    static List<Info> list;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            list = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list.add(new Info(x,y));
            }
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            while(num-->0){
                int n = Integer.parseInt(st.nextToken())-1;
                sb.append(list.get(n).x + " " + list.get(n).y + "\n");
            }
        }
        System.out.println(sb);
    }

    public static class Info implements Comparable<Info>{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Info o) {
            if(this.y == o.y) return Integer.compare(this.x, o.x);
            else{
                return Integer.compare(Math.abs(this.y), Math.abs(o.y));
            }
        }
    }
}
