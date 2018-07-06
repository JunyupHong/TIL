# JavaScript 데이터 타입

## JavaScript 데이터 타입
### 기본타입
* 숫자(Number)
* 문자열(String)
* 불린값(Boolean)
* undefined
* null

### 참조타입
* 객체(Object)
	* 배열(Array)
	* 함수(function)
	* 정규표현식

### JavaScript 데이터 타입의 특징
* 느슨한 타입 체크
* var, let, const 라는 키워드로 변수를 선언 (var는 잘 사용하지 않는다!)
	(변수는 대소문자를 구별)
* 따라서 자바스크립트는 변수에 어떤 형태의 데이터를 저장하느냐에 따라 해당 변수의 타입이 결정된다

- - - -

## 기본 타입
* 그 자체가 하나의 값을 나타냄
* Number, String, boolean, undefined, null

### 숫자
* 모든 숫자를 64비트 부동 소수점 형태로 저장
따라서 실수의 범위는 부등호로 구해야한다!
(0.1000000000034는 0.1000000001과 0.0999999999 사이에 있다)
> 부동소수점  
> 아주 작은 수와 아주 큰 수, 그리고 무한과 NaN을 표현하기 위해 도입된 타입  
> 실수를 표현할 때 소수점의 위치를 고정하지 않는 것  
> 부동 소수점 방식을 사용하면 고정 소수점 방식보다 훨씬 더 많은 범위까지 표현 가능  
> 하지만 부동 소수점 방식에 의한 실수의 표현은 항상 오차가 존재  

* 정수형이 따로 없고, 모든 숫자를 실수로 처리
```javascript
var num = 5/2;
console.log(num);					// 2.5가 출력!
console.log(Math.floor(num));		// 2
```


### 문자열

* 작은 따옴표나 큰 따옴표로 생성
``` javascript
var str = 'a';
var str = 'string';
```
* 한 번 생성된 문자열은 읽기만 가능 (수정 불가)
``` javascript
var str = 'test';
str[0] = 'a';
console.log(str);			// test가 출력!
```

#### 프로퍼티(prototype)
``` javascript
var str = 'abcDE';

// 문자열 길이
str.length();			// 5

// 문자열 내부의 하위 문자열 찾기
str.indexOf('cD');	// 2
str.indexOf(0);		// -1(하위 문자열이 없으면 -1 리턴)

// 문자열 내부의 하위 문자열 추출 
str.slice(2);					// cDE
str.slice(2, 3);				// cD

// 대소문자 변경
str.toLowerCase();			// abcde
str.toUpperCase();			// ABCDE

// 문자열 일부를 변경
str.replace('ABC', 'AAA');	// AAADE
```
	

### boolean
* true, false 값을 나타내는 타입


### null & undefined
* ‘값이 비어있음’ 을 나타냄
#### null
* 개발자가 명시적으로 값이 비어있음을 나타내는데 사용
* null 타입 변수의 타입은 Object
``` javascript
var nullVar = null;
console.log(typeof(nullVar));	// object
```
#### undefined
* 기본적으로 값이 할당되지 않은 변수는 undefined 타입이다
* undefined는 타입이자 값을 나타낸다
``` javascript
var undefinedVar;			// 값이 할당되지 않음 (undefined 타입)
```

- - - -

## 기본타입과 표준 메서드
* 자바스크립트는 숫자, 문자열, 불린 값에 대해 각 타입별로 호출 가능한 표준 메서드를 정의하고 있다
* 기본타입은 객체가 아니지만 메서드 호출이 가능
	=> (메서드 처리 순간에 객체로 변환된 다음 각 타입별 표준 메서드를 호출 하고 메서드 호출이 끝나면 다시 기본값으로 복귀)
``` javascript
// 숫자 메서드 호출
var num = 0.5;
console.log(num.toExponential(1));	// '5.0e-1'

// 문자열 메서드 호출
console.log("test".charAt(2));		// 's'
```

- - - -

## 참조 타입(객체 타입)
* 기본 타입을 제외한 모든 값은 객체다
* 자바스크립트의 객체는 단순히 ‘key : value’ 형태의 프로퍼티들을 저장하는 컨테이너
* 해시 자료구조와 상당히 유사
* 객체의 프로퍼티는 함수로 포함할 수 있다

### 객체 생성
1. Object() 생성자 함수 이용
```javascript
var foo = new Object();

// 객체 프로퍼티 동적 생성
foo.name = 'bob';
foo.age = 42;

console.log(typeof foo);			// object
console.log(foo);					// { name:'bob', age: 42 }
```

2. 객체 리터럴 방식 이용
``` javascript
var foo = {
	name : 'foo',
	age : 42
};

console.log(typeof foo);			// object
console.log(foo);					// { name:'bob', age: 42 }
```

3. 생성자 함수 이용
	* 함수를 통해서 객체 생성이 가능
``` javascript
var foo = new Function() {
	name : 'foo',
	age : 42;
};

console.log(typeof foo);			// object
console.log(foo);					// { name:'bob', age: 42 }
```
	


### 객체 프로퍼티 접근 & 사용

* [] 또는 . 을 사용해서 객체 프로퍼티에 접근 ( . 을 쓰는것을 더 선호)

* 대괄호만을 사용해야하는 경우
	* 접근하려는 프로퍼티가 표현식 일 때
	(ex ‘proterty-name’  => - 연산자가 있는 표현식)
	* 이때 대괄호를 사용하지 않고 마침표를 사용하면 NaN 값이 출력

	* 예약어 일 때 (키워드는 프로퍼티로 사용 불가)
	
> NaN(Not a Number)값  
> 수치 연산을 해서 정상적인 값을 얻지 못할 때 출력되는 값  
> ex) ‘a’ - 1  ,  ’string’ - ‘str’  ,  …  

> 키워드 & 예약어  
> 키워드는 제어문의 시작과 끝, 특정한 조작 목적 등으로 쓰임  
> 기능들이 정의되어 있기 때문에 식별자나 프로퍼티 이름으로 사용할 수 없다  
> 예약어는 아직 특별한 쓰임새는 없지만 미래에 키워드로 쓸 가능성이 있어서 예약해 둔 것  


* 프로퍼티 삭제
	* delete 연산자를 이용 (delete 연산자는 객체를 삭제할 수 없음)
	
``` javascript
// 객체 생성
var foo = {
	name : 'foo',
	major : 'computer'
};

// 객체 프로퍼티 접근
console.log(foo.name);			// foo
console.log(foo['name']);			// foo
console.log(foo.nickname);		// undefined

// 객체 프로퍼티 갱신
foo.major = 'engineering';	// foo의 major 프로퍼티를 바꿈

// 객체 프로퍼티 동적 생성
foo.age = 30;					// foo프로퍼티에 없던 age를 생성, 할당

// 대괄호만을 사용해야하는 경우
foo['foo-name'] = 'foo';

// 객체 프로퍼티 삭제
delete foo.age;				// foo에서 age 프로퍼티를 삭제
delete foo;					// foo객체는 삭제되지 않음
```


### 참조타입의 특성
* 기본 타입을 제외한 모든 값은 객체이다
* 객체는 자바스트립트에서 참조 타입이다
* 객체의 모든 연산이 실제 값이 아닌 참조값으로 처리되기 때문

#### 객체의 비교
* 동등 연산자(==)
객체의 프로퍼티 값이 아닌 참조값을 비교

#### 함수 호출 방식
* 기본타입: Call By Value
* 참조타입: Call By Reference
``` javascript
var a = 100;
var objA = { value : 100 };

function changeValue(num, obj) {
	num = 200;
	obj.value = 200;

	console.log(num, obj);
}

changeValue(a, objA);				// 200, { value : 200 }

console.log(a, objA);				// 100, { value : 200 }
```