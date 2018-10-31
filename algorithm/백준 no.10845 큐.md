# 백준 no.10845 큐
## 큐의 개념을 익히고 실습해 봅니다
* 정수를 저장하는 큐를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.

* 명령은 총 여섯 가지이다.

	* push X: 정수 X를 큐에 넣는 연산이다.
	* pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	* size: 큐에 들어있는 정수의 개수를 출력한다.
	* empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
	* front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	* back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.


* 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.

### 답안
``` java
import java.util.Scanner;

public class Queue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int commandCount = sc.nextInt();
        int[] queue = new int[commandCount];

        int front = 0;
        int back = -1;

        for (int i = 0; i < queue.length; i++) {
            String command = sc.next();


            switch (command) {
                case "push":
                    queue[++back] = sc.nextInt();
                    break;

                case "pop":
                    if (back + 1 - front <= 0) System.out.println(-1);
                    else System.out.println(queue[front++]);
                    break;

                case "size":
                    if(back + 1 - front > 0) System.out.println(back + 1 - front);
                    else System.out.println(0);
                    break;

                case "empty":
                    if (back + 1 - front <= 0) System.out.println(1);
                    else System./out/.println(0);
                    break;

                case "front":
                    if (back + 1 - front <= 0) System.out.println(-1);
                    else System.out.println(queue[front]);
                    break;

                case "back":
                    if (back + 1 - front <= 0) System.out.println(-1);
                    else System.out.println(queue[back]);
                    break;
            }
        }
    }
}
```


### 틀린 이유
* front: 큐의 가장 앞에 있는 원소 출력 =>가장 먼저 들어간 원소를 출력
* back: 큐의 가장 뒤에 있는 원소 출력 => 가장 나중에 들어간 원소를 출력
* 즉, push는 back으로 들어가고 pop은 front로 나온다!
