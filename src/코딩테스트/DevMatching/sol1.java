package 코딩테스트.DevMatching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sol1 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[][] dist = {{0,5,2,4,1},{5,0,3,9,6},{2,3,0,6,3},{4,9,6,0,3},{1,6,3,3,0}};
        int[][] ans = solution(dist);

    }

    public static int[][] solution(int[][] dist) {
        int[][] answer = {};

        return answer;
    }

    public static void DFS(int x, int y, String str){

    }
}
