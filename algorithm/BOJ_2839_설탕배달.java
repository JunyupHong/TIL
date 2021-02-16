import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int count = 0;
		while(N > 0 && N%5 != 0) {
			N -= 3;
			count++;
		}
		if (N < 0) System.out.println(-1);
		else System.out.println(count + (N/5));
		
	}
}

