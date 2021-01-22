import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Solution {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int testCase = 1; testCase <= 10; testCase++) {
			sb.setLength(0);
			sb.append("#").append(testCase).append(" ");
			
			in.readLine();
			String[] inputs = in.readLine().split("");
			Stack<String> stack = new Stack<String>();
			
			boolean result = true;
			for (String s: inputs) {
				if (!result) break;
				
				if (s.equals("(") || s.equals("{") || s.equals("[") || s.equals("<")) {
					stack.push(s);
				} else {
					if (stack.isEmpty()) {
						result = false;
						break;
					}
					switch(stack.pop()) {
						case "{":
							if (!s.equals("}")) {
								result = false;
							}
							break;
						case "[":
							if (!s.equals("]")) {
								result = false;
							}
							break;
						case "<":
							if (!s.equals(">")) {
								result = false;
							}
							break;
						case "(":
							if (!s.equals(")")) {
								result = false;
							}
							break;
					}
				}
			}
			if (!stack.isEmpty()) {
				System.out.println(sb.append("0").toString());
			} else {
				System.out.println(sb.append(result ? "1" : "0").toString());
			}
		}
	}
}

