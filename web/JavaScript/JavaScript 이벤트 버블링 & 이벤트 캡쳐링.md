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

## target
### event.target
* 최초 이벤트가 발생하는 엘리먼트

### event.currentTarget
* 실제로 이벤트가 실행되는 엘리먼트

