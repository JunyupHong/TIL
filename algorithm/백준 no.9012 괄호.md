# 백준 no.9012 괄호
## 문제
* 괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다. 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)이라고 부른다. 한 쌍의 괄호 기호로 된 “( )” 문자열은 기본 VPS 이라고 부른다. 만일 x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS 가 된다. 그리고 두 VPS x 와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS 가 된다. 예를 들어 “(())()”와 “((()))” 는 VPS 이지만 “(()(”, “(())()))” , 그리고 “(()” 는 모두 VPS 가 아닌 문자열이다. 

* 여러분은 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다. 

## 입력
* 입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 주어진다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다. 하나의 괄호 문자열의 길이는 2 이상 50 이하이다. 

## 출력
* 출력은 표준 출력을 사용한다. 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”, 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다. 


## 답안
``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Stack3 {
	public static void main(String[] args) throws IOException {
		InputStream in = System.in;
		InputStreamReader reader = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(reader);

		int lineCount = Integer.parseInt(br.readLine());

		// 입력된 괄호를 넣을 배열
		String[] stack;

		for (int i = 0; i < lineCount; i++) {
			// ( 의 갯수
			int leftCount = 0;
			boolean flag = true;

			// 입력을 한번에 읽어와서 split 해준다
			stack = br.readLine().split("");

			// 홀수 입력일때
			if (stack.length % 2 != 0) {
				System.out.println("NO");
			}

			// 짝수 입력일때
			else {
				for (int j = 0; j < stack.length; j++) {
					if (stack[j].equals("(")) leftCount++;
					else if(stack[j].equals(")")) leftCount--;

					if (leftCount < 0) {
						System.out.println("NO");
						flag = false;
						break;
					}
				}
				// flag로 두번 출력이 되는 것을 막는다
				if(flag) {
					if (leftCount == 0) {
						System.out.println("YES");
					} else System.out.println("NO");
				}
			}
		}
	}
}
```


## 풀이
* VPS인지 아닌지 확인
	* 입력된 괄호의 수가 홀수 이면 VPS가 아니다 & 짝수이면 VPS일 가능성이 있다
	* 왼쪽괄호가 나오면 leftCount를 1 더해준다 & 오른괄호가 나오면 1 빼준다
	* 이때 leftCount(왼쪽 괄호의 갯수) 가 음수가 되면 VPS가 아니다
	* 입력된 괄호만큼 leftCount를 변경했을 때 leftCount가 0 이라면 VPS이다

### 수정할 사항
* flag 없이 문제를 해결할 수 있는 방법 (flag가 많아지면 유지 보수가 힘들다)
* 이중 for문 없이 해결할 수 없을까..

