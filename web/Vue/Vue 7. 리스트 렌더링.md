# Vue 7. 리스트 렌더링
## 목차
* v-for 로 엘리먼트에 배열 매핑하기
* v-for 와 객체
* key
* 배열 변경 감지
* 객체 변경 감지에 관한 주의사항
* 필터링 / 정렬 된 결과 표시하기
* Range v-for
* v-for 템플릿
* v-for와 v-if
* v-for와 컴포넌트

- - - -

## v-for 로 엘리먼트에 배열 매핑하기
* v-for 디렉티브를 사용해 배열을 기반으로 리스트를 렌더링 할 수 있다
* item in items 형태의 문법이 필요(items는 원본 데이터 배열, item은 반복되는 배열 엘리먼트의 별칭)
### 사용법
``` html
<ul id="example-1">
  <li v-for="item in items">
    {{ item.message }}
  </li>
</ul>

--------------------------------------------------
// 결과
<ul id="example-1">
  <li>Foo</li>
  <li>Bar</li>
</ul>
```
``` javascript
var example1 = new Vue({
  el: '#example-1',
  data: {
    items: [
      { message: 'Foo' },
      { message: 'Bar' }
    ]
  }
});
```
* v-for 블록 안에는 부모 범위 속성에 대한 권한이 있다
* v-for는 현재 항목의 인덱스에 대한 두번째 전달인자 옵션을 제공
``` html
<ul id="example-2">
  <li v-for="(item, index) in items">
    {{ parentMessage }} - {{ index }} - {{ item.message }}
  </li>
</ul>

--------------------------------------------------
// 결과
<ul id="example-2">
  <li>Parent - Foo - 0</li>
  <li>Parent - Bar - 0</li>
</ul>
```
``` javascript
var example2 = new Vue({
  el: '#example-2',
  data: {
    parentMessage: 'Parent',
    items: [
      { message: 'Foo' },
      { message: 'Bar' }
    ]
  }
});
```

* in 대신에 of를 구분자로 사용할 수 있다 (JavaScript의 이터레이터에 대한 구문과 유사)
``` html
<div v-for="item of items"></div>
```


- - - -

## v-for 와 객체
* v-for를 사용해 객체의 속성을 반복할 수 있다
``` html
<ul id="v-for-object" class="demo">
  <li v-for="value in object">
    {{ value }}
  </li>
</ul>

--------------------------------------------------
// 결과
<ul id="v-for-object" class="demo">
	<li>John</li>
	<li>Doe</li>
	<li>30</li>
</ul>
```
``` javascript
new Vue({
  el: '#v-for-object',
  data: {
    object: {
      firstName: 'John',
      lastName: 'Doe',
      age: 30
    }
  }
});
```

* key에 대한 두번째 전달 인자를 제공할 수 있다
``` html
<div v-for="(value, key) in object">
  {{ key }}: {{ value }}
</div>

--------------------------------------------------
// 결과
<div>firstName: John</div>
<div>lastName: Doe</div>
<div>age: 30</div>
```

* 세번째 인자로 index 제공
``` html
<div v-for="(value, key, index) in object">
  {{ index }}. {{ key }} : {{ value }}
</div>

--------------------------------------------------
// 결과
<div>0. firstName: John</div>
<div>1. lastName: Doe</div>
<div>2. age: 30</div>
```

> 객체를 반복할 때 순서는 Object.keys()의 키 나열 순서에 따라 결정된다  
> JavaScript 엔진 구현간에 일관적이지는 않다  


- - - -

## key
* Vue가 v-for에서 렌더링된 엘리먼트 목록을 갱신할 때 기본적으로 ‘in-place patch’전략을 사용
> <in-place patch>  
> 데이터 항목의 순서가 변경된 경우 항목의 순서와 일치하도록 DOM요소를 이동하는 대신 각 요소를 적절한 위치에 패치하고 해당 인덱스에서 렌더링할 내용을 반영하는지 확인한다 (track-by=$index의 동작과 유사)  

* 이것은 효율적이지만 목록의 출력 결과가 하위 컴포넌트 상태 또는 임시 DOM 상태(ex input)에 의존하지 않는 경우에 적합
* Vue가 각 노드의 ID를 추적하고 기존 엘리먼트를 재사용하고 재정렬 할 수 있도록 힌트를 제공하려면 각 항목에 고유한 key속성을 제공해야 한다
* key에 대한 이상적인 값은 각 항목의 고유한 ID이다
* 이 특별한 속성(key)은 track-by와 비슷하지만 속성처럼 작동하기 때문에 v-bind를 사용하여 동적 값에 바인딩 해야한다
``` html
<div v-for="item in items" :key="item.id">
</div>
```

* 반복되는 DOM 내용이 단순하지 않거나 의도적인 성능 향상을 위해 기본 동작에 의존하지 않는한 가능하면 언제나 v-for에 key를 추가하는 것이 좋다
* key는 Vue가 노드를 식별하는 일반적인 메커니즘이기 때문에 v-for와 특별히 연관되지 않는 다른 용도로도 사용된다


- - - -

## 배열 변경 감지
### 변이 메소드
* Vue는 감시중인 배열의 변이 메소드를 래핑하여 뷰 갱신을 트리거한다
* 래핑된 메소드
	* push()
	* pop()
	* shift()
	* unshift()
	* splice()
	* sort()
	* reverse()
* 	콘솔에서 배열을 변이 메소드를 호출하여 재생할 수 있다
``` javascript
example1.items.push({ message: 'Baz' });
```

### 배열 대체
* 변이 메소드는 호출된 원본 배열을 변형한다
* 원본 배열을 변형을 하지 않고 새 배열을 반환하는 메소드(변형이 없는 방법으로 작업할때 이전 배열을 새 배열로 바꿀 수 있다)
	* filter()
	* concat()
	* slice()
``` javascript
example1.items = example1.items.filter(function (item) {
  return item.message.match(/Foo/)
});
```
* 이렇게 하면 Vue가 기존 DOM을 버리고 전체 목록을 다시 렌더링 한다고 생각할 수 있다 하지만 그렇지 않다
* Vue는 DOM 요소 재사용을 극대화하기 위해 몇가지 구현을 하므로 배열을 겹치는 객체가 포함된 다른 배열로 대체하여 효율적이다

### 주의사항
* JavaScript 제한으로 인해 Vue는 배열에 대해 몇가지 변경 사항을 감지할 수 없다
1. 인덱스로 배열에 있는 항목을 직접 설정하는 경우 `vm.items[indexOfLength] = newValue;`
	* 1번의 변경 사항(인덱스로 배열에 있는 항목을 직접 설정하는 경우)의 감지를 위하여 사용하는 방법 2가지
``` javascript
// 1. Vue.set
Vue.set(example1.items, indexOfItem, newValue);

// 2. Array.prototype.splice
example1.items.splice(indexOfItem, 1, newValue);
```
2. 배열의 길이를 수정하는 경우 `vm.items.length = newLength;`
	* 2번의 변경 사항(배열의 길이를 수정하는 경우)의 감지를 위하여 사용하는 방법
``` javascript
example1.items.splice(newLength);
```

- - - -

## 객체 변경 감지에 관한 주의사항
* 모던 JavaScript의 한계로 Vue는 속성 추가 및 삭제를 감지하지 못한다
``` javascript
var vm = new Vue({
	data() {
		return{
			// 미리 선언된 vm.a는 반응형이다
			a: 1
		};
	}
});

// 나중에 추가된 vm.b는 반응형이 아니다
vm.b = 2;
```
* Vue는 이미 만들어진 인스턴스에 새로운 루트레벨의 반응형 속성을 동적으로 추가하는 것을 허용하지 않는다
* 그러나 Vue.set(Object, key, value) 메소드를 사용하여 중첩된 객체에 반응형 속성을 추가할 수 있다
``` javascript
var vm = new Vue({
	data() {
		return{
			name: 'foo'
		};
	}
});

// 중첩된 userProfile 객체에 새로운 속성 age 추가(age는 반응형)
Vue.set(vm.userProfile, 'age', 27);

// 또는 인스턴스 메소드인 vm.$set (Vue.set의 별칭)을 사용할 수도 있다
vm.$set(this.userProfile, 'age', 27);

// 때로는 Object.assign()이나 _.extend()를 사용해 기존의 객체에 새 속성을 할당할 수 있다(이 경우 두 객체의 속성을 사용해 새 객체를 만들어야 한다)
Object.assign(this.userProfile, {
	age: 27,
	favoriteColor: 'Vue Green'
});
	// 새로운 반응형 속성을 추가
this.userProfile = Object.assign({}, this.userProfile, {
	age: 27,
	favoriteColor: 'Vue Green'
});
```

- - - -

## 필터링/정렬 된 결과 표시하기
* 원본 데이터를 실제로 변경하거나 재설정하지 않고 배열의 필터링/정렬 된 버전을 표시해야 할 필요가 있다
* 이때 필터링/정렬 된 배열을 반환하는 계산된 속성을 만들 수 있다
``` html
<li v-for="n in evenNumbers">{{ n }}</li>
```
``` javascript
data() {
	return{
		numbers: [ 1,2,3,4,5 ]
	};
},
computed: {
	evenNumbers: function() {
		return this.numbers.filter(function (number) {
			return number % 2 === 0;
		})
	}
}
```

* 계산된 속성을 실행할 수 없는 상황(ex 이중 v-for문 내부)에서 사용하는 방법
``` html
<li v-for="n in even(numbers)">{{ n }}</li>
```
``` javascript
data: {
  numbers: [ 1, 2, 3, 4, 5 ]
},
methods: {
  even: function (numbers) {
    return numbers.filter(function (number) {
      return number % 2 === 0;
    });
  }
}
```



- - - -

## Range v-for
* v-for 는 숫자를 사용할 수 있다(이 경우 템플릿을 여러번 반복)
``` html
<div>
	<span v-for="n in 10">{{ n }} </span>
</div>

// 1 2 3 4 5 ... 9 10 출력
```

- - - -

## v-for 템플릿
* v-if 템플릿과 마찬가지로 `<template>` 태그를 사용해 여러 엘리먼트의 블럭을 렌더링 할 수 있다
``` html
<ul>
	<template v-for="item in items">
		<li>{{ item.msg }}</li>
		<li class="divider"></li>
	</template>
</ul>
```


- - - -

## v-for 와 v-if
* 동일한 노드에 두 가지 구문이 모두 있다면, v-for가 v-if보다 높은 우선순위를 갖게 된다 (즉, v-if는 루프가 반복 될 때마다 실행)
* 이는 일부 항목만 렌더링 하는 경우 유용
``` html
// todos 중에서 todo.isComplete가 false인 경우만 렌더링
<li v-for="todo in todos" v-if="!todo.isComplete"> {{ todo }} </li>
```
* 실행을 조건부로 하는 것이 목적이라면 래퍼엘리먼트나 `<template>`를 사용
``` html
// todos가 없다면 v-for문을 실행하지 않는다
// todos가 있다면 v-for문을 실행
<ul v-if="todos.length">
  <li v-for="todo in todos"> {{ todo }} </li>
</ul>
<p v-else>No todos left!</p>
```

- - - -

## v-for와 컴포넌트
* v-for를 사용자 정의 컴포넌트에 직접 사용할 수 있다
``` html
<my-component v-for="item in items" :key="item.id"></my-component>
```

* 2.2.0 버전 이상에서는 v-for에 key가 필수
* 그러나 컴포넌트에는 자체 범위가 분리되어 있기 때문에 컴포넌트에 데이터를 자동으로 전달하지는 않는다 (반복할 데이터를 컴포넌트로 전달하려면 props도 사용해야함)
``` html
<my-component
	v-for="(item, index) in items"
	v-bind:item="item"
	v-bind:index="index"
	v-bind:key="item.id"
></my-component>
```
* 컴포넌트에 item을 자동으로 주입하지 않는 이유는 자동을 주입하면 컴포넌트가 v-for 의 작동 방식과 밀접하게 결합되기 때문 (-> 데이터의 출처를 명확히 하면 다른 상황에서 컴포넌트를 재사용 할 수 있다)
``` html
<div id="todo-list-example">
  <input
    v-model="newTodoText"
    v-on:keyup.enter="addNewTodo"
    placeholder="Add a todo"
  >
  <ul>
    <li
      is="todo-item"
      v-for="(todo, index) in todos"
      v-bind:key="todo.id"
      v-bind:title="todo.title"
      v-on:remove="todos.splice(index, 1)"
    ></li>
  </ul>
</div>
```
``` javascript
Vue.component('todo-item', {
  template: `
    <li> {{ title }}
      <button v-on:click="$emit(${remove})">X</button>
    </li>`,
  props: ['title']
})

new Vue({
  el: '#todo-list-example',
  data: {
    newTodoText: '',
    todos: [
      {
        id: 1,
        title: 'Do the dishes',
      },
      {
        id: 2,
        title: 'Take out the trash',
      },
      {
        id: 3,
        title: 'Mow the lawn'
      }
    ],
    nextTodoId: 4
  },
  methods: {
    addNewTodo: function () {
      this.todos.push({
        id: this.nextTodoId++,
        title: this.newTodoText
      });
      this.newTodoText = '';
    }
  }
});
```





