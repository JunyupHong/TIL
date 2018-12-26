# kocw 운영체제 2. Process synchronization
## Process Synchronization
* 동시에 여러개의 프로세스들이 실행할때 프로세스 사이의 동기화방법을 어떻게 실현할 것인가..

## Background
* 프로세스들은 현대 운영체제에서 concurrent하게 실행된다
	* => 동기화의 필요성
	* 과거에는 한번에 하나의 프로세스만 실행됐다
* 프로세스들은 언제든지 실행이 중단될수있고 실행을 일시적으로 중단할 수 있다
	* 스케줄링방법중- context switching: 한프로세스가 실행중에 일시적으로 컨텍스트를 저장하고 다른 프로세스가 실행하고 나서 다시 컨텍스트를 복원한다
* 여러 프로세스가 공유데이터를 동시에 접근하게 되면 데이터가 일관되지 않은상태가 될 가능성이 있다
* 데이터 일관성 유지하기위한 방법
	* 실행 순서를 유지해줄수있는 메커니즘을 사용 => 사전에 문제를 방지 => 동기화!!


## Race Condition예제
* producer
	* => 공유 데이터를 생성 (queue에 저장)
	* counter가 버퍼 사이즈를 넘어가면 더이상 데이터를 생성하지 않는다
	* 버퍼에 circular queue형태로 저장 => queue의 마지막 인덱스에 가면 다시 queue의 처음 인텍스부터 저장한다
	* 저장하고나서 counter를 늘려준다
* consumer
	* => 생성된 공유 데이터를 소비 (queue에서 추출)
	* counter가 0이면 더이상 데이터를 소비하지 않는다
	* 버퍼에서 데이터를 추출 (circluar queue)
	* 추출하고 counter를 빼준다

* => 두 프로세스는 데이터를 공유해야한다 (counter)
	* register = counter  공유된 값을 저장
	* register = register + 1  or register - 1  저장한 값을 더하거나 뺀다
	* counter = register  공유된 값을 업데이트

	* => 3가지 명령이 필요! => 이때 context switching에 의해서 문제가 발생할 수 있다 (업데이트 되기전에 counter 값을 사용)

* 명령어의 실행순서에 의해서 결과값이 달라지는 상황 => Race Condition


## 동기화의 문제 해결방법
* 하드웨어의 지원을 받아서 해결하는 방법
* 소프트웨어적으로 해결하는 방법


## critical section (임계영역)
* 동기화의 문제가 생길 수 있는 영역을 critical section으로 지정 => 지정한 영역을 원자적으로 실행
* 둘 이상의 프로세스가 동시에 접근해서는 안되는 공유 자원(자료 구조 또는 장치)을 접근하는 코드의 일부 => atomic 하게 실행되야하는 코드의 일부

* 임계 구역은 지정된 시간이 지난 후 종료된다
* => 어떤 프로세스가 임계 구역에 들어가고자 한다면 지정된 시간만큼 대기해야 한다

* 공유자원의 배타적인 사용을 보장받기 위해서 임계 구역에 들어가거나 나올때는 세마포어 같은 동기화 매커니즘이 사용된다

### critical section Problem
* n개의 프로세스가 있다
* 이 프로세스들은 각각 critical section이라는 코드의 부분(영역)을 갖는다
	* 이때 프로세스는 공유 자원(파일, 변수, 테이블 등등)에 접근한다
	* 한 프로세스가 critical section에 있을 경우 다른 프로세스는 critical section에 있으면 안된다
* critical section Problem => 동기화 문제 해결법
* 프로세스가 critical section에 들어갈때, 나갈때 알려준다

### synchronization hardware
* 동기화 문제를 하드웨어의 지원을 받아서 해결하는 방법
* 많은 시스템들이 critical section 을 구현하기 위한 hardware support를 하고 있다
* 과거 Uniprocessor(단일 프로세스 시스템)일때
	* interrupt를 불가능하게 한다
	* => 여러 작업을 조금씩 실행할 수 없게 switching이 안되게 한다 (동기화가 보장된다)
* 현재는 멀티 프로세서를 사용한다
	* 특정 프로세스에서 interrupt를 불가능하게 하더라도 다른 프로세스에서 접근이 가능하다 (동기화 문제 발생) 
	* automic hardware instruction (하드웨어 명령어) 을 제공
	* => 실행이 중단되지 않게 반드시 보장해준다
	* => test & set 명령어
	* => swap 명령어


### solution to critical section problem using lock
* critical section에 진입하면 다른 프로세스가 접근하지 못하도록 lock한다
``` c
do {
	acquire lock
		// critical section
	relesase lock
} while (true)
```

### test & set 명령어
``` c
boolean test_and_set (boolean *target)
{
	boolean rv = *target;
	*target = true;
	return rv;
}
```

* entry section에서의 사용
``` c
do {
	while (test_and_set(&lock)) /*do nothing*/;
		// lock이 true면 lock에 false를 저장하고(접근 가능)
		// true가 리턴되어 while문에서 무한반복한다
		// lock이 false면 lock에 true를 저장하고(접근 금지)
		// false가 리턴되어 임계영역으로 넘어간다

	// critical section

		// 임계영역에서 빠져나올때 relase lock해줌
		// 다른 프로세스가 접근 가능하게 됨
	lock = false; 
	// remainder section
} while(true);
```

### 순서
* 프로세스는 자신의 임계 구역에 진입하려면 진입허가를 요청해야 한다. 이런 요청을 구현하는 코드 부분을 입장 구역(entry section)이라고 한다
* 입장 구역에서 기다리다가 진입 허가가 나면 임계 구역에 들어간다
* 임계 구역 이후에는 임계 구역을 빠져나왔음을 알리는 코드 부분인 퇴장 구역(exit section)이 있다
* 그밖의 나머지 코드 부분들을 총칭하여 나머지 구역(remainder section)이라 한다
``` c
do {
	wait(mutex);   //입장 구역
	// 임계 구역
	signal(mutex); //퇴장 구역
	// 나머지 구역
}
```


## semaphore
* synchronization tool
* 각각의 atomic operation에 의해 접근 되어진다
* wait() : 프로세스가 일을 시작하기전에 상태를 확인하고 누가 일하고있으면 기다리고 아니면 통과
* signal() : 프로세스가 할 일을 다 하고 끝났다고 신호를 보냄
``` c
wait(S) {
	while (S <= 0) {
		// busy wait
	}
	S--;
}

signal(S) {
	S++;
}
```

### 사용
* 두가지의 semaphore 사용법
	* counting semaphore
	* binary semaphore

#### Binary semaphore
* mutex lock과 동일한 동작 ( S(mutex)가 0 or 1 사이에서만 동작 )
	* 상호배제 동작을하는 lock 기능(critical section 기능을 구현)


* P1 프로세스보다 P2 프로세스가 늦게 실행 되어야 할때는 어떻게 동작하나?
``` c
// synch의 초기 값은 0
	// => wait가 바로 실행되게 한다
P1:
	S1;
	signal(synch);

P2:
	wait(synch);
	S2;
```

#### counting semaphore
* 공유자원을 사용하는 프로세스의 갯수가 많을때 여러 프로세스간의 상호 배제를 할 수 있도록 하는 방법
* queue에 들어가는 데이터 갯수에 대해 counting한다
* counting한 걸로 제어


















