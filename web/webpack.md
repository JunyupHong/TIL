# webpack
* JavaScript 모듈화 도구
* 모듈 번들러

* 자바스크립트 코드가 많아지면 하나의 파일로 관리하는데 한계가 있다
* 그러나 여러개의 파일을 브라우저에서 로딩하면
	* 시간과 비용 측면에서의 단점
	* 각 파일은 서로의 스코프를 침범하지 않아야하는데 잘못 작성하면 변수 충돌의 위험성도 존재

* 자바스크립트는 언어 자체가 지원하는 모듈 시스템이 없다 => 즉시호출함수(IIFE)를 사용해 모듈을 만들어 사용 => CommonJS나 AMD스타일의 모듈 시스템을 이용하면 파일별로 모듈을 관리할 수 있다

> **< CommonJS & AMD >**  
> JavaScript의 표준을 만드는 그룹 (표준 API 라이브러리 제작)  
>   
> CommonJS의 모듈 사용 (모든 파일이 로컬 디스크에 있어야함 => 서버사이드 JavaScript 환경을 전제로함)  
> 	- export라는 전역 객체로 모듈을 정의, 다른 모듈에서 require() 함수로 공유된 모듈을 사용  
>   
> AMD의 모듈 사용 (네트워크를 통해 파일을 받아야하는 브라우저와 같은 환경에서는 CommonJS보다 유연한 방법을 제공 / 비동기환경에서도 작동)  
> 	- export 형태로 모듈 정의, require()함수 사용 가능  
> 	- define() 함수로 파일 스코프의 역할을 함  


* 하지만 브라우저에서는 파일 단위 모듈 시스템을 사용하는 것은 쉽지않다 => 웹 프론트엔드 개발 과정에서는 모듈을 IIFE 스타일로 변경해주는 과정 뿐 아니라 하나의 파일로 묶어(bundled)서 네트위크 비용을 최소화 할 수 있는 방법이 필요하다
* => webpack을 사용!


* webpack의 장점
	* 모듈 시스템을 구성 => 편리한 모듈 의존성 관리
	* 로더 사용 => 다양한 리소스를 효율적으로 사용
	* 빠른 컴파일 속도
	* 등등


## webpack 사용
* webpack은 Node.js 가 설치된 환경에서 실행된다

### 설치
* `$ npm install webpack -g`

### 컴파일
* `$ webpack <엔트리 파일 경로> <번들 파일 경로>`
* 엔트리 파일을 시작으로 의존 관계에 있는 모듈을 엮어서 하나의 번들 파일을 만드는 작업 (하나의 엔트리 파일은 하나의 번들 파일을 만듬)
* ![webpack-compile](https://user-images.githubusercontent.com/39546874/49129856-b9637a80-f314-11e8-82d4-ae48d148d108.png)
* 컴파일 명령어에 `--watch` 옵션을 사용하면 모듈 파일이 변경될 때마다 변경된 모듈을 자동으로 다시 컴파일한다


### 엔트리
* webpack에서 모든 것은 모듈이다 => 자바스크립트, 스타일시트, 이미지 등 모든 것을 자바스크립트 모듈로 로딩해서 사용
* ![webpack-dependency-graph](https://user-images.githubusercontent.com/39546874/49129256-57a21100-f312-11e8-9617-672d6ff6d1fc.jpg)
* 자바스크립트가 로딩하는 모듈이 많아질수록 모듈간의 의존성은 증가
* 의존성 그래프의 시작점을 webpack에서는 엔트리라고 한다
* webpack은 엔트리를 통해서 필요한 모듈을 로딩하고 하나의 파일로 묶는다
* 이렇게 하나로 만들어진 파일을 엔트리 파일이라고한다
``` javascript
module.export = {
	entry: {
		main: './src/main.js'
	}
}
```


### 아웃풋 (번들 파일)
* 엔트리에 설정한 자바스크립트 파일을 시작으로 의존되어 있는 모든 모듈을 하나로 묶은(번들링) 파일을 컴파일한 결과
* html에서는 이 번들파일을 로딩하게 한다
``` javascript
// dist 폴더에 bundle.js에 컴파일된 결과를 저장한다
module.export = {
	output: {
		filename: 'bundle.js',
		path: './dist'
	}
}
```




## 로더
* 다양한 리소스를 JavaScript에서 바로 사용할 수 있는 형태로 로딩하는 기능
* webpack은 자바스크립트 밖에 모르므로 자바스크립트 파일이 아닌 파일을 webpack이 이해하게끔 변경해준다

* 로더의 종류 -> 반환되는 결과
	* json-loader (데이터) -> 데이터 객체
	* handlebars-loader (템플릿) -> 템플릿 함수
	* coffescript (개발언어) -> JavaScript
	* css-loader, style-loader 등등 

* 로더는 test에 로딩할 파일을 지정 / use에 적용할 로더를 설정한다

### 로더 사용
* 설치
	* `$ npm insatll handlebars-loader`
	* `$ npm install babel-loader babel-core babel-preset-env --save-dev `
* 설정 추가
``` javascript
// webpack.config.js 파일
module.exports = {  
	output: {
		// ...
	},
	module: {
		rules: [
			{	// handlebars-loader 설정
				test: /\-tpl.html$/,
				use: 'handlebars-loader'
			},
			{	// babel-loader 설정
				test: /\.js$/,
				exclude: 'node_modules',
				use: {
					loader: 'babel-loader',
					options: {
						presets: ['env']
        			}
				}
			},
			{	// 여러 loader 설정
				// 로더는 오른쪽에서부터 왼쪽으로 실행된다
				// => sass -> css -> style
					// css 파일은 style, css 로더를 갖는다
				test: /\.css$/,
				use: [
					{ loader: 'style-loader' },
					{
						loader: 'css-loader',
						options: {modules: true}
					},
					{ loader: 'sass-loader' }
				]
			}
		]
	}
}
```




## 플러그인
* 로더는 파일단위로 처리
* 플러그인은 번들된 결과물을 처리 => 번들된 자바스크립트를 난독화, 특정 텍스트 추출 등

### 종류
* UglifyJsPlugin
	* 로더로 처리된 자바스크립트 결과물을 난독화 처리하는 플러그인
``` javascript
const webpack = require('webpack')

module.exports = {
	plugins: [
		new webpack.optimize.UglifyJsPlugin()
	]
}
```

* ExtractTextPlugin
	* css의 전처리기인 sass를 사용하려면 기존의 css파일을 sass파일로 변경해서 코딩한 뒤 webpack에서 sass-loader를 추가해야한다 => bundle.js 파일에 포함된다
``` javascript
// webpack.config.js 파일
module.exports = {
	module: {
		rules: [{
				test: /\.scss$/,
				use: ['style-loader', 'css-loader', 'sass-loader']
		}]
	}
};

// 결과: bundle.js 파일이 생성된다
```

	* sass 파일이 매우 커진다면 분리하는 것이 효율적 => style.css 파일로 따로 번들링
``` javascript
// webpack.config.js 파일
const ExtractTextPlugin = require('extract-text-webpack-plugin')

module.exports = {
	module: {
		rules: [{
			test: /\.scss$/,
			use: ExtractTextPlugin.extract({
				fallback: 'style-loader',
				use: ['css-loader', 'sass-loader']
			})
		}]
	},
	// 플러그인 추가
	plugins: [
		new ExtractTextPlugin('style.css')
	]
};

// 결과: bundle.js 파일과 style.css가 생성된다
```











