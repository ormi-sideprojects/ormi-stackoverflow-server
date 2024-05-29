package org.ormi.stackorflow.domain.article;

import java.util.List;
import lombok.Getter;

@Getter
final class ArticleDetailInfo implements ArticleInfo {

  private final List<Reaction> reactions;

  public ArticleDetailInfo(List<Reaction> reactions) {
    this.reactions = reactions;
  }
}
