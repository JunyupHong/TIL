import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);
		
		LinkedList<Integer> people = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			people.add(i);
		}
		
		
		sb.append("<");
		int idx = K-1;
		while(people.size() > 0) {
			sb.append(people.get(idx)).append(", ");
			people.remove(idx);
			
			if (people.size() == 0) break;
			idx = (idx + K - 1) % people.size();
		}
		
		sb.setLength(sb.length() - 2);
		sb.append(">");
		System.out.println(sb.toString());
	}
}

