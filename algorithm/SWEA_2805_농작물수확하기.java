import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(in.readLine());
			
			String line;
			int result = 0;
			int gap = -1;
			for (int i = 0; i <= N / 2; i++) {
				gap++;
				line = in.readLine();
				for (int j = N / 2 - gap; j <= N / 2 + gap; j++)
					result += (int) line.charAt(j) - '0';
			}

			for (int i = N / 2 + 1; i < N; i++) {
				gap--;
				line = in.readLine();
				for (int j = N / 2 - gap; j <= N / 2 + gap; j++)
					result += (int) line.charAt(j) - '0';
			}
			System.out.println("#" + testCase + " " + result);
		}
	}
}

