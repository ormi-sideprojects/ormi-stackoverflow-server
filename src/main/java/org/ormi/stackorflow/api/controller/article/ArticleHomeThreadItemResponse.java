package org.ormi.stackorflow.api.controller.article;

import java.util.UUID;
import org.ormi.stackorflow.core.domain.article.Article;
import org.ormi.stackorflow.core.domain.article.ArticleType;

record ArticleHomeThreadItemResponse(long id, UUID writerId, String writerNickname,
                                            String content, String discordChannelName,
                                            ArticleType type, Integer meTooNumber, Boolean isMeToo,
                                            Integer commentNumber) {

  static ArticleHomeThreadItemResponse of(Article article) {
    return new ArticleHomeThreadItemResponse(article.getId(), article.getProvider().getMemberId(),
        article.getNickname(), article.getContent(), article.getDiscordChannelName(),
        article.getType(), null, null, null);
  }
}
