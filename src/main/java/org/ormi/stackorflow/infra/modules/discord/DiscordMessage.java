package org.ormi.stackorflow.infra.modules.discord;

public record DiscordMessage(
  String channelId,
  String message
) {

  public static DiscordMessage of(String channelId, String message) {
    return new DiscordMessage(channelId, message);
  }
}
