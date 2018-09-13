# Vue 스타일 가이드
## 우선순위 A 규칙: 필수 (에러 방지)
### 컴포넌트 이름에 합성어 사용
* 이렇게하면 모든 HTML 요소가 단일 단어이므로 기존 HTML 요소와 향후 HTML 요소와의 충돌을 방지 할 수 있습니다
* example
``` javascript
// 잘못된 예제
Vue.component('todo', {
  // ...
})
export default {
  name: 'Todo',
  // ...
}
```

``` javascript
// 올바른 예제
Vue.component('todo-item', {
  // ...
})
export default {
  name: 'TodoItem',
  // ...
}
```

### 컴포넌트 데이터
* 구성 요소 (즉, new Vue를 제외한 모든 위치)에서 data 속성을 사용할 때 값은 객체를 반환하는 함수 여야합니다.

* 데이터 값이 객체 인 경우 구성 요소의 모든 인스턴스에서 공유된다
* 그러나 우리는 사용자가 여러가지 목록을 유지하게 하면서 이 컴포넌트를 다시 사용하길 원한다
* 하지만 구성 요소의 모든 인스턴스가 동일한 데이터 객체를 참조하므로 한 목록의 제목을 변경하면 다른 모든 목록의 제목도 변경됩니다.

* 대신 각 구성 요소 인스턴스가 자체 데이터 만 관리하기를 원합니다. 이를 위해 각 인스턴스는 고유한 데이터 객체를 생성해야합니다. JavaScript에서는 함수에서 객체를 반환하여 수행 할 수 있습니다.

``` javascript
// 잘못된 예제
Vue.component('some-comp', {
  data: {
    foo: 'bar'
  }
})
export default {
  data: {
    foo: 'bar'
		// foo 값을 바꾸면 Vue컴포넌트로 생성한 모든 인스턴스의 foo값이 바뀐다!
  }
}
```

``` javascript
// 올바른 예제
Vue.component('some-comp', {
  data: function () {
    return {
      foo: 'bar'
    }
  }
})
// In a .vue file
export default {
  data () {
    return {
      foo: 'bar'
		// foo 값을 바꿔도 이 인스턴스의 foo만 바뀐다
    }
  }
}
// It's OK to use an object directly in a root
// Vue instance, since only a single instance
// will ever exist.
new Vue({
	// 단 하나의 Vue인스턴스만 생성되므로 data를 리턴하지 않아도 된다!
  data: {
    foo: 'bar'
  }
})
```


### Props 정의
* prop의 정의는 가능한 상세해야한다
* prop의 정의는 적어도 타입을 지정해야한다
* 장점
	* 이 API는 구성 요소의 API를 문서화하므로 구성 요소의 사용 방법을 쉽게 알 수 있습니다.
	* 개발 중에 Vue는 구성 요소의 타입이 잘못 지정된 prop이 제공되면 경고 메시지를 표시하여 오류의 잠재적 원인을 파악할 수 있도록 도와줍니다.
``` javascript
// 잘못된 예제
// This is only OK when prototyping
props: ['status']
```
``` javascript
// 올바른 예제
props: {
  status: String
}

// 더 좋은 예제
props: {
  status: {
    type: String,
    required: true,
    validator: function (value) {
      return [
        'syncing',
        'synced',
        'version-conflict',
        'error'
      ].indexOf(value) !== -1
    }
  }
}
```


### v-for에 키 지정
* v-for를 사용할 때는 항상 키를 지정해 주어야 한다
* 하위 트리의 내부 구성 요소 상태를 유지하기 위해서 구성 요소에 v-for의 키가 항상 필요합니다. 하지만 엘리먼트의 경우에도 애니메이션의 객체 불변성과 같은 예측 가능한 동작을 유지하는 것이 좋습니다.

* DOM을 업데이트 할 때 Vue는 가능한 가장 저렴한 DOM 변이를 수행하기 위해 렌더링을 최적화합니다. 즉, 첫 번째 할 일 요소를 삭제 한 다음 목록 끝에 다시 추가 할 수 있습니다.

* 문제는 DOM에 남아있는 요소를 삭제하지 않는 것이 중요하다는 것입니다. 예를 들어, <transition-group>을 사용하여 목록 정렬을 애니메이션으로 만들거나 렌더링 된 요소가 <input> 인 경우 포커스를 유지할 수 있습니다. 이 경우 각 항목에 고유 한 키 (예 : key = "todo.id")를 추가하면 Vue에게 예측 가능성이 높은 행동을 유도합니다.

* 우리의 경험에 비추어 볼 때 항상 고유 한 키를 추가하는 것이 더 좋으므로 귀사와 귀사 팀은 이러한 핵심 사례에 대해 걱정할 필요가 없습니다. 그런 다음 오브젝트 불변성이 필요하지 않은 희소하고 성능이 중요한 시나리오에서 의식적인 예외를 만들 수 있습니다.
``` javascript
// 잘못된 예제
<ul>
  <li v-for="todo in todos">
    {{ todo.text }}
  </li>
</ul>
```
``` javascript
// 올바른 예제
<ul>
  <li v-for="todo in todos":key="todo.id">
    {{ todo.text }}
  </li>
</ul>
```


### v-if와 v-for를 동시에 사용하면 안된다
* v-for와 같은 요소에서 v-if를 사용하지 마십시오.
* v-for와 v-if를 같이 사용할 경우
	* 목록의 항목을 필터링 해야 할 때` v-for = “user in users” v-if = "user.isActive"`. 이러한 경우 users를 필터링 된 배열(예 : activeUsers)을 반환하는 새로운 계산 된 속성으로 바꾼다
	* 숨겨진 목록을 렌더링하지 않을 때 `v-for = "user in users" v-if = "shouldShowUsers"`. 이러한 경우 v-if를 컨테이너 요소 (예 : ul, ol)로 이동합니다.


#### 배열의 항목을 필더링 해야 할 때 => 배열을 필더링 된 목록을 반환하는 새로운 계산된 속성으로 바꾼다 (필터링을 하고 for문을 실행한다)
* Vue가 지시문을 처리 할 때 v-for는 v-if보다 높은 우선 순위를 가지므로아래 템플릿은 그다음 템플릿과 비슷하다
``` html
// users마다 isActive를 실행
<ul>
  <li
    v-for="user in users"
    v-if="user.isActive"
    :key="user.id"
  >
    {{ user.name }}
  <li>
</ul>
```
Will be evaluated similar to:
``` javascript
this.users.map(function (user) {
  if (user.isActive) {
    return user.name
  }
})
```

* 따라서 사용자의 일부분만 렌더링하는 경우에도 활성 사용자 집합이 변경되었는지 여부에 관계없이 다시 렌더링 할 때마다 전체 목록을 반복해야한다.
* 그러므로 다음과 같이 계산 된 속성을 반복하는 것이 효율적!
``` javascript
computed: {
  activeUsers: function () {
    return this.users.filter(function (user) {
      return user.isActive
    })
  }
}
```
``` html
// users 중 activeUsers만을 뽑아서 반복한다
<ul>
  <li
    v-for="user in activeUsers"
    :key="user.id"
  >
    {{ user.name }}
  <li>
</ul>
```

* 장점
	* 필터링 된 목록은 사용자 배열에 관련 변경 사항이있는 경우에만 다시 평가되므로 필터링이 훨씬 효율적
	* v-for = "user in activeUsers”를 사용하여 렌더링하는 동안 활성 사용자에 대해서만 반복 작업을 수행하므로 렌더링이 훨씬 효율적
	* 이제 로직이 프리젠테이션 레이어와 분리되어 유지 관리 (로직 변경 / 확장)가 훨씬 수월하다

#### 숨겨진 목록을 표시하지 않을 때 => v-if를 컨테이너 요소로 이동
* v-if의 이동을 통해 비슷한 이점을 얻을 수 있다
``` html
// v-for와 v-if가 함께 있는 코드
<ul>
  <li
    v-for="user in users"
    v-if="shouldShowUsers"
    :key="user.id"
  >
    {{ user.name }}
  <li>
</ul>
```
=> 변경
``` html
// v-if를 컨테이너 요소로 이동한 코드
<ul v-if="shouldShowUsers">
  <li
    v-for="user in users"
    :key="user.id"
  >
    {{ user.name }}
  <li>
</ul>
```
* v-if를 컨테이너 요소로 이동하면 shouldShowUsers가 모든 users에 대해 더 이상 확인되지 않는다. 대신, showUser인지 확인하고 shouldShowUsers가 false 인 경우 v-for을 실행하지 않는다.

``` html
// 잘못된 예제
<ul>
  <li
    v-for="user in users"
    v-if="user.isActive"
    :key="user.id"
  >
    {{ user.name }}
  <li>
</ul>
<ul>
  <li
    v-for="user in users"
    v-if="shouldShowUsers"
    :key="user.id"
  >
    {{ user.name }}
  <li>
</ul>
```
``` html
// 올바른 예제
<ul>
  <li
    v-for="user in activeUsers"
    :key="user.id"
  >
    {{ user.name }}
  <li>
</ul>
<ul v-if="shouldShowUsers">
  <li
    v-for="user in users"
    :key="user.id"
  >
    {{ user.name }}
  <li>
</ul>
```

### 구성 요소 스타일 범위 지정 (Component style scoping)
* 응용 프로그램의 경우 최상위 수준의 App 컴포넌트와 레이아웃 컴포넌트의 스타일은 전역 일 수 있지만 다른 모든 컴포넌트는 항상 범위를 지정해야한다.
* 이것은 단일 파일 컴포넌트에만 관련이 있습니다. scoped 속성을 사용할 필요는 없다. 범위 지정은 CSS 모듈, BEM과 같은 클래스 기반 전략 또는 다른 라이브러리 / 규칙을 통해 수행 할 수 있다.
* 그러나 컴포넌트 라이브러리는 범위 지정 특성을 사용하는 대신 클래스 기반 전략을 선호해야한다.
* 이렇게하면 내부 스타일을 재정의하는 것이 더 쉽고, 클래스 이름은 너무 높은 특이성을 갖지 않지만 충돌을 일으키지는 않습니다.

* 대규모 프로젝트를 개발하거나 다른 개발자와 협력하거나 제 3 자 HTML / CSS (예 : Auth0)를 포함하는 경우 일관된 범위 지정을 사용하면 스타일이 의도한 컴포넌트에만 적용된다.
* 범위가 지정된 속성 외에도 고유 한 클래스 이름을 사용하면 타사 CSS가 자신의 HTML에 적용되지 않도록 할 수 있다. 예를 들어 많은 프로젝트는 버튼, btn 또는 아이콘 클래스 이름을 사용하므로 BEM과 같은 전략을 사용하지 않더라도 특정 애플리케이션 및 / 또는 구성 요소 별 접두어 (예 : ButtonClose-icon)를 추가하면 일부 보호 기능을 제공 할 수 있다.

``` html
// 잘못된 예제
<template>
  <button class="btn btn-close">X</button>
</template>

<style>
.btn-close {
  background-color: red;
}
</style>
```
``` html
// 올바른 예제


// scoped attribute 사용
<template>
  <button class="button button-close">X</button>
</template>

<!-- Using the `scoped` attribute -->
<style scoped>
.button {
  border: none;
  border-radius: 2px;
}
.button-close {
  background-color: red;
}
</style>


// css module 사용
<template>
  <button :class="[$style.button, $style.buttonClose]">X</button>
</template>

<!-- Using CSS modules -->
<style module>
.button {
  border: none;
  border-radius: 2px;
}
.buttonClose {
  background-color: red;
}
</style>



// BEM conversion 사용
<template>
  <button class="c-Button c-Button--close">X</button>
</template>

<!-- Using the BEM convention -->
<style>
.c-Button {
  border: none;
  border-radius: 2px;
}
.c-Button--close {
  background-color: red;
}
</style>
```



### Private 속성 이름
* 플러그인, 믹스 인 (mixin) 등에서는 항상 맞춤 공개 속성에 $ _ 접두사를 사용합니다. 그런 다음 다른 작성자가 코드와 충돌하지 않게하려면 scope이름 (예 : $_yourPluginName_)도 포함합니다.

* Vue는 _ 접두사를 사용하여 자체 비공개 속성을 정의하므로 동일한 접두사 (예 : _update)를 사용하면 인스턴스 속성을 덮어 쓸 위험이 있다. Vue가 현재 특정 속성 이름을 사용하고 있지 않더라도 차후 버전에서 충돌이 발생하지 않을 것이라는 보장은 없다.
* $ 접두사의 경우 Vue 생태계의 목적은 사용자에게 노출되는 특수 인스턴스 속성이므로 개인 속성에 사용하면 적절하지 않다.
* 대신 Vue와의 충돌이없는 사용자 정의 개인 속성에 대한 규칙으로 두 접두사를 $_ 결합하는 것이 좋다.

``` javascript
// 잘못된 예제
var myGreatMixin = {
  // ...
  methods: {
    update: function () {
      // ...
    }
  }
}
var myGreatMixin = {
  // ...
  methods: {
    _update: function () {
      // ...
    }
  }
}
var myGreatMixin = {
  // ...
  methods: {
    $update: function () {
      // ...
    }
  }
}
var myGreatMixin = {
  // ...
  methods: {
    $_update: function () {
      // ...
    }
  }
}
```
``` javascript
// 올바른 예제
var myGreatMixin = {
  // ...
  methods: {
    $_myGreatMixin_update: function () {
      // ...
    }
  }
}
```


- - - -

## 우선순위 B규칙: 가독성 향상을 위함
### 컴포넌트 파일
* 파일을 연결하기 위해 빌드 시스템을 사용할 때마다, 각 컴포넌트는 자체 파일에 있어야 한다
* 이렇게하면 컴포넌트를 편집하거나 사용 방법을 검토해야 할 때 컴포넌트를 더 빨리 찾을 수 있다

``` javascript
// 잘못된 예제
Vue.component('TodoList', {
  // ...
})

Vue.component('TodoItem', {
  // ...
})
```
```
// 올바른 예제
// 컴포넌트 폴더 내에 컴포넌트 파일들이 들어있어야 한다
components/
|- TodoList.js
|- TodoItem.js

components/
|- TodoList.vue
|- TodoItem.vue
```

### 싱글 파일 컴포넌트 이름 규칙 지정(casing)
* 싱글 파일 컴포넌트의 파일 이름은 항상 PascalCase 이거나 kebab-case이여야 한다 (kebab-case를 사용한다!)
> **<표기법>**  
> > camelCase  
> 	각 단어의 첫문자를 대문자로 표기하고 붙여쓰되, 맨 처음 문자는 소문자로 표기  
> > PascalCase  
> 	첫 단어를 대문자로 표시하는 표기법  
> > kebab-case  
> 	하이픈(-)으로 단어를 연결하는 표기법  
> > snake_case  
> 	단어를 밑줄문자(_)로 구분하는 표기법  
* PascalCase는 JS, JSX 및 템플릿의 컴포넌트를 참조하는 방식과 일관성이 있으므로 code editor에서 자동 완성과 함께 가장 잘 작동한다
* 대소 문자를 혼용하는 파일명은 대소 문자를 구별하지 않는 파일시스템에 문제를 야기할 수 있기 때문에 code editor는 kebab-case도 완벽하게 받아들일 수 있다


### 베이스 컴포넌트 이름
* 앱 별 스타일 및 규칙을 적용하는 기본 컴포넌트 (프리젠 테이션, dumb 또는 순수 컴포넌트)는 모두 Base, App 또는 V와 같은 특정 접두사로 시작해야한다

* 아래 컴포넌트들은 일관된 스타일 및 동작을 위한 기반을 마련한다
	* HTML요소
	* 기타 기본 접두사가 붙은 컴포넌트
	* 타사 UI 컴포넌트
* 하지만 그들은 (Vuex 상점 등의) 글로벌 state를 절대 포함하지 않는다
* 특정 용도로 엘리먼트(ex base-icon)가 존재하지 않는 한, 해당 이름은 종종 래핑하는 요소의 이름(ex base-button, base-table)을 포함한다

* 장점
	* 컴포넌트의 이름은 항상 여러 단어여야 하므로 이 규칙을 사용하면 간단한 구성 요소 래퍼(ex MyButton, VueButton)에 대한 임의의 접두사를 선택할 필요가 없다
	* 이러한 컴포넌트는 자주 사용되므로 모든 곳으로 가져오는 대신 전역으로 만들면 된다(접두사는 Webpack에서 가능하다)
> webpack  
> 두 그룹의 명세를 모두 지원하는 JavaScript 모듈화 도구   
``` javascript
var requireComponent = require.context("./src", true, /^Base[A-Z]/)
requireComponent.keys().forEach(function (fileName) {
  var baseComponentConfig = requireComponent(fileName)
  baseComponentConfig = baseComponentConfig.default || baseComponentConfig
  var baseComponentName = baseComponentConfig.name || (
    fileName
      .replace(/^.+\//, '')
      .replace(/\.\w+$/, '')
  )
  Vue.component(baseComponentName, baseComponentConfig)
})
```


```
// 잘못된 예제
components/
|- MyButton.vue
|- VueTable.vue
|- Icon.vue

// 올바른 예제
components/
|- BaseButton.vue
|- BaseTable.vue
|- BaseIcon.vue

components/
|- AppButton.vue
|- AppTable.vue
|- AppIcon.vue

components/
|- VButton.vue
|- VTable.vue
|- VIcon.vue
```



### 싱글 인스턴스 컴포넌트 이름
* 하나의 활성 인스턴스만을 가져야하는 컴포넌트는 하나만 있을 수 있음을 나타내기 위해 컴포넌트파일 이름은 The 접두어로 시작해야한다

* 이는 컴포넌트가 단일 페이지에서만 사용된다는 의미는 아니지만 페이지 당 한 번만 사용된다. 이러한 컴포넌트는 응용 프로그램 내에서 컨텍스트가 아닌 응용 프로그램에만 해당되므로 props를 허용하지 않는다. 만약 props를 추가해야 할 필요성을 느낀다면 실제로 페이지 당 한 번만 사용되는 재사용 가능한 구성 요소라는 것을 알 수 있다.
```
// 잘못된 예제
components/
|- Heading.vue
|- MySidebar.vue

// 올바른 예제
components/
|- TheHeading.vue
|- TheSidebar.vue
```


### 강한 연관성을 가진 컴포넌트 이름
* 부모와 밀접하게 연결된 하위 컴포넌트에는 상위 구성 요소 이름이 접두사로 포함되어야 한다

* 컴포넌트가 단일 상위 구성 요소의 컨텍스트에서만 의미가있는 경우 해당 관계가 그 이름에 분명해야한다. editor는 일반적으로 파일을 사전 순으로 정리하기 때문에 이러한 관련 파일을 서로 나란히 유지한다.

* 부모 컴포넌트의 이름을 가진 디렉토리에 자식 컴포넌트를 중첩하여 이 문제를 해결할 수 있지만 권장하지 않는다
* 비슷한 이름을 가진 많은 파일은 code ediotr에서 신속한 파일 전환을 어렵게 만든다. 많은 하위 디렉토리가 중첩되어있어 편집기의 사이드 바에서 컴포넌트를 탐색하는데 걸리는 시간이 길어진다
```
// 접두사 대신
// 디렉토리를 이용한 방법 (컴포넌트를 탐색하는데 시간이 오래걸림)
components/
|- TodoList/
   |- Item/
      |- index.vue
      |- Button.vue
   |- index.vue

// 또는

components/
|- TodoList/
   |- Item/
      |- Button.vue
   |- Item.vue
|- TodoList.vue
```
```
// 잘못된 예제
components/
|- TodoList.vue
|- TodoItem.vue
|- TodoButton.vue
components/
|- SearchSidebar.vue
|- NavigationForSearchSidebar.vue

// 올바른 예제
components/
|- TodoList.vue
|- TodoListItem.vue
|- TodoListItemButton.vue
components/
|- SearchSidebar.vue
|- SearchSidebarNavigation.vue
```



### 컴포넌트 이름의 단어 순서 정렬
* 컴포넌트 이름은 최상위 (보통 가장 일반적인) 단어로 시작하고 설명적인 수정 단어로 끝나야한다
* 왜 덜 자연스러운 언어를 사용하도록 구성 요소 이름을 강요할까?
	* 영어는 형용사 및 기타 설명자가 일반적으로 명사 앞에 표시되지만 예외는 커넥터 단어가 필요합니다 (Coffee _with_ milk, Soup _of the_ day, Visitor _to the_ museum)
	* 이런 커넥터 단어를 컴포넌트 이름에 포함할 수 있지만 순서는 여전히 중요하다
	* 또한 "최고 수준"으로 간주되는 것은 앱에 맥락을 나타낸다
```
// 예시
// 검색 양식이 있는 앱
// 검색과 관련된 구성 요소를 확인하는 것은 매우 어렵다
components/
|- ClearSearchButton.vue
|- ExcludeFromSearchInput.vue
|- LaunchOnStartupCheckbox.vue
|- RunSearchButton.vue
|- SearchInput.vue
|- TermsCheckbox.vue

// 규칙에 따라 구성 요소의 이름을 정한 앱
// editor는 일반적으로 파일을 사전 순으로 정리하기 때문에 구성 요소 간의 모든 중요한 관계를 한눈에 알 수 있다
components/
|- SearchButtonClear.vue
|- SearchButtonRun.vue
|- SearchInputExcludeGlob.vue
|- SearchInputQuery.vue
|- SettingsCheckboxLaunchOnStartup.vue
|- SettingsCheckboxTerms.vue
```

* 이 문제를 다르게 해결하고 "검색"디렉토리 아래에 모든 검색 구성 요소를 중첩 한 다음 "설정"디렉토리 아래에 모든 설정 구성 요소를 중첩시켜야 할 수 있다
* 다음과 같은 이유로 매우 큰 앱 (예 : 100 개 이상의 구성 요소)에서만 이 방법을 고려하는 것이 좋다
	* 	일반적으로 중첩 된 하위 디렉토리를 탐색하는 데는 단일 구성 요소 디렉토리를 스크롤하는 것보다 시간이 더 걸린다
	* 이름 충돌로 인해 코드 편집기에서 특정 구성 요소로 빠르게 이동하기가 더 어려워진다
	* 찾기 및 바꾸기가 이동 된 구성 요소에 대한 상대 참조를 업데이트하기에 충분하지 않기 때문에 리팩토링이 어려워진다
```
// 잘못된 예제
components/
|- ClearSearchButton.vue
|- ExcludeFromSearchInput.vue
|- LaunchOnStartupCheckbox.vue
|- RunSearchButton.vue
|- SearchInput.vue
|- TermsCheckbox.vue

// 올바른 예제
components/
|- SearchButtonClear.vue
|- SearchButtonRun.vue
|- SearchInputQuery.vue
|- SearchInputExcludeGlob.vue
|- SettingsCheckboxTerms.vue
|- SettingsCheckboxLaunchOnStartup.vue
```


### 셀프 클로징 컴포넌트
* 내용이 없는 컴포넌트는 단일 파일 컴포넌트, 문자열 템플릿, JSX에서는 self-closing이어야 하지만, DOM 템플릿에서는 절대로 그러면 안된다
* self-close 컴포넌트는 내용이 없으며 내용이 없도록 통신한다. 책의 빈 페이지와 "이 페이지는 고의적으로 비워 두었습니다."라는 레이블이 붙은 페이지의 차이이다. 코드는 불필요한 닫는 태그가 없어도 더 깨끗하다.

* 그러나 HTML은 커스텀 엘리먼트의 self-closing을 허용하지 않는다 - 단지 공식 "void"엘리먼트 만 허용한다. 그렇기 때문에 Vue의 템플릿 컴파일러가 DOM보다 먼저 템플릿에 도달 한 다음 DOM 사양 준수 HTML을 제공해야만 전략이 가능하다다.

``` html
// 잘못된 예제
<!-- In single-file components, string templates, and JSX -->
<MyComponent></MyComponent>
<!-- In DOM templates -->
<my-component/>

// 올바른 예제
<!-- In single-file components, string templates, and JSX -->
<MyComponent/>
<!-- In DOM templates -->
<my-component></my-component>
```


### 템플릿에서 컴포넌트 이름 규칙 지정(casing)
* 대부분의 프로젝트에서 컴포넌트 이름은 단일 파일 컴포넌트와 string template에서 항상 PascalCase 여야하지만 DOM template에서는 kebab-case 여야한다
* PascalCase는 케밥 케이스에 비해 몇 가지 장점이 있다
	* 편집자는 PascalCase가 JavaScript에서도 사용되기 때문에 템플릿의 구성 요소 이름을 자동 완성 할 수 있다.
	* <MyComponent>는 <my-component>보다 한 단어로 된 HTML 요소와 시각적으로 구별된다. 하나의 문자 (하이픈)가 아니라 두 문자의 차이 (대문자)가 있기 때문
	* template에서 웹 컴포넌트와 같은 비 VUE 사용자 정의 요소를 사용하는 경우 PascalCase는 Vue 컴포넌트가 확실하게 표시되도록한다

* HTML의 경우 대소 문자를 구분하지 않기 때문에 DOM 템플릿은 여전히 ​​kebab-case를 사용해야한다
* 또한 이미 kebab-case에 집중적으로 투자 한 경우 HTML 규칙과 일관성을 유지하고 모든 프로젝트에서 동일한 케이스를 사용할 수 있다는 사실이 위에 나열된 이점보다 더 중요 할 수 있다. 이러한 경우 kebab-case를 사방에 사용하는 것도 허용된다


``` html
// 잘못된 예제
<!-- In single-file components and string templates -->
<mycomponent/>
<!-- In single-file components and string templates -->
<myComponent/>
<!-- In DOM templates -->
<MyComponent></MyComponent>

// 올바른 예제
<!-- In single-file components and string templates -->
<MyComponent/>
<!-- In DOM templates -->
<my-component></my-component>

	// OR

<!-- Everywhere -->
<my-component></my-component>
```



### JS/JSX에서 컴포넌트 이름 규칙 지정(casing)
* JS / JSX의 컴포넌트 이름은 항상 PascalCase 여야한다. 
* Vue.component를 통한 전역 구성 요소 등록 만 사용하는 더 간단한 응용 프로그램의 경우 문자열 안에 케밥 케이스가 있을 수 있다.

* JavaScript에서 PascalCase는 클래스와 프로토 타입 생성자에 대한 규칙이다. (기본적으로 별개의 인스턴스를 가질 수 있는 것들에 대한 규칙). Vue 컴포넌트에는 인스턴스가 있으므로 PascalCase도 사용하는 것이 좋다. JSX 및 템플릿 내에서 PascalCase를 사용하면 코드 독자가 구성 요소와 HTML 요소를 더 쉽게 구별 할 수 있다.
* 그러나 Vue.component를 통해 전역 컴포넌트 정의만 사용하는 응용 프로그램의 경우 kebab-case를 대신 사용하는 것이 좋다. 이유는 다음과 같다.
	* 	JavaScript에서 전역 컴포넌트가 참조되는 경우는 드물기 때문에 JavaScript 규칙을 따르는 것이 바람직하지 않다.
	* 이러한 응용 프로그램에는 항상 kebab-case를 사용해야하는 많은 in-DOM 템플릿이 포함되어 있다.

``` javascript
// 잘못된 예제
Vue.component('myComponent', {
  // ...
})
import myComponent from './MyComponent.vue'
export default {
  name: 'myComponent',
  // ...
}
export default {
  name: 'my-component',
  // ...
}

// 올바른 예제
Vue.component('MyComponent', {
  // ...
})
Vue.component('my-component', {
  // ...
})
import MyComponent from './MyComponent.vue'
export default {
  name: 'MyComponent',
  // ...
}
```


### 전체 이름 컴포넌트 이름
* 컴포넌트 이름은 약어보다 완전한 단어를 선호해야한다
* editor의 자동 완성 기능은 더 긴 이름을 쓰는 데 드는 비용을 매우 낮추지만 제공하는 명확성은 매우 중요하다. 흔치 않은 약어는 항상 피해야한다
```
// 잘못된 예제
components/
|- SdSettings.vue
|- UProfOpts.vue

// 올바른 예제
components/
|- StudentDashboardSettings.vue
|- UserProfileOptions.vue
```




### Prop 이름 규칙 지정(casing)

* prop의 이름은 선언하는 동안 항상 camelCase를 사용해야하지만 템플릿과 JSX에서는 kebab-case를 사용해야한다.
* 단순히 각 언어의 규칙을 따르는 것. JavaScript 내에서는 camelCase가 더 자연스럽고, HTML 내에서는 케밥 케이스가 사용된다.

```
// 잘못된 예제
	// script
props: {
  'greeting-text': String
}
	// template
<WelcomeMessage greetingText="hi"/>

// 올바른 예제
	// script
props: {
  greetingText: String
}
	// template
<WelcomeMessage greeting-text="hi"/>
```



### 다중 속성 엘리먼트

* attribute가 여러개인 element는 여러줄에 걸쳐있어야하며, 한줄에 하나의 attribute가 있어야한다
* JavaScript에서는 여러 줄에 걸쳐 여러 attribute를 갖는 객체를 분할하는 것이 훨씬 쉽기 때문에 널리 사용되는 것으로 간주된다. 템플릿과 JSX도 똑같은 배려가 필요하다
``` html
// 잘못된 예제
<img src="https://vuejs.org/images/logo.png" alt="Vue Logo">
<MyComponent foo="a" bar="b" baz="c"/>

// 올바른 예제
<img
  src="https://vuejs.org/images/logo.png"
  alt="Vue Logo"
>
<MyComponent
  foo="a"
  bar="b"
  baz="c"
/>
```



### 템플릿에서 단순한 표현식
* 컴포넌트 템플릿에는 간단한 표현식만 포함되어야하고 복잡한 식은 computed 내에서 프로퍼티나 메소드로 정의되어서 사용되어야 한다

```
// 잘못된 예제
{{
  fullName.split(' ').map(function (word) {
    return word[0].toUpperCase() + word.slice(1)
  }).join(' ')
}}

// 올바른 예제
<!-- In a template -->
{{ normalizedFullName }}

	// The complex expression has been moved to a computed property
computed: {
  normalizedFullName: function () {
    return this.fullName.split(' ').map(function (word) {
      return word[0].toUpperCase() + word.slice(1)
    }).join(' ')
  }
}

```



### 단순한 계산된 속성
* 복잡한 계산된 속성은 가능한한 더 간단한 속성으로 분할되어야 한다
* 단순하고 잘 이름지어진 계산된 속성의 특징
	* 테스트하기 쉽다 => 계산된 각 속성에 아주 간단한 표현식만 포함되어있을때 의존성이 거의 없으면 올바르게 계산된 테스트를 작성하는 것이 더 쉽다
	* 더 읽기 쉽다 => 계산 된 속성을 단순화하면 재사용하지 않아도 각 값에 설명이 포함된 이름을 제공 할 수 있다. 이것은 다른 개발자 (그리고 미래의 개발자)가 관심있는 코드에 집중하고 무슨 일이 일어나고 있는지 쉽게 파악할 수있게 해준다.
	* 변화하는 요구 사항에보다 적응 가능 => 이름을 지정할 수 있는 모든 값이 뷰에 유용할 수 있다. 예를 들어 사용자에게 저장 한 금액을 알려주는 메시지를 표시하도록 결정할 수 있다. 또한 판매 세를 계산하기로 결정할 수도 있지만, 최종 가격의 일부가 아닌 별도로 판매 세를 표시 할 수도 있다.
	* 작고 집중된 계산된 속성은 정보가 사용되는 방법에 대한 가정이 적기 때문에 요구 사항이 변경 될 때 필요한 리팩토링이 적다.
	
``` javascript
// 잘못된 예제
	// price하나로 최종 값을 계산하는 것보다는 더 세분화해서 나누는 것이 좋다
computed: {
  price: function () {
    var basePrice = this.manufactureCost / (1 - this.profitMargin)
    return (
      basePrice -
      basePrice * (this.discountPercent || 0)
    )
  }
}

// 올바른 예제
computed: {
  basePrice: function () {
    return this.manufactureCost / (1 - this.profitMargin)
  },
  discount: function () {
    return this.basePrice * (this.discountPercent || 0)
  },
  finalPrice: function () {
    return this.basePrice - this.discount
  }
}
``` 


### 속성 값에 따옴표
* 비어 있지 않은 HTML attribute 값은 항상 따옴표(단일 또는 이중) 안에 있어야한다 (JS에서 사용되지 않는 경우에도).
* 공백이 없는 attribute 값은 HTML에서 따옴표를 사용할 필요가 없지만 공백을 피하면서 속성 값을 쉽게 읽을 수 없게 만든다.
``` html
// 잘못된 예제
<input type=text>
<AppSidebar :style={width:sidebarWidth+'px'}>

// 올바른 예제
<input type="text">
<AppSidebar :style="{ width: sidebarWidth + 'px' }">
```

### 축약형 디렉티브
* 디렉티브 약어(v-bind은 : , v-on은 @)는 항상 사용되거나 항상 사용되지 않아야한다
``` html
// 잘못된 예제
<input
  v-bind:value="newTodoText"
  :placeholder="newTodoInstructions"
>
<input
  v-on:input="onInput"
  @focus="onFocus"
>

// 올바른 예제
<input
  :value="newTodoText"
  :placeholder="newTodoInstructions"
>
<input
  v-bind:value="newTodoText"
  v-bind:placeholder="newTodoInstructions"
>
<input
  @input="onInput"
  @focus="onFocus"
>
<input
  v-on:input="onInput"
  v-on:focus="onFocus"
>
```















