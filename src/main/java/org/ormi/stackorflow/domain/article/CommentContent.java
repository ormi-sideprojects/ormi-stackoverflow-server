package org.ormi.stackorflow.domain.article;

import java.util.List;
import lombok.Getter;
import org.ormi.stackorflow.domain.common.DiscordProvider;

@Getter
public final class CommentContent {

  private final String content;
  private final List<DiscordProvider> mentions;

  public CommentContent(String content, List<DiscordProvider> mentions) {
    this.content = content;
    this.mentions = mentions;
  }
}
