# Vue 1. 기본기능
## 목차
* 선언적 렌더링
* 조건문과 반복문
* 사용자 입력 핸들링
* 컴포넌트

- - - -

## 선언적 렌더링
* Vue.js의 핵심 => 간단한 템플릿 구문을 사용해 선언적으로 DOM에 데이터를 렌더링 하는 것
``` pug
#app {{ message }}	// message는 변수처럼 사용된다
						// hello가 출력!
```
``` javascript
const app = new Vue({
	el: '#app',
	data: function() {
		return({
			message: 'hello'
		});
	}
});
```

* 데이터와 DOM이 연결된 반응형!
``` pug
#app {{message}}
	.message-list(v-bind:title="message")
		// DOM에 Vue객체의 message가 바인딩되어 message에 따라 렌더링 된다

--------------------------------------------------------------

// 결과
#app hello
	.message-list(title='hello')

// title attribute는 마우스를 올렸을때 출력해주는 기능을 가진 attribute
```
``` javascript
const app = new Vue({
	el: '#app',
	data: function() {
		return({
			message: 'hello'
		});
	}
});


// 반응형이므로 console에서 message값을 바꿔주면
// (app.message = 'bye') 바로 적용!
// Vue의 data 내부 변수에 접근할 때는 app.data.message가 아닌 app.message로 접근!!
```

> <**디렉티브**>  
> 디렉티브는 Vue에서 제공하는 특수 속성임을 나타내는 v- 접두어가 붙어있으며 렌더링 된 DOM에 특수한 반응형 동작을 한다  
> v-bind, v-for, v-on, v-model 등이 있다  

- - - -

## 조건문과 반복문
* 조건문 (v-if 디렉티브)
	* 텍스트와 속성뿐 아니라 DOM의 구조에도 데이터를 바인딩 할 수 있다
	* 	Vue 엘리먼트가 Vue에 삽입_갱신_제거될 때 자동으로 트랜지션 효과를 적용할 수 있다
``` pug
#app
	div(v-if='seen')	// 조건문
```

``` javascript
const app = new Vue({
	el: '#app',
	data: function() {
		return({
			seen: true	// seen=true이면 보이고
			// seen: false 이면 보이지 않는다
		});
	}
});

// console에서 app.seen = false를 해주면 바로 적용이 된다
```

* 반복문 (v-for 디렉티브)\
	* 배열의 데이터를 사용해 항목 목록을 표시하는데 사용
``` pug
#app
	.message(v-for="message in messages")
			// messages를 돌면서 .message와 그 자식 div를 생성
		.text {{message}}
			// 이때 messages의 인자 message를 받아서 출력
	.message(v-for="message in messages2")
		.text {{message.text}}

--------------------------------------------------------------

// 결과
#app
	.message
		.text first
	.message
		.text second
	.message
		.text third
	.message
		.text one
	.message
		.text two
	.message
		.text three
```

``` javascript
const app = new Vue({
	el: '#app',
	data: function() {
		return({
			messages: ['first', 'second', 'third'],
			messages2: [
				{text: 'one'},
				{text: 'two'},
				{text: 'three'}
			]
		});
	}
});
```


- - - -


## 사용자 입력 핸들링
* DOM을 건드리지 않고 앱의 상태를 업데이트 할 수 있다!

* v-on 디렉티브를 사용해 Vue인스턴스에 메소드를 호출하는 이벤트 리스터를 첨부할 수 있다 (사용자가 앱과 상호작용)
``` pug
#app
	.message {{message}}
	button(v-on:click="reverseMessage") 메세지 뒤집기
		// v-on 디렉티브를 이용해 click리스터를 달아준다
		// button이 눌리면 reverseMessage함수를 호출
```
``` javascript
const app = new Vue({
	el: '#app',
	data: function(){
		return({
			message: 'reverse message!'
		});
	},
	methods: {
		reverseMessage: function() {
			this.message = this.message.split('').reverse().join('');
		}
	}
});
```

* v-model 디렉티브를 이ㅛㅇ하여 입력과 앱 상태를 양방향으로 바인딩

``` pug
#app
	.message {{message}}
	input(v-model="message")	// 입력값이 바뀌면 Vue의 message의 값이 바뀐다
								// 따라서 .message의 message값도 바뀌게 된다
```
``` javascript
const app = new Vue({
	el: '#app',
	data: function() {
		return({
			message: 'hello'
		});
	}
});
```




- - - -


## 컴포넌트를 사용한 작성방법
* 컴포넌트 시스템은 Vue의 또 다른 중요한 개념
* 컴포넌트 시스템은 작고 ‘그 자체로 제 기능을 하며 재사용할 수 있는 컴포넌트’로 구성된 대규모 응용 프로그램을 구축할 수 있게 해주는 추상적 개념
* 거의 모든 유형의 응용 프로그램 인터페이스를 컴포넌트 트리로 추상화할 수 있다
* Vue에서 컴포넌트는 본질적으로 미리 정의된 옵션을 가진 Vue 인스턴스이다

* 컴포넌트를 통해 앱을 더 작은 단위로 나눌 수 있고, props 인터페이스를 통하여 자식을 부모로부터 합리적인 수준으로 분리할 수 있다 (props인터페이스를 이용해 부모 앱에 영향을 주지 않으면서 컴포넌트를 만들 수 있다)

* 대규모 응용 프로그램에서는 개발 과정을 관리할 수 있는 수준 하에 두기 위해 전체 앱을 컴포넌트로 나누는 것이 필수적!


### 컴포넌트 등록
``` javascript
//컴포넌트 등록
Vue.component('todo-item', {
  props: ['todo'],
		// props 인터페이스에 todo를 선언해준다
		// 부모영역의 데이터를 자식 컴포넌트에 집어넣을 수 있는 객체를 제공
  template: '<li>{{ todo.text }}</li>'
		// todo-item 컴포넌트를 사용하면 이 템플릿을 사용!
})

const app = new Vue({
  el: '#app',
  data: {
    groceryList: [
      { id: 0, text: 'Vegetables' },
      { id: 1, text: 'Cheese' },
      { id: 2, text: 'Whatever else humans are supposed to eat' }
    ]
  }
});
```

### 컴포넌트 사용
``` pug
// 컴포넌트 등록시에 props인터페이스를 통해서 todo-item 에 todo 객체를 제공한다

ol
	todo-item(v-for:"item for groceryList"
			v-bind:todo="item"
			v-bind:key="item.id")

// v-for문을 돌면서 groceryList를 가져오고
// 컴포넌트에 props인터페이스에 선언해 두었던 todo객체에 item을 bind해준다
// (여기서 todo를 컴포넌트의 props인터페이스에 선언해두지 않았다면 todo라는 attribute가 생성된다!)
// 그리고 key를 바인딩 한다(각 요소마다 key를 제공해야한다)
```


