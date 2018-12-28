# kocw 운영체제 4. Thread
## Thread
* 대부분의 어플리케이션은 멀티 스레드 방식으로 개발된다
* 프로세스 하나를 생성하는 작업보다 스레드를 생성하는게 부하가 더 작다
* 운영체제의 커널도 멀티 스레드

* 스레드는 code, data, file을 가지고 있다
* 멀티 스레드일 경우 이것을 공유

### 이점
* Responsiveness(응답성) - 프로세스의 일부가 블록 당해도 해당 스레드는 계속적으로 동작이 가능
* Resource Sharing(자원공유) - 구조적으로 스레드는 프로세스의 리소스를 상호간에 공유한다
* Economy(경제성) - 프로세스를 생성하는것 보다 훨씬 경제적, 프로세스 간의 컨텍스트 스위칭보다 스레드 간의 스위칭이 더 작은 overhead를 가진다
* Scalability(확장성) - 멀티 스레드를 사용함으로써 프로세스가 멀티프로세서 아키텍쳐의 이점을 갖는다



## Multicore Programming
* 멀티코어 시스템 = 멀티 프로세서 시스템
* 병렬적인 처리를 위해서 멀티코어시스템은 프로그래머에게 pressure를 가한다
	* Dividing activities (Task identification) : task를 병렬화해서 나눠준다
	* Balance : 각각의 core들이 비슷한 양의 task를 가져야한다
	* Data splitting : 데이터도 나누어서 분배해야함, 나눠진 
	* Data dependency : 나눠진 데이터의 의존성 고려
	* Testing and debugging : 다양한 실행경로가 가능해야한다

### Parallelism
* 시스템이 한 task이상을 동시에 수행할 수 있다
* 멀티코어 - 실질적인 병렬 수행
* 종류
	* Data Parallelism - 계산할 데이터를 각 프로세스들에게 나눠서 제공
	* Task parallelism - 수행할 task를 각 프로세세들에게 나눠서 제공

### Concurrency
	* 하나 이상의 task가 진행될 수 있도록 지원하는 형태
	* 멀티코어에서 동시수행 하는것 뿐아니라 싱글코어(하나의 프로세스)에서도 둘 이상의 task가 동시에 실행되는 것처럼 보이는것도 포함 (time-sharing 등)


## Amdahl’s Law
* 추가적인 프로세싱 코어를 추가했을때 특정 어플리케이션에 얼마만큼의 성능의 향상이 있을 것인가 를 알 수 있다
* 프로그래머가 얼마나 병렬성을 염두에 두었는지(dividing activity, balance, data splitting, data dependency, test and debugging)에 따라 차이가 발생한다
* 어플리케이션의 serial한 부분은 추가 코어를 추가하여 얻은 성능에 불균형한 영향을 준다



## User Threads & Kernel Threads
### User Threads
* 유저레벨의 thread 라이브러리를 사용해서 사용
	* unix : POSIX Pthreads 라이브러리
	* window threads
	* Java threads

### Kernel Threads
* 운영체제 내부 kernel에서 지원해주는 Thread

> 두 스레드 사이에는 반드시 relationship이 있어야한다  


### Thread Libraries - Pthread
* unix 운영체제에서 사용 (window에서도 사용 가능)
``` c
#include <pthread.h>
```




























