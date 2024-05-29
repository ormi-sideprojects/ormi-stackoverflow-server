package org.ormi.stackorflow.domain.common;

import java.time.ZonedDateTime;

/*
  생성시점, 업데이트시점을 담는 클래스입니다.
  DB이외에서 변경되지 않으므로 final로 선언합니다.
 */
public record Timestamp(ZonedDateTime createdAt, ZonedDateTime updatedAt) {

}
