# TypeScript Interface
* TypeScript의 핵심 원리: type-checking이 값의 형태에 초점을 맞춘다
	* => duck typing 또는 structural subtyping 이라고도 부른다
	* 동적 타이핑의 한 종류로 객체의 변수 및 메소드의 집합이 객체의 타입을 결정하는 것 => 클래스 상속이나 인터페이스 구현으로 타입을 구분하는 대신, 덕 타이핑은 객체가 어떤 타입에 걸맞은 변수와 메소드를 지니면 객체를 해당 타입에 속하는 것으로 간주한다!
	* 예시(duck test): 만약 어떤 새가 오리처럼 걷고, 헤엄치고 꽥꽥거리는 소리를 내면 그 새는 오리라고 부른다

* TypeScript의 인터페이스는 이러한 타입의 이름을 지정하는 역할을 하며 외부 코드와 개발자의 코드의 약속을 정의하는 강력한 방법

## interface & Type-checker
### 함수의 Type-checker
* Type-checker는 labelledObj에 string 타입의 label이 있는지 확인한다
* 실제로 labelledObj가 더 많은 프로퍼티를 가지고 있을 수 있지만 컴파일러는 필요한 것들이 있는지와 그것들의 타입이 올바른지 만을 확인한다
``` typescript
function printLabel(labelledObj: { label: string }) {
	console.log(labeledObj.label);
}

const myObj = { size: 10, label: "Size 10 Object" };

printLabel(myObj);	// Size 10 Object
```

### 인터페이스
* argument가 다른 언어처럼 인터페이스를 구현한다고 명시적으로 말할 필요가 없다 => argument의 형태가 구현하려는 인터페이스의 형태와 같으면 된다
* Type-checker는 프로퍼티가 어떤 순서로든 상관하지 않으며 인터페이스에 필요한 프로퍼티가 있고 정확한 타입이 필요하다는 점을 지적한다는 것이 중요!
``` typescript
interface LabelledValue {
	label: string
}

function printLabel(labelledObj: LabbeledValue) {
	console.log(labelledObj.label);
}

const myObj = { size: 10, label: "Size 10 Object" }

printLabel(myObj)		// Size 10 Object 
```


## Optional Property
* 인터페이스의 모든 프로퍼티가 필수일 필요는 없다
* optional property는 프로퍼티 선언의 이름 끝에 ‘ ? ’를 사용한다
* optional property를 사용하면 인터페이스의 일부가 아닌 프로퍼티의 사용을 방지하고 사용 가능한 프로퍼티를 열거할 수 있다
* option bags 패턴 (두개의 프로퍼티가 있는 객체를 함수에 전달하는 패턴)을 사용할때 많이 사용
``` typescript
interface SquareConfig {
	color?: string
	width?: number
}

function createSquare(config: SquareConfig): {color: string; area: number} {
	let newSquare = {color: "white", area: 100};
	if (config.color) {
		newSquare.color = config.color;
	}
	if (config.width) {
		newSquare.area = config.width * config.width;
	}
	
	// Error!
	// config에는 height가 없으므로 컴파일타임에 에러가 발생한다
	// new Square.area = config.height * config.height;

	return newSquare;
}
```


## Readonly Property
* 일부 프로퍼티는 객체를 처음 만들때만 수정할 수 있어야한다
* 프로퍼티 이름 앞에 readonly를 두어 읽기전용으로 지정
``` typescript
interface Point {
	readonly x: number
	readonly y: number
}

let p1: Point { x: 10, y: 20 };

p1.x = 5		// error!
```

### ReadonlyArray<T>
* Array<T> 와 동일
* 수정가능한 메서드가 제거되어있다 => 배열 생성후 배열을 변경할 수 없다
* Type Assertion으로 Override는 가능

``` typescript
let arr: number[] = [1, 2, 3, 4];
let roArr: ReadonlyArray<number> = arr;
let roArr2: ReadonlyArray<number> = arr;

roArr[0] = 12; // error!
roArr.push(5); // error!
roArr.length = 100; // error!

// 일반 배열로 재할당 불가
arr = roArr; // error!
// readonlyArray<> 타입으로 재할당 가능
roArr = roArr2

// Type Assertion으로 Override
a = ro as number[];
```

### readonly vs const
* 변수는 const를 사용하고
* 프로퍼티는 readonly를 사용한다


## 프로퍼티 접근 체크
* Type-checker의  ‘argument의 형태가 구현하려는 인터페이스의 형태와 같으면 된다’ 는 것과 Optional property 두가지를 결합하면 아래의 코드는 컴파일타임에 에러가 발생하지 않는다
	* 전달인자 colord와 width는 선택 프로퍼티 이고
	* 실제 넘겨주는 인자는 colour와 width이므로 width는 호환가능한 속성, color은 없는 속성, colour는 여분의 colour 속성이 된다
	* => 따라서 문제가 발생하지 않아야한다
* 하지만 TypeScript는 코드에 버그가 있음을 알 수 있다
* => 객체 리터럴은 다른 변수에 할당하거나 파라미터로 전달될 때 특별한 처리를 받아 프로퍼티 접근 checking을 받는다
* 이때 객체 리터럴에 대상 타입에 없는 속성이 있으면 오류가 발생한다
``` typescript
interface SquareConfig {
	color?: string
	width?: number
}

function createSquare(config: SquareConfig): { color: string; width: number } {
	// ...
}

// error!
// colour는 SquareConfig interface에 정의되어있지 않다
const mySquare = createSquare({ colour: "red", width: 100 })
```

### 프로퍼티 접근 체크 오류를 피하는 방법
* Type Assertion 을 사용
``` typescript
const mySquare = createSquare({ colour: "red", width: 100 } as SquareConfig)
```

* Index signature 추가
	* 객체에 다른 용도로 사용되는 추가 속성이 있다고 확신하는 경우 string에 대한 Index signature를 추가하는 것이 더 나을 수도 있다
	* `[propName: string]: any` => 여러 속성을 가질 수 있으며 다른 프로퍼티들의 타입은 중요하지 않다
``` typescript
interface SquareConfig {
	color?: string
	width?: number
	[propName: string]: any
}
```

* 다른 변수에 객체를 할당해서 인자로 넘기는 방법
	* 초과된 프로퍼티를 check하지 않는다
	* 이 방법은 사용하지 않아야함!
	* 복잡한 객체 리터럴의 경우 이러한 기법을 생각할 수도 있지만 대부분의 초과 프로퍼티는 버그다 
	* 따라서 정의되지 않는 값을 넘기는것 보다는 interface를 재정의하는 것이 더 좋을 것
``` typescript
let squareOptions = { colour: "red", width: 100 }
let mySquare = createSquare(squareOptions)
```








