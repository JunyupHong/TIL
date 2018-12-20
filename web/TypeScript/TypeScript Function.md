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


## this
### this 와 Arrow function
* JavaScript에서 this는 함수가 호출 될 때 설정된 변수
* 일반 함수에서 this를 쓰면 바인딩이 되지 않는다
* arrow function에서 this를 사용하면 바인딩 된다

* 일반 함수는 함수가 호출된 곳의 this를 사용한다
* Arrow function은 함수가 호출된 곳이 아닌 함수가 생성된 곳의 this를 사용
``` typescript
// 일반함수의 this
let deck = {
	suits: ["hearts", "spades", "clubs", "diamonds"],
	cards: Array(52),
	createCardPicker: function() {
		return function() {
			let pickedCard = Math.floor(Math.random() * 52)
			let pickedSuit = Math.floor(pickedCard / 13)
			return {suit: this.suits[pickedSuit], card: pickedCard % 13}
		}
	}
}

// 여기서 this는 window 혹은 undefined가 된다
	// cardPicker() 가 자신을 호출하기 때문
// arrow function을 사용해서 함수가 생성된 곳에서 this를 바인딩 해준다
let cardPicker = deck.createCardPicker();
let pickedCard = cardPicker();
```
``` typescript
// arrow function의 this
let deck = {
	suits: ["hearts", "spades", "clubs", "diamonds"],
	cards: Array(52),
	createCardPicker: function() {
		return function() {
			let pickedCard = Math.floor(Math.random() * 52)
			let pickedSuit = Math.floor(pickedCard / 13)
			return {suit: this.suits[pickedSuit], card: pickedCard % 13}
		}
	}
}

// 여기서 this는 deck이 된다
// arrow function은 함수가 생성된 곳에서 this를 바인딩 해주기때문
let cardPicker = deck.createCardPicker();
let pickedCard = cardPicker();

```


### this 파라미터
* 위의 예제에서 this.suits의 [pickedSuit]의 타입은 any이다
	* this는 객체 리터럴 내부의 함수 표현식에서 나온것이기 때문
* => 명시적으로 this 파라미터를 제공해서 해결할 수 있다
* this 파라미터는 함수의 파라미터 목록에서 처음 나오는 가짜 파라미터!
``` typescript
interface Card {
	suit: string
	card: number
}
interface Deck {
	suits: string[]
	cards: number[]
	createCardPicker(this: Deck): () => Card;
}
let deck: Deck = {
	suits: ["hearts", "spades", "clubs", "diamonds"],
	cards: Array(52),
    // NOTE: The function now explicitly specifies that its callee must be of type Deck
	createCardPicker: function(this: Deck) {
		return () => {
			let pickedCard = Math.floor(Math.random() * 52)
			let pickedSuit = Math.floor(pickedCard / 13)
			return {suit: this.suits[pickedSuit], card: pickedCard % 13}
		}
	}
}

let cardPicker = deck.createCardPicker()
let pickedCard = cardPicker()
```
	* 이렇게 하면 TypeScript는 createCardPicker가 Deck 객체에서 호출될 것이라고 예상
	* this는 Deck 타입이 된다


### 콜백에서의 this 파라미터
* 콜백에서 this를 사용하면 에러가 발생할 수 있다
* 콜백을 호출하는 라이브러리가 정상 함수처럼 호출하기 때문에 this는 undefined가 된다
* 콜백 에러를 막기위해 this 파라미터를 사용해야함

#### 사용법
* 콜백 타입에 this를 사용하여 annotate 추가
	* this:void 는 addClickListener가 onclick이 this 타입을 필요로 하지 않는 함수라는 것을 의미
``` typescript
interface UIElement {
	addClickListener(onclick: (this: void, e: Event) => void): void
}
```

* this를 사용하여 호출 코드에 annotate 추가
	* this가 annotate 되어 있으면 onClickBad는 반드시 Handler의 인스터스에서 호출되어야 한다는 것을 기술 해야함
	* => TypeScript는 addClickListener에 this:void 가 있는 함수가 필요하다는 것을 탐지
	* => this 타입을  void로 변경해야함
``` typescript
class Handler {
	info: string
	onClickBad(this: Handler, e: Event) {
        // oops, used this here. using this callback would crash at runtime
		this.info = e.message;
	}
}
let h = new Handler();
uiElement.addClickListener(h.onClickBad);
```

* this 타입을 void로 변경
	* this의 타입이 void이므로 addClickListener로 넘겨줄 수 있다
	* => this와 this.info를 사용할 수 없음
	* => 사용하려면 arrow function을 이용해야함
``` typescript
class Handler {
	info: string
	onClickGood(this: void, e: Event) {
        // can't use this here because it's of type void!
		console.log('clicked!')
	}
}
let h = new Handler()
uiElement.addClickListener(h.onClickGood)
```

* arrow function을 이용
``` typescript
class Handler {
	info: string
	onClickGood = (e: Event) => { this.info = e.message }
}
```


## overload
* JavaScript는 본질적으로 매우 동적인 언어
	* => 단일 JavaScript 함수가 전달된 파라미터의 모양을 기반으로 여러 타입의 객체를 반환하는 경우는 일반적인 방법
``` typescript
let suits = ["hearts", "spades", "clubs", "diamonds"]

function pickCard(x): any {
	// Check to see if we're working with an object or array
	// if so, they gave us the deck and we'll pick the card
	if (typeof x == "object") {
		let pickedCard = Math.floor(Math.random() * x.length);
		return pickedCard
	}

	// Otherwise just let them pick the card
	else if (typeof x == "number") {
		let pickedSuit = Math.floor(x / 13)
		return { suit: suits[pickedSuit], card: x % 13 }
	}
}

let myDeck = [{ suit: "diamonds", card: 2 }, { suit: "spades", card: 10 }, { suit: "hearts", card: 4 }]

let pickedCard1 = myDeck[pickCard(myDeck)]
alert("card: " + pickedCard1.card + " of " + pickedCard1.suit)

let pickedCard2 = pickCard(15)
alert("card: " + pickedCard2.card + " of " + pickedCard2.suit)
```
	* pickCard 메소드는 사용자가 전달한 것에 따라 다른 것을 리턴한다
	* => 이것을 TypeScript에서는 overload목록과 동일한 함수에 대해 여러 함수 타입을 제공한다

* TypeScript에서 overload 목록과 동일한 함수에 대해 여러 함수 타입을 제공
	* => 이 목록을 컴파일러가 함수 호출을 해결하는 데 사용
``` typescript
let suits = ["hearts", "spades", "clubs", "diamonds"]

// overload 목록과 동일한 함수에 대해 여러 함수 타입을 제공
function pickCard(x: {suit: string; card: number; }[]): number
function pickCard(x: number): {suit: string; card: number; }

function pickCard(x): any {
	// Check to see if we're working with an object or array
	// if so, they gave us the deck and we'll pick the card
	if (typeof x == "object") {
		let pickedCard = Math.floor(Math.random() * x.length)
		return pickedCard
	}
	
// Otherwise just let them pick the card
	else if (typeof x == "number") {
		let pickedSuit = Math.floor(x / 13)
		return { suit: suits[pickedSuit], card: x % 13 }
	}
}

let myDeck = [{ suit: "diamonds", card: 2 }, { suit: "spades", card: 10 }, { suit: "hearts", card: 4 }]

let pickedCard1 = myDeck[pickCard(myDeck)]
alert("card: " + pickedCard1.card + " of " + pickedCard1.suit)

let pickedCard2 = pickCard(15)
alert("card: " + pickedCard2.card + " of " + pickedCard2.suit)
```
	* overload는 pickCard 함수에 대한 타입이 체크된 호출을 제공
	* `pickCard(x): any`는 오버로드 리스트의 일부가 아니므로 객체가 있는 overload, 숫자가 있는 overload 두가지 만 존재
	* => pickCard를 다른 파라미터 타입으로 호출하면 오류 발생

* 컴파일러가 올바른 타입체크를 선택하도록 하기 위해서 기본 JavaScript와 비슷한 과정을 거침
	* overload 목록을 보고 제공된 파라미터로 함수를 호출하는 overload 시도를 함
	* => 일치하는 항목이 있으면 overload를 선택
	* => overload 요청에 따라 가장 구체적인 것에서 덜 구체적인 것 순으로 넘어감

















