package org.ormi.stackorflow.core.domain.article;

import java.util.List;

public interface ArticleRepository {
  List<Article> findAll();
  void create(CreateArticle article);
}
