# CSS scroll 스타일
## IE의 scroll 스타일
* 스크롤의 색만 바꿀수 있다
``` sass
	scrollbar-base-color: #000;
	scrollbar-face-color: #000;
	scrollbar-3dlight-color: #000;
	scrollbar-highlight-color: #000;
	scrollbar-track-color: #000;
	scrollbar-arrow-color: black;
	scrollbar-shadow-color: #000;
	scrollbar-dark-shadow-color: #000;
```

* ![ie_scrollbar](https://user-images.githubusercontent.com/39546874/48771956-3d5ea680-ed06-11e8-936a-7acde4662679.png)

* `-ms-overflow-style: -ms-autohiding-scrollbar`을 사용하면 스크롤바 자동 숨기기가 된다 (스크롤이 활성화 될때만 나타남)

- - - -

## Safari & Chrome의 scroll 스타일
* 스크롤의 스타일을 바꿀 수 있다 (색상, 크기 등)
``` sass
// 스크롤바 전체 영역
::-webkit-scrollbar
	width: 5px
	height: 20px

// 상하, 좌우 화살표가 포함된 부분(버튼부분)
::-webkit-scrollbar-button:start:decrement
::-webkit-scrollbar-button:end:increment
	display: block
	height: 10px

// 화살표를 제외한 몸통 부분
::-webkit-scrollbar-track
	background: #e5e5e5

// 몸통 부분에서 실제 움직이는 부분을 제외한 나머지 영역
::-webkit-scrollbar-track-piece
	background: #eee

// 스크롤바의 몸통 부분에서 실제 움직이는 부분
::-webkit-scrollbar-thumb
	background: #c1c1c1
::-webkit-scrollbar-thumb:hover
	background: #a7a7a7

// 상하, 좌우 스크롤바가 만날때 오른쪽 아래 공간
::-webkit-scrollbar-corner
	background: #fff

// corner 공간으로 크기를 변경할 수 있음
::-webkit-resizer
```


* 가상 선택자
``` sass
// 좌우 스크롤바의 모든 요소에 적용
:horizontal
// 상하 스크롤바의 모든 요소에 적용
:vertical

// 스크롤바에서 위쪽, 왼쪽 방향 button, track 요소에 적용
:decrement
// 스크롤바에서 아래쪽, 오른쪽 방향 button, track 요소에 적용
:increment

// thumb 이전에 위치한 button, track 요소에 적용
:start
// thumb 이후에 위치한 button, track 요소에 적용
:end

// 상하 화살표가 붙어 두 개가 한꺼번에 표시된 요소에 적용 (좌우도 같음)
:double-button
// 상하 화살표가 아래 위 따로 나뉘어 표시된 요소에 적용 (좌우도 같음)
:single-button
// 화살표 button이 없는 요소에 적용
:no-button

// 스크롤바 corner가 있는 요소에 적용
:corner-present

// 지금 활성화된 창의 스크롤바에 적용
:window-inactive
```