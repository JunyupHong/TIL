import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str = in.readLine();
		N = str.charAt(0) - '0';
		M = str.charAt(2) - '0';
		
		combination(0, new int[M], 1);
	}
	
	
	static void combination(int dest, int[] numbers, int start) {
		if (dest == M) {
			System.out.print(numbers[0]);
			for (int i = 1; i < numbers.length; i++) {
				System.out.print(" " + numbers[i]);	
			}
			System.out.println("");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			numbers[dest] = i;
			combination(dest + 1, numbers, i + 1);
		}
	}
}

