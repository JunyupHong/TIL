import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int min;
	static int[] initPos;
	static int[] robotPos;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] inputs;
		while(true) {
			inputs = in.readLine().split(" ");
			if (inputs[0].equals("0") && inputs[1].equals("0")) break;
			
			min = Integer.MAX_VALUE;
			robotPos = new int[] { -1, -1 };

			int w = Integer.parseInt(inputs[0]);
			int h = Integer.parseInt(inputs[1]);
			
			String[][] map = new String[h][w];
			ArrayList<int[]> dirty = new ArrayList<int[]>();
			for (int i = 0; i < h; i++) {
				inputs = in.readLine().split("");
				for(int j = 0; j < w; j++) {
					map[i][j] = inputs[j];
					if (map[i][j].equals("o")) robotPos = new int[] { i, j };
					else if (map[i][j].equals("*")) dirty.add(new int[] { i, j });
				}
			}
			
			permutation(map, dirty, 0, new int[dirty.size()][2], new boolean[dirty.size()]);
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}
	public static void permutation(String[][] map, ArrayList<int[]> dirty, int dest, int[][] selected, boolean[] visited) {
		if (dest == dirty.size()) {
			int distance = 0;
			int[] start = new int[] { robotPos[0], robotPos[1] };
			for (int i = 0; i < selected.length; i++) {
				int res = move(map, new boolean[map.length][map[0].length], start[0], start[1], selected[i][0], selected[i][1]);
				if (res == -1) {
					min = -1;
					break;
				} else {
					distance += res;
					start[0] = selected[i][0];
					start[1] = selected[i][1];
				}
			}
			min = Math.min(min, distance);
			return;
		}
		
		for (int i = 0; i < dirty.size(); i++) {
			if (visited[i]) continue;
			visited[i] = true;
			selected[dest] = dirty.get(i);
			permutation(map, dirty, dest+1, selected, visited);
			visited[i] = false;
		}
	}
	public static int move(String[][] map, boolean[][] visited, int startI, int startJ, int destI, int destJ) {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { startI, startJ });
		int distance = 0;
		while(!queue.isEmpty()) {
			int cnt = queue.size();
			while(cnt > 0) {
				int[] current = queue.poll();
				visited[current[0]][current[1]] = true;
				if (current[0] == destI && current[1] == destJ) return distance;
				
				for (int i = 0; i < 4; i++) {
					int nr = current[0] + dr[i];
					int nc = current[1] + dc[i];
					if (nr < 0 || map.length-1 < nr || nc < 0 || map[0].length-1 < nc) continue;
					if (visited[nr][nc]) continue;
					visited[nr][nc] = true;
					if (map[nr][nc].equals("x")) continue;
					queue.add(new int[] { nr, nc });
				}
				cnt--;
			}
			distance++;
		}
		return -1;
	}
}

