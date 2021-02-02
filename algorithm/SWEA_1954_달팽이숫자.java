import java.util.Scanner;

class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		int t = sc.nextInt();
		for (int testCase = 1; testCase <= t; testCase++) {
			int N = sc.nextInt();
			String[][] map = new String[N][N];

			int i = 0, j = -1;
			int dir = 0;
			for (int n = 1; n <= N * N; n++) {
				i += dr[dir];
				j += dc[dir];
				map[i][j] = Integer.toString(n);
				
				if (dir == 0) {
					if (j + dc[dir] >= N || map[i][j + dc[dir]] != null)
						dir = (dir + 1) % 4;
				} else if (dir == 1) {
					if (i + dr[dir] >= N || map[i + dr[dir]][j] != null)
						dir = (dir + 1) % 4;
				} else if (dir == 2) {
					if (j + dc[dir] < 0 || map[i][j + dc[dir]] != null)
						dir = (dir + 1) % 4;
				} else if (dir == 3) {
					if (i + dr[dir] < 0 || map[i+ dr[dir]][j] != null)
						dir = (dir + 1) % 4;
				}
			}
			System.out.println("#" + testCase);
			for (i = 0; i < N; i++) {
				System.out.println(String.join(" ", map[i]));
			}
			
		}
		
	}
}

