package algorithm.MarApr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2170 {

    public static void main(String[] args) throws IOException {
        InputStream input = bj2170.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Line[] lines = new Line[N];
        for(int i=0;i<N;i++){
             st = new StringTokenizer(br.readLine());
             lines[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(lines);
        int start, end;
        start = lines[0].start;
        end = lines[0].end;
        int result = 0;
        int i=1;
        while(i<N){
            Line cur = lines[i];
            if(cur.start > end){
                result += end-start;
                start = cur.start;
                end = cur.end;
            }else{
                end = Math.max(end,cur.end);
            }
            System.out.println(start + " " + end);
            i++;
        }
        result += end-start;
        System.out.println(result);
    }

    public static class Line implements Comparable<Line> {
        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            if(this.start==o.start){
                return o.end-this.end;
            }else{
                return this.start-o.start;
            }
        }

        @Override
        public String toString() {
            return "Line{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}

/*
스위핑 알고리즘이란?
특정 기준에 따라 정렬된 순서대로 문제를 처리하는 방법.
일반적으로 N^2 등의 시간 복잡도로 해결할 수 없는 문제들,
DP를 사용하기에는 메모제이션 해야할 수가 많은 경우에 사용한다.
스위핑 알고리즘으로 문제를 해결할 경우 NlogN시간 안에 해결 가능하다.
 */