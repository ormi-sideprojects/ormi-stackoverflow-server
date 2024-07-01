package org.ormi.stackorflow.core.domain.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.core.domain.common.auth.Provider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository repository;
  public void createArticle(CreateArticle article) {
    repository.create(article);
  }

  public List<Article> findAll(Provider provider) {
    return repository.findAll(provider);
  }
  public void comment(CreateComment comment){
    Article article = getById(comment.articleId());
    repository.comment(article, comment);
  }
  public List<Comment> findArticleComments(long articleId) {
    Article article = getById(articleId);
    return repository.findArticleComment(article);
  }

  public void delete(Provider provider, long articleId) {
    Article article = getById(articleId);
    if(!article.isOwner(provider)) {
      throw new ArticleForbiddenException();
    }
    repository.delete(article);
  }


  private Article getById(long articleId) {
    Article article = repository.findOne(articleId);
    if(article == null) throw new ArticleNotFoundException();
    return article;
  }

  public void addMeToo(Provider provider, long articleId) {
    Article article = getById(articleId);
    if(repository.isExistMeToo(article, provider)) return;
    repository.addMeToo(article, provider);
  }
  public void deleteMeToo(Provider provider, long articleId) {
    Article article = getById(articleId);
    if(repository.isExistMeToo(article, provider)) return;
    repository.deleteMeToo(article, provider);
  }
}
