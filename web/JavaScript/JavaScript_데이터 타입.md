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

### 특징
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

	---

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
str.indexOf(0);		// -1(하위 문자열이 없으면 -1 출력)

// 문자열 내부의 하위 문자열 추출 
str.slice(2);					// cDE
str.slice(2, 3);				// cD

// 대소문자 변경
str.toLowerCase();			// abcde
str.toUpperCase();			// ABCDE

// 문자열 일부를 변경
str.replace('ABC', 'AAA');	// AAADE
```

	---

### boolean
* true, false 값을 나타내는 타입

	---

### null & undefined
* ‘값이 비어있음’ 을 나타냄
#### null
*  개발자가 명시적으로 값이 비어있음을 나타내는데 사용
* null 타입 변수의 타입은 Object
``` javascript
var nullVar = null;
console.log(typeof(nullVar));	// object
```
#### undefined
* 기본적으로 값이 할당되지 않은 변수 = undefined 타입
`var undefinedVar;`










