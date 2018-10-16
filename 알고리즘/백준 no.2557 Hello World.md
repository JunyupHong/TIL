# 백준 no.2557 Hello World
## “Hello World!” 를 화면에 출력하는 문제
### 답안
``` java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

* System.out은 PrintStream 클래스의 객체
* PrintStream은 콘솔에 값을 출력할 때 사용되는 클래스
* 보통 System.out.println은 콘솔에 문자열을 출력할 경우나 디버깅 시 많이 사용

* System.err라는 것도 있는데 System.out과 동일한 역할(콘솔에 값을 출력).
* 다만 System.err는 오류메시지를 출력할 경우에 사용한다

