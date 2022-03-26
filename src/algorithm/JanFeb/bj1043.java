package algorithm.JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj1043 {
    static int N;
    static int M;
    static ArrayList<Integer> original;
    static ArrayList<Integer> people;
    static List<ArrayList<Integer>> partyman;
    static ArrayList<Integer> truthman;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = M;
        st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        original = new ArrayList<>();
        if(len>0){ //진실을 알고있는 사람의 수가 0보다 크면 original list에 넣어준다.
            for(int i=0;i<len;i++){
                original.add(Integer.parseInt(st.nextToken()));
            }
        }else{
            System.out.println(count);
            return;
        }
        partyman = new ArrayList<>();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            len = Integer.parseInt(st.nextToken());
            people = new ArrayList<>();
            for(int j=0;j<len;j++){
                people.add(Integer.parseInt(st.nextToken()));
            }
            partyman.add(people);
        }

        //System.out.println("party is : " + partyman.toString());
        //System.out.println("original is : " + original.toString());
        truthman = new ArrayList<>();
        while(!original.isEmpty()){
            //System.out.println("original is : " + original.toString());
            //System.out.println("BEFORE --- original is : " + original.toString());
            //System.out.println("BEFORE --- truthman is : " + truthman.toString());
            int person = original.get(0);
            original.remove(0);
            truthman.add(person);
            //System.out.println("AFTER *** original is : " + original.toString());
            //System.out.println("AFTER *** truthman is : " + truthman.toString());
            for(int j=0;j<M;j++){
                //System.out.println("------> partyman[j] : " + partyman.get(j).toString());
                if(partyman.get(j).contains(person)){
                    for(int k=0;k<partyman.get(j).size();k++){
                        Integer target = partyman.get(j).get(k);
                        //System.out.println("------original : " + original.toString() + ", truthman : " + truthman.toString() +"에 target : " + target + " 이 있나?");
                        if(!(original.contains(target)) && !(truthman.contains(target))){
                            original.add(target);
                        }
                    }
                }
            }
        }

        for(int i=0;i<M;i++){
            for(int j=0;j<truthman.size();j++){
                if (partyman.get(i).contains(truthman.get(j))){
                    count -=1;
                    break;
                }
            }
        }

        System.out.println(count);
    }
}

/**
 * 4 3
 * 0
 * 2 1 2
 * 1 3
 * 3 2 3 4
 *
 */