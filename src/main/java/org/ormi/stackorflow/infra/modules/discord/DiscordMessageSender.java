package org.ormi.stackorflow.infra.modules.discord;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import org.ormi.stackorflow.infra.common.annotations.Unstable;
import org.springframework.stereotype.Component;

@Component
public class DiscordMessageSender {

  private final GatewayDiscordClient discordClient;

  public DiscordMessageSender(GatewayDiscordClient discordClient) {
    this.discordClient = discordClient;
  }

  @Unstable
  public void sendMessage(DiscordMessage discordMessage) {
    // TODO: 아직 테스트 안됨, 개발중, 레퍼런스 좀더 필요
    discordClient.getChannelById(Snowflake.of(discordMessage.channelId()))
        .ofType(GuildMessageChannel.class)
        .flatMap(channel -> channel.createMessage(discordMessage.message()))
        .subscribe();
  }
}
