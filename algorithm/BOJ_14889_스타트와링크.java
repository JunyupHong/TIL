import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs;
		int N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < inputs.length; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		subset(map, 0, new boolean[N], 0);
		System.out.println(min);
	}
	
	public static void subset(int[][] map, int dest, boolean[] selected, int selectCnt) {
		if (dest == selected.length) {
			if (selectCnt != selected.length / 2) return;
			int a = 0;
			int b = 0;
			for (int i = 0; i < selected.length; i++) {
				for (int j = 0; j < selected.length; j++) {
					if (selected[i]) {
						if (selected[j]) a += map[i][j];
					} else {
						if (!selected[j]) b += map[i][j];
					}
				}
				
			}
			min = Math.min(min, Math.abs(a - b));
			return;
		}
		selected[dest] = false;
		subset(map, dest+1, selected, selectCnt);
		selected[dest] = true;
		subset(map, dest+1, selected, selectCnt+1);
	}
}

