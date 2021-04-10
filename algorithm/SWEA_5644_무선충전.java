import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	static class BC implements Comparable<BC> {
		public int x;
		public int y;
		public int distance;
		public int performance;
		public BC(int x, int y, int distance, int performance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.performance = performance;
		}
		
		public boolean possible(int x, int y) {
			return this.distance >= Math.abs(x - this.x) + Math.abs(y - this.y);
		}
		
		@Override
		public int compareTo(BC o) {
			return Integer.compare(o.performance, this.performance);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		int[] p1;
		int[] p2;
		
		for (int tc = 1; tc <= T; tc++) {
			p1 = new int[] { 1, 1 };
			p2 = new int[] { 10, 10 };
			String[] inputs = in.readLine().split(" ");
			int M = Integer.parseInt(inputs[0]);
			int A = Integer.parseInt(inputs[1]);
			
			String[] p1Moves = in.readLine().split(" ");
			String[] p2Moves = in.readLine().split(" ");
			
			BC[] BCs = new BC[A];
			for (int i = 0; i < A; i++) {
				inputs = in.readLine().split(" ");
				BCs[i] = new BC(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]));
			}
			
			Arrays.sort(BCs);
			
			int result = 0;
			
			for (int m = 0; m <= M; m++) {
				ArrayList<BC> p1Chargable = new ArrayList<BC>();
				ArrayList<BC> p2Chargable = new ArrayList<BC>();
				for (BC bc : BCs) {
					if (bc.possible(p1[0], p1[1])) p1Chargable.add(bc);
					if (bc.possible(p2[0], p2[1])) p2Chargable.add(bc);
				}
				if (p1Chargable.size() == 0 || p2Chargable.size() == 0) {
					if (p1Chargable.size() > 0) result += p1Chargable.get(0).performance;
					if (p2Chargable.size() > 0) result += p2Chargable.get(0).performance;
				} else if (p1Chargable.size() == 1 && p2Chargable.size() == 1) {
					if (p1Chargable.get(0) == p2Chargable.get(0)) {
						result += p1Chargable.get(0).performance;
					} else {
						result += p1Chargable.get(0).performance;
						result += p2Chargable.get(0).performance;
					}
				} else if (p1Chargable.size() == 1 ||  p2Chargable.size() == 1) {
					if (p1Chargable.get(0) == p2Chargable.get(0)) {
						if (p2Chargable.size() > 1) {
							result += p1Chargable.get(0).performance;
							result += p2Chargable.get(1).performance;
						} else {
							result += p1Chargable.get(1).performance;
							result += p2Chargable.get(0).performance;
						}
					} else {
						result += p1Chargable.get(0).performance;
						result += p2Chargable.get(0).performance;
					}
				} else if (p1Chargable.size() > 1 && p2Chargable.size() > 1) {
					if (p1Chargable.get(0) == p2Chargable.get(0)) {
						result += p1Chargable.get(0).performance;
						result += Math.max(p1Chargable.get(1).performance, p2Chargable.get(1).performance);
					} else {
						result += p1Chargable.get(0).performance;
						result += p2Chargable.get(0).performance;
					}
				}
				
				if (m == M) break;

				if (p1Moves[m].equals("1")) p1[1] -= 1;
				else if (p1Moves[m].equals("2")) p1[0] += 1;
				else if (p1Moves[m].equals("3")) p1[1] += 1;
				else if (p1Moves[m].equals("4")) p1[0] -= 1;
				
				if (p2Moves[m].equals("1")) p2[1] -= 1;
				else if (p2Moves[m].equals("2")) p2[0] += 1;
				else if (p2Moves[m].equals("3")) p2[1] += 1;
				else if (p2Moves[m].equals("4")) p2[0] -= 1;				
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}

