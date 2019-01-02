# kocw 운영체제 6. Virtual Memory

## Virtual Memory
* 유저의 logical 메모리와 실제 physical 메모리를 분리


### 기능
* Demand Paging(요구 페이징) : 가상 메모리를 구현하기 위한 핵심 기능
* Copy-on-Write
* Page Replacement
* Allocation of Frames
* Thrashing
* Memory-Mapped Files
* Allocating Kernel Memory


### 가상 메모리의 필요성
* 전체 코드가 메모리 상에 올라가야 프로그램의 실행 가능
	* 그러나 실질적으로 프로그램을 사용할 때 코드의 모든 부분을 수행하는 것은 아님 (에러코드, 비정상적인 루틴, 큰 데이터 구조)
* 전체 프로그램 코드가 다 쓰인다 하더라도 모든 기능이 동시에 필요한건 아님

* 과거에는 프로그램 전체를 다 올리지 않고 partially-loaded 방식으로 프로그램을 올림
	* 추후에 추가적으로 코드를 메모리 상에 올림
	* => 물리적인 메모리 한계의 제약을 완화시켜줌 (4MB의 물리메모리에서 더 큰 크기의 프로그램을 실행할 수 있음)

* 가상 메모리를 사용 - 프로세스에 가상 메모리를 할당해서 실제 물리메모리에 상관없이 가상메모리에서 프로그램을 실행
	* 프로그램의 일부분만 메모리에 올라와도 메모리에서 실행할 수 있다
	* logical address 공간이 physical address 공간보다 더 크다
	* 가상 메모리를 사용하면 여러 프로세스들이 같은 페이지를 공유하도록 할 수 있다
	* 더 많은 프로그램이 동시에 실행 가능하다
	* 가상 메모리는 Demand Paging, Demand Segmentation을 통해 구현된다

## Virtual-address Space
* 프로세스가 메모리상에 저장되어서 보는 logical 한 공간
* 프로세스 하나만의 공간
* 각 프로그램의 virtual address space는 0에서 시작한다
* 프로세스는 연속된 공간인 것 처럼 느낀다
* MMU(memory management unit) : physical to logical, logical to physical 메모리 변환을 해준다

### virtual-address space의 구조
* code
	* 0번 address 부터 영역 차지
* data
	* code 바로 다음 address 부터 영역 차지
	* 전역변수, static 변수 등이 존재
* heap
	* data 다음 address 부터 영역 차지
	* 할당 될때마다 점점 더 큰 address로 영역을 차지함
	* 동적할당 데이터가 들어감(malloc, calloc 등)
* stack
	* 가장 끝 address 부터 영역 차지
	* 할당 될때마다 점점 더 작은 address의 영역을 차지함
	* 지역변수, 스택 등이 들어감



## Demand Paging
* 요구 페이징
* 페이지가 필요할때만 페이지를 메모리로 가져온다
	* 프로그램의 모든 기능을 다 쓰지않고 다 쓰더라도 한번에 다 쓰지 않는다 => 따라서 일단 필요한 page만 가져오고 다른 페이지가 필요할때 가져온다

* 적은 I_O가 필요 (메모리에 불필요한 I_O가 없다)
* 적은 memory만 필요
* 더 빠른 응답시간 - 실행하는데 필요한 메모리만 있으므로
* 더 많은 사람들이 같이 사용할 수 있다

### Lazy swapper
* Demand Paging은 swap in & swap out의 형태가 계속 된다
	* 필요한 페이지를 하드디스크에서 메모리로 올려주고 필요없는 페이지를 메모리에서 하드디스크로 빼준다
* 이때 페이지가 필요하지 않으면 페이지를 메모리로 swap하지 않는다 (Lazy memory)
* 페이지를 swap하는 swapper를 **Pager**라고 한다


### Virtual Memory의 구현
* == demand paging을 구현하는 것

#### MMU
* MMU(페이징을 담당하는 객체)가 demand paging을 담당
	* MMU의 paging하드웨어가 demand paging이 가능한 하드웨어로 확장되어야한다

* 만약 접근하려고 하는 페이지가 이미 메모리에 올라가 있으면
	* paging과 다르지 않다 (paging은 페이지가 모든 메모리에 올라가 있으므로)
* 만약 접근하려고 하는 페이지가 메모리에 올라가 있지 않으면
	* demand paging 동작을 해야함
	* storage로 부터 페이지를 메모리에 올려놓는다

#### Valid-Invalid Bit
* 페이지 테이블에 valid-invalid bit를 추가해 주어야한다
* 우리가 찾고자하는 페이지가 이미 메모리에 올라가 있는 유효한(valid) 페이지인 것과, demand paging에 의해 아직 메모리에 올라가지 않은 유효하지 않은(invalid)한 페이지인 경우를 구별해야 한다
 
* invalid일 경우 page fault를 발생시킴
	* page fault가 발생할때 운영체제에 의해서 storage에서 페이지를 메모리로 가져오고 valid-invalid 비트를 바꿔준다

#### page fault
* 특정한 페이지를 참조했을때 그 페이지가 존재하지 않는 상황 => 예외 상황! => trap 발생
* CPU입장에서 메모리에 하드디스크에 있는 데이터를 올리는 작업이 시간이 많이 걸린다
	* => CPU가 직접 하지는 않음
	* => 운영체제가 이벤트(trap)를 받아서 storage에서 메모리로 페이지를 올리는 작업을 대신 처리

##### 순서
1. 페이지를 참조 => 첫번째 참조가 없다면 트랩 발생
2. 운영체제가 다른 테이블을 확인
	* 잘못된 접근 - abort (protection 기능)
	* demand paging 때문에 현재 메모리 상에 없음 - 다음단계
3. Free frame을 찾음
	* physical 메모리 상에 비어있는 frame을 찾는다
4. Swap
	* 디스크로부터 page를 가져다가 free frame에 올려놓는다
5. page table 재설정
	* valid&invalid bit를 v로 변경
6. page fault를 일으킨 명령 다시 시작

![os_page_fault](https://user-images.githubusercontent.com/39546874/50515449-2e7ac900-0ae8-11e9-9e4e-3d3535a4c591.png)



> **frame & page**  
> frame : 물리 메모리 상의 슬롯  
> page : 가상 메모리 상의 슬롯  



### Performance of Demand paging
* 메모리에 대한 기술 => 빈번하게 일어나는 리소스 => 성능이 중요





## Page Replacement
* physical 메모리 상에 Free Frame(새로운 페이지를 저장할 비어있는 공간)이 존재 하지 않을때 실행된다

* 기존에 사용되고 있던 페이지(프레임)를 빼내서 free frame을 만든다. 이때 어떤 페이지를 결정할지에 대한 정책이 필요

### Page Replacement 실행 순서
1. 디스크에서 원하는 페이지를 찾는다
2. free frame을 찾는다
	* free frame이 존재하면 그 프레임을 사용
	* 만약 존재하지 않으면 Page Replacement algorithm을 사용해서 없앨 프레임(victim frame)을 선정한다. (만약 victim 프레임이 수정되었다면 수정된 내용을 디스크에 써준다 = swap out / 수정된 내용이 없다면 디스크에 쓰지 않아도 된다)
3.디스크에서 가져온 페이지를 free frame에 가져오고 page/frame table을 업데이트(valid-invalid bit를 변경) 한다
4. trap(page fault)이 발생한 명령을 다시 시작하여 프로세스가 실행할 수 있게 해준다


### Page & Frame Replacement Algorithms
* page & frame replacement는 한가지 알고리즘에 의해 수행되는 것이 아님
* 예시: reference string(7,0,1,2,0,3,0,4,2,3,0,3,0,3,2,1,2,0,1,7,0,1)로 페이지에 접근했다

#### First-In-First-Out (FIFO) Algorithm
* 레퍼런스가 들어온 순서대로 나간다 (가장 먼저 들어온것(오래된것)이 가장 먼저 나간다)
* 프레임을 더 추가할수록 page fault가 발생할 수 있다


#### Optional Algorithm 
#### Least Recently Used (LRU) Algorithm









