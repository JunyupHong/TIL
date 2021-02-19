import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		String[] inputs;
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(in.readLine());
			inputs = in.readLine().split(" ");
			int[] weights = new int[N];
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(inputs[i]);
			}
			sb.append("#").append(t).append(" ").append(dfs(N, weights, 0, 0, 0, new boolean[N])).append("\n");
		}
		System.out.print(sb.toString());
	}
	static int dfs(int N, int[] weights, int dest, int leftWeight, int rightWeight, boolean[] isVisited) {
		if (dest == N) {
			return 1;
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			if (isVisited[i]) continue;
			isVisited[i] = true;
			if (leftWeight >= rightWeight + weights[i]) result += dfs(N, weights, dest+1, leftWeight, rightWeight + weights[i], isVisited);
			result += dfs(N, weights, dest+1, leftWeight + weights[i], rightWeight, isVisited);
			isVisited[i] = false;
		}
		
		return result;
	}
}

