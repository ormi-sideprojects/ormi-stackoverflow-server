package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Attachment;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import java.util.List;

public record DiscordCreatedMessage(
    long createdAt,
    long guildId,
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

  public static DiscordCreatedMessage from(MessageCreateEvent event) {
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
    MessageChannel messageChannel = message.getChannel().block();

    return new DiscordCreatedMessage(
        message.getTimestamp().toEpochMilli(),
        guildId,
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
