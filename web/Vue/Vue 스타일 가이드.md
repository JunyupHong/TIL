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



