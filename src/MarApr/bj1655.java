package MarApr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class bj1655 {
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        InputStream input = bj1655.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        while (N--> 0) {
            int tmp = Integer.parseInt(br.readLine());
            sb.append(solve(tmp) + "\n");
        }
        System.out.println(sb);
    }
    public static int solve(int n) {
        int left = 0;
        int right = list.size() - 1;
        int mid = (left + right) / 2;

        while (left <= right) {
            mid = (left + right) / 2;

            if (list.get(mid) > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
                mid++;
            }
        }

        list.add(mid, n);

        if(list.size()%2 ==0){
            return list.get((list.size() / 2) - 1);
        }else{
            return list.get(list.size() / 2);
        }
    }
}