import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	private static int MOD = 1234567891;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {			
			String[] inputs = in.readLine().split(" ");
			
			int N = Integer.parseInt(inputs[0]);
			int R = Integer.parseInt(inputs[1]);
			
			long[] factorial = new long[N+1];
			factorial[0] = 1;
			for (int i = 1; i <= N; i++) factorial[i] = (factorial[i-1] * i) % MOD;
			
			long a = (factorial[R] * factorial[N-R]) % MOD;
			long b = fermat(a, MOD-2);
			
			sb.append("#").append(tc).append(" ").append((factorial[N] * b % MOD)).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static long fermat(long n, int x) {
        if (x == 0) return 1;
        long tmp = fermat(n, x / 2);
        long ret = (tmp * tmp) % MOD;
        if (x % 2 == 0) return ret;
        else return (ret * n) % MOD;
    }
}

