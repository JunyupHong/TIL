import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(in.readLine());
		String[] inputs;
		for (int t = 1; t <= TC; t++) {
			result = 0;
			inputs = in.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int M = Integer.parseInt(inputs[1]);
		
			int[] weights = new int[N];
			inputs = in.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(inputs[i]);
			}
			
			recursive(weights, M, 0, 0, 0);
			if (result == 0) result = -1;
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void recursive(int[] weights, int maxWeight, int idx, int selectCnt, int sumWeight) {
		if (selectCnt == 2) {
			if (sumWeight <= maxWeight) result = Math.max(result,  sumWeight);
			return;
		}
		if (idx == weights.length) return;
		recursive(weights, maxWeight, idx+1, selectCnt, sumWeight);
		recursive(weights, maxWeight, idx+1, selectCnt+1, sumWeight + weights[idx]);
	}
}

