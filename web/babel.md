# babel
* ES6/ES7 코드를 ECMAScript5 코드로 transpiling 하기 위한 도구 (대부분의 인터넷환경에서 돌아갈 수 있게 자동으로 변환해줌)
* (pug파일을 html로 바꿔서 브라우저가 읽는 것도 이런 원리)

* 다양한 작은 모듈들로 구성
* 다양한 모듈을 담는 일종의 상자 역할을 하며 코드를 컴파일 하기 위해 작은 모듈들(ex. presets)을 사용
* 모듈 없이 babel만 사용하게 되면 그냥 파일을 복붙하게 됨


## 사용법
1. 프로젝트에 src폴더, dist폴더 생성
	* src에 js파일 작성하고
	* dist에 변환된 파일이 작성됨

2. `$ npm install babel-cli -g`
	* babel client를 global로 설치
	* babel-cli는 command line을 통해 코드를 transpile 할 수 있는 도구

3. `$ npm install babel-preset-env --save`
	* 바벨 프리셋 env를 설정해줌
	* 바벨의 초기 세팅(파일 변환시 설정을 자동으로 해줌)
	* babel에게 어떠한 정보를 전달해주지 않는 한 babel은 아무 작업도 수행하지 않는 ‘상자’에 불과(같은 파일ㅇ)
	* 이걸 해줘야 파일이 변환됨(안하면 똑같은 파일이 생성)

> —save를 해주면 package.json파일의 dependency에 추가가 되어서 pull 받을때 $npm install을 하면 자동으로 --save한 라이브러리들이 다 install 됨  

4. 프로젝트 안에 .babelrc 파일을 만들어서 설정
	* .babelrc 파일은 babel을 설정하기 위한 파일
	* babel에 설정 정보를 전달
``` json
{
	"presets": [
		"env"
	],
	"plugins": []

}
```

> 아직 공식 스펙에서 지원하지 않은 기능들을 transform-plugin을 추가하여 사용할 수 있다.  
> 여러 플러그인은 babel 공식 홈페이지에서 확인 가능  
> 추가로 설치한 플러그인은 plugins 옵션으로 추가  
> .babelrc파일에서 설정해줄 수도 있고 Webpack이란 도구에서도 설정 가능  

### <수동으로 변환>

5. 프로젝트 폴더에서
`$ babel ./src/babelExample.js -o ./out.js`
	* src의 babelExample.js를 out.js 파일로 변환

	* babel -> babel을 호출
	* babelExample.js -> transpile 하고자하는 ES6/ES7의 자바스크립트 파일
	* —out-file -> babel에게 전달할 옵션을 명시 (파일로 output을 지정하는 옵션)
> shortcut으로 -o 옵션을 제공  
> 이 이외에도 --out-dir or -d 옵션을 전달할 수 있다  
	* compiled.js -> 출력 파일의 이름을 명시
 

매번 이렇게 할수없으므로 아래 처럼 하면 자동으로 변환됨!!!

### <자동으로 변환>
#### npm script를 사용하여 해당 프로세스를 자동화

5. package.json 파일의 scripts에 "build" : "babel ._src --out-dir ._dist --watch" 추가
``` json
{
	"scripts" : {
		"build" : "babel ./src --out-dir ./dist --watch"
	}
}
```
	* babel ._src --out-dir ._dist --watch
	* 바벨이 ._src 폴더에있는 파일을 ._dist에 변환해줌 (src, dist 폴더가 있어야함!)
	* --watch => 파일이 변환된 경우 자동으로 바꿔줌

6. npm 명령어를 새로고침해주면 build가 생기고 그걸 실행해줌
	* $ npm run build 와 같다!
	
7. app.js 파일에서 `app.use(express.static(path.join(__dirname, 'dist')));`
를 추가해주면 루트 경로를 찾을때 dist파일도 루트로 설정됨





8. babel-polyfill 설정
	* promise같은경우 babel-polyfill을 설정해주지 않으면 파일을 변환하는 과정에서 에러가 날 수 있다... 따라서 설정해주어야함
8-1. $ install babel-polyfill
8-2. 변환할 pug파일에서 script 추가
	`script(src=“/babel-polyfill/dist/polyfill.js”)`

 


