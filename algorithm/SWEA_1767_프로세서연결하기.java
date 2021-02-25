import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Solution {
	static ArrayList<int[]> corePos; 
	static int maxCore;
	static int minLine;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine().trim());
			int[][] map = new int[N][N];
			String[] inputs;
			
			corePos = new ArrayList<int[]>();
			maxCore = 0;
			minLine = 0;
			for (int i = 0; i < N; i++) {
				inputs = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(inputs[j].trim());
					if (map[i][j] == 1) {
						if (i == 0 || i == N-1 || j == 0 || j == N-1) {
							maxCore++; 
						} else {
							corePos.add(new int[]{i, j});
						}
					}
				}
			}
			dfs(map, corePos, 0, 0, maxCore);
			
			sb.append("#").append(tc).append(" ").append(minLine).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	
	static void dfs (int[][] map, ArrayList<int[]> corePos, int dest, int line, int core) {
		if (dest == corePos.size()) {
			if (core > maxCore) {
				maxCore = core;
				minLine = line;
			} else if (core == maxCore) {
				minLine = Math.min(minLine, line);
			}
			return;
		}

		int[] pos = corePos.get(dest);

		
		// 상
		boolean flag = true;
		for (int i = pos[0]-1; i >=0; i--) {
			if (map[i][pos[1]] == 0) continue;
			flag = false;
			break;
		}
		if (flag) {
			for (int i = pos[0]-1; i >=0; i--) map[i][pos[1]] = 1;
			dfs(map, corePos, dest+1, line + pos[0], core+1);
			for (int i = pos[0]-1; i >=0; i--) map[i][pos[1]] = 0;
		}
		
		// 하
		flag = true;
		for (int i = pos[0]+1; i < map.length; i++) {
			if (map[i][pos[1]] == 0) continue;
			flag = false;
			break;
		}
		if (flag) {
			for (int i = pos[0]+1; i < map.length; i++) map[i][pos[1]] = 1;
			dfs(map, corePos, dest+1, line + map.length - pos[0] - 1, core+1);
			for (int i = pos[0]+1; i < map.length; i++) map[i][pos[1]] = 0;
		}
		
		// 좌
		flag = true;
		for (int i = pos[1]-1; i >= 0; i--) {
			if (map[pos[0]][i] == 0) continue;
			flag = false;
			break;
		}
		if (flag) {
			for (int i = pos[1]-1; i >= 0; i--) map[pos[0]][i] = 1;
			dfs(map, corePos, dest+1, line + pos[1], core+1);
			for (int i = pos[1]-1; i >= 0; i--) map[pos[0]][i] = 0;
		}
		
		// 우
		flag = true;
		for (int i = pos[1]+1; i < map.length; i++) {
			if (map[pos[0]][i] == 0) continue;
			flag = false;
			break;
		}
		if (flag) {
			for (int i = pos[1]+1; i < map.length; i++) map[pos[0]][i] = 1;
			dfs(map, corePos, dest+1, line + map.length - pos[1] - 1, core+1);
			for (int i = pos[1]+1; i < map.length; i++) map[pos[0]][i] = 0;
		}
		
		// 연결 안됐을때
		dfs(map, corePos, dest+1, line, core);
	}
}





