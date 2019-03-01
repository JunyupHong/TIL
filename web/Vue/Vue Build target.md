# Vue Build target
* 앱 전체를 빌드하는게 아니라 라이브러리 형태로 빌드할 수 있다
	* => 컴포넌트를 단위별로 빌드 할 수 있다

* `$npm run build` 는 모든 src폴더의 파일을 번들링해서 dust 폴더에 파일을 만들어 준다
* `$vue ui` 로도 설정 가능 (Vue UI의 build에서 설정)


## 사용
1. `$ npx vue-cli-service build --target lib --name libName [entry]`
* 타겟을 라이브러리로 지정하고, entry 파일을 build 한다
* entry로 설정한 파일만 라이브러리로 build 되어서 dist 폴더에 파일이 생성된다.(이전에 build한 파일이 없어지고 새로 생성됨)

2. npm 에 라이브러리를 올린다
	* .npmignore 파일을 만든다
	* package.json설정
	* => publish 할 때 올릴 파일들(dist폴더 내의 모든 파일) `"files": [ "dist/*", ],` 를 넣어준다

2. `$ npm publish`
	* $ npm publish하면 올라간다 (user를 등록해야함)

> <npx>  
> node_modules에 있는 라이브러리를 실행 할 수 있게 해줌  


- - - -

## 참고
* vue document
	* https://cli.vuejs.org/guide/build-targets.html#app

* How to create, publish and use your own VueJS Component library on NPM using [@vue/cli](https://github.com/vuejs/vue-cli) 3.0
	* https://medium.com/justfrontendthings/how-to-create-and-publish-your-own-vuejs-component-library-on-npm-using-vue-cli-28e60943eed3

