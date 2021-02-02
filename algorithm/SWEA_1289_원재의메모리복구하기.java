import java.util.Scanner;

class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T;
		T= sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String[] bits = sc.next().split("");  
			boolean startFlag = false;
			String prev = "";
			int answer = 0;
			for (int i = 0; i < bits.length; i++) {
				if (i > 0)
					prev = bits[i-1];
				if (bits[i].equals("1"))
					startFlag = true;
				if (startFlag && !bits[i].equals(prev)) {
					answer++;
				}
				
			}
			System.out.println("#" + test_case + " " + answer);
		}
		
		sc.close();
	}
}

