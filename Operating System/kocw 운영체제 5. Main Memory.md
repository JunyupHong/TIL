# kocw 운영체제 5. Main Memory
## Memory Management
* 운영체제에서 담당하는 기능 - 메모리에 대한 관리 기능
* swapping
* 연속적인 메모리 할당
* 비연속적인 메모리할당
	* Segmentation
	* Paging
* Pade Table 구조

* 프로그램이 실행되기 위해서는 보조기억장치로부터 메모리에 로드되어야 실행이 가능하다
* 메모리와 레지스터들은 CPU가 직접적으로 접근할 수 있는 유일한 저장소/장치
* 메모리 유닛은 주소와 함께 read 또는 write 요청에 대한 연속적인 스트림을 받는 형태로 동작 (주소+요청 의 형태)
* 레지스터는 one CPU clock 속도로 동작한다
* 메인메모리의 실행속도는 CPU의 속도보다 느리다 => stall (CPU가 멈추고 기다려야함)을 유발
* 캐시메모리는 메인메모리와 CPU 레지스터 사이에 존재 => 캐시메모리에 자주 쓰이는 데이터를 저장함, 다음번 접근시 더 빠르게 해줌
* 메모리 Protection
	* 메모리에서 데이터를 가져오는 과정에서 데이터가 손실되거나 오류발생했을때 ecc (에러커넥션코드)를 이용 - 물리적(하드웨어적)으로 지원
	* 허가되지 않은 메모리 접근을 하고자하면 protection해줄수 있는 기능 지원



## Base & Limit Register
* 한쌍의 base & limit Register는 프로세스가 접근할 수 있는 논리 주소 공간을 정의한다
* base register : 프로세스가 접근하는 메모리의 시작 주소
* limit register : 프로세스의 크기

* CPU는 사용자 모드에서 생성된 모든 메모리 접근이 해당 사용자에 대한 base 주소와 limit 주소 사이인지 확인해야함
* 이를 통해 메모리 Protection을 구현 => 다른 유저 프로세스가 자신의 base&limit register사이가 아닌 곳에 접근하지 못하게 한다 (다른 영역을 접근할 경우 trap을 발생시킴)
* context switching에 의해 실행되는 프로세스가 변경될 때마다 base & limit register의 값도 같이 변경된다

* 만약 프로세스가 300040~420940의 메모리에 접근하고 있다면 base register가 300040이고 limit register가 120900 이 될 것이다 => 다른 프로세스들은 이 사이 메모리에 접근 불가


### 결론
* 운영체제에서 발생하는 모든 메모리에 대한 접근은 하드웨어적으로 세팅되어있는 base & limit register사이에 존재할때 실제 메모리로 접근하게 된다
* 아닐경우 트랩이 발생
![os_hardware_address_protection](https://user-images.githubusercontent.com/39546874/50471969-3bb98a00-09fa-11e9-8c50-d7d25fcdb6f5.png)









## Page Table
* Page table은 메인 메모리에 구성된다
* 각 어플리케이션마다 PTBR과 PTLR을 세팅해준다
	* Page-table base register(PTBR)은 페이지 테이블을 가르킨다
	* Page-table length register(PTLR)은 페이지 테이블의 사이즈
	* 컨텍스트 스위칭이 일어날때마다 값이 바뀜

* 페이지 테이블에 대해서 접근을 하고 난 뒤에 다시 physical 메모리에 접근을 해야한다 => 두번의 메모리 접근이 필요 (페이지 테이블도 메모리에 있으므로) => 오버헤드 발생
* 이러한 문제를 TLB 로 해결 (캐시 메모리와 같은 구조)
* 하지만 TLB에 메모리가 없다면(=hit miss, TLB는 캐시처럼 일부 메모리만 저장되므로 찾는 메모리가 없을 수 있다) 페이지 테이블을 거쳐서 메모리에 접근해야 한다

* 메모리에 접근할때 페이지 테이블을 거치는 방법과 TLB를 이용한 방법을 같이 실행함
* TLB에서 hit가 되면 바로 접근하고 miss되면 페이지 테이블을 거쳐서 접근할 때까지 기다린다

### TLB (translation look-aside buffers)
* special fast-lookup 하드웨어
* 메모리의 주소를 주고 명령을 주면 주소에 있는 데이터가 나오는것(일반적인 메모리 접근)이 아니라
* 찾고자 하는데이터를 넘겨주면 그 데이터를 찾아주는 형태

* 일반적으로 아주 작은 메모리를 갖는다 => 빠르게 동작해야함, 구현하는데 비용이 많이 든다

#### Associative memory
* TLB는 associative memory (연관 메모리 == CAM content addressable memory)
	* 주소가 아닌 컨텐츠로 메모리를 찾는다

* parallel search
	* 내가 찾고자하는 메모리 내용을 전체 엔트리로부터 일괄 검색을 해준다














