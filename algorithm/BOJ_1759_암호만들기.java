import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] inputs = in.readLine().split(" ");
		int L = Integer.parseInt(inputs[0]);
		int C = Integer.parseInt(inputs[1]);
		
		inputs = in.readLine().split(" ");
		ArrayList<String> charList = new ArrayList<String>();
		for (String s : inputs) {
			charList.add(s);
		}
		charList.sort(null);
		
		String[] chars = new String[C];
		for (int i = 0; i < C; i++) {
			chars[i] = charList.get(i);
		}
		
		ArrayList<String> answers = new ArrayList<String>();
		combination(L, 0, 0, chars, new String[L], answers);
		for (String ans : answers) {
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void combination(int N, int start, int dest, String[] chars, String[] selected, ArrayList<String> answers) {
		if (dest == N) {
			int a = 0;
			int b = 0;
			for (String s: selected) {
				if (s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u")) a++;
				else b++;
			}
			if (a == 0) return;
			if (b < 2) return;
			answers.add(String.join("", selected));
			return;
		}
		for (int i = start; i < chars.length; i++) {
			selected[dest] = chars[i];
			combination(N, i+1, dest+1, chars, selected, answers);
		}
	}
}

