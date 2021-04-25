import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		String[] inputs;
		for (int tc = 1; tc <= T; tc++) {
			inputs = in.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int M = Integer.parseInt(inputs[1]);
			int C = Integer.parseInt(inputs[2]);
			
			int map[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				inputs = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(inputs[j]);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(combination(map, N, M, C)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int combination(int[][] map, int N, int M, int C) {
		int result = 0, aBenefit = 0, bBenefit = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N-M+1; j++) {
				
				maxSum = 0;
				subset(map, M, C, i, j, 0, 0, 0);
				aBenefit = maxSum;
				
				maxSum = 0;
				bBenefit = 0;
				for (int j2 = j+M; j2 < N-M+1; j2++) {
					subset(map, M, C, i, j2, 0, 0, 0);
				}
				for (int i2 = i+1; i2 < N; i2++) {
					for (int j2 = 0; j2 < N-M+1; j2++) {
						subset(map, M, C, i2, j2, 0, 0, 0);
					}
				}
				bBenefit = Math.max(maxSum, bBenefit);
				
				result = Math.max(result, aBenefit + bBenefit);
			}
		}
		return result;
	}

	static int maxSum = 0;
	static void subset(int[][] map, int M, int C, int i, int j, int cnt, int sum, int powerSum) {
		if (sum > C) return;
		if (cnt == M) {
			maxSum = Math.max(maxSum, powerSum);
			return;
		}
		
		subset(map, M, C, i, j+1, cnt+1, sum + map[i][j], powerSum + map[i][j] * map[i][j]);
		subset(map, M, C, i, j+1, cnt+1, sum, powerSum);
	}
	
}

