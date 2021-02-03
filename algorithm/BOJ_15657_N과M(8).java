import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = in.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		str = in.readLine().split(" ");
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(numbers);
		
		combination(M, 0, numbers, new int[M]);
		System.out.println(sb.toString());
	}
	
	static void combination(int M, int dest, int[] numbers, int[] selected) {
		if (M == dest) {
			for (int i = 0; i < M; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < numbers.length; i++) {
			if (dest > 0 && selected[dest - 1] > numbers[i]) continue;
			selected[dest] = numbers[i];
			combination(M, dest + 1, numbers, selected);
		}
	}
}

