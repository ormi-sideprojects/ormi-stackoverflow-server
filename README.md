
/

# 컨벤션

## 메소드 네이밍 컨벤션
없을 경우 404혹은 null 관련 에러를 뱉지 않는 경우 prefix로 find를 사용한다.
없을경우 에러를 뱉는 경우 prefix로 get을 사용한다.

서비스의 도메인과 중복되는 요소일 경우 생략한다.  
BoxService.findBox() -> BoxService.find()


## 클래스 접근제어자 컨벤션
최초 작성시 무조건 final class (package-private)로 선언한다.
이후 필요할 때마다 조금씩 해제하는 방향으로 진행한다.

## 메소드 접급제어자 컨벤션
최초 작성시 무조건 private으로 선언한다.
이후 필요할 때마다 default(생략가능, 같은 폴더 내에서만 접근가능), public 순으로 조금씩 해제하는 방향으로 진행한다.

추가적으로 제안하는 컨벤션 입니다.
이 중 괜찮은 것만 논의후 골라서 사용해도 좋을 것 같습니다.
하지만 프로젝트 기간이 길지 않기 때문에 꼭 지켜야 하는 것만 지키고 넘어가는것이 좋아보입니다.

## 오르미 프로젝트에서 @Service @Component 어노테이션에 대한 정의
`@Service`:  비즈니스 로직
`@Component`:  재사용가능한 모듈에 대한 컴포넌트

## 각 레이어에서 정의된 dto는 역참조 될 수 없다.
e.g) 서비스 레이어에서 프레젠테이션 레이어에 정의된 dto는 반환금지
```java
@Service
public final class MyService {

  // wrong !
  public MyResponseDto any() {
  //...
  }

  // correct
  public MyServiceDto any() {
  // ...
  }
}

```

## Request, Response dto 네이밍
request, response 객체는 이름 뒤에 Request, Response로 짓는다.
```text
 ArticleReadRequest.java
 ArticleListResponse.java
 MemberCreateRequest.java
 MemberResponse.java
```

## 프레젠테이션 레이어에서 응답 데이터 조립
각 서비스 또는 컴포넌트들의 협력으로 나온 객체는 응답 객체에 의존적이지 않는게 좋아보입니다.
제가 제안하는 바는 다음과 같습니다.
- Service (비즈니스로직) 레이어에선 각 서비스의 역할에 맞는 객체만 반환합니다.
- Presentation Layer에서 각 서비스의 호출로 얻은 객체를 조립 후 프론트엔드에 응답합니다.

아래는 예제 코드 입니다.
이해를 돕기 위해 알아보기 쉽게 네이밍 한 점 이해부탁합니다.
```java
@RestController
public class ArticleController {
  private final ArticleReader articleReader;
  private final ArticleCommentReader memberReader;

  @GetMapping
  public Responses<ArticleWithCommentResponse> findArticles(@RequestParam("id") Long articleId) {
    ArticleServiceResult articleResult = articleReader.find(articleId);
    ArticleCommentServiceResult articleCommentResult = articleCommentReader.find(articleId);

    return Responses.paginated(ArticleWithCommentResponse.composite(articleResult, articleCommentResult));   
 }
}
```


## 레이어드 아키텍처
- api/Controller or Gateway(Websocket) [프레젠테이션]

- domain/Service [비즈니스]
    - domain/중간 컴포넌트 (선택) [지금은 간단한 CRUD 밖에 없어, 필요없을 수 있습니다.]
        - domain/Repository(Interface)

- infra/Repository(Impl) [퍼시스턴스] - 테스트 시 목업 레포지터리를 손쉽게 만들기 위함입니다.
    - infra/JpaRepository or QueryDslRepository or JDBC...

infra/Entity(package-private) 무조건 infra 레이어 반환 시(RepositoryInterface) domain/클래스로 매핑