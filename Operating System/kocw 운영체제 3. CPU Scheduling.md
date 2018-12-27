# kocw 운영체제 3. CPU Scheduling
## CPU Scheduling
* OS의 멀티프로그래밍의 기초
* 다양한 CPU Scheduling 알고리즘 이 존재

### Basic concepts
* CPU-I/O Burst Cycle
* ![cpu_scheduling](https://user-images.githubusercontent.com/39546874/50421160-ac886700-087f-11e9-8341-134e66a1c7ca.png)
	* 프로세스 실행은 CPU 실행과 I/O대기 사이클로 구성된다
	* CPU 버스트는 I/O버스트 뒤에 온다
	* CPU 버스트 분포는 스케줄링의 주요 관심사
	* I/O 바운드 프로세스는 많은 CPU 버스트를 갖는다


## CPU Scheduler
* 단기 스케줄러는 ready Queue의 프로세스 중 에서 선택하고 그 중 하나에 CPU를 할당한다
	* Queue는 다양한 방식으로 정렬된다
* CPU 스케줄링 결정은 프로세스가 아래의 4가지 상태로 전환될때 발생할 수 있다
	*  실행 상태에서 대기 상태로 전환할 떄
	* 실행 상태에서 준비 상태로 전환할 때
	* 대기 상태에서 준비 상태로 전환할 때
	* 종료될때

* 1번(실행 -> 대기) 과 4번(종료) 의 스케줄링은 비선점적이다
	* 일단 CPU가 할당되면 CPU는 release되거나 대기 상태가 될떄까지 CPU를 유지한다
	* 스케줄링할 새로운 프로세스를 선택하는 것외에 선택의 여지가 없다
* 다른 모든 스케줄링은 선점적이다
	* 공유 데이터에 대한 접근을 고려해야한다
	* 커널 모드에서 선점을 고려해야한다
	* 중요한 운영체제 활동 중에 발생하는 인터럽트를 고려해야한다



## Dispatcher
* dispatcher 모듈은 단기 스케줄러가 선택한 프로세스에 대해 CPU를 제어한다
	* 컨텍스트 스위칭
	* 사용자 모드로의 전환
	* 프로그램을 재시작하기 위해서 유저 프로그램에서 적절한 위치로 점프하는것

* Dispatch latency
	* 디스패치 대기시간
	* dispatcher가 하나의 프로세스를 중지하고 다른 실행을 시작하는데 걸리는 시간
	* 최대한 빠르게 해야한다


## Scheduling Criteria 스케줄링 기준
* CPU utilization (CPU 사용률) - CPU 사용량
* Throughtput (처리량) - 단위 시간당 실행을 완료한 프로세스 수
* Turnaround time (처리 시간) - 특정 프로세스를 실행하는데 걸리는 시간
* Waiting time (대기 시간) - 프로세스가 준비 대기열에서 대기한 시간
* Response time (응답 시간) - 요청이 제출된 후 첫번째 응답이 생성되고 출력되지 않을 때까지 소요되는 시간 (time-sharing 환경의 경우)

### Scheduling Algorithm Optimization Criteria 스케줄링 알고리즘 최적화 기준
* Max CPU utilization
* Max throughput
* Min turnaround time
* Min waiting time
* Min response time



## CPU 스케줄링의 알고리즘
### First-Come, First-Served Scheduling (RCFS)
* 첫번째 들어오는 프로세스를 가장 먼저 서비스 해준다 (들어온 순서대로 서비스 해준다)

#### 예시
* Process가 들어오는 순서 - P1 -> P2 -> P3
* Burst Time: P1: 24, P2: 4, P3: 4
	* waiting time for P1: 0, P2: 24, P3: 27
	* Average wating time: 17

* Process가 들어오는 순서가 P3 -> P2 -> P1으로 바뀌면
	* waiting time for P1: 6, P2: 3, P3: 0
	* Average wating time: 3
	* 으로 바뀐다

* 순서에 따라 스케줄링의 성능이 급격하게 차이나는 현상이 발생 =>RCFS에서 waiting time은 최선이 아니다!

* Convoy effect
	* 긴 process 뒤에 있는 짧은 process들로 인해 실행시간이 저하되는 현상
	* 하나의 긴 CPU bound 및 많은 I_O bound 프로세스가 있다고 가정하면 I_O는 idle상태를 유지하고 CPU bound의 긴 프로세스가 끝날 때까지 대기할 것
	* 스케줄링 알고리즘에 있어서 바람직한 현상이 아님

> CPU bound: few long CPU burst  
> I/O bound: man short CPU burst  


###  Shortest-Job-First Scheduling (SJF)
* 가장 짧은 작업을 먼저 스케줄링 한다
* 시스템에서 수행하고자하는 프로세스의 CPU burst 길이를 미리 알아야 가능
* 전제 조건이 실현 가능하다면 가장 최적의 알고리즘!
* CPU burst의 길이를 알기 쉽지 않다
* 실현은 어렵지만 최적의 평균 대기 시간의 알 수 있다

#### 예시
* 각 Process의 burst time: P1:6, P2: 8, P3: 7, P4: 3
* Process 실행순서: P4 -> P1 -> P3 -> P2
* Average waiting time: 7 (최적의 대기 시간)


### Determining Length of Next CPU Burst
* 슈퍼컴퓨터에서는 CPU Burst를 지정해주는 경우가 있다
* 일반적인 운영체제에서도 통계자료를 이용해 next CPU Burst를 구할 수 있다

* 선점적 버전(preemptive version)은 shortest-remaining-time-first라고 불린다
* 만약 Queue에 잔여시간이 가장 적은 프로세스가 대기열에 추가되면, 이 프로세스를 먼저 실행한다

> Preemptive Scheduling  
> 현재 CPU를 점유하고 있는 프로세스가 자발적으로 CPU를 놓지 않더라도 임의의 시점에 새로운 프로세스가 점유하고 있는 프로세스를 밀어내고 CPU를 점유할 수 있는 스케줄링 방식  


### shortest-remaining-time-first
* SJF와 유사 (SJF에 preemptive scheduling이 더해졌다)

#### 예시
* Process의 도착시간: P1: 0, P2: 1, P3: 2, P4: 3
* Burst time: P1: 8, P2: 4, P3: 9, P4: 5

* P1이 1동안 CPU를 점유하고 있다가 P2가 도착하면 P2가 CPU를 선점하게 되고 실행한다
* 이후 P3와 P4가 순서대로 도착하지만 Burst time이 커서 P2를 밀어낼 수 없으므로 P2가 끝나고 Burst time이 작은 순서(P4 -> P1 -> P3)가 실행된다
* 이때 P4: 5, P1: 7, P3: 9의 CPU Burst동안 실행된다
* => P1: 1 ~ 10까지 기다림, P2: 0, P3: 2~17까지 기다림, P4: 3~5까지 기다림
* => Average waiting time: 6.5


### Priority Scheduling
* 우선 순위 스케줄링 => 실시간 운영체제(realtime OS)에서 많이 사용됨
* Priority number(정수)는 각 프로세스에 부여된다
* CPU는 우선 순위가 가장 높은 프로세스에 할당된다 (우선순위 숫자가 낮은 프로세스가 할당된다)
	* 선점 & 비선점 방식 둘다 구현 가능

* SJF는 우선순위 스케줄링이며, 우선 순위는 다음에 예측된 CPU 버스트 시간의 역순이다

* 문제점: Starvation - 우선순위가 낮은 프로세스는 실행이 안될 수 도 있다
* 해결법: Aging - 대기 시간이 길어지면 우선순위를 높여준다


#### 예제
* Process: burst time, priority
	* P1: 10, 3
	* P2: 1, 1
	* P3: 2, 4
	* P4: 1, 5
	* P5: 5, 2

* 우선순위가 높은 순서(P2 -> P5 -> P1 -> P3 -> P4)대로 실행된다
* Average waiting time: 8.2


### Round Robin Scheduling (RR)
* time-sharing 기반의 알고리즘에서 가장 널리 사용된다
* 각 프로세스들이 small unit of CPU time(10~100 msec)이라는 time quantum을 균등하게 할당 받는다 => 유기적으로 실행
	* time quantum은 context switching 시간의 100배 이상이 적절
* 프로세스는 자신의 time quantum동안 CPU를 사용하고나면 자발적으로 ready queue의 맨 마지막으로 이동한다
* 다음 프로세스가 time quantum동안 CPU를 실행… 반복

* ready queue에 n개의 프로세스가 있고 time quentum이 q인 경우, 각 프로세스가 할당받은 time quentum을 모두 합치면 1/n CPU시간을 동일하게 얻는다
* 이때 어떠한 프로세스도 (n-1)*q 만큼의 시간을 기다리지 않아도 된다

* Timer Interrupt
	* 매 time quantum이 경과될때 마다 timer interrupt가 발생해서 다음 번 프로세스가 실행된다

* 성능
	* time quantum이 크다면(10초 이상) => FIFO (10초 이내에 프로세스가 끝나면 FIFO처럼 동작한다)
	* time quantum이 작다면(10msec 이하) => 컨텍스트 스위칭하는데 대부분의 시간을 사용(효율이 떨어짐), 컨텍스트 스위칭하는 시간보다도 time quantum이 작다면 오버헤드가 발생하게 된다

#### 예시
* Time quantum: 4
* Process: burst time
	* P1: 24
	* P2: 3
	* P3: 3

* P1, P2, P3가 전부 0초에 들어왔다고 하면 P1이 4만큼 실행되고, P2가 3만큼, P3가 3만큼 실행된다. 그 후 P1이 21만큼 실행된다

#### SJF와 비교
	* 전체 처리시간(Turnaround time)은 RR이 더 길다 (RR은 컨텍스트 스위칭에 걸리는 시간이 많다)
	* 하지만 응답시간(Response time)은 더 빠르다 (RR은 앞선 프로세스가 끝나지 않아도 뒤의 프로세스들이 응답을 하므로 응답시간이 빠르다, SJF는 앞선 프로세스가 끝나야 뒤의 프로세스들이 응답을 하므로 느리다)
> 처리시간(Turnaround time): 첫 작업이 시작하는 시점부터 모든 작업이 끝나는 시점  
> 응답시간(Response time): 사용자의 요청에 대한 첫번째 응답을 제공하는 시간(작업이 모두 끝나는 것을 필요로하는 것은 아니다)  
































