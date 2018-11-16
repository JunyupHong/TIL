# Vue 컴포넌트 인스턴스 생성
* 프로그래밍 방식으로 컴포넌트 인스턴스 생성하기
* 원하는 시점에 컴포넌트 인스턴스를 생성하고 새로운 인스턴스로 덮어쓸 수 있다

## 인스턴스 생성
* 잘못된 사용
	* 클래스(생성자 함수)가 아닌 단순 객체(Button)만 내보낸다
``` javascript
import Button from 'Button.vue';
const instance = new Button();
```

* 올바른 사용
	* 컴포넌트 객체(Button)를 전달하여 Vue.extend를 통해 Vue생성자의 하위 클래스를 만든다
	* 그 다음 new를 사용해 인스턴스를 생성한다
``` javascript
import Vue from 'vue';
import Button from 'Button.vue';

const ComponentClass = Vue.extend(Button);
const instance = new ComponentClass();
```

##  DOM에 삽입
* Vue 인스턴스에는 $mount 라는 전달된 요소에 컴포넌트 인스턴스를 마운트하는 메소드가 존재 => 전달된 요소를 컴포넌트 인스턴스로 바꿔준다
* 이때 elementOrSelector 인자를 전달하지 않으면 템플릿이 문서 외 요소로 렌더링되고 DOM API를 사용하여 직접 문서에 삽입해야한다

``` javascript
import Vue from 'vue';
import Button from 'Button.vue';

const ComponentClass = Vue.extend(Button);
const instance = new ComponentClass();

instance.$mount();	// 인자를 넘겨주지 않는다
						// 직접 컴포넌트를 삽입해야한다
this.$refs.container.appendChild(instance.$el);
						// 컴포넌트 삽입
```
	* $refs를 이용해 DOM 엘리먼트에 대한 참조를 얻는다
	* Vue 컴포넌트 인스턴스에서 기본 DOM요소 참조를 가져오려면 $el속성을 사용한다




## 인스턴스에 props 전달
* 인스턴스를 생성할 때 propsData 옵션을 사용

``` javascript
import Vue from 'vue';
import Button from 'Button.vue';

const ComponentClass = Vue.extend(Button);

// propsData를 이용해 prop전달
const instance = new ComponentClass({
	propsData: {
		type: 'primary',
		color: 'blue'
	}
});

instance.$mount();
this.$refs.container.appendChild(instance.$el);

```


## slot 설정
* $slots 속성을 통해 사용
* 인스턴스를 마운트 하기 전에 slot을 설정해야한다

``` javascript
import Vue from 'vue';
import Button from 'Button.vue';

const ComponentClass = Vue.extend(Button);

const instance = new ComponentClass({
	propsData: {
		type: 'primary',
		color: 'blue'
	}
});

// slot 설정
instance.$slot.default = [ 'Click me!' ];

instance.$mount();
this.$refs.container.appendChild(instance.$el);
```




