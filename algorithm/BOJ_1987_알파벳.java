import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main {
	static int R;
	static int C;
	static String[][] map;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = in.readLine().split(" ");
		R = Integer.parseInt(inputs[0]);
		C = Integer.parseInt(inputs[1]);
		
		map = new String[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().split("");
		}
		
		result = 0;
		dfs(0, 0, 0, new HashSet<String>());
		
		System.out.println(result);
	}
	
	static void dfs (int r, int c, int cnt, Set<String> set) {
		if (r < 0 || r > R-1 || c < 0 || c > C-1) return;
		if (set.contains(map[r][c])) return;
		set.add(map[r][c]);
		
		int[] dr = {-1, 0, 0, 1};
		int[] dc = {0, -1, 1, 0};
		for(int i = 0; i < 4; i++) {
			dfs(r+dr[i], c+dc[i], cnt+1, set);
		}
		set.remove(map[r][c]);
		result = Math.max(result, cnt+1);
		return;
	}
}


