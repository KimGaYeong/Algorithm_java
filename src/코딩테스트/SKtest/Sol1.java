package 코딩테스트.SKtest;

public class Sol1 {
    public static void main(String[] args) {
        int money = 4578;
        int[] cost = {1, 4, 99, 35, 50, 1000};

        System.out.println(solution(money, cost));
    }

    public static int solution(int money, int[] costs) {
        int[] val = {1,5,10,50,100,500};
        int[] dp = new int[money+1];
        int make = Integer.MAX_VALUE;
        dp[0] =1;
        coin[] coins = new coin[6];
        for(int i=0;i<6;i++){
            coins[i] = new coin(val[i], costs[i]);
        }

        return make;
    }


    public static class coin{
        int value;
        int make;

        public coin(int value, int make) {
            this.value = value;
            this.make = make;
        }
    }
}
