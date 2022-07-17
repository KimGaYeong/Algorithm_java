package algorithm.June;

import java.util.*;

public class pg파일명정렬 {
    public static void main(String[] args) {
       // String[] files = {"img12.png","img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
      String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
//        String[] files = {"foo9.txt", "foo010bar020.zip", "F-15"};
        //
        System.out.println(Arrays.toString(solution(files)));
    }

    static String first, second, third;
    static boolean isThird;
    static List<FileName> list;
    public static String[] solution(String[] files) {
        String[] answer = new String[files.length];
        String before = "", after="";
        list = new LinkedList<>();
        int idx =0;
        for(String f : files){
            String f2 = f.toUpperCase();
            first= ""; second=""; third = "";
            isThird = false;
            for(int i=0;i<f.length();i++){
                //System.out.print(f2.charAt(i));
                //A~Z : 65~90, 0~9 : 48~57 .:46
                int c = f2.charAt(i);
                String str = String.valueOf(f2.charAt(i));
                if((c<48 || c>57) && !isThird){ //숫자가 아니고 아직 3번 안나옴
                    first += str;
                }else if(c>=48 && c<=57){
                    isThird = true;
                    second += str;
                }else if((c<48 || c>57) && isThird){
                    break;
                }
            }

            if(second.length()>=5){
                before = second.substring(0,5);
                after = second.substring(5);
                second = before;
            }

            System.out.println(first + "," + Integer.parseInt(second) + "," + third);
            int secondtoInt = Integer.parseInt(second);
            list.add(new FileName(f, first, secondtoInt));
        }

        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        int i=0;
        for(FileName s : list){
            answer[i++] = s.fullname;
        }
        return answer;
    }

    public static class FileName implements Comparable<FileName>{
        String fullname;
        String first;
        int second;

        public FileName(String fullname, String first, int second){
            this.fullname = fullname;
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(FileName o){
            int result = this.first.compareTo(o.first);
            if(result==0){
                return this.second-o.second;
            }
            return result;
        }

        @Override
        public String toString(){
            return "FileName : " + fullname;
        }

    }
}
// ["img1.png","IMG01.GIF","img02.png","img2.JPG","img10.png","img12.png"]과 다릅니다.
// 	["A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"]

