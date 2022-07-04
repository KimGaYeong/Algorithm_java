package algorithm.June;

import java.util.Arrays;

public class pg브라이언의고민 {
    public static void main(String[] args) {

        String sentence = "HaEaLaLaObWORLDb";


        //12 12 dHcEcLcLcOdbWfOfRfLfDb
        //2 2 aHELLOabWORLDb
                /*
"HaEaLaLaObWORLDb"	"HELLO WORLD"
"SpIpGpOpNpGJqOqA"	"SIGONG JOA"
"AxAxAxAoBoBoB"	"invalid"
                 */

        System.out.println(solution(sentence));
    }
    /*
 영문 대문자는 원래 문구, 소문자는 특수기호를 의미한다.)

광고 문구 만드는 법
(규칙 1) 특정 단어를 선택하여 글자 사이마다 같은 기호를 넣는다. ex) HELLO -> HaEaLaLaO
(규칙 2) 특정 단어를 선택하여 단어 앞뒤에 같은 기호를 넣는다. ex) WORLD -> bWORLDb
위의 두 가지 규칙은 한 단어에 모두 적용될 수 있지만 같은 규칙은 두 번 적용될 수 없다.
한 번 쓰인 소문자(특수기호)는 다시 쓰일 수 없다.
마지막으로 원래 문구에 있던 공백을 제거한다.
     */

    // 광고 문구를 원래 단어로 만들자...
    static int len;
    static String answer, string;
    static StringBuilder  sb;
    public static String solution(String sentence) {
        sb = new StringBuilder();
        string = sentence;

        len = sentence.length();
        Lower[] lowers = new Lower[26];
        for(int i=0;i<26;i++){
            String s = Character.toString((char)(i+97));
            lowers[i] = new Lower(false, -1,-1,0,s); //시작/끝 초기화하고 개수는 0개로
        }

        int cnt=0;
        for(int i=0;i<len;i++){
            int c = sentence.charAt(i)-'A';

            if(c>=32 && c<=57){
                //소문자
                if(lowers[c-32].check){ //이미 사용했음!
                    //끝 위치 추가해주기
                    lowers[c-32].end = i;
                    lowers[c-32].cnt++; //개수도 추가해주기
                }else{
                    //시작 위치에 표시해주기
                    lowers[c-32].start = i;
                    lowers[c-32].cnt = 1;
                    lowers[c-32].check = true;
                    cnt++;

                }
            }

            //System.out.println(c);
        }

        Arrays.sort(lowers);
        Lower[] real = new Lower[cnt];
        int k=0;
        for(Lower l : lowers){
            if(l.check){
                System.out.println(l);
                real[k++] = l;
            }
        }



        int i=1;
        int prestep = 0;
        boolean flag = false;
        while(i<cnt){
//            System.out.println(i);
            if(i==cnt-1){ //끝까지 다 봤는데도 flag가 있다믄?
                flag = true;
            }
            //합쳐져있는지 확인
            Lower before = real[i-1];
            Lower after = real[i];

            System.out.println(before + " " + after);

            if(before.end > after.start){
                // 1,2가 연결되어 있다는 뜻. 그말은 1 시작~ 1 끝 사이 안에 있는 대문자가 원래 단어다.
                findUpper(before.start, before.end);
                // 원래 단어 찾았으면 인덱스 이동까지 해주자.
                i+=1;
                prestep = before.end;
            }else if(before.end < after.start){
                // 근데 얘가 1인지 2인지르 모르자나?
                // 이럴 때 카운트를 보는데 카운트가 2가 아니면 무조건 문자 안에 특수문자를 넣는 케이스 이므로..
                if(before.cnt !=2){
                    findUpper(before.start-1, before.end+1); //로 찾는다.
                }else{
                    //2인 경우에는 before 앞에 대문자가 오는지 안오는지를 찾으면 된다.
                    // 만약 case 1번인 경우에는 대문자가 3글자여야되고 case 2번인 경우에는 대문자의 글자는 상관이 없다.
                    // 어케 보냐? start, end의 인덱스 차이를 보면 된다. case 1의 경우 대문자가 3글자여야되니까
                    // A b A b A 같이 되니까 인덱스의 차이가 무조건 2가된다.
                    // case 2 의 경우는 aAa aAAa aAAAa ... 인덱스 차이가 2인 경우는 aAa의 경우밖에 없는데 따라서 뭘 보면 되냐면
                    // 우선 인덱스 차이 2로 한번 거르고 그래도 안되면 b앞에 b사이에있는 대문자가 있는지 없는지를 보면 됨.ㅋㅋ 어렵당

                    if(before.end- before.start !=2){
                        findUpper(before.start, before.end);
                    }else{
                        //before.start 인덱스 앞뒤의 문자를 본다.
                        int idx = before.start;
//                        if(sentence.charAt(idx-1) == sentence.charAt(idx+1)){ //J b O b A
//                            findUpper(before.start-1, before.end+1);
//                        }else{
//                            findUpper(before.start, before.end); // aAa
//                        }
                        findUpper(before.start-1, before.end+1);
                    }
                }
                prestep = before.end;
            }else{
                sb = new StringBuilder();
                sb.append("invalid");
                return sb.toString();
            }
            i++;
        }

        //flag 있음 맨 뒤꺼 한번 더보기
        if(flag){
            Lower before = real[cnt-1];

            if(before.cnt !=2){
                findUpper(before.start-1, before.end+1); //로 찾는다.
            }else{
                //2인 경우에는 before 앞에 대문자가 오는지 안오는지를 찾으면 된다.
                // 만약 case 1번인 경우에는 대문자가 3글자여야되고 case 2번인 경우에는 대문자의 글자는 상관이 없다.
                // 어케 보냐? start, end의 인덱스 차이를 보면 된다. case 1의 경우 대문자가 3글자여야되니까
                // A b A b A 같이 되니까 인덱스의 차이가 무조건 2가된다.
                // case 2 의 경우는 aAa aAAa aAAAa ... 인덱스 차이가 2인 경우는 aAa의 경우밖에 없는데 따라서 뭘 보면 되냐면
                // 우선 인덱스 차이 2로 한번 거르고 그래도 안되면 b앞에 b사이에있는 대문자가 있는지 없는지를 보면 됨.ㅋㅋ 어렵당

                if(before.end- before.start !=2){
                    findUpper(before.start, before.end);
                }else{
                    //before.start 인덱스 앞뒤의 문자를 본다.
                    int idx = before.start;
//                        if(sentence.charAt(idx-1) == sentence.charAt(idx+1)){ //J b O b A
//                            findUpper(before.start-1, before.end+1);
//                        }else{
//                            findUpper(before.start, before.end); // aAa
//                        }
                    findUpper(before.start-1, before.end+1);
                }
            }
        }


        return sb.toString().substring(0,sb.length());
    }

    public static void findUpper(int start, int end){
        // start, end idx를 포함하는 문자열에서 대문자만 뽑아내기
        String s = "";
        System.out.println(string.substring(start, end+1));

        for(int i=start;i<=end;i++){
            int c = string.charAt(i)-'A';
            if(c>=0 && c<=25){
                String str = Character.toString((char)(c+65));
                s+=str;
            }
        }
        sb.append(s).append(" ");
    }

    public static class Lower implements Comparable<Lower>{
        boolean check; //등장했는지 확인
        int start, end; //시작, 끝 위치
        int cnt; //개수
        String alphabet;

        public Lower(boolean check, int start, int end, int cnt, String alphabet) {
            this.check = check;
            this.start = start;
            this.end = end;
            this.cnt = cnt;
            this.alphabet = alphabet;
        }

        @Override
        public int compareTo(Lower o) {
            return this.start-o.start;
        }


        @Override
        public String toString() {
            return "Lower{" +
                    "check=" + check +
                    ", start=" + start +
                    ", end=" + end +
                    ", cnt=" + cnt +
                    ", alphabet='" + alphabet + '\'' +
                    '}';
        }
    }
}
