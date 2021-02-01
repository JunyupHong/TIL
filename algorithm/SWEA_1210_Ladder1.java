import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int testCase = 1; testCase <= 10; testCase++) {
			int t = Integer.parseInt(in.readLine());
			String[][] map = new String[100][100];
			for (int i = 0; i < 100; i++) {
				map[100 - i - 1] = in.readLine().split(" ");
			}
			
			
			int[] dx = { 0, -1, 1 };
			int[] dy = { 1, 0, 0 };
			int x = 0, y = 0;
			for (int j = 0; j < 100; j++) {
				if (map[0][j].equals("2")) {
					x = j;
					break;
				}
			}
			
			int direction = 0;
			do {
				x += dx[direction];
				y += dy[direction];
				
				if (direction == 0) {
					if (x > 0 && map[y][x - 1].equals("1")) {
						direction = 1; // left
					} else if (x < 99 && map[y][x + 1].equals("1")) {
						direction = 2; // right
					}
				} else if (direction == 1) {
					if (x > 0 && map[y][x - 1].equals("1")) {
						direction = 1; // left
					} else {
						direction = 0;
					}
				} else if (direction == 2) {
					if (x < 99 && map[y][x + 1].equals("1")) {
						direction = 2; // right
					} else {
						direction = 0;
					}
				}
			} while(y < 99);

			System.out.println("#" + testCase + " " + x);
		}
	}
}

