package org.ormi.stackorflow.infra.article;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface ArticleCommentJpaRepository extends JpaRepository<ArticleCommentEntity, Long> {
  List<ArticleCommentEntity> findByArticleId(long articleId);
}
