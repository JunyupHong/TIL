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
* 부모는 props로 자식에게 데이터를 전달하고, 자식은 events를 부모에게 보낸다($emit을 이용해 부모에게 특정 트리거를 보낼 수도 있다)



- - - -


## Props
* props는 상위 컴포넌트의 정보를 전달하기 위한 사용자 지정 특성


### props로 데이터 전달
* 모든 컴포넌트 인스턴스에서는  자체 격리된 범위가 존재 (즉, 하위 컴포넌트의 템플릿에서 상위 데이터를 직접 참조할 수 없다)
* data는 props옵션을 사용하여 하위 컴포넌트로 전달 될 수 있다
* 하위 컴포넌트는 props 옵션을 사용해 수신할 것으로 기대되는 props를 명시적으로 선언해야한다
``` javascript
Vue.component('test-component', {
	props: ['message'],
	template: '<h1>{{ message }}</h1>'
});
```


### camelCase vs kebab-case
* HTML 속성은 대소문자를 구분하지 않으므로 템플릿을 사용할 때 camelCase prop 이름에 해당하는 kebab-case를 사용해야함
``` javascript
props: ['myMessage']

// 템플릿에서 사용
<component :my-message="메세지"></component>
```

### 동적 props
* 정규 속성 표현식에 바인딩 하는것과 비슷하게 v-bind를 사용하여 부모의 데이터에 props를 동적으로 바인딩 할 수 있다
* => 데이터가 상위에서 업데이트 될 때마다 하위 데이터로도 전달된다
``` html
// template
	// v-bind를 이용한 동적 props
<component :my-message="msg"></component>

// script
data() {
	return { mes: '메세지' };
}
```


* 객체의 모든 속성을 props로 전달하려면, 인자없이 v-bind를 사용
``` html
// script
data() {
	return{
		todo: {
			text: 'todoText',
			isComplete: false
		}
	};
}

// template
<component v-bind="todo"></component>
	// 같다
<component v-bind:text="todo.text" v-bind:is-complete="todo.isComplete"></component>
```

### 리터럴 vs 동적
``` html
// 리터럴 prop 이기 때문에 문자열 "1"이 전달
<component numberProp="1"></component>

// v-bind를 사용해 JavaScript 표현식으로 표현 -> 숫자 1이 전달
<component :numberProp="1"></component>
```

### 단방향 데이터 흐름
* 모든 props는 하위 속성과 상위 속성 사이의 단방향 바인딩을 형성
	* => 상위 속성이 업데이트 되면 하위로 흐른다 (반대는 불가)
	* => 하위 컴포넌트가 실수로 부모의 상태를 변경하여 앱의 데이터 흐름을 추런하기 어렵게 만드는 것을 방지할 수 있다
* 하위 컴포넌트에서 props를 변경시켜야 하는경우
	1. prop은 초기 값을 전달 하는데만 사용하고 하위 컴포넌트는 이후에 이것을 로컬 데이터 속성으로 사용
	* 해결 => prop의 초기 값을 초기 값으로 사용하는 로컬 데이터 속성을 정의
``` javascript
props: ['initialData'],
data() {
	return{
		initData: this.initialData
	};
}
```

	2. prop은 변경 되어야 할 원시 값으로 전달
	* 해결 => prop 값으로부터 계산된 속성을 정의
``` javascript
props: ['size'],
computed: {
	normalizedSize: function () {
		return this.size.trim().toLowerCase();
	}
}
```


> 자바 스크립트의 객체와 배열은 참조로 전달(call by reference)되므로 prop이 배열이나 객체인 경우 하위 객체 또는 배열 자체를 부모 상태로 변경하면 부모 상태에 영향을 준다  



### prop 검증
* 컴포넌트가 prop에 대한 요구사항을 지정할 수 있다
* 요구사항이 충족되지 않으면 Vue에서 경고를 보냄
* props를 문자열 배열로 정의하는 대신 유효성 검사 요구사항이 있는 객체를 사용
``` javascript
props: {
		// propA의 타입은 Number 이어야함
	propA: Number,

		// propB의 타입은 String 또는 Number
	propB: [String, Number],

		// propC의 타입은 String이고 propC는 반드시 필요
	propC: {
		type: String,
		require: true
	},

		// propD의 타입은 Number, 바인딩되지않으면 10의 기본값을 가짐(반드시 필요하진 않음)
	propD: {
		type: Number,
		default: 10
	},

		// propE의 타입은 Object
		// 객체와 배열의 기본값은 팩토리함수에서 리턴되어야함
	propE: {
		type: Object,
		default: function() {
			return { message: 'msg' };
		}
	},

		// 사용자 정의 유효성 검사 가능
	propF: {
		validator: function(value) {
			return value > 10
		}
	}
}
```
* type은 다음 네이티브 생성자 중 하나를 사용할 수 있다
	* String
	* Number
	* Boolean
	* Function
	* Object
	* Array
	* Symbol

* type은 커스텀 생성자가 될 수 있고, assertion은 instance 체크로 만들어질 것이다
* props 검증이 실패하면 Vue는 콘솔에서 경고를 출력
* props는 컴포넌트 인스턴스가 생성되기 전에 검증되기 때문에 default나 validator 함수 내에서 data, computed 또는 methods와 같은 인스턴스 속성을 사용할 수 없다

- - - -

## Props가 아닌 속성
* 컴포넌트로 전달되지만 해당 props는 정의되지 않은 속성
* 명시적으로 정의된 props는 하위 컴포넌트에 정보를 전달하는데 적절하지만 컴포넌트 라이브러리를 만드는 경우 컴포넌트가 사용될 수있는 상황을 항상 예측할 수는 없다. => 컴포넌트가 컴포넌트의 루트 요소에 추가되는 임의의 속성을 허용해야하는 이유

* 예시
	* input에 data-3d-date-picker 속성을 요구하는 부트스트랩 플러그인으로  써드파티 bs-date-input 컴포넌트를 사용하고 있다 이때 속성을 인스턴스에 추가할 수 있다
	* 또한 data-3d-date-picker=“true” 속성은 bs-date-input의 루트 엘리먼트에 자동으로 추가된다
``` html
<bs-date-input data-3d-date-picker="true"></bs-date-input>
```


### 존재하는 속성 교체/병합
* 대부분의 속성의 경우 컴포넌트에 제공된 값은 컴포넌트에서 설정된 값을 대체한다 (제공된 값이 이미 설정된 값을 덮어씀)
* 하지만 class와 style 속성은 대체하는 것이 아니라 합쳐진다

* 예시
	* bs-date-input의 템플릿
``` html
// bs-date-input의 템플릿
<input type="date" class="form-control">
```

	* 이때 date-picker 플러그인의 테마를 추가하려면 특정 클래스를 추가해야 할 수도 있다
``` html
// bs-date-input을 사용하는 곳
	// date-picker-theme-dark 를 
<bs-date-input data-3d-date-picker="true" class="date-picker-theme-dark"></bs-date-input>
```

	* 이 경우 class에 대한 두개의 서로 다른 값이 정의된다
	* =>템플릿 컴포넌트에 설정된 form-control
	* => date-picker-theme-dark 는 부모에 의해 컴포넌트로 전달된다
	* 대부분의 속성의 경우 컴포넌트에 제공된 값은 컴포넌트에서 설정된 값을 대체한다 (제공된 값이 이미 설정된 값을 덮어씀)
	* 하지만 class와 style 속성은 대체하는 것이 아니라 합쳐진다 => form-control와 date-picker-theme-dark 클래스 두개가 합쳐진다


### v-on을 이용한 사용자 지정 이벤트
* 부모가 prop을 사용하여 자식에게 데이터를 전달할 수 있다
* 하지만 자식에서 문제가 발생했을때 부모에게 알리는 것은? => Vue의 사용자 정의 이벤트 시스템을 이용해 부모에게 알린다
* 모든 Vue 인스턴스는 이벤트 인터페이스를 구현한다
	* $on(eventName) => 이벤트 감지
	* $emit(eventName) => 이벤트 트리거

> Vue의 이벤트 시스템은 브라우저의 EventTarget API와 별개  
> 비슷하게 작동하지만 $on, $emit은 addEventListener, dispatchEvent의 별칭이 아니다!  


* 부모 컴포넌트는 자식 컴포넌트가 사용되는 템플릿에서 직접 v-on을 사용하여 자식 컴포넌트에서 보내진 이벤트를 청취할 수 있다

> $on은 자식에서 호출한 이벤트는 감지하지 않는다  
> 즉, v-on을 템플릿에 반드시 지정해야 한다  

* 예제
``` html
<div id="counter-event-example">
  <p>{{ total }}</p>
	// button-counter에서 트리거한 increment를 v-on을 이용해 감지한다
  <button-counter v-on:increment="incrementTotal"></button-counter>
  <button-counter v-on:increment="incrementTotal"></button-counter>
</div>
```
``` javascript
// button-counter 컴포넌트
Vue.component('button-counter', {
  template: '<button v-on:click="incrementCounter">{{ counter }}</button>',
  data: function () {
    return {
      counter: 0
    }
  },
  methods: {
    incrementCounter: function () {
      this.counter += 1
		// $emit을 이용해 부모에게 increment 이벤트를 트리거해준다
      this.$emit('increment')
    }
  },
})

new Vue({
  el: '#counter-event-example',
  data: {
    total: 0
  },
  methods: {
    incrementTotal: function () {
      this.total += 1
    }
  }
})
```

	* 하위 컴포넌트가 외부에서 발생하는것과 완전히 분리된다

#### 컴포넌트에 네이티브 이벤트 바인딩
* 컴포넌트의 루트 엘리먼트에서 네이티브 이벤트를 수신하려는 경우가 있을 수 있다 => 이러한 경우 v-on 에 .native 수식자를 사용할 수 있다
``` html
<my-component v-on:click.native="doTheThing"></my-component>
```


### .sync 수식어
* 2.3.0 버전에 추가
* 일부 경우 **양방향 바인딩**이 필요
* 자식 컴포넌트가 .sync를 가지는 속성을 변경하면 값의 변경이 부모에 반영된다
* 편리하지만 단방향 데이터 흐름이 아니기 때문에 장기적으로 유지보수에 문제가 생김 => 자식 속성을 변경하는 코드는 부모의 상태에 영향을 미침

* 부모의 상태에 영향을 미치는 코드를 더욱 일관적이고 명백하게 만들어야함

``` html
<comp :foo.sync="bar"></comp>
```
	* 위의 코드와 같은 코드
``` html
// bar 가 바뀌면 v-on을 이용해 트리거를 받아 이벤트를 실행한다
<comp :foo="bar" @update:foo="val => bar = val"></comp>
```
``` javascript
// bar가 바뀔때 컴포넌트에서 부모로 명시적으로 트리거를 보내야한다
this.$emit('update:foo', newValue)
```



### 사용자 정의 이벤트를 사용하여 폼 입력 컴포넌트 만들기
* 사용자 정의 이벤트는 v-model에서 작동하는 사용자 정의 입력을 만드는데 사용할 수 있다
``` html
<input v-model="something">
```
	* 	위의 코드와 같은 코드
``` html
<input v-bind:value="something"
	v-on:input="something = $event.target.value">
```

* 컴포넌트와 함께 사용하는 경우
``` html
<custom-input
  :value="something"
  @input="value => { something = value }">
</custom-input>
```

* v-model을 사용하는 컴포넌트는
	* value prop를 갖는다
	* 새로운 값으로 input이벤트를 내보낸다

* 예시
``` html
<currency-input v-model="price"></currency-input>
```
``` javascript
Vue.component('currency-input', {
  template: `
    <span>
      $<input ref="input"
			v-bind:value="value"
			v-on:input="updateValue($event.target.value)">
    </span>`,
  props: ['value'],
  methods: {
    // 값을 직접 업데이트하는 대신 메소드를 사용하여
    // 입력 값에 대한 서식을 지정하고 배치
    updateValue: function (value) {
      var formattedValue = value.trim().slice(0, value.indexOf('.') === -1 ? value.length : value.indexOf('.') + 3);

      // 값이 아직 정규화 되지 않은 경우
      if (formattedValue !== value) {
        this.$refs.input.value = formattedValue;
      }

      // 입력 이벤트를 통해 숫자 값을 내보낸다
      this.$emit('input', Number(formattedValue));
    }
  }
});
```


### 컴포넌트의 v-model 사용자 정의
* 2.2.0 버전에서 추가
* 기본적으로 컴포넌트의 v-model은 value를 보조 변수로 사용하고 input을 이벤트로 사용한다
* 하지만 다른 목적(체크박스, 라디오 버튼 등)으로 value 속성을 사용할 수 있다 => model 옵션을 사용
``` javascript
Vue.component('my-checkbox', {
  model: {
    prop: 'checked',
    event: 'change'
  },
  props: {
    // 다른 목적을 위해 `value` prop를 사용
    checked: Boolean,
    value: String
  }
})
```
``` html
<my-checkbox v-model="foo" value="some value"></my-checkbox>
```
	* 위의 코드와 같은 코드
		* checked prop을 명시적으로 선언해야한다
``` html
<my-checkbox
	:checked="foo"
	@change="val => { foo = val }"
	value="some value">
</my-checkbox>
```



### 비 부모-자식 간 통신
* 두 컴포넌트가 통신할때 서로 부모/자식이 아닐 수도 있다
* 이때 비어있는 Vue인스턴스를 중앙 **이벤트 버스**로 사용할 수 있다

``` javascript
var bus = new Vue();
```
``` javascript
// 컴포넌트 A의 메소드
bus.$emit('id-selected', 1)
```
```javascript
// 컴포넌트 B의 created
bus.$on('id-selected', function (id) {
  // ...
})
```

*  더 복잡한 통신의 경우 전용 상태 관리 패턴을 고려 (vuex의 store)



- - - -

## 슬롯을 사용한 컨텐츠 배포
* 컴포넌트를 사용할 때 이렇게 구성하는 것이 좋다
``` html
<app>
  <app-header></app-header>
  <app-footer></app-footer>
</app>
```
* 이때 주목해야할 사항
	* <app> 컴포넌트는 어떤 컨텐츠를 받을지 모른다 (<app>이 사용하는 컴포넌트에 의해 결정된다)
	* <app> 컴포넌트에는 자체 템플릿이 있을 가능성이 크다

* 위 구성으로 작동하도록 하려면 부모 “content”와 컴포넌트의 자체 템플릿을 섞는 방법이 필요 => 콘텐츠 배포 프로세스
*  Vue.js는 현재 웹 컴포넌트 사양 초안을 모델로 한 콘텐츠 배포 API를 구현하며 원본 콘텐츠의 배포판 역할을 하기 위해 특수한 <slot> 엘리먼트를 사용


### 범위 컴파일
* API를 파헤치기 전에 먼저 내용이 컴파일되는 범위를 명확히 해야한다
``` html
// 컴포넌트
<child-component> {{ message }} </child-component>
```
	* 	이 컴포넌트의 message는 부모에 컴파일된다!

> 상위 템플릿의 모든 내용은 상위 범위로 컴파일된다.  
> 하위 템플릿의 모든 내용은 하위 범위에서 컴파일된다.  

* 부모 템플릿의 하위 속성/메소드에 디렉티브를 바인딩하면 작동하지 않는다
``` html
// someChildProperty는 자식 컴포넌트의 속성이다
// v-show 디렉티브는 작동하지 않는다
<child-component v-show="someChildProperty"></child-component>
```

* 	컴포넌트 루트 노드에서 하위 범위 디렉티브를 바인딩 해야하는 경우 하위 컴포넌트의 자체 템플릿에서 하위 범위 디렉티브를 바인딩해야한다
``` html
Vue.component('child-component', {
	// 이제 작동합니다
	template: '<div v-show="someChildProperty">Child</div>',
	data: function () {
		return {
			someChildProperty: true
		}
	}
});
```

* 마찬가지로 분산된 콘텐츠는 상위 범위에서 컴파일된다

### 단일 슬롯
* 하위 컴포넌트 템플릿에 최소한 하나의 <slot> 콘텐츠가 포함되어 있지 않으면 부모 콘텐츠가 삭제된다
* 속성이 없는 슬롯이 하나 뿐인 경우 전체 내용 조각이 DOM의 해당 위치에 삽입되어 슬롯 자체를 대체

* 원래 <slot> 태그 안에 있는 내용은 대체 콘텐츠 로 간주 => 대체 콘텐츠는 하위 범위에서 컴파일되며 호스팅 엘리먼트가 비어 있고 삽입할 콘텐츠가 없는 경우에만 표시됨
``` html
// 자식 컴포넌트 (my-component)
<div>
	<h2>나는 자식 컴포넌트의 제목입니다</h2>
	<slot>
		제공된 컨텐츠가 없는 경우에만 보실 수 있습니다.
	</slot>
</div>
```
``` html
// 부모 컴포넌트
<div>
	<h1>나는 부모 컴포넌트의 제목입니다</h1>
	<my-component>
		<p>이것은 원본 컨텐츠 입니다.</p>
		<p>이것은 원본 중 추가 컨텐츠 입니다</p>
	</my-component>
</div>
```
* 렌더링 결과
``` html
<div>
	<h1>나는 부모 컴포넌트의 제목입니다</h1>
	<div>
		<h2>나는 자식 컴포넌트의 제목 입니다</h2>
		<p>이것은 원본 컨텐츠 입니다.</p>
		<p>이것은 원본 중 추가 컨텐츠 입니다</p>
	</div>
</div>

// 만약 부모에서 자식 컴포넌트에 추가한 컨텐츠가 없다면 아래와 같다!
<div>
	<h1>나는 부모 컴포넌트의 제목입니다</h1>
	<div>
		<h2>나는 자식 컴포넌트의 제목 입니다</h2>
		<slot>제공된 컨텐츠가 없는 경우에만 보실 수 있습니다.</slot>
	</div>
</div>
```



### 이름을 가지는 슬롯
* <slot> 엘리먼트는 특별한 속성인 name 을 갖는다
* 이름을 가진 슬롯은 내용 조각에 해당 slot 속성이 있는 모든 엘리먼트와 일치한다
* 명명되지 않은 슬롯(기본 슬롯)이 하나 있을 수 있다. 기본 슬롯 은 일치하지 않는 콘텐츠의 포괄적인 컨텐츠 역할을 한다. 기본 슬롯이 없으면 일치하지 않는 콘텐츠가 삭제된다

``` html
// app-layout 컴포넌트
<div class="container">
	<header>
		<slot name="header"></slot>
	</header>
	<main>
		<slot></slot>	// 기본 슬롯
	</main>
	<footer>
		<slot name="footer"></slot>
	</footer>
</div>
```
``` html
// 부모
<app-layout>
	<h1 slot="header">여기에 페이지 제목이 위치합니다</h1>

	<p>메인 컨텐츠의 단락입니다.</p>
	<p>하나 더 있습니다.</p>

	<p slot="footer">여기에 연락처 정보입니다.</p>
</app-layout>
```
* 렌더링 결과
``` html
<div class="container">
	<header>
		<h1>여기에 페이지 제목이 위치합니다</h1>
	</header>
	<main>
		<p>메인 컨텐츠의 단락입니다.</p>
		<p>하나 더 있습니다.</p>
	</main>
	<footer>
		<p>여기에 연락처 정보입니다.</p>
	</footer>
</div>
```



### 범위를 가지는 슬롯
* 범위가 지정된 슬롯은 이미 렌더링 된 엘리먼트 대신 재사용 가능한 템플릿(데이터를 전달할 수 있음)으로 작동하는 특별한 유형의 슬롯
* prop을 컴포넌트에게 전달하는 것처럼, 하위 컴포넌트에서 단순히 데이터를 슬롯에 전달
``` html
// child 컴포넌트
<div class="child">
	<slot text="hello from child"></slot>
</div>
```

* 이때 부모에서 특별한 속성 slot-scope를 가진 <template> 엘리먼트가 있어야 한다. 이것은 범위를 가지는 슬롯을 위한 템플릿임을 나타냄. slot-scope의 값은 자식으로부터 전달 된 props 객체를 담고있는 임시 변수의 이름

``` html
// 부모
<div class="parent">
	<child>
		<template slot-scope="props">
		<span>hello from parent</span>
		<span>{{ props.text }}</span>
		</template>
	</child>
</div>
```
* 렌더링 결과
``` html
<div class="parent">
	<div class="child">
		<span>hello from parent</span>
		<span>hello from child</span>
		</div>
</div>
```

> 2.5.0버전 이후, slot-scope 는 <template> 뿐 아니라 컴포넌트나 엘리먼트에서도 사용할 수 있다  


``` html
// my-list 컴포넌트
<ul>
	<slot name="item"
		v-for="item in items"
		:text="item.text">
		<!-- 대체 컨텐츠는 여기입니다. -->
	</slot>
</ul>
```
``` html
// 부모
<my-list :items="items">
	<!-- scoped slot 역시 이름을 가질 수 있다 -->
	<li slot="item"
		slot-scope="props"
		class="my-fancy-item">
		{{ props.text }}
	</li>
</my-awesome-list>
```

#### 디스트럭처링
* slot-scope 값은 실제로 함수 서명의 인수 위치에 나타날 수 있는 유효한 JavaScript 표현식
``` html
<child>
	<span slot-scope="{ text }">{{ text }}</span>
</child>
```

> 비구조화 할당 (destructuring assignment)  
> -> 배열이나 객체의 속성을 해체하여 그 값을 개별 변수에 담을 수 있게 하는 자바스크립트 표현식  

- - - -


## 동적 컴포넌트
* 같은 마운트 포인트를 사용하고 예약된 <component> 엘리먼트를 사용하여 여러 컴포넌트 간에 동적으로 트랜지션하고 is 속성에 동적으로 바인드 할 수 있다

``` javascript
var vm = new Vue({
	el: '#example',
	data: {
		currentView: 'home'
	},
	components: {
		home: { /* ... */ },
		posts: { /* ... */ },
		archive: { /* ... */ }
	}
})
```
``` html
<component v-bind:is="currentView">
	// vm.currentView가 변경되면 컴포넌트가 변경됨!
</component>
```

* 객체에 직접 바인딩
``` javascript
var Home = {
	template: '<p>Welcome home!</p>'
};

var vm = new Vue({
	el: '#example',
	data: {
		currentView: Home
	}
});
```


### keep-alive
* 트랜지션된 컴포넌트를 메모리에 유지하여 상태를 보존하거나 다시 렌더링하지 않도록하려면 동적 컴포넌트를 <keep-alive> 엘리먼트에 래핑한다
``` html
<keep-alive>
	<component :is="currentView">
		<!-- 비활성화 된 컴포넌트는 캐시 된다! -->
	</component>
</keep-alive>
```


- - - -

## 기타
* 추가 예정



