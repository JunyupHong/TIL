# TypeScript의 Type

## TypeScript의 기본 유형
### boolean
``` typescript
let isDone: boolean = false;
```

### number
``` typescript
let myNum: number = 123;
```

### string
* 큰 따옴표, 작은 따옴표 둘 다 가능
* 템플릿 문자열 사용 가능
```typescript
let myStr1: string = "this is string";
let myStr2: string = 'this is string too';

// 템플릿 문자열
const age: number = 24;
let myStr3: string = `I'm ${ age } years old.
I'll be ${ age + 1 } years old next month.`;
```

### Array
* element type을 이용하여 그 element type의 배열을 나타냄
* generic Array type을 이용하여 나타냄
``` typescript
// element type을 이용하여 배열을 내타냄
let numArr2: number[] = [ 4, 5, 6 ];
let strArr1: string[] = [ "a", "b", "c" ];

// generic Array type 이용
let numArr1: Array<number> = [ 1, 2, 3 ];

// 다차원 배열
let arr: number[][] = [ 1, 2 ][ 3, 4 ];
```

### tuple
* 고정된 수의 요소를 알고 있지만 동일할 필요는 없는 배열을 표현할 수 있다
* type이 고정된 배열의 인덱스는 그 type만 들어가야 한다 => 그렇지 않으면 error
* tuple type 배열 중에서 type이 고정되지 않은 인덱스는 union type이 적용된다 (=> 고정된 type list 중 하나의 type이 들어가면 된다 )
``` typescript
// tuple type 변수 선언
let x: [string, number];

// 초기화
x = [ "hello", 100 ];
	// x = [ 100, "hello" ];	// => Error

console.log( x[0].substr[1] );		// e 출력
console.log( x[1].substr[1] );		// Error


// 고정되지 않은 인덱스 => union type 적용
	// => string | number이 들어가면 된다
x[3] = "world";
console.log(x[5].toString()); // string, number 둘다 toString()함수를 갖는다
// x[6] = true;	// => Error (string 또는 number가 아니다)
```

### enum
* 열거형
* 숫자 값 집합에 더 친숙한 이름을 지정하는 방법
* 자동으로 첫 element 부터 끝까지 순서대로 0번부터 번호를 매긴다
* element의 번호를 변경할 수 있다 (변경한 엘리먼트부터 번호가 자동으로 매겨진다)
``` typescript
// Red = 0, Green = 1, Blue = 2 로 적용된다
enum Color { Red, Green, Blue }
let c: Color = Color.Green;

// Red = 1, Green = 2, Blue = 10
enum Color2 { Red = 1, Green, Blue = 10}
let c2: Color2 = Color.Green;

let colorName: string = Color2[2];
console.log( colorName );		// Green 출력!
```

### any
* type check를 선택하지 않고 컴파일 타임 검사를 통과할 수 있다
* 변수에 어떠한 type이 들어가도 된다
``` typescript
let anyVal: any = 4;
anyVal = "anything";
anyVal = true;
anyVal = [ 1, 3 ];

let anyArr: any[] = [ 1, true, "string" ];
```
* Object와의 차이
	* type 변수 Object는 Object 값을 할당만 할 수 있다 => 실제로 존재하는 메소드 호출 불가
``` typescript
let anyVal: any = 1;
anyVal.ifItExists();
anyVal.toFixed();

let objVal: Object = 1;
objVal.toFixed();		// Error
```

### void
* any와 반대와 유사 => 어떤 타입도 존재하지 않음
* 함수의 리턴 타입이 없을때 주로 사용
* void type 변수를 선언하는 것은 유용하지 않다 => undefined나 null만 할당할 수 있기 때문
``` typescript
let voidFunc(): void {
	console.log("this is void function");
};

let voidVal: void = undefined;
voidVal = null;
```

### null & undefined
* null 과 undefined type은 각각 null, undefined의 값만 갖는다
* 이 type의 변수를 선언하는 것은 유용하지 않다
* 기본적으로 null 과 undefined는 다른 모든 type의 부속 type이다 => 다른 type에 null이나 undefined를 할당 할 수 있다
* 그러나 --strictNullChecks 플래그를 사용할 때 null과 undefined는 void나 해당 type에만 할당 할 수 있게 된다 => 일반적인 오류를 피할 수 있음 => union타입을 사용해서 null과 undefined 타입을 설정해주어야함
``` typescript
let u: undefined = undefined;
let n: null = null;

// 일반적인 상황
let str: string = null;		// not Error
str = undefined;				// not Error

// --strictNullChecks 일 때
let strInStrict: string | null | undefined = null;	// not Error
```

### never
* 결코 발생하지 않는 값의 type을 나타냄
* 항상 예외를 던지거나 리턴하지 않는 함수의 리턴 형식이다
* never type은 모든 타입의 하위 타입이며 모든 타입에 지정할 수 있다
* never type에 never를 제외한 어떠한 type도 할당할 수 없다
``` typescript
// never를 리턴하는 함수는 endpoint에 도달할 수 없어야 한다
function error(message: string): never {
	throw new Error(message);
}

// Inferred return type is never
function fail() {
	return error("Something failed");
}

// never를 리턴하는 함수는 endpoint에 도달할 수 없어야 한다
function infiniteLoop(): never {
	while (true) {
	}
}

```

### object
* 비 기본 유형을 나타내는 유형
* number, string, boolean, symbol, null, undefined가 아닌 모든 것
``` typescript
declare function create(o: object | null): void;

create({ prop: 0 }); // OK
create(null); // OK

create(42); // Error
create("string"); // Error
create(false); // Error
create(undefined); // Error
```


## Type assertions
* 컴파일러에게 ‘내가 하는일을 알고 있다’ 라고 말하는 방법
* 어떤 개체의 type이 현재 type보다 더 구체적임을 알 때 사용

* 다른 언어의 type cast와 유사하지만 특별한 검사나 데이터 재구성을 하지 않는다
* 런타임에 영향을 미치지 않으며 컴파일러에서만 사용된다
* Type assertion을 사용하면 프로그래머가 필요한 특별한 검사를 수행했다고 가정한다

### 유형
1. 꺽쇠 괄호
``` typescript
// someValue는 any type이다
let someValue: any = "this is a string";

// someValue가 any type이지만 이것이 구체적인 string type임을 알 수 있으므로 type assertion을 사용
let strLength: number = (<string>someValue).length;
```

2. as 구문
``` typescript
let someValue: any = "this is a string";

let strLength: number = (someValue as string).length;
```

* 두가지 유형은 동일하다 => 선호도의 차이
* TypeScript를 JSX와 함께 사용하는 경우 as 구문만 허용됨
 



