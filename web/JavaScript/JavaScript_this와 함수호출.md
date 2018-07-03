# JavaScript this와 함수호출
* 자바스크립트에서는 함수를 호출할 때 **매개변수로 전달되는 인자값**과 **arguments 객체** 와 **this 인자**가 함수 내부로 암묵적으로 전달된다.

## arguments 객체
* 런타임시에 호출된 인자의 개수를 확인하고 이에 따라 동작을 다르게 해야할 때 사용
* 자바스크립트에서는 함수를 호출할 때 인자들과 함께 암묵적으로 arguments 객체가 함수 내부로 전달된다
* arguments 객체는 유사 배열 객체!

* 자바스크립트에서는 함수를 호출할 때 함수 형식에 맞춰 인자를 넘기지 않더라도 에러가 발생하지 않는다
	* 정의된 함수의 인자보다 적게 함수를 호출했을경우 undefined 값이 할당된다
	* 인자 객수보다 많게 함수를 호출했을 경우 초과된 인수는 무시한다
``` javascript
function func(arg1, arg2) {
	console.log(arg1, arg2);
}

func();						// undefined, undefined
func(1);						// 1, undefined
func(1, 2);					// 1, 2
func(1, 2, 3);				// 1, 2
```


### argument 객체 구성
* 함수를 호출할 때 넘겨진 인자 (배열 형태로 저장)
	: 함수를 호출할 때 첫번째 인자는 0번 인덱스, 두번째 인자는 1번 인덱스 ...
* length 프로퍼티
	: 호출할 때 넘겨진 인자의 갯수
( 유사 배열 객체 -> 배열 메서드를 직접 사용할 수 없다 (apply()를 이용) )
* callee 프로퍼티
	: 현재 실행 중인 함수의 참조값	

``` javascript
// arguments 객체를 이용한 함수
function sum() {
	var result = 0;
	for(var i = 0; i < arguments.length; i++) {
		result += arguments[i];
	}
	return result;
}

console.log(1, 2);			// 3
console.log(1, 2, 3, 4, 5);	// 15
```

- - - -

## 호출 패턴과 this 바인딩
* 자바스크립트에서 함수를 호출할 때 this 인자가 암묵적으로 함수 내부로 전달된다.
* 자바스크립트의 여러 가지 함수가 **호출되는 방식(호출패턴)**에 따라 **this가 다른 객체를 참조(this 바인딩)**한다.

### 객체의 메서드를 호출할 때 this 바인딩
* 함수 내부에서 사용된 **this는 해당 메서드를 호출한 객체로 바인딩**한다.
``` javascript
var myObject = {
	name: 'foo',
	sayName: function(){			// sayName() 메서드
		console.log(this.name);		// 객체의 메서드에서의 this
	}
};
var otherObject = {
	name: 'bar'
};

otherObject.sayName = myObject.sayName;

// 객체의 메서드 호출 (this는 호출한 객체로 바인딩)
myObject.sayName();				// 'foo'
otherObject.sayName();			// 'bar'
```

	—

### 함수를 호출할 때 this 바인딩
* 자바스크립트에서 함수를 호출하면, 해당 함수 내부 코드에서 사용된 **this는 전역 객체에 바인딩**된다.
* 브라우저에서 실행할 경우 전역 객체는 window 객체가 된다.
> 전역객체?  
> 브라우저 환경에서 자바스크립트를 실행하는 경우, 전역객체는 window 객체가 된다.  
> Node.js와 같은 자바스크립트 언어를 통해 서버 프로그래밍을 할 수 있게끔 해주는 자바스크립트 런타임 환경에서의 전역 객체는 global 객체가 된다.  
``` javascript
// 전역변수와 전역객체의 관계
var foo = 'this is foo';		// 전역변수

console.log(foo);				// this is foo
console.log(window.foo);		// this is foo
								// (전역객체의 프로퍼티로 전역변수에 접근)
```

``` javascript
// 함수를 호출할 때 this 바인딩
var test = 'this is test';
console.log(window.test);			// this is test

var sayFoo = function() {
	console.log(this.test);			// 함수에서의 this
};

// 함수를 호출 (this는 전역 객체에 바인딩)
sayFoo();							// this is test
```

#### 내부함수를 호출할 경우
* 함수 호출에서의 this 바인딩 특성이 그대로 적용 (함수 내부 코드에서 사용된 this는 전역 객체에 바인딩)
	-> 자바스크립트에서는 내부 함수 호출 패턴을 정의해 놓지 않았기 때문!
``` javascript
var value = 100;

var myObject = {
	value : 0,
	func1: function() {
		this.value += 1;
		console.log('func1 : ', this.value);
		func2 : function() {					// inner function
			this.value += 1;
			console.log('func2', this.value);
			func3 : function() {				// inner function
				this.value += 1;
				console.log('func3', this.value);
			}
			func3();				// 내부함수 호출
		}
		func2();					// 내부함수 호출
	}
};

myObject.func1();		// 차례대로 func1, func2, func3 실행
						// func1 : 1	(호출한 객체에 바인딩)
						// func2 : 101	(전역 객체에 바인딩)
						// func2 : 102	(전역 객체에 바인딩)
```

* 이러한 문제를 해결하기 위해서는 **부모함수의 this를 내부 함수가 접근 가능한 다른 변수에 저장하는 방법** 이 있다. (that 변수를 사용)
``` javascript
var value = 100;

var myObject = {
	value : 0,
	func1: function() {
		var that = this;
		this.value += 1;
		console.log('func1 : ', this.value);
		func2 : function() {	// 내부함수에서 부모의 that변수에 접근
			that.value += 1;
			console.log('func2', this.value);
			func3 : function() {// 내부함수에서 부모의 that변수에 접근
				that.value += 1;
				console.log('func3', this.value);
			}
			func3();				// 내부함수 호출
		}
		func2();					// 내부함수 호출
	}
};

myObject.func1();		// 차례대로 func1, func2, func3 실행
			// func1 : 1	(호출한 객체에 바인딩)
			// func2 : 2	(that을 이용해 this에 바인딩 된 객체에 접근)
			// func2 : 2	(that을 이용해 this에 바인딩 된 객체에 접근)
```

> 자바스크립트에서는 이러한 this 바인딩의 한계를 극복하기 위해 call과 apply 메서드를 제공  
> jQuery, underscore.js 등과 같은 자바스크립트 라이브러리는 bind 메서드를 통해 사용자가 원하는 객체를 this에 바인딩 할 수 있는 기능을 제공  

	—

### 생성자 함수를 호출할 때 this 바인딩
* 기존 함수에 new 연산자를 붙여서 호출하면 해당 함수는 생성자 함수로 동작
* **특정 함수가 생성자 함수로 정의 되어있음을 알리려고 함수 이름의 첫 문자를 대문자로 쓰기**를 권고

#### 생성자 함수가 동작하는 방식
* new연산자로 자바스크립트 함수를 생성자로 호출하면

1. 빈 객체 생성 및 this 바인딩
	* 생성자 함수 코드가 실행되기 전 빈 객체 생성
	* 빈 객체는 this로 바인딩된다 (이후 생성자 함수 내부의 this는 빈 객체를 가리킨다)
	* 빈 객체는 자신의 부모인 프로토타입 객체와 연결되어 있다 (자바스크립트 객체의 특징)
	* 생성자 함수의 prototype 프로퍼티가 가리키는 객체를 자신의 프로토타입 객체로 설정

2. this를 이용한 프로퍼티 생성
	* 	함수 코드 내부에서 this를 사용해서 빈 객체에 동적으로 프로퍼티나 메서드를 생성 가능
3. 생성된 객체 리턴
	* 	리턴문이 없는 경우 this로 바인딩된 새로 생성한 객체가 리턴

``` javascript
var Person = function(name) {
							// 코드 실행 전 빈 객체 생성, this로 바인딩
	this.name = name;		// 동적으로 name 프로퍼티 생성
							// 코드 실행 후 this로 바인딩된 객체 리턴
};

var foo = new Person(foo);		// new를 이용해 생성자 함수로 호출
console.log(foo.name);			// foo


var foo2 = new Person(foo2);		// 생성자 함수 호출
console.log(foo2.name);			// foo2
```


> ※ 객체 리터럴 방식과 생성자 함수를 통한 객체 생성 방식의 차이  
> - 재생성:  
> 객체 리터럴 방식은 같은 형태의 객체를 재생성할 수 없다.  
> 생성자 함수를 이용하면 같은 형태의 객체를 재생성할 수 있다.  
> - 프로토타입 객체:  
> 객체 리터럴 방식으로 만든 객체는 프로토타입이 Object.prototype이고  
> 생성자 함수를 이용해 만든 객체는 프로토타입이 생성자함수.prototype 이다  
> (자바스크립트 객체 생성 규칙 때문!)  

> ※ 자바스크립트 객체 생성 규칙  
> 자바스크립트 객체는 자신을 생성한 생성자 함수의 prototype 프로퍼티가 가리키는 객체를 자신의 프로토타입 객체로 설정한다  
> 객체 리터럴의 객체 생성자 함수는 Object() 이고  
> 생성자 함수 방식은 생성자 함수 자체이다.  
``` javascript
// 객체 리터럴 방식으로 객체 생성
var foo = {					// 프로토타입: Object.prototype
	name: 'foo',
	age: 42,
	gender: 'man'
};

// 생성자함수
function Person(name, age, gender, position) {
	this.name = name;
	this.age = age;
	this.gender = gender;
};

// 생성자 함수를 이용해서 객체 생성
// 프로토타입 객체: Person.prototype
var person1 = new Person('bob', 42, 'man');
var person2 = new Person('tom', 24, 'woman');
```



> ※ 생성자 함수를 new를 붙이지 않고 호출할 경우  
> 생성자 함수를 new없이 호출하거나 일반 함수를 new를 붙여 호출하게 되면   
> 일반 함수 호출과 생성자 함수를 호출할 때 this 바인딩 방식이 다르기 때문에 코드에서 오류가 발생할 수 있다.  
``` javascript
function Person(name, age, gender, position) {
	this.name = name;
	this.age = age;
	this.gender = gender;
};
var person1 = Person('bob', 42, 'man');	// new 없이 생성자 호출

// this는 (일반)함수 호출이므로 전역객체에 this가 바인딩된다
// 따라서 window객체에 동적으로 프로퍼티가 생성된다.
console.log(person1);				// undefined
console.log(window.name);			// bob
console.log(window.age);			// 42
```

#### 강제로 인스턴스 생성하기
* 생성자 함수를 new 없이 호출하는 위험성을 방지하게 위해 사용하는 패턴
``` javascript
function A(arg) {
	if(!(this instanceof A))			// new를 붙이지 않았을 경우
		return new A(arg);			// new로 생성자함수를 실행해준다
	this.value = arg ? arg : 0;
}

// if(!(this instanceof arguemnts.callee)) 를
// 이용하면 특정 함수의 이름과 상관없이 패턴을 공통으로 사용하는 모듈을 작성할 수 있다
```
* A가 호출 될때 this가 A의 인스턴스인지를 확인한다
* this가 A의 인스턴스가 아니라면 new로 생성자함수를 호출하고 리턴하게 해준다
	따라서 생성자함수에 new를 사용하지않고도 전역객체에 접근하지않고 새 인스턴스를 생성할 수 있게 한다.


