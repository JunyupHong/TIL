import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[] numbers = new int[9];
		for (int i = 0; i < 9; i++) {
			numbers[i] = Integer.parseInt(in.readLine());
		}
		combination(numbers, 0, new int[7], 0);
	}
	static void combination(int[] numbers, int dest, int[] selected, int start) {
		if (dest == 7) {
			int result = 0;
			for (int i = 0; i< dest; i++) {
				result += selected[i];	
			}
			if (result != 100) return;
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i< dest; i++) {
				sb.append(selected[i]).append("\n");
			}
			System.out.println(sb.toString());
			return;
		}
		if (start == 9) return;
		
		combination(numbers, dest, selected, start+1);
		selected[dest] = numbers[start];
		combination(numbers, dest+1, selected, start+1);
	}
}

