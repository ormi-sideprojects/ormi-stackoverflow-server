package org.ormi.stackorflow.api.controller.article;

import org.ormi.stackorflow.core.domain.article.ArticleType;
import org.ormi.stackorflow.core.domain.article.CreateArticle;
import org.ormi.stackorflow.core.domain.common.auth.Provider;

record CreateArticleRequest(String content, String password) {

  CreateArticle toDomain(Provider provider) {
    return new CreateArticle(provider, content, null, password, ArticleType.NORMAL);
  }
}
