import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static StringBuilder sb;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String str = in.readLine();
		N = str.charAt(0) - '0';
		M = str.charAt(2) - '0';
		recursive(0, new int[M]);
		System.out.println(sb.toString());
	}
	
	static void recursive(int dest, int[] numbers) {
		if (dest == M) {
			for (int i = 0; i < numbers.length; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			numbers[dest] = i;
			recursive(dest+1, numbers);
		}
	}
}

