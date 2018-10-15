# click 이벤트 & $emit 사용 & 컴포넌트의 $el


## click 이벤트
* 이벤트는 자식에서 부모로(위로) 전달된다
* click은 자식 div로 전달된다
* @click.stop을 하면 클릭 될 경우 전파를 중지한다

- - - -

## $emit 사용
* 특정 범위를 이벤트로 부모에게 전달
* 자식에서 $emit을 이용해서 부모에 이벤트를 보낼 수 있다

### 사용법
``` javascript
// 자식
	// $emit을 이용해 부모에게 send 이벤트를 전달
this.$emit('send', arg1, arg2 ...);

// 부모
	// $on을 이용해 자식에게 받은 이벤트를 실행
this.$on('send', (arg1, arg2) => {
	console.log(arg1, arg2);
});

	// 또는
.div(@send="method(arg1, arg2)")

methods: {
	method(arg1, arg2) {
		console.log(arg1, arg2);
	}
}
```


- - - -

## 컴포넌트의 $el
* 엘리먼트에 ref를 달고 console로 확인해보면 html이 떨어진다
* 하지만 만들어진 컴포넌트에 ref를 달아서 그 ref를 console로 확인해보면 Vue.component가 떨어진다
	* => 왜냐하면 컴포넌트는 결국 Vue 인스턴스를 import해서 사용하는 것이기 때문
	* 따라서 this.$refs.componentName.$el을 사용해서 html을 가져온다
	* $el은 Vue인스턴스가 가지고있는 DOM!

``` pug
// 템플릿에서 만든 엘리먼트
.tag-edit-area(ref="element")

// 만든 컴포넌트
tag-editor(ref="component")
```
``` javascript
// html이 출력
console.log(this.$refs.element);

// Vue.component 출력
console.log(this.$refs.component);
// html 출력
console.log(this.$refs.component.$el);
```







