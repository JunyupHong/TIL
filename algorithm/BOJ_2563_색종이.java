import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int paperCount = Integer.parseInt(in.readLine());
		
		boolean[][] map = new boolean[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				map[i][j] = false;
			}
		}
		
		String[] inputs;
		for (int p = 0; p < paperCount; p++) {
			inputs = in.readLine().split(" ");
			int x = Integer.parseInt(inputs[0]);
			int y = Integer.parseInt(inputs[1]);
			
			for (int i = x; i < x + 10; i++) {
				for (int j = y; j < y + 10; j++) {
					map[i][j] = true;
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j]) result++;
			}
		}
		
		System.out.println(result);
	}
}

