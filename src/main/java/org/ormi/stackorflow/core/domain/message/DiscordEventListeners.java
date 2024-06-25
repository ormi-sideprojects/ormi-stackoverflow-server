package org.ormi.stackorflow.core.domain.message;

import org.ormi.stackorflow.infra.modules.discord.DiscordCreatedMessage;
import org.ormi.stackorflow.infra.modules.discord.DiscordMember;
import org.ormi.stackorflow.infra.modules.discord.DiscordMessageCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DiscordEventListeners {

  private final DiscordMessageProcessor discordMessageProcessor;

  public DiscordEventListeners(DiscordMessageProcessor discordMessageProcessor) {
    this.discordMessageProcessor = discordMessageProcessor;
  }

  @EventListener(DiscordMessageCreatedEvent.class)
  public void onReceiveDiscordMessage(DiscordMessageCreatedEvent event) {
    DiscordCreatedMessage discordMessage = event.message();
    DiscordMember discordMember = event.discordMember();

    DiscordDomainChannel channel = DiscordDomainChannel.from(event.discordChannel());
    DiscordDomainUser user = DiscordDomainUser.from(discordMember);
    Message message = new Message(discordMessage.messageId(), discordMessage.content(), user, channel);

    discordMessageProcessor.saveMessage(message);
  }
}
