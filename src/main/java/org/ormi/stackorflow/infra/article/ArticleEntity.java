package org.ormi.stackorflow.infra.article;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.ormi.stackorflow.domain.article.Article;
import org.ormi.stackorflow.domain.article.ArticleContent;
import org.ormi.stackorflow.domain.article.LecturerDiscordContent;
import org.ormi.stackorflow.domain.article.QuestionContent;
import org.ormi.stackorflow.domain.common.Provider;
import org.ormi.stackorflow.domain.common.Timestamp;
import org.ormi.stackorflow.infra.common.BaseEntity;
import org.ormi.stackorflow.infra.user.UserEntity;

@Entity
@Getter
@Table(name = "articles")
class ArticleEntity extends BaseEntity {

  @Id
  @GeneratedValue
  private long id;
  @JoinColumn(name = "user_id", updatable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private UserEntity user;
  @JoinColumn(name = "content_id", updatable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private ArticleContentEntity content;

  Article toArticle() {
    Provider provider = new Provider(user.getId(), user.getRole(), user.getNickname());
    ArticleContent articleContent = null;
    if (content instanceof QuestionEntity question) {
      articleContent = new QuestionContent(question.getContent());
    }
    if (content instanceof LecturerDiscordChatEntity lecturerDiscordChat) {
      articleContent = new LecturerDiscordContent(lecturerDiscordChat.getChannelName(),
          lecturerDiscordChat.getDiscordUser().toProvider(), lecturerDiscordChat.getContent());
    }
    Timestamp timestamp = new Timestamp(createdAt, updatedAt);
    return new Article(id, provider, articleContent, timestamp);
  }
}
