import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Solution {
	static int N;
	static int[][] map;
	static int gap;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		String[] inputs;
		
		for (int t = 1; t <= T; t++) {
			gap = Integer.MAX_VALUE;
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				inputs = in.readLine().split(" ");
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(inputs[j]);
			}
			
			combination(new HashSet<Integer>(), 0);
			sb.append("#").append(t).append(" ").append(gap).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void combination(Set<Integer> selected, int start) {
		if (selected.size() == N/2) {
			int A = 0;
			int B = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (selected.contains(i) && selected.contains(j)) A += map[i][j];
					else if (!selected.contains(i) && !selected.contains(j)) B += map[i][j];
				}
			}
			gap = Math.min(gap, Math.abs(A-B));
			return;
		}
		
		for (int i = start; i < N; i++) {
			selected.add(i);
			combination(selected, i+1);
			selected.remove(i);
		}
	}
}

