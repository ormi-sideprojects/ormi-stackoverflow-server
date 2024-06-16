package org.ormi.stackorflow.core.domain.article;

import java.util.List;

public interface ArticleRepository {
  List<Article> findAll();
  Article findOne(long id);
  void create(CreateArticle article);
  void comment(Article article, CreateComment comment);
  List<Comment> findArticleComment(Article article);
}
