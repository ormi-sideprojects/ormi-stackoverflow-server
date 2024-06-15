package org.ormi.stackorflow.infra.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.core.domain.article.Article;
import org.ormi.stackorflow.core.domain.article.CreateArticle;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepository implements
    org.ormi.stackorflow.core.domain.article.ArticleRepository {

  private final ArticleJpaRepository jpaRepository;

  @Override
  public List<Article> findAll() {
    List<ArticleEntity> entities = jpaRepository.findAll();
    return entities.stream().map(ArticleEntity::toDomain).toList();
  }

  @Override
  public void create(CreateArticle article) {
    jpaRepository.save(ArticleEntity.of(article));
  }
}
