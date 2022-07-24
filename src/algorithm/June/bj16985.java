package algorithm.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class bj16985 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj1082.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String s  ="asdf";
        char c = 'a';
        String ca = String.valueOf(c);
        List<FileName> fileNames = new LinkedList<>();
        System.out.println(ca);


    }
    public static class FileName implements Comparable<FileName>{
        String fullname;
        String first;
        int second;
        String third;

        public FileName(String fullname, String first, int second, String third){
            this.fullname = fullname;
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public int compareTo(FileName o){
            if(o.first == this.first){
                if(this.second == o.second){
                    return this.third.compareTo(o.third);
                }else{
                    return Integer.compare(this.second, o.second);
                }
            }else{
                return this.first.compareTo(o.first);
            }
        }

        @Override
        public String toString() {
            return "FileName{" +
                    "fullname='" + fullname + '\'' +
                    ", first='" + first + '\'' +
                    ", second=" + second +
                    ", third='" + third + '\'' +
                    '}';
        }
    }
}
