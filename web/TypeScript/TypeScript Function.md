# TypeScript Function
* JavaScript의 응용 프로그램을 구성하는 기본 요소
* JavaScript에서 함수는 추상화 계층, 클래스, 접근제한, 모듈을 모방하는 방법
* TypeScript에서는 클래스, 네임스페이스, 모듈이 있어서 함수가 이런 기능을 하지 않지만 작업 수행 방법을 설명하는데 중요한 역할


## Function
* Named function (명명 함수)
* Anonymous function (익명 함수)
``` typescript
function namedFunc() {
	console.log('this is named function')
}

let anonymousFunc = function() {
	console.log('this is anonymous function')
}
```

* 함수 본문 외부의 변수를 참조할 수 있다
* => 변수를 capture한다
``` typescript
let z = 100;
function addToZ(x, y) {
	// 함수 외부의 변수 z를 참조한다
	return x + y + z;
}
```



## 함수타입
### 함수 작성
* 파라미터에 타입을 추가
* 리턴 타입을 추가 (return문을 통해 리턴 타입을 파악할 수 있으므로 생략 가능)
``` typescript
function add(x: number, y: number): number {
	return x + y
}
let myAdd = function(x: number, y: number): number {
	return x+y
}
```


### 함수 타입 작성
* 함수 타입은 파라미터와 리턴 타입으로 구성 (capture 된 변수는 타입에 반영되지 않음)
* 파라미터 타입을 지정 (괄호 내부)
* 리턴 타입을 지정 ( ‘=>’ 이후)
	* 리턴 타입이 없으면 void를 사용

``` typescript
let myAdd: (x: number, y: number) => number = function(x: number, y: number): number {
	return x+y
}

// 함수 타입의 파라미터 이름과 함수의 파라미터 이름이 달라도 된다
let myAdd2: (baseValue:number, increment:number) => number = function(x: number, y: number): number {
	return x + y
}
```


### 타입 추정
* 한쪽에 타입이 있고 다른 쪽에 타입이 없는 경우 TypeScript 컴파일러에서 타입을 알아낼 수 있다
* 이를 컨텍스트 타입 지정이라고 한다
* => 프로그램 입력을 위한 노력이 줄어든다
``` typescript
// myAdd has the full function type
let myAdd = function(x: number, y: number): number {
	return  x + y
}

// The parameters 'x' and 'y' have the type number
let myAdd2: (baseValue:number, increment:number) => number = function(x, y) {
	return x + y
}
```




















