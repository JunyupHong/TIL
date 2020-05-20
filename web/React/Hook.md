# Hook
## Hook 이란?
* 로직 분리가 가능하면서 wrapper hell을 피하고, 리액트 Component life cycle에 종속적이지 않도록 하기 위해 만들어진 것.
* 재사용한 로직만을 분리해서 component로 만들고, 재사용 불가능한 부분들은 parameter로 받아서 처리


## Hook의 특징
* Hook을 사용하면 컴포넌트로부터 상태 관련 로직을 추상화할 수 있음
* Class 없이 리액트 기능(life cycle 등..)을 사용 => 함수형 프로그래밍을 가능하게 함
* 클래스를 대체하는 것은 아니다! => 선택적 사용이 가능
	* 공식 문서에서도 이미 짜놓은 클래스를 모두 재작성하는 것은 권장하지 않음

## Hook 사용규칙
1.  최상위에서만 Hook을 호출
2. React 함수 컴포넌트 내에서만 Hook을 호출
	* custom Hook에서도 호출이 가능
	* JavaScript 함수에서 호출하면 안됨


## Hook API
### useState
* **함수형 컴포넌트에서 상태를 관리하는 방법**
	* (Class 컴포넌트에서는 state를 객체로 묶어서 관리)
* useState는 배열을 반환
	* 구조 분해 할당 구문으로 변수와 setter를 할당함
	* 인자로 초기값을 설정할 수 있다
* setter 함수를 통해 값을 수정해야 상태 관리가 가능
* useState는 내부에서 비동기적으로 처리됨
	* setter를 연속해서 사용하면 호출이 될때마다 일어나는 것이 아님!
	* 가상 돔을 update하고 최종적으로 렌더가 한 번만 일어남
``` javascript
// class Component
state = {
	isLoading: true,
	recipeList: []
}
this.state({recipeList: list, isLoading: false});

// 함수형 Component
const [value, setValue] = useState(10);

setValue(100);
```


### useEffect
* **React Life Cycle에 해당하는 로직을 사용 가능**
	* componentDidMount
	* componentDidUpdate
	* componentWillUnmount

* 서로 관련 없는 로직을 분리할 수 있다.
* 리액트는 컴포넌트에 사용된 모든 effect를 지정된 순서에 맞춰 적용한다.

* 사용
``` typescript
function useEffect(effect: EffectCallback, inputs?: InputIdentityList);
```
	* effect
		* 비동기 작업을 할 때는 useEffect 콜백 함수 안에 직접 넣지말고 비동기 처리하는 함수를 만들어서 호출하라 (공식 문서)
	* inputs (optional)
		* 배열에 정의된 변수들이 변경될 때 함수가 호출됨
		* input이 없으면 계속해서 실행됨
		* input이 빈 배열이라면 mount 시점에 한번만 호출됨 (componentDidMount)
		* Vue.js의 Watch, computed와 유사!

* clean-up
	* userEffect의 콜백함수의 리턴은 componentWillUnmount 시점에 실행됨
	* clean-up이 필요하지 않는 경우에는 리턴하지 않으면 됨
``` javascript
useEffect(() => {
	return () => document.removeEventListener('click', clickFunc);
});
```



### useContext
* context를 이용하면 단계마다 일일이 props를 넘겨주지 않고도 모든 하위 컴포넌트에 데이터를 제공할 수 있음

* 데이터를 트리 안 여러 레벨이 있는 많은 컴포넌트에 주어야할 때 사용
* 데이터 값이 변할 때마다 모든 하위 컴포넌트에 알릴 수 있음
* app에 context를 등록하면 전체 트리에서 사용할 수 있음


* **createContext()** 로 context 생성
	`const appContext = createContext();`
* **useContext()** 로 context를 가져옴
``` javascript
import React, { createContext } from 'react';
// 0. AppContext 생성
const AppContext = createContext();
const App = () => {
	const user = { nickname: 'danuel', isAdmin: true };
	return (<AppContext.Provider value={user}>
				<div> <Posts /> </div>
			</AppContext.Provider> );
};

// 1. PostsContext 생성
const PostsContext = createContext();
const Posts = () => {
	const posts = [ { title: 'useContext 알아보기', content: '이번 편에서는 React Context를 ...' } ];
	return (<PostsContext.Provider value={posts}>
				<Children />
			</PostsContext.Provider> );
}


// 2. user와 posts를 가져와 화면에 보여주기
const Children = () => (
	<AppContext.Consumer>
		{user => (
			<PostsContext.Consumer>
				{posts => {
					let label = 'user'
					if (user.isAdmin) label = 'admin'
					return (<div>
								<div>{label}</div>
								<div>{user.nickname}</div>
								<div>{posts.map((post, index) => (
									<div key={index}>
										<div>{post.title}</div>
									<div>{post.content}
								</div>
							</div> ))}
						</div>
					)
				}}
			</PostsContext.Consumer>
		)}
	</AppContext.Consumer>
);
```


### useRef
* useRef는 .current프로퍼티로 전달된 인자(initialValue)로 초기화된 변경 가능한 ref 객체를 반환한다
* 이때 반환된 객체는 전 생에 주기를 통해 유지된다
``` javascript
const refContainer = useRef(initialValue);
```



## Custom Hook
* custom hook은 다른 hook을 호출하는 JavaScript 함수이다
* custom hook을 만들면 컴포넌트 로직을 재사용 가능한 함수로 뽑아낼 수 있다

* 규칙
	* use로 시작해야함
	* 다른 hook을 호출
``` javascript
import React, { useState, useEffect } from 'react';

// 1. "use" 로 시작
function useFriendStatus(friendID) {
	const [isOnline, setIsOnline] = useState(null);

// 2. 다른 hook을 호출한다
	useEffect(() => {
		function handleStatusChange(status){
			setIsOnline(status.isOnline);
		}
		ChatAPI.subscribeToFriendStatus(friendID, handleStatusChange);
		return () => {
			ChatAPI.unsubscribeFromFriendStatus(friendID, handleStatusChange);
		};
	});

	return isOnline;
}
```

``` javascript
function FriendStatus(props) {
	// custom hook
	const isOnline = useFriendStatus(props.friend.id);
	if (isOnline === null) return 'Loading...';
	return isOnline ? 'Online' : 'Offline';
}
function FriendListItem(props) {
	//custom hook\
	const isOnline = useFriendStatus(props.friend.id);
	return (<li style={{ color: isOnline ? 'green' : 'black' }}>
				{props.friend.name}
			</li>);
}
```


* 커스텀 훅은 state와 관련된 로직을 재사용하기 위한 하나의 매커니즘일 뿐이고 커스텀 훅을 사용한 컴포넌트의 모든 state와 effect는 완벽하게 분리되어 있다.
* 우리는 컴포넌트(FriendListItem)안에서 커스텀 훅을 직접 호출했다. 리액트의 관점에서 이것은 맨 처음 컴포넌트 안에서 직접 useState와 useEffect를 호출한 것과 똑같다. 그리고 우리는 한 컴포넌트 안에서 useState를 여러번 호출해도 각 state마다 독립적인 것을 이미 알고 있다.





