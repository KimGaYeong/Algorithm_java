import java.io.*;
import java.util.*;

public class bj1744 {

    static List<Integer> positive = new ArrayList<>();
    static boolean zero;
    static List<Integer> negative = new ArrayList<>();
    static int p_result = 0;
    static int n_result = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x>0) positive.add(x);
            else if (x==0) zero = true;
            else negative.add(x);
        }

        Collections.sort(positive, Collections.reverseOrder()); //내림차순으로 정렬
        Collections.sort(negative); //오름차순으로 정렬


        /* Positive*/

        //양수가 짝수 개 있을 때.
        if(positive.size()%2==0) {
            for(int p=0;p<positive.size();p+=2) {
                if (positive.get(p)!=1 && positive.get(p+1)!=1)  { //1이 나오면 더해야 하므로 1이 없을 때 곱해줌.
                    p_result += positive.get(p) * positive.get(p+1);
                }
                else{
                    p_result += positive.get(p) + positive.get(p+1); //1이 나오면 더해줌.
                }
            }
            //양수가 홀수 개 있을 때.
        }else {
            if(positive.size()>1) { //size가 3이상.
                for(int p=0;p<positive.size()-1;p+=2) { //일단 짝수개 까지는 동일하게 진행.
                    if (positive.get(p)!=1 && positive.get(p+1)!=1) {
                        p_result += positive.get(p) * positive.get(p+1);
                    }
                    else p_result += positive.get(p) + positive.get(p+1);
                } //마지막 남은건 더해줌.
                p_result += positive.get(positive.size()-1);
            }
            else { //size==1일 때.
                p_result += positive.get(0);
            }
        }

        /* Negative*/

        //음수가 짝수 개 있을 때
        if(negative.size()%2 ==0) { // 두개씩 곱한다.
            for(int p=0;p<negative.size();p+=2) {
                n_result += negative.get(p) * negative.get(p+1);
            }
            //음수가 홀수 개 있을 때
        }else {
            if(negative.size() >1) { //size3이상.
                for(int p=0;p<negative.size()-1;p+=2) {
                    n_result += negative.get(p) * negative.get(p+1);
                }//짝수개 까지는 일단 곱한다.
                if(!zero) { //0이 없으면 그냥 더하고 0이면 0이랑 곱해지니까 뭐 없음.
                    n_result += negative.get(negative.size()-1);
                }
            }
            else { //size==1일 때.
                if(!zero) n_result = negative.get(0);
            }

        }
        System.out.println(p_result + n_result);
    }

}