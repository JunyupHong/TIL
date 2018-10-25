# router-link와 a태그
## router-link와 a 태그의 공통점
* a 태그와 router-link는 둘다 a태그로서 렌더링 된다
	* => 대부분의 웹사이트들은 a태그로 검색을 지원한다
	* 따라서 router-link는 자동으로 a태그로 바뀌므로 검색노출에 불리하지 않다

## router-link와 a 태그의 차이
* 사용
	* a 태그 : `<a href="/user"></a>`
	* router-link : `<router-link to='/user'></router-link>`

* 작동
	* a 태그 : 누를때마다 refreshing 된다
	* router-link : SPA처럼 작동 => 화면 전환시 애니메이션이 가능

* router-link는 현재 라우트와 일치할 때 자동으로 `.router-link-active` 클래스가 추가된다