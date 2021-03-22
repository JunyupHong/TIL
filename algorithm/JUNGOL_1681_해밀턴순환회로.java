import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs;
		int N = Integer.parseInt(in.readLine());
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		boolean[] visited = new boolean[N];
		visited[0] = true;
		dfs(N, map, 0, 0, visited, 0);
		
		System.out.println(min);
	}
	
	static void dfs(int N, int[][] map, int current, int cost, boolean[] visited, int visitCnt) {
		if (visitCnt == N-1) {
			if (map[current][0] == 0) return;
			min = Math.min(min, cost + map[current][0]);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			if (map[current][i] == 0) continue;

			visited[i] = true;
			dfs(N, map, i, cost + map[current][i], visited, visitCnt+1);
			visited[i] = false;
		}
	}
}

