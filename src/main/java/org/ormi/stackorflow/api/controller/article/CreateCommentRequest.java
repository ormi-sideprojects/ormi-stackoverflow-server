package org.ormi.stackorflow.api.controller.article;

import org.ormi.stackorflow.core.domain.article.CreateComment;
import org.ormi.stackorflow.core.domain.common.auth.Provider;

public record CreateCommentRequest(String content) {
  CreateComment toDomain(Provider provider, long articleId) {
    return new CreateComment(provider, articleId, content);
  }
}
