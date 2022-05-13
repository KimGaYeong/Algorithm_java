package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj1041 {
    static long N;
    static long result =0;
    static List<Long> dice, orderdice, twoofdice, threeofdice;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Long.parseLong(br.readLine());
        dice = new ArrayList<>();
        orderdice = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        long sum = 0;

        for(int i=0;i<6;i++){
            dice.add(Long.parseLong(st.nextToken()));
            orderdice.add(dice.get(i));
            sum += (long) dice.get(i);
        }

        Collections.sort(orderdice);
        //System.out.println(orderdice);

        if(N==1){
            result = sum-orderdice.get(5);
            System.out.println(result);
            System.exit(0);
        }

        long one = ((5*N*N) - (16*N) + 12);
        long two = ((8*N) - 12);
        long three = 4;

        result = CalculateThreeOfDice()*three + CalculateTwoOfDice()*two + orderdice.get(0)*one;

        System.out.println(result);
    }

    private static long CalculateTwoOfDice(){
        twoofdice = new ArrayList<>();

        //i+j ==5는 서로 마주보고 있음.
        for(int i=0;i<6;i++){
            for(int j=i+1;j<6;j++){
                if(i==j || i+j==5) continue;
                twoofdice.add(dice.get(i)+dice.get(j));
            }
        }

        Collections.sort(twoofdice);
        return twoofdice.get(0);
    }

    public static long CalculateThreeOfDice(){
        threeofdice = new ArrayList<>();

        //0, 5에 (2,1), (3,1), (2,4), (3,4)
        for(int i=2;i<=3;i++){
            for(int j=1;j<=4;j+=3){
                threeofdice.add(dice.get(0)+(dice.get(i)+dice.get(j)));
                threeofdice.add(dice.get(5)+(dice.get(i)+dice.get(j)));
            }
        }
        Collections.sort(threeofdice);
        return threeofdice.get(0);
    }
}
