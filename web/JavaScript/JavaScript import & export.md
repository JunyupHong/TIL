# JavaScript import & export
## import
### import문은 외부 모듈이나 다른 스크립트 등으로부터 export 된 기능을 가져오는데 사용

* 모듈 전체를 가져올 때. export 한 모든 것들을 현재 범위(스크립트 파일 하나로 구분되는 모듈 범위) 내에 User 로 바인딩된다
``` javascript
import * as User from './form-user';
```

* 모듈에서 하나의 멤버만 가져올 때. 현재 범위 내에 User 에 바인딩된다
``` javascript
import { User } from './form-user';
```

* 모듈에서 여러 멤버들을 가져올 때. 현재 범위 내에 Util 과 Assign 이 바인딩된다
``` javascript
import { Util, Assign } from '../form/index';
```

* 편리한 별명을 지정. 현재 범위 내에 Util을 U에 바인딩한다
	* Assign은 별명을 지정하지 않았으므로 Assign에 바인딩된다
``` javascript
import { Util as U, Assign} from '../form/index';

// 모듈에서 여러 별명을 지정할 수 있다
import { Formatter as F, Time as T } from '../util/common';
```

* 어떠한 바인딩 없이 모듈 전체의 사이드 이펙트만 가져올 때
``` javascript
import "./my-module";
```


## export
### export 문은 지정된 파일(또는 모듈)에서 함수 또는 오브젝트, 원시 타입들을 export 하는데 사용
``` javascript
// index.js 파일
	// vue에서 import 할 때 경로를 폴더로 설정해주면 
	// 폴더 내부의 index.js 파일을 import한다
	// 따라서 전체 파일들을 모아서 index.js에서 export 해주면 편리!
import User from './form-user';
import Group from './form-group';
import Image from './form-image';

export { User, Group, Image};
```