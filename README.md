# 클래스 네이밍 컨벤션

합성을 이용해 클래스 분리를 시도할 때,
변경 가능한 요소는 postfix로 Content를 사용한다.
게시글 내용, 게시글 비밀번호, 멘션목록 등
ex) Article, ArticleContent


변경 불가능한 요소는 postfix로 Info를 사용한다.
좋아요 개수, 댓글 개수 등
ex) Article, ArticleInfo


# 메소드 네이밍 컨벤션
없을 경우 404혹은 null 관련 에러를 뱉지 않는 경우 prefix로 find를 사용한다.
없을경우 에러를 뱉는 경우 prefix로 get을 사용한다.

서비스의 도메인과 중복되는 요소일 경우 생략한다.  
BoxService.findBox() -> BoxService.find()


# 클래스 접근제어자 컨벤션
최초 작성시 무조건 final class (package-private)로 선언한다.
이후 필요할 때마다 조금씩 해제하는 방향으로 진행한다.

# 메소드 접급제어자 컨벤션
최초 작성시 무조건 private으로 선언한다.
이후 필요할 때마다 default(생략가능, 같은 폴더 내에서만 접근가능), public 순으로 조금씩 해제하는 방향으로 진행한다.


