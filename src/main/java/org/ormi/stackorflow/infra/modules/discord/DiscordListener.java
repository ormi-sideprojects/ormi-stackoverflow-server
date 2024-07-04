package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.event.domain.Event;
import reactor.core.publisher.Mono;

public interface DiscordListener<T extends Event> {

  Mono<Void> execute(T event);

  Class<T> type();

  default Mono<Void> handleError(Throwable throwable) {
    return Mono.error(throwable);
  }
}
