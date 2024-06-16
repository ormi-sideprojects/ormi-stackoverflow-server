package org.ormi.stackorflow.infra.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.core.domain.article.Article;
import org.ormi.stackorflow.core.domain.article.Comment;
import org.ormi.stackorflow.core.domain.article.CreateArticle;
import org.ormi.stackorflow.core.domain.article.CreateComment;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepository implements
    org.ormi.stackorflow.core.domain.article.ArticleRepository {

  private final ArticleJpaRepository jpaRepository;
  private final ArticleCommentJpaRepository commentJpaRepository;

  @Override
  public List<Article> findAll() {
    List<ArticleEntity> entities = jpaRepository.findAll();
    return entities.stream().map(ArticleEntity::toDomain).toList();
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


}
