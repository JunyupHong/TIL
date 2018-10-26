# Vue Router 중첩된 라우트
* 실제 앱 UI는 일반적으로 여러 단계로 중첩 된 컴포넌트로 이루어져 있다.
* URL의 세그먼트가 중첩 된 컴포넌트의 특정 구조와 일치한다는 것은 매우 일반적
* 예를 들어 _user_foo_profile은 user foo의 정보를 보여주고 foo의 profile을 보여주는데 /user_foo/posts는 user foo의 정보를 보여주고 foo의 post를 보여준다
* vue-router를 사용하면 중첩된 라우트 구성을 사용하여 관계를 표현하는것이 매우 간단하다

	* children을 사용해야한다!
``` javascript
import Vue from 'vue';
import Router from 'vue-router';
import User from '../user';
	// User의 템플릿
	// <div id="app">
	// 	<router-view></router-view>
	// </div>
import Profile from '../profile';
import Posts from '../posts';

Vue.use(Router);

const routes = [
	{
		path: '/user/:id,
		name: username,
		component: User,

		// children을 사용해서
		// 서브루트를 제공할 수 있다
		children: [
			{
				// /user/:id 인 경우 렌더링 할때는
				// 빈 서브루트 경로를 제공
				path: '',
				component: UserHome
			},
			{
				// /user/:id/profile 인 경우
				// Profile의 템플릿이 User의 router-view에 들어간다
				path: 'profile',
				name: Profile.name,
				component: Profile
			},
			{
				// /user/:id/posts 인 경우
				// // Posts 템플릿이 User의 router-view에 들어간다
				path: 'posts',
				name: Posts.name,
				component: Posts
			}
		]
	}
]
```