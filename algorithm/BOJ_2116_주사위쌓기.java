import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		result = 0;
		int N = Integer.parseInt(in.readLine());
		String[] inputs;
		int[][] dice = new int[N][6];
		
		int[] cross = {5, 3, 4, 1, 2, 0};
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < inputs.length; j++) {
				dice[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		for (int i = 1; i <= 6; i++) {
			recursive(N, dice, cross, 0, i, 0);
		}
		
		System.out.println(result);
	}
	
	static void recursive(int N, int[][] dice, int[] cross, int cnt, int bottom, int total) {
		if (cnt == N) {
			result = Math.max(result, total);
			return;
		}
		
		int idx = 0;
		int sideMax = Integer.MIN_VALUE;
		for (int i = 0; i < 6; i++) {
			if (bottom == dice[cnt][i]) {
				idx = i;
			}
		}
		for (int i = 0; i < 6; i++) {
			if (i != idx && i != cross[idx]) sideMax = Math.max(dice[cnt][i], sideMax);
		}
		recursive(N, dice, cross, cnt+1, dice[cnt][cross[idx]], total + sideMax);
	}
}

