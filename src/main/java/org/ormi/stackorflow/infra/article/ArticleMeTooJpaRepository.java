package org.ormi.stackorflow.infra.article;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleMeTooJpaRepository extends JpaRepository<ArticleMeTooEntity, Long> {
  int countByArticleId(long articleId);
  void deleteByArticleIdAndMemberId(long articleId, UUID memberId);
  boolean existsByArticleIdAndMemberId(long articleId, UUID memberId);
}
