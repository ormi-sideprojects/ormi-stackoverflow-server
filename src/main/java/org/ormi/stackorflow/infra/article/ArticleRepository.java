package org.ormi.stackorflow.infra.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.domain.article.Article;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ArticleRepository implements org.ormi.stackorflow.domain.article.ArticleRepository {
  private final ArticleJpaRepository jpaRepository;

  @Override
  public List<Article> findAll() {
    List<ArticleEntity> articles = jpaRepository.findAllForList();
//    나중에 좋아요 join 연산 추가 (QueryDsl사용)
    return articles.stream().map(ArticleEntity::toArticle).toList();
  }
}
