import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	static int result = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.setLength(0);
			result = 0;
			String[] inputs = in.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int L = Integer.parseInt(inputs[1]);
			
			int[][] menus = new int[N][2];
			for (int i = 0; i < N; i++) {
				inputs = in.readLine().split(" ");
				menus[i] = new int[] {Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])};
			}

			recursive(L, 0, menus, 0, 0);
			System.out.println(sb.append("#").append(testCase).append(" ").append(result).toString());
		}
	}
	
	static void recursive(int maxCalories, int dest, int[][] menus, int prevFavor, int prevCalories) {
		if (prevCalories > maxCalories) return;
		else result = Math.max(result, prevFavor); 
		if (dest == menus.length) return;
		
		recursive(maxCalories, dest+1, menus, prevFavor + menus[dest][0], prevCalories+menus[dest][1]);
		recursive(maxCalories, dest+1, menus, prevFavor, prevCalories);
	}
}

