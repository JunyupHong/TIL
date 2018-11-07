# HTML_iframe태그
## iframe 태그
* HTML Inline Frame 요소
* 다른 HTML 페이지를 현재 페이지에 포함시크는 중첩된 브라우저 컨텍스트를 나타냄
* 광고나 파일 다운로드에 많이 사용됨

### 속성
* width, height: 가로, 세로의 길이
* frameborder: iframe의 border여부 (yes | no)
* marginwidth, marginheight: iframe의 border와 내부 페이지 사이의 여백
* align: 정렬 (left | right | center)
* allowtransparency: 배경색을 투명하게 만들어주는 속성 (true | false)
* hspace, vspace: 가로, 세로 여백
* scrolling: 스크롤바 사용여부 (yes | no | auto )
* name: iframe의 이름 (iframe의 이름을 지정하고 다른 a태그의 target을 iframe의 이름을 설정해주면 눌렀을때 iframe안에 있는 페이지가 a태그의 href주소에 따라 이동한다)

## 사용
``` html
<iframe src="https://www.naver.com" name="myIframe"></iframe>
```



