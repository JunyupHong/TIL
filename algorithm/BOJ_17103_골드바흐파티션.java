import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		int[] numbers = new int[T];
		int max = Integer.MIN_VALUE;
		HashSet<Integer> primes = new HashSet<Integer>();
		
		for (int i = 0; i < T; i++) {
			numbers[i] = Integer.parseInt(in.readLine());
			max = Math.max(max, numbers[i]);
		}
		for (int i = 2; i <= max; i++) {
			primes.add(i);
		}
		for (int i = 2; i <= max/2; i++) {
			for (int j = 2; i*j <= max; j++) {
				primes.remove(i*j);
			}
		}
		int[] primeArr = new int[primes.size()];
		int cnt = 0;
		for (int p : primes) {
			primeArr[cnt++] = p;
		}

		for (int n = 0; n < numbers.length; n++) {
			int answer = 0;
			for (int a : primes) {
				if (a > numbers[n]) break;
				if (primes.contains(numbers[n] - a)) {
					if (numbers[n] - a >= a) answer++;
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
}

