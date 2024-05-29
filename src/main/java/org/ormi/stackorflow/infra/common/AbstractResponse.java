package org.ormi.stackorflow.infra.common;

import lombok.Getter;

/**
 * 공통 응답 최상위 클래스 입니다.
 * 기본적으로 응답 메시지와 결과 데이터를 담고 있습니다.
 * {@link AbstractResponse<T>}
 * @author 남대영
 */
@Getter
public abstract class AbstractResponse<T> {

  private final String message;
  private final T result;

  public AbstractResponse(String message, T result) {
    this.message = message;
    this.result = result;
  }

  public String message() {
    return message;
  }

  public T result() {
    return result;
  }
}
