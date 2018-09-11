# Vue 2. 인스턴스

## 목차
* Vue 인스턴스 만들기
* 속성과 메소드
* 인스턴스 라이프사이클 훅
* 라이프사이클 다이어그램

- - - -

## Vue 인스턴스 만들기
* 모든 Vue앱은 Vue함수로 새 Vue인스턴스를 만드는 것부터 시작!
``` javascript

// vm은 ViewModel의 약자
const vm = new Vue({
	// option객체(element , data, template, method, callback 등)를 전달
});
```

* MVVM패턴과 관련이 없지만 Vue의 디자인은 그것에 영감을 받음(그래서 종종 변수이름을 vm(ViewModel)으로 사용)

> <**MVVM패턴**>  
> Model-View-ViewModel 패턴  
> > MVC(Model-View-Controller) - 아키텍처의 최상위에 뷰가 있고 그 아래 컨트롤러가 있고 그 아래 모델이 있는 패턴. 때문에 뷰는 컨트롤러만 알고 있고 컨트롤러는 모델을 알고있다. 모델이 변경되었을 때 뷰는 컨트롤러를 통해서 통보를 받음  
> > MVP(Model-View-Presenter) - MVC에서 컨트롤러가 Presenter로 교체된 형태. 프리젠터는 뷰와 같은 레벨에 있다. 프리젠터는 뷰와 모델의 이벤트를 모두 받으면서 둘 사이의 상호작용을 조정.  
> > MVVM - MVC에서 컨트롤러가 뷰모델로 교체된 형태. 뷰모델은 UI레이어 아래에 위치. 뷰모델은 뷰가 필요로 하든 데이터와 커맨드 객체를 노출해 주기 때문에 뷰가 필요로하는 데이터와 액션을 담고 있는 컨테이너 객체로 볼 수도 있다.  

* Vue 인스턴스를 인스턴스화 할 때는 마운트(구동)할 엘리먼트, 데이터, 템플릿, 메소드, 라이프사이클 콜백등의 옵션을 포함할 수 있는 option 객체를 전달해야한다
* Vue 생성자는 미리 정의 된 옵션으로 재사용 가능한 컴포넌트 생성자를 생성하도록 확장될 수 있다(`Vue.component('componentname', {})`)
* Vue앱은 new Vue()로 만들어진 root Vue 인스턴스로 구성되며 선택적으로 중첩이 가능하고 재사용 가능한 컴포넌트 트리로 구성된다

- - - -

## 속성과 메소드
* 각 Vue 인스턴스는 data객체에 있는 모든 속성을 프록시 처리(대신 처리, 프록시는 일반적으로 다른 어떤 클래스의 인터페이스로 동작하는 클래스)한다
``` javascript
const data = { a : 1 };

// Vue 인스턴스 생성
const vm = new Vue({
	data: data	// 데이터 객체 추가
});

// 같은 객체를 참조
console.log(vm.a === data.a);		// true

// 속성 설정은 원본데이터에도 영향을 미침
vm.a = 2;
console.log(data.a)	// 2
// 반대
data.a = 3;
console.log(vm.a)		// 3
```

* 데이터가 변경되면 화면은 다시 렌더링 된다
* data에 있는 속성들은 인스턴스가 생성될 때 존재한 것들만 반응형이다!
* 하지만 인스턴스가 생성된 후 새 속성을 추가하면 추가한 속성이 변경되어도 화면이 갱신되지 않는다 따라서 어떤 속성이 나중에 필요하다는 것을 알고 있으며, 빈값이나 존재하지 않는상태로 시작한다면 초기값을 지정해 주어야 한다!
``` javascript
// 위의 코드에서 data에 b속성을 추가
vm.b = 'hi';	// 화면이 갱신되지않는다
```

* `Object.freeze()` 를 사용하는 경우는 예외!
	* 기존 속성이 변경되는 것을 막아 반응성 시스템이 추적할 수 없게 한다
``` javascript
const obj = { foo: 'bar' };

Object.freeze(obj);

new Vue({
	el: '#app',
	data: obj
})
```
``` pug
#app
	p {{ foo }}
	button(v-on:click="foo = 'baz'") change
```
 

* Vue 인스턴스는 데이터 속성 이외에도 유용한 인스턴스 속성 및 메소드를 제공한다
``` javascript
const data = { a : 1 };
const vm = new Vue({
	el: '#app',
	data: data
});

console.log(vm.$data === data);	// true
console.log(vm.$el === document.getElementById('app'));	// true

// $watch는 인스턴스 메소드($watch보다 계산된 속성을 사용하는게 좋음)
vm.$watch('a', function(newVal, oldVal) {
	// vm.a가 변경되면 호출 된다
});
```

- - - -

## 인스턴스 라이프사이클 훅
* Vue 인스턴스는 데이터 관찰을 설정하고, 템플릿을 컴파일하고, 인스턴스를 DOM에 마운트하고, 데이터가 변경 될 때 DOM을 업데이트해야 할 때 일련의 초기화 단계를 거친다
* 이때 사용자 정의 로직을 실행할 수 있는 라이프사이클 훅도 호출된다
* created 훅은 인스턴스가 생성된 후에 호출된다
``` javascript
const app = new Vue({
	data: {
		a: 1
	},
	created: function() {
		// this는 Vue인스턴스를 가리킴!
		console.log(this.a);	// 1
	}
});
```
* 이 외에도 mounted, updated, destroyed 훅이 있다
* 모든 라이프 사이클 훅은 this 컨텍스트가 호출하는 Vue 인스턴스를 가리키며 호출된다

> options 속성이나 콜백에 화살표함수(arrow function) 사용을 지양!  
> 화살표 함수들은 부모 컨텍스트에 바인딩되기 때문에, this 컨텍스트가 호출하는 Vue 인스턴스에서 사용할 경우 오류가 발생한다  

- - - -

## 라이프사이클 다이어그램
![vue 인스턴스 라이프사이클](../image/Vue_Instance_LifeCycle_eng.png)



