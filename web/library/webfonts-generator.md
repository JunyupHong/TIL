# webfonts-generator
* svg이미지를 font로 바꿔준다
* 이미지를 font로 바꿔주면 style을 적용하기 쉽다

- - - -

## 설치
``` shell
$ npm install --save-dev webfonts-generator
```

- - - -

## 사용법
1. webfont.generator.js 파일을 만든다
``` javascript
// webfont.generator.js 파일
	// 이 파일은 빌드할때마다 babel을 사용해 매번 새로 부를 필요가 없다
	// -> 파일을 src폴더 내부가 아닌 build에 만들어준다
const webfontsGenerator = require("webfonts-generator");
const fs = require("fs");
const iconPath = 'src/icons/';
const _ = require('lodash');

// files에는 iconPath경로에 있는 파일들이 들어있다
const files = _.chain(fs.readdirSync(iconPath))
  .filter(f => f.endsWith('.svg'))
  .map(f => `${iconPath}/${f}`)
	.value();

webfontsGenerator({
	files, // files에는 사용할 파일들이 들어간다
	dest: "src/svg-icons/", // 이 위치에 font로 변경된  icon이 들어간다
	html: true, // true 이면 html 미리보기(my-icons.html)를 생성해준다
	templateOptions: {
		baseSelector: ".icon-font", // 기본으로 추가될 클래스 이름(접두어! 예: .icon-font.classPrefix-iconName)
		classPrefix: "icon-font-"   // 벡터 이미지 이름과 조합하여 추가될 클래스 이름(예: icon-font-iconName)
	}
}, function(error) {
  if (error) {
    console.log("Fail!", error);
  } else {
    console.log("Done!");
  }
});
```

2. package.json 파일의 script에 `"build-icons": "node ./build/webfont.generator.js"`을 추가해준다
	* 	이때 경로가 다른데, webfont.generator.js 파일에서 실행하면 경로가 자기 자신이지만 node에서 실행하면 경로가 루트에서 시작되기 때문!

3. 이 아이콘들은 전역으로 사용하므로 App.vue의 style에서 `@import “./svg-icons/iconfont.css”;` 해준뒤 사용할 template에서 `.icon-font.icon-font-baseline-autorenew-24px`로 사용





