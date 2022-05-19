package algorithm.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
'.'는 빈 공간을 나타낸다.
'*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
'$'는 상근이가 훔쳐야하는 문서이다.
알파벳 대문자는 문을 나타낸다.
알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
 */
public class bj9328 {
    static int h, w, result;
    static char[][] map;
    static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
    static Queue<Info> sides;
    static boolean[] keys;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        InputStream input = bj1806.class.getResourceAsStream("input.txt");
        System.setIn(input);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            for(int i=0;i<h;i++){
                String str = br.readLine();
                for(int j=0;j<w;j++){
                    map[i][j] = str.charAt(j);
                }
            }

            keys = new boolean[26];
            String key = br.readLine();
            if(!key.equals("0")){
                for(int i=0;i<key.length();i++){
                    //존재하는 키들을 keys 배열에 넣어줌.
                    keys[(char)(key.charAt(i)-97)] = true;
                }
            }

            //System.out.println(Arrays.toString(keys));
            result =0;

            // 바깥 껍질에서 갈 수 있는 곳이나 키 찾기.
            // 문서..도 찾으면 이득

            sides = new LinkedList<>();
            visit = new boolean[h][w];
            Queue<Info> check = new LinkedList<>();

            // 옆면 훑기
            for(int j=0;j<w;j+=w-1){
                for(int i=1;i<h-1;i++){
                    //System.out.println(i + " " + j);
                    if(map[i][j] == '$'){//문서
                        map[i][j] = '.';
                        result ++;
                        sides.add(new Info(i,j));
                    }
                    else if(map[i][j] >= 'a' && map[i][j] <= 'z'){ //열쇠
                        keys[(char)(map[i][j]-97)] = true;
                        sides.add(new Info(i,j));
                    }else if(map[i][j] >= 'A' && map[i][j] <= 'Z'){ //문(키가 있느냐 없느냐에 따라 다름)
                        int idx= (int)(map[i][j])-65;
                        if(keys[idx]){
                            sides.add(new Info(i,j)); //-> 근데 생각해보니까 ... 여기서 없어도 나중에 키가 생길 수도 있지 않나?
                        }else{
                            check.add(new Info(i,j)); //-> 다 돌고나서 키가 생겼는지 한번 더 확인해야됨.
                        }
                    }else if(map[i][j]=='.'){
                        sides.add(new Info(i,j));
                    }
                    visit[i][j] = true;
                }
            }
            //System.out.println("sides: " + sides);

            //System.out.println("옆면 훑음");

            // 위아래면 훑기
            for(int i=0;i<h;i+=h-1){
                for(int j=0;j<w;j++){
                    if(map[i][j] == '$'){//문서
                        map[i][j] = '.';
                        result ++;
                        sides.add(new Info(i,j));
                    }
                    else if(map[i][j] >= 'a' && map[i][j] <= 'z'){ //열쇠
                        keys[(char)(map[i][j]-97)] = true;
                        sides.add(new Info(i,j));
                    }else if(map[i][j] >= 'A' && map[i][j] <= 'Z'){ //문(키가 있느냐 없느냐에 따라 다름)
                        int idx= (int)(map[i][j])-65;
                        if(keys[idx]){
                            sides.add(new Info(i,j)); //-> 근데 생각해보니까 ... 여기서 없어도 나중에 키가 생길 수도 있지 않나?
                        }else{
                            check.add(new Info(i,j)); //-> 다 돌고나서 키가 생겼는지 한번 더 확인해야됨.
                        }
                    }else if(map[i][j]=='.'){
                        sides.add(new Info(i,j));
                    }
                    visit[i][j] = true;
                }
            }

//            System.out.println("아랫면 훑음");
//
//            System.out.println("check: " + check);
//            System.out.println("sides: " + sides);

            int checksize = check.size();
            while(checksize-->0){
                Info info = check.poll();
                int idx= (int)(map[info.x][info.y])-65;
                if(keys[idx]){
                    sides.add(new Info(info.x,info.y));
                }else{ //여기서도 없을수도 있으니 다시 채워줌.
                    check.add(new Info(info.x,info.y));
                }
            }
            //이렇게 혹시 모르게 키가 생긴 애들까지 다 채워주고....

            //System.out.println("체크1 훑음");



            //side에서 하나씩 꺼내면서 이동을 시킨다.
            //System.out.println("ssssides : "+ sides);
            while(true){

                if(sides.size()==0) break;

                while(!sides.isEmpty()){
                    int cx= sides.peek().x;
                    int cy = sides.peek().y;
                    sides.poll();

                    for(int d=0;d<4;d++){
                        int nx = cx + del[d][0];
                        int ny = cy + del[d][1];

                        if(isIn(nx,ny) && map[nx][ny] !='*' && !visit[nx][ny]){
                            if(map[nx][ny]  >= 'a' && map[nx][ny]  <= 'z'){
                                visit[nx][ny] = true;
                                keys[(char)(map[nx][ny]-97)] = true;
                                sides.add(new Info(nx,ny));
                            }else if(map[nx][ny]  >= 'A' && map[nx][ny]  <= 'Z'){
                                visit[nx][ny] = true;
                                int idx= (int)(map[nx][ny])-65;
                                if(keys[idx]){
                                    sides.add(new Info(nx,ny));
                                }else{ //여기서도 없을수도 있으니 다시 채워줌.
                                    check.add(new Info(nx,ny));
                                }
                            }else if(map[nx][ny] == '$'){//문서
                                visit[nx][ny] = true;
                                map[nx][ny] = '.';
                                result ++;
                                sides.add(new Info(nx,ny));
                            }else if(map[nx][ny]=='.'){
                                visit[nx][ny] = true;
                                sides.add(new Info(nx,ny));
                            }
                        }

                    }
                }

                //System.out.println("check: " + check);
                checksize = check.size();
                while(checksize-->0){
                    Info info = check.poll();
                    int idx= (int)(map[info.x][info.y])-65;
                    if(keys[idx]){
                        sides.add(new Info(info.x,info.y));
                    }else{ //여기서도 없을수도 있으니 다시 채워줌.
                        check.add(new Info(info.x,info.y));
                    }
                }
            }
            //System.out.println("끝남!" + t);

            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    public static boolean isIn(int x, int y){
        return x>=0 && y>=0 && x<h && y<w;
    }
    public static class Info{
        int x,y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
