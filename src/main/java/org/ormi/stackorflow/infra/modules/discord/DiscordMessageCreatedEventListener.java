package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.event.domain.message.MessageCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
final class DiscordMessageCreatedEventListener implements DiscordListener<MessageCreateEvent> {

  private final ApplicationEventPublisher eventPublisher;

  public DiscordMessageCreatedEventListener(ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  @Override
  public Mono<Void> execute(MessageCreateEvent event) {
    if (event.getMember().isEmpty()) {
      return Mono.empty();
    }
    DiscordMessageCreatedEvent applicationEvent = DiscordMessageCreatedEvent.from(event);
    eventPublisher.publishEvent(applicationEvent);

    return Mono.empty();
  }

  @Override
  public Class<MessageCreateEvent> type() {
    return MessageCreateEvent.class;
  }
}
