import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int[] dr = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			String[][] map = new String[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = in.readLine().split("");
			}
			int answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].equals(".") && checkZero(map, N, i, j)) {
						bfs(map, N, i, j);
						answer++;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].equals(".")) answer++;
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	static boolean checkZero(String[][] map, int N, int i, int j) {
		boolean isZero = true;
		for (int k = 0; k < 8; k++) {
			int nr = i + dr[k];
			int nc = j + dc[k];
			if (!(0 <= nr && nr < N && 0 <= nc && nc < N)) continue;
			if (map[nr][nc].equals("*")) {
				isZero = false;
				break;
			}
		}
		return isZero;
	}
	
	static void bfs(String[][] map, int N, int i, int j) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { i, j });
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			if (!map[current[0]][current[1]].equals(".")) continue;

			int bomb = 0;
			for (int k = 0; k < 8; k++) {
				int nr = current[0] + dr[k];
				int nc = current[1] + dc[k];
				if (!(0 <= nr && nr < N && 0 <= nc && nc < N)) continue;
				if (map[nr][nc].equals("*")) bomb++;
			}
			map[current[0]][current[1]] = Integer.toString(bomb);
			if (bomb == 0) {
				for (int k = 0; k < 8; k++) {
					int nr = current[0] + dr[k];
					int nc = current[1] + dc[k];
					if (!(0 <= nr && nr < N && 0 <= nc && nc < N)) continue;
					queue.add(new int[] { nr, nc });
				}
			}
		}
	}
}

