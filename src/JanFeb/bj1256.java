package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class bj1256 {
    static int N, M;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken()); //a개수
        M = Integer.parseInt(st.nextToken()); //b개수
        BigInteger K = new BigInteger(st.nextToken());

        BigInteger original = combi(N+M,M); //현재 조합 개수.
        //System.out.println("original : "+ original);
        //System.out.println("K : " + K);
        if(original.compareTo(K)<0) System.out.println("-1"); //현재 개수보다 찾는수가 더 크면 못찾음.
        else{
            BigInteger leftnum = BigInteger.ZERO;
            while(true){
                //남은 개수가 K보다 큼. (6개중에 내가 찾는게 4번이라 가정)
                BigInteger tmp = leftnum; //6개. (안줄인 애)
                leftnum = leftnum.add(combi(N-1+M, M)); //3개. 맨 앞 a하나빼봄. 맨 앞부터 빼야 작은거부터 찾으니까
                if(leftnum.compareTo(K)<0) {// ex)남은거 3개, 찾는거 4번째)->  못찾음.
                    //System.out.println(leftnum + " < " + K + " -> z추가");
                    M--; //그러니까 z로 시작하는거임.
                    sb.append("z");
                }else if(leftnum.compareTo(K)>0){ //남는거 3개, 찾는거 2번째. 여기에서 찾을 수 있다.
                    //System.out.println(leftnum + " > " + K + " -> a추가");
                    N--;
                    sb.append("a");
                    leftnum = tmp;
                }else if(leftnum.compareTo(K)==0 || N==0 || M==0){ //0일때 남는거 다 더해주고 break;
                    sb.append("a".repeat(Math.max(0, N)));
                    sb.append("z".repeat(Math.max(0, M)));
                    break;
                }

            }
            System.out.println(sb);
        }
    }

    //biginteger 조합
    public static BigInteger combi(int n, int k){
        BigInteger answer = BigInteger.ONE;
        int NminusK =  n-k;
        for(int i=1; i<=k ;i++){
            answer = answer.multiply(BigInteger.valueOf(NminusK + i)).divide(BigInteger.valueOf(i));
        }
        return answer;
    }

}

//N개의 a과 M개의 z a가 앞에 올 수록 빠른 숫자.
/*
숫자가 a,z 두개밖에 없다. 그럼 이진법이랑 관련지어 ??
10으로 바꿔 풀고 나중에 바꿀까???(나중에생각)
모스 부호랑도 문제랑도 관련 있을 듯.

K와 자릿수도 관계가 있을 듯.

K범위 -> 최대 10억. (완탐하면 걍 시간초과)
(n+m)Cn
현재 구하려는 수가 지금 시작하는 수로 시작하는 숫자 범위에 없으면 ?(예를들어 10010) -> 10000로 시작하나? 이 뜻.
n:2 m:2 k:2
가장 작은 수부터. aazz azaz azza zaaz zaza zzaa -> 6가지. 4C2 -> 4.3/2.1
k <6가지 이므로 구할 수 있음.

-> 근데 이 6가지를 어케 구함? ??? 일단 N, M조합으로 구해볼까?? _>Biginteger.


if a로시작한다면? 뒤에꺼 azz가 있으니가 3C1 -> 3
내가 찾고싶은건 2번째이니까 찾을수 있다. (a로 시작)
근데 만약 내가 찾고싶은게 4라면?
3개니까 a로 시작하는게 아님 -> 못찾음.
그럼 z로 시작하니까 3개중에서, 4(내가찾고싶은 수)-3(앞에꺼 개수) : 1번째껄 찾으면 된다.
-> 얘를 반복돌리면 되지않을까????

 */

