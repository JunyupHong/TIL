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
	return x + y + z
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


## 파라미터
### Optional & Default 파라미터
* TypeScript에서는 모든 파라미터가 함수에 필요하다고 가정
	* => 컴파일러는 함수가 호출될때 사용자가 파라미터에 값을 입력했는지 확인한다는 의미
* 컴파일러는 파라미터들이 함수에만 전달되는 파라미터라고 가정
	* => 함수에 주어진 파라미터의 수는 함수가 예상하는 파라미터의 수와 일치해야한다

#### optional 파라미터
* JavaScript에서는 파라미터가 선택사항 => 파라미터가 맞지않으면 undefined로 설정됨
* TypeScript에서는 optional 파라미터를 ‘?’ 로 표시
* 모든 optional 파라미터는 required 파라미터 다음에 나와야한다
``` typescript
function buildName(firstName: string, lastName?: string) {
	if (lastName)
		return firstName + " " + lastName
	else
		return firstName;
}

// 두번째 파라미터는 option
let result1 = buildName("Bob")
let result2 = buildName("Bob", "Adams")

 // error 파라미터 갯수 초과
let result3 = buildName("Bob", "Adams", "Sr.")
```

#### default 파라미터
* 파라미터를 입력하지 않거나 파라미터 값 대신 undefined를 넣어도 파라미터가 할당될 값을 default 값으로 설정할 수 있다
* == default-initialized 파라미터
``` typescript
function buildName(firstName: string, lastName = "Smith") {
	return firstName + " " + lastName
}

let result1 = buildName("Bob")	// Bob Smith
let result2 = buildName("Bob", undefined) // Bob Smith
let result3 = buildName("Bob", "Adams") // Bob Adams

// error 파라미터 갯수 초과
let result4 = buildName("Bob", "Adams", "Sr.")
```

* required 파라미터 다음에 오는 default-initialized 파라미터는 optional 파라미터 처럼 취급
	* => optional 파라미터처럼 해당함수를 호출 할 때 생략 가능
```typescript
// optional parameter
function buildName(firstName: string, lastName?: string) {
	// ...
}

// default-initialized parameter
function buildName(firstName: string, lastName = "Smith") {
	// ...
}
```

* optional 파라미터와 달리 default-initialized 파라미터는 required 파라미터 다음에 올 필요가 없다
* Required 파라미터 앞에 오는 경우 명시적으로 undefined를 전달해야함
``` typescript
function buildName(firstName = "Will", lastName: string) {
	return firstName + " " + lastName
}
let result1 = buildName("Bob") // error lastName 파라미터가 없다
let result2 = buildName("Bob", "Adams", "Sr.") //error 파라미터 갯수 초과

let result3 = buildName("Bob", "Adams") // Bob Adams
let result4 = buildName(undefined, "Adams") // Will Adams
```


### rest 파라미터
* required 파라미터, optional 파라미터, default-initialized 파라미터는 한번에 하나의 파리미터에 대한 지정
* 여러 파리미터를 그룹으로 사용하거나 함수가 궁극적으로 취할 파라미터를 모를때 rest 파라미터(‘…’)를 사용

* JavaScript에서는 arguments 변수를 사용하여 파라미터를 직접 사용
* TypeScript에서는 rest 파라미터를 사용해서 변수로 대입
``` typescript
function buildName(firstName: string, ...restOfName: string[]) {
	return firstName + " " + restOfName.join(" ")
}
let employeeName = buildName("Joseph", "Samuel", "Lucas", "MacKinzie");
```

* 함수 타입에서 rest파라미터 사용
``` typescript
function buildName(firstName: string, ...restOfName: string[]) {
	return firstName + " " + restOfName.join(" ")
}
let buildNameFun: (fname: string, ...rest: string[]) => string = buildName;

```










