import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public static void main(String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int T = 1; T <= 10; T++) {
			int N = Integer.parseInt(in.readLine());
			String[][] tree = new String[N+1][];
			for (int i = 1; i <= N; i++) {
				tree[i] = in.readLine().split(" ");
			}
			
			
			int result = 1;
			for (int i = 1; i <= N; i++) {
				if (tree[i].length == 2) { // 자식이 없음
					if (tree[i][1].matches("[[*]/[-][+]]")) {
						result = 0;
						break;
					}
				} else if (tree[i].length == 3){ // 자식이 하나만 있음
					result = 0;
					break;
				} else { // 자식이 둘 다 있음
					if (!tree[i][1].matches("[[*]/[-][+]]")) {
						result = 0;
						break;
					}
				}
			}
			sb.append("#").append(T).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}

