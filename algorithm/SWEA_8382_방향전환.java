import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] inputs = in.readLine().split(" ");
			int[] p1 = new int[] { Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) };
			int[] p2 = new int[] { Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]) };
			
			int vertical = Math.abs(p1[0] - p2[0]);
			int horizontal = Math.abs(p1[1] - p2[1]);
			int gap = Math.abs(vertical - horizontal);
			
			int result;
			if (gap == 0) result = Math.min(vertical, horizontal) * 2;
			else if (gap == 1) result = Math.min(vertical, horizontal) * 2 + 1;
			else result = Math.min(vertical, horizontal) * 2 + (gap%2 == 0 ? 4 * gap / 2 : 4 * (gap-1) / 2 + 1);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}

