package org.ormi.stackorflow.core.domain.message;

import jakarta.transaction.Transactional;
import org.ormi.stackorflow.infra.modules.discord.DiscordCreatedMessage;
import org.ormi.stackorflow.infra.modules.discord.DiscordMember;
import org.ormi.stackorflow.infra.modules.discord.DiscordMessageCreatedEvent;
import org.springframework.stereotype.Service;

@Service
public class DiscordMessageService {

  private final DiscordMessageProcessor discordMessageProcessor;
  private final DiscordUserProcessor discordUserProcessor;
  private final DiscordChannelProcessor discordChannelProcessor;

  public DiscordMessageService(
      DiscordMessageProcessor discordMessageProcessor,
      DiscordUserProcessor discordUserProcessor,
      DiscordChannelProcessor discordChannelProcessor
  ) {
    this.discordMessageProcessor = discordMessageProcessor;
    this.discordUserProcessor = discordUserProcessor;
    this.discordChannelProcessor = discordChannelProcessor;
  }

  @Transactional
  public void saveNewDiscordMessageByMessageEvent(DiscordMessageCreatedEvent messageCreatedEvent) {
    DiscordCreatedMessage discordMessage = messageCreatedEvent.message();
    DiscordMember discordMember = messageCreatedEvent.discordMember();

    DiscordDomainChannel channel = DiscordDomainChannel.from(messageCreatedEvent.discordChannel());
    discordChannelProcessor.saveIfNotExists(channel);

    DiscordDomainUser user = DiscordDomainUser.from(discordMember);
    discordUserProcessor.saveIfNotExists(user);

    MessageContent messageContent = MessageContent.of(discordMessage.messageId(), discordMessage.content());
    Message message = new Message(messageContent, user, channel);

    discordMessageProcessor.saveMessage(message);
  }
}
