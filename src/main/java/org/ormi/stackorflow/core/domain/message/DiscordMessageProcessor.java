package org.ormi.stackorflow.core.domain.message;

import org.ormi.stackorflow.infra.message.DiscordMessageEntity;
import org.ormi.stackorflow.infra.message.DiscordMessageJpaRepository;
import org.springframework.stereotype.Component;

@Component
public final class DiscordMessageProcessor {

  private final DiscordMessageJpaRepository discordMessageJpaRepository;

  public DiscordMessageProcessor(DiscordMessageJpaRepository discordMessageJpaRepository) {
    this.discordMessageJpaRepository = discordMessageJpaRepository;
  }

  public void saveMessage(Message message) {
    DiscordMessageEntity messageEntity = DiscordMessageEntity.ofNewMessage(message);
    discordMessageJpaRepository.save(messageEntity);
  }
}
