package org.ormi.stackorflow.infra.modules.discord;

public record DiscordMessageCreatedEvent(
    DiscordChannel discordChannel,
    DiscordCreatedMessage message
) {

  public static DiscordMessageCreatedEvent from(DiscordChannel channel, DiscordCreatedMessage message) {
    return new DiscordMessageCreatedEvent(channel, message);
  }
}
