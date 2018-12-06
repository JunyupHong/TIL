# Vue 인스턴스 생명주기
## 생명주기 (Life Cycle)
* **인스턴스의 상태에 따라서 호출할 수 있는 속성들** 을 생명주기라고 한다
* Vue 인스턴스는  (beforeCreate, created, beforeMounte, mounted, beforeUpdate, updated, beforeDestroy, destroyed) 8개의 생명주기를 가진다

## 라이프 사이클 훅 (Life Cycle Hook)
* **생명주기의 각 속성마다 개발자가 추가한 커스텀 로직** 을 라이프 사이클 훅이라고 한다

## Vue 인스턴스 생명주기
* Vue 인스턴스도 객체이기 때문에 생명주기를 가진다
* 속성의 종류로는 beforeCreate, created, beforeMount, mounted, beforeUpdate, update, beforeDestroy, destroyed로 총 8개 이다
* Vue 인스턴스 생명주기
<img width="704" alt="vue_instance_lifecycle_kor" src="https://user-images.githubusercontent.com/39546874/49574164-17323b00-f983-11e8-8755-8a9c7fad4e10.png">

### beforeCreated
* 인스턴스가 생성되고 나서 가장 처음으로 실행되는 라이프 사이클 단계
* 뷰 인스턴스의 data와 methods 속성이 정의되어 있지않고 DOM에도 접근할 수 없다

### created
* 뷰 인스턴스의 data 속성과 methods 속성이 정의되어 있다(두 속성에 접근 가능)
* 화면 요소에 인스턴스가 부착되기 전이기 때문에 template 속성에 정의된 DOM에 접근할 수 없다

### beforeMounted
* render() 함수가 호출되기 직전의 단계
* created 이후에 template 속성에 지정한 마크업 속성을 render() 함수로 변환한 후 el 속성에 지정한 화면에 인스턴스를 부착하기 직전에 호출되기 때문에 화면에 붙이기 전 실행해야 할 코드를 구현

### mounted
* el 속성에서 지정한 화면 요소에 인스턴스가 부착되고 난 후 호출되는 단계
* DOM에 접근, 제어 가능
* DOM에 인스턴스가 부착되자마자 호출되기 때문에 하위 컴포넌트나 외부 라이브러리에 의해 추가된 화면 요소들이 최종 HTML 코드로 변환되는 시점과 다를 수 있다

### beforeUpdate
* el 속성에서 지정한 화면 요소에 인스턴스가 부착되고 난 후에 인스턴스 속성들이 화면에 치환된다. 이 치환된 값을 $watch 속성으로 감시하는데, 이러한 작업을 데이터 관찰이라고 한다. 이렇게 관찰하고 있는 데이터들이 변경되면 가상 돔을 이용해 화면에 다시 그려야한다. 이때, 그리기 직전 호출되는 단계가 beforeUpdate이다
* 변경 예정인 데이터 값을 이용해 작업을 해야할때의 로직을 구현
* 값을 변경하는 로직을 구현해도 다시 화면에 그려지지는 않는다

### updated
* beforeUpdate가 끝나고 화면에 다시 그리고 나면 실행되는 단계
* 데이터가 변경되고 화면 요소를 제어하는것을 구현하고 싶을 때 이 단계에서 작업
* 하지만 이 단계에서 그 값을 또 변경하면 무한루프에 빠질 수 있다 (beforeUpdate -> update -> beforeUpdate -> update -> …)
* 따라서 데이터 값을 갱신하는 로직은 beforeUpdate에서 처리해야함

### beforeDestroy
* 뷰 인스턴스가 destroy 되기 직전에 호출되는 단계
* 아직 인스턴스가 없어지지 않았기 때문에 인스턴스에 접근 가능
* 뷰 인스턴스의 데이터를 삭제하거나 인스턴스가 사라지기 직전 해야하는 작업을 구현

### destroyed
* 뷰 인스턴스가 destroy되고 난 후 호출되는 단계
* 뷰 인스턴스에 정의한 모든 속성이 제거되고 하위에 선언했던 인스턴스들 역시 모두 destroy 된다