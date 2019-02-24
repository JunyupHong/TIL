# firebase hosting
* firebase를 통해서 웹 호스팅이 가능
* firebase 프로젝트의 hosting에서 설정 가능

## firebase cli 인스톨 및 로그인
1. firebase cli 인스톨
`$ npm install -g firebase-tools`

2. firebase 동작 (계정 로그인)
`$ firebase login`
	* 로그인 창이 뜸 - google id로 로그인


## 프로젝트에서 firebase 설정 및 배포
1. firebase 설정
`$ firebase init`
	* database, firestore, function, hosting 등중 사용할 것을 고름
	* public 폴더가 아닌 dist 폴더로 경로 설정 (build 하게 되면 번들링된 파일이 dist에 생성되므로)
	* rewrite index.html:  yes설정

	* -> firebase 파일이 생성된다 (firestore.rules 등)

2. 배포
`$ firebase deploy`
	* build 한 파일들이 배포 된다
	* 업데이트시 build 후 이 명령어 실행하면 새 버전이 배포됨

	* 배포할때 .map 파일을 빼고 배포를 해야한다
	* => 크기가 크고 map파일이 올라가면 코드가 노출된다


## 도메인 연결
1. firebase hosting에서 도메인 연결 누르기
2. 도메인 추가
	* DNS 제공업체 (aws의 route53이나 gabia 등) 에서 구매한 도메인 연결
3. DNS 제공업체에서 소유권 확인
	* firebase hosting의 타입(text)과 value를 복붙 (record set에 추가)

	* 이때 www로 시작하는 도메인(www.junyup.xyz)과 바로 시작하는 도메인(junyup.xyz)을 각각 설정해준다
	* => 와일드 카드('{}')로 쓸 수 있지만 그렇게 하지 않는다!
	* 왜냐면 나중에 관리자 페이지(www.admin.junyup.xyz)과 같이 도메인이 생성될 수 있기 때문



## 이점
* 새 버전에 오류가 있을 시 이전 버전으로 쉽게 되돌아 갈 수 있다
	* firebase hosting에서 롤백을 할 수 있음