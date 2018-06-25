# JavaScript 연산자
## + 연산자
* 두 연산자가 모두 숫자일 경우 더하기 연산 수행
* 숫자가 아닐경우 문자열 연결 연산을 수행
``` javascript
var add1 = 1+2;
var add2 = 1+'a';

console.log(add1);		// 3
console.log(add2);		// 1a
```

	---

## typeof 연산자
* 피연산자의 타입을 문자열 형태로 리턴

> null과 배열은 object 타입  
> 함수는 function 타입  

|                |    타입    | typeof 결과 |
|:—————-:|:—————-:|:————————:|
| 기본타입 |    숫자    | ‘number’ |
| 기본타입 |  문자열   |    ‘string’  |
| 기본타입 |  불린값   |    ‘boolean’  |
| 기본타입 |    **null**     |    **‘object’**  |
| 기본타입 |  undefined   |    ‘undefined’  |
| 참조타입 |  객체   |    ‘object’  |
| 참조타입 |  **배열**   |    **‘object’**  |
| 참조타입 |  **함수**   |    **‘function’**  |


	---

## 동등(==) 연산자 & 일치(===) 연산자
* 값이 동일한지 확인하는 연산자

### 동등연산자
* 비교하려는 피연산자의 타입이 다를 경우 타입 변환을 거친 다음 비교

### 일치연산자
* 피연산자의 타입이 다를 경우 타입을 변경하지 않고 비교

``` javascript
console.log(1 == '1');		// true
console.log(1 === '1');		// false
```

> 동등(==)연산자의 경우 타입변환에 따른 잘못된 결과를 얻을 수 있으므로 동등연산자 사용을 추천하지 않는다  
> 가급적이면 일치(===)연산자를 사용해서 비교!  

	---


## !! 연산자
* 피연산자를 불린값으로 변환하는 것

``` javascript
console.log(!!0);					// false
console.log(!!1);					// true
console.log(!!'string');			// true
console.log(!!'');				// false
console.log(!!true);				// true
console.log(!!false);				// false
console.log(!!null);				// false
console.log(!!undefined);			// false
console.log(!!{});				// true
console.log(!![1, 2, 3]);			// true
```

