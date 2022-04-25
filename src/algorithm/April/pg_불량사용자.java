package algorithm.April;

import java.util.*;

public class pg_불량사용자 {

    static HashSet<String> set;
    static boolean[] visited;
    static StringTokenizer st;
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};

        System.out.println(solution(user_id, banned_id));
    }

    //user_id 배열의 크기는 1 이상 8 이하입니다. OK
    //banned_id 배열 각 원소들의 값은 길이가 1 이상 8 이하인 문자열입니다.
    //
    // 범위가 넘 충분한걸

    public static int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>(); //셋에 아이디 조합을 넣어 관리.
        visited = new boolean[user_id.length];

        for(int i = 0; i < banned_id.length; i++) {
            //정규 표현식
            banned_id[i] = banned_id[i].replace("*", "."); //임의의 한 문자와 매핑되는지 확인.
        }

        DFS(0, "", user_id, banned_id);
        return set.size();
    }

    public static void DFS(int cnt, String result, String[] user, String[] ban) {
        if(cnt == ban.length) {
            st = new StringTokenizer(result);
            String[] str = new String[st.countTokens()];
            int i =0 ;
            while(st.hasMoreTokens()){
                str[i] = st.nextToken();
                i++;
            }
            Arrays.sort(str);

            String tmp = "";
            for(int j = 0; j < str.length; j++) {
                tmp += str[j];
            }
            if(!set.contains(tmp)) set.add(tmp);
            return;
        }

        for(int i = 0; i < user.length; i++) {
            if(!visited[i] && user[i].matches(ban[cnt])) {
                visited[i] = true;
                DFS(cnt + 1, result + " " + user[i], user, ban);
                visited[i] = false;
            }
        }
    }
}
