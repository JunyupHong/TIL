import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		int r = Integer.parseInt(inputs[1]);
		int c = Integer.parseInt(inputs[2]);

		System.out.println(find((int) Math.pow(2, N), 0, r, c));
	}

	static int find(int len, int cnt, int r, int c) {
		if (cnt == N) return 0;

		int half = len / 2;
		if (r < half && c < half) {
			return find(half, cnt + 1, r, c);
		} else if (r < half && c >= half) {
			return half * half * 1 + find(half, cnt + 1, r, c - half);
		} else if (r >= half && c < half) {
			return half * half * 2 + find(half, cnt + 1, r - half, c);
		} else {
			return half * half * 3 + find(half, cnt + 1, r - half, c - half);
		}
	}
}

