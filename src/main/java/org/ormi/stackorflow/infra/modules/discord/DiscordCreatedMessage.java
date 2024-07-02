package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Attachment;
import discord4j.core.object.entity.Message;
import java.util.List;

public record DiscordCreatedMessage(
    long messageId,
    long createdAt,
    long guildId,
    String content,
    List<DiscordMessageAttach> attaches,
    List<DiscordMember> mentions
) {

  public record DiscordMessageAttach(
      long id,
      String fileName,
      String fileUrl,
      long size,
      boolean isImageFile
  ) {

    private static DiscordMessageAttach of(Attachment attachment) {
      String filename = attachment.getFilename();

      return new DiscordMessageAttach(
          attachment.getId().asLong(),
          filename,
          attachment.getUrl(),
          attachment.getSize(),
          DiscordMessageContentPatterns.isImageFile(filename)
      );
    }
  }

  public static DiscordCreatedMessage from(MessageCreateEvent event) {
    Message message = event.getMessage();
    String messageContent = message.getData().content();
    long messageId = event.getMessage().getId().asLong();
    long guildId = event.getGuildId().isPresent() ?
        event.getGuildId().get().asLong() : DiscordConst.UNAVAILABLE_ID;
    List<DiscordMessageAttach> attaches = message.getAttachments().stream()
        .map(DiscordMessageAttach::of)
        .toList();
    List<DiscordMember> mentions = message.getMemberMentions().stream()
        .map(DiscordMember::from)
        .toList();

    return new DiscordCreatedMessage(
        messageId,
        message.getTimestamp().toEpochMilli(),
        guildId,
        messageContent,
        attaches,
        mentions
      );
  }

  public boolean isPresentGuildId() {
    return guildId != DiscordConst.UNAVAILABLE_ID;
  }
}
