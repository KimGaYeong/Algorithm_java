package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj1497 {
    static int n, m;
    static String[][] guitar;
    static boolean[] arr;
    static boolean[] visit;
    static ArrayList<Integer> list;
    static int ansS=0;
    static int ansG=99999999;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        guitar = new String[n][2];
        arr = new boolean[m];
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            guitar[i][0]=st.nextToken();
            guitar[i][1]=st.nextToken();
        }
        powerSet(0);
        if(ansS ==0) System.out.println("-1");
        else System.out.println(ansG);
    }

    static void powerSet(int idx) {
        if(idx == n) {
            list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visit[i]) list.add(i);
            }
            int maxSong = oper(list);
            int maxGuitar = list.size();
            if(maxSong>ansS) {
                ansS=maxSong;
                ansG=maxGuitar;
            }
            else if(maxSong==ansS) {
                if(ansG>=maxGuitar) ansG = maxGuitar;
            }
            return;
        }

        visit[idx] = false;
        powerSet(idx + 1);

        visit[idx] = true;
        powerSet(idx + 1);
    }

    static int oper(ArrayList lis) {
        arr = new boolean[m];
        for (int i = 0; i < lis.size(); i++) {
            for (int j = 0; j < m; j++) {
                if(guitar[list.get(i)][1].charAt(j)=='Y') {
                    arr[j]=true;
                }
            }
        }
        int cnt=0;
        for (int i = 0; i < m; i++) {
            if(arr[i]) cnt++;
        }
        return cnt;
    }
}