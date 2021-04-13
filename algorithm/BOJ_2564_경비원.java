import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int result = 0;
		
		String[] inputs = in.readLine().split(" ");
		int width = Integer.parseInt(inputs[0]);
		int height = Integer.parseInt(inputs[1]);
		
		int n = Integer.parseInt(in.readLine());
		int[][] stores = new int[n][2];
		for (int i = 0; i < n; i++) {
			inputs = in.readLine().split(" ");
			stores[i] = new int[] { Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) };
		}
		inputs = in.readLine().split(" ");
		int[] pos = new int[] { Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) };
		
		for (int[] store : stores) {
			if (store[0] == pos[0]) result += Math.abs(store[1] - pos[1]);
			else  {
				if (pos[0] == 1) {
					if (store[0] == 2) result += Math.min(height + store[1] + pos[1], height + (2 * width - store[1] - pos[1]));
					else if (store[0] == 3) result += (store[1] + pos[1]);
					else if (store[0] == 4) result += (width - pos[1] + store[1]);
				} else if (pos[0] == 2) {
					if (store[0] == 1) result += Math.min(height + store[1] + pos[1], height + (2 * width - store[1] - pos[1]));
					else if (store[0] == 3) result += (pos[1] + height - store[1]);
					else if (store[0] == 4) result += (width - pos[1] + height - store[1]);
				} else if (pos[0] == 3) {
					if (store[0] == 1) result += (store[1] + pos[1]);
					else if (store[0] == 2) result += (store[1] + height - pos[1]);
					else if (store[0] == 4) result += Math.min(width + store[1] + pos[1], width + (2 * height - store[1] - pos[1]));
				} else if (pos[0] == 4) {
					if (store[0] == 1) result += (width - store[1] + pos[1]);
					else if (store[0] == 2) result += (width - store[1] + height - pos[1]);
					else if (store[0] == 3) result += Math.min(width + store[1] + pos[1], width + (2 * height - store[1] - pos[1]));
				}
			}
		}
		System.out.println(result);
	}
}
 
