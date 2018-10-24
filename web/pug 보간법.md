# pug 보간법
## String Interpolation (문자열 보간)
* `- var 변수명`
	* 변수를 등록할 수 있다
* 변수 사용
	* `태그= 변수명`  (변수만 사용시)
	* `태그 #{변수명}` (변수와 string을 같이 사용시)
* 함수 사용
	* `#{함수}`
* # , { , } (특수문자) 사용
	* `#{''}`으로 묶어서 사용 ( ‘’ 안에있는 문자를 텍스트로 처리)
	* `\#{}`으로 묶어서 사용 ( {} 안에 있는 문자를 텍스트로 처리)
``` pug
- var title = "On Dogs: Man's Best Friend";
- var author = "enlore";
- var theGreat = "<span>escape!</span>";
- var msg = "it will change upper!";

h1= title
p author: #{author}
p This will be safe: #{theGreat}

p msg: #{msg.toUpperCase()}

p Escaping works with \#{interpolation}
p Interpolation works with #{'#{interpolation}'} too!
```
``` html
<h1>On Dogs: Man's Best Friend</h1>
<p>author: enlore</p>
<p>This will be safe: &lt;span&gt;escape!&lt;/span&gt;</p>

<p>msg: IT WILL CHANGE UPPER!</p>

<p>Escaping works with #{interpolation}</p>
<p>Interpolation works with #{interpolation} too!</p>
```


- - - -

## Whitespace Control
``` pug
//  | 사용
p
	| If I don't write the paragraph with tag interpolation, tags like
	strong strong
	| and
	em em
	| might produce unexpected results.

//  . 사용
p.
	If
	I
	do,
	whitespace
	is
	#[strong respected]
	and
	#[em everybody]
	is
	happy.
```
``` html
<p>If I don't write the paragraph with tag interpolation, tags like<strong>strong</strong>and<em>em</em>might produce unexpected results.</p>
<p>If I do, whitespace is <strong>respected</strong> and <em>everybody</em> is happy.</p>
```

