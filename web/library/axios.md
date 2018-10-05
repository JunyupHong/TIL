# axios
* HTTP요청을 위한 라이브러리
* express에서 통신을 할 때 jquery를 사용($.get(), $.post()) => 그러나 Vue에서는 통신만을 위해 jquery를 사용하기엔 비용이 크다 => axios를 사용

> jqurey에는 put, delete가 없다!  
> put, delete는 따로 ajax를 이용해 선언해서 사용  
```
// put 선언
$.put = /function/(url, data, callback, type) {
  if ($.isFunction(data)) {
    type = type || callback;
    callback = data;
    data = {};
  }

  return $.ajax({
    url: url,
    type: ‘PUT’,
    success: callback,
    data: data,
    contentType: type
  });
};

// delete 선언
$.delete = function(url, data, callback, type) {
  if ($.isFunction(data)) {
    type = type || callback;
    callback = data;
    data = {};
  }

  return $.ajax({
    url: url,
    type: ‘DELETE’,
    success: callback,
    data: data,
    contentType: type
  });
};
```


## 설치
`$ npm install axios`


## 사용 (Vue)
``` javascript
// import를 해준다
import axios from 'axios';

// prototype에 $https로 axios를 선언
Vue.prototype.$http = axios;

// 사용
await this.$http.post(...);
await this.$http.get(...);
```