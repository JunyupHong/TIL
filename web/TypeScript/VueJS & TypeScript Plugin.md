# VueJS & TypeScript - Plugin

## 필요한것
### component.vue 파일
* 플러그인으로 등록할 vue를 컴포넌트 생성할때 처럼 만든다
* plugin으로 등록할 template, script, style을 작성

### plugin.ts (index.ts)
* declare를 사용해 `vue/types/vue`에 plugin으로 등록한 모듈의 함수를 추가등록한다 (등록하지 않으면 typescript가 plugin의 타입을 알지못함 => plugin의 함수를 모름)
* install 함수를 등록
	* 플러그인 등록시 실행
	* plugin 인스턴스를 생성해서 dom을 붙여준다

### app.ts
* plugin을 등록 `Vue.use(Plugin)`
* declare를 사용해 `/vue/types/vue`의 Vue인터페이스에 plugin으로 등록할 모듈을 추가 등록 (Vue가 plugin을 알지못함)

- - - -

## component.vue 파일
* 플러그인으로 등록할 vue를 컴포넌트 생성할때 처럼 만든다
* plugin으로 등록할 template, script, style을 작성


## plugin.ts 파일 (index.ts)
### install 함수가 존재해야한다
* => 플러그인 등록시(`Vue.use(plugin)`) install 함수가 실행됨
* install 함수 내부에서 dom을 생성하고 붙여줘야함

### 플러그인.vue 파일을 받아와서 생성자로 plugin 인스턴스를 생성해준다
* 플러그인 인스턴스로 install 함수 내부에서 dom을 붙여줌

### Vue.prototype에 plugin 인스턴스 등록
* 함수를 Vue.prototype에 등록( `Vue.prototype.$open = ()=>{}` )할 수 도 있지만 plugin 인스턴스를 바로 등록해서 사용
	* 	함수는 컴포넌트 내부에서만 사용해야하고 plugin.ts에서는 플러그인만 등록해주는  역할!
* `Vue.prototype.$plugin = pluginInstance;`

* 이때 hot reload 방지 (조건문)
	* 계속해서 pluginInstace가 Vue.prototype.$plugin에 새로 할당되면 관리하기 어렵다

### declare로 vue의 Plugin 인터페이스에 plugin 함수 등록
* 등록하지 않으면 Vue가 Plugin의 내부 함수들을 인식하지 못한다
* 등록하면 plugin 내부 함수들을 인식하게 해줌
	* plugin. 을 하면 plugin의 함수가 나타난다

> plugin을 인식 못하는 것은 Vue에서 plugin type을 등록하지 않았기 때문  
> 	=> app.ts에서 declare로 Vue interface에 plugin을 등록해주어야함  

```typescript
// 플러그인.vue 파일을 받아와서 생성자로 생성
import Plugin from 'plugin.vue';

// declare로 /vue/types/vue의 Plugin 인터페이스에 함수 등록 (타입지정)
declare module '/vue/types/vue' {
	interface Plugin extends Plugin {
		// Plugin의 함수들
		// ex)
		// open();
		// close();
	}
}

export default {
	// 플러그인 인스턴스 생성
	pluginInstance: new Plugin();

	// install 함수 선언
	install(vue: typeof Vue, options: any) {
		// 내부에서 dom을 생성 & 붙여줘야함

		// pluginInstance를 Vue.prototype에 등록
		if(Vue.prototype.$plugin != undefined) {
			// hot reload 방지를 위해서 조건문을 걸어줌
			// 	(계속해서 pluginInstace가 정의되면 관리하기 어렵다)
			Vue.prototype.$plugin = this.pluginInstance;
		}
		// mount를 실행해서 plugin dom을 붙여준다
		this.pluginInstance.$mount(document.body.appendChild(document.createElement('div')));
	}
}
```



## app.ts 파일
### 플러그인 등록
* `Vue.use(Plugin);`

### declare로 vue의 Vue 인터페이스에 plugin 등록
* 등록하지 않으면 Vue가 Plugin을 인식하지 못한다
* 등록하면 plugin을 인식하게 해줌

> plugin 내부의 함수들을 인식하지 못하는 것은 Vue에서 plugin의 함수를 등록하지 않았기 때문  
> 	=> plugin.ts(index.ts) 에서 declare로 plugin에 plugin의 함수를 등록해주어야함  

```typescript
import Plugin from 'plugin.ts';
// import Plugin from 'plugin'; // => 플러그인이 index.ts에 있으면 폴더이름으로 Plugin을 import함

// 플러그인 등록
Vue.use(Plugin);

// declare로 /vue/types/vue의 Vue 인터페이스에 플러그인 등록 (타입지정)
declare module 'vue/types/vue' {
	interface Vue {
		$plugin: Plugin;
	}
}
```


- - - -

## 사용
* plugin으로 등록이 끝나면 `this.$plugin.func()` 으로 사용


