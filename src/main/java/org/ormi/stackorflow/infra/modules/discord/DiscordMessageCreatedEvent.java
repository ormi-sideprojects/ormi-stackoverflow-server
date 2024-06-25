package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Attachment;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import java.util.List;

public record DiscordMessageCreatedEvent(
    long createdAt,
    long guildId,
    long channelId,
    long memberId,
    String nickname,
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

    public static DiscordMessageAttach of(Attachment attachment) {
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

  public static DiscordMessageCreatedEvent from(MessageCreateEvent event) {
    Message message = event.getMessage();
    String messageContent = message.getData().content();
    String nickname = "";
    long memberId = DiscordConst.UNAVAILABLE_ID;
    long guildId = event.getGuildId().isPresent() ?
        event.getGuildId().get().asLong() : DiscordConst.UNAVAILABLE_ID;
    if (event.getMember().isPresent()) {
      Member member = event.getMember().get();
      nickname = member.getNickname().orElse("");
      memberId = member.getId().asLong();
    }
    List<DiscordMessageAttach> attaches = message.getAttachments().stream()
        .map(DiscordMessageAttach::of)
        .toList();
    List<DiscordMember> mentions = message.getMemberMentions().stream()
        .map(DiscordMember::from)
        .toList();

    return new DiscordMessageCreatedEvent(
        message.getTimestamp().toEpochMilli(),
        guildId,
        message.getChannelId().asLong(),
        memberId,
        nickname,
        messageContent,
        attaches,
        mentions
      );
  }

  public boolean isPresentGuildId() {
    return guildId != DiscordConst.UNAVAILABLE_ID;
  }
}
