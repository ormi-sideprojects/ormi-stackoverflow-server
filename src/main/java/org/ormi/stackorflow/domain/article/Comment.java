package org.ormi.stackorflow.domain.article;

import lombok.Getter;
import org.ormi.stackorflow.domain.common.Provider;
import org.ormi.stackorflow.domain.common.Timestamp;

@Getter
final class Comment {

  private final long id;
  private final Provider provider;
  private final Timestamp timestamp;
  private final CommentContent content;

  public Comment(long id, Provider provider, Timestamp timestamp, CommentContent content) {
    this.id = id;
    this.provider = provider;
    this.timestamp = timestamp;
    this.content = content;
  }
}
