package org.ormi.stackorflow.infra.modules.discord;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import org.springframework.stereotype.Component;

@Component
public final class DiscordMessageSender {

  private final GatewayDiscordClient discordClient;

  public DiscordMessageSender(GatewayDiscordClient discordClient) {
    this.discordClient = discordClient;
  }

  public void sendMessage(DiscordMessage discordMessage) {
    Snowflake snowflakeChannelId = Snowflake.of(discordMessage.channelId());
    discordClient.rest().getChannelById(snowflakeChannelId)
        .createMessage(discordMessage.message())
        .block();
  }
}
