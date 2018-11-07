# element의 width & height

## scrollHeight / width
* 스크롤 시키지 않았을때의 전체 높이_너비 (element 전체의 높이_너비)

## clientHeight / width
* 눈에 보이는 만큼의 높이_너비 (element 중 눈에 보이는 높이_너비)
* scroll, padding, border 길이 제외

## offsetHeight / width
* 눈에 보이는 만큼의 높이/너비
* scroll, padding, border 길이 포함

## scrollTop
* 스크롤이 되어 올라간 만큼의 높이 (content의 top에서 content의 보이는 부분의 top 사이의 거리)

> 브라우저별 차이점  
> scrollHeight&scrollWidth / clientHeight&clientWidth /  offsetHeight&offsetWidth / scrollTop 모두 W3C의 표준이 아니며 브라우저별로 다르게 표현된다.  
> 같은 효과를 내더라도 경우(Dtd 여부와 종류, 수직 가운데 정렬 등)에 따라서 0~40픽셀 이상의 차이가 생길 수 있으며 올바르게 적용되는 속성과 잘못 적용되는 속성이 뒤섞여있다.  


## 이미지
![width height1](https://user-images.githubusercontent.com/39546874/48113669-4334a080-e29f-11e8-9f37-c6abe840e8b5.png)
![width height2](https://user-images.githubusercontent.com/39546874/48113670-43cd3700-e29f-11e8-8c7d-59720cad66d6.png)
![width height3](https://user-images.githubusercontent.com/39546874/48113671-43cd3700-e29f-11e8-8096-7bad0af4d53a.png)
![width height4](https://user-images.githubusercontent.com/39546874/48113672-43cd3700-e29f-11e8-9f5b-d47092b6a71c.png)



## Vue에서 화면 scroll 제어
* $el을 이용해 scroll을 제어 한다
``` javascript
// this는 Vue 인스턴스
// 화면을 맨 위로 보낸다
this.$el.scrollTop = 0;

// element의 맨 위부터 화면의 아래까지의 길이 > bricksLayout의 화면에 나타나는 길이 인 경우
if (this.$el.scrollTop + this.$el.clientHeight > this.$refs.bricksLayout.$el.clientHeight) {}
```







