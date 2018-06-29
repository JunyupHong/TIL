# JavaScript 함수

## 함수 생성 방법
* 함수 선언문
* 함수 표현식
* Function() 생성자 함수

### 함수 리터럴
* 함수도 일반 객체처럼 값으로 취급
	따라서 함수 리터럴을 이용해 함수를 생성할 수 있다

* function키워드: 함수 리터럴은 function 키워드로 시작
* 함수명 : 함수 몸체의 내부 코드에서 자신을 재귀적으로 호출, 디버거가 해당 함수를 구분하는 식별자로 사용
	(함수명은 선택 사항이다. 함수명이 없는 함수를 익명함수라고 한다)
* 매개변수 리스트: 다른 언어의 매개변수 형태와 비슷하지만 매개변수 타입은 정의하지 않는다
* 함수 몸체 : 함수가 호출됐을 때 실행되는 코드




### 함수 선언문 (function statement)
* 함수 선언문은 함수 리터럴 형태와 같다.
* 하지만 반드시 함수명이 정의되어 있어야한다
``` javascript
// add 함수
function add(x, y) {
	return x + y;
}							// 함수 선언문은 세미콜론을 붙이지 않음
```

### 함수 표현식	 (function expression)
* 함수도 하나의 값처럼 취급된다 (함수는 일급객체)
* 따라서 함수도 숫자나 문자열 처럼 변수에 할당하는 것이 가능하다.

#### 익명함수 표현식 & 기명함수 표현식
##### 익명함수 표현식 
* 익명함수를 이용한 함수 표현식
``` javascript
// 익명함수 표현식
var add = function(x, y) {
	return x + y;
};									// 함수 표현식은 세미콜론을 붙임

var plus = add;					// plus에 add할당 가능
```
> add 변수는 함수 리터럴로 생성한 함수를 참조하는 변수 (함수 변수)  
> add와 plus 함수 변수는 동일한 익명 함수를 참조한다  

##### 기명함수 표현식
* 함수 이름이 포함된 함수 표현식
* 함수 표현식에서 사용된 함수 이름은 외부 코드에서 접근 불가능
``` javascript
// 기명함수 표현식
var add = function sum(x, y) {		
	return x + y;
};									// 함수 표현식은 세미콜론을 붙임

console.log(add(1, 2));			// 3
console.log(sum(1, 2));			// error (sum is not defined)
				// 함수표현식에서 사용된 함수 이름은 외부 코드에서 접근 불가능
```

> < 함수 선언문에서 함수명이 외부코드에서 접근이 가능한 것은 자바스크립트 엔진에 의해 함수 표현식 형태로 변경되기 때문 >  
``` javascript
function add(x, y) {
	return x + y;
}

// 위 코드는 자바스크립트 엔진에 의해 아래 코드로 변경

var add = function add(x, y) {
	return x + y;
};

// 따라서 외부 코드에서 함수이름에 접근하는 것이 아닌 
// add 함수변수로 add함수에 접근하는 것
console.log(add(1, 2));		// 3
```



### 함수 선언문과 함수 표현식에서의 세미콜론
* 일반적으로 자바스크립트 코드를 작성할 때 **함수 선언문 방식**으로 선언된 함수의 경우는 함수 끝에 _세미콜론을 붙이지 않지만_ **함수 표현식 방식**의 경우에는 _세미콜론을 붙이는 것을 권장_ 한다.

* 아래 코드에서는 함수표현식에 세미콜론을 붙이지 않았다.
이때 자바스크립트는 중괄호 끝에 함수가 끝났다고 판단하지 않는다
따라서 뒤의 즉시 실행함수의 괄호를 보고 func()함수로 판단해 func() 함수를 실행한다
그 결과 42가 리턴되고 그 맨 마지막 괄호가 있으므로 42(); 함수를 호출 하려고 한다
그러나 42는 함수가 아니므로 ‘number is not a function’ 에러가 나게된다
``` javascript
var func = function() {
	return 42;				// 함수 표현식에 세미콜론을 붙이지 않으면
} 							//(function() {				// 따라서 뒤의 ()를 보고 
	console.log('function called');
})();
```


### Function() 생성자 함수
* 자바스크립트의 함수도 Function()이라는 기본 내장 생성자 함수로부터 생성된 객체라고 볼 수 있다
* 함수 선언문, 함수 표현식 방식도 Function() 생성자 함수가 아닌 함수 리터럴 방식으로 생성하지만 내부적으로는 Function() 생성자 함수로 함수가 생성된다

* 일반적으로 잘 사용되지 않는 방법
``` javascript
var add = new Function('x', 'y', 'return x + y');
console.log(add(1, 2));			// 3
```


- - - -

## 함수 호이스팅
* 함수 선언문 형태로 정의한 함수의 유효 범위는 코드의 맨 처음부터 시작한다(함수 호이스팅의 정의)
* 함수 호이스팅이 발생하는 원인은 자바스크립트의 **변수 생성, 초기화의 작업이 분리돼서 진행**되기 때문!

* 함수를 생성하는 3가지 방법은 동작 방식에 약간의 차이가 있다
* 그 중 하나가 호이스팅
* 더글라스 크락포드는 함수 생성에 있어서 함수 표현식만을 사용할 것을 권고(함수 호이스팅 때문)

``` javascript
// 함수 호이스팅
add(1, 2);				// 3 (함수 호이스팅에 의해 에러가 나지 않는다)

function(x, y) {			// 함수 선언식
	return x + y;
}

add(2, 3);				// 5
```

``` javascript
// 함수 호이스팅이 발생하지 않음
add(1, 2);						// uncaught type error

var add = function(x, y) {		// 함수 표현식
	return x + y;
}

add(2, 3);						// 5
```


- - - -

## 함수 객체

### 함수는 객체이다
* 함수는 특정 기능의 코드를 수행할 뿐만 아니라 일반 객체처럼 자신의 프로퍼티를 가질 수 있다
* 함수를 생성할 때 함수 코드는 함수 객체의 `[[Code]] 내부 프로퍼티`에 저장된다
``` javascript
function add(x, y) {
	return x + y;			// 함수 코드([[Code]] 프로퍼티에 저장됨)
}

add.result = add(2, 3);		// result 프로퍼티 추가
add.status = 'OK';			// status 프로퍼티 추가

console.log(add.result);		// 5
console.log(add.status);		// 'OK'
```

	---

### 함수는 값으로 취급된다
* 함수는 일반 객체처럼 취급될 수 있다.
	* 리터럴에 의해 의해 생성
	* 변수, 배열의 요소, 객체의 프로퍼티 등에 할당 가능
	* 함수의 인자로 전달 가능
	* 함수의 리턴값으로 리턴 가능
	* 동적으로 프로퍼티를 생성 및 할당 가능

* 따라서 자바스크립트의 함수는 **일급 객체** 라고 부른다
> 일급객체:  
> 	위의 5가지 기능이 모두 가능한 객체  



#### 변수나 프로퍼티의 값으로 할당
``` javascript
// 변수에 함수 할당
var hundred = function() {		// hundred변수에 익명함수를 할당
	return 100;
};

// 프로퍼티에 함수 할당
var obj = {};
obj.baz = function() {
	return 200;
};
```



#### 함수 인자로 전달
``` javascript
var foo = function(func){
	func();
};

foo(function(){					// 익명 함수를 인자로 전달
	console.log('here is function');
});								// 출력: here is function
```


#### 리턴값으로 활용
``` javascript
var foo = function() {
	return function() {					// 익명함수를 리턴
		console.log('function is return');
	}
};

var bar = foo();				// 익명함수가 bar에 저장
bar();						// function is return
```

	---

### 함수 객체의 기본 프로퍼티
* `name` : 함수의 이름을 나타냄(익명함수일 경우 빈 문자열)
* `caller` : 자신을 호출한 함수를 나타냄(호출한 함수가 없으면 null)
* `arguments` : 함수를 호출할 때 전달된 인자 값을 나타냄(호출된 상태가 아니면 null)
* `__proto__` : `[[Prototype]]`이라는 내부 프로퍼티를 크롬에서 구현 (자신의 부모 역할을 하는 프로토타입 객체를 가리킨다)
> 함수 객체의 부모 역할을 하는 프로토타입 객체를 Function.prototype 객체 라고 한다  
> 크롬에서는 ‘Empty()’ 함수가 Function.prototype 객체이다  
> Function.prototype 함수 객체의 부모는 Object.prototype 객체이다  
> Function.prototype 함수 객체는 constructor 프로퍼티, toString(), apply(), call,() bind() 메소드를 갖는다  

#### length 프로퍼티
* 모든 함수가 가져야 하는 표준 프로퍼티
* 함수가 정상적으로 실행될 때 기대되는 인자의 개수를 나타낸다
``` javascript
function func0(){}
function func1(x){ return x }
function func2(x, y){ return x + y; }

console.log(func0.length);			// 0
console.log(func1.length);			// 1
console.log(func2.length);			// 2
```

#### prototype 프로퍼티
* 모든 함수는 객체로서 prototype 프로퍼티를 가지고 있다.
* 함수 객체의 prototype 프로퍼티는 모든 객체의 부모를 나타내는 내부 프로퍼티(`[[Prototype]]프로퍼티`)와 다르다!
> `prototype프로퍼티와  [[Prototype]] 프로퍼티`  
> 두 프로퍼티 모두 프로토타입 객체를 가리킨다  
>   
> `[[Prototype]] 프로퍼티`는 객체 입장에서 **자신의 부모 역할을 하는 프로토타입 객체**를 가리키는 반면,  
> `prototype 프로퍼티`는 함수가 생성자로 사용될 때 **이 함수를 통해 생성된 객체의 부모 역할을 하는 프로토타입 객체**를 가리킨다  

* prototype 프로퍼티는 함수가 생성될 때 만들어 지며, constructor프로퍼티 하나만 있는 객체를 가리킨다. 또한 constructor프로퍼티는 자신과 연결된 함수를 가리킨다.
* 즉 자바스크립트에서는 함수를 생성할 때, 자신과 연결된 프로토타입 객체를 동시에 생성하며 각각 prototype과 constructor라는 프로퍼티를 서로 참조한다

> 프로토타입 객체는 일반적으로 따로 네이밍하지 않고, 자신과 연결된 함수의 prototype 프로퍼티 값을 그대로 이용한다.  
> ex) add()함수의 프로토타입 객체는 add.prototype이 된다  

``` javascript
function myFunction(){
	return true;
}

console.dir(myFunction.prototype);
			// myFunction의 프로토타입 객체
			// constructor 프로퍼티를 가지고있다
console.dir(myFunction.prototype.constructor);
			// 프로토타입 객체와 매핑된 함수를 볼 수 있다
			// == myFunction()
```



- - - -


## 함수의 다양한 형태
	* 콜백함수
	* 즉시 실행함수
	* 내부 함수
	* 함수를 리턴하는 함수

### 콜백함수
* 익명함수의 대표적인 용도
* 코드를 통해 명시적으로 호출하는 함수가 아니라 함수를 등록 하기만하고 이벤트가 발생했거나 특정 시점에 도달했을때 시스템에서 호출되는 함수
* 특정 함수의 인자로 넘겨서 코드 내부에서 호출되는 함수
``` javascript
document.on('click', function(){		// 클릭되었을때 실행되는 콜백함수
	alert('callback function');
});
```

	---

### 즉시 실행 함수
* 함수가 선언 되자마자 실행되게 만든 함수
* 최초 한 번의 실행만을 필요로하는 초기화 코드 등에 사용할 수 있다.
* 함수 리터럴을 괄호로 싸고 함수를 호출 하기위해 ()를 붙여준다
``` javascript
(function(){
	console.log('즉시 실행 함수');
})();
```

> jQuery의 전체 코드를 즉시 실행함수로 만들어 사용하면  함수 유효범위를 제한할 수 있다.  
> 기본적으로 자바스크립트는 함수 유효 범위(function scope)를 지원한다.  
> 함수 내부에서 정의된 매개변수와 변수들은 함수 코드 내부에서만 유효할 뿐 함수 밖에서는 유효하지 않다. (var문으로 변수를 정의했을 경우) (var문 없이 변수를 정의하면 호이스팅(hoisting)에 의해 전역에서 접근이 가능하다..)  
``` javascript
(function() {
  for(var i = 0; i < 10; i++) {		// i는 함수 내부에서만 접근 가능
    console.log('i', i);
  }
})();
console.log('after loop i is', i); // error (i is undefined)


(function() {
  for(j = 0; j < 10; j++) {		// j가 호이스팅 된다..
    console.log('j', j);			// 즉, 전역scope에 var=j;가 생성
  }
})();
console.log('after loop j is', j); // 10 (에러가 나지 않는다)
```

> 라이브러리 코드를 즉시 실행 함수 내부에 정의해두게 되면, 라이브러리 내의 변수들은 함수 외부에서 접근할 수 없다.  
> 따라서 전역 네임스페이스를 더럽히지 않고, 라이브러리 간 변수 이름 충돌 문제를 방지 할 수 있다.  

* 라이브러리 코드가 처음 로드외어 초기화 할때, 즉시 실행 함수 패턴이 많이 사용된다!

	---

### 내부 함수
* 함수 내부에 정의된 함수
* 클로저를 생성하거나 부모 함수 코드에서 외부에서의 접근을 막고 독립적인 헬퍼 함수를 구현하는 용도 등으로 사용

* 내부 함수에서는 자신의 둘러싼 부모 함수의 변수에 접근이 가능하다 (스코프 체이닝)
* 내부 함수는 일반적으로 자신이 정의된 부모 함수 내부에서만 호출이 가능하다
``` javascript
function parent() {
	var a = 100;
	var b = 200;
	function child() {
		var b = 300;
		console.log(a);				// 100	(부모의 변수 a에 접근 가능)
		console.log(b);				// 300	(자신의 변수 b)
	}
	child();			// 부모 함수 안에서 내부함수 호출 가능
}

parent();
child();				// error (내부 함수는 부모의 내부에서만 호출 가능)
```

* 내부 함수를 외부로 리턴하면  부모 함수 밖에서도 내부 함수를 호출하는 것이 가능하다.
``` javascript
function parent() {
	var a = 100;
	var child = function() {		// 내부함수
		console.log(a);
	}
	return child;
}

var inner = parent();
inner();					// 100 (내부함수인 child를 호출)
```
> 실행이 끝난 parent()와 같은 부모 함수 스코프의 변수를 참조하는 inner()와 같은 함수를 클로저 라고 한다  

	---

### 함수를 리턴하는 함수
* 함수는 일급 객체이므로 일반 값처럼 함수 자체를 리턴할 수 있다.
* 함수를 호출함과 동시에 다른 함수로 바꾸거나, 자기 자신을 재정의하는 함수를 구현할 수 있다
``` javascript
var self = function() {
	console.log('a');
	return function() {
		console.log('b');
	}
}

self = self();		// a
self();				// b
```





