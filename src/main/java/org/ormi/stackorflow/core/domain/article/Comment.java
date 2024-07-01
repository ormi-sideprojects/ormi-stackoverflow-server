package org.ormi.stackorflow.core.domain.article;

import lombok.Getter;
import org.ormi.stackorflow.core.domain.common.auth.Provider;
import org.ormi.stackorflow.core.domain.common.Timestamps;

@Getter
public class Comment {

  private final long id;
  private final long articleId;
  private final Provider provider;
  private final String writerNickname;
  private String content;
  private final boolean isSelected;
  private final int likeNumber;
  private final boolean isLiked;
  private final Timestamps timestamps;

  public Comment(long id, long articleId, Provider provider, String writerNickname, String content, Timestamps timestamps,
      boolean isSelected, int likeNumber, boolean isLiked) {
    this.id = id;
    this.articleId = articleId;
    this.provider = provider;
    this.writerNickname = writerNickname;
    this.content = content;
    this.timestamps = timestamps;
    this.isSelected = isSelected;
    this.likeNumber = likeNumber;
    this.isLiked = isLiked;
  }
}
