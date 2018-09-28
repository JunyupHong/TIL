# Vue 9. 폼 입력 바인딩
## 목차
* 기본 사용법
* 값 바인딩하기
* 수식어
* v-model 과 컴포넌트

- - - -


## 기본 사용법
* v-model 디렉티브를 사용하여 input과 textarea 엘리먼트에 양방향 데이터 바인딩을 생성할 수 있다
* v-model 은 기본적으로 사용자 입력 이벤트에 대한 데이터를 업데이트하는 “syntax sugar”이며 일부 경우 주의해야함
> **< syntax sugar >**  
> 사람이 이해 하고 표현하기 쉽게 디자인된 프로그래밍 언어 문법  
> 사람이 프로그래밍 언어를 sweeter하게 사용 할 수 있도록 도와주는 문법  
> 더욱 더 간결하고 명확하게 표현이 가능한 문법  

> **< v-model을 사용할 경우 주의할 사항 >**  
> > v-model은 모든 form 엘리먼트의 초기 value와 checked, selected 속성을 무시한다 (항상 Vue인스턴스 데이터를 원본 소스로 취급) -> 컴포넌트의 data 옵션 안에 있는 JavaScript에서 초기값을 선언해야한다  
> > IME(중국어, 일본어, 한국어 등) 가 필요한 언어의 경우 IME 중 v-model이 업데이트 되지 않는다 -> 업데이트 처리하려면 input이벤트를 대신 사용 해야함  


### 문자열
``` html
<input v-model="message" placeholder="input message">
<p>메시지: {{ message }}</p>
```

### 여러 줄을 가진 문장
``` html
<span>여러 줄을 가지는 메시지:</span>
// white-space: pre-line 속성을 이용해서 스페이스, 탭, 줄바꿈을 처리해준다
<p style="white-space: pre-line">{{ message }}</p>
<br>
<textarea v-model="message" placeholder="여러줄을 입력해보세요"></textarea>
```
> 텍스트 영역의 보간 (<textarea>{{ text }}</textarea>)은 작동하지 않는다 -> 대신 v-model를 사용  

### 체크박스
* 하나의 체크박스는 단일 boolean 값을 가진다
``` html
<input type="checkbox" id="checkbox" v-model="checked">
<label for="checkbox">{{ checked }}</label>
```

* 여러개의 체크박스는 같은 배열을 바인딩 할 수 있다
``` html
<div id='example-3'>
  <input type="checkbox" id="jack" value="Jack" v-model="checkedNames">
  <label for="jack">Jack</label>
  <input type="checkbox" id="john" value="John" v-model="checkedNames">
  <label for="john">John</label>
  <input type="checkbox" id="mike" value="Mike" v-model="checkedNames">
  <label for="mike">Mike</label>
  <br>
  <span>체크한 이름: {{ checkedNames }}</span>
</div>
```
``` javascript
new Vue({
  el: '#example-3',
  data: {
    checkedNames: []
  }
});
``` 

### 라디오
``` html
<input type="radio" id="one" value="One" v-model="picked">
<label for="one">One</label>
<br>
<input type="radio" id="two" value="Two" v-model="picked">
<label for="two">Two</label>
<br>
<span>선택: {{ picked }}</span>
```

### 셀렉트
* 하나의 셀렉트
``` html
<select v-model="selected">
  <option disabled value="">Please select one</option>
  <option>A</option>
  <option>B</option>
  <option>C</option>
</select>
<span>선택함: {{ selected }}</span>
```
``` javascript
new Vue({
  el: '...',
  data: {
    selected: ''
  }
});
```
> v-model 표현식의 초기 값이 어떤 옵션에도 없으면 <selected>엘리먼트는 선택없음 상태로 렌더링된다  
> iOS에서는 이 경우 변경 이벤트가 발생하지 않아 사용자가 첫 번째 항목을 선택할 수 없게된다  
> 따라서 사용하지 않는 옵션에 빈 값을 넣는 것이 좋다  

* 다중 셀렉트 (배열을 바인딩)
``` html
<select v-model="selected" multiple>
  <option>A</option>
  <option>B</option>
  <option>C</option>
</select>
<br>
<span>Selected: {{ selected }}</span>
```

* v-for를 이용한 동적 옵션 렌더링
``` html
<select v-model="selected">
    <option v-for="option in options" v-bind:value="option.value">
    {{ option.text }}
  </option>
</select>
<span>Selected: {{ selected }}</span>
```
``` javascript
new Vue({
  el: '...',
  data: {
    selected: 'A',
    options: [
      { text: 'One', value: 'A' },
      { text: 'Two', value: 'B' },
      { text: 'Three', value: 'C' }
    ]
  }
});
```


- - - -

## 값 바인딩하기
* 라디오, 체크박스, 셀렉트 옵션의 경우 v-model 바인딩 값은 보통 정적인 문자열(또는 체크 박스의 boolean)이다
``` html
<!-- `picked` 는 선택시 문자열 "a" -->
<input type="radio" v-model="picked" value="a">

<!-- `toggle` 는 true 또는 false -->
<input type="checkbox" v-model="toggle">

<!-- `selected`는 "ABC" 선택시 "abc" -->
<select v-model="selected">
  <option value="abc">ABC</option>
</select>
```

* 그러나 값을 Vue 인스턴스에 동적 속성에 바인딩 해야할 수도 있다
* 이때 v-bind를 사용
* v-bind를 사용하면 입력 값을 문자열이 아닌 값에 바인딩 할 수 있다


### 체크박스
``` html
<input
  type="checkbox"
  v-model="toggle"
  true-value="yes"
  false-value="no"
>
```
``` javascript
// 체크된 경우
vm.toggle === 'yes'

// 체크 되지 않은 경우
vm.toggle === 'no'
```
> true-value 와 false-value 속성은 폼 전송시 체크되지 않은 박스를 포함하지 않기 때문에 입력의 value 속성에 영향을 미치지 않는다  
> 두 값 중 하나가 폼을 통해 전송 되려면 (예 : ‘예’ 또는 ‘아니요’) 라디오를 대신 사용해야한다  


### 라디오
``` html
<input type="radio" v-model="pick" v-bind:value="a">
```
``` javascript
// 체크 하면:
vm.pick === vm.a
```


### 셀렉트 옵션
``` html
<select v-model="selected">
  <!-- inline object literal -->
  <option v-bind:value="{ number: 123 }">123</option>
</select>
```
``` javascript
// 선택 하면:
typeof vm.selected // -> 'object'
vm.selected.number // -> 123
```


- - - -

## 수식어

### .lazy
* 기본적으로, v-model은 각 입력 이벤트 후 입력과 데이터를 동기화 한다 (단 앞에서 설명한 IME 구성은 제외)
* .lazy 수식어를 추가하여 change 이벤트 이후에 동기화 할 수 있다
``` html
<!-- "input" 대신 "change" 이후에 동기화 됩니다. -->
<input v-model.lazy="msg" >
```

### .number
* 사용자 입력이 자동으로 숫자로 형변환 되기를 원하면, v-model 이 관리하는 input에 number 수식어를 추가
``` html
<input v-model.number="age" type="number">
```
* type="number"를 사용하는 경우에도 HTML 입력 엘리먼트의 값은 항상 문자열을 반환하기 때문에 유용하게 사용할 수 있다


### .trim
* v-model이 관리하는 input을 자동으로 trim 하기 원하면, trim 수정자를 추가하면 된다
``` html
<input v-model.trim="msg">
```


- - - -

## v-model 과 컴포넌트
* HTML의 기본 제공 input 유형이 항상 사용자의 요구를 만족시킬 수는 없다
* 다행히 Vue 컴포넌트를 사용하면 완전히 사용자 정의 된 동작으로 재사용 가능한 input을 만들 수 있다
* 이 input은 v-model에도 작동!





