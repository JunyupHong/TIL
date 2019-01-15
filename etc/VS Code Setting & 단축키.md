# VS Code Setting & 단축키
## Setting
1. IDE 명령어에 shell 검색 => shell command 설치
	* 프로젝트 폴더에서 `$ code .`를 입력하면 VSCode가 실행
2. prettier 설정
	* 	settings (command + ,)에 가서 Prettier: Use tab 설정 끄기
3. Keyboard shortcut(Command + k + s) 에서 format document 설정 바꾸기
	* 	command + option + l 로 바꾸기
4. user settings & workspace settings 설정
	* quote style 설정을 전부 single로 변경


5. extension 설치
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
	* Vue snippet 세팅: [VSCODE Sass, Vue, Vue Snippet 세팅하기 – Vue.js 한국 사용자 모임](http://vuejs.kr/jekyll/update/2017/02/21/vscode-vue-development-settings/) => $는 매크로 입력시 커서가 있는 위치(순서대로) => $1 에서 $2로 변경하려면 tab을 누르면 된다!

* TSLint
	* TypeScript의 lint

* Add jsdoc comments
	* 주석을 자동으로 달아준다
	* 사용: 함수의 첫 줄을 드래그하고 IDE 명령으로 Add Doc Comment를 실행하면 된다

* vscode-icons
	* icon 형태를 변경




## VS Code npm 프로젝트 생성하기
1. 프로젝트 폴더 생성

2. npm 세팅 (직접세팅하는 방법)
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

2. npm 세팅 (만들어진 세팅을 가져오는 방법)
* `$ git clone https://github.com/hyunwoo/typescript-library-starter <Project NAME>`
* `$ npm install`
* => 아래 3,4,5번 순서를 진행할 필요가 없다 (6번 ts-node만 설치)
* git repository는 따로 설정해주어야함 (.git파일만 생성되어있음)

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

6. ts-node 설치
* `$ npm install ts-node --save-dev`
* ts파일을 javascript파일로 변환을 하지않고 바로 실행시켜준다
* 사용법
	* `$ npx ts-node tsFile.ts`


## typescript & lint & prettier를 사용한 프로젝트 설정
1. setting json 변경 (cmd + shift + p => settings 검색, json 파일 보기)
``` json
{
  "typescript.locale": "ko",
  // "editor.fontSize": 12,
  // "terminal.integrated.lineHeight": 1.2,
  "terminal.integrated.letterSpacing": 0.5,
  // "terminal.integrated.fontSize": 12,
  "terminal.integrated.fontWeight": "400",
  "terminal.integrated.fontWeightBold": "600",
  "terminal.integrated.fontFamily": "Monaco",
  "editor.tabCompletion": "on",
  "editor.fontFamily": "Monaco, Menlo, monospace",
  "editor.tabSize": 2,
  "editor.insertSpaces": true,
  "editor.detectIndentation": false,
  // "html/html-extensions": [".html"],
  "eslint.validate": [
    {
      "language": "vue",
      "autoFix": true
    },
    {
      "language": "html",
      "autoFix": true
    },
    {
      "language": "javascript",
      "autoFix": true
    }
  ],
  // "markdown.preview.fontSize": 16,
  // "rest-client.fontSize": 16,
  "prettier.tslintIntegration": true,
  "prettier.stylelintIntegration": true,
  // "window.zoomLevel": 1,
  "javascript.preferences.quoteStyle": "single",
  "typescript.preferences.quoteStyle": "single",
  "prettier.singleQuote": true,
  "editor.formatOnPaste": true,
  "prettier.useTabs": false,
  "prettier.tabWidth": 2,
  "tslint.exclude": ["**/*.js"],
  "files.associations": {
    "*.vue": "vue"
  },
  "html.format.wrapAttributes": "force-aligned",
  "vetur.format.defaultFormatter.js": "vscode-typescript",
  "vetur.format.defaultFormatter.html": "js-beautify-html",
  "vetur.format.defaultFormatterOptions": {
    "js-beautify-html": {
      "wrap_attributes": "force-aligned"
    }
  },
  "javascript.format.insertSpaceBeforeFunctionParenthesis": true,
  "eslint.autoFixOnSave": true,
  "editor.formatOnSave": true
}
```

2. vue.config.js 파일 변경
	* devServer: {} => 외부에서 접근이 안될때가 있다! 이때 이 설정필요!
	* proxy => localhost가 아닌 외부에서의 접근(ex 192.168. ..)이 막힐때 사용
	* baseURL => `github.io`에서 페이지를 만들때 모든 repository가 연결되고 각각의 repository의 기본 base url이 된다 ex) `https://junyup0319.github.io/anotherproject`로 접속하면 baseUrl이 anotherproject 프로젝트가 띄워진다
``` javascript
module.exports = {
  devServer: {
    host: '0.0.0.0',
    disableHostCheck: true,
    proxy: 'http://localhost:8080'
  },
  baseUrl: '/',
	outputDir: undefined,
	assetsDir: undefined,
	runtimeCompiler: undefined,
	productionSourceMap: undefined,
	parallel: undefined,
	css: undefined,
}
```

3. tsconfig.json 파일 변경
	* ts 파일 설정
	* `"noImplicitAny": false` 는 import 시 에러를 꺼주는 것
``` json
{
  "compilerOptions": {
    "target": "esnext",
    "module": "esnext",
    "strict": true,
    "jsx": "preserve",
    "importHelpers": true,
    "moduleResolution": "node",
    "experimentalDecorators": true,
    "esModuleInterop": true,
    "allowSyntheticDefaultImports": true,
    "sourceMap": true,
    "baseUrl": ".",
    "resolveJsonModule": true,
    "noImplicitAny": false,
    "types": ["webpack-env"],
    "paths": {
      "@/*": ["src/*"]
    },
    "lib": ["esnext", "dom", "dom.iterable", "scripthost"]
  },
  "include": [
    "src/**/*.ts",
    "src/**/*.tsx",
    "src/**/*.vue",
    "tests/**/*.ts",
    "tests/**/*.tsx"
  ],
  "exclude": ["node_modules"]
}
```

4. tslint.json 설정
	* ts파일의 lint 설정
``` json
"rules": {
	"quotemark": [true, "single"],
}
```

5. .prettierrc 파일 생성 후 변경
	* vue 파일의 prettier 설정
``` json
{
  "singleQuote": true,
  "trailing-comma": false,
  "no-submodule-imports": false,
  "no-console": false,
  "no-implicit-dependencies": false
}
```



## 단축키
* `command + p`			: 현재 폴더에서 명령
* `command + shift + p`	: IDE 자체에 명령
* `command + ,`			: settings
* `ctrl + shift +  ₩` or `아래부분 스크롤` : terminal
* `command + k + s`		: Keyboard shortcut
* `command + .`			: suggestion 보여주기







