# pug & sass
* pug(jade) : HTML을 대체
* sass : CSS를 대체

pug와 sass를 사용


## 사용법
### setting
	1. 폴더를 만든다
	2. $ express -v pug -c sass 
	=> -v [VIEW MODE]	=> VIEW 를 pug로 만들겠다
	=> -c [STYLE MODE]	=> STYLE 을 sass로 만들겠다
	3. $ npm install
	4. web-storm으로 연다
	5. www파일에서 port설정, app.js 파일에서 router 설정


### /bin/www 파일
```
	// 포트번호
	var port = normalizePort(process.env.PORT || '3000');
```


### app.js 파일
```
	/** 외부접속 가능한 위치 설정 **/
	// __dirname은 현재 경로, public은 public 폴더를 의미
	// 따라서 현재 경로에 외부에서 접속하게 열어준다!
	app.use(express.static(path.join(__dirname, 'public')));
	
	
	/** router **/
	var indexRouter = require('./routes/index');
	var usersRouter = require('./routes/users');
	
	// 요청을 하는 주소가 달라서 서버가 다른 응답을해줌..
	// 127.0.0.1:3000 로 들어가면 indexRouter
	// 127.0.0.1:3000/users 로 들어가면 userRouter
	// => 여기에서 큰묶음으로 묶는다
	app.use('/', indexRouter);
	app.use('/users', usersRouter);
```



### /routes/ .js 파일
```
    router.get('/test', function(req, res, next) {

    // send는 문자열을 보내는것!
    res.send('hello!!');

    // render는 template engine에 따라 파일을 html로 변환하여 전송한다.
    // 여기에 연결할 pug파일을 넣어준다
    res.render('pugfile');
    });
```
```
res.render('.pug파일', {title: 'Hello'});
	=> 두번째 인자로 title을 넣어준다! -> 선택사항

res.render('.pug', {title: 'title', items:[{name: '재종'}, {name: '준엽'}, {name: '현식'}], });
	=> json데이터를 보냄
```


### /views/ .pug 파일
html을 pug파일로 작성
자동으로 pug파일을 html파일로 변환해서 읽는다
title= 웹페이지의 제목을 설정할 수 있다



### /public/stylesheets/ .sass 파일
css를 sass파일로 작성
run을 하면 .css파일이 생성된다.

## sass 문법
* ‘ < ’ , ’ > ’ , ’ , ’ , ’ ; ’ 가 없음

* `.ClassName`

* `#Id`

* 탭 (tab)으로 부모 & 자식이 결정
```
.parent-class
	.child-class
// .parent-class > .child-class
```

* & : 자기자신 (= this)
```
&.class		// == .class1.class2
&:hover		// class:hover
```

* attribute는 괄호로 묶는다
```
link(rel="stylesheet" type="text/css" href="style.css")
```

## pug 문법
* sass의 기본 문법과 같다

* 여러줄 입력할때 ‘|’ 로 작성
```
p
	| aasdfsdafsad
	| asdfsadfsd
```

* json 데이터 사용 가능

* 변수사용
```
	.text #{item[i].name}			// json데이터 사용

```

* **extends** & **block**
pug파일에 `extends 파일이름.pug` 을 선언 해주고
`block 블럭이름` 을 해주면 extend한 pug파일에 있는 같은 이름의 block을 읽는다 
```
extends layout		// layout에 있는 걸 읽을 수 있다!
						// 이후에 block이 나오면
						// layout.pug에 있는
						// 같은 이름의 block으로 대체한다!
						// extends 와 block 은 세트!!!!!

// ...
// ...

block content			// 읽다가 block을 만나면
						// layout.pug에 있는
						// block content로 선언된 부분을 읽는다.
						// 이후 다시 block아래의 코드를 읽는다
						// extends 와 block 은 세트!!!!!
// ...
// ...
```

* **include**
`include file.pug` 를 하면 file.pug파일에 있는 코드를 가져온다


## sass의 특징
* 반복문 사용가능
	(pug에서 반복문은 잘 사용되지않는다)
	(web-strom의 pug컴파일러가 반복문을 읽지 못해서 에러가 나는것처럼 보이지만 컴파일이 가능하다)
```
-for(var i=0; i< 12; i++) {
	.card(style='background:url(+i+.png)')	
-}

.card
	background-size: cover !important
	background-position: center !important
```
반복문 내에서 style로 background를 주게되면 다른 background속성이 무시된다.
무시되지 않으려면 `!important`를 사용한다


* **변수**를 사용가능
```
$button-height : 40px					// $button-height는 변수!
$color : darken(#f00, 10)				// darken은 어둡게 해줌
$color : lighten(#f00, 10)			// lighten은 밝게 해줌
```

* **@mixin** (매크로역할)
@content를 사용하면 mixin의 내부에 접근할 수 있다!!!!
```
@mixin inline-block				// @mixin의 inline-block을 정의
	display : inline-block
	vertical-align : top
	
@mixin backgroundImage($url)		// 인자를 받을 수도 있음
	background-image : url($url)
	background-size : cover

@mixin view-moblie
	@media screen and (min-width: 480px)
		@content					// @content를 이용하면
									// 사용하는 곳에서 내부에 접근이 가능!
```

**@include**로 사용
```
	.button
		@include inline-block		// inline-block의 매크로를 사용
		&:hover						// &는 자기자신(this)
		&.small						// = .button.small
	
		height: $button-height
		background: #f00
	
		.icon  			// icon은 button의 자식 == .button > .icon
			@include inline-block
			height: $button-height
			@include view-moblie
				background: white		// @content를 사용했으므로
										// 내부에 접근을 할 수 있다
```

* **@import**
 sass도 css처럼 import를 사용해서 여러 sass파일들을 import 할 수 있다.
큰 프로젝트를 할 때 스타일들을 여러 파일들로 나누고, 다른 파일에서 사용할 스타일을 불러와서 사용하면 된다.
css import와 다른 점은 css는 import된 여러 css 파일의 로딩을 http에 요청해야한다면, sass는 여러개의 sass파일을 import 해도 최종적으로 하나의 css로 컴파일해주기 때문에 여러번 요청을 보낼 필요가 없다.
참고로 scss 파일을 import 할 경우, scss 확장자를 써주지 않아도 된다.

**partial**
	만약에 .sass 파일이나 .sass 파일의 이름을 underscore(_)로 시작하면 css파일로 따로 컴파일되지 않는다. (라이브러리 역할)
	html에서 해당 css파일을 불러올일이 없고, import만 되는 경우에는 이 기능을 사용하면 된다.
```
@import "color"
@import "_button"
```

* **@extend**
sass 에서 특정 선택자를 상속 할 때, @extend 지시자를 사용한다.
```
.message {
  border: 1px solid #ccc;
  padding: 10px;
  color: #333;
}

.success {
  @extend .message;
  border-color: green;
}

.error {
  @extend .message;
  border-color: red;
}

// 아래는 html 코드 => class를 두개 만들어야 한다
.message, .success, .error {
  border: 1px solid #cccccc;
  padding: 10px;
  color: #333;
}

.message { border-color: green; }
.error { border-color: red; }

```


