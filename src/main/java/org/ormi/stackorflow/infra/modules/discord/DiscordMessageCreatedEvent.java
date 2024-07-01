package org.ormi.stackorflow.infra.modules.discord;

public record DiscordMessageCreatedEvent(
    DiscordChannel discordChannel,
    DiscordCreatedMessage message,
    DiscordMember discordMember
) {

  static DiscordMessageCreatedEvent from(
      DiscordChannel channel,
      DiscordCreatedMessage message,
      DiscordMember discordMember
  ) {
    return new DiscordMessageCreatedEvent(channel, message, discordMember);
  }
}
