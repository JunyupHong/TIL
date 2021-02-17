import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	static ArrayList<int[]> chicken;
	static ArrayList<int[]> house;
	static int N;
	static int M;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		result = Integer.MAX_VALUE;
		int[][] map = new int[N][N];
		
		house = new ArrayList<int[]>();
		chicken = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
				if (inputs[j].equals("1")) house.add(new int[] {i, j});
				else if (inputs[j].equals("2")) chicken.add(new int[] {i, j});
			}
		}
		
		combination(0, new int[M][2], 0);
		System.out.println(result);
	}
	
	static void combination(int dest, int[][] selected, int start) {
		if (dest == M) {
			int totalDistance = 0;
			for (int[] h : house) {
				int chickenDistance = Integer.MAX_VALUE;
				for (int[] s : selected) {
					chickenDistance = Math.min(chickenDistance, Math.abs(h[0] - s[0]) + Math.abs(h[1] - s[1]));
				}
				totalDistance += chickenDistance;
			}
			result = Math.min(result, totalDistance);
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			selected[dest] = chicken.get(i);
			combination(dest+1, selected, i+1);
		}
	}
}

