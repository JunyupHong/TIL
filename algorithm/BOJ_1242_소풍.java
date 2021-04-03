import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = in.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);
		int M = Integer.parseInt(inputs[2]) - 1; // idx로 계산할꺼니까 -1 처리
		
		int answer = 0;
		int removeIdx = 0;
		while (true) {
			removeIdx += K-1;
			removeIdx %= N; // 지워야할 사람을 찾음
			answer++; // 지움
			if (removeIdx == M) break; // 지운게 M이면 종료
			if (removeIdx < M) M--; // M보다 앞 사람이 지워지면 M을 앞으로 떙김
			N--; // 전체 인원이 1명 줄어듬
		}
		
		System.out.println(answer);
	}
}

