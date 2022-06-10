# WebServiceWithAWS

spring boot and AWS로 웹 서비스 구현
책이 19년도에 출판되서 버전이 많이 다르다
# 현 버전
1. 스프링 이니셜라이즈 사용하여 2.7.0 버전 
Gradle 7.x
자바 11

책과 병행하면서 현 버전에 맞게 수정하여 공부함.
물론 책 저자분 깃헙을 가시면 최신 버전 수정이 있지만 공부 겸 안보고 혼자 할려고 readme 작성

오류 및 수정을 여기해 계속 업데이트 할 예정.

# 22-06-10

junit 오류
@RunWith 어노테이션을 사용하는데 내가 지금 사용하는 junit은 5라 이건 4에서 사용한다고 함.
또 junit은 java8 밑으로는 4를 사용한다고 하여 자바 11이라 junit5 그냥 사용 함.

중간에 private MockMvc mvc; 라인이 있는데 빨간줄로 오류남.
구글링해서 찾음 @AutoConfigureMockMvc 어노테이션을 붙여줘야한다고 함.
근데도 빨간게 안없어짐. 그래서 그냥 disable inspection 누르니 빨간줄 사라짐.
테스트 해보니 잘 돌아감. 먼가 애매해서 위 어노테이션 주석 처리하고 다시 해보니 테스트 성공.
그냥 인텔리제이 오류...


