package org.ormi.stackorflow.infra.article;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.ormi.stackorflow.core.domain.article.Article;
import org.ormi.stackorflow.core.domain.common.Provider;
import org.ormi.stackorflow.infra.common.BaseEntity;

@Entity
@Table(name = "article_me_toos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleMeTooEntity extends BaseEntity {
  @Id @GeneratedValue
  private long id;
  @JoinColumn(name = "article_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private ArticleEntity article;
  private UUID memberId;

  public ArticleMeTooEntity(Article article, Provider provider) {
    this.article = new ArticleEntity(article.getId());
    this.memberId = provider.getMemberId();
  }
}
