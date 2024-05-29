package org.ormi.stackorflow.domain.common;
/*
  초기 서비스에서 익명으로 사용해야하므로 ANONYMOUS
  강사 구분을 위한 LECTURER
  관리자 구분을 위한 ADMIN
  추후 추가될 STUDENT
  를 포함하며 인가에 활용됩니다.
 */
public enum Role {
  ANONYMOUS, ADMIN, LECTURER, STUDENT
}
