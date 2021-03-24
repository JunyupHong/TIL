import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static int[] parents;
	static int find(int idx) {
		if (parents[idx] == idx) return idx;
		return parents[idx] = find(parents[idx]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	static class Line implements Comparable<Line> {
		public int start;
		public int end;
		public double weight;
		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Line o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			
			parents = new int[N];
			for (int i = 0; i < N; i++) {
				parents[i] = i;
			}
			
			Line[] lines = new Line[N*N];
			
			String[] x = in.readLine().split(" ");
			String[] y = in.readLine().split(" ");
			double E = Double.parseDouble(in.readLine());
			
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					lines[cnt] = new Line(i, j);
					lines[cnt].weight = Math.pow(Integer.parseInt(x[i]) - Integer.parseInt(x[j]), 2)
							+ Math.pow(Integer.parseInt(y[i]) - Integer.parseInt(y[j]), 2);
					cnt++;
				}
			}
			
			Double answer = 0.0;
			Arrays.sort(lines);
			for (int i = 0; i < lines.length; i++) {
				if (lines[i].weight == 0) continue;
				if (union(lines[i].start, lines[i].end)) {
					answer += lines[i].weight;
				}
			}
			sb.append("#").append(tc).append(" ").append(Math.round(answer * E)).append("\n");
		}
		System.out.println(sb.toString());
	}
}
