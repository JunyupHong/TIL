import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs;
		int N = Integer.parseInt(in.readLine());
		int[][] materials = new int[N][2];
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			materials[i][0] = Integer.parseInt(inputs[0]);
			materials[i][1] = Integer.parseInt(inputs[1]);
		}
		
		Arrays.sort(materials, (a,b)->a[1]-b[1]);

		int cover = Integer.MIN_VALUE;
		int count = 0;
		for (int[] m : materials) {
			if (cover >= m[0]) continue;
			
			if (cover < m[0]) {
				cover = m[1];
				count++;
			}
		}
		System.out.println(count);
	}
}

