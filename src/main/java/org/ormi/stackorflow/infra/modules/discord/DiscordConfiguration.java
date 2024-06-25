package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfiguration {

  @Bean
  public GatewayDiscordClient discordClient(DiscordBotToken discordBotToken) {
    GatewayDiscordClient discordClient = DiscordClientBuilder.create(discordBotToken.getBotToken())
        .build()
        .login()
        .block();
    Objects.requireNonNull(discordClient, "discordClient");

    return discordClient;
  }
}
