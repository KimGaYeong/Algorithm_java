package algorithm.April;

public class ssafy_ctTest {
    /*
    2022년. 휴가를 가려고 한다. 불길한 13일의 금요일을 피하려 한다.
    2022년에 대하여 "13일의 금요일"이 있는 달을 구하라
    (단 2021년 1월 1일은 토요일이다.)
     */
    static String[] Year_day = {"Sun", "Mon", "Tue", "Wen", "Thr", "Fri", "Sat"};
    static int[] Year_Month = { 31,28,31,30,31,30,31,31,30,31,30,31 };
    static boolean[] check = new boolean[13];

    public static void main(String[] args) {

        /* 13일의 금요일 구하기 */
        int Year = 2022;

        //13일이 금요일인지 check
        for(int i=1;i<=12;i++){
            if(Cal(Year, i, 13).equals("Fri")) check[i] = true;
        }

        //13일의 금요일이 존재하는 달 출력
        for(int i=1;i<=12;i++){
            if(check[i]) System.out.println(Year + "년의 " +i + "월의 13일은 금요일");
        }

    }

    public static String Cal(int year, int month, int day){
        int total = 0;

        //윤년 체크
        if((year%4 ==0 && year%100 !=0) || year%400 ==0){
            Year_Month[1] = 29;
        }

        //달
        for(int i=1;i<month;i++){
            total += Year_Month[i-1];
        }

        //일
        total += day-1;


        // 2022년의 1월 1일은 토요일이므로 6.
        int answer = (total+6) %7;

        return Year_day[answer];

    }
}
