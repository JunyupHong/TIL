import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int answer = 0;
		int N = Integer.parseInt(in.readLine());
		PriorityQueue<Integer> cards = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			cards.add(Integer.parseInt(in.readLine()));
		}
		
		while(cards.size() > 1) {
			int temp = cards.poll() + cards.poll();
			answer += temp;
			cards.add(temp);
		}
		
		System.out.println(answer);
	}
}

