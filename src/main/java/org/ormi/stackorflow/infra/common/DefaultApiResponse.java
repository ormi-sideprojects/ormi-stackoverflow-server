package org.ormi.stackorflow.infra.common;

/**
 * 기본 응답 객체 입니다.
 * 응답 메시지와 결과 데이터 이외에 데이터는 담지 않습니다.
 * 추후 공통 응답 필드가 추가된다면 여기에 추가될 수 있습니다.
 * {@link AbstractResponse<T>}
 *
 * @author skaeodud0507
 */
public final class DefaultApiResponse<T> extends AbstractResponse<T> {

  public DefaultApiResponse(String message, T result) {
    super(message, result);
  }
}
