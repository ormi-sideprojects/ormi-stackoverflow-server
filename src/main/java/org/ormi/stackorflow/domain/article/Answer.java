package org.ormi.stackorflow.domain.article;

import lombok.Getter;

@Getter
final class Answer implements Reaction {
  private final Comment comment;
  private final boolean isAdopted;

  public Answer(Comment comment, boolean isAdopted) {
    this.comment = comment;
    this.isAdopted = isAdopted;
  }
}
