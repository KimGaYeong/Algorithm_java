package JanFeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj1493 {
    static long L, W, H;
    static int N;
    static long[][] cubes;
    static long[] splits;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Long.parseLong(st.nextToken());
        W = Long.parseLong(st.nextToken());
        H = Long.parseLong(st.nextToken());
        N = Integer.parseInt(br.readLine());

        //입력
        cubes = new long[N][3];
        splits = new long[N]; //지금 갖고있는 애들이 몇개 쓰였는지를 저장
        for (int n=N-1;n>=0;n--){
            st = new StringTokenizer(br.readLine());
            cubes[n][0] = Long.parseLong(st.nextToken()); //2의 n승인지
            cubes[n][1] = (long) Math.pow(2,cubes[n][0]) ; //그 값이 무엇인지
            cubes[n][2] = Long.parseLong(st.nextToken()); // 몇 개 있는지.
        }
        //System.out.println(Arrays.toString(cubes));

        // 시작은 부피로 생각. min(H, L, W) 값이 상자에 채울 수 있는 큐브의 최대 크기가 될 것이고
        // 내가 갖고있는 큐브의 크기가 작은 경우 큐브를 쪼개줌. But
        // 전체 부피로만 계산하면 안됨. 남은 조각의 크기의 합이 큰 큐브의 크기와 같을 경우 오류가 생김.
        // -> 상자를 정육면체 상자로 쪼개는 것. 큰 숫자부터 쪼개자.
        // 정육면체 부피는 한 변의 길이가 1씩 증가할 때 마다 8씩 늘어난다. 1, 8, 64 ...

        long min = Math.min(Math.min(L, W), H); //min ==4
        long save = 0;
        for(int i=0;i<N;i++){
            if(cubes[i][1] > min) {
                continue;
            } //if cube에 3이 있었다면 continue 됐겠징
            else { //4 존재.
                long split = cubes[i][1]; //지금 쪼개는 기준 큐브의 한변의 길이 (4)
                long count = cubes[i][2]; //내가 갖고있는 개수.
                long l = L / split;
                long w = W / split;
                long h = H / split;
                //l*w*h = 기준 크기로 쪼갤 수 있는 큐브의 총 개수.
                save *= 8;
                long tmp= l * w * h - (save); //지금 쪼개는걸로 만들 수 있는 큐브의 총 개수.
                /*
                System.out.println("한변의 길이 " + split + " 내가 가지고 있는 개수 " + count);
                System.out.println("한변의 길이 " + split + " 쪼갤 수 있는 개수 " + l*w*h);
                System.out.println("지금 쪼개는걸로 만들 수 있는 큐브의 총 개수. " + save);
                */
                // 내가 갖고있는 애가 개수가 부족하면 byebye

                if(count>= tmp){ //save만큼 저장 가능!
                    splits[N-1-(int)cubes[i][0]] = tmp;// 4로 쪼갠 애는 이만 큼 가질 수 있음.
                    cubes[i][2] -= tmp;
                    save += tmp;

                }else{ //count만큼 저장 가능.
                    splits[N-1-(int)cubes[i][0]] = count;// 4로 쪼갠 애는 이만 큼 가질 수 있음.
                    cubes[i][2] -= count;
                    save += count;
                }
            }
            //System.out.println("------> save " + save);
        }
        long result =0;
        long cnt =0;
        for(int i=0;i<N;i++){
            //System.out.println("n의3승 " + (long)Math.pow(cubes[i][1],3));
            //System.out.println("몇개 " + splits[i]);
            cnt+= splits[i];
            result += ((long)Math.pow(cubes[i][1],3) * splits[i]);
        }

        //System.out.println(Arrays.toString(splits));
        if(result == W*L*H){
            System.out.println(cnt);
        }else {
            System.out.println("-1");
        }
    }
}
