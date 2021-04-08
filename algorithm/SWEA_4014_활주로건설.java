import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			String[] inputs = in.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int X = Integer.parseInt(inputs[1]);
			int[][] map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				inputs = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(inputs[j]);
				}
				if (checkLine(map[i], X)) answer++;
			}
			
			for (int i = 0; i < N; i++) {
				int[] line = new int[N];
				for (int j = 0; j < N; j++) {
					line[j] = map[j][i];
				}
				if (checkLine(line, X)) answer++;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static boolean checkLine(int[] line, int x) {
		boolean[] isChecked = new boolean[line.length];
		for (int i = 0; i < line.length-1; i++) {
			if (Math.abs(line[i] - line[i+1]) > 1) return false;
			
			if (line[i] == line[i+1]) continue;

			if (line[i] - line[i+1] == 1) {
				for (int j = 0; j < x; j++) {
					if (i + 1 + j > line.length-1) return false;
					if (line[i+1] != line[i+1+j]) return false;
				}
				for (int j = 0; j < x; j++) {
					isChecked[i + 1 + j] = true;
				}
				i = i + x - 1;
			} else if (line[i] - line[i+1] == -1) {
				for (int j = 0; j < x; j++) {
					if (i - j < 0) return false;
					if (isChecked[i-j]) return false;
					if (line[i] != line[i-j]) return false;
				}
			}
		}
		return true;
	}
}

