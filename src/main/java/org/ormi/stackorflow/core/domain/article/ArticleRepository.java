package org.ormi.stackorflow.core.domain.article;

import java.util.List;
import org.ormi.stackorflow.core.domain.common.Provider;

public interface ArticleRepository {
  List<Article> findAll();
  Article findOne(long id);
  void create(CreateArticle article);
  void comment(Article article, CreateComment comment);
  List<Comment> findArticleComment(Article article);
  void addMeToo(Article article, Provider provider);
  void deleteMeToo(Article article, Provider provider);
  boolean isExistMeToo(Article article, Provider provider);
}
