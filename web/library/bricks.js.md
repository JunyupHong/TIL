# bricks.js
* masonry와 유사
* masonry는 가로 사이즈가 정해져 있지 않다 그러나 bricks는 가로 사이즈가 정해져있고 그 사이즈에 맞게 세로 사이즈가 정해진다!
* masonry는 느리다 (가로 사이즈가 달라 컴포넌트를 배치하는 알고리즘이 오래걸린다)
* 따라서 bricks가 Vue에서 masonry보다 쓰기 좋다

- - - -

## 설치
* bricks 라는 npm 라이브러리도 있는데 이것이 아니라 bricks.js!!
``` shell
$ npm install bricks.js
```

- - - -


## 사용
1. import brick.js
``` javascript
import Bricks from 'brick.js';
```

2. bricksInstance 생성
``` javascript
const bricksInstance = Bricks({
	container: '.bricks-vue',	// bricksInstance가 생성될 template
	packed: 'data-packed',
	sizes: [
		// mq - the minimum viewport width (String CSS unit: em, px, rem)
		// columns - 열의 갯수
		// gutter  - 열 사이의 공간(px)
		{ columns: 2, gutter: 10 }
		// { mq: '768px', columns: 3, gutter: 25 }
	]
});
```

3. bricksInstance.pack()
``` javascript
// 처음 초기화는 pack, 나중에는 update 를 해준다
// 처음 bricksInstance가 생성된 후에 pack을 해준다
bricksInstance.pack();
	// 또는
bricksInstance.on('pack', () => {
  // ...
});
```

4. bricksInstance.update()
``` javascript
// pack을 하고 난 뒤 bricksInstance가 update되고 난 뒤에 실행해준다
bricksInstance.update();

bricksInstance.on('update', () => {
  // ...
})
```