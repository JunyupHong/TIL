# kocw 운영체제 1. Operating-System Structures
## Operating System Services 운영체제 서비스
* 프로그램의 실행을 위한 환경 제공
* 프로그램, 사용자에게 서비스

### 사용자 관점에서의 제공받는 서비스
* UI: User Interface
	* Command-Line Interface(CLI) - linux, unix 의 서버…
	* Graphics User Interface(GUI)
	* Batch - 여러가지 프로그램이나 테스크를 일괄적으로 처리할 수 있도록하는 것
* Program execution
	* 사용자가 수행하고자가하는 프로그램을 시스템이 메모리에 로드하고 실행할수 있게해줌
	* 이 프로그램이 어떻게 종료될 것인가에 대한 모니터링/서비스(비정상종료, 정상종료 등)
* I/O operations
	* 사용자의 I/O 요청을 운영체제가 담당
	* 하드웨어 장치의 사용권한이 운영체제에게 있음 => 프로그램이 운영체제에 서비스를 요청
	* 파일, 프린트, 네트워크 입출력 등등
* File System manipulation
	* 전통적으로 운영체제에서 중요하게 다루는 서비스
	* 과거 DOS(Disk Operation System) => 과거에는 디스크만 operating 했다 => 과거부터 쭉 이어져옴 => 중요하다
	* 파일 단위로 프로그램이 읽고 쓰기를 할 수 있도록 해주는 기능
	* 디렉토리 생성, 검색, 삭제, 리스트, 권한설정 등등
* communications
	* 정보교환 / 통신
	* 컴퓨터 내부뿐 아니라 컴퓨터 간의 네트워크를 통한 정보 공유
	* shared memory 방식
	* message passing 방식
* error detection
	* 운영체제는 항상 에러가 날 가능성을 인지하고 모니터링 해야한다
	* 에러가 발생하면 사용자에게 알린다
	* 운영체제가 처리할 수 있는 에러면 처리하고, 처리할 수 없는 문제는 사용자에게 알려주고 적절한 방법으로 종료하거나 해결할 수 있도록 한다
	* debugging facilities - 시스템을 효과적으로 사용하기 위해서 프로그래머에게 도움이 될만한 정보들을 수집해서 보내줄 수 있는 기능

### 그 외의 기능 - 운영체제의 기능들 중에 resource sharing을 통해서 운영체제가 효과적으로 동작 할 수 있게해주는 서비스들
* resource allocation
	* 한 컴퓨터 시스템에 여러명의 사용자들이 있을떄 또는 여러개의 수행할 작업들이 동시에 있을때 리소스를 누구에게 할당할 것인가에 대한 기능
	* 가장 핵심, 중요!
	* 제한된 하드웨어 리소스를 여러 곳에서 사용하겠다고 할때 어떤 방식으로 누구에게 할당할 것인가..
* accounting
	* 운영체제가 내부적으로 사용하는 알고리즘(ex: 스케줄링)이 있다
	* 정책을 결정하는데 있어서 정책을 결정하는 시점까지의 사용자 프로그램의 동작 형태 또는 사용자의 동작 형태 통계자료를 바탕으로 정책을 결정
	* 이런 결정을 위해서 accounting 기능이 필요하다
	* => 중요한 정책 결정을 하기위한 베이스
	* 프로세스에 대한 요금 정책을 수립할때도 사용 => 고성능 컴퓨팅 환경/슈퍼컴퓨터 에서는 시스템을 사용하는 시간단위로 돈을 요구함
* protection and security
	* protection: 시스템 리소스에 대한 접근이 운영체제에 의해서 적절히 컨트롤 되는가
	* security: 시스템 외부로부터 들어오는 여러 보안 위협들에 대해서 인증 등의 절차를 통해 방어하는 기법들

## View Of Operationg System Service
<img width="930" alt="view of operating system service" src="https://user-images.githubusercontent.com/39546874/49569658-e13b8980-f977-11e8-94a7-1349c024d12e.png">
* system call 위쪽에 있는 것들(User Interface: GUI, batch, CLI)은 유저 레벨에서 동작
* system call 아래에 있는 것들(program execution, I/O operations, file systems, communication, resource allocation, accounting, error detection, protection and security)은 커널 레벨에서 동작한다
* system call
	* 운영체제 외부에 있는 사용자들과의 동작은 system call 인터페이스에 의해서 일어난다
	* 유저 레벨에서 존재하는 프로그램들이 시스템콜 인터페이스를 통해서 운영체제 서비스에 접근해서 서비스를 받을수 있다 => 통로역할!


## User Operationg System Interface - CLI
* 운영체제 사용자 인터페이스: CLI & GUI
* CLI or command interpreter 방식
	* command interpreter를 통해 명령어를 직접적으로 입력하는 방식
	* 커널에서 구현된다 => 운영체제 상에서 명령어를 받아서 구현(프롬포트에서 명령어를 구현 -> 각각의 명령어가 시스템 프로그램을 실행한다)
	* 시스템 프로그램의 형태로도 구현
* 명령 프롬포트 : shell => 사용자의 입력을 받아서 처리하는 프로그램


## User Operationg System Interface - GUI
* 사용자 친화적
* 마우스, 키보드, 모니터 중심으로 구현
* icon을 통해서 동작을 처리

### 많은 시스템들은 CLI & GUI 인터페이스를 포함한다
	* Microsoft Windows -> GUI, CLI(command shell)
	* Apple Mac OS  -> GUI(Aqua), UNIX kernel & shell
	* Unix and Linux -> CLI, optional GUI interface(CDE, KDE, GNOME)

* 터치 스크린 devices
	* touch screen device
	* mouse is not possible or not desired
	* virtual keyboard



## System Calls
* System call: 프로그래밍 인터페이스 (OS에 의해 제공되는 서비스에 대한 프로그래밍 인터페이스)
* 프로그래밍 인터페이스 => 일반적으로 사용자가 쓰는 인터페이스가 아니다 (프로그래머에게 필요한 인터페이스)
	* 일반 사용자: 응용 소프트웨어, 응용프로그램 등으로 이용
* 대부분 high-level language(C or C++) 로 작성되어 있다 (어셈블리어도 사용됨)
* 시스템 콜 자체를 직접 호출하는 것 보다는 API(Application Programming Interface)를 통해서 간접적으로 시스템콜을 호출한다 => API는 시스템 콜 번호로 사용 (번호를 다 알 순 없다)
	* API 종류 : Win32 API (Windows), POSIX API (POSIX-based system: UNIX, Linux, Mac OS), Java API (JVM) 등


















