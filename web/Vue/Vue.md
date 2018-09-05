# Vue
* Vue.js는 단일 페이지 응용 프로그램을 빠르게 스캐폴딩하기 위한 공식 CLI를 제공

## Vue CLI
* express에서 사용 가능
* SPA(single page application)이 아님!!


## 사용
1. express 프로젝트 생성

2. 직접 script에 추가
``` javascript
script(/src/=‘/javascripts/vue.js’)
```
* vue.js파일을 받아서 script로 추가해주면된다!


3. npm 사용
`$ npm install vue`
* Vue로 대규모 응용 프로그램을 빌드할 때 NPM을 권장
* Webpack 또는 Browserify와 같은 모듈 번들러와 잘 작동
* Vue는 싱글 파일 컴포넌트를 만들기 위한 도구도 제공

4. CLI
* 단일 페이지 응용 프로그램을 빠르게 스캐폴딩하기 위한 공식 CLI




> 싱글 컴포넌트  
> 많은 Vue 프로젝트에서, 전역 컴포넌트는 Vue.component를 사용해 정의되고, 다음에 모든 페이지의 container 엘리먼트를 대상으로 하는 `new Vue({el: '#container'})`가 정의된다  
> 특정 뷰를 향상시키는 용도로만 사용되는 중소 규모 프로젝트에서 유용하지만 복잡한 프로젝트의 경우 또는 프론트엔드가 JavaScript 기반인 경우 단점이 분명해진다  
> 단점:   
> 	1. 전역 정의 모든 구성 요소에 대해 고유한 이름을 지정하도록 강요	문자열 템플릿 구문 강조가 약해 여러 줄로 된 HTML에 보기 안좋은 슬래시가 많이 필요  
> 	2. CSS 지원 없음 HTML 및 JavaScript가 컴포넌트로 모듈화 되어 있으나 CSS가 빠져 있는 것  
> 	3. 빌드 단계 없음 Pug (이전의 Jade) 및 Babel과 같은 전처리기가 아닌 HTML 및 ES5 JavaScript로 제한  
> 따라서 Webpack 또는 Browserify와 같은 빌드 도구를 이용해 .vue 확장자를 가진 싱글 파일 컴포넌트 로 해결   
> 장점:   
> 	1. 완전한 구문 강조  
> 	2. CommonJS 모듈  
> 	3. 컴포넌트에만 제한된 CSS  
>   
``` html
// 싱글 파일 컴포넌트 x
<template>
	<p>{{greeting}} World!</p>
</template>

<script>
module.exports = {
	data: function (){
		return {
			greeting: 'Hello'
		}
	}
}
</script>

<style scoped>
p {
	font-size: 2em;
	text-align: center;
}
</style>
```
``` html
// 싱글 파일 컴포넌트를 사용
<template lang="pug">
div
	p {{greeting}} World!
	other-component
</template>

<script>
import OtherComponent from './OtherComponent.vue'

exports default {
	data () {
		return {
			greeting: 'Hello'
		}
	}
}
</script>

<style scoped lang="sass">
p
	font-size: 2em;
	text-align: center;
</style>
```




