package algorithm.April;

import java.io.*;
import java.util.StringTokenizer;

public class bj2615 {

	static int[][] map = new int[21][21];
	static int[][][] memo = new int[21][21][4];
	static int[][] del = {{1,0},{1,1},{0,1},{-1,1}};
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		InputStream input = bj19236_1.class.getResourceAsStream("input.txt");
		System.setIn(input);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int i = 1; i <= 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		findFive();
		System.out.println(sb);
	}

	private static void findFive() {
		for (int j = 1; j <= 19; j++) {
			for (int i = 1; i <= 19; i++) {
				if (map[i][j] != 0) {
					for (int d = 0; d < 4; d++) {
						if (memo[i][j][d] == 0 && calc(i, j, d, map[i][j]) == 5) {
							sb.append(map[i][j] + "\n" + i + " " + j + "\n");
							return;
						}
					}
				}
			}
		}
		sb.append("0");
	}

	private static int calc(int x, int y, int dir, int color) {
		int nx = x + del[dir][0];
		int ny = y + del[dir][1];

		if (map[nx][ny] == color) {
			return memo[nx][ny][dir] = calc(nx, ny, dir, color) + 1;
		}
		return 1;
	}

}