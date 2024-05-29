package org.ormi.stackorflow.infra.common;

import lombok.Getter;

/**
 * 에러에 대한 공통 응답 입니다.
 * 에러 메시지와 에러 코드를 가집니다.
 * 에러 코드에 대한 구현은 추후 바뀔 수 있습니다.
 *
 * @author 남대영
 */
@Getter
public class ErrorApiResponse<T> {

  private final String message;
  private final String errorCode;

  public ErrorApiResponse(String message) {
    this(message, null);
  }

  public ErrorApiResponse(String message, String errorCode) {
    this.message = message;
    this.errorCode = errorCode;
  }
}
