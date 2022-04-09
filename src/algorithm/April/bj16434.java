package algorithm.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * i번째 방에서만 i+1번째 방으로 이동 가능. 몬스터가 있는 경우 몬스터를 없애야 이동 가능.
 * 방에 갔는데 몬스터가 있다면? 용사 공격력 만큼 몬스터 생명력 깎기. 몬스터 생명력이 0이하가 되면 이동 가능.
 * 몬스터 공격력만큼 용사도 생명력이 깎인다. 0 이하가 되면 사망. 포션이 있으면 생명력, 공격력이 증가. (한도는 최대 생명력)
 *
 */
public class bj16434 {
    static int N;
    static Hero hero;
    static long ATK, answer;
    static long[] H;
    static LinkedList<Room> rooms;
    static boolean isOK = false;
    public static void main(String[] args) throws IOException {
        InputStream input = bj16434.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        //입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ATK = Long.parseLong(st.nextToken());

        rooms = new LinkedList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()); //t==1 : 몬스터 t==2 : 포션
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            rooms.add(new Room(t,a,h)); // index, type, attack, hp
            //몬스터라면 몬스터의 a/h, 포션이라면 용사의 a/h를 올려줌.
        }

        // MaxHP의 크기를 정해놓고, N번째 칸의 용을 쓰러트릴 수 있을지 말지를 체크할 계획이다.
        // 두 가지 방법이 있다.
        // 1. 0부터 작은 순으로 차례차례 MaxHP를 올려가며 확인
        // 2. min 0, max 1000000으로 이분 탐색을 통해 MaxHP를 변경시켜가며 확인


        /* 2 -> 뭔가.. 뭔가 2로 풀어보고싶은 마음이므로 그렇게 하겠음.

        mid값을 내가 찾고자 하는 MaxHP라고 가정한 뒤 조건에 만족하는지를 찾는다.
        조건에 만족한다? 그럼 mid = high로 해서 그 전에 더 있나 찾고..
        조건에 만족하지 않으면 mid = low로 해서 찾고.. 이렇게?? */

        long l = 1;
        long h = 123456000000000000L;
        // 123456000000L -> 2번째 테케 안됨.
        // 1000000000000L -> 35퍼에서 안됨
        // 123456000000000000L -> 통과...
        answer = 0;
        binarySearch(l, h);

        System.out.println(answer);
    }

    private static void binarySearch(long low, long high) {	// 배열 room 중에서 찾을 값 n 입력 받는다.

        while ( low <= high ) {
            long mid = (low+high)/2;

            //System.out.println(low + " " +high);
            if (solve(mid)) {// 조건에 만족하면 현재 mid보다 더 작은 값 중에 만족하는 값이 있나 찾고
                high = mid-1;
                answer = mid;
            }else{	// 조건에 만족하지 않으면 현재 mid보다 더 큰 값 중에 만족하는 값이 있나 찾음.
                low = mid+1;
            }
        }
    }

    // Room : int type, long atx, long hp
    // Hero : long curHP, long maxHP, long ATK
    public static boolean solve(long tmpMaxHP){
        hero = new Hero(tmpMaxHP, tmpMaxHP, ATK);
        for(Room room : rooms){
            if(room.type==1){ //몬스터
                long cnt = room.hp / hero.ATK; // 몇 번 때릴 수 있는지 (?) 몫.

                /*
                몬스터 hp가 100, hero attack이 10이면 한 번 때릴 때마다 10씩 닳는다.
                100/10 = 10. (즉 딱 10번째 때렸을 때 2번을 만족한다.
                즉 몬스터는 hero를 9대 때린다.

                몬스터 hp가 100, hero attack이 9라면? 한 번 때릴 때마다 9씩 담는다.
                100/9 = 11. 11번 때릴 수 있고 1이 남기 때문에 1까지 때려야 2번을 만족한다. (10번 때려야 된다.)
                 */
                if((room.hp % hero.ATK) ==0){
                    hero.curHP -= (cnt-1) * room.ATX;
                }else{
                    hero.curHP -= (cnt) * room.ATX;
                }

                if(hero.curHP<=0) return false;

            }else if(room.type==2){ //포션
                // 용사 에너지들 up
                hero.ATK += room.ATX;
                hero.curHP += room.hp;
                if(hero.curHP>= hero.maxHP) hero.curHP = hero.maxHP;
            }
        }
        return true;
    }

    public static class Room{
        int type;
        long ATX, hp;

        public Room(int type, long ATX, long hp) {
            this.type = type;
            this.ATX = ATX;
            this.hp = hp;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "type=" + type +
                    ", ATX=" + ATX +
                    ", hp=" + hp +
                    '}';
        }
    }

    public static class Hero{
        long curHP, maxHP, ATK;

        public Hero(long curHP, long maxHP, long ATK) {
            this.curHP = curHP;
            this.maxHP = maxHP;
            this.ATK = ATK;
        }

        @Override
        public String toString() {
            return "Hero{" +
                    "curHP=" + curHP +
                    ", maxHP=" + maxHP +
                    ", ATK=" + ATK +
                    '}';
        }
    }
}


/*
49

1 1
1 1000000 1000000

999999000001

 */