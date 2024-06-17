package org.ormi.stackorflow.infra.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.core.domain.article.Article;
import org.ormi.stackorflow.core.domain.article.Comment;
import org.ormi.stackorflow.core.domain.article.CreateArticle;
import org.ormi.stackorflow.core.domain.article.CreateComment;
import org.ormi.stackorflow.core.domain.common.Provider;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepository implements
    org.ormi.stackorflow.core.domain.article.ArticleRepository {

  private final ArticleJpaRepository jpaRepository;
  private final ArticleCommentJpaRepository commentJpaRepository;
  private final ArticleMeTooJpaRepository meTooJpaRepository;
  private final ArticleQueryDslRepository queryDslRepository;

  @Override
  public List<Article> findAll(Provider provider) {
    List<Article> articles = queryDslRepository.getHomeThreadList(provider);
    return articles;
  }

  @Override
  public Article findOne(long id) {
    ArticleEntity result = jpaRepository.findById(id);
    if (result == null) {
      return null;
    }
    return result.toDomain();
  }

  @Override
  public void create(CreateArticle article) {
    jpaRepository.save(ArticleEntity.of(article));
  }

  @Override
  public void comment(Article article, CreateComment comment) {
    ArticleCommentEntity createdComment = new ArticleCommentEntity(comment, article);
    commentJpaRepository.save(createdComment);
  }

  @Override
  public List<Comment> findArticleComment(Article article) {
    return commentJpaRepository.findByArticleId(article.getId()).stream()
        .map(ArticleCommentEntity::toDomain).toList();
  }

  @Override
  public void addMeToo(Article article, Provider provider) {
    ArticleMeTooEntity created = new ArticleMeTooEntity(article, provider);
    meTooJpaRepository.save(created);
  }

  @Override
  public void deleteMeToo(Article article, Provider provider) {
    meTooJpaRepository.deleteByArticleIdAndMemberId(article.getId(), provider.getMemberId());
  }

  @Override
  public boolean isExistMeToo(Article article, Provider provider) {
    return meTooJpaRepository.existsByArticleIdAndMemberId(article.getId(), provider.getMemberId());
  }


}
