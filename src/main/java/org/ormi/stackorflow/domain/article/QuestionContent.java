package org.ormi.stackorflow.domain.article;

import lombok.Getter;

/**
 * {@link org.ormi.stackorflow.domain.common.Role#ANONYMOUS}사용자가 작성할 수 있는
 * {@link Article}의 한 종류인 질문 컨텐츠의 내용입니다.
 *
 */
@Getter
public final class QuestionContent implements ArticleContent {
  private String content;

  public QuestionContent(String content) {
    this.content = content;
  }
}
