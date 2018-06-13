# CSS Box-Model

## box-model
* CSS에서 레이아웃을 구성하는 특징     

* 박스 모델은 margin, border, padding, content로 구성되어 있다
 ![box model](data:image/png;base64,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)


## width & height 
* 기본적으로 width와 height는
	content 영역을 나타나는 값이다
### 단위
* px ( pixel )
* % : 부모의 크기에 대해 퍼센트로 설정
* vw ( view width ) : 현재 스크린 width의 퍼센트 
* vh (view height ) : 현재 스크린 height의 퍼센트

### 서로 다른 단위의 연산
calc()를 이용!
`calc(100% - 50px)`
`calc(50vh + 10% - 5px)`

## max & min - width & height
* max-width & max-height: 화면이 커져도 max값 보다 커지지 않는다
* min-width & min-height: 화면이 작아져도 min값 보다 작아지지 않는다


## box-sizing
속성
* content-box (default): content영역만 width, height에 해당
* border-box: content영역과 border영역까지 width, height에 포함
```
	box-sizing: content-box;
	box-sizing: border-box;
```
	

## margin & padding
* margin: 엘리먼트 바깥쪽을 둘러싼 공간
* padding: 컨텐츠 주위의 공간
```
	margin: 10px;				// 상하좌우 margin을 10px준다
	margin: 5px 10px;			// 상하 5px, 좌우 10px의 margin을 준다
	margin: 1px 2px 3px 4px;	// 순서대로 상 우 하 좌 margin을 준다
	
	padding: 10px;				// 상하좌우 padding을 10px준다
	padding: 5px 10px;			// 상하 5px, 좌우 10px의 padding을 준다
	padding: 1px 2px 3px 4px;	// 순서대로 상 우 하 좌 padding을 준다
```
	
### margin: auto
context의 크기를 제외한 부분을 균일한 margin을 주어 context를 가운데 정렬할 수 있게 해준다


## border
* padding 바깥쪽의 테두리

속성
	border-top, right, bottom, left : 테두리의 위치
	border-width : 테두리 두께
	border-style : 테두리 스타일
		( solid, dotted, dashed, double ... )
	border-color : 테두리 색
	border-radius : 테두리의 모서리
```
	border: 1px solid white;
	border-left: 5px double red;
```