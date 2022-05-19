package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14719 {
    static int H, W, result;
    static int[] height;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        height = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<W;i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        result =0;

        for(int i=1;i<W-1;i++){
            int left = 0;
            int right = 0;

            for(int j = 0; j < i; j++) { //내왼쪽 left : 3, left 1
                left = Math.max(height[j], left);
            }

            for(int j = i + 1; j < W; j++) { //내오른쪽 right : 4
                right = Math.max(height[j], right);
            }

            if(height[i] < left && height[i] < right){ // result : 3-0, result 안더함..
                result += Math.min(left, right) - height[i];
            }
        }

        //고이는 빗물의 총량은 얼마일까?
        System.out.println(result);
    }
}

/*

       ㅁ
ㅁ      ㅁ
ㅁ      ㅁ
ㅁ   ㅁ ㅁ
ㅡ ㅡ ㅡ ㅡ

 */
