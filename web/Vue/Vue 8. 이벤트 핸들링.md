# Vue 8. 이벤트 핸들링
## 목차
* 이벤트 청취
* 메소드 이벤트 핸들러
* 인라인 메소드 핸들러
* 이벤트 수식어
* 키 수식어
* 시스템 수식어 키 목록
* 왜 HTML로 된 리스너를 사용하는가?

- - - -

## 이벤트 청취
* v-on 디렉티브를 사용하여 DOM 이벤트를 듣고 트리거 될 때 JavaScript를 실행할 수 있다
``` html
<div id="example-1">
  <button v-on:click="counter += 1">Add 1</button>
  <p>위 버튼 클릭 횟수 {{ counter }} 번</p>
</div>
```
``` javascript
var example1 = new Vue({
  el: '#example-1',
  data: {
    counter: 0
  }
});
```

- - - -

## 메소드 이벤트 핸들러
* 많은 이벤트 핸들러 로직은 복잡할 것이다 -> 따라서 JavaScritp를 v-on 속성 값으로 보관하는 것은 간단하지 않다
* v-on 이 호출하고자 하는 메소드의 이름을 받아서 트리거 될 때 실행한다
``` html
<div id="example-2">
  <!-- `greet`는 메소드 이름으로 methods에 정의 -->
  <button v-on:click="greet">Greet</button>
</div>
```
``` javascript
var example2 = new Vue({
  el: '#example-2',
  data: {
    name: 'Vue.js'
  },
  // 메소드는 `methods` 객체 안에 정의
  methods: {
    greet: function (event) {
      alert('Hello ' + this.name + '!')
      // `event` 는 네이티브 DOM 이벤트입니다
      if (event) {
        alert(event.target.tagName)
      }
    }
  }
})

// 또한 JavaScript를 이용해서 메소드를 호출할 수 있습니다.
example2.greet() // => 'Hello Vue.js!'
```


- - - -

## 인라인 메소드 핸들러
* 메소드 이름을 직접 바인딩하는 대신 인라인 JavaScript 구문에 메소드를 사용할 수 있다
``` html
<div id="example-3">
  <button v-on:click="say('hi')">Say hi</button>
  <button v-on:click="say('what')">Say what</button>
</div>
```
``` javascript
new Vue({
  el: '#example-3',
  methods: {
    say: function (message) {
      alert(message)
    }
  }
})
```

* 인라인 명령문에 핸들러에서 원본 DOM 이벤트에 접근해야 할 수도 있다
* 이때 $event 변수를 사용해 메소드에 전달할 수 있다
``` html
<button v-on:click="warn('Form cannot be submitted yet.', $event)"> Submit </button>
```
``` javascript
methods: {
  warn: function (message, event) {
    // 이제 네이티브 이벤트에 액세스 할 수 있습니다
    if (event) event.preventDefault()
    alert(message)
  }
}
```


- - - -

## 이벤트 수식어
* 이벤트 핸들러 내부에서 event.preventDefault() 또는 event.stopPropagation() 을 호출하는 것은 매우 보편적
* 메소드 내에서 쉽게 이 작업을 할 수 있지만, DOM 이벤트 세부사항을 처리하는 대신 데이터 로직에 대한 메소드만 사용할 수 있으면 좋다
* 이런 문제를 해결하기 위해 Vue는 v-on 이벤트에 이벤트 수식어를 제공
* 이벤트 수식어
	* .stop
	* .prevent
	* .capture
	* .self
	* .once
``` html
<!-- 클릭 이벤트 전파가 중단됩니다 -->
<a v-on:click.stop="doThis"></a>

<!-- 제출 이벤트가 페이지를 다시 로드 하지 않습니다 -->
<form v-on:submit.prevent="onSubmit"></form>

<!-- 수식어는 체이닝 가능합니다 -->
<a v-on:click.stop.prevent="doThat"></a>

<!-- 단순히 수식어만 사용할 수 있습니다 -->
<form v-on:submit.prevent></form>

<!-- 이벤트 리스너를 추가할 때 캡처모드를 사용합니다 -->
<!-- 즉, 내부 엘리먼트를 대상으로 하는 이벤트가 해당 엘리먼트에서 처리되기 전에 여기서 처리합니다. -->
<div v-on:click.capture="doThis">...</div>


<!-- event.target이 엘리먼트 자체인 경우에만 트리거를 처리합니다 -->
<!-- 자식 엘리먼트에서는 안됩니다 -->
<div v-on:click.self="doThat">...</div>

<!-- 클릭 이벤트는 최대 한번만 트리거 됩니다. -->
	<!-- 2.1.4에서 추가 -->
<a v-on:click.once="doThis"></a>

<!-- 스크롤의 기본 이벤트를 취소할 수 없습니다. -->
	<!-- 2.3.0에서 추가 -->
<div v-on:scroll.passive="onScroll">...</div>
```

> 이벤트 수식어를 사용하는 순서에 따라서 실행순서가 정해진다  
> @click.prevent.self	=>	모든 클릭을 막는다  
> @click.self.prevent	=>	엘리먼트 자체에 대한 클릭만 방지  


* 추가로, Vue는 .passive 수식어를 제공한다. 모바일 환경에서 성능향상에 도움이 됨
* 예를 들어, 브라우저는 핸들러가 event.preventDefault()를 호출할지 알지 못하므로 프로세스가 완료된 후 스크롤 한다. .passive 수식어는 이 이벤트가 기본 동작을 멈추지 않는다는 것을 브라우저에 알릴 수 있습니다

> .passive와 .prevent를 함께 사용하면 안됨  
> 패시브 핸들러는 기본 이벤트를 막지 않는다  


- - - -

## 키 수식어
* 키보드 이벤트를 설정할 때, 공통 키 코드를 확인해야한다
* Vue는 키 이벤트를 수신할 때 v-on 에 대한 키 수식어를 추가할 수 있다
``` html
<!-- keyCode가 13일 때만 `vm.submit()`을 호출합니다  -->
<input v-on:keyup.13="submit">
```
* keyCode를 모두 기억하는 것은 어렵다
* Vue는 자주 사용하는 키의 alias를 제공
* alias 목록
	* .enter
	* .tab
	* .delete (Delete와 Backspace 키 모두 캡쳐)
	* .esc
	* .space
	* .up
	* .down
	* .left
	* .right
* 	전역 config.keyCodes 객체를 통해 사용자 지정 키 수식어 별칭을 지정할 수 있다
``` javascript
// `v-on:keyup.f1`을 사용할 수 있다
Vue.config.keyCodes.f1 = 112
```

### 오토매틱 키 수식어
* 2.5.0에서 추가
* KeyboardEvent.key 를 통해 노출된 유효 키 이름을 수식어로 직접 사용할 수 있다
``` javascript
<input @keyup.page-down="onPageDown">
```

> 일부 키(.esc와 모든 화살표 키)는 IE9에서 일관성 없는 key 값을 가지고 있다. IE9를 지원해야하는 경우 내장 알리아스가 선호된다  


- - - -

## 시스템 수식어 키 목록
* 2.1.0에서 추가
* 다음 수식어를 사용해 해당 수식어 키가 눌러진 경우에만 마우스 또는 키보드 이벤트 리스너를 트리거 할 수 있다 (ex ‘shift+enter’, ‘control+c’...)
	* .ctrl
	* .alt
	* .shift
	* .meta
>  매킨토시 키보드에서 meta는 command 키 (⌘)  
> Windows 키보드에서 meta는 windows 키 (⊞)  
> Sun Microsystems 키보드에서 meta는 단색의 다이아몬드 (◆)로 표시  
> 특정 키보드의 경우, 특히 MIT 및 Lisp 시스템 키보드와 Knight 키보드, space-cadet 키보드와 같은 제품에는 “META” 레이블이 지정  
> Symbolics 키보드에서 메타는 “META” 또는 “Meta”로 표시  

``` html
<!-- Alt + C -->
<input @keyup.alt.67="clear">

<!-- Ctrl + Click -->
<div @click.ctrl="doSomething">Do something</div>
```

> 수식어 키는 일반 키와 다르며 keyup 이벤트와 함께 사용되면 이벤트가 발생할 때 수식어 키가 눌려있어야 한다  
> 즉, keyup.ctrl는 ctrl을 누른 상태에서 키를 놓으면 트리거된다  
> ctrl 키만 놓으면 트리거되지 않음  


### .exact 수식어
* 2.5.0에서 추가
* .exact 수식어는 다른 시스템 수식어와 조합해 그 핸들러가 실행되기 위해 정확한 조합이 눌러야 하는 것을 보여줌
``` html
<!-- Alt 또는 Shift와 함께 눌린 경우에도 실행 -->
<button @click.ctrl="onClick">A</button>

<!-- Ctrl 키만 눌려있을 때만 실행 -->
<button @click.ctrl.exact="onCtrlClick">A</button>

<!-- 시스템 키가 눌리지 않은 상태인 경우에만 실행 -->
<button @click.exact="onClick">A</button>
```

### 마우스 버튼 수식어
* 2.2.0에서 추가
* 종류
	* .left
	* .right
	* .middle
* 위 수정자는 특정 마우스 버튼에 의해 트리거 된 이벤트로 핸들러를 제한

- - - -

## 왜 HTML로 된 리스너를 사용하는가
* 이 모든 이벤트 청취 접근 방법이 우려 사항(관심사) 분리(separation of concerns)에 대한 오래된 규칙을 어긴다고 생각할 수 있다
> Separation of Concerns (SoC)  
> 각 부문이 각자의 관심사를 갖도록 컴퓨터 프로그램을 여러 부문으로 나누는 설계 원칙  
* 모든 Vue 핸들러 함수와 표현식은 현지 Vue 처리하는 ViewModel에 엄격히 바인딩 되기 때문에 유지보수가 어렵지 않다
* v-on을 사용하면 좋은점
	1. HTML 템플릿을 간단히 하여 JavaScript 코드 내에서 핸들러 함수 구현을 찾는 것이 더 쉽다
	2. JavaScript에서 이벤트 리스너를 수동으로 연결할 필요가 없으므로 ViewModel 코드는 순수 로직과 DOM이 필요하지 않다. 이렇게 하면 테스트가 쉬워진다
	3. ViewModel이 파기되면 모든 이벤트 리스너가 자동으로 제거 됩니다. 이벤트 제거에 대한 걱정이 필요 없어진다







 