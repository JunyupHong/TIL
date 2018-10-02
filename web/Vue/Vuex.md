# Vuex
## 목차
* Vuex 설치
* Vuex란?
* 언제 사용해야 하나?
* Vuex 시작하기

- - - -

## Vuex 설치
### 직접 다운로드 / CDN
* https://unpkg.com/vues
* unpeg.com은 NPM 기반 CDN 링크를 제공
* Vue 뒤에 vuex를 추가하면 자동으로 설치된다
``` html
<script src="/path/to/vue.js"></script>
<script src="/path/to/vuex.js"></script>
```

### NPM
``` sh
$ npm install vuex --save
```

### Yarn
``` sh
$ yarn add vuex
```
* 모듈 시스템과 함께 사용하면 Vue.use()를 통해 Vuex를 명시적으로 추가해야 한다
``` javascript
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
```
* 전역 스크립트 태그를 사용할 떄는 이 작업을 할 필요가 없다


### Promise
* Vuex는 Promise를 필요로 함 -> 지원 대상 브라우저가 아직 Promise를 구현하지 않았따면 polyfill 라이브러리를 사용
``` html
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.js"></script>
```

- - - -

## Vuex 란?
* Vue.js 애플리케이션에 대한 **상태 관리 패턴** + **라이브러리**
* 애플리케이션의 모든 컴포넌트에 대한 중앙 집중식 저장소 역할
* 예측가능한 방식으로 상태를 변경할 수 있다

### 상태 관리 패턴
* Vue 앱
	* 상태는 앱을 작동하는 원본 소스
	* 뷰는 상태의 선언적 매핑
	* 액션은 뷰에서 사용자 입력에 대해 반응적으로 상태를 바꾸는 방법
	* => ‘단방향 데이터 흐름 개념’의 매우 단순한 도표 ( State -> View -> Action -> State -> … )
<img width="401" alt="vue structure" src="https://user-images.githubusercontent.com/39546874/46335854-dad11e80-c663-11e8-890b-ad856bfd2234.png">

``` javascript
new Vue({
  // 상태
  data () {
    return {
      count: 0
    }
  },

  // 뷰
  template: `<div>{{ count }}</div>`,

  // 액션
  methods: {
    increment () {
      this.count++
    }
  }
})
```


* 하지만 **공통의 상태를 공유하는 여러 컴포넌트**가 있는 경우 단순함이 저하된다!
	* 여러 뷰는 같은 상태에 의존한다
	* 서로 다른 뷰의 작업은 동일한 상태를 반영 할 수 있다


* 여러 뷰가 같은 상태에 의존해야 하는 문제의 경우, 지나치게 중첩된 컴포넌트를 통과하는 prop는 장황할 수 있고 형제 컴포넌트의 경우 작동하지 않는다
* 서로 다른 뷰의 작업이 동일한 상태를 반영해야 하는 경우, 부모/자식 인스턴스를 참조하거나 이벤트를 통해 상태의 여러 복사본을 변경 및 동기화 하려는 등의 해결 방법을 사용해야 한다
* 이러한 패턴은 부서지기 쉽고 유지보수가 불가능한 코드가 된다


* 따라서 컴포넌트에서 공유된 상태를 추출하고 이를 전역 싱글톤으로 관리해야 한다!
* 이를 통해 컴포넌트 트리는 커다란 뷰가 되고 모든 컴포넌트는 트리에 상관없이 상태에 액세스 하거나 동작을 트리거 할 수 있다
* 또한 상태 관리 및 특정 규칙 적용과 관련된 개념을 정의하고 분리함으로써 코드의 구조와 유지 관리 기능을 향상시킨다

* 이는 Flux, Redux, The Elm Architecture에서 영감을 받은 Vuex의 기본 아이디어
> Flux  
> => facebook에서 클라이언트-사이드 웹 어플리케이션을 만들기 위해 사용하는 어플리케이션  
> => 단방향 데이터 흐름을 활용해 뷰 컴포넌트를 구성하는 React를 보완하는 역할  
>   
> Redux  
> => JavaScript 어플리케이션에서 data-state 와 UI-state 를 관리해주는 도구  
>   
> The Elm Architecture  
> =>  웹 어플리케이션을 만들기 위한 간단한 패턴  
> => 모듈성, 코드 재사용 및 테스트에 적합 -> 리팩토링 및 기능 추가와 함께 유지보수가 쉬운 복잡한 웹 앱을 쉽게 만들 수 있음  


* 다른 패턴과 달리 Vuex는 Vue.js가 효율적인 업데이트를 위해 세분화된 반응 시스템을 활용하도록 특별히 고안된 라이브러리!
<img width="643" alt="vuex structure" src="https://user-images.githubusercontent.com/39546874/46335873-e886a400-c663-11e8-978d-047b99e0914c.png">



- - - -

## 언제 사용해야 하나?
* Vuex는 공유된 상태 관리를 처리하는데 유용하지만, 개념에 대한 이해와 시작하는 비용이 든다
* 앱이 단순하다면 간단한 글로벌 이벤트 버스만 있어도 된다
> 글로벌 이벤트 버스  
> => 비 부모-자식간 통신에서 사용  
> 비어있는 Vue 인스턴스를 중앙 이벤트 버스로 사용한다  
``` javascript
// 글로벌 버스 이벤트

var bus = new Vue();

// 컴포넌트 A의 메소드
bus.$emit('id-selected', 1);

// 컴포넌트 B의 created 훅
bus.$on('id-selected', function(id) { ... });
```
* 하지만 중대형 규모의 SPA를 구축하는 경우 Vue컴포넌트 외부의 상태를 보다 잘 처리할 수 있는 방법이 Vuex!

> Redux의 저자 Dan Abramov의 인용  
> Flux 라이브러리는 안경과 같다. 필요할 때 알아볼 수 있다  


- - - -

## Vuex 시작하기
* 모든 Vuex 애플리케이션에는 store가 있다

### store
* 기본적으로 애플리케이션 상태(state)를 가지고있는 컨테이너!
* Vuex store와 일반 전역 개체와 다른점
	1. Vuex store는 반응형 => Vue 컴포넌트는 상태를 검색할 때 store의 상태가 변경되면 효율적으로 대응하고 업데이트한다
	2. store의 state를 직접 변경할 수 없다 => 명시적인 commit 을 이용한 변경만 가능 (모든 상태에 대한 추적이 가능한 기록이 남는다)


### 예제
``` javascript
// store.js
const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment (state) {
      state.count++
    }
  }
});
```

``` javascript
// 다른 vue파일의 script
store.commit('increment');	// commit메소드로 상태 변경을 트리거한다
console.log(store.state.count);
```

* store.state.count를 직접 변경하는 대신 변이를 수행하는 이유는 명시적으로 추적을 하기 때문
* 모든 변이를 기록하고 상태 스냅샷을 저장하거나 시간 흐름에 따라 디버깅을 할 수 있는 도구를 제공

* 컴포넌트 안에서 저장소 상태를 사용하는 것은 단순히 계산된 속성 내에서 상태를 반환하는 것
* 변경을 트리거하는 것은 컴포넌트 메소드에서 변경을 커밋하는 것을 의미






