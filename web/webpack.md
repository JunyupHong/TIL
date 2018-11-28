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


## 엔트리
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


## 아웃풋 (번들 파일)
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
* UglifyJsPlugin -> webpack 4 에서 사라짐
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

- - - -



## webpack 4
* 설치
	* `$ npm install webpack -g`
	* `npm i -D webpack webpack-cli`  webpack 4 에서 추가

### webpack.config.js 파일
* webpack 설정파일
* 프로젝트 폴더 바로 아래에 있어야함 (package.json 파일과 같은 위치)

``` javascript
// webpack.config.js 파일
const webpack = require('webpack');
module.exports = {
	mode: 'development',
	entry: {
		app: '',
	},
	output: {
		path: '',
		filename: '',
		publicPath: '',
	},
	module: {

	},
	plugins: [],
	optimization: {},
	resolve: {
		modules: ['node_modules'],
		extensions: ['.js', '.json', '.jsx', '.css'],
	},
};
```

### mode
* webpack 4 에서 추가
* 속성
	* development: 개발용
	* production: 배포용 (최적화 => 기존 최적화 플러그인들이 대량으로 호환되지 않는다)

### entry
* webpack이 파일을 읽어들이기 시작하는 부분
``` javascript
// entry의 이름이 결과물의 파일 이름이 된다
entry: {
	app1: '파일 경로',	// app1.js 파일로 생성
	app2: '파일 경로'		// app2.js 파일로 생성
}
```
``` javascript
// 하나의 엔트리에 여러 파일을 넣을 경우 배열을 사용
	// a.js와 b.js가 한 파일로 엮여서 app.js라는 결과물이 생성
entry: {
	app: [ 'a.js', 'b.js' ]
}
```
	* 	파일 경로 대신 npm 모듈을 넣어도 된다 (ex @babel/polyfill)


### output
* 결과물 설정
``` javascript
output: {
	path: './dist',
	filename: '[name].js',
	publicPath: '/'
}
```
* 옵션
	* path: output으로 나올 파일이 저장될 경로
	* publicPath: 파일들이 위치할 서버 상의 경로
	* filename: ‘[name].js’ 로 적용하면 entry의 키 값으로 파일명이 바뀐다
	* hash: webpack 컴파일 시 랜덤한 문자열을 붙여준다 => 캐시 삭제가 유용
	* chunkhash: 파일이 달라질 때만 랜덤 문자열이 바뀐다 => 이 옵션을 사용하면 변경되지 않은 파일들은 계속 캐싱하고 변경된 파일만 새로 불러올 수 있다

### loader
* webpack 1 에서는 rules나 use 대신 loaders를 사용 / options 대신 query를 사용
* webpack 2 에서는 rules, use, options를 사용!
``` javascript
// babel-loader 예제
module: {
	rules: [{
		test: /\.jsx?$/,
		loader: 'babel-loader',
		options: {
			presets: [
				[
					'@babel/preset-env', {
							// node일 경우만
						targets: { node: 'current' },
						modules: 'false'
					}
				],
				'@babel/preset-react',
				'@babel/preset-stage-0'
			],
		},
		exclude: ['/node_modules']
	}]
}
```
	* test 정규식 조건에 부합하는 파일들을 loader에 지정한 로더가 컴파일 해준다.
	* options는 로더에 대한 옵션으로 설치한 preset들(babel preset)을 적용
	* exclude: 제외할 폴더나 파일 => loader로 컴파일 하지 않을 것들을 지정
	* include: 포함할 폴더나 파일 => loader로 컴파일 할 것들을 지정


### plugin
* 플러그인을 사용하면 효과적으로 번들링을 할 수 있다 => 압축, 난독화, 핫리로딩, 파일 복사 등
* webpack 3 에서 DedupePlugin이 사라짐 / OccurrenceOrderPlugin이 기본으로 켜짐(추가할 필요 없음)
* webpack 4 에서 ModuleConcatenationPlugin, UglifyJsPlugin, NoEmitOnErrorsPlugin, NameModules 플러그인이 사라짐 => webpack.config.js에 optimization 속성으로 대체

### optimization
* webpack 4 에서 최적화 관련 플러그인들이 모두 이 속성으로 통합됨
``` javascript
optimization: {
	minimize: true | false,
	splitChunk: {},
	concatenateModules: true | false
}
```
	* minimize: UglifyJsPlugin을 계승 (mode가 production일때 자동으로 켜짐)
	* splitChunks: CommonsChunkPlugin을 계승 (mode가 production일때 자동으로 켜짐)
	* concatenateModules: ModuleConcatenationPlugin을 계승

### resolve
* webpack이 알아서 경로나 확장자를 처리할 수 있게 도와주는 옵션
* modules 배열에 ‘node_modules’를 넣어주어야 디렉토리의 node_modules를 인식할 수 있다
* extensions 배열에 넣은 확장자들은 webpack에서 알아서 처리해주기 때문에 파일에 extensions배열에 있는 확장자들을 입력할 필요가 없다




## webpack 4 로 CSS 번들링
### 설치
* `$ npm insatll -D style-loader css-loader extract-text-webpack-plugin@next`
* next 버전 이어야 webpack4에서 사용가능
* css-loader는 css 파일을 읽어줌
* style-loader는 읽은 css 파일을 style 태그로 만들어 head 태그에 넣어준다
* style 태그 대신 css파일을 만들고 싶으면 플러그인으로 extract-text-webpack-plugin을 사용

### 설정
#### style 태그로 만들기
``` javascript
module: {
	rules: [
		{
			test: /\.jsx?$/,
			loader: 'babel-loader',
			options: {
				presets: [
					[
						'@babel/preset-env', {
							// node일 경우만
							targets: { node: 'current' },
							modules: 'false'
						}
					],
					'@babel/preset-react',
					'@babel/preset-stage-0'
				],
			},
		test: /\.css$/,
		use: ['style-loader', 'css-loader']
	}]
}
```

	* 여러개의 로더를 동시에 사용할땐 use를 사용한다
	* 로더는 뒤에서부터 실행된다 (css-loader -> style-loader)

* entry의 js 파일 상단에서 `require('app.css')`를 하면 알아서 바꿔준다


#### css 파일로 따로 만들기
``` javascript
module: {
	rules: [
			{
				test: /\.jsx?$/,
				loader: 'babel-loader',
				options: {
					presets: [
						[
							'@babel/preset-env', {
								// node일 경우만
								targets: { node: 'current' },
								modules: 'false'
							}
						],
						'@babel/preset-react',
						'@babel/preset-stage-0'
					],
				},
			},
			{
				test: /\.css$/,
				use: ExtractTextPlugin.extract({
				fallback: 'style-loader',
				use: 'css-loader'
				}),
			}
		],
	},
	plugins: [
		// 기타 플러그인
		new ExtractTextPlugin({
			filename: 'app.css',
		});
	]
}
```
* 옵션
	* fallback: 플러그인이 실패했을때 대안으로 작동
	* use: css-loader를 거친 후 extract-text-webpack-plugin으로 파일을 추출

* webpack을 실행하면 output에서 설정한 경로에 app.css파일이 생성된다
* css 파일을 기존에 css를 넣던 방식(link태그)으로 head에 넣으면 된다


## webpack 4 로 기타 파일 번들링
### 설치
* `$ npm install -D file-loader url-loader`
* file-loader는 특정 파일을 그대로 내보내줌
* url-loader는 설정한 사이즈 보다 작은 이미지나 폰트 파일을 인라인화 한다(base64로 인코딩)

### 설정
``` javascript
module: {
	rules: [{
			// 바벨로더와 css 로더들
		},
		{
			test: /\.(ico|png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
			loader: 'url-loader',
			options: {
				name: '[hash].[ext]',
				limit: 10000,
			},
		}
	],
}
```
	* limit: limit보다 작은 파일을 base64로 인코딩해서 인라인화 한다 / limit보다 큰 파일은 file-loader가 처리하여 파일로 내보낸다
	* name: [hash] - 랜덤문자열을 사용, [name] - 키를 이름으로 사용, [ext] - 확장자를 그대로 사용

* js 파일에서 `require(‘이미지 경로/파일.확장자’)` 를 하면 알아서 인라인화하거나 파일로 만들고 경로도 알아서 연결해준다
	




