# Vue cli 3
## 설치
* `$ npm install -g @vue/cli`
* npm 설정
```
? Please pick a preset: Manually select features
? Check the features needed for your project: Babel, Router, Vuex, CSS Pre-proce
ssors, Linter
? Use history mode for router? (Requires proper server setup for index fallback 
in production) Yes
? Pick a CSS pre-processor (PostCSS, Autoprefixer and CSS Modules are supported 
by default): Sass/SCSS
? Pick a linter / formatter config: Prettier
? Pick additional lint features: Lint on save, Lint and fix on commit
? Where do you prefer placing config for Babel, PostCSS, ESLint, etc.? In package.json
? Save this as a preset for future projects? No
```
	* Vuex: 여러 컴포넌트에서 데이터를 관리하기 위한 것
	* CSS Pre-processor: 전처리기 / css를 sass와 scss로 처리 하기위함
	* e2e: end to end test / Java VM 위에서 chrome을 띄워서 테스트해줌
	* Use history mode for router: 라우터에서 앞으로가기, 뒤로가기를 구현
	* SPA: Single Page Application
	* PWA: 웹의 장점과 앱의 장점을 결합한 환경
	* ESLint + Prettier: Vue에서 prettier를 채택
	* 템플릿 저장하기 => 템플릿을 저장하면 나중에 설정할 때 `-p 저장한 템플릿 이름` 옵션을 이용해 불러올 수 있다
	


## 템플릿
* version 2 에서는 각각의 사용자가 템플릿을 만들어서 사용했다 (init으로 사용)
	* `$ vue init hyunwoo/vue-template`
* version 3 에서는 Vue에서 템플릿을 통일했다 (create로 사용)
	* `$ vue create <appName>`
	* 저장된 템플릿 불러오기 `$ vue create <appName> -p <저장된 템플릿 이름>`

* version 3 에서는 init 대신 create를 사용한다
	* init을 사용하려면 `$ npm install @vue/cli-init -g`로 init을 추가해준다!


## vue ui
* Vue 프로젝트 매니저
* 사용 => `$ vue ui`
* Vue cli 3 버전의 프로젝트들을 브라우저에서 보여준다
* 프로젝트 생성, 가져오기 기능 등이 가능

### 프로젝트 가져오기
* Kill port: 켜져있는 포트를 죽임
* 작업목록
	* package.json에 있는 스크립트 명령을 실행할 수 있다 => package.json에 스크립트 명령을 추가하면 추가도 가능
	* state를 볼 수 있다
	* console을 볼 수 있다



## Vuetify
### 컴포넌트
* Vue 에서 컴포넌트는 Vue 안에서 사용하는 페이지 단위에서 사용 `Vue.component()`
* Vue 에서 플러그인은 전역적으로 사용 `Vue.use()`
* **vue cli의 플러그인은 템플릿을 관리**

### Vuetify
* Vue의 세팅을 바꿔준다
* 아이콘을 쓸 수 있게 해줌 (material icons와 유사)
* vue cli의 플러그인
* material icons를 추가하는 것은 코드에서 플러그인을 추가하는것
* Vuetify는 터미널에서 추가 해줌 => 아예 세팅을 바꿔준다
* https://vuetifyjs.com : 필요한 ui 컴포넌트들이 들어있다 (아직 pug지원 X)


### Vuetify 설치
* `$ vue add vuetify`
```
📦  Installing vue-cli-plugin-vuetify...

+ vue-cli-plugin-vuetify@0.4.6
added 1 package from 1 contributor in 8.604s
✔  Successfully installed plugin: vue-cli-plugin-vuetify

? Choose a preset: Default (recommended)

🚀  Invoking generator for vue-cli-plugin-vuetify...
📦  Installing additional dependencies...

added 10 packages from 46 contributors in 14.679s
⚓  Running completion hooks...

✔  Successfully invoked generator for plugin: vue-cli-plugin-vuetify
   The following files have been updated / added:

     src/assets/logo.svg
     src/plugins/vuetify.js
     package-lock.json
     package.json
     public/index.html
     src/App.vue
     src/components/HelloWorld.vue
     src/main.js
     src/views/Home.vue

   You should review these changes with git diff and commit them.
```










