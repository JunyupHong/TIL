# Vue import & index.js
* import 할 때 경로를 폴더로 지정해주면 그 폴더 내부에 있는 index.js 파일을 import 한다!
* index.js 에서 같은 폴더에 있는 파일들을 import, export 하면 하나의 경로로 폴더의 스크립트를 선택해서 import 할 수 있다

## 예시
### 전체 파일 구성
``` 
Util
	|- common
		|- index.js
		|- file.js
		|- time.js
```

### index.js 파일
``` javascript
import File from './file';
import Time from './time';

export { File, Time };
```


### common 안의 파일들을 사용할 파일
``` javascript
import * as Util from '../util/common';
	// common 폴더를 경로로 지정하면 index.js 파일을 자동으로 본다
import { File } from '../util/common';
	// common 폴더의 index.js의 File만을 가져온다
```