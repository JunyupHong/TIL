# Node.js 입출력
1. process.std
2. readline module

## process.std
* 시스템의 stdin을 가져와서 사용
	* process.stdin
	* process.stdout
``` javascript
// 입력
process.stdin.read();

// 출력
process.stdout.write(‘output’);
```


## readline module
* readline 모듈을 사용
``` javascript
const readline = require(‘readline’);
const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});

// 출력
rl.write(‘output’);

// 입력
// 1. question 사용
	// 입력을 한 번 받음
	// 인자로 텍스트를 출력할 수 있음
rl.question(‘입력하세요: ‘, (inputValue) => {
	console.log(‘입력값: ’ + inputValue);
	rl.close(); // 반드시 close를 해줘야함
});

// 2. listener를 등록해서 사용
	// rl.close(); 를 실행시키지 않으면 무한 반복
rl.on(‘line’, (inputValue) => {
	console.log(‘입력값: ’ + inputValue);
	rl.close(); // 반드시 close를 해줘야함
}).on(‘close’, () => {
	// TODO
});
```
