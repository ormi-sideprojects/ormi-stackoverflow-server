package org.ormi.stackorflow.infra.article;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {
  @Query("select a from ArticleEntity a left join fetch a.content left join fetch a.user")
  List<ArticleEntity> findAllForList();

}
