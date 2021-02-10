import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		int R = Integer.parseInt(inputs[2]);
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < inputs.length; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}

		for(String command : in.readLine().split(" ")) {
			switch(Integer.parseInt(command)) {
				case 1:
					map = case1(map, map.length, map[0].length);
					break;
				case 2:
					map = case2(map, map.length, map[0].length);
					break;
				case 3:
					map = case3(map, map.length, map[0].length);
					break;
				case 4:
					map = case4(map, map.length, map[0].length);
					break;
				case 5:
					map = case5(map, map.length, map[0].length);
					break;
				case 6:
					map = case6(map, map.length, map[0].length);
					break;
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static int[][] case1(int[][] map, int N, int M) {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			newMap[i] = map[N-1-i];
		}
		return newMap;
	}
	
	static int[][] case2(int[][] map, int N, int M) {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][M-1-j] = map[i][j];
			}
		}
		return newMap;
	}
	
	static int[][] case3(int[][] map, int N, int M) {
		int[][] newMap = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[j][N - 1 - i] = map[i][j];
			}
		}
		return newMap;
	}
	static int[][] case4(int[][] map, int N, int M) {
		int[][] newMap = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[M-1-j][i] = map[i][j];
			}
		}
		return newMap;
	}
	
	static int[][] case5(int[][] map, int N, int M) {
		int[][] newMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < N/2 && j < M/2) newMap[i][j] = map[i + N/2][j];
				else if (i < N/2 && j >= M/2) newMap[i][j] = map[i][j - M/2];
				else if (i >= N/2 && j >= M/2) newMap[i][j] = map[i - N/2][j];
				else newMap[i][j] = map[i][j + M/2];
			}
		}
		return newMap;
	}
	static int[][] case6(int[][] map, int N, int M) {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < N/2 && j < M/2) newMap[i][j] = map[i][j + M/2];
				else if (i < N/2 && j >= M/2) newMap[i][j] = map[i + N/2][j];
				else if (i >= N/2 && j >= M/2) newMap[i][j] = map[i][j - M/2];
				else newMap[i][j] = map[i - N/2][j];
			}
		}
		return newMap;
	}
}

