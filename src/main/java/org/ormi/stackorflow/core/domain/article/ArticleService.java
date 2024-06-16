package org.ormi.stackorflow.core.domain.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository repository;
  public void createArticle(CreateArticle article) {
    repository.create(article);
  }

  public List<Article> findAll() {
    return repository.findAll();
  }
  public void comment(CreateComment comment){
    Article article = repository.findOne(comment.articleId());
    if (article == null) throw new ArticleNotFoundException();
    repository.comment(article, comment);
  }
  public List<Comment> findArticleComments(long articleId) {
    Article article = repository.findOne(articleId);
    if(article == null) throw new ArticleNotFoundException();
    return repository.findArticleComment(article);
  }
}
