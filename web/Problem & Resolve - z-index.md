# Problem & Resolve - z-index
* 부모의 z-index가 낮을경우 자식의 z-index가 아무리 커도 다른 엘리먼트(자식의 z-index보다 낮고 부모의 z-index보다 클 경우)에 가려질 수 있다

## 문제점
* fixed로 부모의 엘리먼트 내부가 아닌 외부영역에 자식 엘리먼트를 생성
* 이때 자식 엘리먼트의 z-index를 높여줘도 다른 부모의 엘리먼트에 가려지는 현상이 발생
``` pug
.parent
	.child
.other-parent
```
``` sass
.parent
	z-index: 10
	width: 100vw
	height: 100px
	.child
		position: fixed
		top: 100px
		left: 100px
		z-index: 999
.other-parent
	z-index: 100
```

## 해결
* 위에 보이고 싶은 자식 엘리먼트의 부모의 z-index를 다른 부모의 z-index보다 높게 설정해 준다
``` pug
.parent
	.child
.other-parent
```
``` sass
.parent
	// 부모의 z-index를 높여준다
	z-index: 200
	width: 100vw
	height: 100px
	.child
		position: fixed
		top: 100px
		left: 100px
.other-parent
	z-index: 100
```