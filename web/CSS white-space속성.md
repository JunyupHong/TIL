# CSS white-space속성
* white-space 속성은 어떤 엘리먼트 안의 공백이 어떻게 처리될지를 나타내는데 쓰인다
``` sass
white-space: normal | pre | nowrap | pre-wrap | pre-line
```

## white-space의 value
* normal (default)
	* 연속된 공백(스페이스, 탭)이 하나로 병합
	* 줄바꿈 문자는 다른 공백문자와 같이 취급(줄바꿈 병합)
	* 자동 줄바꿈(text wrapping) O => 내용이 엘리먼트의 영역을 벗어나면 줄을 바꾼다
* nowrap
	* 연속된 공백이 하나로 병합
	* 줄바꿈 병합
	* 자동 줄바꿈 X
* pre
	* 연속된 공백이 보존
	* 소스의 줄바꿈 문자나 <br>요소에 의해 줄이 바뀐다 (줄바꿈 보존)
	* 자동 줄바꿈 X => 내용이 엘리먼트의 영역을 벗어나도 줄을 바꾸지 않는다
* pre-wrap
	* 연속된 공백 보존
	* 소스의 줄바꿈 문자나 <br>요소에 의해 줄이 바뀐다 (줄바꿈 보존)
	* 자동 줄바꿈 O
* pre-line
	* 연속된 공백 병합
	* 소스의 줄바꿈 문자나 <br>요소에 의해 줄이 바뀐다 (줄바꿈 보존)
	* 자동 줄바꿈 O

|          | 스페이스 & 탭 | 줄바꿈 | 자동 줄바꿈 |
|:--------:|:-------------:|:------:|-------------|
|  normal  |      병합     |  병합  |      O      |
|  nowrap  |      병합     |  병합  |      X      |
|    pre   |      보존     |  보존  |      X      |
| pre-wrap |      보존     |  보존  |      O      |
| pre-line |      병합     |  보존  |      O      |





## 생략기호 (ellipsis …)
* text-overflow: ellipsis
 
### 조건
* width 또는 height 가 고정
* overflow: hidden을 이용해 영역을 가린다
* 자동 줄바꿈이 되지않게 white-space: nowrap |  pre 를 적용한다
* text-overflow: ellipsis 로 text가 영역을 넘어갈 시 생략기호를 넣어준다

