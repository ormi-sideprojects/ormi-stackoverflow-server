package org.ormi.stackorflow.core.domain.article;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import org.ormi.stackorflow.core.domain.common.auth.Provider;
import org.ormi.stackorflow.core.domain.common.Timestamps;

@Getter
public class Article {
  private final long id;
  private final Provider provider;
  private final String nickname;
  private String content;
  private final String discordChannelName;
  private final ArticleType type;
  private final ArticleMeToo meToo;
  private final int commentNumber;
  private final Timestamps timestamps;

  boolean isOwner(Provider provider) {
    return this.provider.equals(provider);
  }
  @QueryProjection
  public Article(long id, Provider provider, String nickname, String content,
      String discordChannelName, ArticleType type, ArticleMeToo meToo, int commentNumber,
      Timestamps timestamps) {
    this.id = id;
    this.provider = provider;
    this.nickname = nickname;
    this.content = content;
    this.discordChannelName = discordChannelName;
    this.type = type;
    this.meToo = meToo;
    this.commentNumber = commentNumber;
    this.timestamps = timestamps;
  }
}
