import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int result = 0;
			
			String[] inputs = in.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int K = Integer.parseInt(inputs[1]);
			
			
			int[] dp = new int[N];
			Arrays.fill(dp, 1);
			int[] day = new int[N];

			inputs = in.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				day[i] = Integer.parseInt(inputs[i]);
				for (int j = 0; j < i; j++) {
					if (day[j] < day[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
				}
				if (dp[i] >= K) {
					result = 1;
					break;
				}
			}
			
			sb.append("Case #").append(tc).append("\n").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}

