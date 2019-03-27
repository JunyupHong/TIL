# JavaScript to TypeScript Migration

## Decorator
### Props
``` javascript
props: {
	initTags: {
		type: Array,
		defaults: []
	},
	mode: {
		type: String,
		default: "creator"
	},
	require: {
		type: Object,
		required: true
	}
},
```
``` typescript
import { Vue, Component, Prop } from 'vue-property-decorator';

@Component
export default class TagEditor extends Vue {
	@Prop({default: []}) initTags!: Array<{name: string, type: string}>;
	@Prop({default: 'creator'}) mode!: string;
	@Prop({}) mode!: {}; // type을 지정해주어서 required: true의 역할을 함
}
```



### Watch
``` javascript
watch: {
	 modelTag () {
		this.updateTagFromInput();
		this.suggestGroup();
		console.log("model tag changed", this.$refs.tagInput.value);
	},
	initTags () {
		this.tags = this.$_.cloneDeep(this.initTags);
	}
},
```
``` typescript
import { Vue, Component, Watch } from 'vue-property-decorator';

@Component
export default class TagEditor extends Vue {
	@Watch('modelTag')
	onChangeModelTag(val: string, oldVal: string) {
		this.updateTagFromInput();
		this.suggestGroup();
		console.log("model tag changed", this.$refs.tagInput.value);
	}

	@Watch('initTags')
		onChangeInitTags() {
		this.tags = _.cloneDeep(this.initTags);
	}
}
```


### Emit
* Typescript 에서 두가지 방법 모두 사용이 가능하다
	* emit으로 넘길 인자가 두개 이상이면 $emit으로는 여러 인자를 넘길 수 있지만 @Emit으로는 하나의 인자 밖에 넘길 수 없다

``` javascript
private deleteTag(tag) {    
	this.tags = _.filter(this.tags, t => t.name !== tag.name);
	that.$emit("change", that.tags);
}
```
``` typescript
import { Vue, Component, Emit } from 'vue-property-decorator';

@Component
export default class TagEditor extends Vue {
	@Emit("change")
	private deleteTag(tag) {    
		this.tags = _.filter(this.tags, t => t.name !== tag.name);
		return this.tags;
	}
}
```


### computed
* getter, setter를 사용
``` javascript
computed: {
	disableGroup() {
		if (_.isNil(this.group.mainImage)) {
			return '메인 이미지';
		} else if (_.isEmpty(this.group.project)) {
			return '프로젝트';
		} else if (_.isEmpty(this.group.category)) return '카테고리';
		return '';
	}
}

```
``` typescript
get disableGroup() {
	if (_.isNil(this.group.mainImage)) {
		return '메인 이미지';
	} else if (_.isEmpty(this.group.project)) {
		return '프로젝트';
	} else if (_.isEmpty(this.group.category)) return '카테고리';
	return '';
}
```








