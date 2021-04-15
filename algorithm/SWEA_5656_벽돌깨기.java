import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static class Point {
		int r, c, range;
		Point(int r, int c, int range) {
			this.r = r;
			this.c = c;
			this.range = range;
		}
	}
	
	private static int N, W, H, min;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			String[] inputs = in.readLine().split(" ");
			N = Integer.parseInt(inputs[0]);
			W = Integer.parseInt(inputs[1]);
			H = Integer.parseInt(inputs[2]);
			
			int[][] map = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				inputs = in.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(inputs[j]);
				}
			}
			min = Integer.MAX_VALUE;
			drop(0, map);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static boolean drop(int cnt, int[][] map) {
		int result = getRemain(map);
		if (result == 0) {
			min = 0;
			return true;
		}
		if (cnt == N) {
			min = Math.min(min, result);
			return false;
		}
		
		int[][] newMap = new int[H][W];
		// 구슬 떨어뜨리기 시도
		for (int c = 0; c < W; c++) {
			int r = 0;
			while (r < H && map[r][c] == 0) r++;
			
			if (r == H) continue; // 맞는 벽돌 없음
			
			copy(map, newMap); // cnt-1 구슬 상태로 초기화
			remove(newMap, r, c); // 벽돌 깨뜨리기
			down(newMap); // 벽돌 내리기 (빈 공간 채우기)
			if (drop(cnt+1, newMap)) return true; // 다음 구슬 떨어뜨리기
		}
		return false;
	}
	
	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
	
	private static void remove(int[][] map, int r, int c) {
		// bfs로 벽돌을 제거
		Queue<Point> queue = new LinkedList<Point>();
		if(map[r][c] > 1) queue.offer(new Point(r, c, map[r][c]));
		map[r][c] = 0;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r;
				int nc = p.c;
				for (int k = 1; k < p.range; k++) {
					nr += dr[d];
					nc += dc[d];
					if (nr >= 0 && nr < H && 0 <= nc && nc < W && map[nr][nc] != 0) {
						if (map[nr][nc] > 1) queue.offer(new Point(nr, nc, map[nr][nc]));
						map[nr][nc] = 0;
					}
				}
			}
		}
	}

	private static void down(int[][] map) {
		for (int c = 0; c < W; c++) {
			int[] newCol = new int[H];
			int idx = H-1;
			for (int r = H-1; r >= 0; r--) {
				if (map[r][c] == 0) continue;
				else newCol[idx--] = map[r][c];
			}
			for (int r = H-1; r >= 0; r--) {
				map[r][c] = newCol[r];
			}
		}
	}
	private static int getRemain(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0) cnt++;
			}
		}
		return cnt;
	}
}

