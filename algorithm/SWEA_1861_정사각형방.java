import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.setLength(0);
			int N = Integer.parseInt(in.readLine());
			int[][] map = new int[N][N];
			int[][] dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] str = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str[j]);
					dp[i][j] = -1;
				}
			}			
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int targetNum = map[i][j] + 1;
					dp[i][j] = 1;
					if (i > 0 && targetNum == map[i - 1][j]) {
						if (dp[i-1][j] == -1) findPossibleCount(N, map, dp, i-1, j);
						dp[i][j] += dp[i-1][j];
					}
					if (i < N - 1 && targetNum == map[i + 1][j]) {
						if (dp[i+1][j] == -1) findPossibleCount(N, map, dp, i+1, j);
						dp[i][j] += dp[i+1][j];
					}
					if (j > 0 && targetNum == map[i][j - 1]) {
						if (dp[i][j-1] == -1) findPossibleCount(N, map, dp, i, j-1);
						dp[i][j] += dp[i][j-1];
					}
					if (j < N - 1 && targetNum == map[i][j + 1]) {
						if (dp[i][j+1] == -1) findPossibleCount(N, map, dp, i, j+1);
						dp[i][j] += dp[i][j+1];
					}
				}
			}
			
			int resultSum = 0;
			int resultNum = Integer.MAX_VALUE;
			
			for (int i = N-1 ; i>=0; i--) {
				for (int j = N-1; j>=0; j--) {
					if (dp[i][j] > resultSum) {
						resultSum = dp[i][j];
						resultNum = map[i][j];
					} else if (dp[i][j] == resultSum && map[i][j] <= resultNum) {
						resultSum = dp[i][j];
						resultNum = map[i][j];
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(resultNum).append(" ").append(resultSum);
			System.out.println(sb.toString());
		}
	}
	
	static int findPossibleCount(int N, int[][] map, int[][] dp, int i, int j) {
		int count = 1;
		int targetNum = map[i][j] + 1;
		
		if (i > 0 && targetNum == map[i - 1][j]) {
			count += findPossibleCount(N, map, dp, i-1, j);
		}
		if (i < N - 1 && targetNum == map[i + 1][j]) {
			count += findPossibleCount(N, map, dp, i+1, j);
		}
		if (j > 0 && targetNum == map[i][j - 1]) {
			count += findPossibleCount(N, map, dp, i, j-1);
		}
		if (j < N - 1 && targetNum == map[i][j + 1]) {
			count += findPossibleCount(N, map, dp, i, j+1);
		}
		dp[i][j] = count;
		return count;
	}
}

