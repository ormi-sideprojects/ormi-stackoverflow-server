package org.ormi.stackorflow.core.domain.article;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ArticleForbiddenException extends HttpClientErrorException {

  public ArticleForbiddenException() {
    super(HttpStatus.FORBIDDEN, "해당 게시글에 관련된 권한이 없습니다.");
  }
}
