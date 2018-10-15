# Vue 10. 컴포넌트

## 목차
* 컴포넌트란?
* 컴포넌트 사용하기
* props
* props가 아닌 속성
* 슬롯을 사용한 컨텐츠 배포
* 동적 컴포넌트
* 기타


- - - -

## 컴포넌트란?
* 컴포넌트는 Vue의 컴파일러에 의해 동작이 추가된 사용자 지정 엘리먼트
* 기본 HTML 엘리먼트를 확장하여 재사용 가능한 코드를 캡슐화 하는데 도움이 된다
* Vue 컴포넌트는 Vue 인스턴스 이기도 하므로 모든 옵션 객체를 사용할 수 있다(루트에만 사용하는 옵션 제외), 또한 라이프사이클 훅을 사용할 수 있다

- - - -

## 컴포넌트 사용하기
1. Vue 인스턴스(컴포넌트) 생성
	* 컴포넌트를 생성하지않고 바로 컴포넌트를 등록할때 option에 직접 코드로 넣어주어도 된다
``` javascript
// vue-cli
new Vue({
})

// vue-router
<template></template>
<script>
export default{
	name: '',
	data() { return {}; }.
	// ...
}
</script>
<style></style>
```

2. 컴포넌트 등록
	* 전역 등록 `Vue.component(tagName, option);`
``` javascript
// 컴포넌트를 사용할 파일
import Component from './componentpath';

// 전역 등록
	// option에서 직접 컴포넌트를 넣어줄 수 있다
Vue.component('component-name', Component);
```

	* 지역 등록
		* 컴포넌트를 components 인스턴스 옵션으로 등록함으로써 다른 인스턴스/컴포넌트의 범위에서만 사용할 수 있게 한다
``` javascript
// 컴포넌트를 사용할 파일
import Component from './componentpath';

// 지역 등록
	// vue-cli
new Vue({
	components: {
		'component-name': Component,
	}
});

	// vue-router
export default{
	components: {
		'component-name': Component,
	},
	// ...
}
```

3. 컴포넌트 사용
``` javascript
<component-name></component-name>
```


### DOM 템플릿 구문 분석 경고
* DOM을 템플릿으로 사용할 때(ex el 옵션을 사용하여 기존 컨텐츠가 있는 엘리먼트를 마운트 하는 경우), Vue는 템플릿 컨텐츠만 가져올 수 있기 때문에 HTML이 작동하는 방식에 고유한 몇 가지 제한 사항이 적용된다
* <ul>, <ol>, <table>, <select>와 같은 일부 엘리먼트는 그 안에 어떤 엘리먼트가 나타날 수 있는지에 대한 제한을 가지고 있으며, <option>과 같이 특정 다른 엘리먼트 안에만 나타날 수 있다
* 예시
``` html
// 아래의 경우 렌더링시 에러 발생
<table>
	<my-component></my-component>
</table>

// is 속성을 사용해서 해결
<table>
	<tr is="my-component"></tr>
</table>
```

* 다음 중 하나에 포함되면 문자열 템플릿을 사용하는 경우에 이러한 제한 사항이 적용되지 않는다
	* `<script type="text/x-template">`
	* `JavaScript 인라인 템플릿 문자열`
	* `.vue 컴포넌트` 
* 따라서 항상 문자열 템플릿을 사용하는 것이 좋다


### data는 반드시 함수여야한다
* 컴포넌트에서 data는 항상 함수여야한다
* 함수가 아닐 경우 콘솔에서 경고
* data가 함수가 아닌경우 여러 컴포넌트 인스턴스에서 모두 같은 data를 공유하므로 문제가 생긴다
* 따라서 data는 함수로 리턴하는 형태로 사용해야한다

### 컴포넌트 작성
* 컴포넌트는 부모-자식 관계에서 가장 일반적으로 사용하기 위한 것
* 부모는 props로 자식에게 데이터를 전달하고, 자식은 emit events로 부모에게 메세지를 보낸다





