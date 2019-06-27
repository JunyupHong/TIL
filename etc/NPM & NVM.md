# NPM & NVM
## Node.js
* 구글에서 개발한 고성능 자바스크립트 엔진으로 빌드된 **서버 사이드 개발용 소프트웨어 플랫폼**
* Node.js 런타임 환경에서는 모든 종류의 서버 사이드 도구들을 제공하여 자바스크립트로 서버 개발이 가능
### 설치
* `$ brew install node`
* brew (패키지 매니저) 는 홈페이지에서 직접 설치 가능

## NPM
* Node Package Manager
* node 패키지 관리를 위한 프로그램
* Node.js를 설치하면 자동으로 설치 된다


## NVM
* Node.js의 새로운 버전이 나올 경우 버전을 업그레이드를 해야 하고, 하위 호환성을 위해서 버전을 다운그레이드 해야 할 수 있다.

* 여러 버전의 Node를 사용하기 위한 프로그램

### 설치

1. NVM 설치
* `curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.11/install.sh | bash`
* 또는 `wget -qO- https://raw.githubusercontent.com/creationix/nvm/v0.33.11/install.sh | bash`


2. 환경변수 세팅
* 확인
	* `nvm ls`

* 설치 후 환경변수 세팅이 안되어 경로를 찾지 못하면
	* `vi ~/.bash_profile` 파일에서 코드 추가
```
	export NVM_DIR="$HOME/.nvm"
	[ -s "$NVM_DIR/nvm.sh" ] && . "$NVM_DIR/nvm.sh" # This loads nvm
```

* 재시작
	* `source ~/.bash_profile` 후 재확인

3. Node 설치
* `nvm install [version]`

4. 다른 버전 Node 설치 및 버전 변경
* 다른 버전의 Node를 설치하면 사용하는 Node가 가장 최근에 설정한 Node로 변경된다
* 버전 변경은 `nvm use [version]` 으로 변경

