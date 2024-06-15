package org.ormi.stackorflow.core.domain.article;

import lombok.Getter;
import org.ormi.stackorflow.core.domain.common.Provider;

@Getter
public class Article {
  private final long id;
  private final Provider provider;
  private final String nickname;
  private String content;
  private final String discordChannelName;
  private final ArticleType type;
  private final ArticleMeToo meToo;
  private final ArticleComment comment;


  public Article(long id, Provider provider, String nickname, String content,
      String discordChannelName, ArticleType type, ArticleMeToo meToo, ArticleComment comment) {
    this.id = id;
    this.provider = provider;
    this.nickname = nickname;
    this.content = content;
    this.discordChannelName = discordChannelName;
    this.type = type;
    this.meToo = meToo;
    this.comment = comment;
  }
}
