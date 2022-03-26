package 코딩테스트.Testwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodmorningWork {
    static char[] arr = {'a', 'b', 'c'};
    static int result1, result2, result3, result4, result5;
    public static void main(String[] args) {
        int cnt = 2;
        System.out.println("순열 - 한 번씩 뽑을수 있지만 순서 바꾸기 가능 -> visit 필요"); //순서가 필요. 중복이 가능하다. (visit) 1,2,3 != 3 2 1
        makePermutation(cnt, new char[cnt], new boolean[arr.length]);
        System.out.println("순열 = " + result1);

        System.out.println("중복 순열 - 순서 바꾸기 가능 but 중복이 가능  -> visit 필요X");  //순서가 있으면서 중복이 가능하다. 1,1,2,2 != 2 2 1 1
        makePermutationDup(cnt, new char[cnt]);
        System.out.println("중복 순열 = " + result2);

        System.out.println("조합 - 순서 바꿀 수 없다. 중복 요소 안됨."); //순서가 필요 없음. 중복이 불가능하다. 1,2,3 == 3,2,1 (1,2,2 불가능)
        makeCombination(cnt, new char[cnt], 0);
        System.out.println("조합 = " + result3);

        System.out.println("중복 조합 - 순서 바꿀 수 없고 중복은 된다."); //순서가 없으면서 중복이 가능하다. 1,1,2,2 == 2,2,1,1
        makeCombinationDup(cnt, new char[cnt], 0);
        System.out.println("(nHr = n+r-1Cr) 중복 조합 = " + result4);

        System.out.println("부분 집합");
        powerSetDup(arr.length, new boolean[arr.length]);
        System.out.println("부분 집합 = " + result5);

    }



    static void makePermutation(int toChoose, char[] choosed, boolean[] visited){
        //base part
        if(toChoose ==0){
            result1++;
            System.out.println(Arrays.toString(choosed) + " ");
            return;
        }
        //inductive part

        for(int i=0;i<arr.length;i++){
            if(!visited[i]){
                visited[i] = true;
                choosed[choosed.length-toChoose] = arr[i];
                makePermutation(toChoose-1, choosed, visited);
                visited[i] = false;
            }

        }
    }


    static void makePermutationDup(int toChoose, char[] choosed){
        //base part
        if(toChoose==0){
            result2++;
            System.out.println(Arrays.toString(choosed) + " ");
            return;
        }
        //inductive part
        for(int i=0;i<arr.length;i++){
            choosed[choosed.length-toChoose] = arr[i];
            makePermutationDup(toChoose-1, choosed);
        }
    }


    static void makeCombination(int toChoose, char[] choosed, int startidx){
        //base part
        if(toChoose==0){
            result3++;
            System.out.println(Arrays.toString(choosed) + " ");
            return;
        }
        //inductive part
        for(int i=startidx;i<arr.length;i++) {
            choosed[choosed.length-toChoose] = arr[i];
            makeCombination(toChoose-1, choosed, i+1);
        }
    }


    static void makeCombinationDup(int toChoose, char[] choosed, int startidx){
        //base part
        if(toChoose==0){
            result4++;
            System.out.println(Arrays.toString(choosed) + " ");
            return;
        }
        //inductive part
        for(int i=startidx;i<arr.length;i++){
            choosed[choosed.length-toChoose] = arr[i];
            makeCombinationDup(toChoose-1, choosed, i);
        }
    }


    static void powerSetDup(int toCheck, boolean[] checked){
        //base part
        if(toCheck==0){
            result5++;
            print(checked);
            return;
        }
        //inductive part
        checked[checked.length-toCheck] = true;
        powerSetDup(toCheck-1, checked);
        checked[checked.length-toCheck] = false;
        powerSetDup(toCheck-1, checked);

    }

    static void print(boolean[] checked) {
        List<Character> list = new ArrayList<>();
        List<Character> unlist = new ArrayList<>();

        for(int i=0;i<checked.length;i++){
            if(checked[i]){
                list.add(arr[i]);
            }else{
                unlist.add(arr[i]);
            }
        }
        System.out.println("선택 : " + list + ", 미선택 : " + unlist);
    }
}
