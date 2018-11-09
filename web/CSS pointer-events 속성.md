# CSS pointer-events 속성
## pointer-events
* 포인터 이벤트의 제어

### pointer-events의 속성 값
* none: HTML 요소에 정의된 클릭, 상태(hover, active등), 커서 옵션들이 비활성화 됨
* auto: HTML 요소에 정의된 클릭, 상태(hover, active등), 커서 옵션들이 활성화 됨
* inherit : 부모 요소로부터 pointer-events 값을 상속

* svg에서만 사용가능한 속성들
	* stroke, fill, all …

### 주의할 점
* 이벤트 버블링이나 이벤트 캡쳐링에 의해 pointer-events를 껐음에도 불구하고 이벤트가 실행되는 경우가 있다
	* 이런경우 이벤트 전파 방지 필요