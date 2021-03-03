import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] inputs;
		
		for (int tc = 1; tc <= 10; tc++) {
			inputs = in.readLine().split(" ");
			int start = Integer.parseInt(inputs[1]);
			
			boolean[][] map = new boolean[101][101];
			for (int i = 0; i < 100; i++) {
				map[i] = new boolean[101];
			}

			inputs = in.readLine().split(" ");
			for (int i = 0; i < inputs.length; i += 2) {
				map[Integer.parseInt(inputs[i])][Integer.parseInt(inputs[i+1])] = true;
			}
			
			Queue<Integer> queue = new LinkedList<Integer>();
			boolean[] visited = new boolean[101];
			queue.offer(start);
			visited[start] = true;
			
			int answer = -1;
			while(true) {
				int[] fromNodes = new int[queue.size()];
				for (int i = 0; i < fromNodes.length; i++) {
					fromNodes[i] = queue.poll();
				}
				
				for (int from : fromNodes) {
					for (int j = 1; j < map[from].length; j++) {
						if (map[from][j] && !visited[j]) {
							visited[j] = true;
							queue.offer(j);
						}
					}
				}
				if (queue.size() == 0) {
					for (int from : fromNodes) {
						answer = Math.max(answer, from);
					}
					break;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}

