# JQuery 선택자 & eventHandler & 메소드

## 선택자(selector)
### 표기
	* $(‘*’)
	* $(‘태그’)
	* $(‘.class’)
	* $(‘#id’)
	* 	…
	
	---
	
### DOM selector
* `$(‘selector1 selector2’)`
	* selector1의 ‘자손’이면서 selector2와 일치하는 모든 엘리먼트에 적용
* `$(‘selector1 > selector2’)`
	* selector1의 ‘자식’이면서 selector2와 일치하는 모든 엘리먼트에 적용

* `$(‘selector1 + selector2’)`
	* selector1의 형제 엘리먼트 중 selector2와 일치하는 바로 다음에 나오는  엘리먼트 하나에만 적용
* `$(‘selector1 ~ selector2’)`
	* selector1의 형제 엘리먼트 중 selector와 일치하는 모든 엘리먼트에 적용

* `$(’selector1 : has(selector2)’)`
	* selector2를 자손으로 가진 selector1인 모든 엘리먼트에 적용

* `$(‘selector : first’)`
	* 선택자와 일치하는 것 중 페이지에서 처음으로 일치하는 엘리먼트
* `$(‘selector : last’)`
	* 선택자와 일치하는 것 중 페이지에서 마지막으로 일치하는 엘리먼트
	
* `$(‘selector : first-child’)`
	* 선택자와 일치하는 것 중 첫번째 자식 엘리먼트
* `$(‘selector : last-child’)`
	* 선택자와 일치하는 것 중 마지막 자식 엘리먼트
* `$(‘selector : only-child’)`
	* 선택자와 일치하는 것 중 형제가 없는 모든 엘리먼트
* `$(‘selector : nth-chlid(n)’)`
	* 선택자와 일치하는 것 중 n번째 자식 엘리먼트
* `$(‘selector : nth-chlid(even | odd)’)`
	* 선택자와 일치하는 것 중 짝수 또는 홀수 자식 엘리먼트
* `$(‘selector : nth-child(Xn + Y)’)`
	* 선택자와 일치하는 것 중 공식에 따른 번째의 자식 엘리먼트

* `$(‘selector : even | odd’)`
	* 페이지 전체의 짝수/홀수 엘리먼트

* `$(‘selector : eq(n)’)`
	* n번째로 일치하는 엘리먼트
* `$(‘selector : gt(n)’)`
	* n번째 이후의 엘리먼트
* `$(‘selector : lt(n)’)`
	* n번째 이전의 엘리먼트

* `$(this).parent().next()`
	* 현재 객체의 부모로부터 다음번째 엘리먼트
* `$(this).parent().prev()`
	* 현재 객체의 부모로부터 이전 엘리먼트

* `$(this).childrend()`
	* 현재 객체의 자식노드

	---

### 선택자의 attribute

* `$(selector[attr])`
	* selector 중 attr 속성(attribute)값을 가지는 엘리먼트 
	
* `$(selector[attr = ”value”])`
	* selector 중 attr 속성의 값이 value와 동일한 엘리먼트

* `$(selector[attr != ”value”])`
	*  selector 중 attr 속성의 값이 value와 같지 않는 엘리먼트

* `$(selector[attr ^= ”value”])`
	* selector 중 attr 속성의 값이 value 값으로 시작하는 엘리먼트
	
* `$(selector[attr $=”value”])`
	* selector 중 attr 속성의 값이 value 값으로 끝나는 엘리먼트

* `$(selector[attr *= ”value”]) `
	* selector 중 attr 속성의 값이 value 값을 포함하는 엘리먼트

* `$(selector[attr ~= ”value”])` 
	* selector 중 attr 속성의 값이 공백과 함께 value 값을 포함하는 엘리먼트
	 
- - - -


## 이벤트 핸들러
`$("선택자").bind();			// 이벤트 묶기`
`$("선택자").unbind();		// 이벤트 해제`
`$("선택자").click();			// 버튼클릭 = onclick`
`$("선택자").on('click', function());	// 버튼을 클릭하면 함수실행`
`$("선택자").change();		// 텍스트박스의 값이 변경될 때 = onchange`
`$("선택자").dbclick();		//더블클릭`
`$("선택자").focus();			//포커스가 주어질 때`
`$("선택자").keydown();		//키보드가 눌려 있을때`
`$("선택자").keyup();			//키보드가 눌렀다 떼었을때`
`$("선택자").keypress();		//키보드가 눌리는 순간`
`$("선택자").load();			//페이지를 전부 다 읽어들인 후에`
`$("선택자").hover(오버시 실행함수, 아웃시 실행함수);	//롤로버 이벤트핸들러`
`$("선택자").mousedown();		//마우스버튼 눌렀을때`
`$("선택자").mouseenter();	//객체영역에 마우스가 위치했을때`
`$("선택자").mouseleave();	//객체영역에서 마우스가 벗어났을때`
`$("선택자").mouseout();		//마우스 커서가 올려놓았다가 밖으로 나갈때`
`$("선택자").mouseover();		//마우스 커서를 올려놓았을때`
`$("선택자").mouseup();		//마우스버튼 눌렀다 떼었을때`
`$("선택자").scroll();		//스크롤바가 스크롤될때`
`$("선택자").trigger();`
`$("선택자").live();`
```
$("선택자").rotate({
	'duration' : '1000',			//이동속도
	'interval' : 2000,				//간격 (1000 = 1초)
	'stopButton' : '#stopButton',	//스톱버튼 (객체명)
	'playButton' : '#playButton',	//재생버튼 (객체명)
	'prevButton' : '#prevButton',	//이전버튼 (객체명)
	'nextButton' : '#nextButton',	//다음버튼 (객체명)
	'movement' : 'top | left | opacity',
	'autoStart' : true				// 시작시 자동재생
});
```

- - - -

## 속성
* $(‘selector’).속성()     == getter
* $(‘selector’).속성(‘값’)     == setter

* `$("선택자").attr("속성명", "속성값");	// 속성 값을 지정할때`
* `$("선택자").attr("속성명");			// 속성 값을 불러올때`
* `$("선택자").removeAttr("속성명");		// 속성을 삭제할때`
* `$("선택자").val();					// 객체의 값(value)을 가져올때`
* `$("선택자").val("50");				// 객체에 값(value)을 을 50으로 적용`
* `$("선택자").text();					// 객체의 텍스트를 가져온다.`
* `$("선택자").html();					// 객체의 HTML태그를 가져온다.`
* `$("선택자").html("<p>html 태그</p>");	// HTML태그를 삽입한다.`
* `$("선택자").addClass("클래스명");		// 객체에 클래스를 추가한다.`
* `$("선택자").removeClass("클래스명");	// 객체의 클래스를 삭제한다.`
* `$("선택자").toggleClass("클래스명");	// 객체에 해당클래스가 있으면 삭제, 없으면 추가`
* `$("선택자").css("스타일 속성 명","스타일 속성 값");	// CSS의 속성값을 지정`
* `$("선택자").css({"background":"yellow","height":"400px"});	// CSS 다중 지정`
* `$("선택자").css("스타일 속성 명");		// CSS 속성 값을 불러옴.`
* `$("선택자").width();`
* `$("선택자").index(this);				// 배열 객체 중 현재 선택된 인덱스 값`

- - - -

## 메소드
* add
```
$(“선택자1").add("선택자2").css("background-color","yellow");
	//선택자1, 선택자2 추가하여 css 공통 선언
```
* ready
`$("선택자").ready(function(){...}); // 페이지를 읽어들인 후에 함수 실행`
* hide
`$("선택자").hide();		// 감추기`
* show
`$("선택자").show();		// 보이기`
* toggle
`$("선택자").toggle();	// 감추어진것은 보이고, 보여진것은 감추는 토글모드`
* fadeOut
`$("선택자").fadeOut();	// 서서히 감춰지기`
* slideDown
`$("선택자").slideDown(100);		// 0.1초 동안 슬라이드로 내려오면서 보이기`
* slideUp
`$("선택자").slideUp(100);		// 0.1초 동안 슬라이드로 올라가면서 감추기`
* slideToggle
`$("선택자").slideToggle();		// 감추어진것은 내려오면서 보이고, 보여진것은 올라가면서 감추어지기(토글모드)`
* prepend
`$(선택자1).prepend(선택자2)		// 선택자1 자식요소 앞에 선택자2를 넣어라.`
* prependTo
`$(선택자2).prependTo(선택자1)		// 선택자2를 선택자1 자식요소 앞에 넣어라.`
*  append
`$(선택자1).append(선택자2)		// 선택자1 자식요소 뒤에 선택자2를 넣어라.`
* appendTo
`$(선택자2).appendTo(선택자1)		// 선택자2를 선택자1 자식요소 뒤에 넣어라.`
*  before
`$(선택자1).before(선택자2)		// 선택자1 요소 앞에 선택자2를 넣어라.`
* insertBefore
`$(선택자2).insertBefore(선택자1)	// 선택자2를 선택자1 요소 앞에 넣어라.`
* after
`$(선택자1).after(선택자2)			// 선택자1 요소 뒤에 선택자2를 넣어라.`
* insertAfter
`$(선택자2).insertAfter(선택자1)	// 선택자2를 선택자1 요소 뒤에 넣어라.`
 
* animate
`$("선택자").animate();`
`$("선택자").animate({"width":"300px"},500);	// 0.5초간 폭을 300px로 변환 `
* is
`$("선택자").is();`
* prevAll
`$("선택자").prevAll();`
* next
`$("선택자").next();`
* filter
`$("div").filter(".abc");	// div요소 중에서 ".abc"인 것만 걸러서 선택.`
* find
`$("#grp").find(".abc"); 	// #grp 자손에서 ".abc"인 요소만 찾는다.`
* hasClass
`$("선택자").hasClass("클래스명");	// 객체가 해당클래스을 포함하고 있는지 체크 (Boolean값 return)`
* addClass
`​$("선택자").addClass("클래스명");		// 해당 클래스 추가하기`
* removeClass
`$("선택자").removeClass("클래스명");	// 해당 클래스 제거하기`
* toggleClass
`$("선택자").toggleClass("클래스명");	// 토글모드로 클래스 포함 or 제거`

* prevAll
```
$("선택자").prevAll( [ selector ] );	// 선택자 이전의 모든 요소
				// [selector] 요소를 선택하기 위해 필요한 선택자 문자열
```
* prev
`$("선택자").prev();			// 선택자 이전 요소.`
* next
`$("선택자").next();			// 선택자 다음 요소.`
`$("선택자").parent();		// 선택자 부모 요소.`

* 기타
```
$("선택자").ajax();
$("선택자").load();
$("선택자").get();
$("선택자").get("result.php",{"code":"a110"});	// result.php?code=a110
$("선택자").getJSON();
$("선택자").post();
$("선택자").post("send.php",{foo:"bar"},
		function(response){
		alert(response);
});
```





