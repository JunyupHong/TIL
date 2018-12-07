# TypeScript Test
## module test 하기
* `$ npm run test`로 실행 ( package.json에 설정되어있음 )
``` typescript
import * as Test from "../src/ts-first-lib"

describe("ts-first-lib test", () => {
	it("test 이름을 적는곳: 개가 생성이 되었는가?", () => {
		expect(new Test.Dog()).toBeInstanceOf(Util.formatter.Dog)
	})

	it("개소리 검증", () => {
		const instance = new Test.Dog()
		expect(instance.sound()).toEqual("멍멍")
	})

	it("숫자 길이 검증", () => {
		expect(Tset.formatter.number.getLength(100)).toEqual(3)
	})
})
```
	* `describe ( "테스트 title", 콜백 함수)`
	* `it ( "테스트 이름", 테스트 함수/콜백함수 )`
	* `expect ( ( 테스트 할 요소 ) . 어떤 테스트를 할 것인가 ( 조건 ) )`