# CSS Display속성
## display
* 	요소를 어떻게 보여줄지를 결정

**속성**
	none
	block
	inline
	inline-block
	flex


### none
* 보이지 않음
(== visibility: hidden)
* 영역을 차지하지 않음
(!= visibility: hidden)

	---

### block
* defualt 값
* 기본적으로 width: 100%이고 블록마다 줄바꿈이 된다
* 크기를 지정할 수 있다

	---

### inline
* block과 달리 줄바꿈이 되지않고
* width, height를 지정할 수 없다

	—

### inline-block
* block과 inline의 중간형태
* 줄바꿈이 되지 않고 (inline)
* 크기를 지정할 수 있다 (block)
* vertical-align: top과 함께 써주어야 한다
(inline-block은 가로에 대한 정의만 해준다)
=> inline-block은 아래쪽이 기준!
=> 크기를 줄이면 위쪽이 줄어든다
=> vertical-align: top으로 기준을 위쪽으로 바꿔준다



### flex
* 부모가 flex이면 자식들은 부모의 width를 전체 flex중 자신의 flex값의 비율만큼 width를 갖는다
* flex-direction: row가 default값!
* 하지만 flex는 단단하지 않아서 외부의 영향에 따라 크기가 변할 수 있다
따라서 크기의 고정이 필요하면 고정된 값으로 width를 주어야 한다
* 자식들 중 width를 flex값이 아닌 고정된 px값을 갖는다면 그 자식을 제외한 나머지 자식들이 flex값의 비율로 width를 갖는다

**가운데 정렬**
`display: flex`인 부모에 
`align-items: center` (세로 가운데 정렬)
`justify-content: center` (가로 가운데 정렬)을 적용하면
자식들을 중앙 정렬할 수 있다

