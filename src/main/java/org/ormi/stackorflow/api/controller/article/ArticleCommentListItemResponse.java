package org.ormi.stackorflow.api.controller.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.ormi.stackorflow.core.domain.article.Comment;

record ArticleCommentListItemResponse(long id, long articleId, UUID ownerId,
                                             String ownerNickname, String content,
                                             boolean isSelected, int likeNumber,
                                             @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") ZonedDateTime createdAt,
                                             @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") ZonedDateTime updatedAt

) {

  static ArticleCommentListItemResponse fromDomain(Comment comment) {
    return new ArticleCommentListItemResponse(comment.getId(), comment.getArticleId(),
        comment.getProvider().getMemberId(), "익명", comment.getContent(), comment.isSelected(),
        comment.getLikeNumber(), comment.getTimestamps().createdAt(),
        comment.getTimestamps().updatedAt());
  }
}
