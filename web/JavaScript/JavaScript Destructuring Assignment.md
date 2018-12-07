# JavaScript Destructuring Assignment
## Destructuring Assignment
* 구조 분해 할당 / 비구조 할당
* 배열이나 객체의 속성을 해체하여 그 값을 개별 변수에 담을 수 있게 하는 자바스크립트 표현식

## Array destructuring
### 배열 구조분해
``` typescript
let arr = [ 10, 20 ];
let [ a, b ] = arr;
// let [ a, b ] = [ 10, 20 ]; 으로도 사용

console.log(a);		// 10
console.log(b);		// 20
```

### 변수 선언과 할당 분리
``` javascript
let a, b;
[ a, b ] = [ 10, 20 ];

console.log(a);		// 10
console.log(b);		// 20
```

### 기본값 설정
``` javascript
let a, b;
[ a = 10, b = 20 ] = [ 1 ];

console.log(a);		// 1
console.log(b);		// 20
```

### 값 교환
	* 배열 구조분해를 사용하면 한줄로 표현이 가능
``` javascript
let a = 1;
let b = 2;

// swap
[ b, a ] = [ a, b ];

console.log(a);		// 2
console.log(b);		// 1
```

### 함수에서의 사용
* 함수 파라미터에서 사용
``` javascript
function func([a, b]) {
	console.log(a);
	console.log(b);
}

func([1, 2]);
```

* 함수 리턴값으로 사용
``` javascript
function func() {
	return [1, 2];
}
let [ a, b ] = func();
```

### 값 무시
``` javascript
const arr = [ 1, 2, 3, 4, 5];
let [ , a, , b, ] = arr;

console.log(a + ", " + b);	// 2, 4
```

### … 구문
* 변수에 나머지 배열의 값들을 저장한다
* 변수 뒤에  ‘ , ‘가 들어가면 에러!
``` javascript
let a, b, rest;
[ a, b, ...rest ] = [ 10, 20, 30, 40, 50 ];


// [ a, b, ...rest, ] = [ 10, 20, 30, 40, 50 ]; =>  error!

console.log(rest);	// [30, 40, 50]
```


## Object destructuring
### 객체 구조분해
``` javascript
const obj = {
	a: "foo",
	b: 12,
	c: true
};

const { a, b } = obj;
```

### 변수 선언과 할당 분리
* 괄호로 묶어야한다 => JavaScript는 일반적으로 ‘ { ’를 블록의 시작으로 구문분석하기 때문
``` javascript
let a, b;
({ a, b } = { a: 1, b: 2 });
```

### 새로운 변수 이름에 할당
* property에 renaming을 할 수 있다
	* `a: first`: a에 first라는 이름을 지정한다
``` javascript
const obj = {
	a: "foo",
	b: 12,
	c: true
};

let { a: first, b: second } = obj;
console.log(first);		// foo
console.log(second);		// 12
```

### 기본값 설정
* default parameter 사용
``` javascript
function func({size = 'big', cords = { x: 0, y: 0 }, radius = 25} = {}) {
	// es5 에서는 parameter를 받아서 undefined인지 확인해야했다
}

func({
		cords: { x: 18, y: 30 },
		radius: 30
		}
);

```

### 중첩된 객체와 배열 구조분해
``` javascript
var obj1 = {
	name: "obj1",
	arr: [
		{
			name: "obj2"
		}
	],
	string: "string"
};

let { name: anotherName, arr: [{ name: innerName }] } = obj1;

console.log(anotherName);		// obj1
console.log(innerName);		// obj2
```

### 매개변수에서 구조분해
``` javascript
const person = {
	id: 42,
	name: "foobar",
	fullName: {
		firstName: "Foo",
		lastName: "Bar"
	}
};

function getPersonName({name}) {
	return name;
}

function getFirstNameofPerson({name: n, fullName: {firstName: fname}}) {
	console.log("first name of" + n + " is " + fname);
}


console.log("name is:" + getPersonName(person));	// name is foobar
getFirstNameofPerson(person);		// first name of foobar is Foo
```

### 계산된 변수 이름 사용
* 런타임 중에 계산된 값을 구조분해 변수 이름으로 사용할 수 있다
``` javascript
const key = "k";
let { [key]: foo } = { k: "bar"};

console.log(foo);		// bar
```

* ES6부터 리터럴 객체에서 런타임 동안 계산된 값을 프로퍼티의 이름으로 사용할 수 있다
``` javascript
let param = 'Size';
let config = {
	[param]: 12,
	["moblie" + param]: 4
}
console.log(config);	// { Size: 12, mobileSize: 4}
```



