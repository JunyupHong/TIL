# CSS 개요

* Cascading Style Sheets
	-> 태그에 스타일 효과를 주는 언어
	
* RULE set
```
	꾸밀대상(태그이름) {
		속성 : 값;
	}
```	
	이때  꾸밀 대상 (태그)을 Selector (선택자),
	'속성: 값;' 을 Declaration (선언) 이라고 한다
	
* 특징
	속성은 "유전"된다

## Selector의 종류
* Element Selector ( Tag or Type Selector )
* Id Selector
* Class Selector
* Attribute Selector
* Pseudo Selector


## Element Selector
* 특정 타입의 모든 HTML 엘리먼트를 선택

`<p> Hello World! </p>`
```
	p {
		font-size: 100px;
		color: #ffffff;
		background: #000000;
	}
```

## Id Selector
* 특정 아이디를 가진 페이지의 엘리먼트를 선택
	( 한 페이지에는 아이디당 하나의 엘리먼트만 지정할 수 있음 )
* ' # ‘ 을 맨앞에 붙여 사용

`<p id = "my-id"> Hello World! </p>`
```
	#my-id {
		font-size: 100px;
		color: #ffffff;
		background: #000000;
	}
```

## Class Selector
* 특정 클래스를 가진 페이지의 엘리먼트를 선택
	( 한 페이지에 클래스가 여러번 적용될 수 있다 )
* ' . ' 을 맨앞에 붙여 사용
`<p class = "my-class"> Hello </p>`
`<p class = "my-class"> World! </p>`
```
	.my-class {
		font-size: 100px;
		color: #ffffff;
		background: #000000;
	}
```

## Attribute Selector
* 특정 속성을 갖는 페이지의 엘리먼트를 선택
 
`<p class = "my-class", status = "scrolled"> Hello </p>`
`<p class = "my-class"> World! </p>`
```
	// attribute가 scrolled인 my-class만 적용!
	.my-class[status="scrolled"] {
		font-size: 100px;
		color: #ffffff;
		background: #000000;
	}
```

## Pseudo Selector
* 특정 엘리먼트지만 특정 상태에 있을 때만 지정해줌

* 종류
	:link - 방문한 적이 없는 링크
	:visited - 방문한 적이 있는 링크
	:hover - 마우스를 롤오버 했을때
	:active - 마우스를 클릭 했을때
	:focus - 포커스 되었을때(input태그 등)
	:first - 첫번째 요소
	:last - 마지막 요소
	
```
	.my-class {
		font-size: 100px;
		color: #ffffff;
		background: #000000;
	}

	// pseudo selector (hover)
	.my-class:hover {
		color: blue;
		cursor: pointer;
	}
```


## 기타
* 부모 자식 선택자
	1. 태그 이름 사이에 공백을 넣은 문자는 부모 태그 하위에 있는 태그에 스타일을 적용시킨다
```
		// box 클래스 내부의 모든 yellow-box클래스에 적용
	div.box .yellow-box {
		background-color: yellow;
	}
```

	2. ' > ' 특수문자를 이용하면 순수 부모-자식 태그 간의 관계에만 적용된다.
	부모의 속성을 자식이 가져온다
```
		// parent클래스의 자식인 child 클래스만 스타일을 적용
	.parent > .child {
		background-color: black;
	}
```

	
* 다중조건 선택자

	1. 공백이 없는 선택자를 통해 여러 조건을 동시에 만족하는 태그에 스타일을 적용 ( AND )
```		
	태그이름#아이디 { 속성1:속성값; 속성2:속성값; } 
	태그이름.클래스명 { 속성1:속성값; 속성2:속성값; }
	.클래스명#아이디 { 속성1:속성값; 속성2:속성값; }
```

```
	// html
	// 이때 두 엘리먼트의 속성이 같으면 뒤쪽의 속성을 따른다
	
	<태그 id="아이디"></div>
	<div class = "클래스1 클래스2"></div>
	<div class = "클래스">
```
	

	2. 쉼표( , )를 통해 두 선택자 중 하나라도 만족 시 태그에 스타일을 적용
```
	#아이디, .클래스명{ 속성1:속성값; 속성2:속성값; } 
	태그이름, .클래스명{ 속성1:속성값; 속성2:속성값; } 
```
