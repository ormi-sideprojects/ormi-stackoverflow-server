package org.ormi.stackorflow.core.domain.message;

import org.ormi.stackorflow.infra.modules.discord.DiscordChannel;

public record DiscordDomainChannel(
    long channelId,
    String channelName
) {

  public static DiscordDomainChannel from(DiscordChannel discordChannel) {
    return new DiscordDomainChannel(discordChannel.channelId(), discordChannel.channelName());
  }
}
