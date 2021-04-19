import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		result = Integer.MAX_VALUE;
		String[] inputs = in.readLine().split(" ");
		
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		
		int[][] map = new int[N][N];
		ArrayList<int[]> house = new ArrayList<int[]>();
		ArrayList<int[]> chicken = new ArrayList<int[]>();
		
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
				if (map[i][j] == 1) house.add(new int[] { i, j });
				else if (map[i][j] == 2) chicken.add(new int[] { i, j });
			}
		}
		combination(house, chicken, M, 0, 0, new int[M][2]);
		System.out.println(result);
		
	}
	
	static void combination(ArrayList<int[]> house, ArrayList<int[]> chicken, int M, int dest, int start, int[][] selected) {
		if (M == dest) {
			int total = 0;
			for (int i = 0; i < house.size(); i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < M; j++) {
					min = Math.min(min, getDistance(house.get(i), selected[j]));
				}
				total += min;
			}
			result = Math.min(result, total);
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			selected[dest] = chicken.get(i);
			combination(house, chicken, M, dest+1, i+1, selected);
		}
		
	}
	
	static int getDistance(int[] start, int[] dest) {
		return Math.abs(start[0] - dest[0]) + Math.abs(start[1] - dest[1]);
	}
}

