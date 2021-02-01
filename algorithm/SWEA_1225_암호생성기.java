import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String input = "";
		Queue<Integer> queue = new LinkedList<Integer>();
		while ((input = in.readLine()) != null) {
			queue.clear();
			int testCase = Integer.parseInt(input);
			
			String[] inputs = in.readLine().split(" ");
			for(int i = 0 ; i< 8; i++) {
				queue.add( Integer.parseInt(inputs[i]) );
			}

			int minus = 1;
			int n = -1;
			while(true) {
				n = queue.poll();
				if (n <= minus) {
					queue.offer(0);
					break;
				}
				queue.offer(n - minus);
				minus = (minus + 1) % 6;
				if (minus == 0) minus = 1;
			}
			
			
			sb.append("#").append(testCase).append(" ");
			while(!queue.isEmpty()) {
				sb.append(queue.poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}

