import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
	public static void main (String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int count = Integer.parseInt(in.readLine());
		Stack<int[]> towers = new Stack<int[]>();
		
		String[] inputs = in.readLine().split(" ");
		
		int height = 0;
		towers.push(new int[] {Integer.MAX_VALUE, 0});
		for (int i = 0; i < inputs.length; i++) {
			height = Integer.parseInt(inputs[i]);
			while (towers.peek()[0] <= height && towers.size() > 1) {
				towers.pop();
			}
			sb.append(towers.peek()[1]).append(" ");
			towers.push(new int[] {height, i+1});
		}
		System.out.println(sb.toString());
	}
}

