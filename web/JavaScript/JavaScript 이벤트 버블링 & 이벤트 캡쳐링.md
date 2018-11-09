# JavaScript 이벤트 버블링 & 이벤트 캡쳐링
* 이벤트 전달/차단과 관련된 현상

## 이벤트 버블링 (Bubbling)
* 엘리먼트에서 이벤트가 감지 되었을때, 해당 엘리먼트를 포함하고 있는 부모 엘리먼트를 통하여 최상위 까지 이벤트가 전달되는 현상
``` html
<div onclick="alert('First')">First Element
  <div onclick="alert('Second')">Second Element
    <div onclick="alert('Third')">Third Element</div>
  </div>
</div>

// 어떤 엘리먼트를 누르던지 이벤트 버블링이 발생해서 자기부터 최상위 엘리먼트까지 click이벤트가 실행된다
// 엘리먼트가 각각 따로 떨어져있어도 이벤트 버블링이 발생!
```

### 이벤트 버블링 방지
* 이벤트 함수 내부에 `event.stopPropagation()` 메소드를 사용
* 엘리먼트에 여러개의 이벤트가 걸려있다면 `event.stopImmediatePropagation()` 메소드 통해 전체를 막는다

- - - -

## 이벤트 캡쳐링 (Capturing)
* window로 부터 최초 이벤트가 발생한 자식 엘리먼트로 내려가는 현상
* 버블링에 비해 사용빈도가 적다
* addEventListener()의 세번째 파라미터(true/false)를 통해 캡쳐링, 버블링 단계를 확인할 수 있다

- - - -

## 이벤트 전파 중지 방법
### event.perventDefault()
* 현재 이벤트의 동작을 중지한다
``` javascript
$("#aTag").on("click",function(event){
	console.log('a태그 click');  
	// 이벤트의 기본 동작을 중단한다
	// a태그를 클릭해도 이동하지 않는다
	event.preventDefault();
});
```

### event.stopPropagation()
* 현재 이벤트가 상위로 전파되지 않도록 중단한다
``` javascript
// 부모의 click 이벤트 설정
$("#parent").on("click", function(event){
	console.log('parent click');
});

// 자식의 click 이벤트 설정
$("#child").on("click", function(event){
	console.log('chlid click');
	// 상위로 이벤트가 전파되지 않도록 중단한다
	event.stopPropagation();
});

// child를 누르면 parent의 이벤트는 실행되지 않는다
```

### event.stopImmediatePropagation()
* 현재 이벤트가 상위 뿐 아니라 현재 엘리먼트에 걸린 다른 이벤트도 동작하지 않도록 중단한다
``` javascript
// div 영역에 첫번째 click 이벤트 설정
$("#first").on("click", function(event){
	console.log('첫번째 이벤트');
	// 상위 뿐 아니라 같은 레벨로도 이벤트가 전파되지 않도록 중단
	event.stopImmediatePropagation();
});

// div 영역에 두번째 click 이벤트 설정
$("#second").on("click", function(event){
	console.log('두번째 이벤트');
	// 상위로 이벤트가 전파되지 않도록 중단한다
	event.stopPropagation();
});

// event.stopImmediatePropagation()에 의해 두번째 클릭 이벤트는 실행되지 않는다!
```

### return false
* jQuery를 사용 할때는 event.preventDefault()와 event.stopPropagation()을 함께 수행한 것과 같고
* jQuery를 사용하지 않을때는 event.preventDefault()와 같다
``` javascript
$("#aTag").on("click",function(event){
	console.log('a태그 click');

	// jQuery 이벤트의 경우,
	// return false는 event.stopPropagation()과 event.preventDefault() 를
	// 모두 수행한 것과 같은 결과를 보인다
	return false;
});

// 부모로 이벤트 전파도 되지않고 현재 이벤트도 실행되지 않는다
```

- - - -

## 이벤트 target
### event.target
* 최초 이벤트가 발생하는 엘리먼트

### event.currentTarget
* 실제로 이벤트가 실행되는 엘리먼트

