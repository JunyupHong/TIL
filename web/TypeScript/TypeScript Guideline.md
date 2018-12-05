# TypeScript Guideline
## Naming Convention
* type의 이름은 PascalCase를 사용
* interface의 이름의 접두어는 “I”를 사용하면 안된다
* enum 값은 PascalCase를 사용
* 함수 이름은 camelCase를 사용
* 지역변수나 속성이름은 camelCase를 사용
* private 속성의 접두사로 “_”를 사용하면 안된다
* 가능한한 이름에 단어를 전체를 사용

## Components
* 논리적 컴포넌트 당 1개의 파일을 만든다 (스캐너, 검사기, 편집기…)
* 새로운 파일을 만들지 마라
* “.generated.*” 접미사가 붙은 파일은 자동으로 생성되며 수동으로 편집하면 안된다

## Types
* type / function 을 여러 컴포넌트 간에 공유하지 않는 한 타입 / 함수을 export하면 안된다
* 전역 공간에 새로운 type이나 값을 지정하면 안된다
* 공유 type은 types.ts에 정의되어야 한다
* 파일 내에서 type 정의가 먼저 와야한다

## null & undefined
* null을 사용하지 말고 undefined를 사용

## General Assumptions (일반적인 추정)
* Nodes, Symbols 등과 같은 객체를 그 객체를 생성한 컴포넌트 외부에서 불변으로 간주한다 => 외부에서 변경하면 안된다
* 배열 생성후에 기본적으로 불변으로 간주한다

## Class
* 일관성을 위해 핵심 컴파일러 파이프 라인에서 클래스를 사용하지말고 대신 함수 클로저를 사용해라

## Flag
* 하나의 type에 2개 이상의 관련된 boolean 속성을 플래그로 변환해야한다

## String
* 쌍따옴표를 사용
* 사용자가 볼 수 있는 모든 문자열을 현지화 해야함 => diagnosticMessages.json에 항목을 입력

## Diagnostic Messages (진단 메세지)
* 문장의 끝에 마침표를 사용
* 불특정한 entity(개체)에는 부정관사(a/an)를 사용
* 확실한 entity(개체)의 이름을 지정해야 함 (변수이름, type 이름 등)
* 규칙을 말할 때, 피사체는 단수여야 한다 (ex: An external module O / External modules X)
* 현재시제를 사용


## Diagnostic Messages Code (진단 메세지 코드)
* 진단은 일반적인 범위로 분류된다
* 새 진단 메시지를 추가하는 경우 적절한 범위에서 마지막으로 사용한 번호보다 큰 첫 번째 정수를 사용
	* 1000 range for syntactic messages 구문 메세지
	* 2000 for semantic messages 의미론적 메세지
	* 4000 for declaration emit messages 선언을 위한 메세지
	* 5000 for compiler options messages 컴파일러 옵션 메세지
	* 6000 for command line compiler messages 커멘드라인 컴파일러 메세지
	* 7000 for noImplicitAny messages


## General Constructs
* `for in` 구문을 사용하면 안된다 대신 `ts.forEach`, `ts.forEachKey`, `ts.forEachValue`를 사용해라
* 반복문 대신 `ts.forEach `, `ts.map`, `ts.filter`를 사용해라

## Style
* 익명함수 표현식에 대해서 화살표 함수를 사용해라
* 화살표 함수
	* x => x + x;
	* (x, y) => x + y;
	* <T>(x: T, y: T) => x === y;
* 항상 반복문과 조건문을 중괄호로 묶는다 (같은 행에 있는 명령문은 중괄호를 생략할 수 있다)
* 열린 중괄호는 항상 필요한 것과 동일한 행을 사용
* 괄호로 묶인 구문에는 공백이 없어야한다 (단일 공백은 해당 구문에서 쉼표, 콜론 및 세미콜론 다음에 온다)
	* for (var i = 0, n = str.length; I < 10; I++) {}
	* if (x < 10) {}
	* function f(x: number, y: string): void {}
* 변수문 하나당 하나의 선언을 사용
* else를 닫는 중괄호와는 별도의 줄에 있음
* 들여쓰기 당 4칸을 사용










