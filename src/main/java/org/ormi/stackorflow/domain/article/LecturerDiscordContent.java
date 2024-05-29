package org.ormi.stackorflow.domain.article;

import lombok.Getter;
import org.ormi.stackorflow.domain.common.DiscordProvider;

@Getter
public final class LecturerDiscordContent implements ArticleContent{
  private final String channelName;
  private final DiscordProvider discordUser;
  private final String content;

  public LecturerDiscordContent(String channelName, DiscordProvider discordUser, String content) {
    this.channelName = channelName;
    this.discordUser = discordUser;
    this.content = content;
  }
}
