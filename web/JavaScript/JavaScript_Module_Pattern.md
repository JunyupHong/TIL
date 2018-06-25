# JavaScript Module Pattern
## Module
* 독립적이고 재사용 가능한 코드의 작은 단위
* 모듈은 자바스크립트 디자인패턴의 토대가 된다
* 자바스크립트 모듈은 type을 정의하는 대신 값을 내보낸다
* 객체 리터럴, 함수 또는 생성자를 내보냄

## Module Pattern
* 일반적으로 모듈 패턴은 은 원래 기존의 소프트웨어 엔지니어링에서 클래스에 대한 private 과 public한 캡슐화를 제공하는 방법으로 정의

* 자바스크립트에서의 모듈 패턴은 전역 영역(global scope)에서 특정 변수 영역(local scope)을 보호하기 위해 단일 객체 안의 public, private 변수/메서드를 포함할 수 있게 한다
* 이런 특성의 결과로 동일 페이지 내에서도 함수이름이나 변수가 출동하는 것을 예방할 수 있다.
``` javascript
// 동일한 이름이지만 다른 영역에 존재하는 메서드이므로
// 이름 충돌이 발생하지 않는다
first.method();
second.method();
```


* 클로저를 사용해서 상태와 구조를 캡슐화시켜 private 하게 만들 수 있다
* private 과 public한 메서드나 변수를 함께 사용하도록 wrapping 할 수 있으면 이를 통해 글로벌 스코프로 빠져나가는것을 막을 수 있다.
* 또한 다른 개발자의 인터페이스와 충돌하는 것을 예방 할 수도 있다.
* 이 패턴에서는 오직 public API의 경우에만 리턴 되고 나머지는 모두 클로저 안에서 private하게 위치한다

* 자바스크립트의 언어 자체에는 전통적인 다른 프로그래밍언어와는 다르게 access modifiers(접속 제한자)가 없다.
따라서 변수는 public, private으로 선언하는 것이 불가능하기 때문에 함수의 스코프 개념을 통해서 이러한 특징을 흉내를 내는 것이다


### 예제

* incrementCounter() , resetCounter() 메서드에 직접 접근하는 것이 불가능
* counter 변수는 외부에서 접근할 수 없다(private)
* 내부의 모든 리소스는 해당 모듈의 이름 'testModule' 을 통해서만 접근이 가능
``` javascript
var testModule = (function () {
	var counter = 0;
	return {
		incrementCounter: function () {
			return counter++;
		},
		resetCounter: function () {
			console.log( "counter value prior to reset: " + counter );
			counter = 0;
		}
	};
})();
testModule.incrementCounter(); testModule.resetCounter();
```


* 함수의 scope를 이용해서 private, public 변수/메소드를 만드는 방법
``` javascript
var myNamespace = (function () {
	var myPrivateVar, myPrivateMethod;
	// private 변수
	myPrivateVar = 0;
	// private 함수
	myPrivateMethod = function( foo ){
		console.log(foo);
	};

	return {
		// public 변수
		myPublicVar: "foo",
		// public 힘수
		myPublicFunction: function(bar) {
			myPrivateVar++;
			myPrivateMethod(bar);
		}
	};
})();
```




