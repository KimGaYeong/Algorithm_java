package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. Straight Flush : 모두 동일한 슈트에 랭크(값)가 모두 연속적이다                -> 문자 1개, 숫자 5개(연속)
    (여기서는 로얄 플러쉬를 포함한다. 로얄 플러쉬는 모두 동일한 슈트에 T, J, Q, K, A를 갖는다).
2. Four of a Kind : 5장 중 4개의 랭크(값)가 모두 같다.                      -> 문자 상관 없음, 숫자 2개

3. Full House : 3장의 동일한 랭크(값)와 다른 랭크(값)의 동일한 2장으로 구성된다.   -> 문자 상관 없음, 숫자 2개
4. Flush : 5장이 모두 동일한 슈트다.                                      -> 문자 1개. 숫자 상관 없음
5. Straight : 다른 슈트가 섞여있으며 랭크(값)가 모두 연속적이다.                  -> 문자 상관 없음. 숫자 5개(연속)
6. Three of a kind : 동일한 랭크(값)가 3장이다(1,2,3,3,3).                  -> 문자 상관 없음. 숫자 3개
7. Two pair : 동일한 랭크(값) 2장씩 두개의 랭크다(2,6,6,3,3).                  -> 문자 상관 없음. 숫자 3개
8. One pair : 동일한 랭크가 2장이다(2,4,5,5,7).                              -> 문자 상관 없음. 숫자 4개

9. High card : 1~8번에 해당하지 않는다.
 */

/**
 * 숫자를 먼저 보고, 그 뒤에 문자를 보자.
 */
public class swea_9760 {
    static String[] cards;
    static int[] suit, rank;
    static StringTokenizer st;

    public static void main(String[] args)  throws IOException {
        InputStream input = swea_9760.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            cards = new String[5];

            suit = new int[4];
            rank = new int[13];

            String result = solve();
            sb.append("#" + t + " " + result + "\n");
        }
        System.out.println(sb);
    }

    public static String solve(){
        String ans = "High card";
        for(int i=0; i<5; i++) {
            cards[i] = st.nextToken();

            switch(cards[i].charAt(0)) {
                case 'S' :
                    suit[0]++;break;
                case 'D' :
                    suit[1]++;break;
                case 'H' :
                    suit[2]++;break;
                case 'C' :
                    suit[3]++;break;
            }

            switch(cards[i].charAt(1)) {
                case 'A' :
                    rank[0]++; break;
                case '2' :
                    rank[1]++; break;
                case '3' :
                    rank[2]++; break;
                case '4' :
                    rank[3]++; break;
                case '5' :
                    rank[4]++; break;
                case '6' :
                    rank[5]++; break;
                case '7' :
                    rank[6]++; break;
                case '8' :
                    rank[7]++; break;
                case '9' :
                    rank[8]++; break;
                case 'T' :
                    rank[9]++; break;
                case 'J' :
                    rank[10]++; break;
                case 'Q' :
                    rank[11]++; break;
                case 'K' :
                    rank[12]++; break;
            }
        }

        //7, 8, 3, 6
        int c=0; //2개수
        int d=0; //3개수
        for(int k=0;k<13;k++){
            if(rank[k]==2) c++;
            if(rank[k]==3) d++;
        }
        if(d==1){
            if(c==1)ans = "Full House";
            else ans = "Three of a kind";
        }else{
            if(c==1) ans = "One pair";
            else if(c==2) ans = "Two pair";
        }

        //2
        for(int k=0;k<13;k++){
            if(rank[k]==4) {
                ans = "Four of a Kind";
            }
        }

        //4
        for(int k=0;k<4;k++){
            if(suit[k]==5) {
                ans = "Flush";
            }
        }

        //5 -> 연속적이기만 하면 됨. 1-> 모두 동일 수트어야 함.
        String a = "";
        String b = "";
        for(int i=0; i<13; i++) {
            a += rank[i];
        }
        for(int i=0; i<4; i++) {
            b += suit[i];
        }
        if(a.contains("11111")) {
            if(b.contains("5")) ans = "Straight Flush";
            else ans = "Straight";
        }

        return ans;
    }
}

/*
1. Straight Flush : 모두 동일한 슈트에 랭크(값)가 모두 연속적이다                -> 문자 1개, 숫자 5개(연속)
    (여기서는 로얄 플러쉬를 포함한다. 로얄 플러쉬는 모두 동일한 슈트에 T, J, Q, K, A를 갖는다).
2. Four of a Kind : 5장 중 4개의 랭크(값)가 모두 같다.                      -> 문자 상관 없음, 숫자 2개

3. Full House : 3장의 동일한 랭크(값)와 다른 랭크(값)의 동일한 2장으로 구성된다.   -> 문자 상관 없음, 숫자 2개
4. Flush : 5장이 모두 동일한 슈트다.                                      -> 문자 1개. 숫자 상관 없음
5. Straight : 다른 슈트가 섞여있으며 랭크(값)가 모두 연속적이다.                  -> 문자 상관 없음. 숫자 5개(연속)
6. Three of a kind : 동일한 랭크(값)가 3장이다(1,2,3,3,3).                  -> 문자 상관 없음. 숫자 3개
7. Two pair : 동일한 랭크(값) 2장씩 두개의 랭크다(2,6,6,3,3).                  -> 문자 상관 없음. 숫자 3개
8. One pair : 동일한 랭크가 2장이다(2,4,5,5,7).                              -> 문자 상관 없음. 숫자 4개

9. High card : 1~8번에 해당하지 않는다.
 */