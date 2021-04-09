import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	private static int N;
	private static long[][] adjMatrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			
			int[] x = new int[N];
			int[] y = new int[N];
			
			String[] inputs = in.readLine().split(" ");
			
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(inputs[i]);
			}
			
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(inputs[i]);
			}
			
			
			// 인접행렬 구성
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjMatrix[i][j] = getDistance(x[i], y[i], x[j], y[j]);
				}
			}
			
			double E = Double.parseDouble(in.readLine());
			System.out.println("#" + tc + " " + Math.round(makeMST() * E));
		}
	}
	
	static long getDistance(int x1, int y1, int x2, int y2) {
		return (long) Math.pow(x1 - x2, 2) + (long) Math.pow(y1 - y2, 2);
	}
	
	// Prim 알고리즘
	static long makeMST() {
		long[] minEdge = new long[N];
		boolean[] visited = new boolean[N];
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		// 1. 임의의 정점을 시작점으로 만듬
		minEdge[0] = 0;
		
		long result = 0; // 최소 신장트리 가중치의 합
		int cnt = 0; // 정점 개수
		while (true) {
			// 2. 신장 트리에 포함되지 않은 정점 중 최소 간선 비용의 정점 선택
			long min = Integer.MAX_VALUE;
			int minIdx = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) continue;
				if (min > minEdge[i]) {
					minIdx = i;
					min = minEdge[i];
				}
			}
			
			visited[minIdx] = true; // 신장트리에 포함
			result += min;
			
			if (++cnt == N) break; // 신장트리를 완성하면 종료
			
			// 3. 현재 선택된 정점(minIdx)에서 MST가 아닌 다른 정점으로 가는 가중치를 업데이트
			for (int i = 0; i < N; i++) {
				if (visited[i]) continue;
				if (minEdge[i] > adjMatrix[minIdx][i]) {
					minEdge[i] = adjMatrix[minIdx][i];
				}
			}
		}
		return result;
	}
}

