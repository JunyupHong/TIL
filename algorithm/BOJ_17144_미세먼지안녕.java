import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		int R = Integer.parseInt(inputs[0]);
		int C = Integer.parseInt(inputs[1]);
		int T = Integer.parseInt(inputs[2]);
		
		int[] airPos = new int[4];
	
		int[][] map = new int[R][C];
		int[][] moveMap = new int[R][C];
		int idx = 0;
		for (int i = 0; i < R; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
				if (map[i][j] == -1) {
					airPos[idx++] = i;
					airPos[idx++] = j;
				}
			}
		}
		
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 0) continue;
					int cnt = 0;
					if (0 < i) {
						if (!(i-1 == airPos[0] && j == airPos[1]) && !(i-1 == airPos[2] && j == airPos[3])) {
							cnt++;
							moveMap[i-1][j] += map[i][j] / 5;
						}
					}
					if (i < R-1) {
						cnt++;
						moveMap[i+1][j] += map[i][j] / 5;
					}
					if (0 < j) {
						if (!(i == airPos[0] && j-1 == airPos[1]) && !(i == airPos[2] && j-1 == airPos[3])) {
							cnt++;
							moveMap[i][j-1] += map[i][j] / 5;
						}
					}
					if (j < C-1) {
						cnt++;
						moveMap[i][j+1] += map[i][j] / 5;
					}
					map[i][j] = map[i][j] - (map[i][j] / 5) * cnt;
				}
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] += moveMap[i][j];
					moveMap[i][j] = 0;
				}
			}
			
			air(map, airPos, R, C);
		}
		
		int result = 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);
	}
	
	static void air(int[][] map, int[] airPos, int R, int C) {
		for (int i = airPos[0]; i > 0; i--) map[i][0] = map[i-1][0];
		for (int j = 0; j < C-1; j++) map[0][j] = map[0][j+1];
		for (int i = 0; i < airPos[0]; i++) map[i][C-1] = map[i+1][C-1];
		for (int j = C-1; j > 0; j--) map[airPos[0]][j] = map[airPos[0]][j-1];
		
		for (int i = airPos[2]; i < R-1; i++) map[i][0] = map[i+1][0];
		for (int j = 0; j < C-1; j++) map[R-1][j] = map[R-1][j+1];
		for (int i = R-1; i > airPos[2]; i--) map[i][C-1] = map[i-1][C-1];
		for (int j = C-1; j > 0; j--) map[airPos[2]][j] = map[airPos[2]][j-1];
			
		map[airPos[0]][airPos[1] + 1] = 0;
		map[airPos[2]][airPos[3] + 1] = 0;
		map[airPos[0]][airPos[1]] = -1;
		map[airPos[2]][airPos[3]] = -1;
	}
}

