package org.ormi.stackorflow.api.controller.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.ormi.stackorflow.core.domain.article.Article;
import org.ormi.stackorflow.core.domain.article.ArticleType;

record ArticleHomeThreadItemResponse(long id, UUID writerId, String writerNickname, String content,
                                     String discordChannelName, ArticleType type,
                                     Integer meTooNumber, Boolean isMeToo, Integer commentNumber,
                                     @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") ZonedDateTime createdAt,
                                     @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") ZonedDateTime updatedAt) {

  static ArticleHomeThreadItemResponse of(Article article) {
    return new ArticleHomeThreadItemResponse(article.getId(), article.getProvider().getMemberId(),
        article.getNickname(), article.getContent(), article.getDiscordChannelName(),
        article.getType(), null, null, null, article.getTimestamps().createdAt(),
        article.getTimestamps().updatedAt());
  }
}
