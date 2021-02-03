import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
		N = (int) str.charAt(0) - '0';
		M = (int) str.charAt(2) - '0';
		permutation(0, N, new int[M], new boolean[ N + 1]);
	}
	
	static void permutation(int dest, int max, int[] numbers, boolean[] isSelected) {
		if (dest == M) {
			System.out.print(numbers[0]);
			for (int i = 1; i< numbers.length; i++) {
				System.out.print(" " + numbers[i]);
			}
			System.out.println("");
			return;
		}
		
		for (int i = 1; i <= max; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			numbers[dest] = i;
			permutation(dest + 1, max, numbers, isSelected);
			isSelected[i] = false;
		}
	}
}

