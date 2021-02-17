import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int i = 0;
	static String[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		map = new String[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().split("");
		}
		System.out.println(recursive(N, 0, 0));
	}
	
	static String recursive(int len, int r, int c) {
		if (len == 1) return map[r][c];

		String lt = recursive(len/2, r, c);
		String rt = recursive(len/2, r, c+len/2);
		String lb = recursive(len/2, r+len/2, c);
		String rb = recursive(len/2, r+len/2, c+len/2);
		
		if (lt.length() + rt.length() + lb.length() + rb.length() == 4
				&& lt.equals(rt) && rt.equals(lb) && lb.equals(rb)) return lt;
		
		return new StringBuilder().append("(").append(lt).append(rt).append(lb).append(rb).append(")").toString();
	}
}

