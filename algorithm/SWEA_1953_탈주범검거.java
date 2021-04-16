import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int N, M, R, C, L, map[][];
	static int[] dr = { -1, 0, 0, 1 }; // 상좌우하
	static int[] dc = { 0, -1, 1, 0 };
	static String[] type = {
		null,
		"0312", // 상하좌우
		"03", // 상하
		"12", // 좌우
		"02", // 상우
		"32", // 하우
		"31", // 하좌
		"01"  // 상좌
	};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		String[] inputs;
		for (int tc = 1; tc <= T; tc++) {
			inputs = in.readLine().split(" ");
			N = Integer.parseInt(inputs[0]);
			M = Integer.parseInt(inputs[1]);
			R = Integer.parseInt(inputs[2]);
			C = Integer.parseInt(inputs[3]);
			L = Integer.parseInt(inputs[4]);
			
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				inputs = in.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(inputs[j]);
				}
			}
			sb.append("#").append(tc).append(" ").append(bfs()).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int bfs() {
		int result = 0;
		int time = 1;
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		
		queue.offer(new int[] { R, C });
		visited[R][C] = true;
		result++;
		while (time++ < L) {
			int size = queue.size();
			while(size-- > 0) {
				int[] cur = queue.poll();
				int r = cur[0];
				int c = cur[1];
				String info = type[map[r][c]];
				
				for (int d = 0; d < info.length(); d++) {
					int dir = info.charAt(d) - '0';
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					if (0 <= nr && nr < N && 0 <= nc && nc < M
							&& map[nr][nc] > 0
							&& !visited[nr][nc]
							&& type[map[nr][nc]].contains(Integer.toString(3-dir))) {
						queue.offer(new int[] { nr, nc });
						visited[nr][nc] = true;
						result++;
					}
				}
			}
		}

		return result;
	}

}

