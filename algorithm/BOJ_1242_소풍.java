import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = in.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);
		int M = Integer.parseInt(inputs[2]) - 1;		
		int answer = 0;
		int removeIdx = 0;
		while (true) {
			removeIdx += K-1;
			removeIdx %= N;
			answer++;
			if (removeIdx == M) break;
			if (removeIdx < M) M--;
			N--;
		}
		
		System.out.println(answer);
	}
}

