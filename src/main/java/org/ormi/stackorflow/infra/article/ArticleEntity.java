package org.ormi.stackorflow.infra.article;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ormi.stackorflow.core.domain.article.Article;
import org.ormi.stackorflow.core.domain.article.ArticleType;
import org.ormi.stackorflow.core.domain.article.CreateArticle;
import org.ormi.stackorflow.core.domain.common.auth.Provider;
import org.ormi.stackorflow.core.domain.common.Timestamps;
import org.ormi.stackorflow.infra.common.BaseEntity;

@Entity
@Table(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ArticleEntity extends BaseEntity {

  @Id
  @GeneratedValue
  private long id;
  private UUID memberId;
  @NotNull
  private String content;
  @NotNull
  private String password;
  private String discordChannelName;
  @NotNull
  private ArticleType type;

  ArticleEntity(long id) {
    this.id = id;
  }

  ArticleEntity(UUID memberId, String content, String password, String discordChannelName,
      ArticleType type) {
    this.memberId = memberId;
    this.content = content;
    this.password = password;
    this.discordChannelName = discordChannelName;
    this.type = type;
  }

  Article toDomain() {
    return new Article(id, new Provider(memberId), content, password, discordChannelName, type,
        null, 0, new Timestamps(createdAt, updatedAt));
  }

  static ArticleEntity of(CreateArticle article) {
    return new ArticleEntity(article.provider().getMemberId(), article.content(),
        article.password(), article.discordChannelName(), article.type());
  }
}
