import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(in.readLine());
			
			String[] inputs;
			
			int[][] distance = new int[n+2][n+2];
			
			int[][] stores = new int[n+2][2];
			for (int i = 0; i < n+2; i++) {
				inputs = in.readLine().split(" ");
				stores[i] = new int[]{ Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) };
			}
			for (int i = 0; i < n+2; i++) {
				for (int j = 0; j < n+2; j++) {
					if (i == j) {
						distance[i][j] = Integer.MAX_VALUE;
						continue;
					}
					int gap = Math.abs(stores[i][0] - stores[j][0]) + Math.abs(stores[i][1] - stores[j][1]);
					if (gap <= 1000) distance[i][j] = 1;
					else distance[i][j] = Integer.MAX_VALUE;
				}
			}
			
			
			for (int k = 0; k < n+2; k++) {
				for (int i = 0; i < n+2; i++) {
					for(int j = 0; j < n+2; j++) {
						if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE) { 
							distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
						}
					}
				}
			}
			
			if (distance[0][n+1] != Integer.MAX_VALUE) System.out.println("happy");
			else System.out.println("sad");
		}
	}

}

