import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	static int[] heights;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		heights = new int[9];
		for (int i = 0; i < 9; i++) {
			heights[i] = Integer.parseInt(in.readLine());
		}
		
		combination(0, new int[7], 0);
	}
	
	static void combination(int dest, int[] selected, int start) {
		if (dest == 7) {
			int total = 0;
			for (int s: selected) {
				total += s;
			}
			if (total == 100) {
				Arrays.sort(selected);
				for (int s : selected) {
					System.out.println(s);
				}
			}
			return;
		}
		for(int i = start; i < heights.length; i++) {
			selected[dest] = heights[i];
			combination(dest+1, selected, i+1);
		}
	}
}

