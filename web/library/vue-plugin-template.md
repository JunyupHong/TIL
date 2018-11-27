# vue-plugin-template
* 플러그인을 npm에 올리기 위한 라이브러리
* 컴포넌트만 등록하게 설정되어있다

* 플러그인을 올리려면 모든 app이 올라가면 안된다
* 컴포넌트만 등록해야 다른 사람이 받아서 사용할 수 있다
* 따라서 vue-plugin-template을 사용

## 사용
1. 설치
* `$ vue init posva/vue-plugin-template`
```
? Plugin name junyup-first-plugin
? Library name JunyupFirstPlugin
? Plugin description A Vue.js Plugin
? Current version 0.0.0
? Author Junyup Hong <junyup.hong@gmail.com>
? GitHub Account junyup.hong@gmail.com
? Bundler rollup
```

2. 명령어 실행
	* `$ cd junyup-first-plugin`
	* `$ npm install`

3. webstorm을 켠 후 npm 명령어 실행
	* build:dll
	* build
	* dev

4. webstorm의 react파일과 그것을 사용하는 곳 제거
	* test_specs_hello-jsx.sepc.js 삭제
	* src/Hello.jsx 삭제
	* src/index.js 에서 Hello.jsx 사용하는 3곳 삭제

5. github에 연결 (프로젝트 이름과 같게 연결해야함)

6. 유저 추가 (npm 아이디/비밀번호, github 메일로 추가)
	* `$ npm adduser`

7. publish 하기
	* `$ npm publish --access public`
8.  npm 페이지 접속
	* => package에 가보면 플러그인이 등록된 것을 확인할 수 있다

9. README.md 에 설명 추가




