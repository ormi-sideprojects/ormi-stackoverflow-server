package org.ormi.stackorflow.domain.common;
/*
  API를 사용하는 주체의 인증정보를 담아두는 클래스입니다.
 */
public record Provider(long id, Role role, String nickname) {

}
