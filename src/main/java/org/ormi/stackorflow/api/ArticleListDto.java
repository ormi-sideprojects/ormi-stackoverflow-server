package org.ormi.stackorflow.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import org.ormi.stackorflow.domain.article.Article;
import org.ormi.stackorflow.domain.article.LecturerListItemInfo;
import org.ormi.stackorflow.domain.article.LecturerDiscordContent;
import org.ormi.stackorflow.domain.article.QuestionContent;
import org.ormi.stackorflow.domain.article.QuestionListItemInfo;

/**
 * 필수 아닌 필드 (상황마다 다른경우) Wrapper class + null로 처리
 */
@Getter
@JsonInclude(Include.NON_NULL)
public class ArticleListDto {

  private long id;
  private String content;
  private String nickname;
  private Integer likeNumber;
  private int commentNumber;
  private boolean isLiked;
  private Boolean isResolved;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  private ZonedDateTime createdAt;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  private ZonedDateTime updatedAt;

  @Builder
  ArticleListDto(long id, String content, String nickname, Integer likeNumber, int commentNumber,
      Boolean isLiked, Boolean isResolved, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
    this.id = id;
    this.content = content;
    this.nickname = nickname;
    this.likeNumber = likeNumber;
    this.commentNumber = commentNumber;
    this.isLiked = isLiked;
    this.isResolved = isResolved;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  static public ArticleListDto fromDomain(Article article) {
    ArticleListDtoBuilder builder = ArticleListDto.builder();
    builder = builder.id(article.getId()).nickname(article.getProvider().nickname())
        .createdAt(article.getTimestamp().createdAt())
        .updatedAt(article.getTimestamp().updatedAt());
    if (article.getContent() instanceof LecturerDiscordContent lecturerDiscordContent) {
      builder = builder.content(lecturerDiscordContent.getContent());
    }
    if (article.getContent() instanceof QuestionContent questionContent) {
      builder = builder.content(questionContent.getContent());
    }
    if (article.getInfo() instanceof LecturerListItemInfo listItemInfo) {
      builder = builder.commentNumber(listItemInfo.commentNumber());
    }
    if (article.getInfo() instanceof QuestionListItemInfo questionListItemInfo) {
      builder = builder.commentNumber(questionListItemInfo.commentNumber())
          .likeNumber(questionListItemInfo.likeNumber())
          .isLiked(questionListItemInfo.isLiked())
          .isResolved(questionListItemInfo.isResolved());
    }
    return builder.build();
  }
}
