import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int T = 1; T <= 10; T++) {
			int cyperLen = Integer.parseInt(in.readLine());
			LinkedList<String> cyper = new LinkedList<String>();
			for (String str : in.readLine().split(" ")) {
				cyper.add(str);
			}
			
			int commandLineCnt = Integer.parseInt(in.readLine());
			String[] commandLine = in.readLine().split("I ");
			for (int i = 1; i < commandLineCnt+1; i++) {
				String[] commands = commandLine[i].split(" ");
				int x = Integer.parseInt(commands[0]);
				int y = Integer.parseInt(commands[1]);
				for (int j = 0; j < y; j++) {
					cyper.add(x+j, commands[j+2]);
				}
			}
			
			sb.append("#").append(T).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(cyper.get(i)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}

