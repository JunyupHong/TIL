# Vue Scroll
## v-on:scroll
* v-on 디렉티브를 이용해 scroll에 이벤트 리스너를 설정할 수 있다
``` pug
// window엘리먼트에서 scroll이 될때마다 onScroll함수가 불린다
.window(v-on:scroll="onScroll")
```
``` javascript
methods: {
	onScroll(e) {
		console.log(e.target.scrollTop);
	}
}
```


## Example
### 스크롤바가 맨 아래 닿으면 item 추가하기
* `e.target.scrollTop` : scroll이 일어난 target의 현재 top 위치
* `e.target.scrollHeight` : scroll이 일어난 엘리먼트의 height
* `window.innerHeight` : 브라우저 내부(컨텐츠가 보여지는 화면)의 height
* `window.outerHeight` : 브라우저 외부(전체화면)의 height
``` pug
.item-zone(@scroll="onScroll")
	.item(v-for="(i, index) in items") Item {{index}}
```
``` javascript
data() {
	return {
		items = []
	};
},
methods: {
	pushItem() {
		this.item.push({item: 'this is item'});
	},
	onScroll(e) {
		if(e.target.scrollTop === e.target.scrollHeight - window.innerHeight) {
			this.pushItem();
		}
	}
}
```