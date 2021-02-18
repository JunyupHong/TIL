import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	static int N;
	static int[] company;
	static int[] house;
	static int[][] customers;
	static boolean[] isVisited;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			String[] inputs = in.readLine().split(" ");
			
			company = new int[] {Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])};
			house = new int[] {Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3])};
			customers = new int[N][2];
			isVisited = new boolean[N];
			result = Integer.MAX_VALUE;
			
			int idx = 0;
			for (int i = 4; i < 2 * N + 4; i += 2) {
				customers[idx++] = new int[] {Integer.parseInt(inputs[i]), Integer.parseInt(inputs[i + 1])};
			}
			
			int[][] selected = new int[N+1][2];
			selected[0] = company;
			dfs(1, selected, 0);
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	
	static void dfs(int dest, int[][] selected, int distance) {
		if (distance > result) return;
		if (dest == N+1) {
			result = Math.min(result,
					distance + Math.abs(selected[dest-1][0] - house[0]) + Math.abs(selected[dest-1][1] - house[1]));
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (isVisited[i]) continue;
			isVisited[i] = true;
			selected[dest] = customers[i];
			dfs(dest+1, selected,
					distance + Math.abs(selected[dest-1][0] - customers[i][0]) + Math.abs(selected[dest-1][1] - customers[i][1]));
			isVisited[i] = false;
		}
	}
}

