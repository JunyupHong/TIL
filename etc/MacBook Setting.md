# MacBook Setting

### Mac OS를 설치
* 운영체제 업데이트 시 포맷을 해주는게 좋다
* 환경설정 변경
	* 한영키 / spotlight 설정
	* 기타 어플 설치

### brew 설치
* 패키지 매너저
* brew install 검색 -> macOS 용 패키지 관리자-Homebrew 설치

### node 설치
* node 패키지 관리자
* node를 설치하면 npm도 같이 설치됨
`$ brew install node`

### npx 설치
* node_modules 안에 있는 모듈을 실행해 줄 수 있는 라이브러리
`$ npm i -g npx`

### 전역으로 npm 모듈 설치 (ngork, http-server 등)
* 프로젝트 내부에 포함되는 모듈이 아니다
* 프로젝트 외부에서 실행하거나 독립적으로 실행하는 모듈이므로 글로벌로 설치
`$ npm i -g ngrok`

### 프로젝트 생성 후 지역으로 npm 모듈 설치(babel, webpack 등)
* 프로젝트 내부에 포함되는 모듈
* 전역으로 설치하면 버전관리에 취약
* 각 프로젝트의 환경설정을 전역이 아닌 각각의 프로젝트에서 구성해야함
`$ npm i babel`