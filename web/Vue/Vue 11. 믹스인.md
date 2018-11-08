# Vue 11. 믹스인
## mixins
* mixins는 Vue 컴포넌트에 재사용 가능한 기능을 배포하는 유연한 방법
* mixin 객체는 모든 구성요소 옵션(data, methods, created 등)을 포함할 수 있다
* 컴포넌트에 mixin을 사용하면 mixin의 모든 옵션이 컴포넌트의 고유 옵션에 ‘혼합’된다


* mixin은 mixin객체와 mixin을 옵션으로 가진 객체의 내용이 합쳐진다
	* 상속과 유사 => 내용이 중복될 경우 mixin을 옵션으로 가진 객체가 우선순위 (오버라이딩)
* Vue 생명주기와 관련하여 TODO가 있는 경우 사용한다
	* library, util 기능이 필요한 경우에는 mixin이 아닌 prototype을 사용한다

> mixin은 template과 javascript의 제어가 모두 필요할때 사용 (구성요소 옵션으로 template을 제어할 수 있다)  
> 	import MixinBase from '../...';  
> 	mixin: ['MixinBase']  
>   
> export & import 는 javascript만 필요할때 사용  
> 	javascript 에서 사용  
> 	=> export {Common};		 export default Common;  
> 	=> import * as Common from '.._util_common';  
> 	node 에서 사용  
> 	=> module.export();  
> 	=> require('');  

### 예제
``` javascript
// mixin 객체 생성
var myMixin = {
	created: function () {
		this.hello()
	},
	methods: {
		hello: function () {
			console.log('hello from mixin!')
		}
	}
};

// mixin을 사용할 컴포넌트 정의
var Component = Vue.extend({
	mixins: [myMixin]
});

var component = new Component() // => "hello from mixin!"


-----------------------------------------------
// Vue Router에서 mixin 사용 (template, script 필요할때)
import MixinBase from '../...';
export default {
	mixins: ['MixinBase']
};


// export & import 사용 (script만 필요할때)

// 1. js파일에서
export {Common}; // export default Common;
import * as Common from '../util/common';

// 2. node에서
module.export();
require('');
```


- - - -

## 옵션 병합
* mixin과 컴포넌트 자체에 중첩 옵션이 포함되어 있으면 적절한 전략을 사용하여 “병합”된다
* 예를 들어, 같은 이름의 훅 함수가 배열에 병합되어 모든 함수가 호출된다. 또한 mixin 훅은 컴포넌트 자체의 훅 이전에 호출된다

``` javascript
var mixin = {
	created: function () {
		console.log('mixin hook called')
	}
};

new Vue({
	mixins: [mixin],
	created: function () {
		console.log('component hook called')
	}
});

// => "mixin hook called" 이 먼저 불리고
// => "component hook called" 이 나중에 불린다
```

* methods, components, directives와 같은 객체 값을 요구하는 옵션은 같은 객체에 병합된다
* 이러한 객체에 충돌하는 키가 있을 경우 컴포넌트의 옵션이 우선순위를 갖는다
``` javascript
var mixin = {
	methods: {
		foo: function () {
			console.log('foo')
		},
		conflicting: function () {
			console.log('from mixin')
		}
	}
};

var vm = new Vue({
	mixins: [mixin],
	methods: {
		bar: function () {
			console.log('bar')
		},
		conflicting: function () {
			console.log('from self')
		}
	}
});

vm.foo()			// => "foo"
vm.bar()			// => "bar"
vm.conflicting()	// => "from self"
					// (컴포넌트의 conflicting이 불린다)
```

> 같은 병합 전략이 Vue.extend()에서도 사용된다  



- - - -

## 전역 mixin
* mixin은 전역으로 적용 가능
* 전역으로 적용하면 이후 생성된 모든 Vue 인스턴스에 영향을 미침
* 사용자 정의에 대한 처리 로직을 주입하는데 사용할 수 있다

``` javascript
// `myOption` 사용자 정의 옵션을 위한 핸들러 주입
Vue.mixin({
	created: function () {
		var myOption = this.$options.myOption
		if (myOption) {
			console.log(myOption)
		}
	}
});

new Vue({
	myOption: 'hello!'
});
// => "hello!"
```

> 글로벌 mixin은 써드파티 컴포넌트를 포함하여 생성된 모든 단일 Vue 인스턴스에 영향을 주기 때문에 적게 이용하고 신중하게 사용해야함.  
> 대부분의 경우 위 예제에서와 같이 사용자 지정 옵션 처리에만 사용해야한다.  
> 중복 적용을 피하기 위해 plugins로 제공하는 것도 좋은 방법  

- - - -

## 사용자 정의 옵션 병합 전략
* 사용자 지정 옵션을 병합할 때 기본 옵션을 사용하면 기존 값을 덮어쓴다
* 커스텀 로직을 사용해 커스텀 옵션을 병합하려면, Vue.config.optionMergeStrategies에 함수를 추가할 필요가 있다
``` javascript
Vue.config.optionMergeStrategies.myOption = function (toVal, fromVal) {
	// return 병합된 값
}
```

* 대부분의 객체 기반 옵션에서 methods에서 사용한 것과 같은 전략을 간단하게 사용할 수 있다
``` javascript
var strategies = Vue.config.optionMergeStrategies;
strategies.myOption = strategies.methods;
```


* 예제
``` javascript
const merge = Vue.config.optionMergeStrategies.computed;
Vue.config.optionMergeStrategies.vuex = function (toVal, fromVal) {
	if (!toVal) return fromVal;
	if (!fromVal) return toVal;
	return {
		getters: merge(toVal.getters, fromVal.getters),
		state: merge(toVal.state, fromVal.state),
		actions: merge(toVal.actions, fromVal.actions)
	};
};
```











