# CSS flex-box

## flexible box model
* 장점
복잡한 계산 없이 박스의 크기와 순서를 유연하게 배치할 수 있다.
* flex container(부모)와 flex item(자식)으로 이루어져 있다
부모에 display속성을 flex 또는 inline-flex로 선언하면 자식은 자동으로 flex item이 된다


## flex box의 축
### 주축(진행축)
* 주축은 flex-direction 속성에 의해 정의된다
* 속성
	* row                            (default, 오른쪽에서 왼쪽 방향)
	* row-reverse             (왼쪽에서 오른쪽 방향)
	* column                     (위쪽에서 아래쪽 방향)
	* column-reverse      (아래쪽에서 위쪽 방향)

### 교차축
* 교차축은 주축(진행축)에 수직이다
* 추축이 row 또는 row-reverse이면 열 방향
* 주축이 column 또는 column-reverse이면 행 방향


## flex container
* flexbox가 놓여있는 문서의 영역
* flex item의 면적, 방향, 정렬을 결정하는 컨테이너
* `display: inline-flex`나 `display: flex`로 선언한다
(이때 자식은 flex item이 된다)

* 속성
	* flex-direction (주축을 설정한다, default: row)
	`flex-direction: row | row-reverse | column | column-reverse;`
	* flex-wrap (줄 바꿈을 제어, default: nowrap)
	`flex-wrap: nowrap | wrap | wap-reverse;`
	* flex-flow (flex-direction과 flex-wrap을 한번에 제어)
	`flex-flow: <flex-direction> || <flex-wrap>`
	`flex-flow: row wrap;	// default`
	* **order** (배치 순서를 제어)
	flex-direction을 기준으로 낮은 숫자를 먼저 배치하고 높은숫자를 나중에 배치한다!
	`order: 0;	// default값 (양의 정수, 음의 정수 모두 가능)`


## flex item
* 컨테이너 내부에 형성된 free space (남거나 모자라는 공간)을 지수(팽창지수, 수축지수) 값에 따라 형제들이 서로 나누어 갖는다
* flex item의 기본적인 스타일 
```
flex-grow: 0;			// free space가 있어도 폭이 늘어나지 않음
flex-shrink: 1;		// 부모 박스 크기 초과시(free space가 음수일 때) 
						// 자동으로 균등하게 수축
flex-basis: auto;		// 콘텐츠 너비만큼 수축
flex-direction: row;	// 행으로 배치
flex-wrap: nowrap;	// 한줄로만 배치
```
* float 속성은 무시한다
* `position: absolute | fixed`이면 flex item에서 제외
* flex item은 부모나 형제와 margin을 중첩하지 않음
* margin/padding을 상대단위(%)로 사용하지 않는게 좋다
(브라우저마다 구현이 다를 수 있으므로)



## flex item의 팽창&수축
### flex-grow
* 팽창지수 (flex item의 팽창을 제어)
* default 값: 0
* 하지만 단축속성 flex 사용시 생략하면 초기값은 1로 다시 설정 됨
* 0이면 부모의 크기를 자식들이 채우지 못해도 팽창하지 않고
* 0이 아니면 flex-grow 값에 따라 균등하게 팽창한다

	—

### flex-shrink
* 수축지수 (flex item의 수축을 제어)
* default 값: 1
* 0이면 수축하지 않고 부모의 크기를 넘어간다
* 0이 아니면 부모의 너비를 넘어가는 경우 flex-shrink 값에 따라 균등하게 수축 한다

	—

### flex-basis
* flex item의 기준 사이즈를 제어
* default 값: auto
* flex-grow, flex-shrink 에 의해서 팽창/수축하기 전에 기준 크기를 명시
* flex item에 width, height를 선언하는 것과 같다
* 하지만 flex-basis값은 width를 덮어쓰기 때문에 코드를 간결하게 작성하려면 flex-basis값을 선언하는 것을 권장

	—

### flex item의 단축속성 ‘flex’
* flex는 위의 3가지 속성을 하나의 속성값으로 작성할 수 있다.
* flex item에 아무것도 선언을 하지않으면
```
flex: 0 1 auto;		// 아래의 코드와 같다
//	 flex-grow: 0;
//	 flex-shrink: 1;
//	 flex-basis: auto; 
```
의 값을 갖는다
* flex: none
```
flex: none;
// flex: 0 0 auto; 와 같다
```
따라서 flex item에 아무것도 선언하지 않은것과 flex: none은 다르다

* flex: auto;
```
flex: 1 1 auto;
// flex-grow는 default값이 0이지만
// flex속성 사용 시 flex-grow를 생략하면 1로 바뀐다
```


## flex-box의 정렬과 간격
* justify-content: 주축(진행축) 정렬
* align-items, align-self, align-content: 교차축 정렬
* 주축과 교차축은 row일 수도 있고 column일 수도 있어서 행, 열로 표현하지 않고 한줄, 여러줄로 표현한다
(여러줄은 flex-basis, flex-wrap: wrap에 의해서 free space가 음수인 경우 발생할 수 있다)

	—

### justify-content
* 주축(진행축) 정렬, flex-item 사이의 간격을 제어
* 속성
	* flex-start                   (주축의 시작점에 맞춰 정렬)
	* flex-end                    (주축의 끝점에 맞춰 정렬)
	* center                       (주축의 가운데에 맞춰 정렬)
	* space-between       (flex-item의 사이에만 균등하게 간격을 준다)
		(flex-item과 container의 사이 공간은 간격이 없음)
	* space-around          (flex-item의 사이와 flex-item과 container사이에 균등하게 간격을 준다)

	—

### align-items
* 교차축 정렬
* 속성
	* stretch           (default값, 교차축으로 크기를 늘려서 정렬)
	* flex-start         (교차축의 시작점에 맞춰 정렬)
	* flex-end         (교차축의 끝점에 맞춰 정렬)
	* center            (교차축의 가운데에 맞춰 정렬)
	* base-line       (글꼴 사이즈가 다를경우 아래쪽을 기준으로 정렬됨)

	—

### align-self
* 독립적인 교차축 정렬(flex-item 에서 자신만 직접 제어)
* align-items보다 우선순위가 높다
* 속성
	* auto                (default값, align-items 속성을 따름)
	* stretch
	* flex-start
	* flex-end
	* center
	* baseline

	—

### align-content
* 여러 줄의 교차축 정렬과 줄 사이 간격 제어
* `flex-wrap: wrap`인 경우만 적용
* align-items 보다 우선순위가 높다
* 속성
	* stretch           (길이를 늘리지않고 줄 사이의 간격을 균등하게 준다)
	* flex-start
	* flex-end
	* center
	* space-between
	* space-around







