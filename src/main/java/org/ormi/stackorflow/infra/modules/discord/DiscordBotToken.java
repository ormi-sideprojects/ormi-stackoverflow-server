package org.ormi.stackorflow.infra.modules.discord;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "discord")
public class DiscordBotToken {

  private String botToken;
}
