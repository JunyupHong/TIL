# Problem & Resolve - Vue 컴포넌트 publishing 문제
* vue 컴포넌트 생성 시 모든 크기의 컴포넌트를 고려해야한다

## 문제점
* 컴포넌트의 크기(tag-field의 크기) 가 각각 달라서 suggestion-group이 input태그 바로 아래에 달리지 않는 현상
* input 태그가 position: absolute, bottom: 0의 속성을 가지고 있으므로 suggestion-group의 위치를 설정하기 어렵다

``` pug
// tag-editor 컴포넌트
.tag-editor
	.tag-field
		.tag-item(v-for="(tag,i) in tags") {{`#${tag.name}`}}
	input.tag-input(v-model="modelTag")
	.suggestion-group(v-if="!$_.isEmpty(serverTags)")
		.suggestion-item(v-for="serverTag in serverTags") # {{ serverTag }}
```
``` sass
.tag-editor
	width: 100%
	height: 100%
	margin-top: 10px
	border: solid 1px $border-color
	position: relative
	.tag-field
		@include flex
 		flex-wrap: wrap
		justify-content: start
		align-items: flex-start
		padding-left: 12px
		padding-top: 9px
		max-height: calc(100% - 38px)
		overflow-y: auto
		.tag-item
			@include height-with-line-height(26px)
			margin-right: 12px
			margin-bottom: 9px
			font-size: 14px
			padding: 0 27px 0 13px
			color: #fff
			font-weight: 700
			border-radius: 13px
			background: $tag-default-color
			position: relative
	input.tag-input
		position: absolute
		left: 0
		bottom: 0
		width: 100%
	.suggestion-group
		position: relative
		z-index: 300
		max-height: 100px
		padding: 5px 12px
		overflow-y: auto
		background: #f8f8f8
		.suggestion-item
			padding: 7px 0
			text-align: left
			letter-spacing: -0.3px
			font-size: 14px
			font-weight: 700
			color: #313131
```


## 해결
* tag-field와 input을 묶는 wrapper를 만들어준다
* 이렇게 하면 wrapper의 아래에 suggestion-group이 붙게되므로 input 아래에 달리는 것처럼 보이게 된다
``` pug
// tag-editor 컴포넌트
.tag-editor
	// tag-wrapper로 감싼다
	.tag-wrapper
		.tag-field
			.tag-item(v-for="(tag,i) in tags") {{`#${tag.name}`}}
		input.tag-input(v-model="modelTag")
	.suggestion-group(v-if="!$_.isEmpty(serverTags)")
		.suggestion-item(v-for="serverTag in serverTags") # {{ serverTag }}
```

