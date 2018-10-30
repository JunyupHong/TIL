# Vue Router 이름을 가지는 라우트
* 라우터에 연결하거나 탐색을 수행할 때 이름이 있는 라우터를 사용하는 것이 더 편리

### 라우트의 이름 지정
* Router 인스턴스 생성하는동안 routes 옵션에 라우트를 지정할 수 있다
``` javascript
import Vue from 'vue';
import Router from 'vue-router';

import Index from '../page/index/index';
import Download from '../page/download/download';

Vue.use(Router);

const routes = [
	{
		path: '/',
		// 라우트의 이름을 지정
		name: 'index',
		component: Index
	},
	{
		path: '/download/:id',
		// 라우트의 이름을 지정
		name: Download.name,
		component: Download
	}
];
```

### 사용
* 선언적 방식
``` html
<router-link :to="{ name: 'user', params: { userId: 123 }}">User</router-link>
```

* 프로그래밍식 방식
``` javascript
router.push({ name: 'user', params: { userId: 123 }});
```