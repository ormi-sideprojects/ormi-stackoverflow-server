package org.ormi.stackorflow.core.domain.article;


import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

final class ArticleNotFoundException extends HttpClientErrorException {

  public ArticleNotFoundException() {
    super(HttpStatusCode.valueOf(404), "게시글을 찾을 수 없습니다.");
  }
}
