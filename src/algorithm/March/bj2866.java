package algorithm.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj2866 {
    public static void main(String[] args) throws IOException {
        InputStream input = bj2170.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        StringBuilder sb;
        int count=0;
        //일단 한줄로 읽은 애들 String 저장.
        char[][] words = new char[R][C];
        for(int i=0;i<R;i++){
            String tmp = br.readLine();
            for(int j=0;j<C;j++){
                words[i][j] = tmp.charAt(j);
            }
        }
        //걔네를 세로로 읽으면서 세로로 자른 단어들을 만든다.
        String[] strings = new String[C]; //-> 비교군
        for(int i=0;i<C;i++){
            sb = new StringBuilder();
            for(int j=1;j<R;j++){//동일한 문자열은 존재하지 않음.
                //즉 j==0인 경우를 넣어서 괜히 귀찮게 예외처리할 필요가 없어서 이렇게함
                sb.append(words[j][i]);
            }
            strings[i] = sb.toString();
        }

        //자른 단어들을 hashset에 넣으면서 비교 (세로 단어들은 무조건 C개)
        //최대 R-1번 자르르 수 있음.
        first : for(int i=0;i<R-1;i++){
            //C개의 세로 문자들을 hashset에 넣으면서 비교
            HashSet<String> hashSet = new HashSet<>();
            for(int x=0;x<C;x++){
                //겹친다 -> 문자열 그만!
                if(hashSet.contains(strings[x])){
                    break first;
                }else{
                    hashSet.add(strings[x]);
                }
            }
            //만약 되면?
            count++;
            //맨 앞에만 한 칸씩 썰어주기
            for(int x=0;x<C;x++){
                strings[x] = strings[x].substring(1);
            }
        }

        System.out.println(count);

    }
}
