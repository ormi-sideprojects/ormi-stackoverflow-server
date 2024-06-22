package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.event.domain.message.MessageCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class DiscordMessageCreateEventListener implements DiscordListener<MessageCreateEvent> {

  @Override
  public Mono<Void> execute(MessageCreateEvent event) {
    log.info("message: {}", event.getMessage().getContent());
    return Mono.empty();
  }

  @Override
  public Class<MessageCreateEvent> type() {
    return MessageCreateEvent.class;
  }
}
