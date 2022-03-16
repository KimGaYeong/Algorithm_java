package MarApr;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj19644_2 {// BOJ 19644. 좀비 떼가 기관총 진지에도 오다니
    public static void main(String[] args) throws IOException {
        InputStream input = bj19644_2.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Queue<Integer> queue = new LinkedList<>();

        //입력
        int L = Integer.parseInt(br.readLine());
        int[] M = new int[2];
        st = new StringTokenizer(br.readLine());
        M[0] = Integer.parseInt(st.nextToken());//사격 사정거리
        M[1] = Integer.parseInt(st.nextToken());//타격
        int C = Integer.parseInt(br.readLine());// 수평 개수
        boolean check = false;

        for (int i = 0; i < L; i++) {
            int power = Integer.parseInt(br.readLine());// 현재 쏠 좀비 파워
            if (!queue.isEmpty()){ //수평이 한개라도 터트렸음
                int idx = queue.peek()-1; //터트린 자리
                if (i > idx+M[0]) {
                    queue.poll();
                }
            }
            if (power > (M[0]-queue.size())*M[1] && power>0) {// 기관총으로 좀비 못죽임
                C -= 1;// 지뢰 사용
                if (C == 0){
                    check = true;
                    break;
                }
                queue.offer(i);//수평이 터트린 위치
            }
            System.out.println(queue);
        }

        if (check) System.out.println("YES");
        else System.out.println("NO");
    }
}