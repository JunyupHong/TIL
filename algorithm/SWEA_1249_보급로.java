import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			int[][] map = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			int[][] dp = new int[N][N];
			
			String[] inputs;
			for (int i = 0; i < N; i++) {
				inputs = in.readLine().split("");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(inputs[j]);
					visited[i][j] = false;
					dp[i][j] = Integer.MAX_VALUE;
				}
			}

			visited[0][0] = true;
			dp[0][0] = 0;
			dp[0][1] = map[0][1];
			dp[1][0] = map[1][0];
			
			int[] dx = new int[] { -1, 1, 0, 0 };
			int[] dy = new int[] { 0, 0, -1, 1 };
			
			while(true) {
				int min = Integer.MAX_VALUE;
				int minX = -1;
				int minY = -1;
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (visited[i][j] || dp[i][j] == Integer.MAX_VALUE) continue;
                        if(dp[i][j] < min){
                        	min = Math.min(min, dp[i][j]);
                            minX = i;
                            minY = j;
                        }
					}
				}
				
				visited[minX][minY] = true;
				if (minX == N-1 && minY == N-1) break;

				for (int i = 0; i < 4; i++) {
					int nextX = minX + dx[i];
					int nextY = minY + dy[i];
					if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
						if (visited[nextX][nextY]) continue;
						dp[nextX][nextY]
								= Math.min(dp[nextX][nextY],
										dp[minX][minY] + map[nextX][nextY]);
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(dp[N-1][N-1]).append("\n");
		}
		System.out.println(sb.toString());
	}
}

