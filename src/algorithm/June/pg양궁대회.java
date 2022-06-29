package algorithm.June;

// 어피치는 이미 다 쐈고 라이언이 쏠 차례. 라이언이 못이기면 [-1] return. (비기거나 지거나)

import java.util.Arrays;

public class pg양궁대회 {
    public static void main(String[] args) {
        int n = 10;
        int[] info = {0,0,0,0,0,0,0,0,3,4,3};

        //가장 큰 점수차로 우승해야 한다.
        System.out.println(Arrays.toString(solution(n,info)));
        //[1,1,2,0,1,2,2,0,0,0,0]
    }

    static int N, max;
    static int[] answer, apeachInfo, lionInfo;
    public static int[] solution(int n, int[] info) {
        answer = new int[11];
        apeachInfo = info;
        lionInfo = new int[11];
        N = n;
        max = 0;
        DFS(0);

        //System.out.println(max);
        if(max>0) return answer;
        else{
            int[] answer2 = new int[1];
            Arrays.fill(answer2, -1);
            return answer2;
        }
    }

    public static void DFS(int cnt){
        if(cnt>N) return;
        if(cnt==N){
//            System.out.println(Arrays.toString(apeachInfo));
//            System.out.println(Arrays.toString(lionInfo));
            for(int i=0;i<=10;i++){
                if(apeachInfo[i]!=0 && apeachInfo[i] == lionInfo[i]){
                    return;
                }
            }
            solve();
            return;
        }

        for(int i=0;i<=10 && lionInfo[i]<=apeachInfo[i];i++){
            if(lionInfo[i]+1 != apeachInfo[i]){
                lionInfo[i]+=1;
                DFS(cnt+1);
                lionInfo[i] -=1;
            }else{
                if(cnt+2<=N){
                    lionInfo[i] +=2;
                    DFS(cnt+2);
                    lionInfo[i] -=2;
                }
            }
        }

    }

    public static void solve(){
        int apeach = 0;
        int lion = 0;
//만약, k(k는 1~10사이의 자연수)점을 어피치가 a발을 맞혔고 라이언이 b발을 맞혔을 경우
// 더 많은 화살을 k점에 맞힌 선수가 k 점을 가져갑니다.
// 단, a = b일 경우는 어피치가 k점을 가져갑니다.
// k점을 여러 발 맞혀도 k점 보다 많은 점수를 가져가는 게 아니고 k점만 가져가는 것을 유의하세요.
// 또한 a = b = 0 인 경우, 즉, 라이언과 어피치 모두 k점에 단 하나의 화살도 맞히지 못한 경우는
// 어느 누구도 k점을 가져가지 않습니다.
        for(int i=0;i<=10;i++){
            if(apeachInfo[i]==0 && apeachInfo[i] == lionInfo[i]) continue;

            int value = apeachInfo[i] - lionInfo[i];
            //System.out.print(value + " ");
            if(value>=0) apeach+=(10-i);
            else lion+=(10-i);

        }

        if((lion-apeach) >0 && max<=(lion-apeach)){
            max = lion-apeach;
            copy();
            System.out.println("max : " + max + " lionINfo : " + Arrays.toString(lionInfo) );
        }
    }

    public static void copy(){
        for(int i=0;i<=10;i++){
            answer[i] = lionInfo[i];
        }
    }
}

//[1,1,2,0,1,2,2,0,0,0,0]