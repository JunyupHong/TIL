# 백준 no.10828 스택
## 스택의 개념을 익히고 실습해 봅니다
* 정수를 저장하는 스택을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.

* 명령은 총 다섯 가지이다.
	* push X: 정수 X를 스택에 넣는 연산이다.
	* pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	* size: 스택에 들어있는 정수의 개수를 출력한다.
	* empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
	* top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.

* 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.

### 답안
``` java
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		
		int commandCount = sc.nextInt();
		int[] stack = new int[commandCount];

		int top = -1;

		for (int i = 0; i < commandCount; i++) {
			String command = sc.next();
			switch (command) {
				case "push":
					stack[++top] = sc.nextInt();
					break;
				case "pop":
					if (top != -1) System.out.println(stack[top--]);
					else System.out.println(-1);
					break;
				case "size":
					System.out.println(top + 1);
					break;
				case "empty":
					if (top != -1) System.out.println(0);
					else System.out.println(1);
					break;
				case "top":
					if (top != -1) System.out.println(stack[top]);
					else System.out.println(-1);
					break;
			}
		}
	}
}
```


### 틀린 이유
* 초기에는 for문 내에서 sc.nextLine()으로 한번에 받아서 split(“ “)으로 명령과 숫자를 분리했는데 이때 마지막 줄의 입력은 들어가지 않은듯하다(마지막 입력은 줄바꿈이 없어서) 
	* sc.next()로 command를 받고 다시 sc.nextInt()로 숫자를 받는 것으로 바꿈
* Integer.parseInt(sc.next()); 구문을 사용했는데 이로 인해 런타임 오류가 났다
	* sc.nextInt()를 사용해서 한번에 int 입력을 받아온다


### 수정 사항
* Stack이라는 클래스를 만들어서 Main클래스에서 사용
* Main의 switch 구문에 코드가 들어가는게 아니라 Stack의 메소드를 이용해 기능을 구현








