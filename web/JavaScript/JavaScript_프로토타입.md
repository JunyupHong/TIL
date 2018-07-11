# JavaScript 프로토타입
* 자바스크립트의 모든 객체는 자신의 부모 역할을 하는 객체와 연결되어 있다
* 이러한 부모 객체를 프로토타입 객체(프로토타입) 이라고 한다
(객체지향의 상속과 유사)
* **자바스크립트에서 모든 객체는 자신을 생성한 생성자 함수의 prototype 프로퍼티가 가리키는 객체를 자신의 프로토타입 객체(부모 객체)로 취급한다!**


## [[Prototype]] 프로퍼티
* 모든 객체는 자신의 프로토타입을 가리키는 `[[prototype]]`이라는 숨겨진 프로퍼티를 가진다
* 크롬 브라우저에서는 `_proto_`가 숨겨진 프로퍼티를 의미

- - - -

## 함수의 prototype 프로퍼티
* 모든 함수는 객체로서 prototype 프로퍼티를 가지고 있다.
* 함수 객체의 prototype 프로퍼티는 모든 객체의 부모를 나타내는 내부 프로퍼티(`[[Prototype]]프로퍼티`)와 다르다!
> `prototype프로퍼티와  [[Prototype]] 프로퍼티`  
> => 두 프로퍼티 모두 프로토타입 객체를 가리킨다  
> `[[Prototype]] 프로퍼티`는 객체 입장에서 **자신의 부모 역할을 하는 프로토타입 객체**를 가리키는 반면,  
> `prototype 프로퍼티`는 함수가 _생성자로 사용될 때_ **이 함수를 통해 생성된 객체의 부모 역할을 하는 프로토타입 객체**를 가리킨다 (생성자로 생성된 인스턴스들이 이 prototype이 가리키는 프로토타입 객체를 `[[prototype]]프로퍼티(부모역할을 하는 프로토타입 객체)`로 가리킨다)  


* prototype 프로퍼티는 함수가 생성될 때 만들어 지며, constructor프로퍼티 하나만 있는 객체를 가리킨다. 또한 constructor프로퍼티는 자신과 연결된 함수를 가리킨다.
* 즉 자바스크립트에서는 함수를 생성할 때, 자신과 연결된 프로토타입 객체를 동시에 생성하며 각각 prototype과 constructor라는 프로퍼티를 서로 참조한다


- - - -

## 프로토타입 체이닝
* **모든 객체의 프로토타입은 자바스크립트의 룰에 따라 객체를 생성할 때 결정된다**
* 객체는 자기 자신의 프로퍼티뿐만이 아니라, 자신의 부모 역할을 하는 프로토타입 객체의 프로퍼티 또한 자신의 것처럼 접근하는게 가능하다 => 이것을 가능하게 하는 것이 프로토타입 체이닝!
### 프로토타입의 두가지 의미
* 함수 객체의 `prototype프로퍼티`와 객체의 숨은 프로퍼티인 `[[prototype]]프로퍼티` 
	* 자바스크립트에서는 모든 객체는 자신을 생성한 생성자 함수의 prototype프로퍼티가 가리키는 프로토타입 객체를 자신의 부모 객체로 설정하는 `[[prototype]]링크`로 연결한다



### 객체 리터럴 방식으로 생성된 객체의 프로토타입 체이닝
* 특정 객체의 프로퍼티나 메서드에 접근하려고 할 때, 해당 객체에 접근하려는 프로퍼티나 메서드가 없는 경우 `[[prototype]]링크`를 따라 자신의 부모 역할을 하는 프로토타입 객체의 프로퍼티를 차례대로 검색하는 것을 프로토타입 체이닝이라고 한다
``` javascript
var myObject() {
	name : 'foo',
	sayname : function() {
		console.log('my name is ' +this.name);
	}
};

myObject.sayName();			// my name is foo
console.log(myObject.hasOwnProperty('name'));		// true
console.log(myObject.hasOwnProperty('nickName'));	// false
			// myObject는 hasOwnProperty 메서드가 없음에도 호출되었다
			// (프로토타입 체이닝)
console.log(myObject.sayNickName());			// error
			// myObject에 sayNickName()이 없어서 프로토타입 체이닝이 발생
			// 하지만 Object.prototype에도 메서드가 없으므로 에러가 난다
```
* 객체 리터럴로 생성한 객체는 Object() 라는 내장 생성자 함수로 생성된 것이다
* 따라서 리터럴 방식으로 생성된 객체는 Object.prototype 객체가 프로토타입 객체(부모)가 된다
* Object() 생성자 함수의 `prototype 프로퍼티`는 Object.prototype 객체를 가리키고(이때 Object.prototype의 constructor는 Object()생성자 함수를 가리킨다), Object() 생성자 함수로 생성된 myObject()는 `[[prototype]] 프로퍼티`의 `[[prototype]] 링크`로 Object.prototype을 참조하고있다
* 결과적으로 myObject()는 Object.prototype의 hasOwnProperty()메서드를 사용한다
``` javascript
var foo = {
	name: 'foo'
	age: 30;
};

console.log(foo.toString());
	// foo객체는 Object객체를 프로토타입으로 갖는다
	// 따라서 Object에 정의되어 있는 toString() 프로퍼티를 사용할 수 있다
```



### 생성자 함수로 생성된 객체의 프로토타입 체이닝
* 자바스크립트에서 모든 객체는 자신을 생성한 생성자 함수의 prototype 프로퍼티가 가리키는 객체를 자신의 프로토타입 객체(부모 객체)로 취급한다!
``` javascript
function Person(name, age, hobby){
	this.name = name;
	this.age = age;
	this.hobby = hobby;
}

var foo = new Person('foo', 42, 'tennis');
console.log(foo.hasOwnProperty('name'));		// true
				// 프로토타입 체이닝
				// foo의 부모객체 Person.prototype은
				// hasOwnProperty() 메서드를 가지고 있지 않다
				// 그러나 Person.prototype의 부모 객체인
				// Object.prototype은 hasOwnProperty()를 가지고있다
```

### 프로토타입 체이닝의 종점
* 프로토타입 체이닝의 종점은 **Object.prototype**이다
* 객체 리터럴이나 생성자 함수를 이용한 객체나 Obejct.prototype에서 프로토타입 체이닝이 끝난다

### 기본 데이터 타입 확장
* 숫자, 문자열, 배열 등에서 사용되는 표준 배열 메서드들의 경우 이들의 프로토타입인 **Number.prototype**, **String.prototype**, **Array.prototype** 등에 저장되어 있다
* 또한 이런 프로토타입 객체 또한 Object.prototype을 자신의 프로토타입 객체(부모객체)로 가지고 있다
``` javascript
String.prototype.testMethod = function() {
	console.log('this is testMethod');
};					// String.prototype에 testMethod()메서드 추가

var str = 'this is test';
str.testMethod();			// this is testMethod
```


### 프로토타입도 자바스크립트 객체다
* 함수가 생성될 때, 자신의 prototype 프로퍼티에 연결되는 프로토타입 객체는 default로 constructor 프로퍼티만을 가진 객체다
* 프로토타입 객체 역시 자바스크립트 객체이므로 일반객체처럼 동적으로 프로퍼티를 추가/삭제 하는 것이 가능하다
``` javascript
function Person(name) {
	this.name = name;
}

var foo = new Person('foo');

// 여기서 foo.sayHello(); 를 호출하면 에러가 난다

Person.prototype.sayHello = function() {
	console.log('hello');
};						// Person.prototype에 sayHello()메서드 추가

foo.sayHello();		// hello
```


### 프로토타입 메서드와 this 바인딩
* 프로토타입도 객체이므로 프로토타입 객체 메서드(= 프로토타입 메서드)도 객체의 메서드 이다
* 객체의 메서드를 호출할 때 this의 바인딩 규칙(this는 메서드를 호출한 객체에 바인딩된다)
``` javascript
function Person(name){
	this.name = name;
}

Person.prototype.getName = function() {
	return this.name;
};

var foo = new Person('foo');
console.log(foo.getName());			// foo

Person.prototype.name = 'person';
console.log(foo.getName());			// person
```


### 디폴트 프로토타입은 다른 객체로 변경이 가능하다
* 디폴트 프로토타입 객체(constructor만 존재)는 함수가 생성될 때 같이 생성되며, 함수의 prototype 프로퍼티에 연결된다
* 이 디폴트 프로토타입 객체를 다른 일반 객체로 변경하는 것이 가능
* 이런 특징을 이용해 객체지향의 상속을 구현
* 생성자 함수의 프로토타입 객체가 변경되면, 변경된 시점 이후에 생성된 객체는 변경된 프로토타입 객체로 `[[prototype]]링크`로 연결한다
* 그러나 변경된 시점 이전에 생성된 객체는 기존 프로토타입 객체로 `[[prototype]]링크`가 연결한다

``` javascript
function Person(name) {
	this.name = name;
}
console.log(Person.prototype.constructor);	// Person(name)

var foo = new Person('foo');
console.log(foo.country);						// undefined

// 디폴트 프로토타입 객체 변경
Person.prototype = {
	country : korea,
};
console.log(Person.prototype.constructor);	// Object()
			// Person.prototype 객체에는 constructor프로퍼티가 없다
			// 프로토타입 체이닝으로
			// Object.prototype 객체의 constructor를 호출

var bar = new Person('bar');
console.log(foo.country);				// undefined
console.log(bar.country);				// korea
console.log(foo.constructor);			// Person(name)
console.log(bar.constructor);			// Object()
```


### 객체의 프로퍼티 읽기나 메서드를 실행할 때만 프로토타입 체이닝이 동작한다
* 객체의 특정 프로퍼티를 읽으려고 할 때, 프로퍼티가 해당 객체에 없는 경우 프로토타입 체이닝이 발생한다
* 객체에 있는 특정 프로퍼티에 값을 쓰려고하면 프로토타입 체이닝이 일어나지 않고 프로퍼티 값을 변경하거나(객체에 프로퍼티가 있는경우), 프로퍼티를 동적으로 생성한다(객체에 프로퍼티가 없는 경우)
``` javascript
function Person(name) {
	this.name = name;
}
Person.prototype.country = 'korea';

var foo = new Person('foo');
vaer bar = new Person('bar');

// 프로토타입 체이닝
console.log(foo.country);			// korea
console.log(bar.country);			// korea

foo.country = 'USA';
console.log(foo.country);			// USA
							// foo의 country가 호출
console.log(bar.country);			// korea
							// 프로토타입 체이닝
```






