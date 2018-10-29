# Vue Router 프로그래밍 방식 네비게이션
### 라우터의 인스턴스 메소드 사용
* router-link를 사용하여 선언적 네비게이션용 anchor 태그를 만드는 것 외에도 라우터의 인스턴스 메소드를 사용하여 수행 할 수 있다
* push, go back, …

> Vue 인스턴스 내부에서 라우터 인스턴스에 $router로 접근 할 수 있다  
> => this.$router.push() 같은 라우터의 메소드를 사용 가능  


## router.push()
* 다른 URL로 이동
* 새로운 항목을 히스토리 스택에 넣는다 (뒤로가기하면 이전 URL로 돌아감)
* 선언적 방식: `<router-link: to="...">`
	* 선언적 방식을 사용할 때도 내부적으로 router.push() 메소드가 호출
* onComplete, onAbort 콜백 인자를 사용 가능


### 사용
``` javascript
// 리터럴 string
router.push('home');

// object
router.push({ path: 'home' });

// 이름을 가지는 라우트
router.push({ name: 'user', params: { userId: 123 }});

// 쿼리와 함께 사용
	// 결과: '/register?plan=private'
router.push({ path: 'register', query: { plan: 'private' }});
```

### 세번째 인자 (onComplete, onAbort)
* 2.2.0 버전 이후
* push, replace에서의 전달인자
* onComplete 콜백
	* 탐색이 성공적으로 완료될 때 (모든 비동기 훅이 해결된 후) 호출
* onAbort 콜백
	* 탐색이 중단(현재 탐색이 완료되기 전에 동일한 경로로 이동하거나 다른 경로 이동) 될 때 호출


## router.replace()
* router.push와 같은 역할
* 히스토리 항목에 추가하지 않고 탐색 (push와의 차이점, 뒤로가기를 누르면 바로 이전의 URL이 아닌 히스토리에 있는 URL로 이동)
* 선언적 방식: `<router.link: to="...">`
	* 선언적 방식을 사용할 때도 내부적으로 router.push() 메소드가 호출
* onComplete, onAbort 콜백 인자를 사용 가능

## router.go()
* `window.history.go(n)` 와 비슷
* 히스토리 스택에서 앞으로 또는 뒤로 이동하는 단계를 나타내는 정수를 매개변수로 사용

### 사용
``` javascript
// 한 단계 앞으로 이동 == history.forward()
router.go(1);

// 한 단계 뒤로 이동 == history.back()
router.go(-1);

// 3 단계 앞으로 이동
	// 지정한 만큼의 기록이 없으면 자동으로 실패
router.go(3);
```

## history 조작
* router는 window.history의 API를 모방한다
* router.push와 window.history.pushState 와 상응
* router.replace와 window.history.replaceState 와 상응
* router.go와 window.history.go 와 상응



