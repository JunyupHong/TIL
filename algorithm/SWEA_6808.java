import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Solution {
	static int win;
	static int lose;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		String[] inputs;
		for (int testCase = 1; testCase <= T; testCase++) {
			win = 0;
			lose = 0;
			
			Set<Integer> numbers = new HashSet<Integer>();
			
			int[] a = new int[9];
			int[] b = new int[9];
			
			inputs = in.readLine().split(" ");
			
			for (int i = 1; i <= 18; i++) {
				numbers.add(i);
			}
			
			for (int i = 0; i < 9; i++) {
				a[i] = Integer.parseInt(inputs[i]);
				numbers.remove(a[i]);
			}
			
			int bIdx = 0;
			for (int n : numbers) {
				b[bIdx++] = n;
			}

			permutation(b, 0, new int[9], new boolean[9], a);
			
			sb.append("#").append(testCase).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void permutation(int[] numbers, int dest, int[] selected, boolean[] isVisited, int[] compare) {
		if (dest == selected.length) {
			int sumA = 0;
			int sumB = 0;
			for (int i = 0; i < 9; i++) {
				if (selected[i] > compare[i]) sumA += selected[i] + compare[i];
				else sumB += selected[i] + compare[i];
			}
			
			if (sumA < sumB) win++;
			else if (sumA > sumB) lose++;

			return;
		}
		for (int i = 0; i < numbers.length; i++) {
			if (isVisited[i]) continue;
			isVisited[i] = true;
			selected[dest] = numbers[i];
			permutation(numbers, dest+1, selected, isVisited, compare);
			isVisited[i] = false;
		}
	}
}

