# TypeScript Generics
* 일관되고 잘 정의된 API를 보유할 뿐 아니라 재사용 가능한 구성 요소를 구축
* Generic을 이용

## Generic
### Generic을 사용하지 않을때
* 함수에 특정 타입을 부여해야한다
* any 타입은 argument가 모든 타입을 수용하게 하는 확실한 방법이지만, 실제로 함수가 리턴할 때 그 타입이 무엇인지에 대한 정보를 잃어버림
``` typescript
// number 타입 부여
function identity1(arg: number): number {
	return arg
}

// any 타입 부여
function identity2(arg: any): any {
	return arg
}
```

### Generic을 사용할 때
* Generic: 리턴되는 값을 나타내는 데 사용할 수 있는 방식으로 파라미터 타입을 capture한다
* Type 변수(<T>)를 사용
	* => 타입 변수는 값이 아닌 타입에서 작동하는 특별한 종류의 변수
	* 사용자가 제공한 타입을 capture하여 나중에 해당 정보를 사용할 수 있도록 한다
``` typescript
// Type 변수 사용 / 타입 부여
function identity<T>(arg: T): T {
	return arg
}
```


* generic 함수를 사용하는 2가지 방법
	* Type 파라미터를 포함한 모든 파라미터를 함수에 전달
	* 파라미터 타입 추론을 사용 (일반적인 방법) => 컴파일러가 전달하는 파라미터의 타입에 따라 자동으로 Type 변수의 값을 설정
``` typescript
// 아래 두가지 경우 모두 사용자가 제공한 string 타입을 Type 변수에 capture해서 사용

// Type 파라미터를 포함한 모든 파라미터를 전달
let output1 = identity<string>("myString")

// 파라미터 타입 추론
let output2 = identity("myString")
```


## Generic Type 변수로 작업
* Generic 함수를 만들면 컴파일러는 함수의 몸체에 일반적으로 타입이 지정된 파라미터를 올바르게 적용하도록 강제한다
* 실제로 파라미터 변수를 모든 타입이 될 수 있는 것처럼 취급한다
``` typescript
function loggingIdentity<T>(arg: T): T {
	console.log(arg.length)	// error: T는 .length를 가지고 있지 않다
	return arg
}
```
	* Type변수에 모든 타입이 사용될 수 있으므로 number 타입을 전달할 수 있지만, .length 프로퍼티는 가지고 있지 않다
	* => T 대신 T배열로 파라미터 타입을 정해주면 배열인것을 알고 있으므로 .length 프로퍼티를 가지고 있다
``` typescript
function loggingIdentity1<T>(arg: T[]): T[] {
	console.log(arg.length)	// Array는 .length를 가지고 있다
	return arg
}

// 또는
function loggingIdentity2<T>(arg: Array<T>): Array<T> {
	console.log(arg.length)
	return arg
}
```
	* 	Generic함수 loggingIdentity1, 2는 타입 파라미터 변수 T를 취하고 파라미터 arg는 T의 배열이며, T의 배열을 반환한다




















