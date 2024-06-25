package org.ormi.stackorflow.core.domain.message;

import java.util.Optional;
import org.ormi.stackorflow.infra.message.DiscordUserEntity;
import org.ormi.stackorflow.infra.message.DiscordUserJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class DiscordUserProcessor {

  private final DiscordUserJpaRepository discordUserJpaRepository;

  public DiscordUserProcessor(DiscordUserJpaRepository discordUserJpaRepository) {
    this.discordUserJpaRepository = discordUserJpaRepository;
  }

  public void saveIfNotExists(DiscordDomainUser user) {
    Optional<DiscordUserEntity> discordUserEntity = discordUserJpaRepository.findById(user.discordId());
    if (discordUserEntity.isPresent()) {
      return;
    }
    discordUserJpaRepository.save(DiscordUserEntity.of(user));
  }
}
