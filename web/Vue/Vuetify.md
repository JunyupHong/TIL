# Vuetify
## 컴포넌트
* Vue 에서 컴포넌트는 Vue 안에서 사용하는 페이지 단위에서 사용 `Vue.component()`
* Vue 에서 플러그인은 전역적으로 사용 `Vue.use()`
* **vue cli의 플러그인은 템플릿을 관리**

## Vuetify
* Vue의 세팅을 바꿔준다
* 아이콘을 쓸 수 있게 해줌 (material icons와 유사)
* vue cli의 플러그인
* material icons를 추가하는 것은 코드에서 플러그인을 추가하는것
* Vuetify는 터미널에서 추가 해줌 => 아예 세팅을 바꿔준다
* https://vuetifyjs.com : 필요한 ui 컴포넌트들이 들어있다 (아직 pug지원 X)

### Vuetify 설치
* vue cli 3 프로젝트 폴더 내부에서
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

* ts 설정에 vuetify 추가
	* tsconfig.json의 compilerOptions의 types에 vuetify 추가
``` json
"compilerOptions": {
	"types": ["webpack-env", "vuetify"],
}
```

