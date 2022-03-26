package 코딩테스트.오늘의집220326;

import java.util.Arrays;

/**
 * 라운드 1개 이상
 * 직전 라운드 상대, 자기 자신 지목 => 규칙 위반
 * 서로 지목 -> 커플 성립
 * 규칙 위반하면 커플 성립 X
 *
 * "규칙을 어긴 사람의 수는?"
 */
public class sol1 {
    public static void main(String[] args) {

        String[][] rounds = {{"b", "a", "a", "a"},{"c", "a","a","a"},{"b", "a","a","a"}};
        System.out.println(solution(rounds));
    }

    public static int solution(String[][] rounds){
        int answer = 0;
        int len = rounds.length;
        int[][] round = new int[len][4];
        Couple[] couples = new Couple[4]; //항상 네명

        //couple 초기화
        for(int i=1;i<=4;i++){
            couples[i-1] = new Couple(i, 0,0, false);
        }

        //입력 값 바꿈.
        for(int i=0;i<len;i++){
            for(int j=0;j<4;j++) {
                round[i][j] = rounds[i][j].charAt(0) - 96;
            }
        }

        //판은 3번 돌아감.
        for(int i=0;i<len;i++){
            //게임 정보대로 매칭
            for(int j=0;j<4;j++){
                // 내가 나를 고른 사람 부터 먼저 확인
                if(round[i][j] == couples[j].I){
                    //날 골랐으면 규칙어김
                    couples[j].warn+=1;
                    couples[j].you=0; //규칙을 어긴 사람은 커플 성립이 안되므로 상대방을 0으로 바꿈.
                }else if(round[i][j] == couples[j].you){
                    couples[j].warn+=1;
                    couples[j].you =0;
                }else{ //전애인 아니면 고른사람에 추가
                    couples[j].you = round[i][j];
                }
            }

            System.out.println("규칙어김 " + Arrays.toString(couples));

            //규칙 어긴 사람을 골랐으면 커플 된 사람 보기

            //일단 커플을 먼저 만들고
            //커플이 안된 사람을 고른다.
            for(int j=0;j<4;j++){
                //j번째 사람이 고른 you라는 사람이 고른 you가 j이면? 커플.
                //아니라면?
                if(couples[j].you !=0 && couples[j].I == couples[(couples[j].you)-1].you){
                    couples[(couples[j].you)-1].iscouple = true;
                    couples[j].iscouple = true;
                }
            }

            System.out.println("커플고름 " +  Arrays.toString(couples));

            for(int j=0;j<4;j++){
                //j번째 사람이 고른 you라는 사람이 고른 you가 j이면? 커플.
                //아니라면?
                if(!couples[j].iscouple){
                    couples[j].you = 0;
                }
            }
            System.out.println("커플0으로만듬 " +  Arrays.toString(couples));

        }

        for(int j=0;j<4;j++){
            answer += couples[j].warn;
        }
        return answer;
    }

    //각자 커플과 규칙 어긴 횟수를 담은 객체 만들기

    public static class Couple{
        int I, you, warn;
        boolean iscouple;

        public Couple(int i, int you, int warn, boolean iscouple) {
            I = i;
            this.you = you;
            this.warn = warn;
            this.iscouple = iscouple;
        }

        @Override
        public String toString() {
            return "Couple{" +
                    "I=" + I +
                    ", you=" + you +
                    ", warn=" + warn +
                    ", iscouple=" + iscouple +
                    '}';
        }
    }
}
