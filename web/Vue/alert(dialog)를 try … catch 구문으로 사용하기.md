# alert(dialog)를 try … catch 구문으로 사용하기
* alert이 실행되고 positive / negative 버튼을 누를때 callback을 이용해 다음 동작을 실행했다
* 하지만 alert을 실행할때 Promise를 리턴해주면 부모 컴포넌트 또는 페이지에서 try catch구문으로 positive / negative 버튼의 다음 동작을 콜백 없이 실행할 수 있다 (스파게티 코드를 방지)

- - - -

## callback을 이용한 alert/dialog
``` pug
// my-dialog.vue template
.dialog-field(v-if='active')
	.dialog-contents
		.title {{title}}
		.text {{text}}
		.commands-group
			.flex-empty
			.command.negative(@click="clickNegative") {{negative}}
			.command.positive(@click="clickPositive") {{positive}}
```
``` javascript
// my-dialog.vue script
import * as _ from 'lodash';

export default {
	name: 'my-dialog',
	props: {},
	data() {
		return {
			active: false,
			title: 'title이 들어갈 곳',
			text: 'message가 들어갈 곳',
			negative: 'negative',
			positive: 'positive',
			onPositive: null,
			onNegative: null
		};
  },
	methods: {
		clickPositive() {
			// 여기에 콜백을 만들어서 positive 버튼을 누르면 다음 동작을 실행
			this.active = false;
			if(_.isFunction(this.onPositive)) this.onPositive();
		},
		clickNegative() {
			// 여기에 콜백을 만들어서 negative 버튼을 누르면 다음 동작을 실행
			this.active = false;
			if(_.isFunction(this.onNegative)) this.onNegative();
		},
		open(options) {
			if(_.isNil(options)) return;
			this.active = true;
			this.title = options.title;
			this.text = options.text;
			this.negative = options.negative;
			this.positive = options.positive;
			this.onPositive = options.onPositive;
			this.onNegative = options.onNegative;
		}
	}
};
```

``` javascript
// 사용 App.vue에 등록

// template
//	<template>
//		<div id="app">
//			<my-dialog ref="defaultDialog"></my-dialog>
//		<router-view/>
//		</div>
//	</template>

// script
import Dialog from './components/my-dialog';

Vue.component(Dialog.name, Dialog);

export default {
	name: 'App',
	store,
	methods: {},
	mounted() {
		// Vue의 prototype에 선언해놓고 this.$dialog로 사용!
		Vue.prototype.$dialog = this.$refs.defaultDialog;
		Vue.prototype.$http = axios;
	}
};


// 사용하는 곳에서 dialogOpen()을 실행
dialogOpen() {
	this.$dialog.open({
		title: this.dialogTitle,
		text: this.dialogText,
		negative: this.dialogNegativeMessage,
		positive: this.dialogPositiveMessage,

		// positive, negative 버튼을 눌렀을때 동작을 정해준다
		onPositive: () => {
			console.log('on positive');
		},
		onNegative: () => {
			console.log('on negative');
		}
	});
}
```

- - - -

## try catch구문을 이용한 alert/dialog
``` pug
// my-dialog.vue template
.alert-dialog(v-if='use')
	.window
		.title {{option.title}}
		.message(v-html="option.message")
		.button-group
			.flex-empty
			// resolve, reject를 미리 할당해두고 onReject(), onResolve를 통해서 버튼이 눌렸을때 실행
			.button.negative(v-if="!$_.isEmpty(option.negative)" @click="onReject()") {{option.negative}}
			.button.positive(v-if="!$_.isEmpty(option.positive)" @click="onResolve()") {{option.positive}}
```
``` javascript
// my-dialog.vue script
export default{
	data() {
		return {
			use: false,
			useNegative: false,
			option: {
			title: 'title이 들어갈 곳',
			message: 'message가 들어갈 곳',
			positive: '확인',
			negative: '취소'
			},
			resolve: () => {},
			reject: () => {},
			onResolve: () => {
				this.use = false;
				this.resolve();
			},
			onReject: () => {
				this.use = false;
				this.reject();
			}
		};
	},
	name: 'alert-window',
	methods: {
		on(option) {
			if (this.$_.isNil(option.positive)) this.option.positive = '';
			else this.option.positive = option.positive;

			if (this.$_.isNil(option.negative)) this.option.negative = '';
			else this.option.negative = option.negative;

			this.option.title = option.title;
			this.option.message = option.message;
			this.use = true;

			// Promise를 리턴해준다
			return new Promise((resolve, reject) => {
				this.resolve = resolve;
				this.reject = reject;
			});
		},
		off() {
			this.use = false;
		}
	}
};
```

``` javascript
// 사용
try{
	// resolve, reject가 실행될때까지 기다린다!
		// resolve, reject는 dialog의 버튼이 클릭될때 실행되므로 버튼이 눌릴때까지 기다린다
		// positive 버튼이 눌리면 await 아래 구문을 실행
		// negative 버튼이 눌리면 catch 구문을 실행
	await this.$dialog.on({
		title: '이미지 삭제',
		message: `<b>선택된 이미지가 삭제됩니다.</b><br><br>정말 삭제하시겠습니까?`,
		positive: '삭제',
		negative: '취소'
	});

	// positive 버튼이 눌리면 onResolve()가 실행(resolve가 실행)되므로
	// 여기서 positive를 눌렀을때의 동작을 정의 (callback이 필요없다)
} catch(e) {
	// negative 버튼이 눌리면 onReject()가 실행(reject가 실행)되므로
	// 여기서 negative를 눌렀을때의 동작을 정의 (callback이 필요없다)
	return;
}
```



