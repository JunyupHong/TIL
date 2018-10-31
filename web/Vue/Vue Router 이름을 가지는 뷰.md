# Vue Router 이름을 가지는 뷰
* 여러 개의 뷰를 중첩하지 않고 동시에 표시해야 하는 경우
	* sidebar뷰와 main 뷰로 레이아웃을 생성
* 뷰에 하나의 outlet이 있는 대신 여러 개를 사용하여 각 outlet에 이름을 지정
* 이름이 없는 router-view는 이름으로 default가 주어짐
``` html
<router-view class="view one"></router-view>
<router-view class="view two" name="a"></router-view>
<router-view class="view three" name="b"></router-view>
```
* 뷰는 컴포넌트를 사용하여 렌더링 되므로 여러 뷰에는 동일한 라우트에 대해 여러 컴포넌트가 필요하다
	* component 옵션 대신 components 옵션을 사용
``` javascript
import Vue from 'vue';
import Router from 'vue-router';

import Foo from './foo';
import Bar from './bar';
import Baz from './baz';

Vue.use(Router);

const routes = [
	{
		path: '/',
		components: {
			default: Foo,
			a: Bar,
			b: Baz
		}
	}
];
```