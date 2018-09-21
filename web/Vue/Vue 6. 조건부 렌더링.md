# Vue 6. 조건부 렌더링
## 목차
* v-if
* v-show
* v-if vs v-show
* v-if와 v-for

- - - -

## v-if
* handlebars와 같은 문자열 템플릿에서는 다음과 같은 조건부 작성할 수 있다
``` html
// Handlebars 템플릿
{{#if ok}}
	<h1> Yes </h1>
{{/if}}
```

* Vue에서는 v-if디렉티브를 사용할 수 있다
``` html
<h1 v-if="ok"> Yes </h1>
```

* v-else와 함께 else블록을 추가할 수 있다
``` html
<h1 v-if="ok"> Yes </h1>
<h1 v-else> No </h1>
```

### template에 v-if를 갖는 조건부 그룹 만들기
* v-if는 디렉티브이기 때문에 하나의 엘리먼트에 추가해야한다
* 하나 이상의 엘리먼트를 트랜지션하려면 wrapper 역할을 하는 <template> 엘리먼트에 v-if를 사용한다(최종 결과에는 <template> 엘리먼트가 포함되지 않는다)
``` html
<template v-if="ok">
	<h1>Title</h1>
	<p>Paragraph1</p>
	<p>Paragraph2</p>
</template>
```

### v-else
* v-else 디렉티브를 사용하여 v-if에 대한 else블록을 나타낼 수 있다
* v-else 엘리먼트는 v-if 엘리먼트 또는 v-else-if 엘리먼트 바로 뒤에 있어야한다(그렇지 않은경우 인식하지 못함)
``` html
// Math.random()이 0.5보다 크면 v-if가 렌더링 되고,
// 작으면 v-else가 렌더링 된다
<div v-if="Math.random() > 0.5">
  이제 나를 볼 수 있어요
</div>
<div v-else>
  이제는 안보입니다
</div>
```

### v-else-if
* 2.1.0버전에 새로 추가된 디렉티브
* v-if 에 대한 else if 블록 역할을 한다
* 여러 개를 사용할 수 있다
* v-else와 마찬가지로 v-else-if 엘리먼트는 v-if 또는 v-else-if 엘리먼트 바로 뒤에 와야 한다
``` html
<div v-if="type === 'A'"> A </div>
<div v-else-if="type === 'B'"> B </div>
<div v-else-if="type === 'C'"> C </div>
<div v-else> Not A/B/C </div>
```

### key를 이용한 재사용 가능한 엘리먼트 제어
* Vue는 가능한 한 효율적으로 엘리먼트를 렌더링하려고 시도하면 종종 처음부터 렌더링하지 않고 재사용을 한다
* Vue를 매우 빠르게 만드는데 도움이 되는것 이외에 몇가지 유용한 이점
	* 사용자가 여러 로그인 유형을 트랜지션 할 수 있도록 허용하는 경우
``` html
// 여러 로그인 유형을 트랜지션 할 수 있게 한다
// loginType을 바꾸어도 사용자가 이미 입력한 내용은 지워지지 않는다
// 두 템플릿 모두 같은 엘리먼트(input)를 사용하므로 input은 대체되지 않고 placeholder만 변경된다
// 결과적으로 처음 입력한 이름이 이메일의 input에 남아있다

<template v-if="loginType === 'username'">
  <label>사용자 이름</label>
  <input placeholder="사용자 이름을 입력하세요">
</template>
<template v-else>
  <label>이메일</label>
  <input placeholder="이메일 주소를 입력하세요">
</template>
```
* 이 경우 두 엘리먼트는 다르지만 placeholder만 바꿔서 사용된다 이 문제를 해결하려면 key 속성을 추가해준다
* key 속성을 추가하면 트랜지션 할 때마다 input이 처음부터 렌더링 된다
``` html
// key 속성을 추가했으므로 같은 엘리먼트를 사용할지라도 트랜지션될때마다 새로 렌더링된다
// 따라서 앞서 입력한 사용자 이름은 남아있지 않는다
<template v-if="loginType === 'username'">
  <label>사용자 이름</label>
  <input placeholder="사용자 이름을 입력하세요" key="username-input">
</template>
<template v-else>
  <label>이메일</label>
  <input placeholder="이메일 주소를 입력하세요" key="email-input">
</template>
```

* 이때 label 엘리먼트는 key속성이 없기때문에 효율적으로 재사용된다

- - - -

## v-show
* 엘리먼트를 조건부로 표시하기 위한 또 다른 옵션
* 사용법은 v-if와 거의 동일
``` html
<h1 v-show="ok">안녕하세요!</h1>
```
* v-if와의 차이점은 v-show가 있는 엘리먼트는 항상 렌더링 되고 DOM에 남아있다(v-show는 단순히 엘리먼트에 CSS의 display속성을 토글 한다)
* v-show는 <template> 구문을 지원하지 않으며 v-else와도 작동하지 않는다


- - - -

## v-if vs v-show
* v-if는 조건부 블럭 안의 이벤트 리스터와 자식 컴포넌트가 토글하는 동안 적절하게 제거되고 다시 만들어지는 진짜 조건부 렌더링!
* v-if는 게을러서 초기 렌더링 조건이 false인 경우 아무것도 렌더링하지 않는다(조건 블록이 처음으로 true가 될때까지 렌더링 되지 않는다)
* v-show는 단순, CSS기반 토글만으로 초기 조건에 관계없이 엘리먼트가 항상 렌더링 된다
* 일반적으로 v-if는 토글비용이 높고, v-show는 초기 렌더링 비용이 높다
* 따라서 자주 바뀌는 엘리먼트는 v-show를, 런타임 시 조건이 바뀌지 않으면 v-if를 권장

- - - -

## v-if와 v-for
* v-if와 함께 v-for를 사용하는 경우, v-for는 v-if보다 높은 우선순위를 갖는다









