package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
다익스트라 -  한 노드 ▶ 모든 노드 / 빠름 / 가중치가 음수이면 구할 수 없다.

벨만 포드 - 한 노드 ▶ 모든 노드 / 중간 / 가중치가 음수일 때 다익스트라 대신 사용할 수 있다.

플로이드 와샬 - 모든 노드 ▶ 모든 노드 / 느림 / 가중치가 음수여도 사용할 수 있다.
 */
public class bj9205 {
    static int n;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        InputStream input = bj9205.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            n = Integer.parseInt(br.readLine());
            map = new int[n+2][n+2];
            ArrayList<Info> list = new ArrayList<>();

            for (int i = 0; i <=n+1; i++) {
                for (int j = 0; j <= n+1; j++)
                    map[i][j] = 100*50*20-1;
            }

            for (int i = 0; i <= n+1; i++) {
                st = new StringTokenizer(br.readLine());
                list.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            for (int i = 0; i <=n+1; i++) {
                for (int j = 0; j <=n+1; j++) {
                    if (i == j) continue;
                    Info current = list.get(i);
                    Info next = list.get(j);

                    int distance = Math.abs(current.x - next.x) + Math.abs(current.y - next.y);
                    if (distance <= 1000)
                        map[i][j] = 1;
                }
            }

            //이부분이 플로이드 와샬 핵심 코드
            //경유
            for (int k = 0; k <= n+1; k++) {
                //시작
                for (int i = 0; i <= n+1; i++) {
                    //도착
                    for (int j = 0; j <=n+1; j++) {
                        //시작, 도착, 경유노드가 같으면? 넘김
                        if (i == j || k == j) continue;
                        //더 값이 작은 경우에 갱신
                        if (map[i][j] > map[i][k] + map[k][j])
                            map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
            if (map[0][n+1] > 0 && map[0][n+1] < 100*50*20-1)
                System.out.println("happy");
            else
                System.out.println("sad");
        }


    }
    public static class Info implements Comparable<Info>{
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Info o) {
            if(this.x == o.x) return this.y-o.y;
            else return this.x-o.x;
        }
    }
}
