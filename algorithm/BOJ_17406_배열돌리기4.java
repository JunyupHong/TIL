import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = in.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		int K = Integer.parseInt(inputs[2]);
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		int[][] commands = new int[K][3];
		for (int k = 0; k < K; k++) {
			inputs = in.readLine().split(" ");
			commands[k] = new int[] {Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])};
		}

		result = Integer.MAX_VALUE;
		permutation(map, commands, 0, new boolean[K], new int[K]);
		System.out.println(result);
	}
	
	static void permutation(int[][] map, int[][] commands, int dest, boolean[] isVisited, int[] selectedIdx) {
		if (commands.length == dest) {
			int[][] newMap = new int[map.length][map[0].length];
			for (int i = 0; i < newMap.length; i++) {
				for (int j = 0; j < newMap[0].length; j++) {
					newMap[i][j] = map[i][j];
				}
			}
			for (int idx : selectedIdx) {
				rotate(newMap, commands[idx][0]-1, commands[idx][1]-1, commands[idx][2]);
			}
			
			for (int i = 0 ; i < newMap.length; i++) {
				int temp = 0;
				for (int j = 0; j < newMap[0].length; j++) {
					temp += newMap[i][j];
				}
				result = Math.min(result, temp);
			}
			return;
		}
		for (int i = 0; i < commands.length; i++) {
			if (isVisited[i]) continue;
			isVisited[i] = true;
			selectedIdx[dest] = i;
			permutation(map, commands, dest+1, isVisited, selectedIdx);
			isVisited[i] = false;
		}
	}
	
	static void rotate(int[][] map, int r, int c, int s) {
		for (int gap = 0; gap < s; gap++) {
			int left = c-s+gap;
			int right = c+s-gap;
			int top = r-s+gap;
			int bottom = r+s-gap;
			
			int tr = map[top][right];
			int br = map[bottom][right];
			int bl = map[bottom][left];
			int tl = map[top][left];
			
			for (int j = right; j > left; j--) map[top][j] = map[top][j-1];
			for (int i = bottom; i > top; i--) map[i][right] = map[i-1][right];
			map[top+1][right] = tr;
			for (int j = left; j < right; j++) map[bottom][j] = map[bottom][j+1];
			map[bottom][right-1] = br;
			for (int i = top; i < bottom; i++) map[i][left] = map[i+1][left];
			map[bottom-1][left] = bl;
		}
	}
}

