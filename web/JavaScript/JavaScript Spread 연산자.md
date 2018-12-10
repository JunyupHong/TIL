# JavaScript Spread 연산자
* 배열을 다른 배열로 펼치거나, 객체를 다른 객체로 퍼뜨릴 수 있다
* 배열 리터럴의 일부를 반복가능한 식(다른 배열 리터럴 등)에서 초기화하거나 식을 함수 호출에서 여러 인수로 확장할 수 있다
* Destructuring의 반대

## 배열의 spread
* `var array = [ [arg0ToN], ...iterable, [arg0ToN] ]`
	* iterable : 반복 가능한 객체
	* arg0ToN : 하나 이상의 배열 리터럴 요소 (선택사항)
``` javascript
let a = [ 1, 2, 3 ];
let b = "dog";
let c = [ 42, "cat"];

let d = [ ...a, b, ...c ];

console.log(d);	// 1, 2, 3, dog, 42, cat
// concat 메서드의 사용과 유사
// => d = a.concat(b, c);
// 그러나 spread는 배열의 어느 곳에서나 추가할 수 있다
```

## 함수 호출에서의 spread
* `func( [args] ...iterable [args ])`
``` javascript
function func(a, b, c, x, y, z) {
	return a + b + c + x + y + x;
}

let args = [ 1, 2, 3 ];
console.log(f(...args, 4, ...[5, 6]));	// 21
```


## 기타
### apply 사용 간소화하기
``` javascript
function func(x, y, z) {
	return x, y, z;
}

let args = [ 1, 2, 3 ];

// 두 구문은 서로 같다
func.apply(this, args);
func(...args);
```
