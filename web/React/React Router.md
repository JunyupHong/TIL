# React Router

## SPA
* SPA는 index.html 파일에 div 엘리먼트만 하나 두고, 자바스크립트로 모든 부분을 동적으로 랜더링하는 구조.
* 이벤트에 따라서 동적으로 갱신할 수 있기 때문에 인터랙티브 한 사용자 경험을 제공



## React를 사용한 SPA 구현
* state를 사용해 component를 관리하고 state에 따라 화면의 component를 바꿔주면 된다.
``` javascript
function App() {
  const [compoment, setComponent] = useState(page1);
	const changePage = url => setComponent(url);
  return (
    <div children={component}></div>
  )
}

```

### 문제점
* React를 이용해 별도의 라이브러리 없이 SPA를 구현 하게되면 URL이 바뀌지 않아서 문제가 발생
1. 특정 페이지에 대한 즐겨찾기 등록이 불가.
2. 뒤로 가기 버튼을 누르면 다른 웹사이트로 이동.
3. 새로 고침 버튼을 누르면 최초의 페이지로 이동.



## React-router
* SPA의 라우팅 문제를 해결하기 위해서 React에서 표준처럼 사용되고 있는 네비게이션 라이브러리
* location나 history와 같은 브라우저 내장 API와 연동하여 라우팅 문제를 해결

### 설치
`$ npm i react-router-dom`

### 사용
`import { Link, Route, BrowserRouter as Router } from "react-router-dom";`

### 컴포넌트
#### Link Component
`<Link to=“/page1”>Go To another Page1</Link>`
* a 태그와 유사
* 다른점
	* <a> 태그는 href 속성을 통해 이동할 경로를 지정
	* <Link> 컴포넌트는 to prop을 통해서 이동할 경로를 지정
	* link는 전체 페이지를 reload하지 않고 필요한 부분만 reload!

#### Route Component
`<Route path=“/page” component={page} />`
* url의 경로와 매치되는 컴포넌트를 지정
* path prop을 통해서 경로를 지정하고 component prop을 통해서 컴포넌트를 할당
* render, children 속성도 존재
* 컴포넌트에 match, location, history 객체를 넘김
	* match: path에 정의한 것과 매치된 정보
	* location: window.location과 유사, URL의 정보
	* history: window.history와 유사, 주소를 변경하더라도 SPA에 맞게 페이지 일부만 리로드됨
##### 특징
* url의 앞부분이 path prop과 같으면 뒤의 url을 보지 않고 이동.
	* path=“_“ 일 경우, “_“로 시작하는 모든 url이 매칭됨
* exact prop을 사용하면 path prop과 url 전체가 같은 경우에만 매칭됨

#### Router Component
* <Route>와 <Link> 를 묶어줌
``` html
<Router>
  <Link />
  <Link />
	...
  <Router />
  <Router />
	...
</Router>
```


#### 기타 Component
##### Redirect
##### withRouter
##### Switch
##### NavLink


