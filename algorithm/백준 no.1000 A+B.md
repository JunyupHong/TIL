# 백준 no.1000 A+B
## 두 수를 입력받고 합을 출력하는 문제
### 답안
``` java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();
        System.out.println(a + b);
    }
}
```

- - - -

### 콘솔 입력을 얻을수 있는 방법

* 자바의 내장클래스인 InputStream의 System.in을 사용하는 방법
	* 입력값을 byte로 받아들인다 ( => InputStreamReader를 사용해 해결)
	* read() 메소드를 이용하므로 어떠한 입력을 받아도 1byte만 받을 수 있다 => 입력받을 byte를 미리 인자로 넘겨주어야한다 ( -> BufferedReader를 사용해 해결)
``` java
// java.lang 패키지에 속해있지 않은 클래스는 import를 해서 사용
import java.io.InputStream;

public class StreamTest {
	public static void main(String[] args) throws Exception {
		// System.in은 InputStream의 객체
		InputStream in = System.in;

		int a;
		// read()메소드는 1byte의 입력을 받아들인다
			// 사용자가 a를 입력하면 아스키코드값 97이 저장됨
			// ab를 입력하면 1byte인 a의 값 97만 저장됨
		a = in.read();

		// 여러 byte의 값을 얻기 위해서는 byte배열을 만든 후에 read메소드 호출시 입력값으로 전달해야한다
		byte[] arr = new byte[3];
		in.read(arr);	// 3byte를 읽어옴

		System.out.println(a);
    }
}
```

* InputStreamReader를 사용하는 방법
	* 문자로 입력 스트림을 읽는다
	* read() 메소드를 이용하므로 어떠한 입력을 받아도 1byte만 받을 수 있다 => 입력받을 byte를 미리 인자로 넘겨주어야한다 ( -> BufferedReader를 사용해 해결)
``` java
import java.io.InputStream;
// InputStreamReader의 객체를 가져옴
import java.io.InputStreamReader;

public class StreamTest {
	public static void main(String[] args) throws Exception {
		InputStream in = System.in;
		InputStreamReader reader = new InputStreamReader(in);
		char[] a = new char[3];
		
		// 사용자가 abc를 입력하면 byte로 변환되는것이 아니라
		// 문자열 abc가 출력됨
		// 하지만 여전히 읽어들일 값을 미리 인자로 넘겨주어야한다
		reader.read(a);

		System.out.println(a);
	}
}
```

* BufferedReader를 사용하는 방법
	* 사용자 입력을 전부 받아들이는 방법
	* InputStreamReader에 버퍼링 기능을 추가한 것
	* 데이터를 사용자가 요청할때마다 매번 읽어오기 보다는 일정량사이즈로 한번에 읽어온 후 버퍼에 보관 => 사용자가 요구할 때 버퍼에서 읽어옴 => 속도 향상, 소요시간 감소

``` java
// BufferedReader를 import한다
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamTest {
	public static void main(String[] args) throws Exception {
		InputStream in = System.in;
		InputStreamReader reader = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(reader);

		// 사용자가 입력한 문자열을 모두 받아온다
		String a = br.readLine();
		System.out.println(a);
	}
}
```

* Scanner 클래스를 사용하는 방법 (위의 답안)

> 콘솔  
> 사용자의 입력을 받거나 사용자에게 문자열을 출력해 주는 역할을 하는 것을 통칭하는 말  


### Scanner와 BufferedReader의 차이점
* Scanner는 Scanner 클래스만 import, BufferedReader는 2개의 클래스를 import 해주어야한다
* Scanner는 예외처리를 해주지않아도 되고, BufferedReader는 예외처리를 해주어야만 에러없이 사용가능하다
* Scanner의 버퍼 사이즈는 1024 chars 이고 BufferedReader의 버퍼 사이즈는 8192 chars 이다 => 많은 입력이 있는 경우 BufferedReader가 성능상 우위를 갖는다

- - - -

### 틀린 이유
* 사용상 편의와 많은 입력이 많지않아서 InputStream이나 BufferedReader를 사용하지 않고 Scanner를 사용.
* Scanner를 사용하고 close()를 해주지 않아 런타임 에러가 발생
	* => Scanner를 close 해주게 되면 메모리 사용량이 줄어들게 된다





