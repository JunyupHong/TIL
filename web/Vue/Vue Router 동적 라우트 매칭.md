# Vue Router 동적 라우트 매칭
* route => 현재 페이지에 대한 정보
* router => 다른 페이지로의 이동

### $route 객체
	* $route.params (path에 param(동적 세그먼트)이 있는 경우)
	* $route.query (URL에 쿼리가 있는 경우)
	* $route.hash
	* …

### $router 객체
	* $router.push()
	* $router.back()
	* $router.forward()
	* $router.go()
	* $router.replace()
	* …

### param & query
* param은 꼭 필요한 것 (id 등), query는 옵션/선택사항 (상품번호 등)

* 사용
	* param => path에 : 으로 적용 => `/download/:id`
	* query => URL에 ? 로 적용 => `www.naver.com?number=123`

- - - -

## 동적 세그먼트를 사용
* 주어진 패턴을 가진 라우트를 동일한 컴포넌트에 매핑해야하는 경우 동적 세그먼트를 사용 ( `/path/:동적세그먼트` )
* 동적 세그먼트는 콜론 ‘ : ’ 으로 표시
* 동적 세그먼트의 값은 모든 컴포넌트에서 `this.$route.params`로 표시
* 동일한 라우트에 여러 동적 세그먼트를 가질 수 있다 `/user/:username/post/:post_id` => this.$route.params = {username: ‘’, post_id: ‘’}
``` javascript
//
import Vue from 'vue';
import Router from 'vue-router';

import Index from '../page/index/index';
import Download from '../page/download/download';

Vue.use(Router);

const routes = [
	{
		path: '/',
		name: 'index',
		component: Index
	},
	{
		// 동적 세그먼트로 id를 사용
			// $route.params.id 로 id값을 알 수 있다
		path: '/download/:id',
		name: 'download',
		component: Download
	},
]
```


- - - -

## params 변경 사항에 반응하기
* `path: '/user/:id'` 일 때 `/user/foo`에서 `/user/bar`로 이동하면 동일한 컴포넌트 인스턴스가 재사용된다
* 두 라우트 모두 동일한 컴포넌트를 렌더링하므로 이전 인스턴스를 삭제 한 다음 새 인스턴스를 만드는 것보다 효율적
* 그러나 컴포넌트의 라이프 사이클 훅이 호출되지 않음을 의미!
	* => 동일한 컴포넌트의 params 변경 사항에 반응하려면 $route 객체를 watch 한다
``` javascript
watch: {
	'$route' (to, from) {
		// 경로가 바뀌면 실행 된다!
	}
}
```
	* beforeRouteUpdate를 사용
``` javascript
beforeRouteUpdate (to, from, next) {
	// react to route changes...
	// don't forget to call next()
}
```

- - - -

## 매칭 우선순위
* 동일한 URL이 여러 라우트와 일치하는 경우, 일치하는 우선 순위는 라우트 정의의 순서에 따라 결정됨
* 즉, 경로가 더 먼저 정의 될수록 우선 순위가 높아진다











