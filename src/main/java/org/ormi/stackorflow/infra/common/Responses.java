package org.ormi.stackorflow.infra.common;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

/**
 * {@link ResponseEntity<T>}를 상속받은 응답 객체 중앙 클래스 입니다.
 * 여러 타입의 응답 상황을 구현합니다.
 *
 * @author 남대영
 */
public class Responses<T> extends ResponseEntity<Object> {

  Responses(HttpStatusCode status, AbstractResponse<T> response) {
    super(response, status);
  }

  Responses(HttpStatusCode statusCode, ErrorApiResponse<T> response) {
    super(response, statusCode);
  }

  /**
   * 성공 응답 객체를 만들지만 응답 데이터는 null 입니다.
   *
   * @param message 응답 메시지
   * @return 성공 응답 데이터
   */
  public static Responses<Void> ok(String message) {
    return ok(message, null);
  }

  /**
   * 응답 메시지와 응답 데이터를 함께 생성합니다.
   *
   * @param message 응답 메시지
   * @param result 응답 데이터
   * @return 성공 응답 데이터
   */
  public static <U> Responses<U> ok(String message, U result) {
    return of(HttpStatus.OK, message, result);
  }

  /**
   * 페이지네이션 응답 데이터를 생성합니다.
   *
   * @param message 응답 메시지
   * @param result 응답 리스트 데이터
   * @param pagedMetadata 페이지네이션 메타데이터
   * @return 성공 응답 페이지네이션 데이터
   */
  public static <U> Responses<List<U>> paginated(
      String message,
      List<U> result,
      PaginationApiResponse.PagedMetadata pagedMetadata
  ) {
    return new Responses<>(HttpStatus.OK, new PaginationApiResponse<>(message, result, pagedMetadata));
  }

  /**
   * 201 응답을 생성 하지만 결과 데이터는 null 입니다.
   *
   * @param message 응답 메시지
   * @return 201 성공응답 데이터
   */
  public static <U> Responses<U> created(String message) {
    return of(HttpStatus.CREATED, message);
  }

  /**
   * 201 응답을 생성 하며, 응답 메시지와 결과 데이터도 함께 생성합니다.
   *
   * @param message 응답 메시지
   * @param result 응답 결과
   * @return 201 성공 응답 데이터
   */
  public static <U> Responses<U> created(String message, U result) {
    return of(HttpStatus.CREATED, message, result);
  }

  /**
   * 400 에러 응답을 생성 하며, 응답 메시지만 생성합니다.
   *
   * @param message 응답 메시지
   * @return 400 에러 응답 데이터
   */
  public static <U> Responses<U> badRequest(String message) {
    return badRequest(message, null);
  }

  /**
   * 400 에러 응답을 생성 하며, 응답 메시지와 에러 코드를 생성합니다.
   *
   * @param message 응답 메시지
   * @param errorCode 에러 코드
   * @return 400 에러 응답 데이터
   */
  public static <U> Responses<U> badRequest(String message, String errorCode) {
    return error(HttpStatus.BAD_REQUEST, message, errorCode);
  }

  /**
   * 에러 응답을 생성 하며, 에러 메시지만 생성 합니다.
   *
   * @param status 응답 상태 코드
   * @param message 에러 메시지
   * @return 에러 응답 데이터
   */
  public static <U> Responses<U> error(HttpStatusCode status, String message) {
    return error(status, message, null);
  }

  /**
   * 에러 응답을 생성 하며, 에러 메시지와 에러 코드를 생성 합니다.
   *
   * @param status 응답 상태 코드
   * @param message 에러 메시지
   * @param errorCode 에러 코드
   * @return 에러 응답 데이터
   */
  public static <U> Responses<U> error(HttpStatusCode status, String message, String errorCode) {
    return new Responses<>(status, new ErrorApiResponse<>(message, errorCode));
  }

  /**
   * 응답 코드와 메시지로 응답 데이터를 생성합니다.
   *
   * @param status 응답 상태 코드
   * @param message 응답 메시지
   * @return 에러 응답 데이터
   */
  public static <U> Responses<U> of(HttpStatusCode status, String message) {
    return of(status, message, null);
  }

  /**
   * 응답 코드와 메시지로 응답 데이터를 생성합니다.
   * 응답 코드에 따라 error 응답이 생성 될 수 있습니다.
   *
   * @param status 응답 상태 코드
   * @param message 응답 메시지
   * @param result 응답 데이터
   * @return 에러 응답 데이터
   */
  public static <U> Responses<U> of(HttpStatusCode status, String message, U result) {
    if (status.isError()) {
      return error(status, message);
    }
    return new Responses<>(status, new DefaultApiResponse<>(message, result));
  }
}
