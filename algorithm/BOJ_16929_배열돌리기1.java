import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		
		
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		int R = Integer.parseInt(inputs[2]);
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		for (int gap = 0; gap < Math.min(N, M) / 2; gap++) {
			for (int r = 0; r < R; r++) {
				int head = map[gap][gap];
				for (int j = gap; j < M - gap - 1; j++) map[gap][j] = map[gap][j+1];
				int newHead = map[gap][gap];
				for (int i = gap; i < N - gap - 1; i++) map[i][M - gap -1] = map[i+1][M - gap - 1];
				for (int j = M - gap - 1; j > gap; j--) map[N - gap - 1][j] = map[N - gap - 1][j-1];
				for (int i = N - gap - 1; i > gap; i--) map[i][gap] = map[i-1][gap];
				map[gap+1][gap] = head;
				map[gap][gap] = newHead;
				
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}

