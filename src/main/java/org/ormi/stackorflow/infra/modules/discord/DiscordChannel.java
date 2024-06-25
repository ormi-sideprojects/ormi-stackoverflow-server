package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.object.entity.channel.Channel;
import discord4j.discordjson.json.ChannelData;

public record DiscordChannel(
    long channelId,
    String channelName
) {

  public static DiscordChannel from(ChannelData channel) {
    return new DiscordChannel(channel.id().asLong(), channel.name().get());
  }
}
