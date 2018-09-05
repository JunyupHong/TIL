# Vue Router
* Vue.js의 공식 라우터
*  Vue.js를 사용한 싱글 페이지 앱을 쉽게 만들 수 있도록 Vue.js의 코어와 긴밀히 통합되어 있다
* express에서 사용 불가
* SPA(Single Page Application)를 만들 수 있다

> SPA(Single Page Application)  
> 	서버로부터 완전한 새로운 페이지를 불러오지 않고 현재의 페이지를 동적으로 다시 작성함으로써 사용자와 소통하는 웹 애플리케이션이나 웹사이트  

## 기능
* 중첩된 라우트/뷰 매핑
* 모듈화된, 컴포넌트 기반의 라우터 설정
* 라우터 파라미터, 쿼리, 와일드카드
* Vue.js의 트랜지션 시스템을 이용한 트랜지션 효과
* 세밀한 네비게이션 컨트롤
* active CSS 클래스를 자동으로 추가해주는 링크
* HTML5 히스토리 모드 또는 해시 모드(IE9에서 자동으로 폴백)
* 사용자 정의 가능한 스크롤 동작

- - - -

## Setting
1. `$ install npm -g vue-cli`
	 global로 install 한것이므로 한번만 하면됨
2. `$ vue init hyunwoo/vue-template`
	 hyunwoo/vue-template 에 있는 setting을 가져온다!
3. 옵션 설정
```
? *Generate project in current directory?* Yes
? *Project name* vue-router-sample
? *Project description* A Vue.js project
? *Author* junyup.hong@gmail.com
? *Vue build* standalone
? *Install vue-router?* Yes
? *Use ESLint to lint your code?* Yes
? *Pick an ESLint preset* Airbnb
? *Pick an template language* pug
? *Pick an style language* sass
? *Set up unit tests* Yes
? *Pick a test runner* karma
? *Setup e2e tests with Nightwatch?* Yes
? *Pick a documentation tool* documentationjs
? *Do you want to deploy on the github page?* Yes
? *Branch name* gh-pages
? *What is the GitHub Project Name?*
** Important: If this project is a user-wide github page, leave it blank. If not,*
*please enter the name of the project.*
*1) {USERNAME}.github.io => BLANK,*
*2) {USERNAME}.github.io/{PROJECTNAME} => PROJECTNAME*
*:* vou-router-sample							// 폴더 이름(프로젝트 이름)과 같게 설정!
? *Should we run `npm install` for you after the project has been created? (recom*
*mended)* npm
```

4. webstorm code style 설정
	preference -> code style -> scheme에서 setting아이콘을 선택 -> import scheme -> intellij IDEA code style XML 선택 -> 폴더의 xml파일 선택 ( lint 적용..된것)

- - - -

## npm 명령어
* dev === start (port: 8080)
	* dev: 개발용(배포용이 아님), 코드바뀐걸 인식해서 브라우저를 알아서 update 해줌! (무거움)
	* start: 코드 바뀐거만 인식, 브라우저에서는 직접 refresh를 해줘야함!
=> 실행하고 127.0.0.1:8080/repository이름 을 하면 첫 페이지가 나온다
	src폴더에 page폴더에 vue가 생성된다

* unit
	* unit 테스트용
	* _test_unit_specs_Hello.spec.js 의 테스트 코드를 통과하면 실행되고 통과하지 못하면 에러가 난다 (초기 테스트는 hello.vue파일의 h1의 텍스트가 ‘Welcome to Hanu Vue Template’)

* e2e
	* end2end test용
	* browser에서 test?
	* browser 창이 하나 뜸

* test
	* unit과 e2e를 같이 실행해줌!

* lint
	* 자동으로 webstorm에서 지원해줌(실행 할 필요없다)

* generate-docs
	* 주석을 모아둔 doc파일을 dist폴더 안에 documentation안에 만들어줌 (docma.json과  jsdoc.json파일을 참조해서 만든다)

* serve-generate
	* 주석을 모아둔 doc파일을 구동시켜줌 (port: 4001)

* build
	* babel build
	* 실행하면 dist폴더에 static폴더와 index.html 파일이 생성된다 => 파일을 많이 만들어도 static과 index파일만 생성된다
	=> 파일들은 난독화 해서 만들어짐!
	=> 파일 크기를 줄여줌 (빠르다)
	=> 크기가 작은 파일 여러개를 받아오는거보다 큰 파일 하나를 받아오는게 빠르다

* github-deploy
	* 자동으로 github페이지가 생성됨!

* publish
	* 실행하면 배포!
	* 로컬로 실행!

	
## 파일 & 폴더 설명
* /build  &  /config
	* setting을 위한 폴더 (port setting 등..)
* _src_assets
	* 변경이 가능한 파일
* /static
	* 크기가 큰 파일(webpack에 묶이길 원하지 않는 파일)들이 들어가는 폴더
* .eslintrc.js
	* lint setting 파일
* docma.json  &  jsdoc.json
	* doc을 생성할때(npm generate-docs을 실행할 때) 참조하는 파일

* **index.html**
	* index.html을 바탕으로 모든 뷰가 구성이됨! (id= app인 div가 있음..)
	* 여기 head에는 styling하는 script를 추가준다(fontawsome, metarial icons, font 같은 것들..)
	* 다른 script(jQuery, babel-polyfill, lodash, masonry 등)를 넣으면 안됨!!!! script는 vue파일에서 추가

* **package.json**
	* node.js 명령어와 package 관리..




## 특징

* 하나의 vue파일에는 루트가 하나여야 한다!
	* Vue Router는 따로 new Vue()를 해주지 않는다(index.html을 바탕으로 모든 뷰가 구성이 됨) => 따라서 루트가 하나만 존재해야한다

* 하나의 vue파일에
	* template,
	* script,
	* style 이 다 들어간다

* `<template  lang=“pug”>`
	* lang을 추가해주면 추가한 언어를 쓸수있다

* `<style scoped lang=“sass”>`
	* scoped를 쓰면 안에 있는 내용은 그 Vue파일 내부에서만 쓰인다!







## test
* test_unit_specs/Hello.spec.js 파일에서는

``` javascript
// Hello.vue 파일을 마운트(구동)하고  h1에 text를 'welcome to hanu vue template'를 기대한다...
// h1의 text가 'welcome to hanu vue template'가 아니라면 에러!
describe(‘Hello.vue’, () => {
  it(‘should render correct contents’, () => {
    /const/Constructor = Vue.extend(Hello);
    /const/vm = /new/Constructor().$mount();
    expect(vm.$el.querySelector(‘.hello h1’).textContent)
      .to.equal(‘Welcome to Hanu Vue Template’);
  });
});
```














