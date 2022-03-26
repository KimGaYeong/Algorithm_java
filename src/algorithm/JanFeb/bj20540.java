package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj20540 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String Mbti = br.readLine();

        for(int i=0; i<Mbti.length();i++){
            switch (Mbti.charAt(i)){
                // "", ''의 사용을 잘 구분해야 한다.
                case 'E' :
                    System.out.print('I'); break;
                case 'I' :
                    System.out.print('E'); break;
                case 'S' :
                    System.out.print('N'); break;
                case 'N' :
                    System.out.print('S'); break;
                case 'T' :
                    System.out.print('F'); break;
                case 'F' :
                    System.out.print('T'); break;
                case 'J' :
                    System.out.print('P'); break;
                case 'P' :
                    System.out.print('J'); break;
            }

        }

    }
}
