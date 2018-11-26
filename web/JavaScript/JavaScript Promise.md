# JavaScript Promise
## 동기 & 비동기
* 동기: 어떤 작업이 완료되고 그 다음 작업이 실행된다.
* 비동기: 어떤 작업이 완료되지 않았음에도 다음 작업이 같이 실행된다
* JavaScript에서는 대부분의 작업들이 비동기로 이루어진다 => 동기 작업은 콜백함수로 사용
* 초기의 JavaScript의 경우 이벤트가 발생(버튼이 클릭) 했을때, 콜백함수 호출(특정 작업을 수행) 하는 정도 였기 때문에 콜백이 중첩될 일이 거의 없었다
* 하지만 프론트엔드의 규모가 커지면서 복잡도가 높아지고 많은 콜백이 중첩되는 경우가 생겼다.
* 이를 콜백중첩, 콜백지옥, 스파게티 코드 라고 부른다


## Callback
* callback 함수가 많아지면 코드가 복잡해지고 가독성이 떨어진다
``` javascript
async(1, function() {
	async(2, function() {
		async(3, function() {
			// ...
		});
	});
});
```


## Promise
* 위의 콜백 지옥(스파게티 코드) 를 해결하기 위한 패턴
* Promise를 사용하면 동기 작업이 가능하고 코드의 가독성이 좋아진다
* 예외처리에 대한 구조가 탄탄하다

### Promise 선언 & 실행
``` javascript
// Promise 선언
const _promiseFunc = function(param) {
	return new Promise(function(resolve, reject) {
		window.setTimeOut(function() {
			if(param) reslove("success");	// 성공
			else reject("fail");			// 실패
		}, 3000);
	});
};


// Promise 실행
	// success 가 출력된다
_promiseFunc(true)
	.then(function(successMsg) {
		console.log(successMsg);
	})
	.catch(function(failMsg) {
		console.log(failMsg);
	});

	// fail이 출력된다
_promiseFunc(false)
	.then(function(successMsg) {
		console.log(successMsg);
	})
	.catch(function(failMsg) {
		console.log(failMsg);
	});


// then catch 구문을 여러번 사용할 수 있다
_promiseFunc(true)
	.then(() => { console.log(1);})
	.catch(() => { console.log("1 error"); })
	.then(() => { console.log(2);})
	.catch(() => { console.log("2 error"); })
```

### Promise 상태
* Pending (대기)
	* 비동기 처리 로직이 아직 완료되지 않은 상태
	* new Promise() 메소드를 호출 하고 fullfilled 혹은  reject가 되기 전
	* 콜백함수의 인자로 resolve, reject에 접근할 수 있다

* Fulfilled (이행, 완료)
	* 비동기 처리가 완료되어 Promise가 결과 값을 반환해준 상태
	* resolve가 실행된 상태
	* then 또는 try 구문을 이용하여 처리 값을 받을 수 있다

* Rejected (실패)
	* 비동기 처리가 실패하거나 오류가 발생한 상태
	* catch 구문을 이용하여 실패한 이유(실패 처리의 결과 값)을 받을 수 있다

* Settled
	* Fulfilled이던 Rejected 이던 결론이 난 상태

### resolve, reject
* 비동기 처리 로직이 성공하면 resolve()를 실행해준다
* 비동기 처리 로직이 실패할경우 reject()를 실행해준다
* 이때 resolve, reject 둘 다 인자를 넘겨줄 수 있다


## Promise.all
* 여러 Promise가 모두 완료 되었을때 작업을 진행할 경우에 Promise.all API를 사용
* Promise 배열을 인자로 넘겨주어야 한다
* Promise로 각각을 처리하는 것 보다 속도가 매우 빠르다

``` javascript
const promise1 = new Promise(function(resolve, reject) {
	window.setTimeOut(() => {
		console.log("1");
		resolve();
	}, 3000);
});

const promise2 = new Promise(function(resolve, reject) {
	window.setTimeOut(() => {
		console.log("2");
		resolve();
	}, 3000);
});

Promise.all([promise1, promise2])
	.then(() => {
		console.log("1, 2 complete");
	})
```


## async & await
* 비동기 코드를 작성하는 새로운 방법
* 비동기 코드를 사용하려는 함수 앞에 async를 붙인다
* await 키워드는 오직 async로 정의된 함수 내부에서만 사용될 수 있다
* await 의 뒷부분의 코드는 반드시 Promise를 반환해야한다
* async 함수는 암묵적으로 Promise를 반환하고, Promise가 반환할 값을 resolve 한다

* 장점
	* 코드의 간결함/깔끔함 => callback이나 then 구문이 들어가지 안않는다
	* 에러 핸들링 => 동기와 비동기 에러 모두를 try/catch를 통해서 처리가 가능하다

``` javascript
// promiseFunc1(), promiseFunc2(), promiseFunc3() 가 순차적으로 실행된다
	// await 키워드 뒤의 함수들은 Promise를 리턴한다
async asyncFunc function() {
	const first = await promiseFunc1();
	const second = await promiseFunc2();
	const third = await promiseFunc3();
};
```


### async/await와 Promise.all()
``` javascript
asyncFunc = async function() {
	array = await Promise.all([promise1, promise2]);
};
```





