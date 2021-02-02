import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			int dumpCount = Integer.parseInt(in.readLine());
			ArrayList<Integer> boxes = new ArrayList<Integer>();
			for (String s : in.readLine().split(" ")) {
				boxes.add(Integer.parseInt(s));
			}
			
			boxes.sort(null);
			while (dumpCount-- > 0) {
				int min = boxes.get(0);
				int max = boxes.get(boxes.size() - 1);
				if (max - min <= 1)
					break;
				boxes.set(0, min + 1);
				boxes.set(boxes.size() - 1, max - 1);
				boxes.sort(null);
			}
			System.out.println("#" + testCase + " " + (boxes.get(boxes.size() - 1) - boxes.get(0)));
		}
	}
}

