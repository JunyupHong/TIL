import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	static int[] parents;
	
	static void make(int N) {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int idx) {
		if (parents[idx] == idx) return idx;
		
		return parents[idx] = findSet(parents[idx]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;

		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = in.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		int k = Integer.parseInt(inputs[2]);
		
		int[] prices = new int[N+1];
		inputs = in.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			prices[i] = Integer.parseInt(inputs[i-1]);
		}
		
		parents = new int[N+1];
		make(N);
		
		for (int i = 0 ; i < M; i++) {
			inputs = in.readLine().split(" ");
			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);
			union(a, b);
		}
		
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 1; i <= N; i++) {
			int root = findSet(i);
			if (map.containsKey(root)) {
				map.put(root, Math.min(prices[i], map.get(root)));
			} else {
				map.put(root, prices[i]);
			}
		}
		
		int min = 0;
		for(Integer i: map.keySet()) {
			min += map.get(i);
		}
		if (min > k) System.out.println("Oh no");
		else System.out.println(min);
	}
}

