# TypeScript module
* TypeScript는 한 파일에서 여러번 export를 할 수 있다
* 모듈 당 export default {} 가 하나만 있어야 한다

## named export & import
``` typescript
// module.ts 파일
const num: number = 3;
function func(): void {
	console.log("this is function")
}
class Dog {}

export { num, func, Dog }


// module을 쓸 파일
import { num as anotherNameOfNumber, func, Dog } from "./module.ts"

const n: number = anotherNameOfNumber
const f = func
const dog = new Dog()
```


## default export & import
* `export default{}` 를 하면 `import * as Name from "./path"` 으로 사용할때와 `import Name from "./path"` 으로 사용할때 객체가 다르게 넘어온다
	* `import * as Name from "./path"` 는 default 객체가 Name안에 존재 => Name.default 로 사용
	* `import Name from "./path"` 는 Name이 default 객체가 된다 => Name 으로 사용

``` typescript
// formatter.ts 파일
export default {
	number: {
		getLength(num: number): number {
			return (num + "").length
		}
	}
}
const stringVal: string = "this is string";
export { stringVal }



// formatter를 쓸 파일

	// 파일 전체를 import해서 as A 로 사용하면 default 객체와 다른 named export 된 객체를 가져온다
	// => default 객체를 쓰려면 A.default로 사용해야한다
	// => named export 된 객체를 사용하려면 A로 사용한다
	// JavaScript에서는 바로 A.number로 사용할 수 있었다
import * as A from "./formatter"
A.default.number.getLength(100)
A.stringVal


	// as 없이 바로 export한 객체를 가져오면 default 객체만 가져온다
	// => 설정한 이름이 바로 default 객체가 된다
	// => 따라서 B로 바로 사용할 수 있다	
import B from "./formatter"
B.number.getLength(1000)
```

