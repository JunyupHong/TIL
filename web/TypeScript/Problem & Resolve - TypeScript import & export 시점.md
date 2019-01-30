# Problem & Resolve - TypeScript import & export 시점

### index.ts
``` typescript
// import class
import Board from './Board';
import Comment from './Comment';
import Recommend from './Recommend';

console.log('index call');

// export class
export { Board, Comment, Recommend };
```


### test.ts
``` typescript
import { Board, Comment, Recommend } from '../api/index'
console.log('test call');

const board = new Board();	// error!!!
console.log(Board);	// undefined
console.log(Comment);	// undefined
console.log(Recommend);	// undefined

const obj = {
	board: {
		load() {
			return new Board(); // not error!
		},
		publish() {...},
	}
}
```

## 문제점
* 위와같이 index.ts와 test.ts 파일 두개가 있을때 실행하면 test파일의 'test call'이 먼저 불리고 이후에 index파일의 'index call'이 불린다
* 따라서 전역에서 import 한 클래스의 인스턴스 생성(new)을 하면 에러가 난다 (함수 내부에서 생성하는 것은 가능)
	* => import 하기 전에 Board의 인스턴스를 생성하므로 Board는 undefined이고, 따라서 constructor가 없다는 에러가 난다
	* => 파일이 불리는 시간/시점의 문제


## 해결
* index.ts에서 import하는 것이 아닌 클래스를 export하는 파일에서 바로 import해서 쓴다
### test.ts
``` typescript
import { Comment, Recommend } from '../api/index'
import Board from './Board';


const board = new Board();		// Board에서 직접 받았으므로 가능
const Comment = new Comment();	// error

// 하지만 이 시점에는 여전히 undefined지만 직접 import한것은 생성가능
console.log(Board);	// undefined
console.log(Comment);	// undefined
console.log(Recommend);	// undefined
```

* `import * as name`을 사용
### test.ts
``` typescript
import * as api from '../api/index'

const board = new api.Board(); // not error!
const Comment = new api.Comment(); // not error!
```










