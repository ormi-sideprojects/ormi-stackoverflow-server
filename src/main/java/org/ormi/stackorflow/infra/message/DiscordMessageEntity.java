package org.ormi.stackorflow.infra.message;

import static java.util.Objects.requireNonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.ormi.stackorflow.core.domain.message.DiscordDomainChannel;
import org.ormi.stackorflow.core.domain.message.DiscordDomainUser;
import org.ormi.stackorflow.core.domain.message.Message;
import org.ormi.stackorflow.core.domain.message.MessageContent;

@Entity
@Table(name = "discord_message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscordMessageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long messageId;

  @NotNull
  private String content;

  private long discordUserId;

  private long discordChannelId;

  @Builder
  DiscordMessageEntity(long messageId, String content, long discordUserId, long discordChannelId) {
    this.messageId = messageId;
    this.content = content;
    this.discordUserId = discordUserId;
    this.discordChannelId = discordChannelId;
  }

  public void updateContent(String newContent) {
    requireNonNull(newContent, "newContent");
    this.content = newContent;
  }

  public MessageContent toDomainObject() {
    return new MessageContent(messageId, content);
  }

  public static DiscordMessageEntity ofNewMessage(Message message) {
    return of(message.messageContent(), message.user(), message.channel());
  }

  public static DiscordMessageEntity of(MessageContent message, DiscordDomainUser discordUser, DiscordDomainChannel discordChannel) {
    return DiscordMessageEntity
        .builder()
        .messageId(message.messageId())
        .discordUserId(discordUser.discordId())
        .discordChannelId(discordChannel.channelId())
        .content(message.content())
        .build();
  }
}
