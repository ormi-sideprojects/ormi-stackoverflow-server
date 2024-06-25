package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import java.util.List;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DiscordLibraryInitializer<T extends Event> {

  private final List<DiscordListener<T>> discordEventListeners;
  private final GatewayDiscordClient discordClient;

  public DiscordLibraryInitializer(
      List<DiscordListener<T>> discordEventListeners,
      GatewayDiscordClient discordClient
  ) {
    this.discordEventListeners = discordEventListeners;
    this.discordClient = discordClient;
  }

  @EventListener(ApplicationStartedEvent.class)
  public void onApplicationStartup() {
    discordEventListeners.forEach(listener -> {
      discordClient.on(listener.type())
          .flatMap(listener::execute)
          .onErrorResume(listener::handleError)
          .subscribe();
    });
  }
}
