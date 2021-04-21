import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		
		int N = Integer.parseInt(inputs[0]);
		int d = Integer.parseInt(inputs[1]);
		int k = Integer.parseInt(inputs[2]);
		int c = Integer.parseInt(inputs[3]);
		
		int[] counts = new int[d+1];
		int[] belt = new int[N+k];
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(in.readLine());
		}
		for (int i = N; i < N + k; i++) {
			belt[i] = belt[i-N];
		}
		
		HashSet<Integer> temp = new HashSet<Integer>();
		for (int i = 0; i < k; i++) {
			temp.add(belt[i]);
			counts[belt[i]]++;
		}
		
		
		int type = temp.size();
		int max = type + (temp.contains(c) ? 0 : 1);

		for (int i = 0; i < N; i++) {
			counts[belt[i]]--;
			if (counts[belt[i]] == 0) type--;
			if (counts[belt[i+k]] == 0) type++;
			counts[belt[i+k]]++;
			if (counts[c] == 0) max = Math.max(max, type+1);
			else max = Math.max(max, type);
		}
		System.out.println(max);
	}
}

