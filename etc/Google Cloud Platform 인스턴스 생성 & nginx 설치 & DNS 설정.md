# Google Cloud Platform 인스턴스 생성 & nginx 설치 & DNS 설정

## 인스턴스 생성
1. compute engine에서 VM 인스턴스 생성
	ubuntu로 생성

2. VM 인스턴스의 외부ip를 고정ip로 설정
	네트워킹 -> VPC 네트워크 -> 외부 IP 주소 -> 고정으로 설정

3. 방화벽 설정
	네트워킹 -> VPC 네트워크 -> 방화벽 규칙
	(수신 & 송신 다 설정 필요)

*  gcp ssh로 접속하는 방법 찾아보기

## 가비아에서 DNS 설정
1. 가비아에서 DNS 설정(레코드 추가)
* A타입
	* 호스트: @ (@는 기본 도메인 junyup.xyz 를 설정)
	* 값: VM 인스턴스의 외부 ip		
* CNAME
	* 호스트: * (*은 모든 서브도메인 설정 ex test.junyup.xyz)
	* 값: 내 도메인 이름.(==junyup.xyz.)


## nginx
* 동시접속 처리에 특화된 웹 서버 프로그램
* 정적 파일을 처리하는 HTTP 서버로서의 역할
* 응용프로그램 서버에 요청을 보내는 리버스 프록시로서의 역할

### 설치
1. VM 인스턴스에 접속해서 nginx 설치
	* Compute Engine -> VM 인스턴스 -> 연결(SSH, 브라우저 창에서 열기 등)
	* 설치: `$ sudo apt-get install nginx`	

2. nginx config 설정
* `/etc/nginx/nginx_conf` 파일 수정 (sudo 권한으로 접근해야 파일 수정 가능)
```
http {
	server {
		root /path/filename; // 연결해줄 파일
		server_name www.url.com; // url 경로 (이 url로 들어오면 root를 연결해준다)
	}
}
```
* nginx 재시작: `$ sudo service nginx restart`