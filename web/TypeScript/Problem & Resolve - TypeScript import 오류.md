# Problem & Resolve - TypeScript import 오류
* typescript에서 모듈 import가 안되는 경우

### tsconfig.json의 compilerOptions의 types에 모듈 이름을 넣어주면 된다
* ex) loads, vuetify 등등
``` json
{
	"compilerOptions": {
		"types": ["webpack-env", "vuetify"],
		// ...
	},
	// ...
}
```