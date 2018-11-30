# CSS 가상 요소
## 가상요소 (Pseudo-element)
* 가상 클래스 처럼, 선택자에 추가되지만 특별한 상태를 지정하는 대신, 문서의 특정 부분을 스타일링 할 수 있다

> **< 가상 클래스 (Pseudo-class) >**  
> 선택될 element의 특별한 상태를 지정하는 선택자에 추가된 키워드  
> ex) :active / :any / :checked / :hover / 등등  


### 가상요소의 종류
* ::after
	* 실제 내용 바로 앞에서 생성되는 자식요소
	* 요소의 콘텐츠 끝부분에 생성된 콘텐츠를 추가
	* content 속성이 적용 가능
* ::before
	* 실제 내용 바로 뒤에서 생성되는 자식요소
	* 요소의 콘텐츠 시작부분에 생성된 콘텐츠를 추가
	* content 속성이 적용 가능
* ::first-letter
	* 요소의 첫 번째 글자
* ::first-line
	* 요소의 첫 번째 줄
* ::selection
* ::backdrop
* ::placeholder
	* Input 필드에 placeholder 텍스트의 스타일을 적용한다
* ::marker
* ::spelling-error
* ::grammar-error

### content 속성
* ::before와 ::after랑 함께 쓰이는 가짜 속성
* HTML 문서에 정보로 포함되지 않은 요소를 CSS에서 새롭게 생성시켜줌
* normal: default 값
* string: 문자열 생성
* image: 이미지나 비디오를 불러올 수 있음(크기 조절 불가능)
* counter: 순서를 매길 수 있음(counter-increment, counter-reset과 함께 사용)
* none: 아무것도 표시하지 않음
* attr: 해당 속성의 속성값 표시