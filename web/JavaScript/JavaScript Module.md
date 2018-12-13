# JavaScript Module 
## 모듈이란
* 구현 세부사항을 캡슐화하고 공개 API를 노출해 다른 코드에서 쉽게 로드하고 사용 할 수 있도록 하는 **독립적이고 재사용 가능한 코드 조각**

## 모듈을 사용하는 이유
* 코드 추상화: 특수한 라이브러리에 기능을 위임 => 실제 구현의 복잡도를 이해할 필요가 없음
* 코드 캡슐화: 코드를 변경하지 않으려면 모듈 내부에 코드를 숨김
* 코드 재사용: 같은 코드를 반복해서 작성하는 것을 피함
* 의존성 관리: 코드를 다시 작성하지 않고도 쉽게 의존성을 변경

## ES5의 모듈 패턴
* EcmaScript 5 및 이전 버전은 모듈을 염두해두지 않았다
* 따라서 모듈화 디자인을 위해 다양한 패턴을 생각했다

### 즉시 실행 함수 표현식(IIFE, Immediately Invoked Function Expressions)
* 선언되었을 때 바로 실행되는 익명함수
* IIFE 내부의 코드 복잡도를 캡슐화 하여 IIFE 코드가 무엇을 하는지 이해하지 않아도된다
* IIFE 안에 변수를 정의하여 전역 스코프를 오염시키지 않는다 (Naming Space pattern)
``` javascript
// 함수 표현식
	// 함수 표현식은 함수를 반환하고 즉시 그 함수를 호출할 수 있다
(function() {
	// ...
})

// 즉시 실행 함수 표현식
	// 함수 표현식을 즉시 실행한 것
(function() {
		// ...
	}
)()
```
* => 하지만 IIFE는 의존성 관리를 위한 매커니즘은 제공하지 않는다

### 노출식 모듈(Revealing Module)
* IIFE와 유사하지만 변수에 반환 값을 할당한다
	* 할당한 함수를 즉시 실행해서 사용하면 하나의 인스턴스만 갖는 싱글톤이 생성된다
``` javascript
// Expose module as global variable
var singleton = function() {
	function sayHello() {
		console.log("hello!!");
	}

	// Expose API
	return {
		sayHello: sayHello;
	}
}()
```

* 모듈 API에 변수를 통해서 접근할 수 있다
``` javascript
singleton.sayHello();	// hello!!
```


* 함수를 바로 실행하지 않는 대신(싱글톤으로 사용하지 않고), Module 생성자 함수를 사용해서 인스턴스화 할 수 있다
``` javascript
var Module = function(){
  function sayHello(){
    console.log('hello!!');
  }

  return {
    sayHello: sayHello
  }
}

var module = new Module();
module.sayHello();		// hello!!
```

* => 노출식 모듈 패턴은 IIFE와 유사한 장점이 있지만 의존성 관리에 대한 매커니즘은 제공하지 않는다


## 모듈 포맷
* 모듈을 정의하기 위해 사용할 수 있는 문법

### 모듈포맷의 종류
* 비동기 모듈 정의 (AMD, Asynchronous Module Definition)
* CommonJS
* 만능 모듈 정의 (UMD, Universal Module Definition)
* System.register
* ES6 모듈 포맷
* …


### CommonJS
* JavaScript를 브라우저에서뿐만 아니라, 서버사이드 애플리케이션이나 데스크톱 애플리케이션에서도 사용하려고 조직한 자발적 워킹 그룹
* => JavaScript가 범용적인 언어로 사용되려면 체계가 필요하다
	* JavaScript의 문제점 (체계화의 필요성)
	* 서로 호환되는 표준 라이브러리가 없다
	* 다른 모듈을 삽입하는 표준적인 방법이 없다
	* 코드를 패키징해서 배포하고 설치하는 방법이 필요하다
	* 의존성 문제까지 해결하는 공통 패키지 모듈 저장소가 필요하다
	* => 모듈화로 해결가능!!

* 서버사이드 JavaScript환경을 전제 => 모든 파일이 로컬디스크에 있어서 필요할 때 바로 불러올 수 있다

#### 단점
* 필요한 모듈을 모두 내려받을때까지 아무것도 할 수 없다! (필요한 모듈이 로컬디스크에 있어야한다)
	* => <script> 태그를 동적으로 삽입해서 해결한다

* 비동기 모듈 문제
	* JavaScript가 브라우저에서 동작할때는 파일 단위의 스코프가 없다
	* <script >태그를 이용해서 로드하면 변수를 덮어 쓸 수도 있다(전역변수 문제)
	* => 모듈 전송 포맷을 추가로 정의함 (브라우저에서 사용하는 모듈)

#### CommonJS 포맷
* require와 module.exports를 사용해서 의존성과 모듈을 정의 (서버사이드 모듈과 브라우저 모듈이 다르다)
``` javascript
var dep1 = require('./dep1');
var dep2 = require('./dep2');

module.exports = function() {
	// ...
}
```

* 서버사이드에서 사용하는 모듈
``` javascript
var sum = require("./math").sum;  

exports.plusTwo = function(a){  
	return sum(a, 2);  
};
```

* 브라우저에서 사용하는 모듈 (모듈 전송 포맷)
	* require()함수(함수 클로저) 를 통해 전역 변수를 통제
	* 비동기 모듈 문제 해결
``` javascript
require.define({"complex-numbers/plus-two": function(require, exports){

	// 콜백 함수 안에 모듈을 정의한다
	var sum = require("./complex-number").sum;  
	exports.plusTwo = function(a){  
	return sum(a, 2);  
	};
}, ["complex-numbers/math"]);
// 먼저 로드되어야 할 모듈을 기술한다
```

* CommonJS는 서버사이드와 브라우저(비동기 상황)에서 사용이 가능하다 하지만 원래 목적은 서버사이드에서 JavaScript를 사용하는 것이었기 때문에 서버사이드 용으로 사용할때 장점이 많다



### AMD (비동기 모듈 정의)
* 비동기 상황에서도 JavaScript 모듈을 쓰기 위해 CommonJS에서 함께 논의하다 합의점을 이루지 못하고 독립한 그룹
* 비동기 모듈(필요한 모듈을 네트워크를 통해 내려받을 수 있도록 하는 것)에 대한 표준안을 다룸
* => 브라우저에서 사용
* 장점
	* 비동기 환경에서도 잘 동작
	* 브라우저와 서버사이드에서 동일한 코드로 동작
	* CommonJS의 모듈 전송 포맷(브라우저에서의 모듈)보다 간결, 명확

#### AMD 포맷
* require()함수를 사용할 수 있으며, exports 형태로 모듈을 정의할 수 있다 => CommonJS와 호환된다

* define 함수를 사용해서 모듈을 정의 => AMD만의 특징
``` javascript
define(id?, dependencies?, factory);  

// 예시
define(['jquery', 'lodash'], function(j, l) {
	return function {
		$: j,
		_: l
	};
});
```
* id: 모듈을 식별하는데 사용하는 인수 (선택)
	* id가 없으면 로더가 요청하는 < script > 태그의 src 값을 기본 id로 설정
* dependencies: 정의하려는 모듈의 의존성을 나타내는 배열 (선택)
	* 반드시 먼저 로드돼야 하는 모듈을 나타냄 => 먼저 로드된 모듈은 세 번째 인수인 팩토리 함수의 인수로 넘겨진다
	* default값: ['require', 'exports', 'module'] => CommonJS에서 정의한 전역객체와 동일한 역할
* factory: 팩토리 함수 (객체를 리턴하는 함수)
	* 모듈이나 객체를 인스턴스화하는 실제 구현을 담당
	* 팩토리 인수가 함수라면 싱글톤으로 한 번만 실행되고, 반환되는 값이 있다면 그 값을 exports 객체의 속성값으로 할당
	* 팩토리 인수가 객체라면 exports 객체의 속성값으로 할당

> **<CommonJS와 AMD 비교>**  
> 모듈 로드에서 모듈 명세의 차이가 있다  
> => 서버사이드에서는 CommonJS 명세가 간결  
> => 브라우저 환경(필요한 파일을 네트워크를 통해 받아야하는 환경)에서는 AMD가 유연한 방법을 제공  


### UMD (만능 모듈 정의)
* 브라우저와 서버사이드에서 둘 다 사용될 수 있다
* 디자인 패턴에 가까움
``` javascript
(function (root, factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD. Register as an anonymous module.
		define(['b'], factory);
	} else if (typeof module === 'object' && module.exports) {
		// Node. Does not work with strict CommonJS, but
		// only CommonJS-like environments that support module.exports,
		// like Node.
		module.exports = factory(require('b'));
	} else {
		// Browser globals (root is window)
		root.returnExports = factory(root.b);
	}
}(this, function (b) {
	//use b in some fashion.

	// Just return a value to define the module export.
	// This example returns an object, but the module
	// can return a function as the exported value.
	return {};
}));
```

### System.register
* ES5에서 ES6 모듈 문법을 지원하기 위해 설계된 모듈 형식

#### 작동원리
``` javascript
import { p as q } from './dep';
 var s = 'local';
  
export function func() {
	return q;
}

export class C {
}
```


``` javascript
System.register(['./dep'], function($__export, $__moduleContext) {
	var s, C, q;
	function func() {
		return q;
	}
	$__export('func', func);
	return {
		setters: [
      // every time a dependency updates an export, 
      // this function is called to update the local binding
      // the setter array matches up with the dependency array above
			function(m) {
				q = m.p;
			}
		],
		execute: function() {
			// use the export function to update the exports of this module
			s = 'local';
			$__export('C', C = $traceurRuntime.createClass(...));
		}
	};
});
```





