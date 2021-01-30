import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			String[] NM = in.readLine().split(" ");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			
			int[][] map = new int[N+2][N+2];
			
			for (int i = 0; i < N + 2; i++) {
				map[0][i] = 0;
				map[N+1][i] = 0;
			}
			for (int i = 1; i < N+1; i++) {
				String[] input = in.readLine().split(" ");
				map[i][0] = 0;
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = Integer.parseInt(input[j-1]);
				}
				map[i][N+1] = 0;
			}
			
			int result = 0;
			for (int i = 0; i < N+2 - M; i++) {
				for (int j = 0; j < N+2 - M; j++) {
					int temp = 0;
					for (int k = i; k < i + M; k++) {
						for (int l = j; l < j + M; l++) {
							temp += map[k][l];
						}
					}
					if (temp > result)
						result = temp;
				}
			}
			System.out.println("#" + testCase + " " + result);
		}
	}
}

