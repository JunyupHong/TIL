# JavaScript NameSpace Pattern
## NameSpace Pattern
* 전역 변수를 줄이고 과도한 접두어를 사용하지 않고도 이름이 겹치지 않게 하기 위해서 사용
* 전역 객체를 하나 만들고 모든 기능을 이 객체에 추가하는 패턴
* 전역 네임스페이스 객체의 이름은 애플리케이션 이름이나 라이브러리의 이름, 도메인명, 회사 이름 중에서 선택할 수도 있다
* 전역 객체 이름은 모두 대문자로 쓰는 네이밍 규칙을 사용하기도 함
* 코드 내의 이름 충돌 뿐 아니라 이 코드와 같은 페이지에 존재하는 자바스크립트 라이브러리나 위젯 등 서드 파티 코드와의 이름 충돌도 방지

``` javascript
// 전역 객체
var MYAPP = {};

// 생성자
MYAPP.Parent = function() {};
MYAPP.Child = function() {};

// 변수
MYAPP.some_var = 1;

//	객체 컨테이너
MYAPP.modules = {};

// 객체들을 컨테이너 안에 추가한다.
MYAPP.modules.module1 = {}; MYAPP.modules.module1.data = { a : 1, b : 2 }; MYAPP.modules.module2 = {};
```

### 단점
* 모든 변수와 함수에 접두어를 붙여야 하기 때문에 코드량이 많아짐
* 전역 인스턴스가 하나뿐이기 때문에 코드의 어느 한 부분이 수정되어도 전역 인스턴스를 수정하게 된다. 즉 나머지 기능들도 갱신된 상태를 물려 받는다.
* 이름이 중첩되고 길어지므로 프로퍼티를 판별하기 위한 검색 작업이 느려진다. 


## 범용 NameSpace 함수
* 프로그램의 복잡도가 증가하고 코드의 각 부분들이 별개의 파일로 분리되어 선택적으로 포함하게 되면, 어떤 코드가 특정 네임스페이스나 그 내부의 프로퍼티를 처음으로 정의한다고 가정하기 어렵다

* 네임스페이스에 추가하려는 프로퍼티가 이미 존재할 수도 있고 따라서 내용을 덮어쓰게 될 지도 모름

* 그러므로 네임스페이스를 생성하거나 프로퍼티를 추가하기 전에 이미 존재하는지 여부를 확인하는 것이 바람직

``` javascript
// 위험한 패턴
var MYAPP = {};

// 개선된 패턴
if(typeof MYAPP === 'undefined') {
	var MYAPP = {};
}

// 짧게 쓸 수 있다.
var MYAPP = MYAPP || {};
```

* 추가된 확인 작업 때문에 상당량의 중복 코드가 생겨날 수 있다
예를 들어 MYAPP.modules.module2 를 정의하려면, 각 단계의 객체와 프로퍼티를 정의할 때마다 확인 작업을 거쳐야 하므로 코드가 세 번 중복된다

* 이때 네임스페이스 생성의 실제 작업을 맡아 줄 재사용 가능한 함수를 만들어두면 편리하다
``` javascript
// 네임스페이스 생성 함수를 사용한다.
MYAPP.namespace('MYAPP.modules.module2');

// 위의 네임스페이스 함수는 다음과 같은 결과를 반환
var MYAPP = {
	modules : {
		module2 : {
		}
	}
};
```


* 다음과 같은 방식은 해당 네임스페이스가 존재하면 덮어쓰지 않기 때문에 기존의 코드를 망가뜨리지 않는다.
``` javascript
var MYAPP = MYAPP || {};

MYAPP.namespace = function (ns_string) {
	var parts = ns_string.split('.'),
	parent = MYAPP,
	i;

	// 처음에 중복되는 전역 객체명은 제거한다.
	if(parts[0] === MYAPP) {
		parts = parts.slice(1);
	}
	for (i = 0; i < parts.length; i ++) {
		// 프로퍼티가 존재하지 않는다면 생성한다.
		if(typeof parent[parts[i]] === 'undefined'){
			parent[parts[i]] = {};
		}
		parent = parent[parts[i]];
	}
	return parent;
};
```











