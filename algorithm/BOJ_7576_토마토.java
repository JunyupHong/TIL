import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = in.readLine().split(" ");
		
		int M = Integer.parseInt(inputs[0]);
		int N = Integer.parseInt(inputs[1]);
		
		int[][] box = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(inputs[j]);
				if (box[i][j] == 1) {
					queue.offer(new int[] {i, j});
					visited[i][j] = true;
				}
			}
		}
		
		int day = -1;
		while(queue.size() > 0) {
			day++;
			int[][] pos = new int[queue.size()][2];
			for (int i = 0; i < pos.length; i++) {
				pos[i] = queue.poll();
			}
			for (int[] cur : pos) {
				if (0 < cur[0]) {
					if (!visited[cur[0]-1][cur[1]] && box[cur[0]-1][cur[1]] == 0) {
						queue.offer(new int[] { cur[0] - 1, cur[1] });
						visited[cur[0]-1][cur[1]] = true;
					}
				}
				if (cur[0] < N-1) {
					if (!visited[cur[0]+1][cur[1]] && box[cur[0]+1][cur[1]] == 0) {
						queue.offer(new int[] { cur[0] + 1, cur[1] });
						visited[cur[0]+1][cur[1]] = true;
					}
				}
				if (0 < cur[1]) {
					if (!visited[cur[0]][cur[1]-1] && box[cur[0]][cur[1]-1] == 0) {
						queue.offer(new int[] { cur[0], cur[1] - 1 });
						visited[cur[0]][cur[1] - 1] = true;
					}
				}
				
				if (cur[1] < M-1) {
					if (!visited[cur[0]][cur[1]+1] && box[cur[0]][cur[1]+1] == 0) {
						queue.offer(new int[] { cur[0], cur[1] + 1 });
						visited[cur[0]][cur[1]+1] = true;
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] != -1 && !visited[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(day);
	}
}

