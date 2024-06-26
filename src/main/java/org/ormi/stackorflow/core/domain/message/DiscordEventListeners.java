package org.ormi.stackorflow.core.domain.message;

import org.ormi.stackorflow.infra.modules.discord.DiscordMessageCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DiscordEventListeners {

  private final DiscordMessageService discordMessageService;

  public DiscordEventListeners(DiscordMessageService discordMessageService) {
    this.discordMessageService = discordMessageService;
  }

  @EventListener(DiscordMessageCreatedEvent.class)
  public void onReceiveDiscordMessage(DiscordMessageCreatedEvent event) {
    discordMessageService.saveNewDiscordMessageByMessageEvent(event);
  }
}
