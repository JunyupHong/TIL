import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int N;
	static int result;
	static int[][] menus;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		
		N = Integer.parseInt(in.readLine());
		
		result = Integer.MAX_VALUE;
		menus = new int[N][2];
		String[] inputs;		
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			menus[i][0] = Integer.parseInt(inputs[0]);
			menus[i][1] = Integer.parseInt(inputs[1]);
		}
		
		sub(new int[N][2], 0, 0);
		System.out.println(result);
	}
	
	static void sub(int[][] selected, int dest, int start) {
		if (dest != 0) {
			int[] tempResult = new int[2];
			tempResult[0] = 1;
			tempResult[1] = 0;
			for (int i = 0; i < dest; i++) {
				tempResult[0] *= selected[i][0];
				tempResult[1] += selected[i][1];
			}
			result = Math.min(Math.abs((tempResult[0] - tempResult[1])), result);
		}
		if (start == N) return;
		
		sub(selected, dest, start+1);
		selected[dest] = menus[start];
		sub(selected, dest+1, start+1);
	}
}

