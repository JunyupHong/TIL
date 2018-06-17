# HTML 태그
* 마크업 언어는 태그를 사용하여 데이터를 표현한다
* 해당 태그 영역이 어떤 역할을 해야 하는지 웹 브라우저의 렌더링 엔진에 알려주는 역할

## 닫는 태그가 없는 태그
* 태그 내부에 넣을 값이 없기 때문에 닫는 태그를 사용하지 않음
* self-closing
	규칙을 맞춰주기 위해서 <태그/> 로 사용하기도 한다 (강제적인 것은 아님)
		
```
// 닫는 태그가 없는 태그의 종류
<br>
<img>
<meta>
<link>
<hr>
```

## 태그의 속성 (attribute)
* 태그는 내부에 값을 넣을 수 있을 뿐 아니라 속성을 부여할 수 있다.
`<tag type(속성) = "value(값)">`

## id & class 속성
* 하나의 id는 하나의 태그에만 적용할 수 있다
* 하나의 class는 여러 개의 태그를 적용할 수 있다. 또한 하나의 태그가 여러 개의 class에 적용될 수 있다
```
	// 예시
	<div id="id-1"></div>
	<div id="id-2" class="box"></div>
	<div id="id-3" class="box"></div>
	<div id="id-4" class="box"></div>
```

## style 속성
* 보이는 형태를 정의할 수 있다
* HTML의 자체의 기능이라기 보다는 css의 속성을 HTML 문서 내에서 태그에 직접 설정할 때 쓰인다.

- - - -

## < head > 태그
* 웹 페이지에서 보이지 않는 정보들을 담는 영역

## 종류
### `<title> ... </title>`
	* 웹 페이지의 제목을 나타내는 태그
	* 웹페이지의 본문에는 보이지 않으며, 브라우저 탭 등에서 확인 할 수 있다.
	* 검색 엔진 등에서 가장 크게 보여지는 텍스트이므로 페이지의 특성을 드러내는 제목을 작성하는 것이 중요하다.

### `<meta/>`
	* meta 태그는 웹 페이지의 보이지 않는 정보를 제공하는데 쓰이는 태그이다.  
	* 페이지의 설명 요약, 핵심 키워드, 제작자, 크롤링 정책 등 수많은 정보를 제공할 수 있다.
	
### `<link> ... </link>`
	* 외부 파일을 연결
	* 주로 css 연결에 사용

### `<script> ... </script>`
* 외부 파일과 연결
* 주로 JavaScript 연결에 사용

- - - -

## < body > 태그
* 웹 페이지에서 보이는 정보들을 담는 영역

## 종류
### `<br>`
* Line Break
* 줄 바꿈으로 사용
* 기본적으로 HTML은 코드의 가독성 향상을 위해 엔터를 눌러도 화면에 나타나지 않는다. => <br>을 통해서 줄바꿈을 해준다
	
	---
	
### `<pre> ... </pre>`
* Preformatted Text
* HTML	에서 엔터를 화면에 적용하고 싶다면 `<pre>`태그를 이용하면 된다.
* `<pre>` 태그는 줄바꿈, 띄어쓰기, 탭 등 원래 무시되던 문자들이 웹페이지에 나타난다.
	
	---
	
### `<p> ... </p>`
* Paragraph
* 하나의 문단을 만들 때 사용
* 문단의 위쪽과 아래쪽은 줄바꿈이 생긴다
	
	---
	
### `<b> ... </b>`
* Bold
* 글자를 굵게 표시하는 태그 (== css의 `font-weidth: bold`)
* 하지만 최신 표준에서는 `<strong>` 태그 를 권고
* (<b> 태그는 단순히 굵기만을 변경, <strong> 태그는 강조의 의미)
	
	---
	
### `<i> ... </i>`
* Italic
* 글자를 기울여서 표시 (== css의 “font-style: italic”)

	---

### `<h1> ... </h1>`
* Head
* 섹션, 문단의 제목을 나타냄
* 숫자가 커질수록 글자의 크기가 작아짐
* 단순히 글자의 크기가 크게 보일뿐 아니라 문서를 파악하기 위해 쓰임

	---

### `<a> ... </a>`
* Anchor
* 하이퍼 링크를 걸어주는 태그
**속성**
	* href: 클릭 시 이동할 링크
	* target: 링크를 여는 방법
		1. _self: 현재 페이지 (default)
		2. _blank: 새 탭
		3. _parent: 부모 페이지
		4. _top: 최상위 페이지
		5. 프레임이름: 직접 프레임 이름을 명시 가능
**사용법**
```
		<a href="http://www.naver.com">Go Naver</a><br>
		<a href="http://google.co.kr" target="_blank">Go Google (new window)</a>
```
		
	---
		
### `<img> ... </img>`
* 이미지를 삽입하는 태그
* 이미지 파일이 경로에 없을 시 이미지가 출력되지 않거나 엑스박스가 뜨게 된다.
	
**속성**
	* src: 이미지의 경로
	* width: 이미지 가로 크기
	* height: 이미지 세로 크기
	* ...
	
**사용법**
```
	<img src="image.png" alt="이미지 설명" width="500" height="300">
```

**img태그와 css의 background-image 비교**
	* img태그의 이미지는 width, height에 맞게 늘어난다 (비율이 달라진다)
	그러나 background-image의 이미지는 늘어나지 않고 잘린다 (비율 유지 가능)
	* img태그는 height를 가지고 있기때문에 height: auto가 가능.
	하지만 background-image는 이미지의 height를 모르므로 height를 직접 지정해 주어야한다.
	( width는 둘 다 지정해줘야 한다 )
	* img태그는 text를 넣을 수 없지만
	background-image는 배경이므로 text를 넣을 수 있다
	
	---
	
### `<table>`
	* < tr > 표의 열을 나타냄
	* < td > 표의 행을 나타냄
	* < table >태그는 적절한 사용방법이 아니다
	* 따라서 div태그와 css를 이용해서 table을 만드는 것이 좋다!
	
	---

### `<div> ... <div>`
	* Division
	* 레이아웃을 나누는데 쓰임
	* 특별한 기능은 없지만, 가상의 레이아웃을 설계 하는데 쓰임
	* 주로 css와 연동하여 사용
	* 기본 display속성: block (div마다 줄바꿈이 되어 나타남)

	---

### `<span> ... </span>`
	* div태그와 비슷하나 기본 display 속성이 inline이다.
	* inline 속성은 줄바꿈이 되지 않고 한줄로 나타남.
		
	---

### `<li> ... </li>`
	* List
	* 목록을 만드는데 쓰임
	* `<ul> ... </ul>` 또는 `<ol> ... </ol>` 과 같이 사용
	* `<ul>`: Unordered List 순서없이 모양으로 목록을 만듬
	* `<ol>`: Ordered List 번호를 매겨 순서가 있는 목록을 만듬
	
**사용법**
```
	<ol>
		<li>목록1</li>
		<li>목록2</li>
	</ol>
	<ul>
		<li>목록1</li>
		<li>목록2</li>
		<li>목록3</li>
		<ol>
			<li>목록3-1</li>
			<li>목록3-2</li>
		</ol>
	</ul>
```

	---

### `<form/>`
* 웹 페이지에서의 입력 양식을 의미
	
* **속성**
	* name: form의 이름
	* action: form 데이터가 전송되는 백엔드 url
	* method: form 전송 방식 (GET / POST)
	* ...

* **종류**

* **< Input > 태그**

**Input 태그의 속성**
	type: 종류를 나타냄
	`text: 일반 문자`
	`password: 비밀번호`
	`button: 버튼`
	`submit: 양식 제출용 버튼`
	`reset: 양식 초기화용 버튼`
	`radio: 한개만 선택할 수 있는 컴포넌트`
	`checkbox: 다수를 선택할 수 있는 컴포넌트`
	`file: 파일업로드`
	`reset: form의 값들을 초기화하는 컴포넌트`
	`image: 이미지 형태로 나타남, src="이미지파일", alt="대체 텍스트 작성"`
	`hidden: 사용자에게 보이지 않는 숨은 요소`
	`disabled: 해당 form요소를 비활성화시킴`
	`readonly: 폼안의 텍스트를 수정할 수 없고 읽을수만 있다`
	`maxlength: 글자 수 제한으로 지정된 숫자 이상은 넣을 수 없게 한다`
				
	name: 데이터의 이름
	value: 기본 값을 지정
	placeholder: 글씨가 써질 곳에 연하게 글을 써줌. 마우스 커서가 생성되면 없어짐
	
**사용법**
```
	<input type="text" name="name" placeholder="아이디 입력">
```
```
	<input type="password" name="password" value="비밀번호 입력">
```	
```
	<input type="radio" name="gender" value="M">남자
	<input type="radio" name="gender" value="F">여자
```
```
	<input type="checkbox" name="part" value="eng">영어
	<input type="checkbox" name="part" value="math">수학
```	
```
	<input type="submit" value="제출">
```
	
* < select > & < option > 태그
드롭다운 리스트를 만드는 태그
		
**사용법**
```
	<select>
		<option value="ktx">KTX</option>
		<option value="itx">ITX 새마을</option>
		<option value="mugung">무궁화호</option>
	</select>
```
		
* 기타 < form >태그
`<label>`
`<textarea>`
`<button>`
`<optgroup>`
`<fieldset>`



* **< textarea > 태그**
높이와 너비를 가진 글 박스

**Input 태그의 속성**
```
cols: 너비
	cols="40"	// 40글자의 너비를 갖는다
```
```
rows: 높이
	rows=“3”	// 3줄의 높이를 갖는다
```
`disabled: 해당 form요소를 비활성화시킴`
`readonly: 폼안의 텍스트를 수정할 수 없고 읽을수만 있다`

**input 태그의 text type과 textarea의 차이점**
input태그는 한 줄로만 작성가능
textarea는 여러 줄 작성 가능 (엔터도 작성 가능)
