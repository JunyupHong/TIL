# VS Code Setting & 단축키
## Setting
1. IDE 명령어에 shell 검색 => shell command 설치
	* 프로젝트 폴더에서 `$ code .`를 입력하면 VSCode가 실행
2. prettier 설정
	* 	settings (command + ,)에 가서 Prettier: Use tab 설정 끄기
3. Keyboard shortcut(Command + k + s) 에서 format document 설정 바꾸기
	* 	command + option + l 로 바꾸기

4. extension 설치
* Prettier - code formatter
	* Lint의 한 종류

* gitLens
	* git과 연동해서 누가 언제 코드를 작성했는지 보여준다

* Vue.js Extension Pack
	* Vuejs를 사용하기 위한 extension

* Vue VSCode snippets
	* 매크로적으로 코드를 모아두고 사용
	* vbase: vue초기 세팅 매크로 (template, script, style이 적용됨)
	* vmethod: method 사용 매크로 등등
	* IDE에서 Preferences: Configure User Snippets를 검색해서 새로운 snippet을 추가해서 직접 매크로를 만들 수 있다
	* Vue snippet 세팅: [VSCODE Sass, Vue, Vue Snippet 세팅하기 – Vue.js 한국 사용자 모임](http://vuejs.kr/jekyll/update/2017/02/21/vscode-vue-development-settings/)

* TSLint
	* TypeScript의 lint

* Add jsdoc comments
	* 주석을 자동으로 달아준다
	* 사용: 함수의 첫 줄을 드래그하고 IDE 명령으로 Add Doc Comment를 실행하면 된다

* vscode-icons
	* icon 형태를 변경




## VS Code 프로젝트 생성하기
1. 프로젝트 생성
2. npm 세팅
* `$ npm init`
* package.json을 만든다
* 모든 node 프로젝트들은 package.json으로 돌아간다!
```
package name: (first-ts)	// 프로젝트 이름
	version: (1.0.0) 0.0.0	// 나중에 업데이트를 위해서 0.0.0으로 설정
	description: my first ts project	// 프로젝트 설명
	entry point: (index.js)	
	test command: 
	git repository: 	// git repository가 있으면 설정
	keywords: 
	author: junyup.hong@gmail.com	// 작성자
	license: (ISC) 		// 배포시 라이센스 설정 ex) MIT
	About to write to /Users/junyup/Develop/Project.Web/first-ts/package.json:

	{
  	"name": "first-ts",
  	"version": "0.0.0",
  	"description": "my first ts project",
  	"main": "index.js",
  	"scripts": {
  	  "test": "echo \"Error: no test specified\" && exit 1"
  	},
  	"author": "junyup.hong@gmail.com",
  	"license": "ISC"
	}


	Is this OK? (yes) 
```

3. dist, src 폴더 생성

4. src 폴더에 index.ts 파일 생성

5. tsc 설정하기
* `$ npm install typescript --save-dev`
* 사용법 (npx를 설치했으면 npx를 이용 / 설치 안했으면 node_moduels의 tsc에 직접 접근해서 이용)
	* `$ npx tsc --init` => tsconfig.json 파일 생성
	* `$ npx tsc` => tsc 실행
	* 또는 `$ ./node_modules/.bin/tsc tsc --init` / `$ ./node_modules/.bin/tsc tsc`
* tsconfig.json에 필요한 설정 (기본 설정이 되어있다 => 변경하기)
``` javascript
{
	"compilerOptions": {
		"target": "es6",
		"module": "commonjs",
		"outDir": "dist",
		"sourceMap": true
	}
}
```

> **< typescript >**  
> TypeScript 파일을 JavaScript 파일로 바꿔주는 라이브러리  
>   
> **< npx >**  
> node_modules에 있는 모듈을 쓸 수 있게 해주는 라이브러리  


## 단축키
* `command + p`			: 현재 폴더에서 명령
* `command + shift + p`	: IDE 자체에 명령
* `command + ,`			: settings
* `ctrl + shift +  ₩` or `아래부분 스크롤` : terminal
* `Command + k + s`		: Keyboard shortcut





