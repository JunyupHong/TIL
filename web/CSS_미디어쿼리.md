# CSS 미디어 쿼리
## 미디어 쿼리
* 반응형 웹에서 사용
* 브라우저 지원
	IE 9+, chrome 21+, firefox 3.5+, safari 4.0+, opera 9+

### 연산자
* 미디어 쿼리의 연산자: and, or, not, only

* and 연산자
	여러 미디어 특징들을 하나로 결합
* , (or)연산자
	각각 개별 미디어 쿼리를 나타냄
* not 연산자
	전체 미디어 쿼리를 부정
* only 연산자
	미디어 쿼리를 지원하지 않는 브라우저가 주어진 스타일을 적용하는 것을 방지
**주의**
	* 	not이나 only 연산자를 사용하려면 미디어 타입을 규정해야함
	* 미디어 쿼리는 대소문자를 구별하지 않음

### media type (미디어 종류)
* all, print, speech, screen이 있다

* all
	* default값
	* 모든 미디어 장치에 사용
* print
	프린터에 사용
* screen
	컴퓨터 스크린, 테블릿, 스마트폰 등 스크린에 사용
* speech
	페이지를 읽어주는 화면 낭독기

### media feature(미디어 특징)
* width & height
	* 화면의 너비 & 높이
	* min-, max- 접두어 사용가능
```
// 너비가 700px이상 1000px이하에서 사용
	@media (min-width: 700px) and (max-width: 1000px)
```

* device-width & device-height
	* 	출력장치의 너비 & 높이
	(ex 컴퓨터 스크린)

* aspect-ratio
	* 화면 영역의 가로 세로 비율
```
// 가로/세로 비율이 1:1이상일때 적용
	@media screen and (min-aspect-: 1/1)
```

* device-aspect-ratio
	* 	출력장치의 가로 세로 비율

* color
	* 출력 장치의 색상 구성요소 당 비트 수
```
// 색상 구성요소당 최소 4비트를 지닌 장치에 적용
	@media all and (min-color: 4)
```
* 	color-index
	* 장치가 표시할 수 있는 색상 수

* grid
	* 출력 장치가 그리드 장치 또는 비트맵 장치냐에 따라 결정
	* 그리드 기반이면 1, 아니면 0

* monochrome
	* 흑백 장치에 색상 당 비트 수

* orientation
	* 	화면이 가로모드인지 세로모드인지 지정
```
// 화면이 가로모드 일때만 적용
	@media all and (orientation: portrait)
// 화면이 세로모드 일떄만 적용
	@media all and (orientation: landscape)
```

* resolution
	* 출력장치의 해상도

### 사용법
1. css내부에 삽입
```
@media (max-width: 700px) {
	// max-width가 700px 일 때 모든 미디어 쿼리에 적용
	// and all이 생략되어있음!
}

@media print, (min-width: 700px) and (max-width: 1000px) {
	// min-width가 700px, max-width가 1000px 이거나 print 장치일 때 적용
}

@media not screen and print,(min-width: 700px) {
	// 모든 screen 장치와 print장치에 적용하지 않고,
		// (not연산자는 개별 미디어 쿼리 전체에 영향)
	// min-width가 700px일 때 적용
		// (not연산자가 ,뒤에는 영향을 미치지 않는다)
}
```
2. 링크로 연결
```
<link rel="stylesheet" media="mediatype and|not|only mediafeature" href="file.css">
```
