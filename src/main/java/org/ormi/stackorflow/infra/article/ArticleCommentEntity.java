package org.ormi.stackorflow.infra.article;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.ormi.stackorflow.core.domain.article.Article;
import org.ormi.stackorflow.core.domain.article.Comment;
import org.ormi.stackorflow.core.domain.article.CreateComment;
import org.ormi.stackorflow.core.domain.common.auth.Provider;
import org.ormi.stackorflow.core.domain.common.Timestamps;
import org.ormi.stackorflow.infra.common.BaseEntity;

@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class ArticleCommentEntity extends BaseEntity {

  @Id
  @GeneratedValue
  private long id;
  @JoinColumn(name = "article_id")
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private ArticleEntity article;
  private UUID memberId;
  @NotNull
  private String content;

  ArticleCommentEntity(CreateComment comment, Article article) {
    this.article = new ArticleEntity(article.getId());
    this.memberId = comment.provider().getMemberId();
    this.content = comment.content();
  }

  Comment toDomain() {
    return new Comment(id, article.getId(), new Provider(memberId), null, content,
        new Timestamps(createdAt, updatedAt), false, 0, false);
  }
}
