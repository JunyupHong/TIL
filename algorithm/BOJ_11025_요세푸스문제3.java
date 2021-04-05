import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		
		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);
		int dp = 1;
		for (int i = 2; i <= N; i++) {
			dp = (dp + K-1) % i + 1;
		}
		
		System.out.println(dp);
	}
}

