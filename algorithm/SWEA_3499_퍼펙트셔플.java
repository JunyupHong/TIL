import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		Queue<String> deck1 = new LinkedList<String>();
		Queue<String> deck2 = new LinkedList<String>();
		
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.setLength(0);
			sb.append("#").append(testCase).append(" ");
			
			int cardCount = Integer.parseInt(in.readLine());
			String[] inputs = in.readLine().split(" ");
			
			for (int i = 0; i < cardCount; i++) {
				if (i < cardCount/2.0) deck1.offer(inputs[i]);
				else deck2.offer(inputs[i]);
			}
			
			while(deck1.size() + deck2.size() > 0) {
				if (deck1.size() > 0) {
					sb.append(deck1.poll()).append(" ");
				}
				if (deck2.size() > 0) {
					sb.append(deck2.poll()).append(" ");
				}
			}
			System.out.println(sb.toString());
		}
	}
}

