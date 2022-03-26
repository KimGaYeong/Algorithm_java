package 코딩테스트.Testwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Goodmorning {
    static char[] arr = {'a', 'b', 'c'};
    static int result1, result2, result3, result4, result5;
    public static void main(String[] args) {
        int cnt = 2;
        System.out.println("순열 - 한 번씩 뽑을수 있지만 순서 바꾸기 가능 -> visit 필요"); //순서가 필요. 중복이 가능하다. (visit) 1,2,3 != 3 2 1
        makePermutation(cnt,new char[cnt], new boolean[arr.length]);
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


    /**
     * 순열 -> 원소의 중복을 허용하지만, 순서가 있다. nPr = n*n-1*n-2... n-r+1까지 곱한다.
     * @param toChoose : 남은 숫자, 즉 뽑아야 할 숫자들을 확인하거나, 내가 지금 뽑고 있는 숫자가 몇 번째 순서에 들어갈 숫자인지 확인하는 메소드.
     * @param choosed : 뽑은 숫자를 저장할 배열
     * @param visited : 중복을 제거하기 위해 방문했는지를 나타내는 배열
     */
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
                //순열은 순서 또한 중요한 구조이다.
                visited[i] = true; //B번째 자리에 A라는 수를 집어넣고, 가능한 모든 자리를 다 확인하고 돌아옴.
                choosed[choosed.length-toChoose] = arr[i];
                makePermutation(toChoose-1, choosed, visited);
                visited[i] = false; // 그럼 이제 B번째 자리에 A 말고 다른 애를 넣어보는 시도도 해야됨.
            }
        }
    }

    /**
     * 중복 순열 -> 원소의 중복을 허용하고, 순서도 있으나, 이미 뽑은 숫자라도 중복해서 뽑을 수 있다.
     * @param toChoose : 고를 숫자가 몇 개 남았는지 체크할 수 있음.
     * @param choosed : 고른 숫자들을 저장하는 배열.
     */
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

    /**
     * 조합 : 정해진 개수의 숫자만 딱 고르는 집합을 만드는 것이다. 순서가 없다. boolean 배열 대신 시작 인덱스가 필요하다.
     * @param toChoose : 남은 숫자를 알려준다.
     * @param choosed : 뽑은 숫자를 저장한다.
     * @param startidx : 시작 인덱스를 나타낸다.
     */
    static void makeCombination(int toChoose, char[] choosed, int startidx){
        //base part
        if(toChoose ==0){
            result3++;
            System.out.println(Arrays.toString(choosed) + " ");
            return;
        }

        //inductive part
        for(int i=startidx;i<arr.length;i++){
            choosed[choosed.length-toChoose] = arr[i];
            makeCombination(toChoose-1, choosed, i+1);
        }
    }

    /**
     * 중복 조합 : 4개중에 3개를 고르는데
     * @param toChoose
     * @param choosed
     * @param startidx
     */
    static void makeCombinationDup(int toChoose, char[] choosed, int startidx){
        //base part
        if(toChoose ==0){
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

    /**
     * arr에서 중복을 허용하는게 아닌, true/false에서 중복을 허용해서 선택한다.
     * @param toCheck : 남은 개수.
     * @param checked : 있는지 없는지를 선택하는 true, false
     */
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

    static void print(boolean[] tmp){
        List<Character> llist = new ArrayList<>();
        List<Character> rlist = new ArrayList<>();

        for(int i=0;i<tmp.length;i++){
            if(tmp[i]){
                llist.add(arr[i]);
            }else{
                rlist.add(arr[i]);
            }
        }
        System.out.println("선택 list : " + llist + " 미선택 list : " + rlist);
    }
}
