package org.ormi.stackorflow.core.domain.message;

import org.ormi.stackorflow.infra.message.DiscordChannelEntity;
import org.ormi.stackorflow.infra.message.DiscordMessageEntity;
import org.ormi.stackorflow.infra.message.DiscordMessageJpaRepository;
import org.ormi.stackorflow.infra.message.DiscordUserEntity;
import org.springframework.stereotype.Component;

@Component
public class DiscordMessageProcessor {

  private final DiscordMessageJpaRepository discordMessageJpaRepository;

  public DiscordMessageProcessor(DiscordMessageJpaRepository discordMessageJpaRepository) {
    this.discordMessageJpaRepository = discordMessageJpaRepository;
  }

  public void saveMessage(Message message) {
    DiscordMessageEntity messageEntity = DiscordMessageEntity.ofNewMessage(message);
    discordMessageJpaRepository.save(messageEntity);
  }

  public void saveMessageWith(Message message, DiscordUserEntity discordUser, DiscordChannelEntity discordChannel) {

  }
}
