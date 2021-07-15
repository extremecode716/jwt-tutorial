# Spring Security, JWT Tutorial

Dependencies: Spring Web, Spirng Security, Spirng Data JPA, H2 Database, Lombok, Validation, jwt

## JWT : JSON Web Token
   
RFC 7519 웹 표준으로 지정이 되어있고 JSON 객체를 사용해서 토큰 자체에 정보들을 저장하고 있는 Web Token이라고 정의할 수 있습니다. 

특히, JWT를 이용하는 방식은 헤비하지 않고 아주 간편하고 쉽게 적용할 수 있어서 사이드 프로젝트를 진행할떄는 아주 유용한 방식이 아닐까 합니다.   

#### JWT는 Header, Payload, Signature 3개의 부분으로 구성되어져 있습니다.
**Header**: Signature를 해싱하기 위한 알고리즘 정보들이 담겨있고
**Payload**는 서버와 클라이언트가 주고받는, 시스템에서 실제로 사용될 정보에 대한 내용들을 담고있습니다.
**Signature**는 토큰의 유효성 검증을 위한 문자열입니다.

**JWT 장점:**
중앙의 인증서버, 데이터 스토어에 대한 의존성 없음, 시스템 수평 확장 유리
Base64 URL Safe Encoding > URL, Cookie, Header 모두 사용 가능

**JWT 단점:**
Payload의 정보가 많아지면 네트워크 사용량 증가, 데이터 설계 고려 필요
토큰이 클라이언트에 저장, 서버에서 클라인트의 토큰을 조작할 수 없음.
