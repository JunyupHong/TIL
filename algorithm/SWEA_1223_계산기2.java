import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Solution {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> numbers = new Stack<Integer>();
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			sb.setLength(0);
			numbers.clear();
			String length = in.readLine();
			String[] inputs = in.readLine().split("");
			
			for (int i = 0; i < inputs.length; i++) {
				if (inputs[i].equals("+")) {
				} else if (inputs[i].equals("*")) {
					numbers.push(numbers.pop() * Integer.parseInt(inputs[++i]));
				} else {
					numbers.push(Integer.parseInt(inputs[i]));
				}
			}
			
			int result = 0;
			for (int n : numbers) {
				result += n;
			}
			System.out.println(sb.append("#").append(testCase).append(" ").append(result).toString());			
		}
		
	}
}

