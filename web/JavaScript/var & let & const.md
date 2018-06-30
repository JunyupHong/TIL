# var & let & const
## var
### var의 특징

* 변수 재선언 가능
	(같은 이름의 변수를 여러번 선언해도 에러가 나지 않는다)
``` javascript
var foo = 'first';
var foo = 'second';
```

* 호이스팅
``` javascript
console.log(foo);		// undefined (not error!)
var foo;				// 호이스팅 되어 에러가 발생하지 않음
```

* function scope
``` javascript
for(var j=0; j<10; j++) {
  console.log('j', j);
};
console.log(j); 		// 10


// 아래의 경우에는 에러가 발생
function counter () {
  for(var i=0; i<10; i++) {
    console.log(i);
  }
};
counter();
console.log(i);		// error (i is not defined)


// 즉시 실행 함수를 이용
(function(){
	for(var a = 0; a < 10; a++){
	// a는 function 내부에 호이스팅 된다.
	}

	for(b = 0; b < 10; b++){
		// var 없이 선언된 변수 b는 전역 scope에 호이스팅된다
		// 이걸 막기 위해선 'use strict'를 사용
	}
})();

console.log(a);			// error (a is undefined)
console.log(b);			// 10
```

> var은  
> 호이스팅과 function scope의  부작용 문제 때문에 사용하지 않는다!  
> let과 const를 사용!  

- - - -

## let, const
* ES6(ECMA Script 2015, ES6)로 넘어오면서 기존 ES5까지 사용하던 변수 선언 키워드인 var에 const와 let 키워드 추가

### let과 const의 특징
* 변수 재선언 불가능
	(같은 이름의 변수를 여러번 선언 할 수 없다.)
``` javascript
let a = 'first';
let a = 'second';			// error!

const b = 'first';
const b = 'second';		// error!
```

* let은 변수, const는 상수
	* let은 재할당이 가능하지만 const는 재할당이 불가능
``` javascript
// let
let a = 'first';
console.log(a);		// first
a = 'second';
console.log(a);		// second

// const
const b = 'first';
console.log(b);		// first
b = 'second';			// error!
```

	* 원시타입과 참조타입 재할당 비교
``` javascript
// 원시타입
let a = 'fisrt';
const b = 'first';

a = 'second';
b = 'second';				// error (재할당 불가능)

// 참조타입
let obj_a = {
	name: 'obj_a'
};
const obj_b = {
	name: 'obj_b'
}

obj_a['name'] = 'a';		// 프로퍼티 재할당 가능
obj_b['name'] = 'b';		// 프로퍼티 재할당 가능

obj_a['number'] = 1;		// 프로퍼티 동적 생성 가능
obj_b['number'] = 2;		// 프로퍼티 동적 생성 가능

obj_a = {}
obj_b = {}
```

* 호이스팅
	*  let과 const 모두 호이스팅이 일어난다.
	* 하지만 TDZ(Temporal Dead Zone)때문에 error가 발생한다

> TDZ(Temporal Dead Zone)  
> let과 const는 값을 할당하기 전에 변수가 선언 되어있어야 한다.  
> let은 변수 선언 후 나중에 할당이 가능하지만  
> const는 선언과 동시에 할당을 해야한다.  
``` javascript
a = 'test';		// error!
let a;			// (값 할당 전에 변수가 선언되어야 한다.)

let b;
b = 'let';		// 선언 후 할당 가능

const c;			// error! 선언과 동시에 할당을 해야함
const d = 'const';
```


* block scope
``` javascript
let a = 'in function';
const b = 'function';
if(true) {
	let a = 'in block';
	console.log(a);			// in block
	const b = 'block';
	console.log(b);			// block
}
console.log(a);			// in function
console.log(b);			// function
```

	* 	block scope & 호이스팅
``` javascript
const a = 'a';
const b = 'b';
if(true) {
	console.log(a);			// a
}

if(true) {
	console.log(b);			// error (b is undefined)
	const b = 'block';		// if문 내에서 호이스팅
}							// TDZ에 의해 에러가 난다!!
```


* 
