# Vuex 핵심 컨셉
## 목차
* state
* getters
* mutations
* actions
* 모듈

- - - -

## state (상태)
* 필요한 상태를 저장
* 직접 접근해서 상태를 변경할 수 없다
* mutations의 메소드를 사용해 상태를 변경할 수 있다


### 단일 상태 트리
* 단일 상태 트리를 사용 => 이 단일 객체는 모든 애플리케이션 수준의 상태를 포함하여 원본 소스 역할을 한다
* 각 애플리케이션마다 하나의 저장소만 갖게 된다는 것을 의미
* 단일 상태 트리를 사용하면 특정 상태를 쉽게 찾을수 있고 디버깅을 위해 현재 앱 상태의 스냅샷을 쉽게 가져올수 있다
* 단일 상태 트리는 모듈성과 충돌하지 않는다

### Vuex의 state를 Vue 컴포넌트에서 가져오기
* Vue 컴포넌트에서 저장소 내부의 상태를 표시하려면 계산된 속성(computed) 내에서 일부 저장소 상태를 가져온다
``` javascript
const Counter = {
  template: `<div>{{ count }}</div>`,
  computed: {
    count () {
      return store.state.count
    }
  }
};
```
	* store.state.count가 변경되면 계산된 속성이 다시 변경되고 관련 DOM 업데이트가 트리거 된다
	* 그러나 이 패턴은 컴포넌트가 전역 저장소 단독 항목에 의존한다
	* 모듈 시스템을 사용할 때는 저장소 상태를 사용하는 모든 컴포넌트에서 저장소를 가져와야하며 컴포넌트를 테스트 할 때는 가짜데이터가 필요하다

* Vuex는 store옵션( `Vue.use(Vuex)`에 의해서 가능하다 ) 으로 루트 컴포넌트의 모든 자식 컴포넌트에 store를 import하는 메커니즘을 제공
``` javascript
const app = new Vue({
  el: '#app',
  // "store" 옵션을 사용하여 저장소를 제공
  // 그러면 모든 하위 컴포넌트에 저장소 인스턴스가 삽입된다
  store,
  components: { Counter },
  template: `
    <div class="app">
      <counter></counter>
    </div>`
});
```
	* 	루트 인스턴스에 store 옵션을 제공함으로써 저장소는 루트의 모든 하위 컴포넌트에 주입되고 this.$store로 사용할 수 있다
``` javascript
// Counter는 app(루트 인스턴스)의 하위 컴포넌트!
const Counter = {
  template: `<div>{{ count }}</div>`,
  computed: {
    count () {
      return this.$store.state.count
    }
  }
};
```


### mapState 헬퍼
* store에 있는 변수들을 상수로 사용 (enum과 유사)
* 대규모 일때만 사용한다

* 컴포넌트가 여러 저장소 상태 속성이나 getter를 사용해야하는 경우 계산된 속성을 모두 선언하면 반복적이고 장황해진다
* 이를 처리하기 위해 계산된 getter함수를 생성하는 mapState 헬퍼를 사용하여 키 입력을 줄일 수 있다
``` javascript
import { mapState } from 'vuex';

export default {
  computed: mapState({
    count: state => state.count,

    // 문자열 값 'count'를 전달하는 것은 `state => state.count`와 같다
    countAlias: 'count',

    // `this`를 사용하여 로컬 상태에 액세스하려면 일반적인 함수를 사용해야한다
    countPlusLocalState (state) {
      return state.count + this.localCount
    }
  })
};
```
* 또한 매핑된 계산된 속성의 이름이 상태 하위 트리 이름과 같을때 문자열배열을 mapState에 전달할 수 있다
``` javascript
computed: mapState([
  // this.count를 store.state.count에 매핑
  'count'
])
```


### 객체 전개 연산자 (Object Spread Operator)
* mapState는 객체를 리턴한다
* 다른 로컬 영역의 계산된 속성과 함께 사용하려면 최종 객체를 computed에 전달할 수 있도록 여러 객체를 하나로 병합하는 유틸리티를 사용해야한다
* 그러나 객체 전개 연산자를 사용하면 문법을 매우 단순화 할 수 있다
``` javascript
computed: {
  localComputed () { /* ... */ },
  // localComputed() 를 객체 전개 연산자(Object Spread Operator)를 사용하여 외부 객체에 추가
  ...mapState({
    // ...
  })
}
```

### 컴포넌트에 여전히 로컬 상태가 있을 수 있다
* Vuex에 모든 상태를 넣어야 하는 것이 아니다
* Vuex에 더 많은 상태를 넣으면 상태 변이가 더 명확하고 디버그 가능하지만, 때로는 코드를 보다 장황하고 간접적으로 만들 수 있다
* 상태 조각이 단일 컴포넌트에 엄격하게 속한 경우 로컬 상태로 남겨둘 수 있다
* 기회비용을 판단하고 앱 개발 요구에 맞는 결정을 내려야한다


- - - -

## getters
* 저장소 상태를 기반하는 state를 계산해야 할 수도 있다
* 둘 이상의 컴포넌트가 이를 사용해야하는 경우 함수를 복제하거나 공유된 헬퍼를 추출하여 여러 위치에서 가져와야한다(이상적이지 않다)
* Vuex를 사용하면 store 에서 getters를 정의할 수 있다
* getters는 store의 계산된 속성으로 생각할 수 있다 => 계산된 속성처럼 getter의 결과는 종속성에 따라 캐쉬되고, 일부 종속성이 변경된 경우에만 다시 재계산된다

* getters는 첫번째 인자로 state를 받는다
``` javascript
const store = new Vuex.Store({
  state: {
    todos: [
      { id: 1, text: '...', done: true },
      { id: 2, text: '...', done: false }
    ]
  },
  getters: {
		// doto 중에서 done이 true인 index만 리턴
    doneTodos: state => {	// 첫번째 인자로 state를 받는다
      return state.todos.filter(todo => todo.done);
    }
  }
});
```

### 속성 유형 접근
* getters는 store.getters 객체에 노출되고, 프로퍼티로 값에 접근할 수 있다
``` javascript
store.getters.doneTodos;
	// [{ id: 1, text: '...', done: true }]
```

* getters는 두번째 인자로 다른 getter도 받을수 있다
``` javascript
getters: {
	doneTodos: state => {
    return state.todos.filter(todo => todo.done);
  },
  doneTodosCount: (state, getters) => {
    return getters.doneTodos.length;
  }
};

// 사용
// store.getters.doneTodosCount;	// 1
```

* 컴포넌트 내부에서 사용
``` javascript
computed: {
  doneTodosCount () {
    return this.$store.getters.doneTodosCount;
  }
};
```

* 프로퍼티로 접근하는 getter는 Vue의 반응성 시스템의 일부로 캐시된 것!

### 메소드 유형 접근
* 함수를 반환하여 getter에 전달인자로 전달할 수도 있다 (store의 배열을 검색할 때 유용)
``` javascript
getters: {
  // ...
  getTodoById: (state) => (id) => {
    return state.todos.find(todo => todo.id === id)
  }
	// getTodoById: (state) => function(id) { return(); } 와 같은 형태
};


// 사용
// store.getters.getTodoById(2);
		// { id: 2, text: '...', done: false }
```

* 메소드를 통해 접근하는 getter는 호출할 때 마다 실행되며 결과가 캐시되지 않는다!

### mapGetters 헬퍼
* mapGetters 헬퍼는 store의 getter를 로컬 계산된 속성에 매핑한다
``` javascript
import { mapGetters } from 'vuex'

export default {
  // ...
  computed: {
    // getter를 객체 전개 연산자(Object Spread Operator)로 계산하여 추가
    ...mapGetters([
      'doneTodosCount',
      'anotherGetter',
      // ...
    ])
  }
};
```

* getters를 다른 이름으로 매핑하려면 객체를 사용
``` javascript
...mapGetters({
  // this.doneCount를 store.getters.doneTodosCount에 매핑
  doneCount: 'doneTodosCount'
});
```



- - - -

## mutaions
* Vuex store에서 실제로 상태를 변경하는 유일한 방법은 mutation하는 것
* mutation은 이벤트와 매우 유사
* 각 mutation에는 타입 문자열 핸들러가 있다
* 핸들러 함수는 실제 상태를 수정하는곳이며 첫번째 전달인자로 state를 받는다
``` javascript
const store = new Vuex.Store({
  state: {
    count: 1
  },
  mutations: {
	// mutation의 이름은 상수!
    increment (state) {
      // 상태 변이
      state.count++;
    }
  }
});
```
* 변이 핸들러를 직접 호출할 수 없다 (이벤트 등록과 비슷) => 타입이 increment인 변이가 발생하면 이 핸들러를 호출한다
* 변이 핸들러를 호출하려면 해당 타입과 함께 store.commit을 호출해야한다 (== mutation을 호출할때는 commit을 쓴다)
	* 이때 첫 인자는 함수이름
	* 두번째 인자는 전달할 argument
``` javascript
store.commit('increment');

// 컴포넌트 내부에서 사용시
this.$store.commit(‘changeAge’, this.$store.getters.age + 1);
```

### payload를 가진 commit
* mutation에 대해 payload라고 하는 추가 전달인자를 commit에 사용할 수 있다(commit의 두번째 인자)
``` javascript
mutations: {
	// mutation의 이름은 상수!
  increment (state, n) {
    state.count += n
  }
};
```
``` javascript
store.commit('increment', 10);

// 컴포넌트 내부에서 사용시
this.$store.commit(‘changeAge’, this.$store.getters.age + 1);
```

* 대부분의 payload는 여러 필드를 포함할 수 있는 객체여야하며 기록된 mutation은 더 이해하기 쉽다
``` javascript
mutations: {
	// mutation의 이름은 상수!
  increment (state, payload) {
    state.count += payload.amount
  }
};
```
``` javascript
store.commit('increment', {
  amount: 10
});
```

### 객체 스타일 commit
* mutation을 commit 할때 type 속성을 가진 객체를 직접 사용해서 하나의 인자로 commit 할 수 있다 (이때 객체는 payload로 전달된다)
``` javascript
store.commit({
  type: 'increment',
  amount: 10
});
```


### Vue의 반응성 규칙을 따르는 변이
* Vuex store의 상태는 Vue에 의해 반응하므로, 상태를 변경하면 상태를 관찰하는 Vue컴포넌트가 자동으로 업데이트된다
* => Vuex 변이가 일반 Vue로 작업 할 때 동일한 반응성에 대한 경고를 받을 수 있음을 의미
	1. 원하는 모든 필드에 앞서 저장소를 초기화하는 것이 좋다 (store의 state에 초기값을 넣어준다)
	2. 객체에 새 속성을 추가할 때  `Vue.set(obj, 'newProp', 123);` 을 사용하거나 객체를 새로운 것으로 교체해야한다


### 변이 타입에 상수 사용
* 선택사항(대규모 프로젝트에서 유용 => 어떤 mutation이 가능한지 한눈에 파악할 수 있다)
* store의 mutations의 함수 이름이 문자열 이므로 상수로 빼둘 필요가 있다
* 사용할 인스턴스에서 commit 할 때 문자열을 사용하므로 어떤 함수가 있는지 모르면 사용하기 힘들다 => 따라서 enum처럼 상수로 빼두면 명시적으로 사용할 수 있다
``` javascript
mutations: {
	// mutation의 이름은 상수!
  changeAge(state, age) {
		state.age = age;
	}
};
```
``` javascript
const StoreFunction = {
	// changeAge라는 mutation을 상수처럼 뺴둔다
  age: {
    set: 'changeAge'
  }
};


methods: {
	increaseAge() {
		// type에 문자열을 사용하면 어떤 mutation이 있는지 알기 어렵다
		// this.$store.commit('changeAge', this.$store.getters.age + 1);

		// 하지만 상수처럼 사용하면 어떤 변이가 있는지 알기 쉽다
		this.$store.commit(StoreFunction.age.set, this.$store.getters.age + 1);
	}
}
```


### mutation은 반드시 동기적(synchronize)이어야 한다
* 반드시 기억해야할 규칙
* 콜백에서 수행 된 모든 상태 변이는 본질적으로 추적 할 수 없다
* 비동기성이 state의 mutation과 결합하면 프로그램을 파악하기가 매우 어려워 질 수 있다 => 상태를 변경하는 두 가지 비동기 콜백 메소드를 호출할 때 호출되는 시점과 먼저 호출 된 콜백을 알 수 없다
* Vuex에서 mutation는 동기적으로 트랜잭션(==상호작용의 단위)한다

* 비동기로 처리해야 할 mutation는 commit을 사용해서 mutation을 호출하는 것이 아니라 actions을 사용해서 mutation을 호출한다


### 컴포넌트 안에서 mutation commit 하기
* `this.$store.commit('mutaion')`을 사용
* store.commit 호출에 매핑하는 mapMutations 헬퍼를 사용 ( mapMutations를  import해야한다)
``` javascript
import { mapMutations } from 'vuex'

export default {
  // ...
  methods: {
    ...mapMutations([
      'increment' // this.increment()를 this.$store.commit('increment')에 매핑
    ]),
    ...mapMutations({
      add: 'increment' // this.add()를 this.$store.commit('increment')에 매핑
    })
  }
};
```


- - - -

## actions
* actions는 mutations와 유사
* actions와 mutations의 다른 점
	* state를 변이시키는 대신  action으로 mutation에 대한 commit을 한다
	* 임의의 비동기 작업이 포함될 수 있다

``` javascript
const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment (state) {
      state.count++;
    }
  },
  actions: {
    increment (context) {
      context.commit('increment');
    }
  }
});
```
* 	action 핸들러는 store 인스턴스 같은 메소드/프로퍼티 세트를 드러내는 컨텍스트 객체를 받는다 (컨텍스트 객체는 store 인스턴스 자체가 아니다)
* 따라서 context.commit을 호출하여 mutation을 commit하거나 context.state와 context.getters를 통해 상태와 getters에 접근할 수 있다
* commit을 여러번 호출해야하는경우 코드를 단순화하기 위해서 ES2015 전달인자 분해를 사용한다
``` javascript
// 전달인자 분해
actions: {
  increment ({ commit }) {
    commit('increment')
  }
};
```

### 디스패치 actions
* actions 호출은 store.dispatch() 메소드로 시작된다
``` javascript
store.dispatch('increment');

// 컴포넌트 내부에서 사용
this.$store.dispatch('increment');
```

* actions에서 비동기 작업을 수행할 수 있다
``` javascript
actions: {
	// 전달 인자 분해 사용 X
	incrementAsync (context) {
    setTimeout(() => {
      context.commit('increment')
    }, 1000)
  }

	// 전달 인자 분해 사용 O
  incrementAsync ({ commit }) {
    setTimeout(() => {
      commit('increment')
    }, 1000)
  }
};
```

* actions는 동일한 payload 타입과 객체 스타일의 디스패치를 지원한다
``` javascript
// 페이로드와 함께 디스패치
store.dispatch('incrementAsync', {
  amount: 10
});

// 객체와 함께 디스패치
store.dispatch({
  type: 'incrementAsync',
  amount: 10
});
```


* 예제 (비동기 API 호출 & 여러 개의 mutation을 commit)
	* 비동기 작업의 흐름을 수행하고 commit하여 작업의 사이드이펙트(상태 변이)을 기록
``` javascript
actions: {
  checkout ({ commit, state }, products) {
    // 장바구니에 현재있는 항목을 저장
    const savedCartItems = [...state.cart.added]

    // 결제 요청을 보낸 후 장바구니를 비움
    commit(types.CHECKOUT_REQUEST)

    // 상점 API는 성공 콜백 및 실패 콜백을 받음
    shop.buyProducts(
      products,
      // 요청 성공 핸들러
      () => commit(types.CHECKOUT_SUCCESS),
      // 요청 실패 핸들러
      () => commit(types.CHECKOUT_FAILURE, savedCartItems)
    )
  }
};
```


### 컴포넌트 내부에서 디스패치 action 사용
* `this.$store.dispatch('action');`을 사용하거나
* store.dispatch() 호출에 매핑하는 mapActions 헬퍼를 사용(mapActions를 import 해야함)
``` javascript
import { mapActions } from 'vuex'

export default {
  methods: {
    ...mapActions([
      'increment' // this.increment()을 this.$store.dispatch('increment')에 매핑
    ]),
    ...mapActions({
      add: 'increment' // this.add()을 this.$store.dispatch('increment')에 매핑
    })
  }
};
```

## actions 구성하기
* action은 종종 비동기적 이다
* store.patch()가 트리거 된 action 핸들러에 의해 반환된 Promise를 처리할 수 있으며 Promise를 반환한다
``` javascript
actions: {
  actionA ({ commit }) {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        commit('someMutation');
        resolve();
      }, 1000)
    })
  }
};
```
``` javascript
// then().catch() 로 사용
store.dispatch('actionA').then(() => {
  // ...
});
```

* action 내부에 또다른 action을 사용할 수 있다
``` javascript
actions: {
  // ...
  actionB ({ dispatch, commit }) {
    return dispatch('actionA').then(() => {
      commit('someOtherMutation')
    })
  }
}
```

* JavaScript 기능인 async/await 사용 가능
``` javascript
// mutations의 getData() 및 getOtherData()가 Promise를 반환한다고 가정
actions: {
  async actionA ({ commit }) {
    commit('gotData', await getData());
  },
  async actionB ({ dispatch, commit }) {
    await dispatch('actionA'); // actionA가 끝나면 아래로 넘어감
    commit('gotOtherData', await getOtherData());
  }
}
```

> store.dispatch()가 다른 모듈에서 여러 action 핸들러를 트리거 하는것이 가능하다  
> 이 경우 리턴된 값은 모든 트리거 된 처리기가 완료 되었을때 처리되는 Promise이다  
