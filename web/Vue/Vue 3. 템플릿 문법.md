# Vue 3. 템플릿 문법
* Vue.js는 렌더링 된 DOM을 기본 Vue 인스턴스의 데이터에 선언적으로 바인딩 할 수 있는 HTML 기반 템플릿 구문을 사용
* 내부적으로 Vue는 템플릿을 가상 DOM 렌더링 함수로 컴파일한다
* 렌더링 함수를 직접 작성 할 수 있고 JSX를 지원한다
>  ※ JSX  
> JavaScript + XML을 합쳐서 탄생한 기존 자바스크립트의 확장 문법  
> 개발자가 자바스크립트 내부에 마크업 코드를 작성해 줄 수 있게 해준다  
> 단순히 XML만 아니라 변수나 프로퍼티의 바인딩 기능도 제공  

## 목차
* 보간법
	* 문자열
	* 원시 HTML
	* JavaScript 표현식 사용
* 디렉티브
	* 전달인자
	* 수식어
* 약어
	* v-bind 약어
	* v-on 약어

- - - -


## 보간법
* 데이터 바인딩의 가장 기본 형태: “Mustache”구문(이중 중괄호)을 사용한 텍스트 보간

### 문자열
``` html
<span>메세지: {{ message }}</span>
```
* Mustache 태그는 해당 데이터 객체의 message 속성 값으로 대체
* 데이터 객체의 message 속성이 변경될 때 마다 갱신

* v-once 디렉티브를 사용하여 데이터 변경 시 업데이트 되지 않는 일회성 보간을 수행할 수 있지만, 같은 노드의 바인딩에도 영향을 미친다(해당 컴포넌트와 자식 컴포넌트 모두 한번만 렌더링 된다)
``` javascript
<span v-once>변하지 않는 메세지: {{ message }}</span>
```

### 원시 HTML
* 이중 중괄호(mustaches)는 HTML이 아닌 일반 텍스트로 데이터를 해석
* 실제 HTML을 출력하려면 v-html 디렉티브를 사용
* v-html을 사용하면 변수에있는 내용이 텍스트로 들어간다
``` html
<p>Using mustaches: {{ rawHtml }}</p>
<p>Using v-html directive: <span v-html="rawHtml"></span></p>
```
``` javascript
const app = new Vue({
	data: function() {
		return({
			rawHtml: '<span style="color:red">This should be red</span>'
		});
	}
});
```
> 웹사이트에서 임의의 HTML을 동적으로 렌더링 하면 XSS(Cross Site Scripting)취약점으로 쉽게 이어질 수 있다  
> 따라서 신뢰할 수 있는 콘텐츠에서만 HTML 보간을 사용하고 사용자가 제공한 콘텐츠에서는 절대 사용하면 안된다!  

> <XSS취약점>  
> 페이지에 악의적인 스크립트를 포함시켜 웹페이지를 열람하는 접속자의 권한으로 부적절한 스크립트가 수행되어 정보유출 등의 공격을 유발할 수 있는 취약점  

* Mustaches는 HTML 속성에서 사용할 수 없다. 대신 v-bind 디렉티브를 사용
``` html
<div v-bind:id="dynamicId"></div>
// <div id={{ dynamicId }}></div> 사용불가!
```
``` javascript
const app = new Vue({
	data: function() {
		return({
			dynamicId: 'id1',
		});
	}
});
```

* boolean속성을 사용할 때 속성값이 true인 경우 v-bind는 다르게 작동한다 (null, undefined, false의 값을 가지면 속성은 렌더링 된 엘리먼트에 포함되지 않는다!)
``` html
<button v-bind:disabled="isButtonDisabled">Button</button>
// isButtonDisabled의 값이 null, undefined, false이면  disabled는 Button엘리먼트에 포함되지않는다
```



### JavaScript 표현식 사용
* Vue.js는 모든 데이터 바인딩 내에서 JavaScript 표현식의 모든 기능을 지원한다
* 바인딩 하나에 하나의 단일 표현식만 포함된다
``` html
{{num + 1}}
{{ ok ? 'YES' : 'NO' }}
{{ message.split('').reverse().join('') }}
<div v-bind:id="'list-'+id"></div>
```

> 템플릿 표현식은 샌드박스 처리되며 Math와 Date 같은 전역으로 사용 가능한 것에만 접근할 수 있다. 템플릿 표현식에서 사용자가 정의한 전역에 접근하면 안된다  
>  ※ 샌드박스  
> 외부 접근 및 영향을 차단하여 제한된 영역 내에서만 프로그램을 동작시키는 것  

- - - -

## 디렉티브
* 디렉티브는 v- 접두사가 있는 특수 속성
* 디렉티브 속성 값은 단일 JavaScript 표현식이 된다(v-for은 예외)
* 디렉티브의 역할은 표현식의 값이 변경될 때 사이드 이펙트를 반응적으로 DOM에 적용하는 것

``` html
// seen에 따라 p엘리먼트를 제거 또는 삽입한다
<p v-if="seen"></p>

// v-bind 디렉티브는 반응적으로 HTML속성을 갱신하는데 사용된다
// id를 bind1으로 바인딩해준다
<p v-bind:id="bind1"></p>
```

### 전달인자
* 콜론(:)으로 표시되는 전달인자
``` html
// href는 전달인자로
// 엘리먼트의 href 속성을 표현식 url의 값에 바인드 하는 v-bind 디렉티브에게 알려준다
<a v-bind:href="url"> URL </a>

// v-on 디렉티브
// 전달인자는 이벤트를 받을 이름
<a v-on:click="doSomething"> Click! </a>
```

### 수식어
* 수식어는 점(.)으로 표시되는 특수 접미사
``` html
// .prevent 수식어
// 트리거된 이벤트에서 event.preventDefault()를 호출하도록 v-on 디렉티브에게 알려준다
<form v-on:submit.prevent="onSubmit"> ... </form>
```

### 디렉티브의 종류
* v-text: 화면에 태그 문자열이 그대로 나타남
``` html
// message가 화면에 나타남
<p v-text="message"></p>
```
* v-html: 태그 문자열을 파싱하여 화면에 나타남
``` html
// message라는 변수의 값이 html로 파싱되어 화면에 나타남
<p v-html="message"></p>
```
* v-bind: 속성 바인딩
* v-model: 양방향 바인딩
	* 1-way 바인딩: 모델 -> 뷰 방향만 전달
	* 2-way 바인딩: 모델 -> 뷰, 뷰 -> 모델 양방향 전달
``` html
// input에서 입력된 값이 바로 p엘리먼트에 적용된다
<input type="text" v-model="name"/>
<div>입력된 이름 : <p v-html="name"></p>
</div>
```
	* v-show, v-if, v-else, v-else-if 를 이용해 여러 view모델을 바인딩 할 수 있다
``` html
// input의 값에 따라 바인딩 되는 모델이 다르다
<div id="account">
잔고 : <input type="text" v-model="balance" />
  <br />
등급: <span v-if="balance >= 1000000">Gold</span>
  	<span v-else-if="balance >= 500000">Silver</span>
  	<span v-else-if="balance >= 200000">Bronze</span>
  	<span v-else>Basic</span>
</div>
```
* v-for: 반복 디렉티브
* 등등...
- - - -

## 약어
* v- 접두사는 템플릿의 Vue 특정 속성을 식별하기 위한 시각적인 신호 역할을 한다
* 동적인 동작을 적용할 때 유용하지만, 자주 사용되는 디렉티브에 대해 너무 장황하다고 느껴진다
* 동시에 Vue.js가 모든 템플릿을 관리하는 SPA를 만들 때 v- 접두어의 필요성이 떨어진다
* 따라서 자주 사용하는 두 개의 디렉티브인 v-bind, v-on 에 대해 약어를 제공한다

### v-bind 약어
``` html
<a v-bind:href="url"> ... </a>
<a :href="url"> ... </a>
```

### v-on 약어
``` javascript
<a v-on:click="doSomething"> ... </a>
<a @click="doSomething"> ... </a>
```