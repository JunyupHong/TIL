# Vue 5. 클래스와 스타일 바인딩
* 데이터 바인딩은 엘리먼트 클래스 목록과 인라인 스타일을 조작하기 위해 일반적으로 사용된다
* 두 속성은 v-bind를 사용하여 처리할 수 있다  (표현식으로 최종 문자열을 계산하면 된다) -> 그러나 문자열 연결에 간섭하는것은 짜증나는 일이며 오류가 발생하기 쉽다
* 이러한 이류로 Vue는 class와 style에 v-bind를 사용할 때 특별히 향상된 기능을 제공한다 -> 표현식은 문자열 이외에 객체 또는 배열을 이용할 수 있다

- - - -

## 목차
* HTML 클래스 바인딩하기
* 인라인 스타일 바인딩

- - - -

## HTML 클래스 바인딩하기
### 객체 구문
* 클래스를 동적으로 토글하기 위해 v-bind:class에 객체를 전달할 수 있다
``` html
// active 클래스의 존재 여부가 데이터 속성 isActive 의 참 속성에 의해 결정되는 것을 의미
<div v-bind:class="{ active: isActive }"></div>
```

* 객체에 필드가 더 있으면 여러 클래스를 토글할 수 있다
* 또한 v-bind:class 디렉티브는 일반 class 속성과 공존할 수 있다
``` html
<div class="static"
     v-bind:class="{ active: isActive, 'text-danger': hasError }">
</div>

--------------------------------------------------
// 데이터
data: {
	isActive: true,
	hasError: false
}
--------------------------------------------------
// 결과
	// 속성 값이 null, false, undefined의 값은 bind되지 않는다
	// 따라서 속성 값이 true인 isActive는 bind되고,
	// 속성 값이 false인 hasError는 bind되지 않는다
<div class="static active"></div>


// isActive 또는 hasError가 변경되면 클래스 목록도 업데이트 된다
// (hasError의 값이 true로 바뀌면 '<div class="static active text-danger"></div>'가 된다)
```


* 바인딩 된 객체는 인라인 일 필요는 없다
``` html

<div v-bind:class="classObject"></div>

--------------------------------------------------
// 데이터
data() {
	return {
		classObject: {
			active: true,
			'text-danger': false
		}
	};
}
```


* 객체를 반환하는 계산된 속성에도 바인딩 할 수 있다 (일반적이며 강력한 패턴이다)
``` javascript
// template
<div v-bind:class="classObject"></div>

--------------------------------------------------
// script
data() {
	return{
		isActive: true,
		error: null
	};
}
computed: {
	classObject() {
		return {
			active: this.isActive && !this.error,
			'text-danger': this.error && this.error.type === 'fatal'
		}
	}
}
```



### 배열 구문

* v-bind:class에 배열을 전달하여 클랙스 목록을 지정할 수 있다
``` javascript
// template
<div v-bind:class="[activeClass, errorClass]"></div>

	// 결과
	// <div class="active text-danger"></div>

--------------------------------------------------
// script
data() {
	return {
		activeClass: 'active',
		errorClass: 'text-danger'
	};
}
```

* 배열에 있는 클래스를 조건부 토글(삼항 연산자를 이용)
``` html
// isActive가 true일 경우 activeClass를 바인딩한다
// false일 경우 바인딩하지 않는다
<div v-bind:class="[isActive ? activeClass : '', errorClass]"></div>
```
	* 삼항 연산자를 이용하면 조건부 클래스가 여러 개 인 경우 너무 길어질 수 있다 -> 배열 내에서 객체 구문을 사용해 해결

* 배열 구문 내에서 객체 구문을 사용
``` html
<div v-bind:class="[{ active: isActive }, errorClass]"></div>
```


### 컴포넌트와 함께 사용하는 방법
* 사용자 정의 컴포넌트로 class속성을 사용하면, 클래스가 컴포넌트의 루트 엘리먼트에 추가된다
* 추가된 엘리먼트는 기존 클래스는 덮어쓰지 않는다
``` javascript
// 컴포넌트 선언
Vue.component('my-component', {
  template: '<p class="foo bar">Hi</p>'
})

--------------------------------------------------
// template

<my-component class="baz boo"></my-component>
	// 결과
	// <p class="foo bar baz boo">Hi</p>

// 클래스 바인딩
<my-component v-bind:class="{ active: isActive }"></my-component>
	// 결과 (isActive가 true일 경우)
	// <p class="foo bar active">Hi</p>
```

- - - -

## 인라인 스타일 바인딩
### 객체 구문
* v-bind:style 객체 구문은 매우 직설적이다
* CSS처럼 보이지만 JavaScript 객체이다
* 속성이름에 camelCase와 kebab-case를 사용할 수 있다(kebab-case의 경우 따옴표를 함께 사용해야한다)
``` html
// template
<div v-bind:style="{ color: activeColor, fontSize: fontSize + 'px' }"></div>

--------------------------------------------------
// data
data: {
  activeColor: 'red',
  fontSize: 30
}
```

``` html
// 스타일 객체에 직접 바인딩 하여 템플릿이 간결하게 하는게 더 좋다

// template
<div v-bind:style="styleObject"></div>

--------------------------------------------------
// data
data() {
	return {
		styleObject: {
			color: 'red',
			fontSize: '13px'
		}
	};
}
```
* 객체 구문은 종종 객체를 반환하는 계산된 속성과 함께 사용한다 (일반적이고 강력한 패턴)


### 배열 구문
* v-bind:style 에 대한 배열 구문은 같은 스타일의 엘리먼트에 여러개의 스타일 객체를 사용할 수 있게 한다
``` html
<div v-bind:style="[baseStyles, overridingStyles]"></div>
```

### 자동 접두사
* v-bind:style 에 브라우저 벤더 접두어(vender prefix)가 필요한 CSS 속성(ex transform)을 사용하면 Vue는 자동으로 해당 접두어를 감지하여 스타일을 적용한다
> **< 벤더 접두어(vender prefix) >**  
> 브라우저별로 따로 정한 CSS3의 속성을 잡아주기 위해서 사용  
> 마크업시 CSS의 Class앞에 ‘-moz-‘, ‘-webkit-‘, ‘-o-‘, ‘-ms-‘라는 각 브라우저에서 판독이 가능한 접두어를 붙여서 해당 브라우저에서 인식할 수 있게 하는 것을 지칭  
> - webkit: Chrome, Safari  
> - moz: Firefox  
> - o: Opera  
> - ms: Internet Explorer  


### 다중 값 제공
* 2.3버전 부터 스타일 속성에 접두사가 있는 여러 값을 배열로 전달할 수 있다
* 브라우저가 지원하는 배열의 마지막 값만 렌더링한다
``` html
// 이 예제에서는 flexbox의 접두어가 붙지않은 버전을 지원하는 브라우저에 대해 display : flex를 렌더링한다
<div v-bind:style="{ display: ['-webkit-box', '-ms-flexbox', 'flex'] }"></div>
```












