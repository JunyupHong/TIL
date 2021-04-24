import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//public class Solution {
//	static int N, M;
//	static int adj[][];
//	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		
//		int T = Integer.parseInt(in.readLine());
//		String[] inputs;
//		for (int tc = 1; tc <= T; tc++) {
//			N = Integer.parseInt(in.readLine());
//			M = Integer.parseInt(in.readLine());
//			
//			adj = new int[N+1][N+1];
//			for (int m = 0; m < M; m++) {
//				inputs = in.readLine().split(" ");
//				int i = Integer.parseInt(inputs[0]);
//				int j = Integer.parseInt(inputs[1]);
//				
//				adj[i][j] = 1;
//			}
//			
//			int answer = 0;
//			for (int k = 1; k <= N; k++) {
//				if (gtBFS(k, new boolean[N+1]) + ltBFS(k, new boolean[N+1]) == N-1) answer++;
//			}
//			
//			sb.append("#").append(tc).append(" ").append(answer).append("\n");
//		}
//		System.out.println(sb.toString());
//	}
//	
//	private static int gtBFS(int start, boolean[] visited) {
//		Queue<Integer> queue = new LinkedList<Integer>();
//		queue.add(start);
//		visited[start] = true;
//		
//		int cnt = 0;
//		while (!queue.isEmpty()) {
//			int k = queue.poll();
//			for (int i = 1; i <= N; i++) {
//				if (visited[i] || adj[k][i] != 1) continue;
//				
//				queue.offer(i);
//				visited[i] = true;
//				cnt++;
//			}
//		}
//		return cnt;
//	}
//
//	private static int ltBFS(int start, boolean[] visited) {
//		Queue<Integer> queue = new LinkedList<Integer>();
//		queue.add(start);
//		visited[start] = true;
//		
//		int cnt = 0;
//		while (!queue.isEmpty()) {
//			int k = queue.poll();
//			for (int i = 1; i <= N; i++) {
//				if (visited[i] || adj[i][k] != 1) continue;
//				
//				queue.offer(i);
//				visited[i] = true;
//				cnt++;
//			}
//		}
//		return cnt;
//	}
//}

public class Solution {
	static int N, M;
	static int adj[][];
	static int gtCnt, ltCnt;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		String[] inputs;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			M = Integer.parseInt(in.readLine());
			
			adj = new int[N+1][N+1];
			for (int m = 0; m < M; m++) {
				inputs = in.readLine().split(" ");
				int i = Integer.parseInt(inputs[0]);
				int j = Integer.parseInt(inputs[1]);
				
				adj[i][j] = 1;
			}
			
			int answer = 0;
			for (int k = 1; k <= N; k++) {
				gtCnt = 0;
				ltCnt = 0;
				gtDFS(k, new boolean[N+1]);
				ltDFS(k, new boolean[N+1]);
				if (gtCnt + ltCnt == N-1) answer++;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void gtDFS(int cur, boolean[] visited) {
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if (visited[i] || adj[cur][i] != 1) continue;
			gtDFS(i, visited);
			gtCnt++;
		}
	}

	private static void ltDFS(int cur, boolean[] visited) {
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if (visited[i] || adj[i][cur] != 1) continue;
			ltDFS(i, visited);
			ltCnt++;
		}
	}
}

