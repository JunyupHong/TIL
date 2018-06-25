# JavaScript 배열
* 배열은 자바스크립트 객체의 특별한 형태
* C나 JAVA의 배열과 같은 기능을 하는 객체
* 크기를 지정되지 않아도 된다
* 어떤 위치에 어느 타입의 데이터를 저장하는 것이 가능


## 배열 리터럴
* 자바스크립트에서 새로운 배열을 만드는데 사용하는 표기법
* 대괄호[ ] 를 사용
* 배열 리터럴에서는 각 요소의 값 만을 포함
	(객체 리터럴에서는 프로퍼티의 이름, 프로퍼티의 값을 모두 포함)
* 배열에 접근하기 위해서는 대괄호에 인덱스 값을 넣어서 접근
``` javascript
// 배열 리터럴을 통한 배열 생성
var array = ['a', 'b', 'c'];

// 배열 접근
console.log(array[0]);	// a
```


## 배열의 요소 생성
* 동적으로 배열의 원소를 추가 가능
* 자바스크립트의 배열은 값을 순차적으로 넣을 필요없이 아무 인덱스에 값을 동적으로 추가 가능
``` javascript
var array = [];				// 빈 배열 생성

array[10] = 'a';
array[0] = 10;
array[3] = false;
array['foo'] = true;
array['goo'] = function(){};

console.log(array)	
// [10, <2 empty items>, false, <6 empty items>, 'a', foo: 'aa', goo: [Function]]

console.log(array.length);		// 11
// length프로퍼티는 배열 내에 가장 큰 인덱스에 1을 더한 값
```


## length 프로퍼티
* 자바스크립트의 모든 배열은 length 프로퍼티가 존재
* 배열 내에 가장 큰 인덱스에 1을 더한 값!
* 하지만 실제 메모리는 length 크기처럼 할당되지 않는다
	=> 실제 값을 가지고 있는 인덱스만 메모리 할당
* 배열의 length 프로퍼티는 코드를 통해 명시적으로 값을 변경 가능

``` javascript
var arr = [];
console.log(arr.length);		// 0

arr[0] = 0;
arr[1] = 1;
arr[100] = 100;
console.log(arr.length);		// 101(가장 큰 인덱스에 1을 더한 값)


// length 프로퍼티 변경
arr.length = 3;
console.log(arr);				// [0, 1, <1 empty items>]
console.log(arr[100]);		// undefined

arr.length = 0;
console.log(arr);				// []
```


### length 프로퍼티와 push() 메소드
* 자바스크립트는 배열에서 사용 가능한 다양한 표준 메서드를 제공
	( ex push() )
* 배열 메서드는 length 프로퍼티를 기반으로 동작

#### push()
* 인자로 넘어온 항목을 배열의 끝에 추가하는 표준 배열 메서드
* 즉, length 프로퍼티가 가리키는 인덱스에 값을 저장
``` javascript
var arr = [];
arr.push(0);
console.log(arr);			// [0]

arr.length = 5;
arr.push(1);
console.log(arr);			// [0, <3 empty items>, 1]
```



## 배열과 객체
* 배열은 객체이다

### 배열과 객체 비교
``` javascript
// array 선언 (대괄호)
var array = ['red', 'green', 'blue'];

// object 선언 (중괄호)
// 숫자가 아닌 문자열로 key값 설정
var obj = {
		'0': 'red',
		'1': 'green',
		'2': 'blue'
};

console.log(array[0]);		// red
console.log(obj[0]);			// red
		// 연산자 내에 숫자가 사용될 경우
		// 자바스크립트 엔진이 자동으로 숫자를 문자열로 바꿔줌
		// 따라서 obj['0']이 아닌 obj[0]으로도 접근 가능

// type 비교
console.log(typeof array);	// object (배열도 객체이다!)
console.log(typeof obj);		// object

// length 프로퍼티
console.log(array.length);	// 3
console.log(obj.length);		// undefined
								// (length 프로퍼티가 존재하지 않음)

// 배열 표준 메서드
array.push('white');			// 가능
obj.push('white');			// 불가능(type error)
```


## 배열의 프로퍼티 동적 생성
* 배열은 객체이므로 인덱스가 숫자인 배열 원소 이외에도 객체처럼 동적으로 프로퍼티를 생성 가능
``` javascript
var arr = [0, 1, 2];
console.log(arr.lenght);		// 3

arr.color = 'red';
arr.name = 'array';
console.log(arr.length);		// 3
			// (length 프로퍼티는 배열원소의 가장 큰 인덱스가 변했을때 변함)
```


## 배열의 프로퍼티 열거
* 객체는 for in 문으로 프로퍼티를 열거
* 하지만 배열은 for in 문을 쓸 경우 불필요한 프로퍼티가 출력될 수 있으므로 for 문도 사용

``` javascript
var arr = [0, 1, 2];
arr.color = 'red';
arr.name = 'array';

for (var prop in arr) {
	console.log(arr[prop]);					// 0 1 2 red blue
}

for (var i = 0; i < arr.length; i++) {
	console.log(arr[i]);					// 0 1 2
}
```


## 배열 요소 삭제
* 배열도 객체이므로 delete 연산자를 이용
* splice() 배열 메서드 사용

### delete 연산자
* 배열의 요소 값만 삭제
* 값을 삭제하고 undefined가 할당
* 따라서 배열의 length는 변화 없음
``` javascript
var arr = [0, 1, 2, 3, 4, 5];
delete arr[2];
console.log(arr);		//	[0, 1, <1 empty item>, 3, 4, 5];
console.log(arr.length)	// 6
```

	---

### splice() 배열 메서드
* 배열에서 요소를 완전히 삭제
* `splice(start, deleteCount, item)`
	* start: 배열에서 시작위치
	* deleteCount: start부터 삭제 할 요소의 수
	* item: 삭제할 위치에 추가할 요소

``` javascript
var arr = [0, 1, 2, 3, 4, 5];

arr.splice(2, 1);					// 2번 인덱스부터 1개 삭제
console.log(arr);					// [0, 1, 3, 4, 5]
console.log(arr.length);			// 5

arr.splice(1, 2, 9, 9, 9, 9)		// 1번 인덱스부터 2개 삭제하고
									// 9를 4개 넣음
console.log(arr);					// [0, 9, 9, 9, 9, 4, 5]
```



## 생성자 함수 Array()
* 배열은 일반적으로 배열 리터럴로 생성
* 배열 리터럴은 Array() 생성자 함수로 배열을 생성하는 과정을 단순화 시킨 것

### 인자에 따른 동작
* 인자가 1개이고 숫자일 경우
	호출된 인자를 length로 갖는 빈 배열 생성
* 그 외의 경우
	호출된 인자를 요소로 갖는 배열 생성

``` javascript
var arr = new Array(3);
console.log(arr);					// [ <3 empty items> ]

var arr2 = new Array(0, 1, 2);
console.log(arr);					// [0, 1, 2]
```



## 유사 배열 객체
* 일반 객체 중 length 프로퍼티를 가진 객체
* 표준 객체 메서드 사용 가능
	(apply() 메소드를 이용해 push() 메소드 사용)
* ex) arguments 객체, jQuery 객체, …

``` javascript
var obj = {
	name: 'obj',
	length: 1		// length 프로퍼티가 있으므로 obj는 유사 배열 객체
}

console.log(obj.length);		// 1
obj.push('a');				// type error
```


