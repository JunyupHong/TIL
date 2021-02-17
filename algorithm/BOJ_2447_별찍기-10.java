import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static String[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		map = new String[N][N];
		
		recursive(N, 0, 0);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null) sb.append(map[i][j]);
				else sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void recursive(int n, int r, int c) {
		if (n == 3) {
			for (int i = 0; i < 3; i++) map[r][c+i] = "*";
			map[r+1][c] = "*"; 
			map[r+1][c+2] = "*";
			for (int i = 0; i < 3; i++) map[r+2][c+i] = "*";
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			recursive(n/3, r + n/3*i, c);
		}
		recursive(n/3, r, c + n/3);
		recursive(n/3, r + n/3*2, c + n/3);
		for (int i = 0; i < 3; i++) {
			recursive(n/3, r + n/3*i, c+n/3*2);
		}
	}
}

