# 백준 no.11718 그대로 출력하기 no.11719 그대로 출력하기2

## no.11718 그대로 출력하기
* 입력이 주어진다. 입력은 최대 100줄로 이루어져 있고, 알파벳 소문자, 대문자, 공백, 숫자로만 이루어져 있다. 각 줄은 100글자를 넘지 않으며, 빈 줄은 주어지지 않는다. 또, 각 줄은 공백으로 시작하지 않고, 공백으로 끝나지 않는다.

### 답안
``` java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while(scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
```

* 몇 줄이 입력될지 모르므로 hasNextLine() 메소드로 조건을 준다
	* true면 다음줄 출력
	* false면 종료


## no.11719 그대로 출력하기2
* 입력이 주어진다. 입력은 최대 100줄로 이루어져 있고, 알파벳 소문자, 대문자, 공백, 숫자로만 이루어져 있다. 각 줄은 100글자를 넘지 않으며, 빈 줄이 주어질 수도 있고, 각 줄의 앞 뒤에 공백이 있을 수도 있다.

### 답안
``` java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while(scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
```
* 1번과 같은 방식으로 해결