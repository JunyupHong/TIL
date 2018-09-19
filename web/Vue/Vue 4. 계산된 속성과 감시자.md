# Vue 4. 계산된 속성과 감시자
## 목차
* 계산된 속성
* 감시자

## 계산된 속성
* 템플릿 내에서 사용하는 표현식은 편리하지만 단순한 연산에만 사용해야한다 => 유지보수의 어려움
* 복잡한 로직의 경우 반드시 계산된 속성을 사용해야 한다

### 예제
``` html
// 메시지를 거꾸로 출력하는 예시
<div id="example">
  <p>원본 메시지: "{{ message }}"</p>
  <p>뒤집히도록 계산된 메시지: "{{ reversedMessage }}"</p>
</div>
```
``` javascript
var vm = new Vue({
  el: '#example',
  data: {
    message: '안녕하세요'
  },
  computed: {
    // 계산된 getter
    reversedMessage: function () {
      // `this` 는 vm 인스턴스를 가리킵니다.
      return this.message.split('').reverse().join('')
    }
  }
})
```
* 일반 속성처럼 템플릿의 계산된 속성에 데이터 바인딩 할 수 있다
* Vue는 vm.reversedMessage가 vm.message에 의존하는 것을 알고 있기 때문에 vm.message가 바뀔 때 vm.reversedMessage에 의존하는 바인딩을 모두 업데이트 한다

### 계산된 캐싱 vs 메소드
* 계산된 속성 대신 메소드와 같은 함수를 정의할 수 있다
* 최종 결과는 두가지 접근 방식은 서로 동일
* 하지만 계산된 속성은 종속성에 따라 캐시 된다! => 계산된 속성은 종속성 중 일부가 변경된 경우에만 다시 계산
* 즉, 위의 예제에서 message가 변경되지 않는 한, 계산된 속성인 reversecMessage에 대한 다중 접근은 함수를 다시 수행할 필요없이 이전에 계산된 결과를 즉시 반환한다
* 메소드 호출은 재 렌더링 할때마다 항상 메소드를 호출한다

> 캐싱이 왜 필요할까?  
>  시간이 많이 소요되는 A 속성을 가지고 있다고 가정하면 거대한 배열을 반복하고 많은 계산을 해야한다. 그런 다음 A 에 의존하는 다른 계산된 속성을 가질 수 있다. 캐싱하지 않으면 A의 getter를 필요한 것보다 더 많이 실행하게 된다!  


### 계산된 속성 vs 감시된 속성
* Vue는 Vue 인스턴스의 데이터 변경을 관찰하고 이에 반응하는 보다 일반적인 속성 감시 방법을 제공한다
* 다른 데이터 기반으로 변경할 필요가 있는 데이터가 있는 경우, 특히 AngularJS를 사용하던 경우 watch를 남용하는 경우가 있다. 하지만 watch 콜백보다 계산된 속성을 사용하는 것이 더 좋다

``` html
<div id="demo">{{ fullName }}</div>
```
``` javascript
// 감시된 속성 사용(watch)
var vm = new Vue({
  el: '#demo',
  data: {
    firstName: 'Foo',
    lastName: 'Bar',
    fullName: 'Foo Bar'
  },
  watch: {
    firstName: function (val) {
      this.fullName = val + ' ' + this.lastName
    },
    lastName: function (val) {
      this.fullName = this.firstName + ' ' + val
    }
  }
})

// 계산된 속성 사용 (computed)
	// 코드의 반복이 줄어든다
var vm = new Vue({
  el: '#demo',
  data: {
    firstName: 'Foo',
    lastName: 'Bar'
  },
  computed: {
    fullName: function () {
      return this.firstName + ' ' + this.lastName
    }
  }
})
```


### 계산된 setter
* 계산된 속성은 기본적으로 getter만 가지고 있지만, 필요한 경우 setter를 제공할 수 있다
``` javascript
// 계산된 속성의 setter
	// vm.fullName = 'John Doe'를 실행하면 설정자가 호출되고, vm.firstName과 vm.lastName이 업데이트(set) 된다
computed: {
  fullName: {
    // getter
    get: function () {
      return this.firstName + ' ' + this.lastName
    },
    // setter
    set: function (newValue) {
      var names = newValue.split(' ')
      this.firstName = names[0]
      this.lastName = names[names.length - 1]
    }
  }
}
```


- - - -

## 감시자
* 대부분의 경우 계산된 속성이 더 적합하지만 사용자 정의 감시자가 필요한 경우가 있다
* Vue는 watch 옵션을 통해 데이터 변경에 반응하는 보다 일반적인 방법을 제공한다
* watch는 데이터 변경에 대한 응답으로 비동기식 또는 시간이 많이 소요되는 조작을 수행하려는 경우에 가장 유용하다

``` html
<div id="watch-example">
  <p>
    yes/no 질문을 물어보세요:
    <input v-model="question">
  </p>
  <p>{{ answer }}</p>
</div>
```
``` html
<!-- 이미 Ajax 라이브러리의 풍부한 생태계와 범용 유틸리티 메소드 컬렉션이 있기 때문에, -->
<!-- Vue 코어는 다시 만들지 않아 작게 유지됩니다. -->
<!-- 이것은 이미 익숙한 것을 선택할 수 있는 자유를 줍니다. -->
<script src="https://unpkg.com/axios@0.12.0/dist/axios.min.js"></script>
<script src="https://unpkg.com/lodash@4.13.1/lodash.min.js"></script>
<script>
var watchExampleVM = new Vue({
  el: '#watch-example',
  data: {
    question: '',
    answer: '질문을 하기 전까지는 대답할 수 없습니다.'
  },
  watch: {
    // 질문이 변경될 때 마다 이 기능이 실행됩니다.
    question: function (newQuestion) {
      this.answer = '입력을 기다리는 중...'
      this.getAnswer()
    }
  },
  methods: {
    // _.debounce는 lodash가 제공하는 기능으로
    // 특히 시간이 많이 소요되는 작업을 실행할 수 있는 빈도를 제한합니다.
    // 이 경우, 우리는 yesno.wtf/api 에 액세스 하는 빈도를 제한하고,
    // 사용자가 ajax요청을 하기 전에 타이핑을 완전히 마칠 때까지 기다리길 바랍니다.
    // _.debounce 함수(또는 이와 유사한 _.throttle)에 대한
    // 자세한 내용을 보려면 https://lodash.com/docs#debounce 를 방문하세요.
    getAnswer: _.debounce(
      function () {
        if (this.question.indexOf('?') === -1) {
          this.answer = '질문에는 일반적으로 물음표가 포함 됩니다. ;-)'
          return
        }
        this.answer = '생각중...'
        var vm = this
        axios.get('https://yesno.wtf/api')
          .then(function (response) {
            vm.answer = _.capitalize(response.data.answer)
          })
          .catch(function (error) {
            vm.answer = '에러! API 요청에 오류가 있습니다. ' + error
          })
      },
      // 사용자가 입력을 기다리는 시간(밀리세컨드) 입니다.
      500
    )
  }
})
</script>
```

* 이 경우 watch 옵션을 사용하면 비동기 연산 (API 엑세스)를 수행하고, 우리가 그 연산을 얼마나 자주 수행하는지 제한하고, 최종 응답을 얻을 때까지 중간 상태를 설정할 수 있다
* 계산된 속성은 이러한 기능을 수행할 수 없다









