package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import java.util.Objects;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfiguration {

  @Bean
  public <T extends Event> GatewayDiscordClient discordClient(
      DiscordBotToken discordBotToken,
      Set<DiscordListener<T>> discordListeners
  ) {
    GatewayDiscordClient discordClient = DiscordClientBuilder.create(discordBotToken.getBotToken())
        .build()
        .login()
        .block();
    Objects.requireNonNull(discordClient, "discordClient");

    discordListeners.forEach(listener -> {
      discordClient.on(listener.type())
          .flatMap(listener::execute)
          .onErrorResume(listener::handleError)
          .subscribe();
    });

    return discordClient;
  }
}
