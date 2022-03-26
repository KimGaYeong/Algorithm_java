package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class bj1181 {
    static int N;
    static Word[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new Word[N];

        for(int n=0;n<N;n++){
            String s = br.readLine();
            int l = s.length();
            arr[n] = new Word(l,s);
        }

        Arrays.sort(arr, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if(o1.len == o2.len) {
                    return o1.mean.compareTo(o2.mean);
                }
                else return o1.len-o2.len;
            }
        });

        String s = "";
        for(Word w : arr){
            if(s.equals(w.mean)) continue;
            else{
                System.out.println(w.mean);
            }
            s = w.mean;
        }
    }
    public static class Word{
        String mean;
        int len;
        Word(int len, String mean){
            this.len = len;
            this.mean = mean;
        }
    }

}
