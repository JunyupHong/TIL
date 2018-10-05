# cors
## CORS (Cross-Origin Resource Sharing)
* HTTP 접근 제어
* 처음 전송되는 리소스의 도메인과 다른 도메인으로부터 리소스가 요청될 경우 해당 리소스는 cross-origin HTTP요청에 의해 요청된다 (http://A.com에서 http://B.com/image.jpg 를 요청하는 경우)
* 이때 보안상의 이유로, 브라우저들은 스크립트 내에서 초기화되는 cross-origin HTTP 요청을 제한한다
* CORS 매커니즘은 웹 서버에게 보안 cross-domain 데이터 전송을 활성화 하는 cross-domain 접근 제어권을 부여한다



## 설치
`$ npm install cors`




## 사용
``` javascript
// express에서 사용
// app.js 파일에서
const express = require('express')
const cors = require('cors');
const app = express();

app.use(cors(‘*’));

// 이렇게 하면 CORS를 허용해준다!
```





