import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[][] costs = new int[N][3];
		
		String[] inputs;
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				costs[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		int[][] dp = new int[N][3];
		dp[0] = costs[0];
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];
		}
		
		int min = Integer.MAX_VALUE;
		for (int n: dp[N-1]) {
			if (min > n) min = n;
		}
		
		System.out.println(min);
	}
}

